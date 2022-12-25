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
		Scanner sc = new Scanner(file);
		
		while(sc.hasNextLine()) {
			String linea[] = sc.nextLine().split(";");
			if (linea[0].equals("1")) {
				Refresco refresco = new Refresco(linea[1], linea[0], linea[2], Integer.parseInt(linea[3])
						, Double.parseDouble(linea[4]), Integer.parseInt(linea[5]), linea[6], 
						linea[7].equals("true") ? true : false , linea[8].equals("true") ? true : false , 
								Integer.parseInt(linea[9]));
				articulos.add(refresco);
			}if (linea[0].equals("2")) {
				Vino vino = new Vino(linea[1], linea[0], linea[2], Integer.parseInt(linea[3])
						, Double.parseDouble(linea[4]), Integer.parseInt(linea[5]), linea[6], 
						linea[7], Integer.parseInt(linea[8]) , linea[9], Double.parseDouble(linea[10]));
				articulos.add(vino);
			}if (linea[0].equals("2")) {
				Cerveza cerveza = new Cerveza(linea[1], linea[0], linea[2], Integer.parseInt(linea[3])
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
	
	public void ordenarPorStock(String orden) {
		ArrayList<Articulo> articulosOrdenados = (ArrayList<Articulo>) articulos.clone();
		
		if (orden.equals("descendente")) {
			articulosOrdenados.sort(new CompararStockPorOrdenDescendente());
		}else {
			articulosOrdenados.sort(new CompararStockPorOrdenAscendente());
		}
		
		for (Articulo articulo : articulosOrdenados) {
			if (articulo instanceof Refresco) {
				((Refresco) articulo).visualizarPropiedades();
			}else if (articulo instanceof Cerveza) {
				((Cerveza) articulo).visualizarAticulo();
			}else if (articulo instanceof Vino) {
				((Vino) articulo).visualizarAticulo();
			}
		}
	}

}
