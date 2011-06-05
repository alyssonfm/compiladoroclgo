package cvslogic;

public class Main {
	public static void main(String[] args) {
		XMIParser parser = new XMIParser();
		parser.loadModel("Modelos/profe.xml");
		parser.setPackage("PacoteSistema");
		System.out.println(parser.existsPackage());
		System.out.println(parser.getError());
		// parser.setContext("Torada::getTorada(torada:Cheque):Torada");
		// parser.setContext("Conta::debitar(pts:Integer):Boolean");
		// parser.setContext("Cheque::programa():ProgramaFidelidade");
		parser.setContext("Cheque::programa():ProgramaFidelidade");
		System.out.println(parser.existsContext());
		System.out.println(parser.getError());
		System.out.println("oi " + parser.getAttributeType("servico"));
		// System.out
		// .println(parser
		// .getOperationType("getTorada"));
		// parser.getOperationType("getMulta");
		// System.out
		// .println(parser
		// .getParametersType("PacoteDaTorada::Torada::getTorada(torada : PacoteSistema::Cheque)"));
		// System.out.println(parser.getSuperType("Transacao", "Cheque"));
	}
}
