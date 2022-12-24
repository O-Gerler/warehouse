package clases;

import java.util.Comparator;

public class CompararPorOrdenDescendente implements Comparator<Articulo>{

	@Override
	public int compare(Articulo arg0, Articulo arg1) {
		return (int)(arg1.getPrecio() - arg0.getPrecio());
	}

}
