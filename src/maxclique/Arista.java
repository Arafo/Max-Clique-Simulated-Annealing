package maxclique;

import java.util.ArrayList;
import java.util.List;

public class Arista {
		
	private final List<Vertice> ends = new ArrayList<Vertice>();
	
	public Arista(Vertice origen, Vertice destino) {
		super();		
		ends.add(origen);
		ends.add(destino);
	}
	
	public boolean contiene(Vertice v1, Vertice v2) {
		return ends.contains(v1) && ends.contains(v2);
    }
	
	public Vertice getVerticeOpuesto(Vertice v) {
		if (!ends.contains(v)) {
			throw new IllegalArgumentException("Vertice " + v.getIndice());
        }
		return ends.get(1 - ends.indexOf(v));	
	}
	
	public void reemplazarVertice(Vertice oldV, Vertice newV) {
		if (!ends.contains(oldV)) {
			throw new IllegalArgumentException("Vertice " + oldV.getIndice());	
		}
		
        ends.remove(oldV);
        ends.add(newV);    
	}

	public List<Vertice> getVertices() {
		return ends;
	}
}