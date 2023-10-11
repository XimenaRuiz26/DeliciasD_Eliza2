package redVendedores.controllers;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import redVendedores.aplication.Aplicacion;

public class RestablecerContrasena2AdminController {
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Label txtCodigoVerificacion;

	    @FXML
	    private Button btnRestablecer;

	    @FXML
	    private PasswordField txtContraseniaAgain;

	    @FXML
	    private Label ingresa;

	    @FXML
	    private TextField txtCodigoIngresado;

	    @FXML
	    private PasswordField txtNuevaContrasenia;

	    @FXML
	    private Button btnVerificar;

	    private String correo;
	    
	    private String codigoGenerado;

	    private Aplicacion aplicacion;
	    
	    @FXML
	    private Label identificador;
	    
	    ModelFactoryController modelFactoryController; 

	    @FXML
	    void verificarCodigoEvent(ActionEvent event) {
	    	verificarCodigo();
	    }

	    @FXML
	    void restablecerContrasenaFinalEvent(ActionEvent event) {
	    	actualizarContrasenia();
	    }

	    @FXML
	    void ingresaEvent(MouseEvent event) {
	    	ingresa.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		aplicacion.mostrarVentanaLoginAdmin();
	    	});
	    }

	@FXML
	void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
		txtNuevaContrasenia.setEditable(false);
		txtContraseniaAgain.setEditable(false);
 }

	public void setAplicacion(Aplicacion aplicacion, String correo, String codigoGenerado) {
		this.aplicacion = aplicacion;
		this.correo= correo;
		this.codigoGenerado = codigoGenerado;
		identificador.setText(correo);
	}


	private void verificarCodigo(){
		String codigo = codigoGenerado;
		String codigoIngresado = txtCodigoIngresado.getText();

    		if(!(codigoIngresado.equals(""))){
        		if(codigo.equals(codigoIngresado)){
        			mostrarMensaje("Notificaciï¿½n codigo", "Codigo correcto", "El codigo fue ingresado correctamente, restablezca su contraseï¿½a", AlertType.INFORMATION);
        			txtNuevaContrasenia.setEditable(true);
        			txtContraseniaAgain.setEditable(true);
        		}else{
        			mostrarMensaje("Notificaciï¿½n codigo", "Codigo incorrecto", "El codigo ingresado es incorrecto, vuelva a escribirlo", AlertType.ERROR);
        		}
        	}else{
        		mostrarMensaje("Notificaciï¿½n codigo", "Datos Incompletos", "Debe ingresar el codigo para poder restablecer su contraseï¿½a, despues de 3 intentos se bloqueara el usuario", AlertType.WARNING);
        		txtNuevaContrasenia.setEditable(false);
    			txtContraseniaAgain.setEditable(false);
        	}
    }


	private void actualizarContrasenia(){
		String nuevaContrasenia = txtNuevaContrasenia.getText();
		String contraseniaAgain = txtContraseniaAgain.getText();
		String email = correo;

			if(datosValidos(nuevaContrasenia, contraseniaAgain)){
				if(nuevaContrasenia.equals(contraseniaAgain)){
					if(verificarContraseña(nuevaContrasenia)){
						modelFactoryController.actualizarContraseniaAdmin(email, nuevaContrasenia);
						mostrarMensaje("Notificaciï¿½n contraseï¿½a", "Contraseï¿½a correcta", "Su contraseï¿½a fue bien escrita, se ha actualizado correctamente", AlertType.INFORMATION);
						enviarCorreo(email);
						aplicacion.mostrarVentanaLoginAdmin();
					}else{
						mostrarMensaje("Notificaciï¿½n contrasenia", "contrasenia invalida", "La contraseña debe tener una mayuscula, un número, una minuscula y un caracter. No sobrepasarse de 8 letras", AlertType.ERROR);
					}
				}else{
					mostrarMensaje("Notificaciï¿½n contraseï¿½a", "Contraseï¿½a incorrecto", "Las contraseï¿½as no son iguales, vuelva a escribirlas", AlertType.ERROR);
				}
			}else{
				mostrarMensaje("Notificaciï¿½n contraseï¿½a", "Datos Incompletos", "Debe ingresar la nueva contraseï¿½a para poder restablecerla, despues de 3 intentos se bloqueara el usuario", AlertType.WARNING);
			}
	}
	
	public static boolean verificarContraseña(String contraseña) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!.,?¡¿-]).{8}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(contraseña);

        return matcher.matches();
    }
	
	private void enviarCorreo(String destinatario) {
		//String nombre= modelFactoryController.traerNombre(correo);
		 Properties properties = new Properties();
	        properties.put("mail.smtp.host", "smtp.gmail.com"); // Cambia esto al servidor SMTP que desees utilizar
	        properties.put("mail.smtp.port", "587"); // Cambia esto al puerto SMTP adecuado
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true"); // Habilita STARTTLS para la seguridad

       Session session = Session.getInstance(properties, new Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication("deliciasd.elizaseguridad@gmail.com", "bxlg szks nsqa ntus");
           }
       });

       try {
           // Crear un objeto de mensaje
           Message mensaje = new MimeMessage(session);

           // Configurar el remitente y los destinatarios
           mensaje.setFrom(new InternetAddress(destinatario));
           mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
           mensaje.setSubject("Contraseña restablecida - Delicias D' Eliza");
           mensaje.setText("No te preocupes, a todos se nos olvida algo alguna vez" +"\n"+ "\n"+ "Hola, " + correo+ "\n" + "\n" + "Hemos recibido tu solicitud para restablecer la contraseña de tu cuenta asociada a Delicias D' Eliza." + "\n" +"\n"+ "Tu código de un solo uso para restablecer la contraseña  es: " + txtCodigoVerificacion + "\n" + "Si no solicitaste este código, puedes hacer caso omiso a este mensaje de correo electrónico. Otra persona puede haber escrito tu dirección de correo electrónico por error." + "\n" + "Gracias");

           // Enviar el mensaje
           Transport.send(mensaje);

           System.out.println("Correo electrónico enviado con éxito.");
       } catch (MessagingException e) {
           e.printStackTrace();
           System.err.println("Error al enviar el correo electrónico: " + e.getMessage());
       }
   }




	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

	private boolean datosValidos(String codigoGenerado, String codigoIngresado) {
		if(codigoGenerado.equals("") || codigoIngresado.equals("")){
			return false;
		}
		return true;
	}







}
