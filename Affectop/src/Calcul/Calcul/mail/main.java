package Calcul.Calcul.mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class main {

	public static void main(String[] args) {
		/*
		 * // Recipient's email ID needs to be mentioned. String to =
		 * "valentin.jabre@etu.univ-amu.fr";
		 * 
		 * // Sender's email ID needs to be mentioned String from =
		 * "noreply.Affectop@etu.univ-amu.fr";
		 * 
		 * String host = "smtp.univ-amu.fr";
		 * 
		 * String port = "587"; final String password = "Viridian123!"; final
		 * String username = "j14003448"; // Get system properties Properties
		 * properties = System.getProperties();
		 * 
		 * // Setup mail server properties.put("mail.smtp.host", host);
		 * 
		 * // properties.setProperty("mail.smtp.host", "smtp-mail.outlook.com");
		 * properties.put("mail.smtp.port", port); //
		 * properties.put("mail.smtp.user", from); //
		 * properties.put("mail.smtp.proxy.user", username); //
		 * properties.put("mail.smtp.proxy.password", password);
		 * 
		 * properties.put("mail.smtp.starttls.enable", "true");
		 * properties.put("mail.smtp.auth", "true");
		 * 
		 * 
		 * try {
		 * 
		 * Session session = Session.getDefaultInstance(properties); Transport
		 * tr = session.getTransport("smtp");
		 * 
		 * tr.connect(host, username, password);
		 * 
		 * MimeMessage message = new MimeMessage(session);
		 * 
		 * 
		 * message.setFrom(new InternetAddress(from));
		 * 
		 * 
		 * message.addRecipient(Message.RecipientType.TO, new
		 * InternetAddress(to));
		 * 
		 * // Set Subject: header field message.setSubject("Message de test");
		 * 
		 * // Now set the actual message
		 * message.setText("Message de test à ignorer"); message.saveChanges();
		 * tr.sendMessage(message, message.getAllRecipients()); tr.close();
		 * System.out.println("Sent message successfully...."); } catch
		 * (MessagingException mex) { mex.printStackTrace(); }
		 */

		Address[] to = new Address[5];
		try {
			to[0] = new InternetAddress("valenftin.jabre@gmail.com");

			to[1] = new InternetAddress("valentin.jabre@etu.univ-amu.fr");

			to[2] = new InternetAddress("valentin.jabre@gmil.com");
			to[3] = new InternetAddress("valentinjkcjsdhcdsc@gmail.com");
			to[4] = new InternetAddress("mathieu.vallet.1@etu.univ-amu.fr");
		} catch (AddressException e) {
		
			e.printStackTrace();
		}
		MailManager m = new MailManager("", "contenu", "noreply.Affectop@etu.univ-amu.fr", to);
		m.sendMail();
		

	}
}
