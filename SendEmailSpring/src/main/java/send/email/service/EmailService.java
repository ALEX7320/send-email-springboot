package send.email.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String supportMail;
	
	@Value("${support.name}")
	private String supportName;
	
	public void enviar(String username, String email, String newpassword) throws MessagingException {
			
		String contenido = this.contenidoHtml(username, email, newpassword);
		
		MimeMessage mail = mailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mail);
		message.setSubject("Cambio de contraseña 🔓");
		message.setText(contenido,true); // contenido: text , html: true
		message.setFrom(supportName+" <"+supportMail+">");
		message.setTo(email);
		mailSender.send(mail);
		System.out.println("[Correo enviado]+"+supportMail);		

		
		
	}
		
	public String generarFecha() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localDateTime = LocalDateTime.now();
		String formattedDateTime = localDateTime.format(formatter);
		// https://stackoverflow.com/questions/22463062/how-can-i-parse-format-dates-with-localdatetime-java-8
		
		return formattedDateTime; // 2023-11-16 20:06
	}
	
	
	public String contenidoHtml(String username, String email, String newpassword) {
		
		String imagen = "https://i.postimg.cc/Gt1rWjyY/imgemail.jpg";
	
		String contenido = String.format(""+
			"<div style='background-color: white'>"+
			    "<div style='padding: 30px;display: flex;align-items: center;'>"+
			        "<div>"+
			            "<div style='max-width: 600px; background-color: white;border: 1px solid rgb(211, 211, 211);border-radius: 7px; padding-bottom: 20px;'>"+
			                "<center>"+
			                    "<img src='%s' style='width: 350px; margin-top: 15px;' alt='logo'>"+
			                "</center>"+
			                "<center  style='margin-left: 3rem;margin-right: 3rem;font-size: 18px;'>"+
			                    "Se ha realizado el cambio de contraseña el día %s, de su cuenta <b>%s</b> (%s)"+
			                "</center>"+
			                "<div style='margin-left: 3rem; margin-right: 3rem;margin-top: 40px; margin-bottom: 17px;'>"+
			                    "<div class='input-group'>"+
			                        "<b>Nueva contraseña </b>"+
			                        "<span style='border: 1px solid gray; border-radius: 5px; padding: 5px;'>%s</span>"+
			                    "</div>"+
			                "</div>"+
			            "</div>"+
			        "</div>"+
			    "</div>"+
			"</div>",imagen, this.generarFecha(),username, email, newpassword);
		
		return contenido;
	}


}
