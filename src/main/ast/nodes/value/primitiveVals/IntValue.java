package main.ast.nodes.value.primitiveVals;

import main.ast.nodes.type.BooleanType;
import main.ast.nodes.type.IntType;
import main.ast.nodes.type.Type;
import main.visitor.IVisitor;

public class IntValue extends PrimitiveVals {
    private int intVal;
    public IntValue(String intVal){ this.intVal = Integer.parseInt(intVal); }

    public int getIntVal() {
        return intVal;
    }

    public void setIntVal(int intVal) {
        this.intVal = intVal;
    }

    public Type getType() {
        return new IntType();
    }
    @Override
    public String toString(){return String.valueOf(this.intVal);}
    @Override
    public <T> T accept(IVisitor<T> visitor){return visitor.visit(this);}
}
