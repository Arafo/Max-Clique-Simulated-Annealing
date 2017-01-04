package maxclique;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vertice {
	
	private int indice;
	private Set<Arista> aristas = new HashSet<Arista>();
	
	public Vertice(int indice) {
		super();
		this.indice = indice;
	}
	
	public void addArista(Arista arista) {
		aristas.add(arista);	
	}
	
	public Arista getAristaA(Vertice v) {
		for (Arista arista : aristas) {
			if (arista.contiene(this, v))
				return arista;	
		}
		
		return null;	
	}
	
	public List<Vertice> getVerticesAdyacentes() {
		List<Vertice> vertices = new ArrayList<Vertice>();
		for (Arista a : aristas) {
			for (Vertice v : a.getVertices()) {
				if (v.getIndice() != indice && !vertices.contains(v))
					vertices.add(v);
			}
		}
		return vertices;
	}
	
	public Set<Arista> getAristas() {
		return aristas;
	}
	
	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
}