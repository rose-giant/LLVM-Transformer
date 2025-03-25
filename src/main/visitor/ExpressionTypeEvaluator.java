package main.visitor;

import main.ast.nodes.Stmt.*;
import main.ast.nodes.expr.BinaryExpr;
import main.ast.nodes.expr.Expr;
import main.ast.nodes.expr.UnaryExpr;
import main.ast.nodes.type.BooleanType;
import main.ast.nodes.type.NonType;
import main.ast.nodes.type.Type;
import main.ast.nodes.value.Identifier;
import main.ast.nodes.value.primitiveVals.BooleanValue;
import main.ast.nodes.value.primitiveVals.IntValue;
import main.ast.nodes.value.primitiveVals.StringValue;
import main.compileError.CompileError;
import main.compileError.typeErrors.Mismatch;
import main.compileError.typeErrors.NonSameOperands;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.item.VarDecSymbolTableItem;

import java.util.ArrayList;

public class ExpressionTypeEvaluator extends Visitor<Type>{
    public ArrayList<CompileError> getTypeErrors() {
        return typeErrors;
    }
    private SymbolTable currentSymbolTable;

    public void setCurrentSymbolTable(SymbolTable currentSymbolTable) {
        this.currentSymbolTable = currentSymbolTable;
    }

    public void setTypeErrors(ArrayList<CompileError> typeErrors) {
        this.typeErrors = typeErrors;
    }

    public ArrayList<CompileError> typeErrors = new ArrayList<>();

    @Override
    public Type visit(VarDec varDec) {
        return varDec.getType();
    }

    @Override
    public Type visit(Expr expr) {
        if (expr instanceof UnaryExpr)
            return ((UnaryExpr)expr).accept(this);
        else if (expr instanceof BinaryExpr)
            return ((BinaryExpr)expr).accept(this);
        return new NonType();
    }

    @Override
    public Type visit(UnaryExpr unaryExpr) {
        return unaryExpr.getOperand().accept(this);
    }

    @Override
    public Type visit(BinaryExpr binaryExpr) {
        if (binaryExpr.getSecondOperand() != null) {
            binaryExpr.getSecondOperand().accept(this);
        }

        Type firstType = binaryExpr.getFirstOperand().getType();
        Type restType = binaryExpr.getSecondOperand().accept(this);
        if (!firstType.sameType(restType)) {
            typeErrors.add(new NonSameOperands(binaryExpr.getLine()));
            return new NonType();
        }

        return binaryExpr.getFirstOperand().getType();
    }

    @Override
    public Type visit(Assign assign) {
        VarDecSymbolTableItem varDecSymbolTableItem = null;
        try {
            varDecSymbolTableItem = (VarDecSymbolTableItem) currentSymbolTable.getItem(VarDecSymbolTableItem.START_KEY + assign.getLeftHand());
        } catch (ItemNotFoundException e) {
        }

        Type leftHandType = varDecSymbolTableItem.getVarDec().getType();
        Type rightHandType = this.visit(assign.getRightHand());

        if (!leftHandType.sameType(rightHandType)) {
            typeErrors.add(new Mismatch(assign.getLine()));
            return new NonType();
        }

        return new NonType();
    }

    @Override
    public Type visit(Identifier identifier) {
        VarDecSymbolTableItem varDecSymbolTableItem = null;
        try {
            varDecSymbolTableItem = (VarDecSymbolTableItem) currentSymbolTable.getItem(VarDecSymbolTableItem.START_KEY + identifier.getName());
        } catch (ItemNotFoundException e) {
        }

        return varDecSymbolTableItem.getVarDec().getType();
    }

    @Override
    public Type visit(IntValue intValue) {
        return intValue.getType();
    }

    @Override
    public Type visit(StringValue stringValue) {
        return stringValue.getType();
    }

    @Override
    public Type visit(BooleanValue booleanValue) {
        return booleanValue.getType();
    }
}
