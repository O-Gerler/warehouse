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
		
		int opcionMainMenu = -1;
		
		do {
			mostrarOpcionesMainMenu(MAIN_MENU_REALIZAR_COMPRA, MAIN_MENU_REALIZAR_VENTA, MAIN_MENU_VER_ARTICULOS_SALDABLES,
					MAIN_MENU_VER_ARTICULO_MAS_CARO, MAIN_MENU_VER_ARTICULOS_MENOS_STOCK,MAIN_MENU_SALIR);
			opcionMainMenu = elegirOpcion(opcionMainMenu, sc);
			
			switch(opcionMainMenu) {
			
			case MAIN_MENU_REALIZAR_VENTA:
				Factura factura = crearFacturaConDatos(sc);
				int opcionMenuFactura = -1;
				do {
					mostrarOpcionesMenuFactura(MENU_FACTURA_ADD_LINEA, MENU_FACTURA_ELIMINAR_LINEA, 
							MENU_FACTURA_MOSTRAR_EN_PANTALLA, MENU_FACTURA_GUARDAR_EN_FICHERO,
							MENU_FACTURA_SALIR);
					opcionMenuFactura = elegirOpcion(opcionMenuFactura, sc);
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
				System.out.println("Aun no esta preparado");
				break;
			case MAIN_MENU_VER_ARTICULOS_SALDABLES:
				mostrarArticulosSaludables(almacen);
				break;
			case MAIN_MENU_VER_ARTICULO_MAS_CARO:
				mostrarArticulosPorOrdenDePrecio(almacen, sc);
				break;
			case MAIN_MENU_VER_ARTICULOS_MENOS_STOCK:
				String opcioOrdenLista = elegirOrden(sc);
				almacen.ordenarPorStock(opcioOrdenLista);
				break;
			case MAIN_MENU_SALIR:
				break;
			default:
				System.out.println("Introduce una opcion correcta");
			}
		} while (opcionMainMenu != MAIN_MENU_SALIR);
		
	}

	private void mostrarArticulosPorOrdenDePrecio(Almacen almacen, Scanner sc) {
		String opcionTipoDeOrdenacionLista = elegirOrden(sc);
		for (Articulo articulo : almacen.ordenarPorPrecio(opcionTipoDeOrdenacionLista)) {
			mostrarPorTipoDeArticulo(articulo);
		}
	}

	private void mostrarPorTipoDeArticulo(Articulo articulo) {
		if (articulo instanceof Refresco) {
			((Refresco) articulo).visualizarPropiedades();
		}else if (articulo instanceof Cerveza) {
			((Refresco) articulo).visualizarPropiedades();
		}else if (articulo instanceof Vino) {
			((Vino) articulo).visualizarAticulo();
		}
	}

	private String elegirOrden(Scanner sc) {
		System.out.print("Mostar lista en orden descendete[S/n]");
		String opcionTipoDeOrdenacionLista = sc.nextLine();
		opcionTipoDeOrdenacionLista = opcionTipoDeOrdenacionLista.trim().toLowerCase().equals("n") 
									  ? "ascendente" : "descendente";
		return opcionTipoDeOrdenacionLista;
	}

	private void mostrarArticulosSaludables(Almacen almacen) {
		for (Articulo articulo : almacen.getArticulos()) {
			if (articulo instanceof Refresco) {
				if (((Refresco) articulo).esSaludable()) {
					((Refresco) articulo).visualizarPropiedades();
				}
			}else if (articulo instanceof Cerveza) {
				if (((Refresco) articulo).esSaludable()) {
					((Refresco) articulo).visualizarPropiedades();
				}
			}else if (articulo instanceof Vino) {
				if (((Vino) articulo).esSaludable()) {
					((Vino) articulo).visualizarAticulo();
				}
			}
		}
	}
	
	private int elegirOpcion(int opcionMenu, Scanner sc) {
		
		do {
			System.out.print("Introduce la opcion: ");
			try {
				opcionMenu = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("ERROR!!!");
				opcionMenu = -1;
			}
		} while (opcionMenu > 0);
		
		return opcionMenu;
	}

	private void mostrarOpcionesMenuFactura(int mENU_FACTURA_ADD_LINEA, int mENU_FACTURA_ELIMINAR_LINEA,
			int mENU_FACTURA_MOSTRAR_EN_PANTALLA, int mENU_FACTURA_GUARDAR_EN_FICHERO, int mENU_FACTURA_SALIR) {
		System.out.println("=====================MENU-FACTURA=====================");
		System.out.println(mENU_FACTURA_ADD_LINEA + ".- Agregar linea de factura");
		System.out.println(mENU_FACTURA_ELIMINAR_LINEA + ".- Eliminar linea de factura");
		System.out.println(mENU_FACTURA_MOSTRAR_EN_PANTALLA + ".- Mostrar factura en pantalla");
		System.out.println(mENU_FACTURA_GUARDAR_EN_FICHERO + ".- Guardar factura");
		System.out.println(mENU_FACTURA_SALIR + ".- Salir");
		
	}

	private void mostrarOpcionesMainMenu(int mAIN_MENU_REALIZAR_COMPRA, int mAIN_MENU_REALIZAR_VENTA, int mAIN_MENU_VER_ARTICULOS_SALDABLES,
			int mAIN_MENU_VER_ARTICULO_MAS_CARO, int mAIN_MENU_VER_ARTICULO_MENOS_STOCK, int mAIN_MENU_SALIR) {
		System.out.println("=====================MENU-PRINCPAL=====================");
		System.out.println(mAIN_MENU_REALIZAR_COMPRA + ".- Realizar compra");
		System.out.println(mAIN_MENU_REALIZAR_VENTA + ".- Realizar venta");
		System.out.println(mAIN_MENU_VER_ARTICULOS_SALDABLES + ".- Ver articulos saludables");
		System.out.println(mAIN_MENU_VER_ARTICULO_MAS_CARO + ".- Ordenar articulos por precio");
		System.out.println(mAIN_MENU_VER_ARTICULO_MENOS_STOCK + ".- Ordenar articulos por stock");
		System.out.println(mAIN_MENU_SALIR + ".- Salir");
		
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
