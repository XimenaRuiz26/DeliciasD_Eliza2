package redVendedores.model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.Image;
import redVendedores.exceptions.VendedorException;


public class Red implements Serializable{


	/*
	 * Atributos de la clase Red
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private ArrayList<Cliente> listaClientes;
	private Administrador administrador;

	/*
	 * Constructores de la clase Red
	 */

	public Red(String nombre) {
		super();
		this.nombre = nombre;
		listaClientes = new ArrayList<Cliente>();
		this.administrador = new Administrador();
	}

	public Red(){
		super();
		
	}



	/*
	 * Getters & setters de la clase Red
	 */

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}



	public ArrayList<Cliente> getlistaClientes() {
		return listaClientes;
	}

	public void setlistaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	/*
	 * metodo equals de la clase Red
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Red other = (Red) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	/*
	 *toString de la clase Red
	 */

	@Override
	public String toString() {
		return "Biblioteca [nombre=" + nombre + "]";
	}

	//----------------------------------------------------

	/*
	 * Metodo que llama a la clase Vendedor para verificar si el documento (usuario) y la contrasenia son correctas
	 */

	public boolean verificarUsuario(String usuario, String contrasenia) {
		for (Cliente cliente : listaClientes) {
			if(cliente.verificarUsuario(usuario,contrasenia)){
				return true;
			}
		}
		return false;
	}

	/*
	 * Metodo que verifica si el documento (Usuario) y contrasenia ingresados por el administrador son correctos
	 */

	public boolean verificarAdmin(String correo, String contrasenia){
		if(administrador.getCorreo().equals(correo)&& administrador.getContrasenia().equals(contrasenia)){
			return true;
		}
		return false;
	}

	/*
	 * Metodo que permite crear un vendedor dentro de la red
	 */

	public Cliente crearCliente(String nombre2, String apellidos, String documento,String direccion, 
			String contrasenia, String email, String usuario, Image imagen) throws VendedorException {
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre2);
		cliente.setApellidos(apellidos);
		cliente.setDocumento(documento);
		cliente.setDireccion(direccion);
		cliente.setContrasenia(contrasenia);
		cliente.setCorreo(email);
		cliente.setUsuario(usuario);
		cliente.setImagen(imagen);

		if(verificarCorreo(email) == true){
			throw new VendedorException("Ya existe un cliente asociado a este correo");
		}
		listaClientes.add(cliente);
		return cliente;
	}
//
//	/*
//	 * Metodo que verifica si ya existe un vendedor en la red
//	 */
//
//	public boolean existeVendedor(String documento) {
//		for (Cliente vendedor : listaClientes) {
//			if(vendedor.getDocumento().equals(documento)){
//				return true;
//			}
//		}
//		return false;
//	}
//
	/*
	 * Metodo que verifica si ya existe un administrador dentro de la red
	 */

	public boolean existeAdministrador (String correo) {

			if(administrador.getCorreo().equals(correo)){
				return true;
			}

		return false;
	}

	/*
	 * Metodo que llama a la clase Vendedor para actualizar la contrasenia
	 */

	public void actualizarContrasenia( String correo, String nuevaContrasenia ) {
		for (Cliente cliente : listaClientes) {
			if(cliente.getCorreo().equals(correo)){
				cliente.setContrasenia(nuevaContrasenia);
			}
		}
	}

	/*
	 * Metodo que permite actualizar la contrasenia del administrador
	 */

	public void actualizarContraseniaAdmin(String correo, String nuevaContrasenia) {
		if(administrador.getCorreo().equals(correo)){
			administrador.setContrasenia(nuevaContrasenia);
			}
		}

