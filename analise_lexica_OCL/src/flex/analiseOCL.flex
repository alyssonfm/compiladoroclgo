package ClassesJava;

import java_cup.runtime.*; 
import java_cup.runtime.Symbol;
import java_cup.sym; 

%%
%standalone

%class AnaliseLexicaOCL
%unicode
%cup
%line
%column

%{
	StringBuffer string = new StringBuffer();

	private Symbol symbol(int type) {
		System.out.println(yytext());
		Symbol symbol =  new Symbol(type, yyline, yycolumn); 
		return symbol;
	}
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}
%}

/* Delimitadores segundo UNICODE */

LineTerminator = \n|\r|\n\r|\r\n
WhiteSpace     = {LineTerminator}|[ \f\t]

/* Comentários */

ParagraphComment  = "/*" ~"*/"
LineComment       = "--" ~{LineTerminator}
Comments          = {LineComment} | {ParagraphComment}

/* Letras e dígitos */
 
Digit  = [0-9] 
Letter = [A-Za-z] | [_] 
Alpha  = {Letter} | {Digit} 

/*Tipos basicos*/

Integer    = {Digit}+ 
Real       = {Integer}"\."{Integer}[eE][+-]?{Integer} | 
             {Integer}[eE][+-]?{Integer} | {Integer}"\."{Integer}       
String     = "'" ~"'"    
Identifier = {Letter}{Alpha}*     

%%

<YYINITIAL> {  

  {WhiteSpace} { /* Ignorar. */ }    
  {Comments}   { /* Ignorar. */ }
  ".."         { return symbol(sym.DOT_DOT); } 
  "::"         { return symbol(sym.COLON_COLON); } 
  "^^"         { return symbol(sym.UP_UP); } 
  "."          { return symbol(sym.DOT); } 
  ":"          { return symbol(sym.COLON); } 
  "^"          { return symbol(sym.UP); }
  
  /* Parênteses, colchetes e chaves */
  
  "("          { return symbol(sym.LEFT_PAR); } 
  "["          { return symbol(sym.LEFT_BRK); } 
  "{"          { return symbol(sym.LEFT_BRA); } 
  ")"          { return symbol(sym.RIGHT_PAR); } 
  "]"          { return symbol(sym.RIGHT_BRK); } 
  "}"          { return symbol(sym.RIGHT_BRA); } 
  
  /* Pontuações */
    	
  ","          { return symbol(sym.COMMA); } 
  ";"          { return symbol(sym.SEMICOLON); } 
  "|"          { return symbol(sym.BAR); } 
  "@"          { return symbol(sym.AT); } 
  "?"          { return symbol(sym.QUESTION); } 
  
  /* Operadores */
  
  "="          { return symbol(sym.EQ); } 
  "<>"         { return symbol(sym.NE); } 
 
  "<="         { return symbol(sym.LE); } 
  ">="         { return symbol(sym.GE); } 
  "<"          { return symbol(sym.LT); } 
  ">"          { return symbol(sym.GT); } 
 
  "+"          { return symbol(sym.PLUS); } 
  "->"         { return symbol(sym.MINUS_GT); } 
  "-"          { return symbol(sym.MINUS); } 
 
  "*"          { return symbol(sym.TIMES); } 
  "/"          { return symbol(sym.DIVIDE); }
  
  /* Palavras Reservadas */
  
  "package"       { return symbol(sym.PACKAGE); } 
  "endpackage"    { return symbol(sym.ENDPACKAGE); } 
  "context"       { return symbol(sym.CONTEXT); }    
  "body"          { return symbol(sym.BODY); } 
  
  /* Tipos Compostos */
  
  "Set"           { return symbol(sym.COLLECTION); } 
  "Bag"           { return symbol(sym.COLLECTION); } 
  "Sequence"      { return symbol(sym.COLLECTION); } 
  "Collection"    { return symbol(sym.COLLECTION); } 
  "OrderedSet"    { return symbol(sym.COLLECTION); } 
  "TupleType"     { return symbol(sym.TUPLE); } 
  "Tuple"         { return symbol(sym.TUPLE); }
  
  /* Expressões Condicionais */
  
  "if"            { return symbol(sym.IF); } 
  "then"          { return symbol(sym.THEN); } 
  "else"          { return symbol(sym.ELSE); } 
  "endif"         { return symbol(sym.ENDIF); } 
  
  /* Operadores Lógicos */	
  
  "implies"      { return symbol(sym.IMPLIES); } 
  "and"          { return symbol(sym.AND); } 
  "or"           { return symbol(sym.OR); } 
  "xor"          { return symbol(sym.XOR); } 
  "not"          { return symbol(sym.NOT); } 
  "true"         { return symbol(sym.TRUE); } 
  "false"        { return symbol(sym.FALSE); }
  
  "div"          { return symbol(sym.INT_DIVIDE); }
  "mod"          { return symbol(sym.INT_MOD); } 
  
  /* Tipos Básicos e Identifier*/
  
  {Real}         { return symbol(sym.REAL); } 
  {Integer}      { return symbol(sym.INTEGER); } 
   
  {Identifier}   { return symbol(sym.SIMPLE_NAME); } 
  {String}       { return symbol(sym.STRING); } 

} 
 
/*[^] { log.reportError("Illegal character '"+yytext()+"'"); 
      return symbol(sym.BAD);}*/ 