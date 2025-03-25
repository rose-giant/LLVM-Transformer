package main.ast.nodes.expr;

import main.ast.nodes.value.Value;
import main.visitor.IVisitor;

public class BinaryExpr extends Expr {
    private Value firstOperand;
    private Expr secondOperand;
    private String operator;

    public BinaryExpr(Value firstOperand, String operator, Expr secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Value getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(Value firstOperand) {
        this.firstOperand = firstOperand;
    }

    public Expr getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(Expr secondOperand) {
        this.secondOperand = secondOperand;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
