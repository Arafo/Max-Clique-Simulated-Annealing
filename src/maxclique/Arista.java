/** 
 *  @file    Arista.java
 *  @author  Rafael Marcén Altarriba (650435)
 *  @date    07/01/2017  
 *  @version 1.0 
 */

package maxclique;

import java.util.ArrayList;
import java.util.List;

public class Arista {
		
	private final List<Vertice> ends = new ArrayList<Vertice>();
	
	/**
	 * Constructor
	 * @param origen
	 * @param destino
	 */
	public Arista(Vertice origen, Vertice destino) {
		super();		
		ends.add(origen);
		ends.add(destino);
	}
	
	/**
	 * Devuelve true si los vertices correspondientes a los 
	 * vertices <v1> y <v2> corresponden con los vertices de
	 * la arista
	 * @param v1
	 * @param v2
	 * @return
	 */
	public boolean contiene(Vertice v1, Vertice v2) {
		return ends.contains(v1) && ends.contains(v2);
    }
	
	/**
	 * Devuelve el vertice opuesto al vertice <v>
	 * @param v
	 * @return
	 */
	public Vertice getVerticeOpuesto(Vertice v) {
		if (!ends.contains(v)) {
			throw new IllegalArgumentException("Vertice " + v.getIndice());
        }
		return ends.get(1 - ends.indexOf(v));	
	}
	
	/**
	 * Reemplaza el vertice <oldV> por el vertice <newV>
	 * @param oldV
	 * @param newV
	 */
	public void reemplazarVertice(Vertice oldV, Vertice newV) {
		if (!ends.contains(oldV)) {
			throw new IllegalArgumentException("Vertice " + oldV.getIndice());	
		}
		
        ends.remove(oldV);
        ends.add(newV);    
	}

	/**
	 * Devuelve la lista de vertices de la arista
	 * @return
	 */
	public List<Vertice> getVertices() {
		return ends;
	}
}