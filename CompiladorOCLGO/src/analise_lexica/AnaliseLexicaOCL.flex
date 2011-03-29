package analise_lexica;

import java_cup.runtime.*;
import analise_sintatica.sym; 

%%
%class AnaliseLexicaOCL
%unicode
%cup
%line
%column

%{
	StringBuilder string = new StringBuilder();
	
	private Symbol symbol(int tokenname) {
		Symbol symbol = new Symbol(tokenname, yyline, yycolumn, yytext());
		return symbol;
	}
	private Symbol symbol(int tokenname, String value) {
		Symbol symbol = new Symbol(tokenname, yyline, yycolumn, value);
		return symbol;
	}
%}

lineterminator = \n|\r|\n\r|\r\n
inputcharacter = [^\r\n]
whitespace     = {lineterminator}|[ \f\t]
paragraphcomment  = ("/*"~"*/")(~"*/")*
linecomment       = "--" {inputcharacter}* {lineterminator}
comments          = {linecomment} | {paragraphcomment} 

digit  = [0-9] 
letter = [A-Za-z] | [_] 
alpha  = {letter} | {digit} 
identifier = {letter}{alpha}*

%state STRING
stringdelimiter = \'
integer    = -?{digit}+  
real       = {integer}("\."{digit}+)?([eE][+-]?{digit}+)?
boolean = "true"|"false"    
collections = "Set"|"Bag"|"Sequence"|"OrderedSet"

%%

<YYINITIAL>  {whitespace} {  }    
<YYINITIAL>  {comments}   {  }	
<YYINITIAL>  "::"         { return symbol(sym.PATHNAME); } 
<YYINITIAL>  "."          { return symbol(sym.DOT); }
<YYINITIAL>  "->"         { return symbol(sym.COLLECTIONOPERATION); }   
<YYINITIAL>  ":"          { return symbol(sym.COLON); }  
<YYINITIAL>  "("          { return symbol(sym.LEFT_PAR); } 
<YYINITIAL>  "["          { return symbol(sym.LEFT_BRK); } 
<YYINITIAL>  "{"          { return symbol(sym.LEFT_BRA); } 
<YYINITIAL>  ")"          { return symbol(sym.RIGHT_PAR); } 
<YYINITIAL>  "]"          { return symbol(sym.RIGHT_BRK); } 
<YYINITIAL>  "}"          { return symbol(sym.RIGHT_BRA); }   	
<YYINITIAL>  ","          { return symbol(sym.COMMA); } 
<YYINITIAL>  "|"          { return symbol(sym.BAR); }
<YYINITIAL>  "="          { return symbol(sym.EQ); } 
<YYINITIAL>  "<>"         { return symbol(sym.NE); } 
<YYINITIAL>  "<="         { return symbol(sym.LE); } 
<YYINITIAL>  ">="         { return symbol(sym.GE); } 
<YYINITIAL>  "<"          { return symbol(sym.LT); } 
<YYINITIAL>  ">"          { return symbol(sym.GT); } 
<YYINITIAL>  "+"          { return symbol(sym.PLUS); } 
<YYINITIAL>  "-"          { return symbol(sym.MINUS); } 
<YYINITIAL>  "*"          { return symbol(sym.TIMES); } 
<YYINITIAL>  "/"          { return symbol(sym.DIVIDE); } 
<YYINITIAL>  "package"          { return symbol(sym.PACKAGE); }
<YYINITIAL>  "endpackage"       { return symbol(sym.ENDPACKAGE); }
<YYINITIAL>  "context"          { return symbol(sym.CONTEXT); }
<YYINITIAL>  "body"             { return symbol(sym.BODY); }
<YYINITIAL>  "if"               { return symbol(sym.IF); }
<YYINITIAL>  "then"             { return symbol(sym.THEN); }
<YYINITIAL>  "else"             { return symbol(sym.ELSE); }
<YYINITIAL>  "endif"            { return symbol(sym.ENDIF); }
<YYINITIAL>  "implies"          { return symbol(sym.IMPLIES); }
<YYINITIAL>  "and"              { return symbol(sym.AND); }
<YYINITIAL>  "or"               { return symbol(sym.OR); }
<YYINITIAL>  "xor"              { return symbol(sym.XOR); }
<YYINITIAL>  "not"              { return symbol(sym.NOT); }
<YYINITIAL>  {boolean}               { return symbol(sym.BOOLEAN); }
<YYINITIAL>  {identifier}            { return symbol(sym.IDENTIFIER); } 
<YYINITIAL>  {real}                  { return symbol(sym.REAL); } 
<YYINITIAL>  {integer}               { return symbol(sym.INTEGER); }
<YYINITIAL>  {stringdelimiter}  { string.setLength(0); yybegin(STRING); }
<STRING>  [^\n\r\'\\]+          { string.append( yytext()); }
<STRING>  \\t                   { string.append('\t'); }
<STRING>  \\n                   { string.append('\n'); }
<STRING>  \\r                   { string.append('\r'); }
<STRING>  \\\'                  { string.append('\''); }
<STRING>  \\                    { string.append('\\'); }
<STRING> {stringdelimiter} 		{ yybegin(YYINITIAL); return symbol(sym.STRING, string.toString());}

.|\n {throw new Error("Lexema não reconhecido pela linguagem OCL: <"+ yytext()+">" + " Linha: "+ yyline + " Coluna: "+ yycolumn+"\r");}
