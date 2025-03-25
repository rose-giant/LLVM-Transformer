package main.visitor;

import main.ast.nodes.Program;
import main.ast.nodes.Stmt.*;
import main.ast.nodes.declaration.*;
import main.ast.nodes.expr.BinaryExpr;
import main.ast.nodes.expr.Expr;
import main.ast.nodes.expr.UnaryExpr;
import main.ast.nodes.type.*;
import main.ast.nodes.value.Identifier;
import main.ast.nodes.value.primitiveVals.BooleanValue;
import main.ast.nodes.value.primitiveVals.IntValue;
import main.ast.nodes.value.primitiveVals.StringValue;

public abstract class Visitor<T> implements IVisitor<T> {
    @Override
    public T visit(Program program) {
        return null;
    }
    public T visit(Main main) {
        return null;
    }
    public T visit(Assign assign) {
        return null;
    }
    public T visit(VarDec varDec) {
        return null;
    }
    public T visit(IfStmt ifStmt) {
        return null;
    }
    public T visit(FuncCall funcCall) {
        return null;
    }
    public T visit(FuncDec funcDec) {
        return null;
    }
    public T visit(UnaryExpr unaryExpr) {
        return null;
    }
    public T visit(BinaryExpr binaryExpr) {
        return null;
    }
    public T visit(IntType intType) {
        return null;
    }
    public T visit(StringType stringType) {
        return null;
    }
    public T visit(BooleanType booleanType) {
        return null;
    }
    public T visit(IntValue intValue) {
        return null;
    }
    public T visit(StringValue stringValue) {
        return null;
    }
    public T visit(BooleanValue booleanType) {return null;}
    public T visit(Identifier identifier) {return null;}
    public T visit(NonType nonType) {return null;}
    public T visit(Expr expr) {return null;}
}
