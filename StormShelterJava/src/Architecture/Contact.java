package Architecture;
import java.util.Date;
import com.angryelectron.fona.Fona;
import com.angryelectron.fona.FonaException;

public class Contact {
	
	private final static int MESSAGE_WAIT = 2000; 
	
	//The local fona and the phone number to contact
	private final Fona fona; 
	private final String phonenumber; 
	
	//Creates a new contact from the phone number
	public Contact(Fona fona, String phonenumber) {
		this.fona = fona;
		this.phonenumber = phonenumber;
	}
	
	//Sends a message to the given contact
	public boolean sendMessage(String string) {
		
		long endtime = new Date().getTime() + MESSAGE_WAIT;
		
		System.out.println(phonenumber + ": " + string);
		//try {fona.smsSend(phonenumber, string);}
		//catch (FonaException e) {return false;}

		while(new Date().getTime() < endtime);
		return true;

	}

}
