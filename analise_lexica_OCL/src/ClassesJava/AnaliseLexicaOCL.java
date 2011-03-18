/* The following code was generated by JFlex 1.4.3 on 17/03/11 22:20 */

package ClassesJava;

import java_cup.runtime.Symbol;
import java_cup.sym; 


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 17/03/11 22:20 from the specification file
 * <tt>C:/Users/Nat�/Desktop/UFCG/Disciplinas/Compiladores/Projeto/workspace/analise_lexica_OCL/src/flex/analiseOCL.flex</tt>
 */
class AnaliseLexicaOCL implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING = 2;
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\1\1\0\1\3\1\2\22\0\1\3\6\0\1\62"+
    "\1\50\1\53\1\5\1\13\1\56\1\6\1\11\1\4\12\7\1\46"+
    "\1\0\1\61\1\60\1\47\2\0\1\10\1\25\1\34\1\10\1\12"+
    "\11\10\1\32\3\10\1\24\7\10\1\51\1\63\1\54\1\0\1\10"+
    "\1\0\1\21\1\44\1\31\1\33\1\17\1\20\1\26\1\37\1\36"+
    "\1\10\1\43\1\22\1\40\1\30\1\35\1\41\1\27\1\15\1\23"+
    "\1\14\1\16\2\10\1\42\1\45\1\10\1\52\1\57\1\55\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\3\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\20\7\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\1\27\1\30\2\0\1\31\2\0\1\32\15\7\1\33"+
    "\3\7\1\34\1\35\1\36\1\37\1\40\1\41\1\42"+
    "\1\43\2\0\1\44\1\0\1\44\6\7\1\45\7\7"+
    "\1\0\1\46\1\7\1\47\31\7";

  private static int [] zzUnpackAction() {
    int [] result = new int[123];
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
    "\0\0\0\64\0\150\0\234\0\320\0\150\0\u0104\0\150"+
    "\0\u0138\0\u016c\0\u01a0\0\u01d4\0\150\0\u0208\0\u023c\0\u0270"+
    "\0\u02a4\0\u02d8\0\u030c\0\u0340\0\u0374\0\u03a8\0\u03dc\0\u0410"+
    "\0\u0444\0\u0478\0\u04ac\0\u04e0\0\u0514\0\u0548\0\u057c\0\150"+
    "\0\150\0\150\0\150\0\150\0\150\0\150\0\150\0\150"+
    "\0\u05b0\0\u05e4\0\150\0\u0618\0\u064c\0\u0680\0\150\0\u06b4"+
    "\0\u06e8\0\150\0\u071c\0\u0750\0\u0784\0\u07b8\0\u07ec\0\u0820"+
    "\0\u0854\0\u0888\0\u08bc\0\u08f0\0\u0924\0\u0958\0\u098c\0\u01a0"+
    "\0\u09c0\0\u09f4\0\u0a28\0\150\0\150\0\150\0\150\0\150"+
    "\0\150\0\150\0\150\0\u0a5c\0\u0a90\0\u0ac4\0\u0af8\0\u0af8"+
    "\0\u0b2c\0\u0b60\0\u0b94\0\u0bc8\0\u0bfc\0\u0c30\0\u01a0\0\u0c64"+
    "\0\u0c98\0\u0ccc\0\u0d00\0\u0d34\0\u0d68\0\u0d9c\0\u0dd0\0\u01a0"+
    "\0\u0e04\0\u01a0\0\u0e38\0\u0e6c\0\u0ea0\0\u0ed4\0\u0f08\0\u0f3c"+
    "\0\u0f70\0\u0fa4\0\u0fd8\0\u100c\0\u1040\0\u1074\0\u10a8\0\u10dc"+
    "\0\u1110\0\u1144\0\u1178\0\u11ac\0\u11e0\0\u1214\0\u1248\0\u127c"+
    "\0\u12b0\0\u12e4\0\u1318";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[123];
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
    "\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12"+
    "\1\13\1\14\1\13\1\15\1\16\2\13\1\17\1\20"+
    "\1\21\1\13\1\22\1\23\1\24\2\13\1\25\1\26"+
    "\1\27\1\13\1\30\1\31\1\32\2\13\1\33\1\34"+
    "\1\13\1\35\1\13\1\36\1\37\1\40\1\41\1\42"+
    "\1\43\1\44\1\45\1\46\1\47\1\50\1\51\2\3"+
    "\1\52\2\3\57\52\1\53\1\54\66\0\1\6\62\0"+
    "\1\6\67\0\1\55\64\0\1\56\40\0\1\57\23\0"+
    "\1\12\1\0\1\60\1\61\4\0\1\61\53\0\2\13"+
    "\1\0\1\13\1\0\32\13\27\0\1\62\61\0\2\13"+
    "\1\0\1\13\1\0\1\13\1\63\21\13\1\64\6\13"+
    "\25\0\2\13\1\0\1\13\1\0\6\13\1\65\5\13"+
    "\1\66\15\13\25\0\2\13\1\0\1\13\1\0\5\13"+
    "\1\67\24\13\25\0\2\13\1\0\1\13\1\0\14\13"+
    "\1\70\15\13\25\0\2\13\1\0\1\13\1\0\3\13"+
    "\1\71\26\13\25\0\2\13\1\0\1\13\1\0\3\13"+
    "\1\72\26\13\25\0\2\13\1\0\1\13\1\0\5\13"+
    "\1\73\24\13\25\0\2\13\1\0\1\13\1\0\21\13"+
    "\1\74\10\13\25\0\2\13\1\0\1\13\1\0\21\13"+
    "\1\75\10\13\25\0\2\13\1\0\1\13\1\0\1\13"+
    "\1\76\30\13\25\0\2\13\1\0\1\13\1\0\21\13"+
    "\1\77\10\13\25\0\2\13\1\0\1\13\1\0\1\13"+
    "\1\100\30\13\25\0\2\13\1\0\1\13\1\0\4\13"+
    "\1\100\17\13\1\101\5\13\25\0\2\13\1\0\1\13"+
    "\1\0\5\13\1\102\24\13\25\0\2\13\1\0\1\13"+
    "\1\0\21\13\1\31\10\13\25\0\2\13\1\0\1\13"+
    "\1\0\21\13\1\103\10\13\64\0\1\104\75\0\1\105"+
    "\52\0\1\106\10\0\1\107\3\0\1\52\2\0\57\52"+
    "\16\0\1\110\1\111\12\0\1\112\31\0\1\113\1\0"+
    "\5\114\1\115\56\114\1\56\1\4\1\5\61\56\7\0"+
    "\1\116\62\0\1\117\1\120\3\0\1\117\57\0\2\13"+
    "\1\0\1\13\1\0\2\13\1\121\27\13\25\0\2\13"+
    "\1\0\1\13\1\0\3\13\1\122\26\13\25\0\2\13"+
    "\1\0\1\13\1\0\7\13\1\123\22\13\25\0\2\13"+
    "\1\0\1\13\1\0\17\13\1\124\12\13\25\0\2\13"+
    "\1\0\1\13\1\0\6\13\1\125\23\13\25\0\2\13"+
    "\1\0\1\13\1\0\17\13\1\100\12\13\25\0\2\13"+
    "\1\0\1\13\1\0\6\13\1\126\23\13\25\0\2\13"+
    "\1\0\1\13\1\0\1\127\12\13\1\130\16\13\25\0"+
    "\2\13\1\0\1\13\1\0\12\13\1\127\17\13\25\0"+
    "\2\13\1\0\1\13\1\0\1\100\31\13\25\0\2\13"+
    "\1\0\1\13\1\0\14\13\1\131\15\13\25\0\2\13"+
    "\1\0\1\13\1\0\17\13\1\132\12\13\25\0\2\13"+
    "\1\0\1\13\1\0\6\13\1\133\23\13\25\0\2\13"+
    "\1\0\1\13\1\0\25\13\1\134\4\13\25\0\2\13"+
    "\1\0\1\13\1\0\15\13\1\135\14\13\25\0\2\13"+
    "\1\0\1\13\1\0\17\13\1\136\12\13\16\0\5\114"+
    "\1\137\56\114\4\0\1\6\1\115\65\0\1\116\2\0"+
    "\1\61\4\0\1\61\53\0\1\120\63\0\2\13\1\0"+
    "\1\13\1\0\3\13\1\140\26\13\25\0\2\13\1\0"+
    "\1\13\1\0\14\13\1\100\15\13\25\0\2\13\1\0"+
    "\1\13\1\0\3\13\1\100\26\13\25\0\2\13\1\0"+
    "\1\13\1\0\22\13\1\141\2\13\1\33\4\13\25\0"+
    "\2\13\1\0\1\13\1\0\7\13\1\121\22\13\25\0"+
    "\2\13\1\0\1\13\1\0\4\13\1\142\25\13\25\0"+
    "\2\13\1\0\1\13\1\0\2\13\1\143\27\13\25\0"+
    "\2\13\1\0\1\13\1\0\1\144\31\13\25\0\2\13"+
    "\1\0\1\13\1\0\3\13\1\145\26\13\25\0\2\13"+
    "\1\0\1\13\1\0\6\13\1\146\23\13\25\0\2\13"+
    "\1\0\1\13\1\0\6\13\1\147\23\13\25\0\2\13"+
    "\1\0\1\13\1\0\27\13\1\150\2\13\25\0\2\13"+
    "\1\0\1\13\1\0\31\13\1\142\16\0\4\114\1\6"+
    "\1\137\56\114\7\0\2\13\1\0\1\13\1\0\4\13"+
    "\1\100\25\13\25\0\2\13\1\0\1\13\1\0\3\13"+
    "\1\151\26\13\25\0\2\13\1\0\1\13\1\0\3\13"+
    "\1\152\26\13\25\0\2\13\1\0\1\13\1\0\1\13"+
    "\1\153\30\13\25\0\2\13\1\0\1\13\1\0\3\13"+
    "\1\154\26\13\25\0\2\13\1\0\1\13\1\0\22\13"+
    "\1\155\7\13\25\0\2\13\1\0\1\13\1\0\5\13"+
    "\1\156\24\13\25\0\2\13\1\0\1\13\1\0\14\13"+
    "\1\157\15\13\25\0\2\13\1\0\1\13\1\0\26\13"+
    "\1\160\3\13\25\0\2\13\1\0\1\13\1\0\3\13"+
    "\1\161\26\13\25\0\2\13\1\0\1\13\1\0\15\13"+
    "\1\162\14\13\25\0\2\13\1\0\1\13\1\0\3\13"+
    "\1\163\26\13\25\0\2\13\1\0\1\13\1\0\12\13"+
    "\1\164\17\13\25\0\2\13\1\0\1\13\1\0\15\13"+
    "\1\165\14\13\25\0\2\13\1\0\1\13\1\0\1\142"+
    "\31\13\25\0\2\13\1\0\1\13\1\0\17\13\1\166"+
    "\12\13\25\0\2\13\1\0\1\13\1\0\1\167\31\13"+
    "\25\0\2\13\1\0\1\13\1\0\7\13\1\100\22\13"+
    "\25\0\2\13\1\0\1\13\1\0\3\13\1\142\26\13"+
    "\25\0\2\13\1\0\1\13\1\0\3\13\1\127\26\13"+
    "\25\0\2\13\1\0\1\13\1\0\10\13\1\170\21\13"+
    "\25\0\2\13\1\0\1\13\1\0\22\13\1\171\7\13"+
    "\25\0\2\13\1\0\1\13\1\0\3\13\1\172\26\13"+
    "\25\0\2\13\1\0\1\13\1\0\21\13\1\173\10\13"+
    "\25\0\2\13\1\0\1\13\1\0\1\127\31\13\25\0"+
    "\2\13\1\0\1\13\1\0\14\13\1\127\15\13\16\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4940];
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
    "\2\0\1\11\2\1\1\11\1\1\1\11\4\1\1\11"+
    "\22\1\11\11\2\1\1\11\1\1\2\0\1\11\2\0"+
    "\1\11\21\1\10\11\2\0\1\1\1\0\17\1\1\0"+
    "\34\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[123];
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
        case 19: 
          { return symbol(sym.BAR);
          }
        case 40: break;
        case 23: 
          { yybegin(YYINITIAL); return symbol(sym.STRING, string.toString());
          }
        case 41: break;
        case 16: 
          { return symbol(sym.RIGHT_BRK);
          }
        case 42: break;
        case 14: 
          { return symbol(sym.LEFT_BRA);
          }
        case 43: break;
        case 11: 
          { return symbol(sym.GT);
          }
        case 44: break;
        case 32: 
          { string.append("\t");
          }
        case 45: break;
        case 21: 
          { return symbol(sym.LT);
          }
        case 46: break;
        case 17: 
          { return symbol(sym.RIGHT_BRA);
          }
        case 47: break;
        case 1: 
          { System.out.println("Erro!"); throw new Error("Illegal character <"+ yytext()+">");
          }
        case 48: break;
        case 28: 
          { return symbol(sym.PATHNAME);
          }
        case 49: break;
        case 12: 
          { return symbol(sym.LEFT_PAR);
          }
        case 50: break;
        case 37: 
          { return symbol(sym.COLLECTION);
          }
        case 51: break;
        case 24: 
          { string.append("\\");
          }
        case 52: break;
        case 33: 
          { string.append("\r");
          }
        case 53: break;
        case 9: 
          { return symbol(sym.PLUS);
          }
        case 54: break;
        case 15: 
          { return symbol(sym.RIGHT_PAR);
          }
        case 55: break;
        case 4: 
          { return symbol(sym.TIMES);
          }
        case 56: break;
        case 10: 
          { return symbol(sym.COLON);
          }
        case 57: break;
        case 7: 
          { return symbol(sym.IDENTIFIER);
          }
        case 58: break;
        case 35: 
          { string.append("\'");
          }
        case 59: break;
        case 20: 
          { return symbol(sym.EQ);
          }
        case 60: break;
        case 34: 
          { string.append("\n");
          }
        case 61: break;
        case 27: 
          { return symbol(sym.BOOLEANOPERATOR);
          }
        case 62: break;
        case 30: 
          { return symbol(sym.NE);
          }
        case 63: break;
        case 6: 
          { return symbol(sym.INTEGER);
          }
        case 64: break;
        case 25: 
          { return symbol(sym.MINUS_GT);
          }
        case 65: break;
        case 5: 
          { return symbol(sym.MINUS);
          }
        case 66: break;
        case 38: 
          { return symbol(sym.BOOLEAN);
          }
        case 67: break;
        case 26: 
          { return symbol(sym.DOT_DOT);
          }
        case 68: break;
        case 29: 
          { return symbol(sym.GE);
          }
        case 69: break;
        case 3: 
          { return symbol(sym.DIVIDE);
          }
        case 70: break;
        case 18: 
          { return symbol(sym.COMMA);
          }
        case 71: break;
        case 13: 
          { return symbol(sym.LEFT_BRK);
          }
        case 72: break;
        case 36: 
          { return symbol(sym.REAL);
          }
        case 73: break;
        case 31: 
          { return symbol(sym.LE);
          }
        case 74: break;
        case 22: 
          { string.append( yytext() );
          }
        case 75: break;
        case 39: 
          { return symbol(sym.KEYWORD);
          }
        case 76: break;
        case 8: 
          { return symbol(sym.DOT);
          }
        case 77: break;
        case 2: 
          { 
          }
        case 78: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
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
