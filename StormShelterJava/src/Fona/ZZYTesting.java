package Fona;
import com.angryelectron.fona.Fona;
import com.angryelectron.fona.FonaException;

public class ZZYTesting {
	
	public static void main(String[] args) throws FonaException {
		System.out.println("Starting");
		
		Fona fona = new Fona();
		fona.open("myport", 115200);
		
		fona.batteryPercent();
		
		
		System.out.println("Finished");
	}

}
