import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class User {
	
	protected String firstname;
	protected String lastname;
	protected ArrayList<EmergancyContact> contacts;
	
	public User(BufferedReader br) throws IOException {
		
		System.out.print("Please enter the user's first and last name: ");
		String name = br.readLine();
		String[] names = name.split(" ");
		firstname = names[0];
		lastname = names[1];
		
		contacts = new ArrayList<EmergancyContact>();
		contacts.add(new EmergancyContact(br));
		System.out.println("Created user " + this + ". Emergancy contact is " + contacts.get(0) + ".");
	}
	
	public String toString() {
		return firstname + " " + lastname;
	}

}
