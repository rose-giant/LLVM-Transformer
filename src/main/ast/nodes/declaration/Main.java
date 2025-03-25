package main.ast.nodes.declaration;

import main.ast.nodes.Node;
import main.ast.nodes.Stmt.Stmt;
import main.symbolTable.SymbolTable;
import main.visitor.IVisitor;

import java.util.ArrayList;

public class Main extends Node {
    private ArrayList<Stmt> stmts = new ArrayList<Stmt>();

    public SymbolTable getMainSymbolTable() {
        return mainSymbolTable;
    }

    public void setMainSymbolTable(SymbolTable mainSymbolTable) {
        this.mainSymbolTable = mainSymbolTable;
    }

    private SymbolTable mainSymbolTable;

    public Main() {}
    public void addStmt(Stmt stmt) {
        this.stmts.add(stmt);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public ArrayList<Stmt> getStmts() {
        return stmts;
    }

    public void setStmts(ArrayList<Stmt> stmts) {
        this.stmts = stmts;
    }

    public void setSymbolTable(SymbolTable mainSymbolTable) {
        this.mainSymbolTable = mainSymbolTable;
    }
}
