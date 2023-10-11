package redVendedores.controllers;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import redVendedores.aplication.Aplicacion;
import redVendedores.exceptions.VendedorException;
import redVendedores.model.Cliente;

public class NuevoVendedorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDocumento;
    
    @FXML
    private TextField txtEmail;
      
    @FXML
    private TextField txtUsuario;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private TextField txtContrasenia;

    private Aplicacion aplicacion;

    @FXML
    private ImageView flechaRegresar;
    
    @FXML
    private Button btnSubirFoto;

    @FXML
    private ImageView foto;

    
    ModelFactoryController modelFactoryController; 
    
    private Image imagenSeleccionada;

    @FXML
    void registrarseEvent(ActionEvent event) {
    	registrarseAction();
    }
    
    @FXML
    void mouseEvent(MouseEvent event) {
    	btnRegistrarse.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent ->{
    		btnRegistrarse.setStyle("-fx-background-color: #999999;");
        	});
    }

    @FXML
    void mouseEvent2(MouseEvent event) {
    	
    	btnRegistrarse.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent ->{
    		btnRegistrarse.setStyle("-fx-background-color #0676b7;");
    	});
    }
    
    @FXML
    void subirFotoEvent(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.png", "*.gif", "*.bmp"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            imagenSeleccionada = new Image(selectedFile.toURI().toString());
            foto.setImage(imagenSeleccionada);
        }

    }

    private void registrarseAction() {
    	String nombre = txtNombre.getText();
    	String apellido = txtApellido.getText();
    	String documento = txtDocumento.getText();
    	String direccion = txtDireccion.getText();
    	String email = txtEmail.getText();
    	String usuario = txtUsuario.getText();
    	String contrasenia= txtContrasenia.getText();
    	
    	
    	if(datosValidos(nombre, apellido, documento, direccion, contrasenia, email, usuario) == true){
    		if(verificarUsuario(usuario)){
    			if(verificarContraseña(contrasenia)){

    	    		Cliente cliente = null;
    	    		try {
    					cliente=modelFactoryController.crearCliente(nombre, apellido, documento, direccion, contrasenia, email, usuario, imagenSeleccionada);
    					mostrarMensaje("Notificaciï¿½n registro", "Vendedor registrado", "Se ha registrado con exito en Shopify", AlertType.INFORMATION);
    				 	aplicacion.mostrarVentanaLoginVendedor();
    				} catch (VendedorException e) {

    					mostrarMensaje("Notificaciï¿½n registro", "Vendedor no registrado", "Ya se ha registrado un vendedor con nï¿½ identificacion"+documento+".", AlertType.ERROR);
    				}
    			}
    		}
    	}
    		else{
    			mostrarMensaje("Notificaciï¿½n registro", "Informacion invalida", "Informacion invalida", AlertType.ERROR);
    		}
    	}
    
    private boolean verificarUsuario(String usuario) {
		// Define una expresión regular para verificar el nombre de usuario
        String regex = "^[a-zA-Z]+$";

        // Compila la expresión regular en un patrón
        Pattern pattern = Pattern.compile(regex);

        // Crea un matcher para el nombre de usuario
        Matcher matcher = pattern.matcher(usuario);

        // Comprueba si el nombre de usuario cumple con la expresión regular
        return matcher.matches();
	}
	
    private boolean verificarContraseña(String contraseña) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!.,?¡¿-]).{8}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(contraseña);

        return matcher.matches();
    }
    
    
		
	private boolean datosValidos(String nombre, String apellido, String documento, String direccion,
			String contrasenia, String email, String usuario) {
		if(nombre.equals("")){
			return false;
		}
		if(apellido.equals("")){
			return false;
		}
		if(documento.equals("")){
			return false;
		}
		if(direccion.equals("")){
			return false;
		}
		if(contrasenia.equals("")){
			return false;
		}
		if(email.equals("")){
			return false;
		}
		if(usuario.equals("")){
			return false;
		}
		
		return true;
		
	}

	@FXML
    void flechaRegresarEvent(MouseEvent event) {
    	flechaRegresar.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaIniciarRed();
    	});


    }

    @FXML
    void initialize() {

    	modelFactoryController = ModelFactoryController.getInstance();
    }

    public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}
    
    private boolean mostrarMensajeConfirmacion(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmaciï¿½n");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);

		Optional<ButtonType> action = alert.showAndWait();

		if(action.get() == ButtonType.OK){
			return true;
		}else{
			return false;
		}
	}

	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

    	Alert alert = new Alert(alertType);
    	alert.setTitle(titulo);
    	alert.setHeaderText(header);
    	alert.setContentText(contenido);
    	alert.showAndWait();
	}
}