//	/*
//	 * Metodo que permite actualizar un vendedor de la red
//	 */
//
//	public void actualizarVendedor(String documento, String nombre2, String apellido, String documento2,
//			String direccion, String contrasenia) {
//		for (Cliente vendedor : listaClientes) {
//			if(vendedor.getDocumento().equals(documento)){
//				vendedor.setNombre(nombre2);
//				vendedor.setApellidos(apellido);
//				vendedor.setDocumento(documento2);
//				vendedor.setDireccion(direccion);
//				vendedor.setContrasenia(contrasenia);
//			}
//		}
//	}
//
//	/*
//	 * Metodo que permite eliminar un vendedor de la red
//	 */
//
//	public boolean eliminarVendedor(String documento) {
//		if(existeVendedor(documento)){
//			for (Cliente vendedor : listaClientes) {
//				if(vendedor.getDocumento().equals(documento)){
//					listaClientes.remove(vendedor);
//					return true;
//				}
//			}
//		}else{
//			return false;
//		}
//		return false;
//	}
//
//	/*
//	 * Metodo obtiene el nombre de un vendedor segun su numero de identificacion (documento)
//	 */
//
//	public String obtenerNombreVendedor(String documento) {
//		String cadenaAux= "";
//		for (Cliente vendedor : listaClientes) {
//			if(vendedor.getDocumento().equals(documento)){
//				cadenaAux= vendedor.getNombre()+" "+vendedor.getApellidos();
//			}
//		}
//		return cadenaAux;
//	}
//
//	/*
//	 * Metodo que retorna una lista de productos de un vendedor
//	 */
//
//	public ArrayList<Producto> obtenerProductos(String documento) {
//		ArrayList<Producto> listaProducto = new ArrayList<>();
//		for (Cliente vendedor : listaClientes) {
//			if(vendedor.getDocumento().equals(documento)){
//				listaProducto.addAll(vendedor.getListaProductos());
//			}
//		}
//		return listaProducto;
//	}
//
//	/*
//	 * Metodo que llama la clase Vendedor para verificar si existe un producto
//	 */
//	public boolean existeProducto(String documento, String codigo) {
//		for (Cliente vendedor : listaClientes) {
//			if(vendedor.getDocumento().equals(documento)){
//				vendedor.verificarExisteProducto(codigo);
//				return true;
//			}
//		}
//		return false;
//	}
//
//	/*
//	 * Metodo que llama a la clase Vendedor para poder crear un producto
//	 */
//
//	public Producto crearProducto(String nombre2, String codigo, String precio, String estado, String categoria,
//			String fechaHora, String documento) throws ProductoException {
//		for (Cliente vendedor : listaClientes) {
//			if(vendedor.getDocumento().equals(documento)){
//				return vendedor.crearProducto(nombre2, codigo, precio, estado, categoria, fechaHora, documento);
//			}
//			}
//		return null;
//
//	}
//
//	/*
//	 * Metodo que llama a la clase Vendedor para poder actualizar un producto
//	 */
//
//	public void actualizarProducto(String codigo, String nombre2, String codigo2, String precio, String estado,
//			String categoria, String fechaHora, String documento) {
//		for (Cliente vendedor : listaClientes) {
//			if(vendedor.getDocumento().equals(documento)){
//				vendedor.actualizarProducto(codigo, nombre2, codigo2, precio, estado, categoria, fechaHora);
//			}
//		}
//	}
//
//	/*
//	 * Metodo que llama a la clase Vendedor para poder eliminar un producto
//	 */
//
//	public boolean eliminarProducto(String codigo, String documento) {
//		for (Cliente vendedor : listaClientes) {
//			if(vendedor.getDocumento().equals(documento)){
//				return vendedor.eliminarProducto(codigo);
//			}
//		}
//		return false;
//
//	}
//
//	/*
//	 * Metodo que retorna una lista con los nombres de los vendedores de la red
//	 */
//
//	public ArrayList<String> obtenerNombresVendedores(String documento) {
//		ArrayList<String> nombres = new ArrayList<>();
//		for (Cliente vendedor : listaClientes) {
//			if(vendedor.getDocumento().compareTo(documento)!=0){
//				nombres.add(vendedor.getNombre()+ " " + vendedor.getApellidos());
//			}
//		}
//		return nombres;
//	}
//
//	/*
//	 * Metodo que verifica si el nombre ingresado es igual al almacenado
//	 */
//
//	public boolean obtenerInformacion(String nombre2) {
//		for (Cliente vendedor : listaClientes) {
//			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
//			if(nombreVendedor.equals(nombre2)){
//				return true;
//			}
//		}
//		return false;
//	}
//
//	/*
//	 * Metodo que retorna una lista de productos de los vendedores amigos
//	 */
//
//	public ArrayList<Producto> obtenerProductosAmigos(String nombre2) {
//		ArrayList<Producto> listaProducto = new ArrayList<>();
//		for (Cliente vendedor : listaClientes) {
//			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
//			if(nombreVendedor.equals(nombre2)){
//				listaProducto.addAll(vendedor.getListaProductos());
//			}
//		}
//		return listaProducto;
//	}
//
//	/*
//	 * Metodo que llama a la clase Vendedor para poder dar Me Gusta a un producto
//	 */
//
//	public boolean darMeGusta(String codigo, String nombre, String documento){
//		for (Cliente vendedor : listaClientes) {
//			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
//			if(nombreVendedor.equals(nombre)){
//				try {
//					return vendedor.darMeGusta(codigo,documento);
//				} catch (MeGustaException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return false;
//	}
//
//	/*
//	 * Metodo que llama a la clase Vendedor para saber el numero de Me Gustas de un producto
//	 */
//	
//	
//	public String obtenerNumeroMeGustas(String nombre, String codigo) {
//		for (Cliente vendedor : listaClientes) {
//			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
//			if(nombreVendedor.equals(nombre)){
//				return vendedor.obtenerMeGustas(codigo);
//			}
//		}
//		return null;
//
//	}
//
//	/*
//	 * Metodo que retorna una lista con los nombres de los vendedores
//	 */
//	public ArrayList<Cliente> obtenerAmigos(String documento) {
//			for (Cliente vendedor : listaClientes) {
//				if(vendedor.getDocumento().equals(documento)){
//					return vendedor.getListaAmigos();
//				}
//			}
//			return null;
//		}
//
//	
//	public String obtenerNumeroMeGustas2(String codigo, String documento) {
//		for (Cliente vendedor : listaClientes) {
//			if(vendedor.getDocumento().equals(documento)){
//				return vendedor.obtenerMeGustas2(codigo);
//			}
//		}
//		return null;
//	}
//
//	public ArrayList<Producto> obtenerProductosTop() {
//		ArrayList<Producto> productosTop = new ArrayList<>();
//		for (int i = 0; i < listaClientes.size(); i++) {
//			Cliente vendedor= listaClientes.get(i);
//			productosTop.addAll(vendedor.obtenerProductosTop());
//			}
//			return productosTop; 
//		}
//
//	public void escribirComentario(String codigo, String nombre2) throws ComentarioException{
//		for (Cliente vendedor : listaClientes) {
//			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
//			if(nombreVendedor.equals(nombre2)){
//				 vendedor.escribirComentario(codigo);
//			}
//		}
//		
//	}
//
//	public ArrayList<Comentario> obtenerComentarios(String codigo, String documento) {
//		for (Cliente vendedor : listaClientes) {
//			if(vendedor.getDocumento().equals(documento)){
//				return vendedor.obtenerComentarios(codigo);
//			}
//		}
//		return null;
//		
//	}
//
//	public boolean agregarAmigo(String nombre, String documento) {
//		for(Cliente vendedor: listaClientes){
//			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
//			for (Cliente vendedor2 : listaClientes) {
//				if(vendedor2.getDocumento().equals(documento) && nombreVendedor.equals(nombre)){
//				 return vendedor.agregarAmigo(vendedor, vendedor2);
//				}
//			}
//		}	
//		return false;
//	}
//
//	public ArrayList<Cliente> obtenerSolicitudes(String documento) {
//		ArrayList<Cliente> listaSolicitudes = new ArrayList<>();
//		for (Cliente vendedor : listaClientes) {
//			if(vendedor.getDocumento().equals(documento)){
//				listaSolicitudes.addAll(vendedor.getListaSolicitudesP());
//			}
//		}
//		return listaSolicitudes;
//		
//	}
//
//	public boolean aceptarSolicitud(String nombreS, String documento) {
//		for(Cliente vendedor: listaClientes){
//			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
//			for (Cliente vendedor2 : listaClientes) {
//				if(vendedor2.getDocumento().equals(documento) && nombreVendedor.equals(nombreS)){
//				 return vendedor.aceptarSolicitud(vendedor, vendedor2);
//				}
//			}
//		}	
//		return false;
//		}
//
//
//	public boolean eliminarSolicitud(String documentoS, String documento) {
//		for(Cliente vendedor: listaClientes){
//			String nombreVendedor= vendedor.getNombre()+" "+vendedor.getApellidos();
//			for (Cliente vendedor2 : listaClientes) {
//				if(vendedor2.getDocumento().equals(documento) && nombreVendedor.equals(documentoS)){
//				 return vendedor.eliminarSolicitud(vendedor, vendedor2);
//				}
//			}
//		}	
//		return false;	
//	}

	public boolean verificarCorreo(String correo) {
		for (Cliente vendedor : listaClientes) {
			if(vendedor.getCorreo().equals(correo)){
				return true;
			}
		}
		return false;
	}

	public String conseguirNombre(String correo) {
		for (Cliente vendedor : listaClientes) {
			if(vendedor.getCorreo().equals(correo)){
				return vendedor.getNombre();
			}
		}
		return correo;
	}
	
	












}
