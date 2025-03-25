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
import main.symbolTable.exceptions.ItemAlreadyExists;
import main.symbolTable.exceptions.ItemNotFound;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.item.VarDecSymbolTableItem;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

import static java.util.Objects.isNull;

public class CodeGenerator extends Visitor<String>{
    private SymbolTable currentSymbolTable;
    private ExpressionTypeEvaluator expressionTypeEvaluator = new ExpressionTypeEvaluator();
    private final HashMap<String, Integer> slots = new HashMap<>();

    private final String outputPath;

    private FileWriter mainFile;
    public CodeGenerator() {
        outputPath = "./codeGenOutput/";
        prepareOutputFolder();
    }

    private void prepareOutputFolder(){
//        String jasminPath = "utilities/jarFiles/jasmin.jar";
//        String listClassPath = "utilities/codeGenerationUtilityClasses/List.j";
//        String fptrClassPath = "utilities/codeGenerationUtilityClasses/Fptr.j";
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
//        copyFile(jasminPath, this.outputPath + "jasmin.jar");
//        copyFile(listClassPath, this.outputPath + "List.j");
//        copyFile(fptrClassPath, this.outputPath + "Fptr.j");

        try {
            String path = outputPath + "main.ll";
            File file = new File(path);
            file.createNewFile();
            mainFile = new FileWriter(path);
        } catch (IOException e){
            // ignore
        }
    }

    private void addCommand(String command){
//        System.out.println("suspicious command is " + command + " and " + isNull(command));
        try {
            command = String.join("\n\t\t", command.split("\n"));
            if(command.startsWith("Label_"))
                mainFile.write("\t" + command + "\n");
            else if(command.startsWith("."))
                mainFile.write(command + "\n");
            else
                mainFile.write("\t\t" + command + "\n");
            mainFile.flush();
        } catch (IOException e){
            // ignore
        }
    }

    private void copyFile(String toBeCopied, String toBePasted){
        try {
            File readingFile = new File(toBeCopied);
            File writingFile = new File(toBePasted);
            InputStream readingFileStream = new FileInputStream(readingFile);
            OutputStream writingFileStream = new FileOutputStream(writingFile);
            byte[] buffer = new byte[1024];
            int readLength;
            while ((readLength = readingFileStream.read(buffer)) > 0)
                writingFileStream.write(buffer, 0, readLength);
            readingFileStream.close();
            writingFileStream.close();
        } catch (IOException e){
            // ignore
        }
    }

    private void handleMainClass(){
        String command = """
                define i32 @main() {
                
                    ret i32 0
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

        slots.clear();
        slotOf("MAIN");
        String commands = "";
//        commands += ".method public <init>()V\n";
//        commands += ".limit stack 128\n";
//        commands += ".limit locals 128\n";
//        commands += "aload 0\n";
//        commands += "invokespecial java/lang/Object/<init>()V\n";
        addCommand(commands);
        for (var statement : main.getStmts()) {
            statement.accept(this);
        }
        commands = "";
        addCommand(commands);

        return null;
    }

    @Override
    public String visit(FuncCall funcCall) {
        if(Objects.equals(funcCall.getFunctionName(), "print")) handlePrint(funcCall);

        return null;
    }

    private void handlePrint(FuncCall funcCall) {
        String text = funcCall.getValues().getFirst().accept(this);

        expressionTypeEvaluator.setCurrentSymbolTable(currentSymbolTable);
        Type type = funcCall.getValues().getFirst().accept(expressionTypeEvaluator);

        processExpression(funcCall.getValues().getFirst(), text);

//        addCommand("getstatic java/lang/System/out Ljava/io/PrintStream;");
//        addCommand("swap");

        if (type.sameType(new IntType())) {
//            addCommand("invokevirtual java/io/PrintStream/println(I)V");
        } else if (type.sameType(new BooleanType())) {
//            addCommand("invokevirtual java/io/PrintStream/println(Z)V");
        } else if (type.sameType(new StringType())) {
//            addCommand("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        }
    }

    private <T extends Expr> void processExpression(T genericExpression, String command) {
        if (genericExpression instanceof Identifier identifier) {
            addCommand(getLoadCommand(Objects.requireNonNull(getItemFromName(identifier.getName())), false));
        } else if (genericExpression instanceof UnaryExpr unaryExpr && unaryExpr.getOperand() instanceof Identifier identifier) {
            addCommand(getLoadCommand(Objects.requireNonNull(getItemFromName(identifier.getName())), false));
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

    private String getLoadCommand(VarDecSymbolTableItem varItem, boolean isArg) {
        String loadCommand = "";
//        int index = slotOf(varItem.getVarDec().getVarName());
        String variableName = varItem.getVarDec().getVarName();

//        if (isArg) {
//            if (varItem.getVarDec().getType() instanceof IntType || varItem.getVarDec().getType().sameType(new BooleanType()))
//                loadCommand = "iload " + index;
//            else
//                loadCommand = "aload " + index;
//        }
//        else {
//            loadCommand = "aload " + index;
            if (varItem.getVarDec().getType().sameType(new IntType())) {
                loadCommand += "\n%"+ variableName +" = alloca i32";
//            } else if (varItem.getVarDec().getType().sameType(new BooleanType())) {
//                loadCommand += "\ninvokevirtual java/lang/Boolean/booleanValue()Z";
//            }
        }
        return loadCommand;
    }

    private int slotOf(String var) {
        if (!slots.containsKey(var)) {
            slots.put(var, slots.size());
            return slots.size() - 1;
        }
        return slots.get(var);
    }

    @Override
    public String visit(VarDec varDec) {
        String variableName = varDec.getVarName();
        String command = "\n%"+ variableName +" = alloca i32";
        addCommand(command);

        return null;
    }

    @Override
    public String visit(Assign assign) {
        if ( assign.getRightHand() != null ) {
            assign.getRightHand().accept(this);
        }

        int index = slotOf(assign.getLeftHand());
        String assignExprByteCode = assign.getRightHand().accept(this);
        String jasminCode;
        processExpression(assign.getRightHand(), assignExprByteCode);

        VarDecSymbolTableItem leftHand = getItemFromName(assign.getLeftHand());

        if (leftHand.getVarDec().getType().sameType(new IntType()) || leftHand.getVarDec().getType().sameType(new BooleanType())) {
            jasminCode = "istore " + index;
        } else {
            jasminCode = "astore " + index;
        }

        addCommand(jasminCode);

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
        return "ldc " + stringValue.getStr();
    }

    @Override
    public String visit(IntValue intValue) {
        return "ldc " + intValue.getIntVal();
    }

    @Override
    public String visit(BooleanValue booleanValue) {
        return "ldc " + booleanValue.getBool();
    }

    @Override
    public String visit(Identifier identifier) {
        return identifier.getName();
    }
}
