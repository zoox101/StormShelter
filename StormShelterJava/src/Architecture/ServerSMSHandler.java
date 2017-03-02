package Architecture;

import java.util.LinkedList;
import java.util.Queue;

import com.angryelectron.fona.Fona.Network;
import com.angryelectron.fona.FonaEventHandler;
import com.angryelectron.fona.FonaSmsMessage;

public class ServerSMSHandler implements FonaEventHandler {
	
	public Queue<FonaSmsMessage> queue;
	
	public ServerSMSHandler() {
		queue = new LinkedList<FonaSmsMessage>();
	}

	@Override
	public void onError(String error) {
		System.out.println(error);}

	@Override
	public void onNetworkStatusChange(Network arg0) {}

	@Override
	public void onSerialReady() {}

	@Override
	public void onSmsMessageReceived(FonaSmsMessage message) {
		queue.add(message);}

}
