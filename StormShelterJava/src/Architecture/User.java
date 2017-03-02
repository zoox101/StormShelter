package Architecture;
import java.util.ArrayList;
import java.util.Date;

public class User {
	
	private static int ID = 0;
	
	private final int PANIC_TIME = 10 * 1000;
	
	//User variables
	public final String name; 
	public final int id;
	public final ArrayList<Contact> contacts;
	
	//Class variables
	private long entertime;
	public boolean panicked; 
	
	//Creates a new user object
	public User(String name, ArrayList<Contact> contacts) {
		this.name = name;
		this.id = ID++;
		this.contacts = contacts;
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
			this.panicked = true;}
		return panicked;
	}
	
}