package Fona;
import com.angryelectron.fona.Fona;
import com.angryelectron.fona.FonaException;
import com.angryelectron.fona.FonaEventHandler;
import com.angryelectron.fona.FonaSmsMessage;
import com.angryelectron.fona.Fona.Network;
import java.util.*;


public class communicationTest2 {
	
	
	
	//Main Method
		public static void main(String[] args) throws FonaException {
					
			
			System.out.println("Starting");
			final StringWrapper messageList = new StringWrapper();			

			
			FonaEventHandler smsHandler = new FonaEventHandler() {
								
				
			    @Override
			    public void onSmsMessageReceived(FonaSmsMessage sms) {
			        /* called when new message arrives */
			    				    				    				        
			        System.out.println("New message received:");
			        System.out.println(sms.sender);
			        System.out.println(sms.message);
			        
			        messageList.str1.add(sms.sender);
			        messageList.str2.add(sms.message);
			        			        
			    }
			    
			    @Override
			    public void onSerialReady()  {    	
			    }
			    
			    @Override
			    public void onNetworkStatusChange(Network status)  {    	
			    }


			    @Override
			    public void onError(String message) {
			        /* called when something goes wrong */
			        System.out.println(message);
			    }
			};

			System.out.println("Opening Serial Connection");
			
			/* connect to serial port and start listening */
			Fona fona = new Fona();
			fona.open("COM4", 115200, smsHandler);
		
			System.out.println("Battery Percentage: " + fona.batteryPercent());
			System.out.println("Cellular Signal: " + fona.simRSSI());
			//System.out.println("Sending Test Messege");
			//fona.smsSend("5802241789", "Test.");
			System.out.println("Sending Ping...");
			
			long i = System.currentTimeMillis();
			long c = i + 300000;
			long c2 = i + 15000;
			int count = 0;
			while(i < c)
			{
				i = System.currentTimeMillis();
				if(c2 == i)
				{
					count++;
					fona.smsSend("5802241789", "Messege #" + count + " ||| Time Left: " + (c-i));
					
					System.out.println(messageList.str1);
					System.out.println(messageList.str2);
					
					count = messageList.str1.size();
					while(count > 0)
					{
						count--;
						fona.smsSend(messageList.str1.get(count), "Message Received");
					}
					messageList.str1.clear();
					messageList.str2.clear();
					
					c2 = i+15000;
				}

			}
			
			fona.close();
			System.out.println();
			System.out.println("Finished");
		}

}

class StringWrapper {
	public ArrayList<String> str1 = new ArrayList();
	public ArrayList<String> str2 = new ArrayList();
	
	StringWrapper(){
	
	}
	
	
	
	
}
