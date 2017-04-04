package Architecture;
import java.util.ArrayList;
import java.util.Date;

public class User {

	private final int PANIC_TIME = 10 * 1000;

	//User variables
	public final String name; 
	public final String id;
	public final ArrayList<Contact> contacts;

	//Class variables
	private long entertime;
	private boolean panicked;
	public boolean alertedtimeout;
	public boolean alertedpanic; 

	//Creates a new user object
	public User(String id, String name, ArrayList<Contact> contacts) {
		this.id = id; 
		this.name = name;
		this.contacts = contacts;
		this.entertime = Long.MAX_VALUE;
		this.panicked = false; 
	}

	//Runs the requested operation
	public void runOperation(Operation operation) {
		switch(operation) {
		case ENTER_SHELTER: shelterEnter(); break;
		case EXIT_SHELTER:  shelterExit(); break;
		case PANIC: panic(); break;
		case UNPANIC: unpanic(); break;
		default: break;
		}
	}

	//The user has entered their shelter
	private void shelterEnter() {
		this.entertime = new Date().getTime();
		this.alertedtimeout = false;
	}

	//The user has left their shelter
	private void shelterExit() {
		this.entertime = Long.MAX_VALUE;
		this.alertedtimeout = false; 
	}
	
	//The user has hit the panic button
	private void panic() {
		this.alertedpanic = false;
		this.panicked = true;
	}
	
	//The user has canceled the panic button
	private void unpanic() {
		this.alertedpanic = false;
		this.panicked = false; 
	}
	
	//Returns the total amount of time the user has been in the shelter
	public long timeInShelter() {
		return (new Date().getTime() - entertime);
	}

	//Checks to see if the user has been in their shelter for too long
	public boolean checkShelterTimeout() {
		if(timeInShelter() > PANIC_TIME) {return true;}
		return false;
	}
	
	//Checks to see if the user has hit the panic button
	public boolean checkPanicked() {
		return this.panicked; 
	}

}