/* The following code was generated by JFlex 1.4.3 on 26/04/11 22:37 */

package analise_lexica;

import java_cup.runtime.*;
import util.sym; 


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 26/04/11 22:37 from the specification file
 * <tt>AnaliseLexica.flex</tt>
 */
public class AnaliseLexica implements java_cup.runtime.Scanner {

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
    "\11\0\1\3\1\1\1\0\1\3\1\2\22\0\1\3\6\0\1\11"+
    "\1\42\1\45\1\5\1\14\1\50\1\6\1\12\1\4\12\7\1\40"+
    "\1\0\1\53\1\52\1\41\2\0\1\10\1\26\1\35\1\10\1\13"+
    "\3\10\1\64\5\10\1\33\2\10\1\63\1\25\7\10\1\43\1\65"+
    "\1\46\1\0\1\10\1\0\1\22\1\57\1\32\1\34\1\20\1\21"+
    "\1\27\1\61\1\37\1\10\1\55\1\23\1\62\1\31\1\36\1\54"+
    "\1\30\1\16\1\24\1\15\1\17\2\10\1\56\1\60\1\10\1\44"+
    "\1\51\1\47\uff82\0";

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
    "\1\10\1\11\1\12\15\7\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\5\7\1\27\1\30\1\31\2\0\1\32\1\0\17\7"+
    "\1\33\1\34\1\7\1\35\1\36\1\37\1\40\5\7"+
    "\1\41\1\42\1\43\1\44\1\0\1\45\5\7\1\46"+
    "\2\7\1\47\2\7\1\50\5\7\1\51\3\7\1\2"+
    "\1\0\1\52\1\53\1\54\2\7\1\55\10\7\1\56"+
    "\1\57\1\7\1\0\1\45\1\60\13\7\1\61\12\7"+
    "\1\62\1\63\2\7\1\64\1\65\1\66\6\7\1\67";

