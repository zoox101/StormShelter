import java.io.BufferedReader;
import java.io.IOException;

public class EmergancyContact {
	
	protected String phonenumber;
	protected SMS.Carrier carrier;
	
	public EmergancyContact(BufferedReader br) throws IOException {
		
		System.out.print("Please enter a ten digit phone number for your emergancy contact: ");
		phonenumber = br.readLine();
		System.out.println("Please select the carrier for " + SMS.numberToString(phonenumber));
		System.out.println("1: AT&T\n2: TMobile\n3: Sprint\n4: Verizon");
		int selection = Integer.parseInt(br.readLine());
		
		switch(selection) {
		case 1: carrier = SMS.Carrier.ATT; break;
		case 2: carrier = SMS.Carrier.TMOBILE; break;
		case 3: carrier = SMS.Carrier.SPRINT; break;
		case 4: carrier = SMS.Carrier.VERIZON; break;
		}
		
	}
	
	public String toString() {
		return SMS.numberToString(phonenumber) + " at " + carrier;
	}

}
