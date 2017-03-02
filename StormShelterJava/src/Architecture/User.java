package Architecture;
import java.util.ArrayList;
import java.util.Date;

public class User {
	
	private final static int PANIC_TIME = 10 * 1000; 
	
	//User variables
	private String name; 
	private ArrayList<Contact> contacts;
	
	//Class variables
	private long entertime;
	private boolean panicked; 
	
	//Creates a new user object
	public User(String name, ArrayList<Contact> contacts) {
		this.name = name;
		this.entertime = Long.MAX_VALUE;
		this.panicked = false; 
	}
	
	//The user has entered their shelter
	public void shelterEnter() {
		this.entertime = new Date().getTime();
		this.panicked = false;
	}
	
	//The user has left their shelter
	public void shelterExit() {
		this.entertime = Long.MAX_VALUE;
		this.panicked = false; 
	}
	
	//Check to make sure the user is ok
	public boolean check() {
		long currenttime = new Date().getTime();
		if(currenttime-entertime > PANIC_TIME && !panicked) {
			this.panicked = true; 
			double minutes = PANIC_TIME / (60 * 1000);
			pingAllContacts(name + " has been in their shelter for over " + minutes + " minutes.");
		}
		return panicked;
	}

	//Sends a message to all the user's emergency contacts
	private void pingAllContacts(String string) {
		for(Contact contact: contacts) {
			contact.sendMessage(string);}
	}
	
}