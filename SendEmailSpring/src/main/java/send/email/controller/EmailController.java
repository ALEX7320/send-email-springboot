package send.email.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import send.email.service.EmailService;
import send.email.template.EmailTemplate;

@CrossOrigin("*")
@RestController
@RequestMapping("user")
public class EmailController {

	
	@Autowired
	private EmailService emailServicio;
	

	// [POST] : CAMBIAR CONTRASEÑA Y ENVIAR CORREO
	@PostMapping("/forgot")
	public ResponseEntity<?> crear(@RequestBody EmailTemplate template){
               
		// GENERAR CONTRASEÑA
		Integer num1 = (int) (Math.random()*99+1);
		Integer num2 = (int) (Math.random()*99+1);
		Integer num3 = (int) (Math.random()*99+1);
		String newPassword = "password."+String.format("%s%s%s",num1,num2,num3);

		try {
			
			// ENVIO DE CORREO
			emailServicio.enviar(template.getTemplateUsername(), template.getTemplateEmail(), newPassword);
			System.out.println("[Correo enviado]");		
			
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		Map<String,String> respuesta = new HashMap<>();
		respuesta.put("codigo","OK");
		respuesta.put("mensaje","Email envíado correctamente");
		return new ResponseEntity<>(respuesta,HttpStatus.OK);
	}
	
	
}
