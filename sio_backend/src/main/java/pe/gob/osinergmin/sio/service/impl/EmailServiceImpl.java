package pe.gob.osinergmin.sio.service.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import pe.gob.osinergmin.sio.service.EmailService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{

	@Value("${sio.email.host}")
	private String host;

	@Value("${sio.email.user}")
	private String user;

	@Value("${sio.email.clave}")
	private String clave;

	@Value("${sio.email.auth}")
	private String auth;

	@Value("${sio.email.port}")
	private String port;
	
	@Autowired
	private SpringTemplateEngine templateEngine;

	@Override
	public void enviarEmail(String emailTo, String subject, String messageHTML) {

		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", user);
		props.put("mail.smtp.clave", clave);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			Context context = new Context();
			String htmlContent = templateEngine.process(messageHTML, context);

			message.setFrom(new InternetAddress(user));
			message.addRecipients(Message.RecipientType.TO, emailTo);
			message.setSubject(subject);
			message.setText(htmlContent, "utf-8", "html");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, user, clave);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace();
		}

	}
}
