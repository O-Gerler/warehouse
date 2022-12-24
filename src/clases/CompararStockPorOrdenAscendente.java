package clases;

import java.util.Comparator;

public class CompararStockPorOrdenAscendente implements Comparator<Articulo>{

	@Override
	public int compare(Articulo arg0, Articulo arg1) {
		return (int)(arg0.getStock() - arg1.getStock());
	}

}
