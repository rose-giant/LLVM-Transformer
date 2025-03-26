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
import java.util.HashMap;
import java.util.Objects;

public class CodeGenerator extends Visitor<String>{
    private SymbolTable currentSymbolTable;
    private final ExpressionTypeEvaluator expressionTypeEvaluator = new ExpressionTypeEvaluator();
    private final HashMap<String, Integer> slots = new HashMap<>();

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
        currentCommand = command.concat(currentCommand);
    }

    private void handleMainClass(){
        String command = """
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

    @Override
    public String visit(FuncDec funcDec) {
        for (Stmt stmt : funcDec.getStmts()) {
            if (stmt != null) {
                stmt.accept(this);
            }
        }

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

    private void handlePrint(FuncCall funcCall) {
        String text = funcCall.getValues().getFirst().accept(this);
        expressionTypeEvaluator.setCurrentSymbolTable(currentSymbolTable);
        Type type = funcCall.getValues().getFirst().accept(expressionTypeEvaluator);
        processExpression(funcCall.getValues().getFirst(), text);

        boolean paramIsPrinted = currentSymbolTable.items.containsKey("VarDec_"+text);

        if (paramIsPrinted) {
            if (type.sameType(new IntType())) {
                addCommandAtBeginning("""
                \n@.fmt = private constant [4 x i8] c"%d\\0A\\00"
                declare i32 @printf(i8*, ...)
                """);

                String temVarName = getNewTempVar();
                String fmtPointerName = getNewPrintName();

                addCommand("\n%"+ temVarName +" = load i32, i32* %" + text +
                        "\n%" + fmtPointerName + " = getelementptr [4 x i8], [4 x i8]* @.fmt, i32 0, i32 0" +
                        "\ncall i32 (i8*, ...) @printf(i8* %"+ fmtPointerName +", i32 %"+ temVarName +")");

            } else if (type.sameType(new BooleanType())) {

            } else if (type.sameType(new StringType())) {
                addCommand("@.fmt = private constant [" +
                        text.length() + 1 +" x i8] c\"%d\\00\" \n" +
                        "declare i32 @printf(i8*, ...)");
            }
        } else {
            UnaryExpr unaryExpr = (UnaryExpr) funcCall.getValues().getFirst();
            String argValue = unaryExpr.getOperand().toString();
            if (type instanceof IntType) {
                addCommandAtBeginning("""
                \n@.fmt = private constant [4 x i8] c"%d\\0A\\00"
                declare i32 @printf(i8*, ...)
                """);

                String fmtPointerName = getNewPrintName();

                addCommand("%"+fmtPointerName + " = getelementptr [4 x i8], [4 x i8]* @.fmt, i32 0, i32 0" +
                        "\ncall i32 (i8*, ...) @printf(i8* %"+ fmtPointerName +", i32 "+ argValue +")");
            }
        }

    }

    private <T extends Expr> void processExpression(T genericExpression, String command) {
        if (genericExpression instanceof Identifier identifier) {
//            addCommand(getLoadCommand(Objects.requireNonNull(getItemFromName(identifier.getName()))));
//        } else if (genericExpression instanceof UnaryExpr unaryExpr && unaryExpr.getOperand() instanceof Identifier identifier) {
//            addCommand(getLoadCommand(Objects.requireNonNull(getItemFromName(identifier.getName()))));
        } else {
            Type genericType = genericExpression.accept(expressionTypeEvaluator);
            addCommand(command);
            if (genericType.sameType(new BooleanType())) {
//                addCommand("invokevirtual java/lang/Boolean/booleanValue()Z");
            }
        }
    }

    private VarDecSymbolTableItem getItemFromName(String name) {
        SymbolTable symbolTable = SymbolTable.top;

        VarDecSymbolTableItem varDecSymbolTableItem = null;
        try {
            return (VarDecSymbolTableItem) currentSymbolTable.getItem(VarDecSymbolTableItem.START_KEY + name);
        } catch (ItemNotFoundException e) {
        }

        return null;
    }

//    private String getLoadCommand(VarDecSymbolTableItem varItem) {
//        String loadCommand = "";
//        int index = slotOf(varItem.getVarDec().getVarName());
//        String variableName = varItem.getVarDec().getVarName();

//        if (isArg) {
//            if (varItem.getVarDec().getType() instanceof IntType || varItem.getVarDec().getType().sameType(new BooleanType()))
//                loadCommand = "iload " + index;
//            else
//                loadCommand = "aload " + index;
//        }
//        else {
//            loadCommand = "aload " + index;
//            if (varItem.getVarDec().getType().sameType(new IntType())) {
//                loadCommand += "\n%"+ variableName +" = alloca i32";
//            } else if (varItem.getVarDec().getType().sameType(new BooleanType())) {
//                loadCommand += "\ninvokevirtual java/lang/Boolean/booleanValue()Z";
//            }
//        }
//        return loadCommand;
//    }

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

    private Object getExprssionValue(Expr expression) {

        if (expression instanceof UnaryExpr) {
            return getUnaryExpressionValue( (UnaryExpr) expression);
        }

        return null;
    }

    private Object getUnaryExpressionValue(UnaryExpr unaryExpr) {
        Object operand = unaryExpr.getOperand();
        if (operand instanceof IntValue)
            return ((IntValue) operand).getIntVal() + "";
        if (operand instanceof StringValue)
            return ((StringValue) operand).getStr();

        return null;
    }

    @Override
    public String visit(Assign assign) {
        if ( assign.getRightHand() != null ) {
            assign.getRightHand().accept(this);
        }

        String assignExprByteCode = assign.getRightHand().accept(this);
        String command = "";
        processExpression(assign.getRightHand(), assignExprByteCode);

        Object rightHandValue = getExprssionValue(assign.getRightHand());
        VarDecSymbolTableItem leftHand = getItemFromName(assign.getLeftHand());
        assert leftHand != null;
        String variableName = leftHand.getVarDec().getVarName();

        if (leftHand.getVarDec().getType().sameType(new IntType())) {
            command = "\nstore i32 "+ rightHandValue +", i32* %" + variableName;
        }
//        if (leftHand.getVarDec().getType().sameType(new IntType()) || leftHand.getVarDec().getType().sameType(new BooleanType())) {
//            command = "istore " + index;
//        } else {
//            command = "astore " + index;
//        }
        addCommand(command);

        return null;
    }

    @Override
    public String visit(IfStmt ifStmt) {
        if (ifStmt.getCondition() != null) {
            ifStmt.getCondition().accept(this);
        }

        return null;
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
