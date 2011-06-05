package util;
import java.util.ArrayList;


public class Logger {
	
	private ArrayList<String> messages;
	private ArrayList<String> errors;
	
	public Logger() {
		super();
		messages = new ArrayList<String>();
		errors = new ArrayList<String>();
	}
	
	public void addMessage(String message){
		messages.add(message);
	}
	
	public void addError(String error) {
		errors.add(error);
	}
	
	public String getError(){
		String out = "";
		if(errors.size() > 0){
			for(String s : errors){
				out += s + "\n";
			}
			out += "Ocorreram " + errors.size() + " erros." + "\n";
		}else{
			out += "Sem erros." + "\n";
		}
		return out;
	}
	
	public boolean hasError(){
		return errors.size() > 0 ? true:false;
	}
	
	public String getMessages(){
		String out = "";
		if(messages.size() > 0){
			for(String s : messages){
				out += s + "\n";
			}
		}else{
			out += "Sem log." + "\n";
		}
		return out;
	}
	
	@Override
	public String toString() {
		if(errors.size() > 0){
			return getError();
		}
		String out = getMessages();
		out += "Sem erros.\n";
		return out;
	}
	

}
