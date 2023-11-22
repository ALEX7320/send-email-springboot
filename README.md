# Enviar correo con SpringBoot (Gmail)

En este repositorio te mostraremos un pequeños ejemplo para enviar correo con SpringBoot, hemos creado una plantilla de "Recuperar contraseña" (en HTML).

* [Conectar cuenta Google](#conectar-cuenta-google)
    - [Conexión en Spring](#conexión-en-spring)
    - [Obtener contraseña de App Google](#obtener-contraseña-de-app-google)
* [Dependencia: SpringBoot](#dependencia-springboot)
* [Plantilla HTML](#plantilla-html)
* [Uso en Postman](#uso-en-postman)
* [Previsualización](#previsualización)
* [Enlaces de apoyo](#enlaces-de-apoyo)
* [Consideración](#previsualización)



# Entorno de desarrollo

| Herramienta | Versión |
| ------------ | ------------ | 
|  Spring Tool Suite 4  | 4.12.0.RELEASE |
| Java  | 16 |
| Postman  | 10.20.0 |


# Conectar cuenta Google

### Conexión en Spring
```
# CONFIGURACION DE CONEXION
...
support.name=SendEmailSpring TEST
spring.mail.username=alex7320f@gmail.com
spring.mail.password=cdkfpsiumctfuavp
```
* **support.name** : Nombre de pila que se mostrará en el correo.
* **spring.mail.username** : Correo Gmail del remitente.
* **spring.mail.password** : Contraseña de aplicación Google (No es la contraseña de la cuenta Google)

### Obtener contraseña de App Google

[Ver la guía aquí.](/Documento/Configurar%20contraseña%20de%20app%20Google.pdf)

La contraseña que generemos debemos colocarlo en `spring.mail.password`


# Dependencia: SpringBoot 

Se ha agregado al pom.xml la dependencia **spring-boot-starter-mail** `v2.7.17`

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-mail</artifactId>
</dependency>		
```

# Plantilla HTML

Podemos modificar la plantilla en la función `contenidoHtml` (Clase: EmailService)

```java
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
```

# Uso en Postman

Utilizar la siguiente uri con el metodo POST.

```
http://localhost:8080/user/forgot
```

Estos son los datos para el receptor del correo. Debemos utilizar la siguiente estructura en JSON, configurado en Spring en el paquete `send.email.template` , clase `EmailTemplate` **(Por favor no utilizar el siguiente correo, solo es de ejemplo)**

```json
{
    "templateEmail":"alex7320k@gmail.com",
    "templateUsername":"ALEX7320"
}
```

![](https://i.postimg.cc/3Nv59fJm/postman.png)

# Previsualización

![](https://i.postimg.cc/s2Lsfrtp/Correoej.png)


# Enlaces de apoyo

- [Set sender name in JavaMailSender](https://stackoverflow.com/questions/32666985/set-sender-name-in-javamailsender "")
- [Javamail Could not convert socket to TLS GMail](https://stackoverflow.com/questions/16115453/javamail-could-not-convert-socket-to-tls-gmail "")
- [How can I parse/format dates with LocalDateTime? (Java 8)](https://stackoverflow.com/questions/22463062/how-can-i-parse-format-dates-with-localdatetime-java-8 "")

# Consideración

> Si tienes el antivirus Avast deberás desactivarlo, de lo contrario te saldrá error al enviar el correo mediante SpringBoot.