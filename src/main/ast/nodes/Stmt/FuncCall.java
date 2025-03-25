package main.ast.nodes.Stmt;

import main.ast.nodes.expr.Expr;
import main.ast.nodes.value.Value;
import main.visitor.IVisitor;

import java.util.ArrayList;

public class FuncCall extends Stmt{
    private String functionName;
    private ArrayList<Expr> values = new ArrayList<>();

    public ArrayList<Expr> getValues() {
        return values;
    }

    public void setValues(ArrayList<Expr> values) {
        this.values = values;
    }

    public void addValue(Expr value) {
        this.values.add(value);
    }

    public FuncCall(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
