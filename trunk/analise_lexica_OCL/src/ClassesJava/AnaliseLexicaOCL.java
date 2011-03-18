/* The following code was generated by JFlex 1.4.3 on 18/03/11 17:03 */

package ClassesJava;

import java_cup.runtime.Symbol;
import java_cup.sym; 


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 18/03/11 17:03 from the specification file
 * <tt>C:/Users/Nat�/Desktop/UFCG/Disciplinas/Compiladores/Projeto/workspace/analise_lexica_OCL/src/flex/analiseOCL.flex</tt>
 */
class AnaliseLexicaOCL implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\1\1\0\1\3\1\2\22\0\1\3\6\0\1\11"+
    "\1\51\1\54\1\5\1\14\1\57\1\6\1\12\1\4\12\7\1\47"+
    "\1\0\1\62\1\61\1\50\2\0\1\10\1\26\1\35\1\10\1\13"+
    "\11\10\1\33\3\10\1\25\7\10\1\52\1\0\1\55\1\0\1\10"+
    "\1\0\1\22\1\45\1\32\1\34\1\20\1\21\1\27\1\40\1\37"+
    "\1\10\1\44\1\23\1\41\1\31\1\36\1\42\1\30\1\16\1\24"+
    "\1\15\1\17\2\10\1\43\1\46\1\10\1\53\1\60\1\56\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\3\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\1\1\10\1\11\20\7\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\2\0\1\26\1\1\1\0\1\1\1\0\1\27\1\30"+
    "\15\7\1\31\3\7\1\32\1\33\1\34\1\35\2\0"+
    "\1\1\1\36\1\0\1\36\6\7\1\37\7\7\3\0"+
    "\3\36\1\1\1\40\1\7\1\41\6\7\1\1\1\0"+
    "\1\1\1\0\1\36\1\0\1\1\6\7\1\0\2\1"+
    "\1\0\1\1\6\7\2\0\2\1\3\7\1\0\4\7";

  private static int [] zzUnpackAction() {
    int [] result = new int[143];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\63\0\146\0\231\0\63\0\314\0\63\0\377"+
    "\0\u0132\0\u0165\0\u0198\0\u01cb\0\63\0\u01fe\0\u0231\0\u0264"+
    "\0\u0297\0\u02ca\0\u02fd\0\u0330\0\u0363\0\u0396\0\u03c9\0\u03fc"+
    "\0\u042f\0\u0462\0\u0495\0\u04c8\0\u04fb\0\u052e\0\u0561\0\63"+
    "\0\63\0\63\0\63\0\63\0\63\0\63\0\63\0\63"+
    "\0\u0594\0\u05c7\0\u05fa\0\63\0\u062d\0\u0660\0\u0693\0\u0198"+
    "\0\63\0\63\0\u06c6\0\u06f9\0\u072c\0\u075f\0\u0792\0\u07c5"+
    "\0\u07f8\0\u082b\0\u085e\0\u0891\0\u08c4\0\u08f7\0\u092a\0\u0165"+
    "\0\u095d\0\u0990\0\u09c3\0\63\0\63\0\63\0\63\0\u09f6"+
    "\0\u0a29\0\u0a5c\0\u0a8f\0\u0ac2\0\u0af5\0\u0b28\0\u0b5b\0\u0b8e"+
    "\0\u0bc1\0\u0bf4\0\u0c27\0\u0165\0\u0c5a\0\u0c8d\0\u0cc0\0\u0cf3"+
    "\0\u0d26\0\u0d59\0\u0d8c\0\u0dbf\0\u0df2\0\u0e25\0\u0e58\0\u0e8b"+
    "\0\u0ebe\0\u0ef1\0\u0165\0\u0f24\0\u0165\0\u0f57\0\u0f8a\0\u0fbd"+
    "\0\u0ff0\0\u1023\0\u1056\0\u1089\0\u10bc\0\u10ef\0\u1122\0\u1155"+
    "\0\u1188\0\u11bb\0\u11ee\0\u1221\0\u1254\0\u1287\0\u12ba\0\u12ed"+
    "\0\u1320\0\u1353\0\u1386\0\u13b9\0\u13ec\0\u141f\0\u1452\0\u1485"+
    "\0\u14b8\0\u14eb\0\u151e\0\u1551\0\u1584\0\u15b7\0\u15ea\0\u161d"+
    "\0\u1650\0\u1683\0\u16b6\0\u16e9\0\u171c\0\u174f\0\u1782";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[143];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\12\1\15\1\16\2\12\1\17"+
    "\1\20\1\21\1\12\1\22\1\23\1\24\2\12\1\25"+
    "\1\26\1\27\1\12\1\30\1\31\1\32\2\12\1\33"+
    "\1\34\1\12\1\35\1\12\1\36\1\37\1\40\1\41"+
    "\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51"+
    "\65\0\1\5\61\0\1\5\66\0\1\52\63\0\1\53"+
    "\41\0\1\54\21\0\1\11\1\55\1\0\1\56\1\57"+
    "\1\0\3\55\1\57\26\55\23\0\2\12\2\0\1\12"+
    "\1\0\32\12\14\0\11\60\1\61\51\60\12\0\1\62"+
    "\57\0\2\12\2\0\1\12\1\0\1\12\1\63\21\12"+
    "\1\64\6\12\23\0\2\12\2\0\1\12\1\0\6\12"+
    "\1\65\5\12\1\66\15\12\23\0\2\12\2\0\1\12"+
    "\1\0\5\12\1\67\24\12\23\0\2\12\2\0\1\12"+
    "\1\0\14\12\1\70\15\12\23\0\2\12\2\0\1\12"+
    "\1\0\3\12\1\71\26\12\23\0\2\12\2\0\1\12"+
    "\1\0\3\12\1\72\26\12\23\0\2\12\2\0\1\12"+
    "\1\0\5\12\1\73\24\12\23\0\2\12\2\0\1\12"+
    "\1\0\21\12\1\74\10\12\23\0\2\12\2\0\1\12"+
    "\1\0\21\12\1\75\10\12\23\0\2\12\2\0\1\12"+
    "\1\0\1\12\1\76\30\12\23\0\2\12\2\0\1\12"+
    "\1\0\21\12\1\77\10\12\23\0\2\12\2\0\1\12"+
    "\1\0\1\12\1\100\30\12\23\0\2\12\2\0\1\12"+
    "\1\0\4\12\1\100\17\12\1\101\5\12\23\0\2\12"+
    "\2\0\1\12\1\0\5\12\1\102\24\12\23\0\2\12"+
    "\2\0\1\12\1\0\21\12\1\31\10\12\23\0\2\12"+
    "\2\0\1\12\1\0\21\12\1\103\10\12\63\0\1\104"+
    "\74\0\1\105\51\0\1\106\10\0\1\107\1\0\5\110"+
    "\1\111\55\110\1\53\1\3\1\4\60\53\7\0\1\112"+
    "\1\55\2\0\1\55\1\0\32\55\23\0\1\113\61\0"+
    "\1\114\1\115\1\55\2\0\1\55\1\114\32\55\23\0"+
    "\2\12\2\0\1\12\1\0\2\12\1\116\27\12\23\0"+
    "\2\12\2\0\1\12\1\0\3\12\1\117\26\12\23\0"+
    "\2\12\2\0\1\12\1\0\7\12\1\120\22\12\23\0"+
    "\2\12\2\0\1\12\1\0\17\12\1\121\12\12\23\0"+
    "\2\12\2\0\1\12\1\0\6\12\1\122\23\12\23\0"+
    "\2\12\2\0\1\12\1\0\17\12\1\100\12\12\23\0"+
    "\2\12\2\0\1\12\1\0\6\12\1\123\23\12\23\0"+
    "\2\12\2\0\1\12\1\0\1\124\12\12\1\125\16\12"+
    "\23\0\2\12\2\0\1\12\1\0\12\12\1\124\17\12"+
    "\23\0\2\12\2\0\1\12\1\0\1\100\31\12\23\0"+
    "\2\12\2\0\1\12\1\0\14\12\1\126\15\12\23\0"+
    "\2\12\2\0\1\12\1\0\17\12\1\127\12\12\23\0"+
    "\2\12\2\0\1\12\1\0\6\12\1\130\23\12\23\0"+
    "\2\12\2\0\1\12\1\0\25\12\1\131\4\12\23\0"+
    "\2\12\2\0\1\12\1\0\15\12\1\132\14\12\23\0"+
    "\2\12\2\0\1\12\1\0\17\12\1\133\12\12\14\0"+
    "\5\110\1\134\55\110\4\0\1\5\1\111\64\0\1\112"+
    "\2\0\1\135\1\136\4\0\1\136\51\0\1\137\1\55"+
    "\2\0\1\57\1\0\3\55\1\57\26\55\23\0\1\140"+
    "\62\0\1\141\1\55\1\0\1\135\1\142\1\0\3\55"+
    "\1\142\26\55\23\0\2\12\2\0\1\12\1\0\3\12"+
    "\1\143\26\12\23\0\2\12\2\0\1\12\1\0\14\12"+
    "\1\100\15\12\23\0\2\12\2\0\1\12\1\0\3\12"+
    "\1\100\26\12\23\0\2\12\2\0\1\12\1\0\22\12"+
    "\1\144\2\12\1\33\4\12\23\0\2\12\2\0\1\12"+
    "\1\0\7\12\1\116\22\12\23\0\2\12\2\0\1\12"+
    "\1\0\4\12\1\145\25\12\23\0\2\12\2\0\1\12"+
    "\1\0\2\12\1\146\27\12\23\0\2\12\2\0\1\12"+
    "\1\0\1\147\31\12\23\0\2\12\2\0\1\12\1\0"+
    "\3\12\1\150\26\12\23\0\2\12\2\0\1\12\1\0"+
    "\6\12\1\151\23\12\23\0\2\12\2\0\1\12\1\0"+
    "\6\12\1\152\23\12\23\0\2\12\2\0\1\12\1\0"+
    "\27\12\1\153\2\12\23\0\2\12\2\0\1\12\1\0"+
    "\31\12\1\145\14\0\4\110\1\5\1\134\55\110\7\0"+
    "\1\154\61\0\1\155\1\156\4\0\1\155\55\0\1\137"+
    "\1\55\1\0\1\157\1\57\1\0\3\55\1\57\26\55"+
    "\23\0\1\160\1\55\2\0\1\55\1\0\32\55\23\0"+
    "\1\141\1\55\1\0\1\161\1\162\1\0\3\55\1\162"+
    "\26\55\22\0\1\155\1\112\1\55\2\0\1\55\1\155"+
    "\32\55\23\0\2\12\2\0\1\12\1\0\4\12\1\100"+
    "\25\12\23\0\2\12\2\0\1\12\1\0\3\12\1\163"+
    "\26\12\23\0\2\12\2\0\1\12\1\0\3\12\1\164"+
    "\26\12\23\0\2\12\2\0\1\12\1\0\1\12\1\165"+
    "\30\12\23\0\2\12\2\0\1\12\1\0\3\12\1\166"+
    "\26\12\23\0\2\12\2\0\1\12\1\0\22\12\1\167"+
    "\7\12\23\0\2\12\2\0\1\12\1\0\5\12\1\170"+
    "\24\12\23\0\1\112\3\0\1\136\4\0\1\136\51\0"+
    "\1\156\62\0\1\112\62\0\1\171\62\0\1\160\1\55"+
    "\1\0\1\157\1\172\1\0\3\55\1\172\26\55\23\0"+
    "\1\173\61\0\1\174\1\175\1\55\2\0\1\55\1\174"+
    "\32\55\23\0\2\12\2\0\1\12\1\0\14\12\1\176"+
    "\15\12\23\0\2\12\2\0\1\12\1\0\26\12\1\177"+
    "\3\12\23\0\2\12\2\0\1\12\1\0\3\12\1\200"+
    "\26\12\23\0\2\12\2\0\1\12\1\0\15\12\1\201"+
    "\14\12\23\0\2\12\2\0\1\12\1\0\3\12\1\202"+
    "\26\12\23\0\2\12\2\0\1\12\1\0\12\12\1\203"+
    "\17\12\23\0\1\204\1\55\2\0\1\172\1\0\3\55"+
    "\1\172\26\55\22\0\1\205\1\175\1\55\2\0\1\55"+
    "\1\205\32\55\23\0\1\206\1\55\2\0\1\162\1\0"+
    "\3\55\1\162\26\55\23\0\1\207\62\0\1\206\1\55"+
    "\1\0\1\135\1\142\1\0\3\55\1\142\26\55\23\0"+
    "\2\12\2\0\1\12\1\0\15\12\1\210\14\12\23\0"+
    "\2\12\2\0\1\12\1\0\1\145\31\12\23\0\2\12"+
    "\2\0\1\12\1\0\17\12\1\211\12\12\23\0\2\12"+
    "\2\0\1\12\1\0\1\212\31\12\23\0\2\12\2\0"+
    "\1\12\1\0\7\12\1\100\22\12\23\0\2\12\2\0"+
    "\1\12\1\0\3\12\1\145\26\12\23\0\1\204\1\55"+
    "\1\0\1\157\1\172\1\0\3\55\1\172\26\55\23\0"+
    "\1\213\62\0\1\206\1\55\1\0\1\161\1\162\1\0"+
    "\3\55\1\162\26\55\23\0\1\206\1\55\2\0\1\55"+
    "\1\0\32\55\23\0\2\12\2\0\1\12\1\0\3\12"+
    "\1\124\26\12\23\0\2\12\2\0\1\12\1\0\10\12"+
    "\1\214\21\12\23\0\2\12\2\0\1\12\1\0\22\12"+
    "\1\215\7\12\23\0\1\204\1\55\2\0\1\55\1\0"+
    "\32\55\23\0\2\12\2\0\1\12\1\0\3\12\1\216"+
    "\26\12\23\0\2\12\2\0\1\12\1\0\21\12\1\217"+
    "\10\12\23\0\2\12\2\0\1\12\1\0\1\124\31\12"+
    "\23\0\2\12\2\0\1\12\1\0\14\12\1\124\15\12"+
    "\14\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6069];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\2\1\1\11\1\1\1\11\5\1\1\11"+
    "\22\1\11\11\1\1\2\0\1\11\1\1\1\0\1\1"+
    "\1\0\2\11\21\1\4\11\2\0\2\1\1\0\17\1"+
    "\3\0\16\1\1\0\1\1\1\0\1\1\1\0\7\1"+
    "\1\0\2\1\1\0\7\1\2\0\5\1\1\0\4\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[143];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
	StringBuilder erros = new StringBuilder();
	
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


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  AnaliseLexicaOCL(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  AnaliseLexicaOCL(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 140) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 6: 
          { return symbol(sym.INTEGER);
          }
        case 34: break;
        case 33: 
          { return symbol(sym.KEYWORD);
          }
        case 35: break;
        case 24: 
          { return symbol(sym.DOT_DOT);
          }
        case 36: break;
        case 31: 
          { return symbol(sym.COLLECTION);
          }
        case 37: break;
        case 25: 
          { return symbol(sym.BOOLEANOPERATOR);
          }
        case 38: break;
        case 3: 
          { return symbol(sym.DIVIDE);
          }
        case 39: break;
        case 12: 
          { return symbol(sym.LEFT_PAR);
          }
        case 40: break;
        case 17: 
          { return symbol(sym.RIGHT_BRA);
          }
        case 41: break;
        case 29: 
          { return symbol(sym.LE);
          }
        case 42: break;
        case 13: 
          { return symbol(sym.LEFT_BRK);
          }
        case 43: break;
        case 10: 
          { return symbol(sym.COLON);
          }
        case 44: break;
        case 26: 
          { return symbol(sym.PATHNAME);
          }
        case 45: break;
        case 19: 
          { return symbol(sym.BAR);
          }
        case 46: break;
        case 18: 
          { return symbol(sym.COMMA);
          }
        case 47: break;
        case 27: 
          { return symbol(sym.GE);
          }
        case 48: break;
        case 28: 
          { return symbol(sym.NE);
          }
        case 49: break;
        case 21: 
          { return symbol(sym.LT);
          }
        case 50: break;
        case 23: 
          { return symbol(sym.STRING);
          }
        case 51: break;
        case 1: 
          { erros.append("Lexema n�o identificado: <"+ yytext()+">" + " Linha: "+ yyline + " Coluna: "+ yycolumn+"\r");
          }
        case 52: break;
        case 14: 
          { return symbol(sym.LEFT_BRA);
          }
        case 53: break;
        case 22: 
          { return symbol(sym.MINUS_GT);
          }
        case 54: break;
        case 15: 
          { return symbol(sym.RIGHT_PAR);
          }
        case 55: break;
        case 16: 
          { return symbol(sym.RIGHT_BRK);
          }
        case 56: break;
        case 32: 
          { return symbol(sym.BOOLEAN);
          }
        case 57: break;
        case 7: 
          { return symbol(sym.IDENTIFIER);
          }
        case 58: break;
        case 9: 
          { return symbol(sym.PLUS);
          }
        case 59: break;
        case 5: 
          { return symbol(sym.MINUS);
          }
        case 60: break;
        case 11: 
          { return symbol(sym.GT);
          }
        case 61: break;
        case 20: 
          { return symbol(sym.EQ);
          }
        case 62: break;
        case 4: 
          { return symbol(sym.TIMES);
          }
        case 63: break;
        case 30: 
          { return symbol(sym.REAL);
          }
        case 64: break;
        case 8: 
          { return symbol(sym.DOT);
          }
        case 65: break;
        case 2: 
          { 
          }
        case 66: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {
                System.err.println(erros.toString());System.exit(1);
              }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java AnaliseLexicaOCL <inputfile>");
    }
    else {
      for (int i = 0; i < argv.length; i++) {
        AnaliseLexicaOCL scanner = null;
        try {
          scanner = new AnaliseLexicaOCL( new java.io.FileReader(argv[i]) );
          while ( !scanner.zzAtEOF ) scanner.next_token();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}