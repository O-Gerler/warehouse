package clases;

import interfaces.Alcoholico;

public class Vino extends Articulo implements Alcoholico{

	@Override
	public boolean esFuerte() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double calcularTasa() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void visualizarAticulo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void precioTotal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean esSaludable() {
		// TODO Auto-generated method stub
		return false;
	}

}
