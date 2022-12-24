package clases;

import java.util.Comparator;

public class CompararPorOrdenAscendete implements Comparator<Articulo>{

	@Override
	public int compare(Articulo arg0, Articulo arg1) {
		// TODO Auto-generated method stub
		return (int) (arg0.getPrecio() - arg1.getPrecio());
	}

}
