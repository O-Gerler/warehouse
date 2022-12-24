package clases;

import java.util.Comparator;

public class CompararStockPorOrdenDescendente implements Comparator<Articulo>{

	@Override
	public int compare(Articulo arg0, Articulo arg1) {
		return (int)(arg1.getStock() - arg0.getStock());
	}
}
