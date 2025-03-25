// Generated from D:/9/TA/Compiler/AntlrPersentation/Phase3/src/main/grammar/SimpleLang.g4 by ANTLR 4.13.1
package parsers;

    import main.ast.nodes.value.primitiveVals.*;
    import main.ast.nodes.*;
    import main.ast.nodes.declaration.*;
    import main.ast.nodes.type.*;
    import main.ast.nodes.Stmt.*;
    import main.ast.nodes.expr.*;
    import main.ast.nodes.value.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimpleLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimpleLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(SimpleLangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#funcDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDec(SimpleLangParser.FuncDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(SimpleLangParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(SimpleLangParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(SimpleLangParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#varDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(SimpleLangParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(SimpleLangParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(SimpleLangParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(SimpleLangParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#primitiveVals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveVals(SimpleLangParser.PrimitiveValsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(SimpleLangParser.FuncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(SimpleLangParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpleLangParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(SimpleLangParser.IdentifierContext ctx);
}