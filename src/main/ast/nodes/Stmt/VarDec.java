package main.ast.nodes.Stmt;

import main.ast.nodes.type.Type;
import main.visitor.IVisitor;

public class VarDec extends Stmt{
    private String varName;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    private Type type;

    public VarDec(String varName, Type type) {
        this.varName = varName;
        this.type = type;
    }
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }
}
