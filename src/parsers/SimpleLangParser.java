// Generated from D:/9/TA/Compiler/AntlrPersentation/Phase3/src/main/grammar/SimpleLang.g4 by ANTLR 4.13.1
package parsers;

    import main.ast.nodes.value.primitiveVals.*;
    import main.ast.nodes.*;
    import main.ast.nodes.declaration.*;
    import main.ast.nodes.type.*;
    import main.ast.nodes.Stmt.*;
    import main.ast.nodes.expr.*;
    import main.ast.nodes.value.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SimpleLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, MAIN=2, INT=3, STRING=4, BOOLEAN=5, IF=6, ELSE=7, LBRACE=8, RBRACE=9, 
		SEMI=10, ASSIGN=11, PLUS=12, LPAR=13, RPAR=14, COMMA=15, INT_VAL=16, STRING_VAL=17, 
		TRUE=18, FALSE=19, ID=20, WHITE_SPACE=21, LINE_COMMENT=22, BLOCK_COMMENT=23;
	public static final int
		RULE_program = 0, RULE_funcDec = 1, RULE_arguments = 2, RULE_main = 3, 
		RULE_stmt = 4, RULE_varDec = 5, RULE_assign = 6, RULE_ifStmt = 7, RULE_expr = 8, 
		RULE_primitiveVals = 9, RULE_funcCall = 10, RULE_type = 11, RULE_identifier = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "funcDec", "arguments", "main", "stmt", "varDec", "assign", 
			"ifStmt", "expr", "primitiveVals", "funcCall", "type", "identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'FuncDec'", "'main'", "'int'", "'string'", "'boolean'", "'if'", 
			"'else'", "'{'", "'}'", "';'", "'='", "'+'", "'('", "')'", "','", null, 
			null, "'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "MAIN", "INT", "STRING", "BOOLEAN", "IF", "ELSE", "LBRACE", 
			"RBRACE", "SEMI", "ASSIGN", "PLUS", "LPAR", "RPAR", "COMMA", "INT_VAL", 
			"STRING_VAL", "TRUE", "FALSE", "ID", "WHITE_SPACE", "LINE_COMMENT", "BLOCK_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SimpleLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SimpleLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public Program programRet;
		public FuncDecContext f;
		public MainContext m;
		public MainContext main() {
			return getRuleContext(MainContext.class,0);
		}
		public List<FuncDecContext> funcDec() {
			return getRuleContexts(FuncDecContext.class);
		}
		public FuncDecContext funcDec(int i) {
			return getRuleContext(FuncDecContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ProgramContext)_localctx).programRet =  new Program(); 
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(27);
				((ProgramContext)_localctx).f = funcDec();
				 _localctx.programRet.addFuncDec(((ProgramContext)_localctx).f.funcDecRet); 
				}
				}
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(35);
			((ProgramContext)_localctx).m = main();
			 _localctx.programRet.setMain(((ProgramContext)_localctx).m.mainRet); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncDecContext extends ParserRuleContext {
		public FuncDec funcDecRet;
		public Token f;
		public Token i;
		public ArgumentsContext args;
		public StmtContext s;
		public TerminalNode LPAR() { return getToken(SimpleLangParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SimpleLangParser.RPAR, 0); }
		public TerminalNode LBRACE() { return getToken(SimpleLangParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SimpleLangParser.RBRACE, 0); }
		public TerminalNode ID() { return getToken(SimpleLangParser.ID, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public FuncDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterFuncDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitFuncDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitFuncDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDecContext funcDec() throws RecognitionException {
		FuncDecContext _localctx = new FuncDecContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_funcDec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			((FuncDecContext)_localctx).f = match(T__0);
			setState(39);
			((FuncDecContext)_localctx).i = match(ID);
			 ((FuncDecContext)_localctx).funcDecRet =  new FuncDec((((FuncDecContext)_localctx).i!=null?((FuncDecContext)_localctx).i.getText():null)); 
			setState(41);
			match(LPAR);
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0)) {
				{
				setState(42);
				((FuncDecContext)_localctx).args = arguments();
				_localctx.funcDecRet.setArgs(((FuncDecContext)_localctx).args.argsRet);
				}
			}

			setState(47);
			match(RPAR);
			setState(48);
			match(LBRACE);
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1048696L) != 0)) {
				{
				{
				setState(49);
				((FuncDecContext)_localctx).s = stmt();
				 _localctx.funcDecRet.addStmt(((FuncDecContext)_localctx).s.stmtRet); 
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(57);
			match(RBRACE);
			 _localctx.funcDecRet.setLine(((FuncDecContext)_localctx).f.getLine()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsContext extends ParserRuleContext {
		public ArrayList<VarDec> argsRet;
		public TypeContext t1;
		public Token id1;
		public TypeContext t2;
		public Token id2;
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(SimpleLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SimpleLangParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimpleLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimpleLangParser.COMMA, i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((ArgumentsContext)_localctx).argsRet =  new ArrayList<>();
			setState(61);
			((ArgumentsContext)_localctx).t1 = type();
			setState(62);
			((ArgumentsContext)_localctx).id1 = match(ID);
			_localctx.argsRet.add(new VarDec((((ArgumentsContext)_localctx).id1!=null?((ArgumentsContext)_localctx).id1.getText():null), ((ArgumentsContext)_localctx).t1.typeRet));
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(64);
				match(COMMA);
				setState(65);
				((ArgumentsContext)_localctx).t2 = type();
				setState(66);
				((ArgumentsContext)_localctx).id2 = match(ID);
				_localctx.argsRet.add(new VarDec((((ArgumentsContext)_localctx).id2!=null?((ArgumentsContext)_localctx).id2.getText():null), ((ArgumentsContext)_localctx).t2.typeRet));
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MainContext extends ParserRuleContext {
		public Main mainRet;
		public Token m;
		public StmtContext s;
		public TerminalNode LPAR() { return getToken(SimpleLangParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SimpleLangParser.RPAR, 0); }
		public TerminalNode LBRACE() { return getToken(SimpleLangParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SimpleLangParser.RBRACE, 0); }
		public TerminalNode MAIN() { return getToken(SimpleLangParser.MAIN, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public MainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterMain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitMain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitMain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainContext main() throws RecognitionException {
		MainContext _localctx = new MainContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_main);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((MainContext)_localctx).mainRet =  new Main(); 
			setState(75);
			((MainContext)_localctx).m = match(MAIN);
			setState(76);
			match(LPAR);
			setState(77);
			match(RPAR);
			setState(78);
			match(LBRACE);
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1048696L) != 0)) {
				{
				{
				setState(79);
				((MainContext)_localctx).s = stmt();
				 _localctx.mainRet.addStmt(((MainContext)_localctx).s.stmtRet); 
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(87);
			match(RBRACE);
			 _localctx.mainRet.setLine(((MainContext)_localctx).m.getLine()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public Stmt stmtRet;
		public AssignContext a;
		public IfStmtContext i;
		public VarDecContext v;
		public FuncCallContext f;
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public VarDecContext varDec() {
			return getRuleContext(VarDecContext.class,0);
		}
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_stmt);
		try {
			setState(102);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				((StmtContext)_localctx).a = assign();
				 ((StmtContext)_localctx).stmtRet =  ((StmtContext)_localctx).a.assignRet; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				((StmtContext)_localctx).i = ifStmt();
				 ((StmtContext)_localctx).stmtRet =  ((StmtContext)_localctx).i.ifStmtRet; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(96);
				((StmtContext)_localctx).v = varDec();
				 ((StmtContext)_localctx).stmtRet =  ((StmtContext)_localctx).v.varDecRet; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(99);
				((StmtContext)_localctx).f = funcCall();
				 ((StmtContext)_localctx).stmtRet =  ((StmtContext)_localctx).f.funcCallRet; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDecContext extends ParserRuleContext {
		public VarDec varDecRet;
		public TypeContext t;
		public Token id;
		public TerminalNode SEMI() { return getToken(SimpleLangParser.SEMI, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(SimpleLangParser.ID, 0); }
		public VarDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterVarDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitVarDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitVarDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDecContext varDec() throws RecognitionException {
		VarDecContext _localctx = new VarDecContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varDec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			((VarDecContext)_localctx).t = type();
			setState(105);
			((VarDecContext)_localctx).id = match(ID);
			 ((VarDecContext)_localctx).varDecRet =  new VarDec((((VarDecContext)_localctx).id!=null?((VarDecContext)_localctx).id.getText():null), ((VarDecContext)_localctx).t.typeRet); 
			setState(107);
			match(SEMI);
			 _localctx.varDecRet.setLine(((VarDecContext)_localctx).t.typeRet.getLine()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public Assign assignRet;
		public Token id;
		public ExprContext e;
		public TerminalNode ASSIGN() { return getToken(SimpleLangParser.ASSIGN, 0); }
		public TerminalNode SEMI() { return getToken(SimpleLangParser.SEMI, 0); }
		public TerminalNode ID() { return getToken(SimpleLangParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			((AssignContext)_localctx).id = match(ID);
			setState(111);
			match(ASSIGN);
			setState(112);
			((AssignContext)_localctx).e = expr();
			setState(113);
			match(SEMI);
			 ((AssignContext)_localctx).assignRet =  new Assign((((AssignContext)_localctx).id!=null?((AssignContext)_localctx).id.getText():null), ((AssignContext)_localctx).e.exprRet); _localctx.assignRet.setLine(((AssignContext)_localctx).id.getLine()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends ParserRuleContext {
		public IfStmt ifStmtRet;
		public Token i;
		public ExprContext e;
		public StmtContext s1;
		public StmtContext s2;
		public TerminalNode LPAR() { return getToken(SimpleLangParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SimpleLangParser.RPAR, 0); }
		public TerminalNode IF() { return getToken(SimpleLangParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(SimpleLangParser.ELSE, 0); }
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			((IfStmtContext)_localctx).i = match(IF);
			setState(117);
			match(LPAR);
			setState(118);
			((IfStmtContext)_localctx).e = expr();
			 ((IfStmtContext)_localctx).ifStmtRet =  new IfStmt(((IfStmtContext)_localctx).e.exprRet); 
			setState(120);
			match(RPAR);
			setState(121);
			((IfStmtContext)_localctx).s1 = stmt();
			 _localctx.ifStmtRet.setIfBody(((IfStmtContext)_localctx).s1.stmtRet); 
			setState(127);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(123);
				match(ELSE);
				setState(124);
				((IfStmtContext)_localctx).s2 = stmt();
				 _localctx.ifStmtRet.setElseBody(((IfStmtContext)_localctx).s2.stmtRet); 
				}
				break;
			}
			 _localctx.ifStmtRet.setLine(((IfStmtContext)_localctx).i.getLine()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public Expr exprRet;
		public PrimitiveValsContext pv1;
		public Token p1;
		public ExprContext e1;
		public PrimitiveValsContext pv2;
		public IdentifierContext id1;
		public Token p2;
		public ExprContext e2;
		public IdentifierContext id2;
		public PrimitiveValsContext primitiveVals() {
			return getRuleContext(PrimitiveValsContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(SimpleLangParser.PLUS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expr);
		try {
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				((ExprContext)_localctx).pv1 = primitiveVals();
				setState(132);
				((ExprContext)_localctx).p1 = match(PLUS);
				setState(133);
				((ExprContext)_localctx).e1 = expr();
				 ((ExprContext)_localctx).exprRet =  new BinaryExpr(((ExprContext)_localctx).pv1.primitiveValsRet, (((ExprContext)_localctx).p1!=null?((ExprContext)_localctx).p1.getText():null), ((ExprContext)_localctx).e1.exprRet); _localctx.exprRet.setLine(((ExprContext)_localctx).pv1.primitiveValsRet.getLine()); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				((ExprContext)_localctx).pv2 = primitiveVals();
				 ((ExprContext)_localctx).exprRet =  new UnaryExpr(((ExprContext)_localctx).pv2.primitiveValsRet); _localctx.exprRet.setLine(((ExprContext)_localctx).pv2.primitiveValsRet.getLine()); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(139);
				((ExprContext)_localctx).id1 = identifier();
				setState(140);
				((ExprContext)_localctx).p2 = match(PLUS);
				setState(141);
				((ExprContext)_localctx).e2 = expr();
				 ((ExprContext)_localctx).exprRet =  new BinaryExpr(((ExprContext)_localctx).id1.identifierRet, (((ExprContext)_localctx).p2!=null?((ExprContext)_localctx).p2.getText():null), ((ExprContext)_localctx).e2.exprRet); _localctx.exprRet.setLine(((ExprContext)_localctx).id1.identifierRet.getLine()); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(144);
				((ExprContext)_localctx).id2 = identifier();
				 ((ExprContext)_localctx).exprRet =  new UnaryExpr(((ExprContext)_localctx).id2.identifierRet); _localctx.exprRet.setLine(((ExprContext)_localctx).id2.identifierRet.getLine()); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimitiveValsContext extends ParserRuleContext {
		public PrimitiveVals primitiveValsRet;
		public Token i;
		public Token s;
		public Token t;
		public Token f;
		public TerminalNode INT_VAL() { return getToken(SimpleLangParser.INT_VAL, 0); }
		public TerminalNode STRING_VAL() { return getToken(SimpleLangParser.STRING_VAL, 0); }
		public TerminalNode TRUE() { return getToken(SimpleLangParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(SimpleLangParser.FALSE, 0); }
		public PrimitiveValsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveVals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterPrimitiveVals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitPrimitiveVals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitPrimitiveVals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveValsContext primitiveVals() throws RecognitionException {
		PrimitiveValsContext _localctx = new PrimitiveValsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_primitiveVals);
		try {
			setState(157);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_VAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				((PrimitiveValsContext)_localctx).i = match(INT_VAL);
				((PrimitiveValsContext)_localctx).primitiveValsRet =  new IntValue((((PrimitiveValsContext)_localctx).i!=null?((PrimitiveValsContext)_localctx).i.getText():null)); _localctx.primitiveValsRet.setLine((((PrimitiveValsContext)_localctx).i!=null?((PrimitiveValsContext)_localctx).i.getLine():0));
				}
				break;
			case STRING_VAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				((PrimitiveValsContext)_localctx).s = match(STRING_VAL);
				((PrimitiveValsContext)_localctx).primitiveValsRet =  new StringValue((((PrimitiveValsContext)_localctx).s!=null?((PrimitiveValsContext)_localctx).s.getText():null)); _localctx.primitiveValsRet.setLine((((PrimitiveValsContext)_localctx).s!=null?((PrimitiveValsContext)_localctx).s.getLine():0));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				((PrimitiveValsContext)_localctx).t = match(TRUE);
				((PrimitiveValsContext)_localctx).primitiveValsRet =  new BooleanValue(true); _localctx.primitiveValsRet.setLine((((PrimitiveValsContext)_localctx).t!=null?((PrimitiveValsContext)_localctx).t.getLine():0));
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(155);
				((PrimitiveValsContext)_localctx).f = match(FALSE);
				((PrimitiveValsContext)_localctx).primitiveValsRet =  new BooleanValue(false); _localctx.primitiveValsRet.setLine((((PrimitiveValsContext)_localctx).f!=null?((PrimitiveValsContext)_localctx).f.getLine():0));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncCallContext extends ParserRuleContext {
		public FuncCall funcCallRet;
		public Token id;
		public ExprContext e1;
		public ExprContext e2;
		public TerminalNode LPAR() { return getToken(SimpleLangParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SimpleLangParser.RPAR, 0); }
		public TerminalNode SEMI() { return getToken(SimpleLangParser.SEMI, 0); }
		public TerminalNode ID() { return getToken(SimpleLangParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimpleLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimpleLangParser.COMMA, i);
		}
		public FuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitFuncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_funcCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			((FuncCallContext)_localctx).id = match(ID);
			 ((FuncCallContext)_localctx).funcCallRet =  new FuncCall((((FuncCallContext)_localctx).id!=null?((FuncCallContext)_localctx).id.getText():null)); 
			setState(161);
			match(LPAR);
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2031616L) != 0)) {
				{
				setState(162);
				((FuncCallContext)_localctx).e1 = expr();
				_localctx.funcCallRet.addValue(((FuncCallContext)_localctx).e1.exprRet);
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(164);
					match(COMMA);
					setState(165);
					((FuncCallContext)_localctx).e2 = expr();
					_localctx.funcCallRet.addValue(((FuncCallContext)_localctx).e2.exprRet);
					}
					}
					setState(172);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(175);
			match(RPAR);
			setState(176);
			match(SEMI);
			 _localctx.funcCallRet.setLine(((FuncCallContext)_localctx).id.getLine()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public Type typeRet;
		public Token i1;
		public Token s;
		public Token b;
		public TerminalNode INT() { return getToken(SimpleLangParser.INT, 0); }
		public TerminalNode STRING() { return getToken(SimpleLangParser.STRING, 0); }
		public TerminalNode BOOLEAN() { return getToken(SimpleLangParser.BOOLEAN, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_type);
		try {
			setState(185);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				((TypeContext)_localctx).i1 = match(INT);
				 ((TypeContext)_localctx).typeRet =  new IntType(); _localctx.typeRet.setLine((((TypeContext)_localctx).i1!=null?((TypeContext)_localctx).i1.getLine():0)); 
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(181);
				((TypeContext)_localctx).s = match(STRING);
				 ((TypeContext)_localctx).typeRet =  new StringType(); _localctx.typeRet.setLine((((TypeContext)_localctx).s!=null?((TypeContext)_localctx).s.getLine():0)); 
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(183);
				((TypeContext)_localctx).b = match(BOOLEAN);
				 ((TypeContext)_localctx).typeRet =  new BooleanType(); _localctx.typeRet.setLine((((TypeContext)_localctx).b!=null?((TypeContext)_localctx).b.getLine():0)); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public Identifier identifierRet;
		public Token id;
		public TerminalNode ID() { return getToken(SimpleLangParser.ID, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleLangListener ) ((SimpleLangListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimpleLangVisitor ) return ((SimpleLangVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			((IdentifierContext)_localctx).id = match(ID);
			 ((IdentifierContext)_localctx).identifierRet =  new Identifier((((IdentifierContext)_localctx).id!=null?((IdentifierContext)_localctx).id.getText():null)); _localctx.identifierRet.setLine((((IdentifierContext)_localctx).id!=null?((IdentifierContext)_localctx).id.getLine():0)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0017\u00bf\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005"+
		"\u0000\u001f\b\u0000\n\u0000\f\u0000\"\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001.\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u00015\b\u0001\n\u0001\f\u0001"+
		"8\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0005\u0002F\b\u0002\n\u0002\f\u0002I\t\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003S\b\u0003\n\u0003\f\u0003V\t\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004g\b\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0080\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u0094\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0003\t\u009e\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0005\n\u00a9\b\n\n\n\f\n\u00ac\t\n\u0003\n\u00ae\b"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00ba\b\u000b\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0000\u0000\r\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u0000\u0000\u00c4\u0000\u001a\u0001\u0000"+
		"\u0000\u0000\u0002&\u0001\u0000\u0000\u0000\u0004<\u0001\u0000\u0000\u0000"+
		"\u0006J\u0001\u0000\u0000\u0000\bf\u0001\u0000\u0000\u0000\nh\u0001\u0000"+
		"\u0000\u0000\fn\u0001\u0000\u0000\u0000\u000et\u0001\u0000\u0000\u0000"+
		"\u0010\u0093\u0001\u0000\u0000\u0000\u0012\u009d\u0001\u0000\u0000\u0000"+
		"\u0014\u009f\u0001\u0000\u0000\u0000\u0016\u00b9\u0001\u0000\u0000\u0000"+
		"\u0018\u00bb\u0001\u0000\u0000\u0000\u001a \u0006\u0000\uffff\uffff\u0000"+
		"\u001b\u001c\u0003\u0002\u0001\u0000\u001c\u001d\u0006\u0000\uffff\uffff"+
		"\u0000\u001d\u001f\u0001\u0000\u0000\u0000\u001e\u001b\u0001\u0000\u0000"+
		"\u0000\u001f\"\u0001\u0000\u0000\u0000 \u001e\u0001\u0000\u0000\u0000"+
		" !\u0001\u0000\u0000\u0000!#\u0001\u0000\u0000\u0000\" \u0001\u0000\u0000"+
		"\u0000#$\u0003\u0006\u0003\u0000$%\u0006\u0000\uffff\uffff\u0000%\u0001"+
		"\u0001\u0000\u0000\u0000&\'\u0005\u0001\u0000\u0000\'(\u0005\u0014\u0000"+
		"\u0000()\u0006\u0001\uffff\uffff\u0000)-\u0005\r\u0000\u0000*+\u0003\u0004"+
		"\u0002\u0000+,\u0006\u0001\uffff\uffff\u0000,.\u0001\u0000\u0000\u0000"+
		"-*\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000./\u0001\u0000\u0000"+
		"\u0000/0\u0005\u000e\u0000\u000006\u0005\b\u0000\u000012\u0003\b\u0004"+
		"\u000023\u0006\u0001\uffff\uffff\u000035\u0001\u0000\u0000\u000041\u0001"+
		"\u0000\u0000\u000058\u0001\u0000\u0000\u000064\u0001\u0000\u0000\u0000"+
		"67\u0001\u0000\u0000\u000079\u0001\u0000\u0000\u000086\u0001\u0000\u0000"+
		"\u00009:\u0005\t\u0000\u0000:;\u0006\u0001\uffff\uffff\u0000;\u0003\u0001"+
		"\u0000\u0000\u0000<=\u0006\u0002\uffff\uffff\u0000=>\u0003\u0016\u000b"+
		"\u0000>?\u0005\u0014\u0000\u0000?G\u0006\u0002\uffff\uffff\u0000@A\u0005"+
		"\u000f\u0000\u0000AB\u0003\u0016\u000b\u0000BC\u0005\u0014\u0000\u0000"+
		"CD\u0006\u0002\uffff\uffff\u0000DF\u0001\u0000\u0000\u0000E@\u0001\u0000"+
		"\u0000\u0000FI\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000GH\u0001"+
		"\u0000\u0000\u0000H\u0005\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000"+
		"\u0000JK\u0006\u0003\uffff\uffff\u0000KL\u0005\u0002\u0000\u0000LM\u0005"+
		"\r\u0000\u0000MN\u0005\u000e\u0000\u0000NT\u0005\b\u0000\u0000OP\u0003"+
		"\b\u0004\u0000PQ\u0006\u0003\uffff\uffff\u0000QS\u0001\u0000\u0000\u0000"+
		"RO\u0001\u0000\u0000\u0000SV\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000"+
		"\u0000TU\u0001\u0000\u0000\u0000UW\u0001\u0000\u0000\u0000VT\u0001\u0000"+
		"\u0000\u0000WX\u0005\t\u0000\u0000XY\u0006\u0003\uffff\uffff\u0000Y\u0007"+
		"\u0001\u0000\u0000\u0000Z[\u0003\f\u0006\u0000[\\\u0006\u0004\uffff\uffff"+
		"\u0000\\g\u0001\u0000\u0000\u0000]^\u0003\u000e\u0007\u0000^_\u0006\u0004"+
		"\uffff\uffff\u0000_g\u0001\u0000\u0000\u0000`a\u0003\n\u0005\u0000ab\u0006"+
		"\u0004\uffff\uffff\u0000bg\u0001\u0000\u0000\u0000cd\u0003\u0014\n\u0000"+
		"de\u0006\u0004\uffff\uffff\u0000eg\u0001\u0000\u0000\u0000fZ\u0001\u0000"+
		"\u0000\u0000f]\u0001\u0000\u0000\u0000f`\u0001\u0000\u0000\u0000fc\u0001"+
		"\u0000\u0000\u0000g\t\u0001\u0000\u0000\u0000hi\u0003\u0016\u000b\u0000"+
		"ij\u0005\u0014\u0000\u0000jk\u0006\u0005\uffff\uffff\u0000kl\u0005\n\u0000"+
		"\u0000lm\u0006\u0005\uffff\uffff\u0000m\u000b\u0001\u0000\u0000\u0000"+
		"no\u0005\u0014\u0000\u0000op\u0005\u000b\u0000\u0000pq\u0003\u0010\b\u0000"+
		"qr\u0005\n\u0000\u0000rs\u0006\u0006\uffff\uffff\u0000s\r\u0001\u0000"+
		"\u0000\u0000tu\u0005\u0006\u0000\u0000uv\u0005\r\u0000\u0000vw\u0003\u0010"+
		"\b\u0000wx\u0006\u0007\uffff\uffff\u0000xy\u0005\u000e\u0000\u0000yz\u0003"+
		"\b\u0004\u0000z\u007f\u0006\u0007\uffff\uffff\u0000{|\u0005\u0007\u0000"+
		"\u0000|}\u0003\b\u0004\u0000}~\u0006\u0007\uffff\uffff\u0000~\u0080\u0001"+
		"\u0000\u0000\u0000\u007f{\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000"+
		"\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0082\u0006\u0007"+
		"\uffff\uffff\u0000\u0082\u000f\u0001\u0000\u0000\u0000\u0083\u0084\u0003"+
		"\u0012\t\u0000\u0084\u0085\u0005\f\u0000\u0000\u0085\u0086\u0003\u0010"+
		"\b\u0000\u0086\u0087\u0006\b\uffff\uffff\u0000\u0087\u0094\u0001\u0000"+
		"\u0000\u0000\u0088\u0089\u0003\u0012\t\u0000\u0089\u008a\u0006\b\uffff"+
		"\uffff\u0000\u008a\u0094\u0001\u0000\u0000\u0000\u008b\u008c\u0003\u0018"+
		"\f\u0000\u008c\u008d\u0005\f\u0000\u0000\u008d\u008e\u0003\u0010\b\u0000"+
		"\u008e\u008f\u0006\b\uffff\uffff\u0000\u008f\u0094\u0001\u0000\u0000\u0000"+
		"\u0090\u0091\u0003\u0018\f\u0000\u0091\u0092\u0006\b\uffff\uffff\u0000"+
		"\u0092\u0094\u0001\u0000\u0000\u0000\u0093\u0083\u0001\u0000\u0000\u0000"+
		"\u0093\u0088\u0001\u0000\u0000\u0000\u0093\u008b\u0001\u0000\u0000\u0000"+
		"\u0093\u0090\u0001\u0000\u0000\u0000\u0094\u0011\u0001\u0000\u0000\u0000"+
		"\u0095\u0096\u0005\u0010\u0000\u0000\u0096\u009e\u0006\t\uffff\uffff\u0000"+
		"\u0097\u0098\u0005\u0011\u0000\u0000\u0098\u009e\u0006\t\uffff\uffff\u0000"+
		"\u0099\u009a\u0005\u0012\u0000\u0000\u009a\u009e\u0006\t\uffff\uffff\u0000"+
		"\u009b\u009c\u0005\u0013\u0000\u0000\u009c\u009e\u0006\t\uffff\uffff\u0000"+
		"\u009d\u0095\u0001\u0000\u0000\u0000\u009d\u0097\u0001\u0000\u0000\u0000"+
		"\u009d\u0099\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000\u0000\u0000"+
		"\u009e\u0013\u0001\u0000\u0000\u0000\u009f\u00a0\u0005\u0014\u0000\u0000"+
		"\u00a0\u00a1\u0006\n\uffff\uffff\u0000\u00a1\u00ad\u0005\r\u0000\u0000"+
		"\u00a2\u00a3\u0003\u0010\b\u0000\u00a3\u00aa\u0006\n\uffff\uffff\u0000"+
		"\u00a4\u00a5\u0005\u000f\u0000\u0000\u00a5\u00a6\u0003\u0010\b\u0000\u00a6"+
		"\u00a7\u0006\n\uffff\uffff\u0000\u00a7\u00a9\u0001\u0000\u0000\u0000\u00a8"+
		"\u00a4\u0001\u0000\u0000\u0000\u00a9\u00ac\u0001\u0000\u0000\u0000\u00aa"+
		"\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab"+
		"\u00ae\u0001\u0000\u0000\u0000\u00ac\u00aa\u0001\u0000\u0000\u0000\u00ad"+
		"\u00a2\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae"+
		"\u00af\u0001\u0000\u0000\u0000\u00af\u00b0\u0005\u000e\u0000\u0000\u00b0"+
		"\u00b1\u0005\n\u0000\u0000\u00b1\u00b2\u0006\n\uffff\uffff\u0000\u00b2"+
		"\u0015\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005\u0003\u0000\u0000\u00b4"+
		"\u00ba\u0006\u000b\uffff\uffff\u0000\u00b5\u00b6\u0005\u0004\u0000\u0000"+
		"\u00b6\u00ba\u0006\u000b\uffff\uffff\u0000\u00b7\u00b8\u0005\u0005\u0000"+
		"\u0000\u00b8\u00ba\u0006\u000b\uffff\uffff\u0000\u00b9\u00b3\u0001\u0000"+
		"\u0000\u0000\u00b9\u00b5\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000"+
		"\u0000\u0000\u00ba\u0017\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005\u0014"+
		"\u0000\u0000\u00bc\u00bd\u0006\f\uffff\uffff\u0000\u00bd\u0019\u0001\u0000"+
		"\u0000\u0000\f -6GTf\u007f\u0093\u009d\u00aa\u00ad\u00b9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}