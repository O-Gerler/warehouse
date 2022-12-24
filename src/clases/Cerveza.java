package clases;

import interfaces.Alcoholico;

public class Cerveza extends Articulo implements Alcoholico{
	
	private String origen;
	private String cereales;
	private double gradosAlcohol;

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getCereales() {
		return cereales;
	}

	public void setCereales(String cereales) {
		this.cereales = cereales;
	}

	public double getGradosAlcohol() {
		return gradosAlcohol;
	}

	public void setGradosAlcohol(double gradosAlcohol) {
		this.gradosAlcohol = gradosAlcohol;
	}

	@Override
	public boolean esFuerte() {
		if (this.gradosAlcohol > 7) 
			return true;
		
		return false;
	}

	@Override
	public double calcularTasa() {

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
		if(this.origen.trim().toLowerCase().equals("galicia"))
			return true;
		
		return false;
	}

}
