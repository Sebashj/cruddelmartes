package Modelo;

public class Provedor {
	int idprovedor, cantidad;
	String marca, modelo;
	double precio;
	public Provedor(){
		
	}
	public Provedor(int idprovedor, int cantidad, String marca, String modelo, double precio) {
		super();
		this.idprovedor = idprovedor;
		this.cantidad = cantidad;
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
	}
	
	public int getIdprovedor() {
		return idprovedor;
	}
	public void setIdprovedor(int idprovedor) {
		this.idprovedor = idprovedor;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
