import java_cup.runtime.*; 

%%
%standalone

%class AnaliseLexicaOCL
%unicode
%cup
%line
%column
%cupsym sym

%{
	StringBuffer string = new StringBuffer();

	private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}
%}

/*Delimitadores segundo UNICODE*/

lineTerminator = \n|\r|\n\r|\r\n
whiteSpace = {lineTerminator}|[ \f\t]

/*Especifica��o OCL - Pg 90 */

paragraphComment  = "/*" ~"*/"
lineComment = "--" ~{lineTerminator}
comments = lineComment | paragraphComment    


context = context
identifier = [:jletter:] [:jletterdigit:]*

%%
{whiteSpace} {System.out.println("Espa�o ou quebra de linha!");}
{comments} {System.out.println("Coment�rio");}
context { System.out.println("Keyword: context"); }
{identifier} { System.out.println("Identifier: "); }



