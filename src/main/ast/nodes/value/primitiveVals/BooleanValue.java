package main.ast.nodes.value.primitiveVals;

import main.ast.nodes.type.BooleanType;
import main.ast.nodes.type.Type;
import main.visitor.IVisitor;

public class BooleanValue extends PrimitiveVals {
    private Boolean bool;
    public BooleanValue( Boolean bool ){this.bool = bool;}

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public Type getType() {
        return new BooleanType();
    }
    @Override
    public String toString(){return String.valueOf(this.bool);}
    @Override
    public <T> T accept(IVisitor<T> visitor){return visitor.visit(this);}
}
