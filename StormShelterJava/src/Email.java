import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	protected static String USERNAME;
	protected static String PASSWORD;
	protected String address;
	
	public Email(String address) {
		this.address = address;
	}
	
	public boolean message(String subject, String message) {
		
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		//Creating the authenticator
		Authenticator authenticator = new Authenticator() { 
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		};
		Session session = Session.getInstance(props, authenticator);

		//Attempting to send message
		try {
			//Creating message
			javax.mail.Message email = new MimeMessage(session);
			//Sending email to carrier's Email-SMS service
			email.setRecipients(javax.mail.Message.RecipientType.TO,
					InternetAddress.parse(address));
			email.setSubject(subject);
			email.setText(message);
			//Sending message
			Transport.send(email);
			return true;
		} catch(MessagingException error) {
			System.out.print("A messaging error occured.\n");
			//If the user failed to enter a valid GMail username or password...
			if(USERNAME == null || PASSWORD == null)
				System.out.println("Unspecified username or password. Set GMail proxy with Email.setGMailCredentials(username, password).");
			error.printStackTrace();
			return false;
		}

	}
	
	/**
	 * Static method that sets the GMail proxy
	 * @param username -- The user name of the GMail account to be used
	 * @param password -- The password of the GMail account to be used
	 */
	public static void setGMailCredentials(String username, String password) {
		Email.USERNAME = username;
		Email.PASSWORD = password;
	}

}
