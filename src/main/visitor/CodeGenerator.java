package main.visitor;

import main.ast.nodes.Program;
import main.ast.nodes.Stmt.*;
import main.ast.nodes.declaration.FuncDec;
import main.ast.nodes.declaration.Main;
import main.ast.nodes.expr.BinaryExpr;
import main.ast.nodes.expr.Expr;
import main.ast.nodes.expr.UnaryExpr;
import main.ast.nodes.type.BooleanType;
import main.ast.nodes.type.IntType;
import main.ast.nodes.type.StringType;
import main.ast.nodes.type.Type;
import main.ast.nodes.value.Identifier;
import main.ast.nodes.value.primitiveVals.BooleanValue;
import main.ast.nodes.value.primitiveVals.IntValue;
import main.ast.nodes.value.primitiveVals.StringValue;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.item.VarDecSymbolTableItem;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CodeGenerator extends Visitor<String>{
    private SymbolTable currentSymbolTable;
    private final ExpressionTypeEvaluator expressionTypeEvaluator = new ExpressionTypeEvaluator();

    private final String outputPath;
    private String currentCommand = "";

    private FileWriter mainFile;
    public CodeGenerator() {
        outputPath = "./codeGenOutput/";
        prepareOutputFolder();
    }

    private void prepareOutputFolder(){
        try{
            File directory = new File(this.outputPath);
            File[] files = directory.listFiles();
            if(files != null)
                for (File file : files)
                    file.delete();
            directory.mkdir();
        }
        catch(SecurityException e){
            // ignore
        }

        try {
            String path = outputPath + "main.ll";
            File file = new File(path);
            file.createNewFile();
            mainFile = new FileWriter(path);
        } catch (IOException e){
            // ignore
        }
    }

    private void writeCommandsToOutputFile() {
        try {
            currentCommand = String.join("\n\t\t", currentCommand.split("\n"));
            if(currentCommand.startsWith("Label_"))
                mainFile.write("\t" + currentCommand + "\n");
            else if(currentCommand.startsWith("."))
                mainFile.write(currentCommand + "\n");
            else
                mainFile.write("\t\t" + currentCommand + "\n");
            mainFile.flush();
        } catch (IOException e){
            // ignore
        }
    }

    private void addCommand(String command){
        currentCommand = currentCommand.concat(command);
    }

    private void addCommandAtBeginning(String command) {
        String printSignature = "@.fmt";
        if (currentCommand.contains(printSignature) && command.contains(printSignature)) {
            return;
        }
        currentCommand = command.concat(currentCommand);
    }

    private void handleMainClass(){
        String command = """
                @.fmt_int = private constant [4 x i8] c"%d\\0A\\00"  ; Format string for printing integers
                declare i32 @printf(i8*, ...)
                declare i32 @puts(i8*)
                define i32 @main() {
                """;
        addCommand(command);
    }

    private void finishMainClass() {
        String command = """
            \nret i32 0
            }
            """;
        addCommand(command);
        addCommand(funcDecCommand);
    }

    @Override
    public String visit(Program program) {
        handleMainClass();

        for (FuncDec funcDec : program.getFuncDecs()) {
            if (funcDec != null) {
                funcDec.accept(this);
            }
        }

        if (program.getMain() != null) {
            program.getMain().accept(this);
        }

        finishMainClass();
        writeCommandsToOutputFile();

        return null;
    }

    String funcDecCommand = "";
    private void addFuncDecCommand(String newCommand) {
        funcDecCommand = funcDecCommand.concat(newCommand);
    }

    @Override
    public String visit(FuncDec funcDec) {
        ArrayList<VarDec> args = funcDec.getArgs();
        addFuncDecCommand("\ndefine void @" + funcDec.getFuncName()+"(");
        for (int i = 0; i < args.size(); i++) {
            visit(args.get(i));
        }
        addFuncDecCommand(") {\n" + "entry:");

        for (Stmt stmt : funcDec.getStmts()) {
            //handleStatementVisitors(stmt);
        }
        addFuncDecCommand("\n}");

        return null;
    }

    @Override
    public String visit(Main main) {
        currentSymbolTable = main.getMainSymbolTable();
        for (var statement : main.getStmts()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public String visit(FuncCall funcCall) {
        if(Objects.equals(funcCall.getFunctionName(), "print")) handlePrint(funcCall);
        return null;
    }

    private int tempVariableCounter = -1;
    private String getNewTempVar() {
        tempVariableCounter++;
        String varName = "val";
        return varName + tempVariableCounter;
    }

    private int printCounter = -1;
    private String getNewPrintName() {
        printCounter ++;
        String varName = "fmt_ptr";
        return varName + printCounter;
    }

    private int stringCounter = -1;
    private int getStringCounter() {
        stringCounter++;
        return stringCounter;
    }

    private void handlePrint(FuncCall funcCall) {
        String text = funcCall.getValues().getFirst().accept(this);
        expressionTypeEvaluator.setCurrentSymbolTable(currentSymbolTable);
        Type type = funcCall.getValues().getFirst().accept(expressionTypeEvaluator);

        boolean paramIsPrinted = currentSymbolTable.items.containsKey("VarDec_"+text);

        if (paramIsPrinted) {
            String temVarName = getNewTempVar();
            String fmtPointerName = getNewPrintName();

            if (type.sameType(new IntType())) {
                addCommand("\n%"+ temVarName +" = load i32, i32* %" + text +
                        "\n%" + fmtPointerName + " = getelementptr [4 x i8], [4 x i8]* @.fmt_int, i32 0, i32 0" +
                        "\ncall i32 (i8*, ...) @printf(i8* %"+ fmtPointerName +", i32 %"+ temVarName +")");
            }
            else if (type.sameType(new BooleanType())) {

            }
            else if (type.sameType(new StringType())) {
                addCommand("\n%"+temVarName+" = load i8*, i8** %"+ text);
                addCommand("\ncall i32 (i8*, ...) @printf(i8* %"+temVarName+")");
            }
        }
        else {
            UnaryExpr unaryExpr = (UnaryExpr) funcCall.getValues().getFirst();
            String argValue = unaryExpr.getOperand().toString();
            String fmtPointerName = getNewPrintName();

            if (type instanceof IntType) {
                addCommand("\n%"+fmtPointerName + " = getelementptr [4 x i8], [4 x i8]* @.fmt_int, i32 0, i32 0" +
                        "\ncall i32 (i8*, ...) @printf(i8* %"+ fmtPointerName +", i32 "+ argValue +")");
            }
            else if (type instanceof StringType) {
                String strLiteral = removeExtraQuotations(argValue);
                String strName = "str"+getStringCounter();
                int strLen = strLiteral.length()+2;
                String printPointerName = getNewPrintName();

                addCommandAtBeginning("@."+ strName +" = private constant ["+ strLen +" x i8] c\""+strLiteral+"\\0A\\00\"\n");
                addCommand("\n%"+printPointerName+" = getelementptr ["+strLen+" x i8], ["+strLen+" x i8]* @."+strName+", i32 0, i32 0\n" +
                        "\ncall i32 @puts(i8* %"+printPointerName+")");
            }
        }
    }

    private VarDecSymbolTableItem getItemFromName(String name) {
        try {
            return (VarDecSymbolTableItem) currentSymbolTable.getItem(VarDecSymbolTableItem.START_KEY + name);
        } catch (ItemNotFoundException e) {
        }

        return null;
    }

    @Override
    public String visit(VarDec varDec) {
        String variableName = varDec.getVarName();
        String command = "\n%"+ variableName +" = alloca ";

        if (varDec.getType() instanceof IntType) {
            command = command + "i32";
        }

        if (varDec.getType() instanceof BooleanType) {
            command = command + "i1";
        }

        if (varDec.getType() instanceof StringType) {
            command = command + "i8*";
        }

        addCommand(command);
        return null;
    }

    private Object getExpressionValue(Expr expression) {
        if (expression instanceof UnaryExpr) {
            return getUnaryExpressionValue( (UnaryExpr) expression);
        }

        return null;
    }

    private Object getUnaryExpressionValue(UnaryExpr unaryExpr) {
        Object operand = unaryExpr.getOperand();
        if (Objects.equals(operand.toString(), "true")) {
            return "true";
        }
        if (Objects.equals(operand.toString(), "false")) {
            return "false";
        }
        if (operand instanceof IntValue)
            return ((IntValue) operand).getIntVal();
        if (operand instanceof StringValue)
            if (((StringValue) operand).getStr().startsWith("\"") && ((StringValue) operand).getStr().endsWith("\"")) {
                return ((StringValue) operand).getStr().substring(1, ((StringValue) operand).getStr().length() - 1);
            } else {
                return ((StringValue) operand).getStr();
            }

        return null;
    }

    private String removeExtraQuotations(String inputStr) {
        if ( inputStr.startsWith("\"") && inputStr.endsWith("\"") ) {
            return inputStr.substring(1, inputStr.length() - 1);
        } else {
            return inputStr;
        }
    }

    @Override
    public String visit(Assign assign) {
        if ( assign.getRightHand() != null ) {
            assign.getRightHand().accept(this);
        }

        String command = "";
        Object rightHandValue = getExpressionValue(assign.getRightHand());
        VarDecSymbolTableItem leftHand = getItemFromName(assign.getLeftHand());
        assert leftHand != null;
        String variableName = leftHand.getVarDec().getVarName();

        if (leftHand.getVarDec().getType().sameType(new IntType())) {
            command = "\nstore i32 "+ rightHandValue +", i32* %" + variableName;
        }
        else if (leftHand.getVarDec().getType() instanceof StringType) {
            int strIndex = getStringCounter();
            int strLen = rightHandValue.toString().length() + 1;
            addCommandAtBeginning("@.str"+ strIndex +" = private constant ["+
                    strLen +" x i8] c\""+ rightHandValue +"\\00\"\n");
            addCommand("\n%c_ptr"+strIndex+" = getelementptr ["+ strLen +" x i8], ["+ strLen +" x i8]* @.str"+strIndex+", i32 0, i32 0" +
                    "\nstore i8* %c_ptr"+strIndex+", i8** %c");
        }
        else if (leftHand.getVarDec().getType() instanceof BooleanType) {
            if (Objects.equals(rightHandValue.toString(), "true")) {
                addCommand("\nstore i1 1, i1* %" + variableName);
            } else {
                addCommand("\nstore i1 0, i1* %" + variableName);
            }
        }
        addCommand(command);

        return null;
    }

    private int booleanCounter = -1;
    private String getNewBooleanName() {
        booleanCounter++;
        return "bool_" + booleanCounter;
    }

    @Override
    public String visit(IfStmt ifStmt) {
        if (ifStmt.getCondition() != null) {
            if (ifStmt.getCondition() instanceof UnaryExpr) {
                UnaryExpr condition = (UnaryExpr)ifStmt.getCondition();

                String tempVarName = getNewTempVar();
                addCommand("\n%"+tempVarName+" = load i1, i1* %"+ condition.getOperand()+"\n" +
                        "br i1 %"+tempVarName+", label %thenBlock, label %elseBlock\n");

                addCommand("\nthenBlock:\n");
                Stmt thenStmt = ifStmt.getIfBody();
                handleStatementVisitors(thenStmt);
                addCommand("\nbr label %endIf\n");

                addCommand("\nelseBlock:\n");
                Stmt elseStmt = ifStmt.getElseBody();
                handleStatementVisitors(elseStmt);
                addCommand("\nbr label %endIf\n");

                addCommand("\nendIf:\n");
            }
        }

        return null;
    }

    private void handleStatementVisitors(Stmt stmt) {
        if (stmt instanceof Assign) {
            visit((Assign) stmt);
        } else if (stmt instanceof VarDec) {
            visit((VarDec) stmt);
        } else if (stmt instanceof FuncCall) {
            visit((FuncCall) stmt);
        } else if (stmt instanceof IfStmt) {
            visit((IfStmt) stmt);
        }
    }

    @Override
    public String visit(UnaryExpr unaryExpr) {
        return unaryExpr.getOperand().accept(this);
    }

    @Override
    public String visit(BinaryExpr binaryExpr) {
        if (binaryExpr.getSecondOperand() != null) {
            binaryExpr.getSecondOperand().accept(this);
        }

        return null;
    }

    @Override
    public String visit(StringValue stringValue) {
        return "";
    }

    @Override
    public String visit(IntValue intValue) {
        return "";
    }

    @Override
    public String visit(BooleanValue booleanValue) {
        return "";
    }

    @Override
    public String visit(Identifier identifier) {
        return identifier.getName();
    }
}
