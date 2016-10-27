import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AAADriver {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static boolean exit = false;
	static Customer user;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Starting");
		String username = User.getString("Username: ");
		String password = User.getString("Password: ");
		SMS.setGMailCredentials(username, password);
		user = new Customer(br);
		while(!exit) {analyze(br.readLine());}
		System.out.println("Exiting");
	}

	private static void analyze(String readLine) throws IOException {
		switch(readLine) {
		case "exit": exit = true; break;
		case "pingenter": pingshelter(); break;
		case "pingexit": pingexit(); break;
		case "newuser": user = new Customer(br); break;
		case "help": System.out.println("exit\tpingenter\tpingexit\tnewuser"); break;
		default: System.out.println("Command not found"); break;
		}
	}
	
	private static void pingshelter() {
		for(EmergancyContact contact: user.contacts) {
			String message = user + " has taken shelter.";
			System.out.println("Messaging " + contact + " that " + message);
			SMS phone = new SMS(contact.phonenumber, contact.carrier);
			phone.message(message);
		}
	}
	
	private static void pingexit() {
		for(EmergancyContact contact: user.contacts) {
			String message = user + " has exited their shelter.";
			System.out.println("Messaging " + contact + " that " + message);
			SMS phone = new SMS(contact.phonenumber, contact.carrier);
			phone.message(message);
		}
	}
}
