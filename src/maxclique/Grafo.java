package maxclique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Grafo {
	
	private final Map<Integer, Vertice> vertices = new TreeMap<Integer, Vertice>();
	private final List<Arista> aristas = new ArrayList<Arista>();
	
	public Grafo(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			//System.out.print("Vertice: " + i);
			Vertice v = getVertice(i);
			for (int edgeTo : array[i]) {
				Vertice v2 = getVertice(edgeTo);
				Arista e;
				if ((e = v2.getAristaA(v)) == null) {
					//System.out.print(", Arista: " + edgeTo + "-" + v.getIndice() + " ");
					e = new Arista(v, v2);
					aristas.add(e);
                    v.addArista(e);
                    v2.addArista(e);    
				}	
			}
			//System.out.println();
		}
	}
	
	public void addVertice(Vertice v) {
		vertices.put(v.getIndice(), v);	
	}
	
	public Vertice getVertice(int indice) {
		Vertice v;
		
		if (( v = vertices.get(indice)) == null ) {
			v = new Vertice(indice);
			addVertice(v);	
		}
		return v;	
	}
	
	public Map<Integer, Vertice> getVertices() {
		return vertices;
	}

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
	
	public String GrafoWolfram(int[][] array) {
		StringBuilder sb = new StringBuilder();
		int ind = 0;
		
		sb.append("GraphPlot[{");
		//sb.append(array.length + "\n");
		for (int[] adj : array) {
			//sb.append(i + " -> ");
			for (int i : adj) {
				sb.append(ind + " -> " + i + ", ");
				//sb.append(" " + i);	
			}
			ind++;
			//sb.append("\n");		
		}
		sb.replace(sb.length() - 2, sb.length() - 1, "");
		sb.append("}, VertexLabeling -> True]");
		return sb.toString();
	}
}