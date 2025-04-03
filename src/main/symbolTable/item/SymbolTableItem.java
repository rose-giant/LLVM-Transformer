package main.symbolTable.item;

import main.ast.nodes.type.Type;

public abstract class SymbolTableItem {
    protected String name;
    protected Type type;
    public abstract String getKey();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Type getType(String name) {
//        return type;
//    }
}
