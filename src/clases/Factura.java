package clases;

import java.io.File;
import java.io.FileNotFoundException;
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
		Articulo art = null;
		
		do {
			LineaFactura lineaFactura = new LineaFactura();
			do {
				art = recibirCodigoArticulo(almacen, sc);
			} while (recibirCodigoArticulo(almacen, sc) == null);
			int cantidadArticulo = recibirCantidadArticulo(sc);
			
			System.out.println(lineaFactura);
			System.out.print("Desea continuar con la compra[S/n]: ");
			String continuarCompra = sc.nextLine();
			
			if (!continuarCompra.toLowerCase().equals("n")) {
				lineaFactura.setNumero(numLinea);
				lineaFactura.setArticulo(art);
				lineaFactura.setCantidad(cantidadArticulo);
				lineaFacturas.add(lineaFactura);
				System.out.println("Linea agregada");
				numLinea++;
			}else {
				agregarLinea = false;
			}
			
			if (agregarLinea) {
				System.out.print("Desea continuar con la compra[S/n]: ");
				continuarCompra = sc.nextLine();
				if (!continuarCompra.toLowerCase().equals("n"))
					agregarLinea = true;
			}
			
		}while(agregarLinea);
	}

	private int recibirCantidadArticulo(Scanner sc) {
		int cantidadArticulo = 0;
		
		do {
			System.out.print("Introduce la cantidad del articulo: ");
			try {
				cantidadArticulo = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("ERROR!!!");
				cantidadArticulo = -1;
			}
		} while (cantidadArticulo < 1);
		
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
		float precioTotal = 0;
		
		for (LineaFactura lineaFactura : lineaFacturas) {
			precioTotal += (lineaFactura.getArticulo().getPrecio() * lineaFactura.getCantidad());
		}
		
		return precioTotal();
	}
	
	public void mostrarEnPantalla() {
		System.out.println("-------------------------------FACTURA-------------------------------");
		System.out.println("Numero de factura: \t\t\t" + this.numero);
		System.out.println("Nombre de empresa: \t\t\t" + this.nombreEmpresa);
		System.out.println("Concepto de factura: \t\t\t" + this.concepto);
		System.out.println("Fecha: " + this.fecha != null ? new SimpleDateFormat("yyyy/MM/dd").format(this.fecha) : new Date());
		System.out.println("---------------------------------------------------------------------");
		System.out.println("\tNumero\t|\tCantidad\t|\tPrecio Total\t|\tArticulo\t\t|");
		for (LineaFactura lineaFactura : lineaFacturas) {
			System.out.println(lineaFactura);
		}
		System.out.println("---------------------------------------------------------------------");
		System.out.println("Precio total: " + precioTotal());
	}
	
	public void guardarEnFichero() throws FileNotFoundException {
		String nombreFichero = generarNombreFichero();
		File file = new File(nombreFichero);
		
		if (file.exists()) {
			System.out.println("El archivo ya existe");
			nombreFichero = String.valueOf(numFichero).concat(nombreFichero);
			file = new File(nombreFichero);
		}
		
		PrintWriter pw = new PrintWriter(file);
		pw.println("-------------------------------FACTURA-------------------------------");
		pw.println("Numero de factura: \t\t\t" + this.numero);
		pw.println("Nombre de empresa: \t\t\t" + this.nombreEmpresa);
		pw.println("Concepto de factura: \t\t\t" + this.concepto);
		pw.println("Fecha: " + this.fecha != null ? new SimpleDateFormat("yyyy/MM/dd").format(this.fecha) : new Date());
		pw.println("---------------------------------------------------------------------");
		pw.println("\tNumero\t|\tCantidad\t|\tPrecio Total\t|\tArticulo\t\t|");
		for (LineaFactura lineaFactura : lineaFacturas) {
			pw.println(lineaFactura);
		}
		pw.println("---------------------------------------------------------------------");
		pw.println("Precio total: " + precioTotal());
		
		
	}

	private String generarNombreFichero() {
		String nombreFichero = this.numero + "_ "+ (this.fecha != null ? new SimpleDateFormat("yyyy/MM/dd").format(this.fecha) : new Date()) 
				+"_factura.txt";
		return nombreFichero;
	}
}
