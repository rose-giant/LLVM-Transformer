package main.ast.nodes.value;

import main.ast.nodes.value.primitiveVals.PrimitiveVals;
import main.visitor.IVisitor;

public class Identifier extends Value {
    private String name;
    public Identifier(String str) {
        this.name = str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
