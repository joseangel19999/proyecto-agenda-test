package prueba.com.pruebaAgenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import prueba.com.pruebaAgenda.dto.NotificationRequestDto;

@Service
public class NotificacionCorreoImpl implements INotificacion{

	@Autowired
    private JavaMailSender mailSender;

    @Value("${app.spring.mail.sender}")
    private String fromEmail;
    
	@Override
	public void enviar(NotificationRequestDto notificacion) {
		 SimpleMailMessage message = new SimpleMailMessage();
	        
	        message.setFrom(fromEmail);
	        message.setTo(notificacion.getDestinatario());
	        message.setSubject(notificacion.getAsunto());
	        message.setText(notificacion.getCuerpo());
	        mailSender.send(message);
	        System.out.println("Correo enviado exitosamente a " +notificacion.getDestinatario());
		
	}

}
