package Fona;
import com.angryelectron.fona.Fona;
import com.angryelectron.fona.FonaException;

public class ZZZTesting {
	
	//Main Method
	public static void main(String[] args) throws FonaException {
		System.out.println("Starting");
		Fona fona = new Fona();
		fona.open("COM4", 115200);
		fona.batteryPercent();
		fona.close();
		System.out.println();
		System.out.println("Finished");
	}

}
