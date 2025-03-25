// Generated from D:/9/TA/Compiler/AntlrPersentation/Phase3/src/main/grammar/SimpleLang.g4 by ANTLR 4.13.1
package parsers;

    import main.ast.nodes.value.primitiveVals.*;
    import main.ast.nodes.*;
    import main.ast.nodes.declaration.*;
    import main.ast.nodes.type.*;
    import main.ast.nodes.Stmt.*;
    import main.ast.nodes.expr.*;
    import main.ast.nodes.value.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleLangParser}.
 */
public interface SimpleLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SimpleLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SimpleLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#funcDec}.
	 * @param ctx the parse tree
	 */
	void enterFuncDec(SimpleLangParser.FuncDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#funcDec}.
	 * @param ctx the parse tree
	 */
	void exitFuncDec(SimpleLangParser.FuncDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(SimpleLangParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(SimpleLangParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(SimpleLangParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(SimpleLangParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(SimpleLangParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(SimpleLangParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#varDec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(SimpleLangParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#varDec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(SimpleLangParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(SimpleLangParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(SimpleLangParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(SimpleLangParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(SimpleLangParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(SimpleLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(SimpleLangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#primitiveVals}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveVals(SimpleLangParser.PrimitiveValsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#primitiveVals}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveVals(SimpleLangParser.PrimitiveValsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(SimpleLangParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(SimpleLangParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(SimpleLangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(SimpleLangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(SimpleLangParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(SimpleLangParser.IdentifierContext ctx);
}