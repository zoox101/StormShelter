package Architecture;
import java.util.Date;

import com.angryelectron.fona.Fona;
import com.angryelectron.fona.FonaException;

public class FonaWrapper extends Fona {
	
	private final static int MESSAGE_WAIT = 2000; 
	
	public void smsSend(String phonenumber, String message) throws FonaException {
		long endtime = new Date().getTime() + MESSAGE_WAIT;
		System.out.println(phonenumber + ": " + message);
		super.smsSend(phonenumber, message);
		while(new Date().getTime() < endtime);
		
	}
	
	
	
	

}
