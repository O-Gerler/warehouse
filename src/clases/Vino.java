package clases;

import interfaces.Alcoholico;

public class Vino extends Articulo implements Alcoholico{
	
	private String color;
	private String origen;
	private int anio;
	private String tipoUva;
	private double gradosAlcohol;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getTipoUva() {
		return tipoUva;
	}

	public void setTipoUva(String tipoUva) {
		this.tipoUva = tipoUva;
	}

	public double getGradosAlcohol() {
		return gradosAlcohol;
	}

	public void setGradosAlcohol(double gradosAlcohol) {
		this.gradosAlcohol = gradosAlcohol;
	}

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
