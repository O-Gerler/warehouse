package clases;

public class LineaFactura {
	private int numero;
	private Articulo articulo;
	private int cantidad;
	
	public LineaFactura() {}
	
	public LineaFactura(int numero, Articulo articulo, int cantidad) {
		super();
		this.numero = numero;
		this.articulo = articulo;
		this.cantidad = cantidad;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
