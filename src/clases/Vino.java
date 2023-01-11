package clases;

import interfaces.Alcoholico;

public class Vino extends Articulo implements Alcoholico{
	
	private String color;
	private String origen;
	private int anio;
	private String tipoUva;
	private double gradosAlcohol;
	
	public Vino() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vino(String name, String code, String mark, int capacidadBotella, double precio, int stock,
			String color, String origen, int anio, String tipoUva, double gradosAlcohol) {
		super(name, code, mark, capacidadBotella, precio, stock);
		this.color = color;
		this.origen = origen;
		this.anio = anio;
		this.tipoUva = tipoUva;
		this.gradosAlcohol = gradosAlcohol;
	}

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
		return this.gradosAlcohol > 13.5 ? true : false;
	}

	@Override
	public double calcularTasa() {
		// La tasa en vinos es 0
		return 0;
	}

	@Override
	public void visualizarArticulo() {
		System.out.println("name = " + super.name + ", code = " + super.code + ", mark = " + super.mark + ", capacidadBotella = " 
				+ super.capacidadBotella + ", precio = " + super.precio + ", stock = " + super.stock + 
				", color = " + color + ", origen = " + origen + ", anio = " + anio + ", tipoUva = " + tipoUva
				+ ", gradosAlcohol = " + gradosAlcohol);
	}

	@Override
	public void precioTotal() {
		System.out.println("El precio total es: " + (super.stock * super.precio));
	}

	@Override
	public boolean esSaludable() {
		return this.origen.trim().toLowerCase().equals("navarra") ? true :false;
	}

	@Override
	public String toString() {
		return super.name + ";" + super.code + ";" + super.mark + ";" + super.capacidadBotella + ";" + super.precio + ";" + super.stock 
				+ ";" + color + ";" + origen + ";" + anio + ";" + tipoUva + ";" + gradosAlcohol;
	}

	
}
