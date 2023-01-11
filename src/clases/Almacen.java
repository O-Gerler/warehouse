package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Almacen {
	private ArrayList<Articulo> articulos = new ArrayList<>();

	public Almacen() {}
	
	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}

	public void cargarDatos() throws FileNotFoundException {
		File file = new File("src/datosFichero/datos.txt");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		
		while(sc.hasNextLine()) {
			String linea[] = sc.nextLine().split(";");
			if (linea[1].charAt(0) == '1') {
				Refresco refresco = new Refresco(linea[0], linea[1], linea[2], Integer.parseInt(linea[3])
						, Double.parseDouble(linea[4]), Integer.parseInt(linea[5]), linea[6], 
						linea[7].equals("true") ? true : false , linea[8].equals("true") ? true : false , 
								Integer.parseInt(linea[9]));
				articulos.add(refresco);
			}if (linea[1].charAt(0) == '2') {
				Vino vino = new Vino(linea[0], linea[1], linea[2], Integer.parseInt(linea[3])
						, Double.parseDouble(linea[4]), Integer.parseInt(linea[5]), linea[6], 
						linea[7], Integer.parseInt(linea[8]) , linea[9], Double.parseDouble(linea[10]));
				articulos.add(vino);
			}if (linea[1].charAt(0) == '3') {
				Cerveza cerveza = new Cerveza(linea[0], linea[1], linea[2], Integer.parseInt(linea[3])
						, Double.parseDouble(linea[4]), Integer.parseInt(linea[5]), linea[6], linea[7],
						Double.parseDouble(linea[8]));
				articulos.add(cerveza);
			}
		}
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
		return articulo(codigoProducto) != null ? articulo(codigoProducto).getPrecio() : null;
	}
	
	public boolean hayStock(String codigoProducto) {
		return articulo(codigoProducto) != null && articulo(codigoProducto).getStock() > 0 ? true : false;
	}
	
	public ArrayList<Articulo> stockJusto() {
		ArrayList<Articulo> articulosStockJusto = new ArrayList<>();
		
		for (Articulo articulo : articulos) {
			if (articulo.getStock() <= 10) {
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
	
	public boolean disponibilidad(String codigoProducto, int cantidad) {
		return articulo(codigoProducto) != null && articulo(codigoProducto).getStock() > cantidad ? true : false;
	}
	
	public ArrayList<Articulo> equivalentes(String codigoArticulo) {
		ArrayList<Articulo> articulosEquivalentes = new ArrayList<>();
		Articulo articulo = this.articulo(codigoArticulo);
		
		for (Articulo art : articulos) {
			if (!art.getCode().equals(articulo.getCode()) && art.getPrecio() <= (articulo.getPrecio() + 0.20) && art.getPrecio() >= (articulo.getPrecio() - 0.20)) {
				articulosEquivalentes.add(art);
			}
		}
		
		return articulosEquivalentes;
	}
	
	public ArrayList<Articulo> ordenarPorPrecio(String orden) {
		ArrayList<Articulo> articulosOrdenadosPorPrecio = articulos;
		
		if (orden.equals("descendente")) {
			articulosOrdenadosPorPrecio.sort((arg0, arg1) ->  arg0.getPrecio() - arg1.getPrecio() > 0 ? 1 : -1) ;
		}else {
			articulosOrdenadosPorPrecio.sort((arg0, arg1) -> arg1.getPrecio() - arg0.getPrecio() > 0 ? 1 : -1) ;
		}
		
		return articulosOrdenadosPorPrecio;
	}
	
	public void ordenarPorStock(String orden) {
		ArrayList<Articulo> articulosOrdenados = articulos;
		
		if (orden.equals("descendente")) {
			articulosOrdenados.sort((arg0, arg1) -> arg1.getStock() - arg0.getStock());
		}else {
			articulosOrdenados.sort((arg0, arg1) -> arg0.getStock() - arg1.getStock());
		}
		
		for (Articulo articulo : articulosOrdenados) {
			if (articulo instanceof Refresco) {
				((Refresco) articulo).visualizarArticulo();
			}else if (articulo instanceof Cerveza) {
				((Cerveza) articulo).visualizarArticulo();
			}else if (articulo instanceof Vino) {
				((Vino) articulo).visualizarArticulo();
			}
		}
	}

}