  private static int [] zzUnpackAction() {
    int [] result = new int[163];
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
    "\0\0\0\66\0\154\0\242\0\330\0\154\0\u010e\0\154"+
    "\0\u0144\0\u017a\0\u01b0\0\154\0\154\0\154\0\u01e6\0\u021c"+
    "\0\u0252\0\u0288\0\u02be\0\u02f4\0\u032a\0\u0360\0\u0396\0\u03cc"+
    "\0\u0402\0\u0438\0\u046e\0\u04a4\0\u04da\0\154\0\154\0\154"+
    "\0\154\0\154\0\154\0\154\0\154\0\154\0\u0510\0\u0546"+
    "\0\u057c\0\u05b2\0\u05e8\0\u061e\0\u0654\0\154\0\u068a\0\u06c0"+
    "\0\u06f6\0\154\0\u072c\0\u0762\0\u0798\0\u07ce\0\u0804\0\u083a"+
    "\0\u0870\0\u08a6\0\u08dc\0\u0912\0\u0948\0\u097e\0\u09b4\0\u09ea"+
    "\0\u0a20\0\u0a56\0\u01b0\0\u01b0\0\u0a8c\0\154\0\154\0\154"+
    "\0\154\0\u0ac2\0\u0af8\0\u0b2e\0\u0b64\0\u0b9a\0\154\0\154"+
    "\0\154\0\154\0\u0bd0\0\u0c06\0\u0c3c\0\u0c72\0\u0ca8\0\u0cde"+
    "\0\u0d14\0\u01b0\0\u0d4a\0\u0d80\0\u01b0\0\u0db6\0\u0dec\0\u01b0"+
    "\0\u0e22\0\u0e58\0\u0e8e\0\u0ec4\0\u0efa\0\u01b0\0\u0f30\0\u0f66"+
    "\0\u0f9c\0\u06c0\0\u0fd2\0\u01b0\0\u01b0\0\u01b0\0\u1008\0\u103e"+
    "\0\u01b0\0\u1074\0\u10aa\0\u10e0\0\u1116\0\u114c\0\u1182\0\u11b8"+
    "\0\u11ee\0\u01b0\0\u01b0\0\u1224\0\u125a\0\u125a\0\u01b0\0\u1290"+
    "\0\u12c6\0\u12fc\0\u1332\0\u1368\0\u139e\0\u13d4\0\u140a\0\u1440"+
    "\0\u1476\0\u14ac\0\u01b0\0\u14e2\0\u1518\0\u154e\0\u1584\0\u15ba"+
    "\0\u15f0\0\u1626\0\u165c\0\u1692\0\u16c8\0\u01b0\0\u01b0\0\u16fe"+
    "\0\u1734\0\u01b0\0\u01b0\0\u01b0\0\u176a\0\u17a0\0\u17d6\0\u180c"+
    "\0\u1842\0\u1878\0\u01b0";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[163];
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
    "\1\13\1\14\1\15\1\13\1\16\1\17\2\13\1\20"+
    "\1\21\1\22\1\13\1\23\1\24\1\25\2\13\1\26"+
    "\1\27\1\30\1\13\1\31\1\32\1\33\1\34\1\35"+
    "\1\36\1\37\1\40\1\41\1\42\1\43\1\44\1\45"+
    "\1\46\1\47\1\50\1\13\1\51\1\52\3\13\1\53"+
    "\1\54\1\3\1\55\2\3\6\55\1\56\53\55\1\57"+
    "\70\0\1\6\64\0\1\6\71\0\1\60\66\0\1\61"+
    "\1\12\31\0\1\62\33\0\1\12\2\0\1\63\62\0"+
    "\2\13\2\0\1\13\1\0\23\13\14\0\11\13\10\0"+
    "\2\13\2\0\1\13\1\0\1\13\1\64\21\13\14\0"+
    "\5\13\1\65\3\13\10\0\2\13\2\0\1\13\1\0"+
    "\6\13\1\66\5\13\1\67\6\13\14\0\11\13\10\0"+
    "\2\13\2\0\1\13\1\0\5\13\1\70\15\13\14\0"+
    "\11\13\10\0\2\13\2\0\1\13\1\0\14\13\1\71"+
    "\6\13\14\0\11\13\10\0\2\13\2\0\1\13\1\0"+
    "\3\13\1\72\17\13\14\0\11\13\10\0\2\13\2\0"+
    "\1\13\1\0\1\73\2\13\1\74\17\13\14\0\11\13"+
    "\10\0\2\13\2\0\1\13\1\0\5\13\1\75\13\13"+
    "\1\76\1\13\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\21\13\1\77\1\13\14\0\11\13\10\0\2\13"+
    "\2\0\1\13\1\0\21\13\1\100\1\13\14\0\11\13"+
    "\10\0\2\13\2\0\1\13\1\0\1\13\1\101\21\13"+
    "\14\0\11\13\10\0\2\13\2\0\1\13\1\0\21\13"+
    "\1\102\1\13\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\1\13\1\103\21\13\14\0\11\13\10\0\2\13"+
    "\2\0\1\13\1\0\4\13\1\104\16\13\14\0\6\13"+
    "\1\105\2\13\41\0\1\106\77\0\1\107\54\0\1\110"+
    "\10\0\1\111\22\0\2\13\2\0\1\13\1\0\5\13"+
    "\1\112\15\13\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\21\13\1\113\1\13\14\0\11\13\10\0\2\13"+
    "\2\0\1\13\1\0\21\13\1\114\1\13\14\0\11\13"+
    "\10\0\2\13\2\0\1\13\1\0\3\13\1\115\17\13"+
    "\14\0\11\13\10\0\2\13\2\0\1\13\1\0\14\13"+
    "\1\116\6\13\14\0\11\13\1\0\1\55\2\0\6\55"+
    "\1\0\53\55\12\0\1\117\3\0\1\120\1\121\12\0"+
    "\1\122\34\0\5\60\1\123\60\60\1\61\1\4\1\5"+
    "\63\61\7\0\1\124\65\0\2\13\2\0\1\13\1\0"+
    "\2\13\1\125\20\13\14\0\11\13\10\0\2\13\2\0"+
    "\1\13\1\0\3\13\1\126\17\13\14\0\11\13\10\0"+
    "\2\13\2\0\1\13\1\0\7\13\1\127\13\13\14\0"+
    "\11\13\10\0\2\13\2\0\1\13\1\0\17\13\1\130"+
    "\3\13\14\0\11\13\10\0\2\13\2\0\1\13\1\0"+
    "\6\13\1\131\14\13\14\0\11\13\10\0\2\13\2\0"+
    "\1\13\1\0\17\13\1\132\3\13\14\0\11\13\10\0"+
    "\2\13\2\0\1\13\1\0\6\13\1\133\14\13\14\0"+
    "\11\13\10\0\2\13\2\0\1\13\1\0\1\13\1\134"+
    "\21\13\14\0\11\13\10\0\2\13\2\0\1\13\1\0"+
    "\1\135\12\13\1\136\7\13\14\0\11\13\10\0\2\13"+
    "\2\0\1\13\1\0\12\13\1\135\10\13\14\0\11\13"+
    "\10\0\2\13\2\0\1\13\1\0\21\13\1\137\1\13"+
    "\14\0\11\13\10\0\2\13\2\0\1\13\1\0\1\140"+
    "\22\13\14\0\11\13\10\0\2\13\2\0\1\13\1\0"+
    "\14\13\1\141\6\13\14\0\11\13\10\0\2\13\2\0"+
    "\1\13\1\0\17\13\1\142\3\13\14\0\11\13\10\0"+
    "\2\13\2\0\1\13\1\0\6\13\1\143\14\13\14\0"+
    "\11\13\10\0\2\13\2\0\1\13\1\0\23\13\14\0"+
    "\1\144\10\13\10\0\2\13\2\0\1\13\1\0\15\13"+
    "\1\145\5\13\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\1\13\1\146\21\13\14\0\11\13\10\0\2\13"+
    "\2\0\1\13\1\0\17\13\1\147\3\13\14\0\11\13"+
    "\10\0\2\13\2\0\1\13\1\0\5\13\1\150\15\13"+
    "\14\0\11\13\10\0\2\13\2\0\1\13\1\0\1\151"+
    "\22\13\14\0\11\13\1\0\4\60\1\152\1\123\60\60"+
    "\7\0\1\124\3\0\1\153\4\0\1\153\54\0\2\13"+
    "\2\0\1\13\1\0\3\13\1\154\17\13\14\0\11\13"+
    "\10\0\2\13\2\0\1\13\1\0\14\13\1\155\6\13"+
    "\14\0\11\13\10\0\2\13\2\0\1\13\1\0\3\13"+
    "\1\156\17\13\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\22\13\1\157\14\0\1\160\10\13\10\0\2\13"+
    "\2\0\1\13\1\0\7\13\1\125\13\13\14\0\11\13"+
    "\10\0\2\13\2\0\1\13\1\0\4\13\1\161\16\13"+
    "\14\0\11\13\10\0\2\13\2\0\1\13\1\0\22\13"+
    "\1\162\14\0\11\13\10\0\2\13\2\0\1\13\1\0"+
    "\2\13\1\163\20\13\14\0\11\13\10\0\2\13\2\0"+
    "\1\13\1\0\6\13\1\164\14\13\14\0\11\13\10\0"+
    "\2\13\2\0\1\13\1\0\1\165\22\13\14\0\11\13"+
    "\10\0\2\13\2\0\1\13\1\0\3\13\1\166\17\13"+
    "\14\0\11\13\10\0\2\13\2\0\1\13\1\0\6\13"+
    "\1\167\14\13\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\6\13\1\170\14\13\14\0\11\13\10\0\2\13"+
    "\2\0\1\13\1\0\23\13\14\0\1\13\1\171\7\13"+
    "\10\0\2\13\2\0\1\13\1\0\23\13\14\0\4\13"+
    "\1\172\4\13\10\0\2\13\2\0\1\13\1\0\6\13"+
    "\1\173\14\13\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\3\13\1\174\17\13\14\0\11\13\7\0\1\175"+
    "\1\176\4\0\1\175\60\0\2\13\2\0\1\13\1\0"+
    "\4\13\1\177\16\13\14\0\11\13\10\0\2\13\2\0"+
    "\1\13\1\0\5\13\1\200\15\13\14\0\11\13\10\0"+
    "\2\13\2\0\1\13\1\0\14\13\1\201\6\13\14\0"+
    "\11\13\10\0\2\13\2\0\1\13\1\0\3\13\1\202"+
    "\17\13\14\0\11\13\10\0\2\13\2\0\1\13\1\0"+
    "\3\13\1\203\17\13\14\0\11\13\10\0\2\13\2\0"+
    "\1\13\1\0\3\13\1\204\17\13\14\0\11\13\10\0"+
    "\2\13\2\0\1\13\1\0\1\13\1\205\21\13\14\0"+
    "\11\13\10\0\2\13\2\0\1\13\1\0\3\13\1\206"+
    "\17\13\14\0\11\13\10\0\2\13\2\0\1\13\1\0"+
    "\22\13\1\207\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\5\13\1\210\15\13\14\0\11\13\10\0\2\13"+
    "\2\0\1\13\1\0\12\13\1\211\10\13\14\0\11\13"+
    "\10\0\1\176\65\0\2\13\2\0\1\13\1\0\15\13"+
    "\1\212\5\13\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\12\13\1\213\10\13\14\0\11\13\10\0\2\13"+
    "\2\0\1\13\1\0\14\13\1\214\6\13\14\0\11\13"+
    "\10\0\2\13\2\0\1\13\1\0\5\13\1\215\15\13"+
    "\14\0\11\13\10\0\2\13\2\0\1\13\1\0\23\13"+
    "\14\0\2\13\1\216\6\13\10\0\2\13\2\0\1\13"+
    "\1\0\3\13\1\217\17\13\14\0\11\13\10\0\2\13"+
    "\2\0\1\13\1\0\15\13\1\220\5\13\14\0\11\13"+
    "\10\0\2\13\2\0\1\13\1\0\3\13\1\221\17\13"+
    "\14\0\11\13\10\0\2\13\2\0\1\13\1\0\12\13"+
    "\1\222\10\13\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\3\13\1\223\17\13\14\0\11\13\10\0\2\13"+
    "\2\0\1\13\1\0\23\13\14\0\1\13\1\224\7\13"+
    "\10\0\2\13\2\0\1\13\1\0\15\13\1\225\5\13"+
    "\14\0\11\13\10\0\2\13\2\0\1\13\1\0\14\13"+
    "\1\226\6\13\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\1\227\22\13\14\0\11\13\10\0\2\13\2\0"+
    "\1\13\1\0\17\13\1\230\3\13\14\0\11\13\10\0"+
    "\2\13\2\0\1\13\1\0\1\231\22\13\14\0\11\13"+
    "\10\0\2\13\2\0\1\13\1\0\7\13\1\232\13\13"+
    "\14\0\11\13\10\0\2\13\2\0\1\13\1\0\3\13"+
    "\1\233\17\13\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\1\13\1\234\21\13\14\0\11\13\10\0\2\13"+
    "\2\0\1\13\1\0\5\13\1\235\15\13\14\0\11\13"+
    "\10\0\2\13\2\0\1\13\1\0\3\13\1\135\17\13"+
    "\14\0\11\13\10\0\2\13\2\0\1\13\1\0\10\13"+
    "\1\236\12\13\14\0\11\13\10\0\2\13\2\0\1\13"+
    "\1\0\22\13\1\237\14\0\11\13\10\0\2\13\2\0"+
    "\1\13\1\0\12\13\1\240\10\13\14\0\11\13\10\0"+
    "\2\13\2\0\1\13\1\0\3\13\1\241\17\13\14\0"+
    "\11\13\10\0\2\13\2\0\1\13\1\0\21\13\1\242"+
    "\1\13\14\0\11\13\10\0\2\13\2\0\1\13\1\0"+
    "\3\13\1\243\17\13\14\0\11\13\10\0\2\13\2\0"+
    "\1\13\1\0\1\135\22\13\14\0\11\13\10\0\2\13"+
    "\2\0\1\13\1\0\14\13\1\135\6\13\14\0\11\13"+
    "\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6318];
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
    "\2\0\1\11\2\1\1\11\1\1\1\11\3\1\3\11"+
    "\17\1\11\11\7\1\1\11\1\1\2\0\1\11\1\0"+
    "\22\1\4\11\5\1\4\11\1\0\27\1\1\0\21\1"+
    "\1\0\46\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[163];
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
	StringBuilder string = new StringBuilder();
	
	private Symbol symbol(int tokenname) {
		Symbol symbol = new Symbol(tokenname, yyline, yycolumn, yytext());
		return symbol;
	}
	private Symbol symbol(int tokenname, Object value) {
		Symbol symbol = new Symbol(tokenname, yyline, yycolumn, value);
		return symbol;
	}


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  AnaliseLexica(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public AnaliseLexica(java.io.InputStream in) {
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
    while (i < 146) {
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
        case 1: 
          { throw new Error("Lexema n�o reconhecido pela linguagem OCL: <"+ yytext()+">" + " Linha: "+ (yyline + 1) + " Coluna: "+ (yycolumn + 1)+"\r");
          }
        case 56: break;
        case 20: 
          { return symbol(sym.BAR);
          }
        case 57: break;
        case 24: 
          { yybegin(YYINITIAL); return symbol(sym.STRING, string.toString());
          }
        case 58: break;
        case 17: 
          { return symbol(sym.RIGHT_BRK);
          }
        case 59: break;
        case 15: 
          { return symbol(sym.LEFT_BRA);
          }
        case 60: break;
        case 51: 
          { return symbol(sym.CONTEXT);
          }
        case 61: break;
        case 12: 
          { return symbol(sym.GT);
          }
        case 62: break;
        case 34: 
          { string.append('\t');
          }
        case 63: break;
        case 22: 
          { return symbol(sym.LT);
          }
        case 64: break;
        case 8: 
          { string.setLength(0); yybegin(STRING);
          }
        case 65: break;
        case 50: 
          { return symbol(sym.BOOLEANTYPE);
          }
        case 66: break;
        case 18: 
          { return symbol(sym.RIGHT_BRA);
          }
        case 67: break;
        case 29: 
          { return symbol(sym.PATHNAME);
          }
        case 68: break;
        case 40: 
          { return symbol(sym.NOT);
          }
        case 69: break;
        case 38: 
          { return symbol(sym.AND);
          }
        case 70: break;
        case 13: 
          { return symbol(sym.LEFT_PAR);
          }
        case 71: break;
        case 39: 
          { return symbol(sym.COLLECTION);
          }
        case 72: break;
        case 25: 
          { string.append('\\');
          }
        case 73: break;
        case 35: 
          { string.append('\r');
          }
        case 74: break;
        case 10: 
          { return symbol(sym.PLUS);
          }
        case 75: break;
        case 16: 
          { return symbol(sym.RIGHT_PAR);
          }
        case 76: break;
        case 26: 
          { return symbol(sym.COLLECTIONOPERATION);
          }
        case 77: break;
        case 54: 
          { return symbol(sym.INTEGERTYPE);
          }
        case 78: break;
        case 4: 
          { return symbol(sym.TIMES);
          }
        case 79: break;
        case 44: 
          { return symbol(sym.ELSE);
          }
        case 80: break;
        case 11: 
          { return symbol(sym.COLON);
          }
        case 81: break;
        case 7: 
          { return symbol(sym.IDENTIFIER);
          }
        case 82: break;
        case 43: 
          { return symbol(sym.THEN);
          }
        case 83: break;
        case 33: 
          { string.append('\'');
          }
        case 84: break;
        case 28: 
          { return symbol(sym.IF);
          }
        case 85: break;
        case 52: 
          { return symbol(sym.IMPLIES);
          }
        case 86: break;
        case 48: 
          { return symbol(sym.ENDIF);
          }
        case 87: break;
        case 47: 
          { return symbol(sym.REALTYPE);
          }
        case 88: break;
        case 45: 
          { return symbol(sym.SELF);
          }
        case 89: break;
        case 27: 
          { return symbol(sym.OR);
          }
        case 90: break;
        case 46: 
          { return symbol(sym.BODY);
          }
        case 91: break;
        case 21: 
          { return symbol(sym.EQ);
          }
        case 92: break;
        case 36: 
          { string.append('\n');
          }
        case 93: break;
        case 53: 
          { return symbol(sym.PACKAGE);
          }
        case 94: break;
        case 31: 
          { return symbol(sym.NE);
          }
        case 95: break;
        case 41: 
          { return symbol(sym.XOR);
          }
        case 96: break;
        case 5: 
          { return symbol(sym.MINUS);
          }
        case 97: break;
        case 6: 
          { return symbol(sym.INTEGER, new Integer(yytext()));
          }
        case 98: break;
        case 23: 
          { string.append( yytext());
          }
        case 99: break;
        case 42: 
          { return symbol(sym.BOOLEAN);
          }
        case 100: break;
        case 49: 
          { return symbol(sym.STRINGTYPE);
          }
        case 101: break;
        case 30: 
          { return symbol(sym.GE);
          }
        case 102: break;
        case 3: 
          { return symbol(sym.DIVIDE);
          }
        case 103: break;
        case 19: 
          { return symbol(sym.COMMA);
          }
        case 104: break;
        case 14: 
          { return symbol(sym.LEFT_BRK);
          }
        case 105: break;
        case 37: 
          { return symbol(sym.REAL);
          }
        case 106: break;
        case 32: 
          { return symbol(sym.LE);
          }
        case 107: break;
        case 55: 
          { return symbol(sym.ENDPACKAGE);
          }
        case 108: break;
        case 9: 
          { return symbol(sym.DOT);
          }
        case 109: break;
        case 2: 
          { 
          }
        case 110: break;
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


}