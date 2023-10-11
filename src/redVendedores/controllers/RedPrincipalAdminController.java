package redVendedores.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import redVendedores.aplication.Aplicacion;
import redVendedores.exceptions.VendedorException;
import redVendedores.model.Producto;
import redVendedores.model.Cliente;

public class RedPrincipalAdminController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label cerrarSesion;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TableColumn<Cliente, String> columnDocumento;

    @FXML
    private Button btnEliminarVendedor;

    @FXML
    private TableColumn<Cliente, String> columnUsuario;

    @FXML
    private TableColumn<Cliente, String> columnNombre;

    @FXML
    private Button btnCrearVendedor;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtCedula;

    @FXML
    private TableColumn<Cliente, String> columnApellidos;

    @FXML
    private TableColumn<Cliente, String> columnContrasenia;

    @FXML
    private TableColumn<Cliente, String> columnDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TableView<Cliente> tableVendedores;

    @FXML
    private TextField txtContrasenia;

    @FXML
    private Button btnActualizarVendedor;

    @FXML
    private ImageView iconCerrarS;

    @FXML
    private ImageView notificaciones;

    @FXML
    private ImageView usuario;

    private Aplicacion aplicacion;

    @FXML
    private TableView<Producto> tableProductos;

    @FXML
    private TableColumn<Producto, String> columnProducto;

    @FXML
    private TableColumn<Producto, String> columnEstado;

    @FXML
    private TableColumn<Producto, String> columnPrecio;

    @FXML
    private TableColumn<Producto, String> columnCodigo;

    @FXML
    private TableColumn<Producto,String> columnCategoria;

    @FXML
    private TableColumn<Producto, String > columnFechaHora;

    ObservableList<Producto> listaProductosData = FXCollections.observableArrayList();

    Cliente vendedorSeleccionado;

    ObservableList<Cliente> listaVendedoresData = FXCollections.observableArrayList();

    private String documento;
    
    ModelFactoryController modelFactoryController;


    @FXML
    void crearVendedorEvent(ActionEvent event) {
    	crearVendedorAction();
    	limpiarDatos();

    }

    @FXML
    void actualizarVendedorEvent(ActionEvent event) {
    	actualizarVendedorAction();

    }

    @FXML
    void eliminarVendedorEvent(ActionEvent event) throws VendedorException {
    	eliminarVendedorAction();

    }

    @FXML
    void cerrarSesionEvent(MouseEvent event) {
    	cerrarSesion.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaIniciarRed();
    	});


    }

    @FXML
    void cerrarSesion2Event(MouseEvent event) {
    	iconCerrarS.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaIniciarRed();
    	});

    }

    @FXML
    void notificacionEvent(MouseEvent event) {
    	notificaciones.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		JOptionPane.showMessageDialog(null, "hola", null, JOptionPane.INFORMATION_MESSAGE);
    	});
    }

    @FXML
    void usuarioEvent(MouseEvent event) {
    	usuario.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		JOptionPane.showMessageDialog(null, "Se encuentra en linea el administrador con N� documento: "+documento+"",null, JOptionPane.INFORMATION_MESSAGE);
    	});


    }

	public void setAplicacion(Aplicacion aplicacion, String documento) {

		this.aplicacion = aplicacion;
		tableVendedores.getItems().clear();
		tableVendedores.setItems(getListaVendedoresData());
		this.documento= documento;
		
	}

	private ObservableList <Cliente> getListaVendedoresData(){
//		listaVendedoresData.addAll(modelFactoryController.obtenerVendedores());
		return listaVendedoresData;

	}

	@FXML
    void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
    	this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	this.columnApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
    	this.columnDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
    	this.columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
    	this.columnUsuario.setCellValueFactory(new PropertyValueFactory<>("documento"));
    	this.columnContrasenia.setCellValueFactory(new PropertyValueFactory<>("contrasenia"));

    	tableVendedores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

    		vendedorSeleccionado = newSelection;
    		mostrarInformacionVendedor(vendedorSeleccionado);
    		getListaProductosData(vendedorSeleccionado);
    		tableProductos.getItems().clear();
    		tableProductos.setItems(getListaProductosData(vendedorSeleccionado));


    	this.columnProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	this.columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    	this.columnEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    	this.columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
    	this.columnFechaHora.setCellValueFactory(new PropertyValueFactory<>("fecha Hora"));

    	});


    }

	private void mostrarInformacionVendedor(Cliente vendedorSeleccionado) {
		txtNombre.setText(vendedorSeleccionado.getNombre());
		txtApellidos.setText(vendedorSeleccionado.getApellidos());
		txtDocumento.setText(vendedorSeleccionado.getDocumento());
		txtDireccion.setText(vendedorSeleccionado.getDireccion());
		txtCedula.setText(vendedorSeleccionado.getDocumento());
		txtContrasenia.setText(vendedorSeleccionado.getContrasenia());

	}

	private void crearVendedorAction() {
		String nombre = txtNombre.getText();
    	String apellido = txtApellidos.getText();
    	String documento = txtDocumento.getText();
    	String direccion = txtDireccion.getText();
    	String contrasenia= txtContrasenia.getText();

		if(datosValidos(nombre, apellido, documento, direccion, contrasenia) == true){

			Cliente vendedor = null;

//			try {
//				vendedor = modelFactoryController.crearVendedor(nombre, apellido, documento,direccion, contrasenia);
//				listaVendedoresData.add(vendedor);
//
//				mostrarMensaje("Notificaci�n Administrador", "Vendedor creado", "El vendedor se ha creado con �xito", AlertType.INFORMATION);
//
//			} catch (VendedorException e) {
//				mostrarMensaje("Notificaci�n Administrador", "Vendedor no creado", "El vendedor con docuemnto "+documento+" ya se encuentra creado dentro de DAX", AlertType.ERROR);
//			}
		}
		else{
			mostrarMensaje("Notificaci�n Administrador", "Informacion invalida", "Informacion invalida", AlertType.ERROR);

		}
	}

	private void actualizarVendedorAction() {

		String nombre = txtNombre.getText();
    	String apellido = txtApellidos.getText();
    	String documento = txtDocumento.getText();
    	String direccion = txtDireccion.getText();
    	String contrasenia= txtContrasenia.getText();


		if(vendedorSeleccionado == null){
			mostrarMensaje("Notificaci�n Administradorr", "Seleccion Vendedor", "Debe seleccionar un vendedor", AlertType.ERROR);
		}else{
//			if(datosValidos(nombre, apellido, documento, direccion, contrasenia) == true){
//				modelFactoryController.actualizarVendedor(vendedorSeleccionado.getDocumento(),nombre, apellido, documento,direccion, contrasenia);
//				tableVendedores.refresh();
//			}else{
//				mostrarMensaje("Notificaci�n Administrador", "Vendedor no actualizado", "Datos invalidos", AlertType.ERROR);
//
//			}

		}
	}

	private void eliminarVendedorAction() throws VendedorException {

		if(vendedorSeleccionado == null){
			mostrarMensaje("Notificaci�n Administrador", "Seleccion vendedor", "Debe seleccionar un vendedor", AlertType.ERROR);
		}else{
//			boolean eliminado = modelFactoryController.eliminarVendedor(vendedorSeleccionado.getDocumento());
//			if(eliminado){
//				listaVendedoresData.remove(vendedorSeleccionado);
//				vendedorSeleccionado = null;
//				limpiarDatos();
//				tableVendedores.refresh();
//				tableVendedores.getSelectionModel().clearSelection();
//				mostrarMensaje("Notificaci�n Administrador", "Vendedor eliminado", "El vendedor ha sido eliminado", AlertType.ERROR);
//			}else{
//				mostrarMensaje("Notificaci�n Administrador", "Vendedor no eliminado", "El vendedor No ha sido eliminado", AlertType.ERROR);
//
//			}
		}

	}


	private ObservableList <Producto> getListaProductosData(Cliente vendedorSeleccionado){
		String documento= vendedorSeleccionado.getDocumento();
//		listaProductosData.addAll(modelFactoryController.obtenerProductos(documento));
		return listaProductosData;

	}

	private void limpiarDatos() {
		txtNombre.setText("");
		txtApellidos.setText("");
		txtDocumento.setText("");
		txtDireccion.setText("");
		txtCedula.setText("");
		txtContrasenia.setText("");

	}

	private boolean datosValidos(String nombre, String apellido, String documento, String direccion,
			String contrasenia) {
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

		return true;

	}

	private boolean mostrarMensajeConfirmacion(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmaci�n");
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



