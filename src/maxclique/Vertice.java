/** 
 *  @file    Vertice.java
 *  @author  Rafael Marcén Altarriba (650435)
 *  @date    07/01/2017  
 *  @version 1.0 
 */

package maxclique;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vertice {
	
	private int indice;
	private Set<Arista> aristas = new HashSet<Arista>();
	
	/**
	 * Constructor
	 * @param indice
	 */
	public Vertice(int indice) {
		super();
		this.indice = indice;
	}
	
	/**
	 * Añade la arista <arista> al conjunto de aristas
	 * @param arista
	 */
	public void addArista(Arista arista) {
		aristas.add(arista);	
	}
	
	/**
	 * Devuelve la arista opuesta al vertice <v>
	 * @param v
	 * @return
	 */
	public Arista getAristaA(Vertice v) {
		for (Arista arista : aristas) {
			if (arista.contiene(this, v))
				return arista;	
		}
		
		return null;	
	}
	
	/**
	 * Devuelve la lista de vertices adyacentes al 
	 * vertice
	 * @return
	 */
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
	
	/**
	 * Devuelve el conjunto de aristas del vertice
	 * @return
	 */
	public Set<Arista> getAristas() {
		return aristas;
	}
	
	/**
	 * Devuelve el indice del vertice
	 * @return
	 */
	public int getIndice() {
		return indice;
	}

	/**
	 * Reemplaza el indice del vertice por <indice>
	 * @param indice
	 */
	public void setIndice(int indice) {
		this.indice = indice;
	}
}