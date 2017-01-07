/** 
 *  @file    Grafo.java
 *  @author  Rafael Marcén Altarriba (650435)
 *  @date    07/01/2017  
 *  @version 1.0 
 */

package maxclique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Grafo {
	
	private final Map<Integer, Vertice> vertices = new TreeMap<Integer, Vertice>();
	private final List<Arista> aristas = new ArrayList<Arista>();
	
	/**
	 * Constructor
	 * @param array
	 */
	public Grafo(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			Vertice v = getVertice(i);
			for (int edgeTo : array[i]) {
				Vertice v2 = getVertice(edgeTo);
				Arista e;
				if ((e = v2.getAristaA(v)) == null) {
					e = new Arista(v, v2);
					aristas.add(e);
                    v.addArista(e);
                    v2.addArista(e);    
				}	
			}
		}
	}
	
	/**
	 * Añade el vertice <v> al grafo
	 * @param v
	 */
	public void addVertice(Vertice v) {
		vertices.put(v.getIndice(), v);	
	}
	
	/**
	 * Devuelve el vertice cuyo indice es indice <indice>.
	 * Si dicho vertice no existe en el grafo, lo añade y lo
	 * devuelve
	 * @param indice
	 * @return
	 */
	public Vertice getVertice(int indice) {
		Vertice v;
		
		if (( v = vertices.get(indice)) == null ) {
			v = new Vertice(indice);
			addVertice(v);	
		}
		return v;	
	}
	
	/**
	 * Devuelve la lista de vertices del grafo
	 * @return
	 */
	public Map<Integer, Vertice> getVertices() {
		return vertices;
	}
	
	/**
	 * Devuelve la lista de arista del grafo
	 * @return
	 */
	public List<Arista> getAristas() {
		return aristas;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Grafo\n");
		for (Vertice v : vertices.values()) {
			sb.append(v.getIndice() + ":");
			//System.out.print(v.getIndice() + ":");
			for (Arista arista : v.getAristas()) {
				sb.append(" " + arista.getVerticeOpuesto(v).getIndice());
				//System.out.print(" " + arista.getVerticeOpuesto(v).getIndice());	
			}
			sb.append("\n");
			//System.out.println();	
		}
		return sb.toString();
	}
	
	/**
	 * http://www.cs.rpi.edu/research/groups/pb/graphdraw/headpage.html
	 * @param array
	 * @return
	 */
	public String AdjString(int[][] array) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(array.length + "\n");
		for (int[] adj : array) {
			sb.append(adj.length);
			for (int i : adj) {
				sb.append(" " + i);	
			}
			sb.append("\n");		
		}
		return sb.toString();
	}
	
	/**
	 * Formato de Wolfram Alpha
	 * @param array
	 * @return
	 */
	public String GrafoWolfram(int[][] array) {
		StringBuilder sb = new StringBuilder();
		int ind = 0;
		
		sb.append("GraphPlot[{");
		for (int[] adj : array) {
			for (int i : adj) {
				sb.append(ind + " -> " + i + ", ");
			}
			ind++;
		}
		sb.replace(sb.length() - 2, sb.length() - 1, "");
		sb.append("}, VertexLabeling -> True]");
		return sb.toString();
	}
}