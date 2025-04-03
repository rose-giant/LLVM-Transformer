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
import main.symbolTable.item.SymbolTableItem;
import main.symbolTable.item.VarDecSymbolTableItem;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

import static java.util.Objects.isNull;

public class CodeGenerator extends Visitor<String>{
    private SymbolTable currentSymbolTable;
    private final ExpressionTypeEvaluator expressionTypeEvaluator = new ExpressionTypeEvaluator();

    private final String outputPath;
    private String currentCommand = "";
    private FuncDec currentFuncDec;

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
                @.fmt_int = private constant [4 x i8] c"%d\\0A\\00"
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
        currentFuncDec = funcDec;
        ArrayList<VarDec> args = funcDec.getArgs();
        addFuncDecCommand("\ndefine void @" + funcDec.getFuncName()+"(");
        for (int i = 0; i < args.size(); i++) {
            args.get(i).accept(this);
            addFuncDecCommand(i != 0 ? ", " : "");
            addFuncDecCommand(returnLLVMType(args.get(i)) +
                    " %" + args.get(i).getVarName());
        }
        addFuncDecCommand(") {\n" + "entry:");

        for (Stmt stmt : funcDec.getStmts()) {
            stmt.accept(this);
            addFuncDecCommand(handleStatementVisitors(stmt));
        }

