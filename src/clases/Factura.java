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
		LineaFactura lineaFactura = new LineaFactura();
		
		int numFactura = pedirNumeroFactura(sc);
		System.out.print("Introduce el nombre de la empresa: ");
		String nombreEmpresa = sc.nextLine().trim();
		Date fecha = introducirFecha(sc);
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
	private int pedirNumeroFactura(Scanner sc) {
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
