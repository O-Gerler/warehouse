package main;

import java.io.FileNotFoundException;

import clases.GestorAlmacen;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		GestorAlmacen gestorAlmacen = new GestorAlmacen();
		gestorAlmacen.run();
	}

}
