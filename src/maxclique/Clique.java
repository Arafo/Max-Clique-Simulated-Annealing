package maxclique;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Clique {
	
	private Map<Integer, Vertice> clique = new TreeMap<Integer, Vertice>();

	public Clique() {
		
	}
	
	public Clique(Map<Integer, Vertice> clique) {
		this.clique = new TreeMap<Integer, Vertice>(clique);
	}

	public Map<Integer, Vertice> getClique() {
		return clique;
	}

	public int getMaxClique() {
		return clique.size();
	}

	public void generarClique(Grafo g) {
		/*for (Vertice v : g.getVertices().values()) {
			clique.put(v.getIndice(), v);
		}*/
		int vertice = new Random().nextInt((g.getVertices().size()));
		clique.put(vertice, g.getVertice(vertice));
	}
	
	public boolean comprobarClique() {
		boolean clique = true;
		
		for (Vertice v1 : this.clique.values()) {
			//System.out.print(v1.getIndice() + ": ");
			for (Vertice v2 : this.clique.values()) {
				if (v1.getIndice() != v2.getIndice()) {
					//System.out.print(" " + v2.getIndice());
					if (v1.getAristaA(v2) == null || v2.getAristaA(v1) == null) {
						//System.out.print("\nNO CLIQUE\n\n");
						clique = false;
						return clique;
						//break;
					}
				}
			}
			//System.out.println();
		}
		//System.out.println();
		
		return clique;
	}
}