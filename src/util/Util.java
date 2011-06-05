package util;

import java_cup.runtime.Symbol;

public class Util {
	
	private static Logger log;
	
	public static void setLog(Logger l){
		log = l;
	}
	
	public static Logger getLog() {
		return log;
	}

	public static void report_error(String message, Object info) {
		if (info instanceof Symbol){
			Symbol sym = (Symbol) info;
			if (sym.left != -1){
				if(log != null){
					log.addError("Erro Sintatico na linha " + (sym.left + 1) + " e coluna " + (sym.right + 1) + "\n" + "Palavra " + sym.value + " nao esperada.");
				}
				System.out.println("Erro Sintatico na linha " + (sym.left + 1) + " e coluna " + (sym.right + 1));
				System.out.println("Palavra " + sym.value + " nao esperada.");
			}else{
				if(log != null){
					log.addError("Final de Arquivo e Restricao Invalida");
				}
				System.out.println("Final de Arquivo e Restricao Invalida");
			}
		}
	}

	public static void syntax_error(Symbol cur_token) {
		report_error("Erro sintático detectado: ", cur_token.value + " "
				+ cur_token.toString());
	}

	public static void debug_message(String mess) {
		String out = mess;
		if (mess.equals("# Initializing parser")) {
			out = ">>>> Iniciando Análise Sintática <<<<";
		} else if (mess.startsWith(("# Current Symbol is #"))) {
			out = mess.replace("# Current Symbol is #", "# Símbolo atual: #");
		} else if (mess.startsWith("# Reduce rule: top state ")) {
			out = mess.replace("# Reduce rule: top state ",
					"# Reduce - Estado do topo: #");
			out = out.replace(" lhs sym ", " Símbolo #");
			out = out.replace(" -> state ", " Estado #");
		} else if (mess.startsWith("# Goto state")) {
			out = mess.replace("# Goto state", "# Vai para o estado: ");
		} else if (mess.startsWith("# Current token is #")) {
			out = mess.replace("# Current token is #", "# Token Atual: #");
		} else if (mess.startsWith("Couldn't repair and continue parse")) {
			out = mess.replace("Couldn't repair and continue parse",
					"Análise Sintática Interrompida!");
		}
		out = changeTokenNames(out);

		System.err.println(out);
	}

	public static void debug_shift(Symbol shift_tkn) {
		debug_message("# Shift sob o termo: #" + shift_tkn.sym
				+ " - Vai para o estado: #" + shift_tkn.parse_state);
	}

	public static void debug_reduce(int prod_num, int nt_num, int rhs_size) {
		debug_message("# Reduce para a produção #" + prod_num);
		// + " [Índice: "+ nt_num + ", " + "Tamanho: " + rhs_size + "]"
	}

