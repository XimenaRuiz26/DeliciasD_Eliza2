package redVendedores.controllers;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import redVendedores.aplication.Aplicacion;

public class RestablecerContrasenaAdminController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRestablecer;

    @FXML
    private Label regresar;

    @FXML
    private TextField txtDocumento;
    
    ModelFactoryController modelFactoryController; 
    
    String txtCodigoVerificacion= "";

    @FXML
    void restablecerContrasenaEvent(ActionEvent event) {
    	restablecerAction();
    }

    private void restablecerAction() {

    	String correo = "";

    	correo = txtDocumento.getText();

    	if(datosValidos(correo)){

    		boolean correoValido = modelFactoryController.verificarCorreoAdmin(correo);
    		if(correoValido){
    			enviarCorreo(correo);
    			aplicacion.mostrarVentanaRestablecerContrasena2Admin(correo, txtCodigoVerificacion);
    		}else{
    			mostrarMensaje("Notificaci�n Inicio sesion", "Usuario no existe", "Los datos ingresados no corresponde a un usuario valido", AlertType.INFORMATION);

    		}
    	}else{
			mostrarMensaje("Notificaci�n Inicio sesion", "Datos Incompletos", "Debe ingresar los datos correctamente, despues de 3 intentos se bloqueara el usuario", AlertType.WARNING);

    	}

	}
    

    private String cadenaAleatoria() {
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String cadenaAux= "";
        for (int x = 0; x < 8; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadenaAux+=caracterAleatorio;
            txtCodigoVerificacion= cadenaAux;
        }
        return txtCodigoVerificacion;

    }
	
	private int numeroAleatorioEnRango(int minimo, int maximo) {
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }
	
	private void enviarCorreo(String destinatario) {
		
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
           mensaje.setSubject("Solicitud para restablecer contrase�a de Delicias�D'�Eliza");
           mensaje.setText("No te preocupes, a todos se nos olvida algo alguna vez" +"\n"+ "\n"+ "Hola, " + txtDocumento.getText()+ "\n" + "\n" + "Hemos recibido tu solicitud para restablecer la contrase�a de tu cuenta asociada a Delicias D' Eliza." + "\n" +"\n"+ "Tu c�digo de un solo uso para restablecer la contrase�a  es: " + txtCodigoVerificacion + "\n" + "Si no solicitaste este c�digo, puedes hacer caso omiso a este mensaje de correo electr�nico. Otra persona puede haber escrito tu direcci�n de correo electr�nico por error." + "\n" + "Gracias");

           // Enviar el mensaje
           Transport.send(mensaje);

           System.out.println("Correo electr�nico enviado con �xito.");
       } catch (MessagingException e) {
           e.printStackTrace();
           System.err.println("Error al enviar el correo electr�nico: " + e.getMessage());
       }
   }


    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

  		Alert alert = new Alert(alertType);
  		alert.setTitle(titulo);
  		alert.setHeaderText(header);
  		alert.setContentText(contenido);
  		alert.showAndWait();
  	}

  	private boolean datosValidos(String usuario) {
  		if(usuario.equals("")){
  			return false;
  		}
  		return true;
  	}


    @FXML
    void regresarEvent(MouseEvent event) {
    	regresar.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaLoginAdmin();
    	});
    }


	private Aplicacion aplicacion;

	@FXML
    void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
		cadenaAleatoria();

    }

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}

}
