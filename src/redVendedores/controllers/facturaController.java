package redVendedores.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class facturaController {
	
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

    
    @FXML
    void flechaRegresarEvent(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert labelFecha != null : "fx:id=\"labelFecha\" was not injected: check your FXML file 'Factura.fxml'.";
        assert labelProducto != null : "fx:id=\"labelProducto\" was not injected: check your FXML file 'Factura.fxml'.";
        assert labelCantidadP != null : "fx:id=\"labelCantidadP\" was not injected: check your FXML file 'Factura.fxml'.";
        assert labelMetodoPago != null : "fx:id=\"labelMetodoPago\" was not injected: check your FXML file 'Factura.fxml'.";
        assert labelTotalPagar != null : "fx:id=\"labelTotalPagar\" was not injected: check your FXML file 'Factura.fxml'.";

    }
}

	

