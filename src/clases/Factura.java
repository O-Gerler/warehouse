package clases;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Factura {
	private static final int IVA = 21;
	private int numero;
	private String nombreEmpresa;
	private Date fecha;
	private String concepto;
	ArrayList<LineaFactura> lineaFacturas = new ArrayList<>();
	private int numLinea = 1;
	private int numFichero = 1;
	
	public Factura() {
		// TODO Auto-generated constructor stub
	}
	
	public Factura(int numero, String nombreEmpresa, Date fecha, String concepto) {
		this.numero = numero;
		this.nombreEmpresa = nombreEmpresa;
		this.fecha = fecha;
		this.concepto = concepto;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
	public void addLinea(Almacen almacen, Scanner sc) {
		boolean agregarLinea = false;
		
		do {
			Articulo art = null;
			LineaFactura lineaFactura = new LineaFactura();
			art = recibirCodigoArticulo(almacen, sc);
			int cantidadArticulo = recibirCantidadArticulo(almacen,sc,art);
			
			System.out.println(numLinea + ";" + art.getName()+ ";" + cantidadArticulo);
			System.out.print("Desea continuar con la compra[S/n]: ");
			String continuarCompra = sc.nextLine();
			
			if (!continuarCompra.toLowerCase().equals("n")) {
				lineaFactura.setNumero(numLinea);
				lineaFactura.setArticulo(art);
				lineaFactura.setCantidad(cantidadArticulo);
				lineaFacturas.add(lineaFactura);
				System.out.println("Linea agregada");
				numLinea++;
				agregarLinea = true;
			}else {
				agregarLinea = false;
			}
			
			if (agregarLinea) {
				System.out.print("Desea continuar con la compra[S/n]: ");
				continuarCompra = sc.nextLine();
				if (continuarCompra.toLowerCase().equals("n"))
					agregarLinea = false;
			}
			
		}while(agregarLinea);
	}

	private int recibirCantidadArticulo(Almacen almacen, Scanner sc, Articulo art) {
		int cantidadArticulo = 0;
		
		do {
			System.out.print("Introduce la cantidad del articulo: ");
			try {
				cantidadArticulo = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("ERROR!!!");
				cantidadArticulo = -1;
			}
		} while (cantidadArticulo < 1 && !almacen.disponibilidad(art.getCode(), cantidadArticulo));
		
		return cantidadArticulo;
	}

	private Articulo recibirCodigoArticulo(Almacen almacen, Scanner sc) {
		String codigoProducto;
		
		do {
			System.out.print("Introduce el codigo del producto: ");
			codigoProducto = sc.nextLine().trim().toLowerCase();
		} while (!almacen.hayStock(codigoProducto));
		
		return almacen.articulo(codigoProducto);
		
	}
	
	public void eliminarLinea(int numeroLinea, Scanner sc) {
		System.out.println("La linea que quieres borrar es: ");
		Iterator<LineaFactura> it = lineaFacturas.iterator();
		while (it.hasNext()) {
			LineaFactura lineaFactura = it.next();
			if (lineaFactura.getNumero() == numeroLinea) {
				System.out.print("Desea continuar con la compra[S/n]: ");
				String continuarCompra = sc.nextLine();
				if (!continuarCompra.toLowerCase().equals("n"))
					it.remove();
			}
		}
		reasignarNumeroDeLinea();
	}

	private void reasignarNumeroDeLinea() {
		int numeroNuevo = 1;
		
		for (LineaFactura lineaFactura : lineaFacturas) {
			lineaFactura.setNumero(numeroNuevo);
			numeroNuevo++;
		}
		
		numLinea = numeroNuevo;
	}
	
	public double precioTotal() {
		double precioTotal = 0;
		
		for (LineaFactura lineaFactura : lineaFacturas) {
			if (lineaFactura.getArticulo() instanceof Cerveza) 
				precioTotal += (((Cerveza)lineaFactura.getArticulo()).calcularTasa() * lineaFactura.getArticulo().getPrecio());
			else
				precioTotal += (lineaFactura.getArticulo().getPrecio() * lineaFactura.getCantidad());
		}
		
		return precioTotal * IVA;
	}
	
	public void mostrarEnPantalla() {
		System.out.println("-------------------------------FACTURA-------------------------------");
		System.out.println("Numero de factura: \t\t\t" + this.numero);
		System.out.println("Nombre de empresa: \t\t\t" + this.nombreEmpresa);
		System.out.println("Concepto de factura: \t\t\t" + this.concepto);
		System.out.println("Fecha: " + this.fecha != null ? new SimpleDateFormat("yyyy/MM/dd").format(this.fecha) : new Date());
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("\tNumero\t|\tCantidad\t|\tPrecio Total\t|\tArticulo\t\t|");
		for (LineaFactura lineaFactura : lineaFacturas) {
			System.out.println("\t" + lineaFactura.getNumero() + "\t\t"
					+ lineaFactura.getCantidad() + "\t\t\t" + lineaFactura.precioTotal() + 
					"\t\t\t" + lineaFactura.getArticulo().getName());
		}
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("Precio total: " + precioTotal());
	}
	
	public void guardarEnFichero(Almacen almacen) {
		String nombreFichero = generarNombreFichero();
		File file = new File(nombreFichero);
		
		if (file.exists()) {
			do {
				System.out.println("El archivo ya existe\nCambiando nombre...");
				nombreFichero = String.valueOf(numFichero).concat(nombreFichero);
				numFichero++;
			}while(new File(nombreFichero).exists());
			
		}
		
		try {
			PrintWriter pw = new PrintWriter(nombreFichero);
			pw.println("-------------------------------FACTURA-------------------------------");
			pw.println("Numero de factura: \t\t\t" + this.numero);
			pw.println("Nombre de empresa: \t\t\t" + this.nombreEmpresa);
			pw.println("Concepto de factura: \t\t\t" + this.concepto);
			pw.println("Fecha: " + this.fecha != null ? new SimpleDateFormat("yyyy/MM/dd").format(this.fecha) : new Date());
			pw.println("------------------------------------------------------------------------------------------------");
			pw.println("\tNumero\t|\tCantidad\t|\tPrecio Total\t|\tArticulo\t\t|");
			for (LineaFactura lineaFactura : lineaFacturas) {
				pw.println("\t" + lineaFactura.getNumero() + "\t\t"
						+ lineaFactura.getCantidad() + "\t\t\t" + lineaFactura.precioTotal() + 
						"\t\t\t" + lineaFactura.getArticulo().getName());
			}
			pw.println("------------------------------------------------------------------------------------------------");
			pw.println("Precio total: " + precioTotal());
				
			pw.close();
			
			for (LineaFactura lineaFactura : lineaFacturas) {
				for (Articulo articulo : almacen.getArticulos()) {
					if (articulo.code.equals(lineaFactura.getArticulo().getCode())) {
						articulo.disminuirStock(lineaFactura.getCantidad());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("No se pudo crear el archivo");
		}
	}

	private String generarNombreFichero() {
		String nombreFichero = this.numero + "_"+ (this.fecha != null ? new SimpleDateFormat("yyyy/MM/dd").format(this.fecha) 
				: new SimpleDateFormat("yyyy/MM/dd").format(new Date()))
				+"_factura.txt";
		return nombreFichero;
	}
}
