package ClassesJava;

import java_cup.runtime.Symbol;
import java_cup.sym; 

%%
%class AnaliseLexicaOCL
%standalone
%unicode
%cup
%line
%column

%{
	StringBuffer string = new StringBuffer();
	
	private Symbol symbol(int tokenname) {
		Symbol symbol = new Symbol(tokenname, yytext(), yyline, yycolumn);
		System.out.println("Token:" + symbol.getName() + " Valor: " + symbol.getValue() + " Linha: "+ symbol.getLine() + " Coluna: "+ symbol.getColumn());
		return symbol;
	}
	private Symbol symbol(int tokenname, String value) {
		Symbol symbol = new Symbol(tokenname, value, yyline, yycolumn);
		System.out.println("Token:" + symbol.getName() + " Valor: " + symbol.getValue() + " Linha: "+ symbol.getLine() + " Coluna: "+ symbol.getColumn());
		return symbol;
	}
%}

lineterminator = \n|\r|\n\r|\r\n
inputcharacter = [^\r\n]
whitespace     = {lineterminator}|[ \f\t]
paragraphcomment  = "/*" [^*] ~"*/" | "/*" "*"+ "/"
linecomment       = "--" {inputcharacter}* {lineterminator}
comments          = {linecomment} | {paragraphcomment} /*Pg. 127*/

digit  = [0-9] 
letter = [A-Za-z] | [_] 
alpha  = {letter} | {digit} 
identifier = {letter}{alpha}*    
/*Pg. 74 Types*/
integer    = {digit}+ 
real       = {integer}"\."{integer}[eE][+-]?{integer} | 
             {integer}[eE][+-]?{integer} | {integer}"\."{integer}
boolean = "true"|"false"    
collections = "Set"|"Bag"|"Sequence"|"OrderedSet"|"Collection" /*Pg. 137*/
booleanoperators = "if"|"then"|"else"|"endif"|"implies"|"and"|"or"|"xor"|"not" /*Pg. 123*/
keywords = "self"|"package"|"endpackage"|"context"|"body"  /*Pg. 13 e 14 Especificação OCL*/
%state STRING

%%

<YYINITIAL> {  	
  {whitespace} {  }    
  {comments}   {  }	
  ".."         { return symbol(sym.DOT_DOT); }
  "::"         { return symbol(sym.PATHNAME); } 
  "."          { return symbol(sym.DOT); }
  "->"         { return symbol(sym.MINUS_GT); }   
  ":"          { return symbol(sym.COLON); }  
  "("          { return symbol(sym.LEFT_PAR); } 
  "["          { return symbol(sym.LEFT_BRK); } 
  "{"          { return symbol(sym.LEFT_BRA); } 
  ")"          { return symbol(sym.RIGHT_PAR); } 
  "]"          { return symbol(sym.RIGHT_BRK); } 
  "}"          { return symbol(sym.RIGHT_BRA); }   	
  ","          { return symbol(sym.COMMA); } 
  "|"          { return symbol(sym.BAR); }
  "="          { return symbol(sym.EQ); } 
  "<>"         { return symbol(sym.NE); } 
  "<="         { return symbol(sym.LE); } 
  ">="         { return symbol(sym.GE); } 
  "<"          { return symbol(sym.LT); } 
  ">"          { return symbol(sym.GT); } 
  "+"          { return symbol(sym.PLUS); } 
  "-"          { return symbol(sym.MINUS); } 
  "*"          { return symbol(sym.TIMES); } 
  "/"          { return symbol(sym.DIVIDE); } 
  {keywords}         { return symbol(sym.KEYWORD); }  
  {collections}      { return symbol(sym.COLLECTION); } 
  {booleanoperators} { return symbol(sym.BOOLEANOPERATOR); }
  {boolean}          { return symbol(sym.BOOLEAN); }
  {identifier}       { return symbol(sym.IDENTIFIER); } 
  {real}             { return symbol(sym.REAL); } 
  {integer}          { return symbol(sym.INTEGER); }
} 
<STRING> {
	'           { yybegin(YYINITIAL); return symbol(sym.STRING, string.toString());}
	[^\n\r'\\]+  { string.append( yytext() ); }
	\\t          { string.append("\t"); }
	\\n          { string.append("\n"); }
	\\r          { string.append("\r"); }
	\\'          { string.append("\'"); }
	\\           { string.append("\\"); }
}
.|\n {System.out.println("Erro!"); throw new Error("Illegal character <"+ yytext()+">");} 