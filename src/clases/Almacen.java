package clases;

import java.util.ArrayList;

public class Almacen {
	private ArrayList<Articulo> articulos = new ArrayList<>();

	public Almacen() {}
	
	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}

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
	
	public boolean hayStock(String codigoProducto) {
		for (Articulo articulo : articulos) {
			if (codigoProducto.equals(articulo.getCode()) && articulo.getStock() > 0) {
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Articulo> stockJusto(int stock) {
		ArrayList<Articulo> articulosStockJusto = new ArrayList<>();
		
		for (Articulo articulo : articulos) {
			if (articulo.getStock() == stock) {
				articulosStockJusto.add(articulo);
			}
		}
		
		return articulosStockJusto;
	}
	
	public Articulo articulo(String codigoArticulo) {
		Articulo articulo = null;
		
		for (Articulo art : articulos) {
			if (codigoArticulo.equals(art.getCode())) {
				articulo = art;
			}
		}
		
		return articulo;
	}
	
	public boolean disponibilidad(String codigoProducto, int stock) {
		for (Articulo articulo : articulos) {
			if (codigoProducto.equals(articulo.getCode()) && stock <= articulo.getStock()) {
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Articulo> equivalentes(String codigoArticulo) {
		Articulo articulo = null;
		ArrayList<Articulo> articulosEquivalentes = new ArrayList<>();
		
		for (Articulo art : articulos) {
			if (codigoArticulo.equals(art.getCode())) {
				articulo = art;
			}
		}
		
		for (Articulo art : articulos) {
			if (art.getPrecio() <= (articulo.getPrecio() + 20) || art.getPrecio() >= (articulo.getPrecio() - 20)) {
				articulosEquivalentes.add(art);
			}
		}
		
		return articulosEquivalentes;
	}
	
	public ArrayList<Articulo> ordenarPorPrecio(String orden) {
		ArrayList<Articulo> articulosOrdenados = (ArrayList<Articulo>) articulos.clone();
		
		if (orden.equals("descendente")) {
			articulosOrdenados.sort(new CompararPrecioPorOrdenDescendente());
		}else {
			articulosOrdenados.sort(new CompararPrecioPorOrdenAscendete());
		}
		
		return articulosOrdenados;
	}
	
	private void ordenarPorStock(String orden) {
		ArrayList<Articulo> articulosOrdenados = (ArrayList<Articulo>) articulos.clone();
		
		if (orden.equals("descendente")) {
			articulosOrdenados.sort(new CompararStockPorOrdenDescendente());
		}else {
			articulosOrdenados.sort(new CompararStockPorOrdenAscendente());
		}
		
		for (Articulo articulo : articulosOrdenados) {
			articulo.visualizarAticulo();
		}
	}

}
