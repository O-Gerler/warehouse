package clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Factura {
	private static final int IVA = 21;
	private int numero;
	private String nombreEmpresa;
	private Date fecha;
	private String concepto;
	ArrayList<LineaFactura> lineaFacturas = new ArrayList<>();
	
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
	
	public Factura crearFacturaConDatos(Almacen almacen, Scanner sc) {
		LineaFactura lineaFactura = new LineaFactura();
		
		int numFactura = pedirNumero(sc);
		System.out.print("Introduce el nombre de la empresa: ");
		String nombreEmpresa = sc.nextLine().trim();
		Date fecha = introducirFecha(sc);
		System.out.print("Introduce el concepto de la factura: ");
		String concepto = sc.nextLine().trim();
		
		Factura factura = new Factura(numFactura, nombreEmpresa, fecha, concepto);
		
		return factura;
	}
	
	private Date introducirFecha(Scanner sc) {
		System.out.print("Introduce la fecha(yyyy/MM/dd): ");
		String fechaIntroducida = sc.nextLine().trim();
		Date fecha;
		try {
			fecha = new SimpleDateFormat("yyyy/MM/dd").parse(fechaIntroducida);
		} catch (ParseException e) {
			fecha = new Date();
		}
		return fecha;
	}
	
	private int pedirNumero(Scanner sc) {
		int numFactura = 0;
		
		do {
			System.out.print("Introduce el numero de factura: ");
			try {
				numFactura = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("ERROR!!!");
				numFactura = -1;
			}
		} while (numFactura < 0);
		
		return numFactura;
	}
}
