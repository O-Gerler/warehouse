package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class GestorAlmacen {
	public void run() throws IOException {
		Almacen almacen = new Almacen();
		almacen.cargarDatos();
		
		Scanner sc = new Scanner(System.in);
		
		final int MAIN_MENU_REALIZAR_VENTA =1;
		final int MAIN_MENU_REALIZAR_COMPRA =2;
		final int MAIN_MENU_VER_ARTICULOS_SALDABLES =3;
		final int MAIN_MENU_VER_ARTICULO_MAS_CARO =4;
		final int MAIN_MENU_VER_ARTICULOS_CON_MENOR_STOCK =5;
		final int MAIN_MENU_FILTRAR_ARTICULO_MAS_CARO =6;
		final int MAIN_MENU_FILTRAR_ARTICULOS_MENOS_STOCK =7;
		final int MAIN_MENU_SALIR =0;
		
		final int MENU_FACTURA_ADD_LINEA =1;
		final int MENU_FACTURA_ELIMINAR_LINEA =2;
		final int MENU_FACTURA_MOSTRAR_SUGERENCIAS =3;
		final int MENU_FACTURA_MOSTRAR_EN_PANTALLA =4;
		final int MENU_FACTURA_GUARDAR_EN_FICHERO =5;
		final int MENU_FACTURA_SALIR =0;
		
		int opcionMainMenu = -1;
		
		do {
			mostrarOpcionesMainMenu(MAIN_MENU_REALIZAR_COMPRA, MAIN_MENU_REALIZAR_VENTA, MAIN_MENU_VER_ARTICULOS_SALDABLES,
					MAIN_MENU_VER_ARTICULO_MAS_CARO, MAIN_MENU_VER_ARTICULOS_CON_MENOR_STOCK,
					MAIN_MENU_FILTRAR_ARTICULO_MAS_CARO, MAIN_MENU_FILTRAR_ARTICULOS_MENOS_STOCK,MAIN_MENU_SALIR);
			opcionMainMenu = elegirOpcion(opcionMainMenu, sc);
			
			switch(opcionMainMenu) {
			
			case MAIN_MENU_REALIZAR_VENTA:
				Factura factura = crearFacturaConDatos(sc);
				int opcionMenuFactura = -1;
				do {
					mostrarOpcionesMenuFactura(MENU_FACTURA_ADD_LINEA, MENU_FACTURA_ELIMINAR_LINEA, MENU_FACTURA_MOSTRAR_SUGERENCIAS,
							MENU_FACTURA_MOSTRAR_EN_PANTALLA, MENU_FACTURA_GUARDAR_EN_FICHERO,MENU_FACTURA_SALIR);
					opcionMenuFactura = elegirOpcion(opcionMenuFactura, sc);
					switch(opcionMenuFactura) {
					case MENU_FACTURA_ADD_LINEA:
						factura.addLinea(almacen, sc);
						break;
					case MENU_FACTURA_ELIMINAR_LINEA:
						factura.eliminarLinea(opcionMenuFactura, sc);
						break;
					case MENU_FACTURA_MOSTRAR_SUGERENCIAS:
						if (factura.lineaFacturas.size() > 0) {
							factura.mostrarArticulosEquivalentes(almacen);
						}
						break;
					case MENU_FACTURA_MOSTRAR_EN_PANTALLA:
						factura.mostrarEnPantalla();
						break;
					case MENU_FACTURA_GUARDAR_EN_FICHERO:
						factura.guardarEnFichero(almacen);
						break;
					case MENU_FACTURA_SALIR:
						System.out.println("Saliendo al menu pricipal...");
						break;
					default:
						System.out.println("Introduce una opcion correcta");
					}
					
				} while (opcionMenuFactura != MENU_FACTURA_SALIR);
				break;
			case MAIN_MENU_REALIZAR_COMPRA:
				realizarCompra(almacen, sc);
				break;
			case MAIN_MENU_VER_ARTICULOS_SALDABLES:
				mostrarArticulosSaludables(almacen);
				break;
			case MAIN_MENU_VER_ARTICULO_MAS_CARO:
				System.out.println(almacen.mostrarMasCaro()); 
				break;
			case MAIN_MENU_VER_ARTICULOS_CON_MENOR_STOCK:
				mostrarArticulosConStockMenorA(almacen, sc);
				break;
			case MAIN_MENU_FILTRAR_ARTICULO_MAS_CARO:
				mostrarArticulosPorOrdenDePrecio(almacen, sc);
				break;
			case MAIN_MENU_FILTRAR_ARTICULOS_MENOS_STOCK:
				String opcioOrdenLista = elegirOrden(sc);
				almacen.ordenarPorStock(opcioOrdenLista);
				break;
			case MAIN_MENU_SALIR:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Introduce una opcion correcta");
			}
		} while (opcionMainMenu != MAIN_MENU_SALIR);
		guardarDatos(almacen,sc);
	}

	private void mostrarArticulosConStockMenorA(Almacen almacen, Scanner sc) {
		Articulo articuloBuscar = pedirArticulo(almacen, sc);
		if (articuloBuscar != null) {
			almacen.getArticulos()
				.stream()
				.filter(a -> a.getStock() < articuloBuscar.getStock())
				.forEach(System.out::println);
		} else 
			System.out.println("No se encontro el articulo");
	}

	private void guardarDatos(Almacen almacen, Scanner sc) {
		File file = new File("src/datosFichero/datos.txt");
		
		try {
			PrintWriter pw = new PrintWriter(file);
			for (Articulo articulo : almacen.getArticulos()) {
				pw.println(articulo);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No se han podido guardar los datos");
		}
	}

	private void realizarCompra(Almacen almacen, Scanner sc) {
		Articulo articuloBuscar = pedirArticulo(almacen, sc);
		if (articuloBuscar != null) {
			int cantidadIncrementar = pedirCantidadIncrementar(almacen, sc);
			articuloBuscar.incrementarStock(cantidadIncrementar);
		} else 
			System.out.println("Articulo no encontrado");
	}

	private int pedirCantidadIncrementar(Almacen almacen, Scanner sc) {
		int cantidad = -1;
		
		do {
			System.out.print("Introduce la cantidad: ");
			try {
				cantidad = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("ERROR!!!");
				cantidad = -1;
			}
		} while (cantidad < 0);
		
		return cantidad;
	}

	private Articulo pedirArticulo(Almacen almacen, Scanner sc) {
		System.out.print("Introduce el codigo del articulo: ");
		String articuloBuscar = sc.nextLine();
		return almacen.articulo(articuloBuscar) != null ? almacen.articulo(articuloBuscar) : null;
	}

	private void mostrarArticulosPorOrdenDePrecio(Almacen almacen, Scanner sc) {
		String opcionTipoDeOrdenacionLista = elegirOrden(sc);
		ArrayList<Articulo> articulos = almacen.ordenarPorPrecio(opcionTipoDeOrdenacionLista);
		for (Articulo articulo : articulos) {
			mostrarPorTipoDeArticulo(articulo);
		}
	}

	private void mostrarPorTipoDeArticulo(Articulo articulo) {
		if (articulo instanceof Refresco) {
			((Refresco) articulo).visualizarArticulo();
		}else if (articulo instanceof Cerveza) {
			((Cerveza) articulo).visualizarArticulo();
		}else if (articulo instanceof Vino) {
			((Vino) articulo).visualizarArticulo();
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
					((Refresco) articulo).visualizarArticulo();
				}
			}else if (articulo instanceof Cerveza) {
				if (((Cerveza) articulo).esSaludable()) {
					((Cerveza) articulo).visualizarArticulo();
				}
			}else if (articulo instanceof Vino) {
				if (((Vino) articulo).esSaludable()) {
					((Vino) articulo).visualizarArticulo();
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
		} while (opcionMenu < 0);
		
		return opcionMenu;
	}

	private void mostrarOpcionesMenuFactura(int mENU_FACTURA_ADD_LINEA, int mENU_FACTURA_ELIMINAR_LINEA,int mENU_FACTURA_MOSTRAR_EQUIVALENTES,
			int mENU_FACTURA_MOSTRAR_EN_PANTALLA, int mENU_FACTURA_GUARDAR_EN_FICHERO, int mENU_FACTURA_SALIR) {
		System.out.println("=====================MENU-FACTURA=====================");
		System.out.println(mENU_FACTURA_ADD_LINEA + ".- Agregar linea de factura");
		System.out.println(mENU_FACTURA_ELIMINAR_LINEA + ".- Eliminar linea de factura");
		System.out.println(mENU_FACTURA_MOSTRAR_EQUIVALENTES + ".- Mostrar articulos equivalentes");
		System.out.println(mENU_FACTURA_MOSTRAR_EN_PANTALLA + ".- Mostrar factura en pantalla");
		System.out.println(mENU_FACTURA_GUARDAR_EN_FICHERO + ".- Guardar factura");
		System.out.println(mENU_FACTURA_SALIR + ".- Salir");
		
	}

	private void mostrarOpcionesMainMenu(int mAIN_MENU_REALIZAR_COMPRA, int mAIN_MENU_REALIZAR_VENTA, int mAIN_MENU_VER_ARTICULOS_SALDABLES,
			int MAIN_MENU_VER_ARTICULO_MAS_CARO, int MAIN_MENU_VER_ARTICULOS_CON_MENOR_STOCK,
			int mAIN_MENU_FILTRAR_ARTICULO_MAS_CARO, int mAIN_MENU_FILTRAR_ARTICULO_MENOS_STOCK, int mAIN_MENU_SALIR) {
		System.out.println("=====================MENU-PRINCPAL=====================");
		System.out.println(mAIN_MENU_REALIZAR_VENTA + ".- Realizar venta");
		System.out.println(mAIN_MENU_REALIZAR_COMPRA + ".- Realizar compra");
		System.out.println(mAIN_MENU_VER_ARTICULOS_SALDABLES + ".- Ver articulos saludables");
		System.out.println(MAIN_MENU_VER_ARTICULO_MAS_CARO + ".- Mostrar articulo mas caro");
		System.out.println(MAIN_MENU_VER_ARTICULOS_CON_MENOR_STOCK + ".- Mostrar articulos con stock menor a");
		System.out.println(mAIN_MENU_FILTRAR_ARTICULO_MAS_CARO + ".- Ordenar articulos por precio");
		System.out.println(mAIN_MENU_FILTRAR_ARTICULO_MENOS_STOCK + ".- Ordenar articulos por stock");
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
