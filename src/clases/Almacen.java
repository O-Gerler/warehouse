package clases;

import java.util.ArrayList;

public class Almacen {
	private ArrayList<Articulo> articulos = new ArrayList<>();

	public Almacen() {}
	
	public void cargarDatos() {
		//con un fichero
	}
	
	public Articulo mostrarMasCaro() {
		double price = 0;
		Articulo articulo = null;
	
		for (Articulo art : articulos) {
			if (price < art.getPrecio()) {
				articulo = art;
				price = art.getPrecio();
			}
		}
		return articulo;
	}
	
	public double precio(String codigoProducto) {
		for (Articulo articulo : articulos) {
			if (codigoProducto.equals(articulo.getCode())) {
				return articulo.getPrecio();
				}
		}
		
		return 0;
	}
}
