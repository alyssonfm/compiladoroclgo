package ClassesJava;

import java_cup.runtime.Symbol;
import java_cup.sym; 

%%
%class AnaliseLexicaOCL
%unicode
%cup
%line
%column

%{
	StringBuilder erros = new StringBuilder();
	
	private Symbol symbol(int tokenname) {
		Symbol symbol = new Symbol(tokenname, yytext(), yyline, yycolumn);
		return symbol;
	}
	private Symbol symbol(int tokenname, String value) {
		Symbol symbol = new Symbol(tokenname, value, yyline, yycolumn);
		return symbol;
	}
%}

lineterminator = \n|\r|\n\r|\r\n
inputcharacter = [^\r\n]
whitespace     = {lineterminator}|[ \f\t]
paragraphcomment  = "/*" ~"*/"
linecomment       = "--" {inputcharacter}* {lineterminator}
comments          = {linecomment} | {paragraphcomment} /*Pg. 127*/

digit  = [0-9] 
letter = [A-Za-z] | [_] 
alpha  = {letter} | {digit} 
identifier = {letter}{alpha}*

/*Pg. 74 Types*/
string = "'" ~"'" 
integer    = -?{digit}+  
real       = {integer}("\."{digit}+)?([eE][+-]?{digit}+)?
boolean = "true"|"false"    
collections = "Set"|"Bag"|"Sequence"|"OrderedSet" /*Pg. 137*/
booleanoperators = "implies"|"and"|"or"|"xor"|"not" /*Pg. 123*/
conditionalexpression = "if"|"then"|"else"|"endif"
keywords = "self"|"package"|"endpackage"|"context"|"body"  /*Pg. 13 e 14 Especificação OCL*/

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
<YYINITIAL>  {keywords}              { return symbol(sym.KEYWORD); }  
<YYINITIAL>  {collections}           { return symbol(sym.COLLECTION); } 
<YYINITIAL>  {booleanoperators}      { return symbol(sym.BOOLEANOPERATOR); }
<YYINITIAL>  {conditionalexpression} { return symbol(sym.CONDITIONALEXPRESSION); }
<YYINITIAL>  {boolean}               { return symbol(sym.BOOLEAN); }
<YYINITIAL>  {identifier}            { return symbol(sym.IDENTIFIER); } 
<YYINITIAL>  {real}                  { return symbol(sym.REAL); } 
<YYINITIAL>  {integer}               { return symbol(sym.INTEGER); }
<YYINITIAL>  {string}                { return symbol(sym.STRING); }
.|\n {erros.append("Lexema não reconhecido pela linguagem OCL: <"+ yytext()+">" + " Linha: "+ yyline + " Coluna: "+ yycolumn+"\r");}

<<EOF>>  {System.err.println(erros.toString());System.exit(1);}