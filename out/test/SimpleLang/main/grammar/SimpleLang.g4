grammar SimpleLang;

@header{
    import main.ast.nodes.value.primitiveVals.*;
    import main.ast.nodes.*;
    import main.ast.nodes.declaration.*;
    import main.ast.nodes.type.*;
    import main.ast.nodes.Stmt.*;
    import main.ast.nodes.expr.*;
    import main.ast.nodes.value.*;
}

// Parser rules
program returns [Program programRet] :
    { $programRet = new Program(); }
    (f = funcDec { $programRet.addFuncDec($f.funcDecRet); } )*
    m = main { $programRet.setMain($m.mainRet); }
    ;

funcDec returns [FuncDec funcDecRet] :
    f = 'FuncDec' i = ID { $funcDecRet = new FuncDec($i.text); }
    LPAR
    ( args = arguments {$funcDecRet.setArgs($args.argsRet);} )?
    RPAR
    LBRACE
    ( s = stmt { $funcDecRet.addStmt($s.stmtRet); } )*
    RBRACE
    { $funcDecRet.setLine($f.getLine()); };

arguments returns [ArrayList<VarDec> argsRet]:
    {$argsRet = new ArrayList<>();}
    t1 = type id1 = ID {$argsRet.add(new VarDec($id1.text, $t1.typeRet));}
    ( COMMA t2 = type id2 = ID {$argsRet.add(new VarDec($id2.text, $t2.typeRet));} )*
;

main returns [SimpleLang mainRet] :
    { $mainRet = new SimpleLang(); }
    m = MAIN LPAR RPAR LBRACE
    (s = stmt { $mainRet.addStmt($s.stmtRet); })*
    RBRACE
    { $mainRet.setLine($m.getLine()); };

stmt returns [Stmt stmtRet] :
    a = assign { $stmtRet = $a.assignRet; }
    | i = ifStmt { $stmtRet = $i.ifStmtRet; }
    | v = varDec { $stmtRet = $v.varDecRet; }
    | f = funcCall { $stmtRet = $f.funcCallRet; }
    ;

varDec returns [VarDec varDecRet] :
    t = type id = ID { $varDecRet = new VarDec($id.text, $t.typeRet); } SEMI
    { $varDecRet.setLine($t.typeRet.getLine()); };

assign returns [Assign assignRet] :
    id = ID ASSIGN e = expr SEMI
    { $assignRet = new Assign($id.text, $e.exprRet); $assignRet.setLine($id.getLine()); };

ifStmt returns [IfStmt ifStmtRet] :
    i = IF LPAR e = expr { $ifStmtRet = new IfStmt($e.exprRet); } RPAR
    s1 = stmt { $ifStmtRet.setIfBody($s1.stmtRet); }
    (ELSE s2 = stmt { $ifStmtRet.setElseBody($s2.stmtRet); })?
    { $ifStmtRet.setLine($i.getLine()); };

expr returns [Expr exprRet] :
    pv1 = primitiveVals p1 = PLUS e1 = expr { $exprRet = new BinaryExpr($pv1.primitiveValsRet, $p1.text, $e1.exprRet); $exprRet.setLine($pv1.primitiveValsRet.getLine()); }
    | pv2 = primitiveVals { $exprRet = new UnaryExpr($pv2.primitiveValsRet); $exprRet.setLine($pv2.primitiveValsRet.getLine()); }
    | id1 = identifier p2 = PLUS e2 = expr { $exprRet = new BinaryExpr($id1.identifierRet, $p2.text, $e2.exprRet); $exprRet.setLine($id1.identifierRet.getLine()); }
    | id2 = identifier { $exprRet = new UnaryExpr($id2.identifierRet); $exprRet.setLine($id2.identifierRet.getLine()); };

primitiveVals returns [PrimitiveVals primitiveValsRet]:
    i = INT_VAL {$primitiveValsRet = new IntValue($i.text); $primitiveValsRet.setLine($i.line);}
    | s = STRING_VAL {$primitiveValsRet = new StringValue($s.text); $primitiveValsRet.setLine($s.line);}
    | t = TRUE {$primitiveValsRet = new BooleanValue(true); $primitiveValsRet.setLine($t.line);}
    | f = FALSE {$primitiveValsRet = new BooleanValue(false); $primitiveValsRet.setLine($f.line);};

funcCall returns [FuncCall funcCallRet] :
    id = ID { $funcCallRet = new FuncCall($id.text); }
    LPAR
    (
    e1 = expr {$funcCallRet.addValue($e1.exprRet);}
    ( COMMA e2 = expr {$funcCallRet.addValue($e2.exprRet);} )*
    )?
    RPAR SEMI
    { $funcCallRet.setLine($id.getLine()); };

type returns [Type typeRet] :
    i1 = INT { $typeRet = new IntType(); $typeRet.setLine($i1.line); }
    | s = STRING { $typeRet = new StringType(); $typeRet.setLine($s.line); }
    | b = BOOLEAN { $typeRet = new BooleanType(); $typeRet.setLine($b.line); };

identifier returns [Identifier identifierRet] :
    id = ID { $identifierRet = new Identifier($id.text); $identifierRet.setLine($id.line); };

// Lexer rules

// 1- General structure
MAIN : 'main';
INT : 'int';
STRING : 'string';
BOOLEAN : 'boolean';
IF : 'if';
ELSE : 'else';

// 2- Symbols
LBRACE : '{';
RBRACE : '}';
SEMI : ';';
ASSIGN : '=';
PLUS : '+';
LPAR : '(';
RPAR : ')';
COMMA: ',';

// 3- Identifiers
INT_VAL : [0-9]+;
STRING_VAL: '"'~["]*'"';
TRUE: 'true';
FALSE: 'false';
ID : [a-zA-Z_][a-zA-Z0-9_]*;

// 4- Whitespace and comments
WHITE_SPACE : [ \t\r\n]+ -> skip;
LINE_COMMENT : '//' ~[\r\n]* -> skip;
BLOCK_COMMENT : '/*' .*? '*/' -> skip;