package main.ast.nodes.type;

import main.visitor.IVisitor;

public class NonType extends Type{
    public NonType() {

    }
    @Override
    public <T> T accept(IVisitor<T> visitor) { return visitor.visit(this); }
}
