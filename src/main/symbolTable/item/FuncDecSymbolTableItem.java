package main.symbolTable.item;

public class FuncDecSymbolTableItem extends SymbolTableItem {
    public static final String START_KEY = "FuncDec_";

    private String functionName;

    public FuncDecSymbolTableItem(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public String getKey() {
        return START_KEY + this.functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
