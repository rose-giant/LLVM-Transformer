package main.ast.nodes.value;

import main.ast.nodes.Node;
import main.ast.nodes.expr.Expr;
import main.ast.nodes.type.NonType;
import main.ast.nodes.type.Type;

public abstract class Value extends Expr {
    public Type getType() {
        return new NonType();
    }
}
