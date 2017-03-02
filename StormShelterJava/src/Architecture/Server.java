package Architecture;

import com.angryelectron.fona.Fona;
import com.angryelectron.fona.FonaEventHandler;
import com.angryelectron.fona.FonaException;

public class Server {

	public static void main(String[] args) throws FonaException {

		FonaEventHandler smshandler = new ServerSMSHandler();
		Fona fona = new FonaWrapper();
		fona.open("COM4", 115200, smshandler);
		


	}

}
