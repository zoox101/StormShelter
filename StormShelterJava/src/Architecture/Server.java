package Architecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.angryelectron.fona.Fona;
import com.angryelectron.fona.FonaException;
import com.angryelectron.fona.FonaSmsMessage;


//The StormShelter server class
public class Server {

	//Initializing server variables
	private static HashMap<String, User> users = new HashMap<String, User>();
	private static HashSet<String> admins = new HashSet<String>();
	private static ServerSMSHandler handler = new ServerSMSHandler();
	private static FonaWrapper fona = new FonaWrapper();
	
	//Shuts down the server
	private static boolean exit = false;

	//Runs the server
	public static void main(String[] args) throws FonaException {
		
		//TODO: Add method to read in admins and users
		//Hard coding admins and users
		admins.add("+15802241789");
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		contacts.add(new Contact("15802241789"));
		users.put("+15802241789", new User("+15802241789", "Zack Herman", contacts));

		//Initializing the fona and printing current status
		fona.open("COM4", 115200, handler);
		printStatus(fona);

		//Run the server until it is told to exit
		while(!exit) {
			//Handle all incoming messages
			while(!handler.queue.isEmpty()) {handleMessage(handler.queue.remove());}
			//Check to see if any users are in trouble
			ArrayList<User> emergency = checkAllUsers();
			//Alert their emergency contacts if they are
			alertContacts(emergency);
		}

		//Shuts down the server
		shutDown(fona);
	}



	//Alerts the user's emergency contacts
	private static void alertContacts(ArrayList<User> emergency) throws FonaException {

		//For every user in trouble...
		for(User user: emergency) {

			//If the user is panicked, alert their contacts
			if(user.checkPanicked() && !user.alertedpanic) {
				String message = user.name + " has hit the panic button in the storm shelter.";
				for(Contact contact: user.contacts) {
					fona.smsSend(contact.phonenumber, message);}
				user.alertedpanic = true;
			}
			
			//If the user has been in their shelter too long, alert their contacts
			else if(user.checkShelterTimeout() && !user.alertedtimeout) {
				double doubletimeinshelter = ((double)user.timeInShelter() / (60 * 1000));
				String minutesinshelter = Double.toString(((double) Math.round(doubletimeinshelter * 100)/100));
				String message = user.name + " has been in their shelter for over " + minutesinshelter + " minutes.";
				for(Contact contact: user.contacts) {
					fona.smsSend(contact.phonenumber, message);}
				user.alertedtimeout = true;
			}
			
			//Should never hit this section
			else {System.out.println("An error occured when alerting " + user + "'s emergency contacts");}
		}
	}



	//Gets the list of users that are in trouble
	private static ArrayList<User> checkAllUsers() {

		ArrayList<User> emergency = new ArrayList<User>();

		//For each user...
		for(String key: users.keySet()) {

			//Get the current user
			User user = users.get(key);

			//If they've panicked or timed out and we haven't handled it, handle it
			boolean panicked = user.checkPanicked();
			boolean timeout = user.checkShelterTimeout();
			if((panicked && !user.alertedpanic) || (timeout && !user.alertedtimeout)) {
				emergency.add(user);}
		}
		
		//Returns the list of users in trouble
		return emergency;
	}



	//Handles a single message sent to the server
	private static void handleMessage(FonaSmsMessage item) {
		
		//Run the operation requested
		String id = item.sender;
		String message = item.message;
		
		//If the user is requesting an operation, do it
		if(users.containsKey(id) && Operation.valueOf(message) != null) {
			User user = users.get(id);
			user.runOperation(Operation.valueOf(message));
		}
		
		//If an admin is shutting down the server, close the server
		else if(admins.contains(id) && message.toUpperCase().equals("SHUTDOWN")) {
			Server.exit = true;
		}
		
		//Should never hit this section
		else {
			System.out.println("An error occured when processing the user input.");
			System.out.println(id + "\t" + message);
		}
	}



	//Prints the current status of the fona
	private static void printStatus(Fona fona) throws FonaException {
		System.out.println("Battery Percentage: " + fona.batteryPercent());
		System.out.println("Cellular Signal: " + fona.simRSSI());
	}



	//Shuts down the fona
	private static void shutDown(Fona fona) throws FonaException {
		fona.close();
	}
}
