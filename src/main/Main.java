package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import clases.GestorAlmacen;

public class Main {

	public static void main(String[] args) throws IOException {
		GestorAlmacen gestorAlmacen = new GestorAlmacen();
		gestorAlmacen.run();
	}

}
