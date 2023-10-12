package redVendedores.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import redVendedores.aplication.Aplicacion;

public class FacturaController {
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelFecha;

    @FXML
    private Label labelProducto;

    @FXML
    private Label labelCantidadP;

    @FXML
    private Label labelMetodoPago;

    @FXML
    private Label labelTotalPagar;
    
    @FXML
    private ImageView flechaRegresar;
    
    
    private Integer totalPagar;
    
    private String metodo; 
    
    private String nombre;
    
    private Integer numP;
    
	public void setAplicacion(Aplicacion aplicacion, Integer totalPagar, String metodo, String nombre, Integer numP) {
		this.aplicacion = aplicacion;
		this.totalPagar = totalPagar; 
		this.metodo = metodo; 
		this.nombre = nombre; 
		this.numP = numP;

	}
	
	private Aplicacion aplicacion;

    
    @FXML
    void flechaRegresarEvent(MouseEvent event) {

    }

}

	

