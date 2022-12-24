package interfaces;

public interface Alcoholico {
	static final double TASA_BEBIDAS_FUERTES = 64.13;
	static final double TASA_BEBIDAS_SUAZES = 38.43;
	
	public boolean esFuerte();
	public double calcularTasa();
}
