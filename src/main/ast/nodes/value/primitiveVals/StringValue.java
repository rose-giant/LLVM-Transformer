package main.ast.nodes.value.primitiveVals;

import main.ast.nodes.type.BooleanType;
import main.ast.nodes.type.StringType;
import main.ast.nodes.type.Type;
import main.visitor.IVisitor;

public class StringValue extends PrimitiveVals {
    private String str;
    public StringValue(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
    public Type getType() {
        return new StringType();
    }
    @Override
    public String toString(){return this.str;}
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
