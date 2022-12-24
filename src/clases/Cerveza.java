package clases;

import interfaces.Alcoholico;

public class Cerveza extends Articulo implements Alcoholico{
	
	private String origen;
	private String cereales;
	private double gradosAlcohol;

	public Cerveza(String name, String code, String mark, int capacidadBotella, double precio, int stock,
			String origen, String cereales, double gradosAlcohol) {
		super(name, code, mark, capacidadBotella, precio, stock);
		this.origen = origen;
		this.cereales = cereales;
		this.gradosAlcohol = gradosAlcohol;
	}

	public Cerveza() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		System.out.println("El precio total es: " + (super.stock * super.precio));
		
	}

	@Override
	public boolean esSaludable() {
		if(this.origen.trim().toLowerCase().equals("galicia"))
			return true;
		
		return false;
	}

}
