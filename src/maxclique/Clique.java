/** 
 *  @file    Clique.java
 *  @author  Rafael Marcén Altarriba (650435)
 *  @date    07/01/2017  
 *  @version 1.0 
 */

package maxclique;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Clique {
	
	private Map<Integer, Vertice> clique = new TreeMap<Integer, Vertice>();
	
	/**
	 * Constructor
	 */
	public Clique() {
		
	}
	
	/**
	 * Constructor
	 * @param clique
	 */
	public Clique(Map<Integer, Vertice> clique) {
		this.clique = new TreeMap<Integer, Vertice>(clique);
	}
	
	/**
	 * Devuelve el clique
	 * @return
	 */
	public Map<Integer, Vertice> getClique() {
		return clique;
	}

	/**
	 * Devuelve el tamaño del clique
	 * @return
	 */
	public int getMaxClique() {
		return clique.size();
	}
	
	/**
	 * Genera un clique aleatorio escogiendo un vertice 
	 * del grafo <g> de forma aleatoria
	 * @param g
	 */
	public void generarClique(Grafo g) {
		int vertice = new Random().nextInt((g.getVertices().size()));
		clique.put(vertice, g.getVertice(vertice));
	}
	
	/**
	 * Devuelve true si el clique es de verdad un clique.
	 * False en caso contrario
	 * @return
	 */
	public boolean comprobarClique() {
		boolean clique = true;
		
		for (Vertice v1 : this.clique.values()) {
			for (Vertice v2 : this.clique.values()) {
				if (v1.getIndice() != v2.getIndice()) {
					if (v1.getAristaA(v2) == null || v2.getAristaA(v1) == null) {
						clique = false;
						return clique;
					}
				}
			}
		}
		
		return clique;
	}
}