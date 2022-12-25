package clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GestorAlmacen {
	public void run() {
		Almacen almacen = new Almacen();
		almacen.cargarDatos();
		
		Scanner sc = new Scanner(System.in);
		
		final int REALIZAR_VENTA =1;
		final int REALIZAR_COMPRA =2;
		final int VER_ARTICULOS_SALDABLES =3;
		final int VER_ARTICULO_MAS_CARO =4;
		final int VER_ARTICULOS_MENOS_STOCK =5;
		final int SALIR =0;
		
		int opicionMainMenu = -1;
		
		do {
			switch(opicionMainMenu) {
			case REALIZAR_VENTA:
				break;
			case REALIZAR_COMPRA:
				break;
			case VER_ARTICULOS_SALDABLES:
				break;
			case VER_ARTICULO_MAS_CARO:
				break;
			case VER_ARTICULOS_MENOS_STOCK:
				break;
			case SALIR:
				break;
			default:
				System.out.println("Introduce una opcion correcta");
			}
		} while (opicionMainMenu != SALIR);
		
	}
	
	private Factura crearFacturaConDatos(Scanner sc) {
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
