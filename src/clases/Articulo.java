package clases;

public class Articulo {
	private String name;
	private String code;
	private String mark;
	private int capacidadBotella;
	private double precio;
	private int stock;
	
	public Articulo() {};
	
	public Articulo(String name, String code, String mark, int capacidadBotella, double precio, int stock) {
		this.name = name;
		this.code = code;
		this.mark = mark;
		this.capacidadBotella = capacidadBotella;
		this.precio = precio;
		this.stock = stock;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public int getCapacidadBotella() {
		return capacidadBotella;
	}
	public void setCapacidadBotella(int capacidadBotella) {
		this.capacidadBotella = capacidadBotella;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void incrementarStock() {
		this.stock += 1;
	}
	
	public void disminuirStock(int cantidad) {
		if (cantidad <= this.stock) {
			this.stock -= cantidad;
			System.out.println("El articulo " + this.name + " reducio su cantidad en " + cantidad 
					+ " unidades, unidades restanes: " + this.stock);
		}else {
			System.out.println("No se ha podido disminuir la cantidad seleccionada");
		}
	}
}
