package clases;

public class Refresco extends Articulo{
	
	private String sabor;
	private boolean zumo;
	private boolean gaseoso;
	private int cantidadAzucar;

	public Refresco() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Refresco(String name, String code, String mark, int capacidadBotella, double precio, int stock
			, String sabor, boolean zumo, boolean gaseoso, int cantidadAzucar) {
		super(name, code, mark, capacidadBotella, precio, stock);
		this.sabor = sabor;
		this.zumo = zumo;
		this.gaseoso = gaseoso;
		this.cantidadAzucar = cantidadAzucar;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	public boolean isZumo() {
		return zumo;
	}

	public void setZumo(boolean zumo) {
		this.zumo = zumo;
	}

	public boolean isGaseoso() {
		return gaseoso;
	}

	public void setGaseoso(boolean gaseoso) {
		this.gaseoso = gaseoso;
	}

	public int getCantidadAzucar() {
		return cantidadAzucar;
	}

	public void setCantidadAzucar(int cantidadAzucar) {
		this.cantidadAzucar = cantidadAzucar;
	}

	@Override
	public void visualizarAticulo() {
		System.out.println("name = " + super.name + ", code = " + super.code + ", mark = " + super.mark + ", capacidadBotella = " 
				+ super.capacidadBotella + ", precio = " + super.precio + ", stock = " + super.stock + "sabor = " + this.sabor + 
				", zumo = " + this.zumo + ", gaseoso = " + this.gaseoso + ", cantidadAzucar = " + this.cantidadAzucar);		
	}

	@Override
	public void precioTotal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void esSaludable() {
		// TODO Auto-generated method stub
		
	}
	
}
