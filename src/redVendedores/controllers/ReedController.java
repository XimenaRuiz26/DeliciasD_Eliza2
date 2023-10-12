package redVendedores.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import redVendedores.aplication.Aplicacion;

import redVendedores.model.MyListener;
import redVendedores.model.Producto;


public class ReedController {


	    ObservableList<Producto> listaProductosData = FXCollections.observableArrayList();

	    Producto productoSeleccionado;
	    
	    private Aplicacion aplicacion;

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Label cerrarSesion;


	    @FXML
	    private Label nombreVendedor;

	    @FXML
	    private ImageView foto;


	    @FXML
	    private ImageView iconCerrarS;

	    @FXML
	    private Label txtCategoria;

	    @FXML
	    private Label txtCodigo;

	    @FXML
	    private TextField txtPrecio;

	    @FXML
	    private ImageView imagenProducto;

	    @FXML
	    private TextField txtNombreProducto;

	    @FXML
	    private ImageView iconComentarios;

	    @FXML
	    private ImageView iconMeGusta;

	    private String usuario;

	    @FXML
	    private ScrollPane scroll;
	    
	    @FXML
	    private GridPane grid;
	    
	    @FXML
	    private Button btnComprar;
	    
	    @FXML
	    private Spinner<Integer> nroProductos;
	    
	    private int totalPrecio;


	    
	    private ArrayList<Producto> productos = new ArrayList<>();
	    private MyListener myListener;
	    
	    ModelFactoryController modelFactoryController; 

	    private void setChosenProducto(Producto producto) {
	        txtNombreProducto.setText(producto.getNombre());
	        txtCodigo.setText(producto.getCodigo());
	        txtPrecio.setText(producto.getPrecio());
	        txtCategoria.setText(producto.getCategoria());
	        Image imagen2= new Image("file:/"+producto.getImagen());
	        imagenProducto.setImage(imagen2);
	        
	        productoSeleccionado = producto;
	    }


	    @FXML
	    void ComprarEvent(ActionEvent event) {
	    	nroProductos.valueProperty().addListener((obs, oldValue, newValue) -> {
	    	    totalPrecio= modelFactoryController.calcularTotal(newValue, productoSeleccionado.getCodigo());
	    	    String opcionSeleccionada= "";
	    		String [] botones = { "Nequi", "Tarjeta Debito/Credito", "PSE"};
	    		int variable = JOptionPane.showOptionDialog (null, "¿Por qué medio desea realizar su pago?", "Metodos de pago", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);
	    		if (variable >= 0 && variable < botones.length) {
	                opcionSeleccionada = botones[variable];
	                System.out.println("Hola");
	    		}
	    		aplicacion.mostrarVentanaFactura(totalPrecio, opcionSeleccionada, productoSeleccionado.getNombre(), newValue);
	    	});

	    }


	    @FXML
	    void usuarioEvent(MouseEvent event) {
	    	foto.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		JOptionPane.showMessageDialog(null, "Se encuentra en linea el vendedor"+nombreVendedor.getText()+"con Nï¿½ documento"+usuario+"",null, JOptionPane.INFORMATION_MESSAGE);
	    	});

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
	    void meGustaEvent(MouseEvent event) {
	    	iconMeGusta.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		obtenerNumeroMeGustas(productoSeleccionado, usuario);
	    	});
	    }

	    @FXML
	    void comentariosEvent(MouseEvent event) {
	    	iconComentarios.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		obtenerComentarios(productoSeleccionado, usuario);
	    	});

	    }

	public void setAplicacion(Aplicacion aplicacion, String usuario) {
		this.aplicacion = aplicacion;
		this.usuario= usuario;
		obtenerNombreVendedor(usuario);
		
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
		nroProductos.setValueFactory(valueFactory);
		getListaProductosData();
		productos.addAll(getListaProductosData());
		System.out.println(productos);
		if (productos.size() > 0) {
	        setChosenProducto(productos.get(0));
	        myListener = new MyListener() {
	            @Override
	            public void onClickListener(Producto producto) {
	                setChosenProducto(producto);
	            }
	        };
	        
	        grid.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
	            Object source = event.getTarget();
	            if (source instanceof Producto) {
	                Producto clickedRectangle = (Producto) source;

	                if (productoSeleccionado != null) {
	                    System.out.println("Lo cogio");// Restaurar el color anterior del objeto previamente seleccionado
	                }

	               // Cambiar el color del objeto seleccionado
	                productoSeleccionado = clickedRectangle;
	            }
	        });

	    }
	    int column = 0;
	    int row = 1;
	    try {
	        for (int i = 0; i < productos.size(); i++) {
	            FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Aplicacion.class.getResource("../views/Producto.fxml"));

				AnchorPane rootLayout = (AnchorPane)loader.load();

	            ProductoController itemController = loader.getController();
	            itemController.setData(productos.get(i),myListener);

	            if (column == 2) {
	                column = 0;
	                row++;
	            }

	            grid.add(rootLayout, column++, row); //(child,column,row)
	            //set grid width
	            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
	            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
	            grid.setMaxWidth(Region.USE_PREF_SIZE);

	            //set grid height
	            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
	            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
	            grid.setMaxHeight(Region.USE_PREF_SIZE);

	            GridPane.setMargin(rootLayout, new Insets(10));
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

	private ArrayList <Producto> getListaProductosData(){
		return modelFactoryController.obtenerProductos();
	}
	

	@FXML
   void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
   }

	 
	private void obtenerNumeroMeGustas(Producto productoSeleccionado, String documento){
		String codigo= productoSeleccionado.getCodigo();
//		String numeroMeGusta= modelFactoryController.obtenerMeGustas2(codigo, documento);
//		JOptionPane.showMessageDialog(null, "El producto tiene: "+numeroMeGusta+" me gustas");
	}
	
	private void obtenerComentarios(Producto productoSeleccionado2, String documento2) {
		String codigo= productoSeleccionado.getCodigo();
//		ArrayList<Comentario> numeroMeGusta= modelFactoryController.obtenerComentarios(codigo, documento);
//		JOptionPane.showMessageDialog(null, numeroMeGusta, "Comentarios del producto", JOptionPane.PLAIN_MESSAGE);
	}
		
	


//	private void mostrarInformacionProducto(Producto productoSeleccionado) {
//		txtNombreProducto.setText(productoSeleccionado.getNombre());
//		txtCodigo.setText(productoSeleccionado.getCodigo());
//		txtPrecio.setText(productoSeleccionado.getPrecio());
//		txtCategoria.setText(productoSeleccionado.getCategoria());
//		Image imagen= new Image("file:/"+productoSeleccionado.getImagen());
//		imagenProducto.setImage(imagen);
//
//	}

	private void obtenerNombreVendedor(String usuario){
		String nombre= modelFactoryController.obtenerNombre(usuario);
		nombreVendedor.setText(nombre);

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
