// Generated from D:/9/TA/Compiler/AntlrPersentation/Phase3/src/main/grammar/SimpleLang.g4 by ANTLR 4.13.1
package parsers;

    import main.ast.nodes.value.primitiveVals.*;
    import main.ast.nodes.*;
    import main.ast.nodes.declaration.*;
    import main.ast.nodes.type.*;
    import main.ast.nodes.Stmt.*;
    import main.ast.nodes.expr.*;
    import main.ast.nodes.value.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class SimpleLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, MAIN=2, INT=3, STRING=4, BOOLEAN=5, IF=6, ELSE=7, LBRACE=8, RBRACE=9, 
		SEMI=10, ASSIGN=11, PLUS=12, LPAR=13, RPAR=14, COMMA=15, INT_VAL=16, STRING_VAL=17, 
		TRUE=18, FALSE=19, ID=20, WHITE_SPACE=21, LINE_COMMENT=22, BLOCK_COMMENT=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "MAIN", "INT", "STRING", "BOOLEAN", "IF", "ELSE", "LBRACE", "RBRACE", 
			"SEMI", "ASSIGN", "PLUS", "LPAR", "RPAR", "COMMA", "INT_VAL", "STRING_VAL", 
			"TRUE", "FALSE", "ID", "WHITE_SPACE", "LINE_COMMENT", "BLOCK_COMMENT"
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


	public SimpleLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SimpleLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0017\u00a7\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0004\u000fi\b\u000f\u000b"+
		"\u000f\f\u000fj\u0001\u0010\u0001\u0010\u0005\u0010o\b\u0010\n\u0010\f"+
		"\u0010r\t\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0005\u0013\u0083"+
		"\b\u0013\n\u0013\f\u0013\u0086\t\u0013\u0001\u0014\u0004\u0014\u0089\b"+
		"\u0014\u000b\u0014\f\u0014\u008a\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u0093\b\u0015\n\u0015\f\u0015"+
		"\u0096\t\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0005\u0016\u009e\b\u0016\n\u0016\f\u0016\u00a1\t\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u009f\u0000"+
		"\u0017\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006"+
		"\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017"+
		"\u0001\u0000\u0006\u0001\u000009\u0001\u0000\"\"\u0003\u0000AZ__az\u0004"+
		"\u000009AZ__az\u0003\u0000\t\n\r\r  \u0002\u0000\n\n\r\r\u00ac\u0000\u0001"+
		"\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000"+
		"\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000"+
		"\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000"+
		"\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000"+
		"\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001"+
		"\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0001/\u0001\u0000\u0000"+
		"\u0000\u00037\u0001\u0000\u0000\u0000\u0005<\u0001\u0000\u0000\u0000\u0007"+
		"@\u0001\u0000\u0000\u0000\tG\u0001\u0000\u0000\u0000\u000bO\u0001\u0000"+
		"\u0000\u0000\rR\u0001\u0000\u0000\u0000\u000fW\u0001\u0000\u0000\u0000"+
		"\u0011Y\u0001\u0000\u0000\u0000\u0013[\u0001\u0000\u0000\u0000\u0015]"+
		"\u0001\u0000\u0000\u0000\u0017_\u0001\u0000\u0000\u0000\u0019a\u0001\u0000"+
		"\u0000\u0000\u001bc\u0001\u0000\u0000\u0000\u001de\u0001\u0000\u0000\u0000"+
		"\u001fh\u0001\u0000\u0000\u0000!l\u0001\u0000\u0000\u0000#u\u0001\u0000"+
		"\u0000\u0000%z\u0001\u0000\u0000\u0000\'\u0080\u0001\u0000\u0000\u0000"+
		")\u0088\u0001\u0000\u0000\u0000+\u008e\u0001\u0000\u0000\u0000-\u0099"+
		"\u0001\u0000\u0000\u0000/0\u0005F\u0000\u000001\u0005u\u0000\u000012\u0005"+
		"n\u0000\u000023\u0005c\u0000\u000034\u0005D\u0000\u000045\u0005e\u0000"+
		"\u000056\u0005c\u0000\u00006\u0002\u0001\u0000\u0000\u000078\u0005m\u0000"+
		"\u000089\u0005a\u0000\u00009:\u0005i\u0000\u0000:;\u0005n\u0000\u0000"+
		";\u0004\u0001\u0000\u0000\u0000<=\u0005i\u0000\u0000=>\u0005n\u0000\u0000"+
		">?\u0005t\u0000\u0000?\u0006\u0001\u0000\u0000\u0000@A\u0005s\u0000\u0000"+
		"AB\u0005t\u0000\u0000BC\u0005r\u0000\u0000CD\u0005i\u0000\u0000DE\u0005"+
		"n\u0000\u0000EF\u0005g\u0000\u0000F\b\u0001\u0000\u0000\u0000GH\u0005"+
		"b\u0000\u0000HI\u0005o\u0000\u0000IJ\u0005o\u0000\u0000JK\u0005l\u0000"+
		"\u0000KL\u0005e\u0000\u0000LM\u0005a\u0000\u0000MN\u0005n\u0000\u0000"+
		"N\n\u0001\u0000\u0000\u0000OP\u0005i\u0000\u0000PQ\u0005f\u0000\u0000"+
		"Q\f\u0001\u0000\u0000\u0000RS\u0005e\u0000\u0000ST\u0005l\u0000\u0000"+
		"TU\u0005s\u0000\u0000UV\u0005e\u0000\u0000V\u000e\u0001\u0000\u0000\u0000"+
		"WX\u0005{\u0000\u0000X\u0010\u0001\u0000\u0000\u0000YZ\u0005}\u0000\u0000"+
		"Z\u0012\u0001\u0000\u0000\u0000[\\\u0005;\u0000\u0000\\\u0014\u0001\u0000"+
		"\u0000\u0000]^\u0005=\u0000\u0000^\u0016\u0001\u0000\u0000\u0000_`\u0005"+
		"+\u0000\u0000`\u0018\u0001\u0000\u0000\u0000ab\u0005(\u0000\u0000b\u001a"+
		"\u0001\u0000\u0000\u0000cd\u0005)\u0000\u0000d\u001c\u0001\u0000\u0000"+
		"\u0000ef\u0005,\u0000\u0000f\u001e\u0001\u0000\u0000\u0000gi\u0007\u0000"+
		"\u0000\u0000hg\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jh\u0001"+
		"\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000k \u0001\u0000\u0000\u0000"+
		"lp\u0005\"\u0000\u0000mo\b\u0001\u0000\u0000nm\u0001\u0000\u0000\u0000"+
		"or\u0001\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000"+
		"\u0000qs\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000st\u0005\"\u0000"+
		"\u0000t\"\u0001\u0000\u0000\u0000uv\u0005t\u0000\u0000vw\u0005r\u0000"+
		"\u0000wx\u0005u\u0000\u0000xy\u0005e\u0000\u0000y$\u0001\u0000\u0000\u0000"+
		"z{\u0005f\u0000\u0000{|\u0005a\u0000\u0000|}\u0005l\u0000\u0000}~\u0005"+
		"s\u0000\u0000~\u007f\u0005e\u0000\u0000\u007f&\u0001\u0000\u0000\u0000"+
		"\u0080\u0084\u0007\u0002\u0000\u0000\u0081\u0083\u0007\u0003\u0000\u0000"+
		"\u0082\u0081\u0001\u0000\u0000\u0000\u0083\u0086\u0001\u0000\u0000\u0000"+
		"\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000"+
		"\u0085(\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0087"+
		"\u0089\u0007\u0004\u0000\u0000\u0088\u0087\u0001\u0000\u0000\u0000\u0089"+
		"\u008a\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008a"+
		"\u008b\u0001\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c"+
		"\u008d\u0006\u0014\u0000\u0000\u008d*\u0001\u0000\u0000\u0000\u008e\u008f"+
		"\u0005/\u0000\u0000\u008f\u0090\u0005/\u0000\u0000\u0090\u0094\u0001\u0000"+
		"\u0000\u0000\u0091\u0093\b\u0005\u0000\u0000\u0092\u0091\u0001\u0000\u0000"+
		"\u0000\u0093\u0096\u0001\u0000\u0000\u0000\u0094\u0092\u0001\u0000\u0000"+
		"\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0097\u0001\u0000\u0000"+
		"\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0097\u0098\u0006\u0015\u0000"+
		"\u0000\u0098,\u0001\u0000\u0000\u0000\u0099\u009a\u0005/\u0000\u0000\u009a"+
		"\u009b\u0005*\u0000\u0000\u009b\u009f\u0001\u0000\u0000\u0000\u009c\u009e"+
		"\t\u0000\u0000\u0000\u009d\u009c\u0001\u0000\u0000\u0000\u009e\u00a1\u0001"+
		"\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u009f\u009d\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a2\u0001\u0000\u0000\u0000\u00a1\u009f\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a3\u0005*\u0000\u0000\u00a3\u00a4\u0005/\u0000"+
		"\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6\u0006\u0016\u0000"+
		"\u0000\u00a6.\u0001\u0000\u0000\u0000\u0007\u0000jp\u0084\u008a\u0094"+
		"\u009f\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}