package main.ast.nodes.expr;

import main.ast.nodes.Node;
import main.visitor.IVisitor;

public abstract class Expr extends Node {
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
