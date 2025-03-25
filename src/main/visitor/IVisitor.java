package main.visitor;

import main.ast.nodes.Program;
import main.ast.nodes.Stmt.Assign;
import main.ast.nodes.Stmt.FuncCall;
import main.ast.nodes.Stmt.IfStmt;
import main.ast.nodes.Stmt.VarDec;
import main.ast.nodes.declaration.FuncDec;
import main.ast.nodes.declaration.Main;
import main.ast.nodes.expr.BinaryExpr;
import main.ast.nodes.expr.Expr;
import main.ast.nodes.expr.UnaryExpr;
import main.ast.nodes.type.NonType;
import main.ast.nodes.value.Identifier;
import main.ast.nodes.value.primitiveVals.BooleanValue;
import main.ast.nodes.value.primitiveVals.IntValue;
import main.ast.nodes.value.primitiveVals.StringValue;
import main.ast.nodes.type.BooleanType;
import main.ast.nodes.type.IntType;
import main.ast.nodes.type.StringType;

public interface IVisitor<T> {

    T visit(Program program);
    T visit(Main main);
    T visit(VarDec varDec);
    T visit(IfStmt ifStmt);
    T visit(FuncCall funcCall);
    T visit(Assign assign);
    T visit(FuncDec funcDec);
    T visit(UnaryExpr unaryExpr);
    T visit(BinaryExpr binaryExpr);
    T visit(IntType intType);
    T visit(StringType stringType);
    T visit(BooleanType booleanType);
    T visit(IntValue intValue);
    T visit(StringValue stringValue);
    T visit(BooleanValue booleanType);
    T visit(Identifier identifier);
    T visit(NonType nonType);
    T visit(Expr expr);

}
