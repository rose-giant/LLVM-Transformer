package main.ast.nodes.expr;

import main.ast.nodes.value.Value;
import main.visitor.IVisitor;

public class UnaryExpr extends Expr{
    private Value operand;

    public UnaryExpr(Value operand) {
        this.operand = operand;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Value getOperand() {
        return operand;
    }
}
