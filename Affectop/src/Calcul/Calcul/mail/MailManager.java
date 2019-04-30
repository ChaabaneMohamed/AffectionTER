package Calcul.Calcul.mail;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailManager {
	final private String subject;
	final private String content;
	final private String from;
	final private Address[] to;

	public MailManager(String subject, String content, String from, Address[] to) {
		this.subject = subject;
		this.content = content;
		this.from = from;
		this.to = to;
	}

	public void sendMail() {

		final String host = "smtp.univ-amu.fr";

		final String port = "587";
		final String password = "";
		final String username = "";
		// Get system properties
		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);

		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");

		// Activation de l'auth
		properties.put("mail.smtp.starttls.enable", "true");

		try {

			Session session = Session.getDefaultInstance(properties);
			Transport tr = session.getTransport("smtp");
			tr.connect(host, username, password);

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.addRecipients(Message.RecipientType.TO, to);

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(content);
			message.saveChanges();
			tr.sendMessage(message, message.getAllRecipients());

			tr.close();
			System.out.println("Messages envoyés ....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}
}
