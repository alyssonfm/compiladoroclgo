package java_cup.runtime;

import java_cup.sym;

/**
 * Defines the Symbol class, which is used to represent all terminals and
 * nonterminals while parsing. The lexer should pass CUP Symbols and CUP returns
 * a Symbol.
 * 
 * @version last updated: 7/3/96
 * @author Frank Flannery
 */

/* ****************************************************************
 * Class Symbol what the parser expects to receive from the lexer. the token is
 * identified as follows: sym: the symbol type parse_state: the parse state.
 * value: is the lexical value of type Object left : is the left position in the
 * original input file right: is the right position in the original input file
 * ****************************************************************
 */

public class Symbol {

	/*******************************
	 * Constructor for l,r values
	 *******************************/

	public Symbol(int id, String value, int l, int r) {
		this(id);
		left = l;
		right = r;
		this.value = value;
		this.text = getTokenName(id);
	}

	public Symbol(int id, int l, int r, Object o) {
		this(id);
		left = l;
		right = r;
		value = o;
	}

	public Symbol(int id, int l, int r) {
		this(id);
		left = l;
		right = r;
	}

	public Symbol(int id, Object value) {
		this(id);
		this.value = value;
	}

	public static String getTokenName(int tokenName) {
		String out = "SPECIALCHAR";
		if (java_cup.sym.KEYWORD == tokenName) {
			out = "KEYWORD";
		} else if (java_cup.sym.OPERATOR == tokenName
				|| java_cup.sym.NE == tokenName || java_cup.sym.EQ == tokenName
				|| java_cup.sym.LE == tokenName || java_cup.sym.GE == tokenName
				|| java_cup.sym.LT == tokenName || java_cup.sym.GT == tokenName
				|| java_cup.sym.PLUS == tokenName
				|| java_cup.sym.TIMES == tokenName
				|| java_cup.sym.MINUS == tokenName
				|| java_cup.sym.DIVIDE == tokenName) {
			out = "OPERATOR";
		} else if (java_cup.sym.COLLECTION == tokenName) {
			out = "COLLECTION";
		} else if (java_cup.sym.BOOLEAN == tokenName) {
			out = "BOOLEAN";
		} else if (java_cup.sym.REAL == tokenName) {
			out = "REAL";
		} else if (java_cup.sym.INTEGER == tokenName) {
			out = "INTEGER";
		} else if (java_cup.sym.STRING == tokenName) {
			out = "STRING";
		} else if (java_cup.sym.IDENTIFIER == tokenName) {
			out = "IDENTIFIER";
		} else if (java_cup.sym.PATHNAME == tokenName) {
			out = "sym.PATHNAME";
		}
		return out;

	}

	/*******************************
	 * Constructor for no l,r values
	 ********************************/

	/*****************************
	 * Constructor for no value
	 ***************************/

	/***********************************
	 * Constructor for no value or l,r
	 ***********************************/

	public Symbol(int sym_num) {
		this(sym_num, -1);
		left = -1;
		right = -1;
		value = null;
	}

	/***********************************
	 * Constructor to give a start state
	 ***********************************/
	Symbol(int sym_num, int state) {
		sym = sym_num;
		parse_state = state;
	}

	/* . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . */

	/** The symbol number of the terminal or non terminal being represented */
	public int sym;

	/* . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . */

	/**
	 * The parse state to be recorded on the parse stack with this symbol. This
	 * field is for the convenience of the parser and shouldn't be modified
	 * except by the parser.
	 */
	public int parse_state;
	/**
	 * This allows us to catch some errors caused by scanners recycling symbols.
	 * For the use of the parser only. [CSA, 23-Jul-1999]
	 */
	boolean used_by_parser = false;

	/*******************************
	 * The data passed to parser
	 *******************************/

	public int left, right;
	public Object value;
	public String text;

	/*****************************
	 * Printing this token out. (Override for pretty-print).
	 ****************************/
	public String toString() {
		return "#" + sym;
	}

	public String getValue() {
		return this.value.toString();
	}

	public int getLine() {
		return this.left;
	}

	public int getColumn() {
		return this.right;
	}

	public String getName() {
		return this.text.toString();
	}
}
