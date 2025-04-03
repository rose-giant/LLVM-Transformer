package main.ast.nodes.declaration;

import main.ast.nodes.Node;
import main.ast.nodes.Stmt.Stmt;
import main.ast.nodes.Stmt.VarDec;
import main.symbolTable.SymbolTable;
import main.symbolTable.item.VarDecSymbolTableItem;
import main.visitor.IVisitor;

import java.util.ArrayList;

import static java.util.Objects.isNull;

public class FuncDec extends Node {
    private String funcName;
    private VarDecSymbolTableItem varDecSymbolTableItem;

    public void setVarDecSymbolTableItem(VarDec varDec) {
        if (isNull(varDecSymbolTableItem)) varDecSymbolTableItem = new VarDecSymbolTableItem(varDec);

        else varDecSymbolTableItem.setVarDec(varDec);

//        functionSymbolTable
    }
    public SymbolTable getFunctionSymbolTable() {
        return functionSymbolTable;
    }

    public void setFunctionSymbolTable(SymbolTable functionSymbolTable) {
        this.functionSymbolTable = functionSymbolTable;
    }

    private SymbolTable functionSymbolTable;
    private ArrayList<Stmt> stmts = new ArrayList<Stmt>();

    public ArrayList<VarDec> getArgs() {
        return args;
    }

    public void setArgs(ArrayList<VarDec> args) {
        this.args = args;
    }

    private ArrayList<VarDec> args = new ArrayList<>();

    public FuncDec(String funcName) {
        this.funcName = funcName;
    }

    public void addStmt(Stmt stmt) {
        this.stmts.add(stmt);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public ArrayList<Stmt> getStmts() {
        return stmts;
    }

    public void setStmts(ArrayList<Stmt> stmts) {
        this.stmts = stmts;
    }

    public void setSymbolTable(SymbolTable funcDecSymbolTable) {
        this.functionSymbolTable = funcDecSymbolTable;
    }
}