        addFuncDecCommand("\nret void;\n}\n");
        currentFuncDec = null;
        return null;
    }

    @Override
    public String visit(Main main) {
        currentSymbolTable = main.getMainSymbolTable();
        for (var statement : main.getStmts()) {
            statement.accept(this);
            addCommand(handleStatementVisitors(statement));
        }
        return null;
    }

    @Override
    public String visit(FuncCall funcCall) {
        String command = "";
        if(Objects.equals(funcCall.getFunctionName(), "print"))
            command = handlePrint(funcCall);
        else {
            ArrayList <String> argValues = new ArrayList<>();
            String args = "";

            //here
            for (int i = 0; i < funcCall.values.size(); i++) {
                if (funcCall.values.get(i) instanceof UnaryExpr) {
                    String type = getUnaryExpressionLLVMType( (UnaryExpr) funcCall.values.get(i));
                    args = args.concat(type + " " + ((UnaryExpr) funcCall.values.get(i)).getOperand());
                }
                //args = args.concat(strArgVal);
                args = i==funcCall.values.size()-1 ? args : args.concat(",");
            }

            command = "\ncall void @"+ funcCall.getFunctionName() +"(" +
                    args + ")\n";
        }
        return command;
    }

    private String getUnaryExpressionLLVMType(UnaryExpr unaryExpr) {
        if (unaryExpr.getOperand().getType() instanceof IntType) {
            return "i32";
        }

        else if (unaryExpr.getOperand().getType() instanceof BooleanType) {
            return "i1";
        }

        else if (unaryExpr.getOperand().getType() instanceof StringType) {
            return "i8*";
        }

        return "";
    }

    private Type converttring2Type(String strType) {
        Type type = null;
        if (Objects.equals(strType, "int")) {
            type = new IntType();
        }

        else if (Objects.equals(strType, "boolean")) {
            type = new BooleanType();
        }

        else if (Objects.equals(strType, "string")) {
            type = new StringType();
        }

        return type;
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

    private VarDecSymbolTableItem getFunctionLocalItemFromName(FuncDec funcDec, String name) {
        try {
            return (VarDecSymbolTableItem) funcDec.getFunctionSymbolTable()
                    .getItem(VarDecSymbolTableItem.START_KEY + name);
        } catch (ItemNotFoundException e) {
        }

        return null;
    }

    private VarDecSymbolTableItem getItemFromName(String name) {
        try {
            return (VarDecSymbolTableItem) currentSymbolTable.getItem(VarDecSymbolTableItem.START_KEY + name);
        } catch (ItemNotFoundException e) {
        }

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

    private String handlePrint(FuncCall funcCall) {
        String text = funcCall.values.getFirst().accept(this);
        expressionTypeEvaluator.setCurrentSymbolTable(currentSymbolTable);
        currentSymbolTable = isNull(currentFuncDec) ? currentSymbolTable : currentFuncDec.getFunctionSymbolTable();
        VarDecSymbolTableItem sti = null;
        boolean paramIsPrinted = currentSymbolTable.items.containsKey("VarDec_"+text);
        String command = "";

        if (paramIsPrinted) {
            try {
                sti = (VarDecSymbolTableItem) currentSymbolTable.getItem("VarDec_" + text);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Type type = sti.getVarDec().getType();

            String temVarName = getNewTempVar();
            String fmtPointerName = getNewPrintName();

            if (!Objects.equals(currentFuncDec.getFuncName(), "main")) {
                if (type.sameType(new IntType())) {
                    command = command.concat("\n"+
                            "\n%" + fmtPointerName + " = getelementptr [4 x i8], [4 x i8]* @.fmt_int, i32 0, i32 0" +
                            "\ncall i32 (i8*, ...) @printf(i8* %"+ fmtPointerName +", i32 %"+ text +")");
                }
                else if (type.sameType(new StringType())) {
                    command = command.concat("\n%"+temVarName+" = load i8*, i8** %"+ text +
                            "\ncall i32 (i8*, ...) @printf(i8* %"+temVarName+")");
                }
            } else {
                if (type.sameType(new IntType())) {
                    command = command.concat("\n%"+ temVarName +" = load i32, i32* %" + text +
                            "\n%" + fmtPointerName + " = getelementptr [4 x i8], [4 x i8]* @.fmt_int, i32 0, i32 0" +
                            "\ncall i32 (i8*, ...) @printf(i8* %"+ fmtPointerName +", i32 %"+ temVarName +")");
                }
                else if (type.sameType(new StringType())) {
                    command = command.concat("\n%"+temVarName+" = load i8*, i8** %"+ text +
                            "\ncall i32 (i8*, ...) @printf(i8* %"+temVarName+")");
                }
            }
        }
        else {
            UnaryExpr unaryExpr = (UnaryExpr) funcCall.getValues().getFirst();
            Type type  = unaryExpr.getOperand().getType();
            String argValue = unaryExpr.getOperand().toString();
            String fmtPointerName = getNewPrintName();
//            Type type = converttring2Type(getUnaryExpressionLLVMType(unaryExpr));

            if (type instanceof IntType) {
                command = command.concat("\n%"+fmtPointerName +
                        " = getelementptr [4 x i8], [4 x i8]* @.fmt_int, i32 0, i32 0" +
                        "\ncall i32 (i8*, ...) @printf(i8* %"+ fmtPointerName +", i32 "+ argValue +")");
            }
            else if (type instanceof StringType) {
                String strLiteral = removeExtraQuotations(argValue);
                String strName = "str"+getStringCounter();
                int strLen = strLiteral.length()+2;
                String printPointerName = getNewPrintName();

                addCommandAtBeginning("@."+ strName +" = private constant ["+ strLen +" x i8] c\""+strLiteral+"\\0A\\00\"\n");
                command = command.concat("\n%"+printPointerName+
                        " = getelementptr ["+strLen+" x i8], ["+strLen+" x i8]* @."+strName+", i32 0, i32 0\n" +
                        "\ncall i32 @puts(i8* %"+printPointerName+")");
            }
        }

        return command;
    }

    private  String returnLLVMType(VarDec varDec) {
        String command = "";
        if (varDec.getType() instanceof IntType) {
            command = command + "i32";
        }

        if (varDec.getType() instanceof BooleanType) {
            command = command + "i1";
        }

        if (varDec.getType() instanceof StringType) {
            command = command + "i8*";
        }

        return command;
    }

    @Override
    public String visit(VarDec varDec) {
        String variableName = varDec.getVarName();
        String command = "\n%"+ variableName +" = alloca ";

        command = command + returnLLVMType(varDec);
//        if (varDec.getType() instanceof IntType) {
//            command = command + "i32";
//        }
//
//        if (varDec.getType() instanceof BooleanType) {
//            command = command + "i1";
//        }
//
//        if (varDec.getType() instanceof StringType) {
//            command = command + "i8*";
//        }

//        if (isNull(currentFuncDec)) addCommand(command);

        return command;
    }

    @Override
    public String visit(Assign assign) {
        if ( assign.getRightHand() != null ) {
            assign.getRightHand().accept(this);
        }

        String command = "";
        Object rightHandValue = getExpressionValue(assign.getRightHand());
        VarDecSymbolTableItem leftHand;
        if (isNull(currentFuncDec)) leftHand = getItemFromName(assign.getLeftHand());
        else leftHand = getFunctionLocalItemFromName(currentFuncDec, assign.getLeftHand());

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
            command = "\n%c_ptr"+strIndex+" = getelementptr ["+ strLen +" x i8], ["+ strLen +" x i8]* @.str"+strIndex+", i32 0, i32 0" +
                    "\nstore i8* %c_ptr"+strIndex+", i8** %c";
//            addCommand(command);
        }
        else if (leftHand.getVarDec().getType() instanceof BooleanType) {
            if (Objects.equals(rightHandValue.toString(), "true")) {
                command = "\nstore i1 1, i1* %" + variableName;
//                addCommand(command);
            } else {
                command = "\nstore i1 0, i1* %" + variableName;
//                addCommand(command);
            }
        }
        //addCommand(command);

//        if (isNull(currentFuncDec)) addCommand(command);
        return command;
    }

    @Override
    public String visit(IfStmt ifStmt) {
        String command = "";
        if (ifStmt.getCondition() != null) {
            if (ifStmt.getCondition() instanceof UnaryExpr) {
                UnaryExpr condition = (UnaryExpr)ifStmt.getCondition();
                String tempVarName = getNewTempVar();

                if (!isNull(currentFuncDec) && !Objects.equals(currentFuncDec.getFuncName(), "main")) {
                    command = command.concat("\nbr i1 %"+condition.getOperand()+", label %thenBlock, label %elseBlock\n");
                } else {
                    command = command.concat("\n%"+tempVarName+" = load i1, i1* %"+ condition.getOperand()+"\n" +
                            "br i1 %"+tempVarName+", label %thenBlock, label %elseBlock\n");
                }

                command = command.concat("\nthenBlock:\n");
                Stmt thenStmt = ifStmt.getIfBody();
                command = command.concat(handleStatementVisitors(thenStmt));
                command = command.concat("\nbr label %endIf\n");

                command = command.concat("\nelseBlock:\n");
                Stmt elseStmt = ifStmt.getElseBody();
                command = command.concat(handleStatementVisitors(elseStmt));

                command = command.concat("\nbr label %endIf\nendIf:\n");
            }
        }

        return command;
    }

    private String handleStatementVisitors(Stmt stmt) {
        String command = "";
        if (stmt instanceof Assign) {
            command = visit((Assign) stmt);
        } else if (stmt instanceof VarDec) {
            command = visit((VarDec) stmt);
        } else if (stmt instanceof FuncCall) {
            command = visit((FuncCall) stmt);
        } else if (stmt instanceof IfStmt) {
            command = visit((IfStmt) stmt);
        }

        return command;
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
