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
		
		final int MAIN_MENU_REALIZAR_VENTA =1;
		final int MAIN_MENU_REALIZAR_COMPRA =2;
		final int MAIN_MENU_VER_ARTICULOS_SALDABLES =3;
		final int MAIN_MENU_VER_ARTICULO_MAS_CARO =4;
		final int MAIN_MENU_VER_ARTICULOS_MENOS_STOCK =5;
		final int MAIN_MENU_SALIR =0;
		
		final int MENU_FACTURA_ADD_LINEA =1;
		final int MENU_FACTURA_ELIMINAR_LINEA =2;
		final int MENU_FACTURA_MOSTRAR_EN_PANTALLA =3;
		final int MENU_FACTURA_GUARDAR_EN_FICHERO =4;
		final int MENU_FACTURA_SALIR =0;
		
		int opicionMainMenu = -1;
		
		do {
			switch(opicionMainMenu) {
			case MAIN_MENU_REALIZAR_VENTA:
				Factura factura = crearFacturaConDatos(sc);
				int opcionMenuFactura = -1;
				do {
					switch(opcionMenuFactura) {
					case MENU_FACTURA_ADD_LINEA:
						break;
					case MENU_FACTURA_ELIMINAR_LINEA:
						break;
					case MENU_FACTURA_MOSTRAR_EN_PANTALLA:
						break;
					case MENU_FACTURA_GUARDAR_EN_FICHERO:
						break;
					case MENU_FACTURA_SALIR:
						break;
					default:
						System.out.println("Introduce una opcion correcta");
					}
					
				} while (opcionMenuFactura != MENU_FACTURA_SALIR);
				break;
			case MAIN_MENU_REALIZAR_COMPRA:
				break;
			case MAIN_MENU_VER_ARTICULOS_SALDABLES:
				break;
			case MAIN_MENU_VER_ARTICULO_MAS_CARO:
				break;
			case MAIN_MENU_VER_ARTICULOS_MENOS_STOCK:
				break;
			case MAIN_MENU_SALIR:
				break;
			default:
				System.out.println("Introduce una opcion correcta");
			}
		} while (opicionMainMenu != MAIN_MENU_SALIR);
		
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