	public static String changeTokenNames(String string) {
		String out = string;
		if (string.contains("#" + sym.BOOLEAN)) {
			out = out.replace("#" + sym.BOOLEAN, Constants.BOOLEAN);
		}
		if (string.contains("#" + sym.IDENTIFIER)) {
			out = out.replace("#" + sym.IDENTIFIER, Constants.IDENTIFIER);
		}
		if (string.contains("#" + sym.STRING)) {
			out = out.replace("#" + sym.STRING, Constants.STRING);
		}
		if (string.contains("#" + sym.REAL)) {
			out = out.replace("#" + sym.REAL, Constants.REAL);
		}
		if (string.contains("#" + sym.INTEGER)) {
			out = out.replace("#" + sym.INTEGER, Constants.INTEGER);
		}
		if (string.contains("#" + sym.STRINGTYPE)) {
			out = out.replace("#" + sym.STRINGTYPE, Constants.STRINGTYPE);
		}
		if (string.contains("#" + sym.BOOLEANTYPE)) {
			out = out.replace("#" + sym.BOOLEANTYPE, Constants.BOOLEANTYPE);
		}
		if (string.contains("#" + sym.REALTYPE)) {
			out = out.replace("#" + sym.REALTYPE, Constants.REALTYPE);
		}
		if (string.contains("#" + sym.INTEGERTYPE)) {
			out = out.replace("#" + sym.INTEGERTYPE, Constants.INTEGERTYPE);
		}
		if (string.contains("#" + sym.DIVIDE)) {
			out = out.replace("#" + sym.DIVIDE, Constants.DIVIDE);
		}
		if (string.contains("#" + sym.TIMES)) {
			out = out.replace("#" + sym.TIMES, Constants.TIMES);
		}
		if (string.contains("#" + sym.MINUS)) {
			out = out.replace("#" + sym.MINUS, Constants.MINUS);
		}
		if (string.contains("#" + sym.PLUS)) {
			out = out.replace("#" + sym.PLUS, Constants.PLUS);
		}
		if (string.contains("#" + sym.ENDIF)) {
			out = out.replace("#" + sym.ENDIF, Constants.ENDIF);
		}
		if (string.contains("#" + sym.THEN)) {
			out = out.replace("#" + sym.THEN, Constants.THEN);
		}
		if (string.contains("#" + sym.ELSE)) {
			out = out.replace("#" + sym.ELSE, Constants.ELSE);
		}
		if (string.contains("#" + sym.IF)) {
			out = out.replace("#" + sym.IF, Constants.IF);
		}
		if (string.contains("#" + sym.IMPLIES)) {
			out = out.replace("#" + sym.IMPLIES, Constants.IMPLIES);
		}
		if (string.contains("#" + sym.XOR)) {
			out = out.replace("#" + sym.XOR, Constants.XOR);
		}
		if (string.contains("#" + sym.NOT)) {
			out = out.replace("#" + sym.NOT, Constants.NOT);
		}
		if (string.contains("#" + sym.OR)) {
			out = out.replace("#" + sym.OR, Constants.OR);
		}
		if (string.contains("#" + sym.AND)) {
			out = out.replace("#" + sym.AND, Constants.AND);
		}
		if (string.contains("#" + sym.LT)) {
			out = out.replace("#" + sym.LT, Constants.LT);
		}
		if (string.contains("#" + sym.GT)) {
			out = out.replace("#" + sym.GT, Constants.GT);
		}
		if (string.contains("#" + sym.GE)) {
			out = out.replace("#" + sym.GE, Constants.GE);
		}
		if (string.contains("#" + sym.LE)) {
			out = out.replace("#" + sym.LE, Constants.LE);
		}
		if (string.contains("#" + sym.NE)) {
			out = out.replace("#" + sym.NE, Constants.NE);
		}
		if (string.contains("#" + sym.EQ)) {
			out = out.replace("#" + sym.EQ, Constants.EQ);
		}
		if (string.contains("#" + sym.COLLECTIONOPERATION)) {
			out = out.replace("#" + sym.COLLECTIONOPERATION,
					Constants.COLLECTIONOPERATION);
		}
		if (string.contains("#" + sym.BAR)) {
			out = out.replace("#" + sym.BAR, Constants.BAR);
		}
		if (string.contains("#" + sym.RIGHT_BRA)) {
			out = out.replace("#" + sym.RIGHT_BRA, Constants.RIGHT_BRA);
		}
		if (string.contains("#" + sym.RIGHT_BRK)) {
			out = out.replace("#" + sym.RIGHT_BRK, Constants.RIGHT_BRK);
		}
		if (string.contains("#" + sym.RIGHT_PAR)) {
			out = out.replace("#" + sym.RIGHT_PAR, Constants.RIGHT_PAR);
		}
		if (string.contains("#" + sym.LEFT_BRA)) {
			out = out.replace("#" + sym.LEFT_BRA, Constants.LEFT_BRA);
		}
		if (string.contains("#" + sym.LEFT_BRK)) {
			out = out.replace("#" + sym.LEFT_BRK, Constants.LEFT_BRK);
		}
		if (string.contains("#" + sym.LEFT_PAR)) {
			out = out.replace("#" + sym.LEFT_PAR, Constants.LEFT_PAR);
		}
		if (string.contains("#" + sym.PATHNAME)) {
			out = out.replace("#" + sym.PATHNAME, Constants.PATHNAME);
		}
		if (string.contains("#" + sym.COLON)) {
			out = out.replace("#" + sym.COLON, Constants.COLON);
		}
		if (string.contains("#" + sym.COMMA)) {
			out = out.replace("#" + sym.COMMA, Constants.COMMA);
		}
		if (string.contains("#" + sym.DOT)) {
			out = out.replace("#" + sym.DOT, Constants.DOT);
		}
		if (string.contains("#" + sym.SELF)) {
			out = out.replace("#" + sym.SELF, Constants.SELF);
		}
		if (string.contains("#" + sym.COLLECTION)) {
			out = out.replace("#" + sym.COLLECTION, Constants.COLLECTION);
		}
		if (string.contains("#" + sym.BODY)) {
			out = out.replace("#" + sym.BODY, Constants.BODY);
		}
		if (string.contains("#" + sym.CONTEXT)) {
			out = out.replace("#" + sym.CONTEXT, Constants.CONTEXT);
		}
		if (string.contains("#" + sym.PACKAGE)) {
			out = out.replace("#" + sym.PACKAGE, Constants.PACKAGE);
		}
		if (string.contains("#" + sym.ENDPACKAGE)) {
			out = out.replace("#" + sym.ENDPACKAGE, Constants.ENDPACKAGE);
		}
		if (string.contains("#" + sym.error)) {
			out = out.replace("#" + sym.error, Constants.error);
		}
		return out;
	}
}
