package maxclique;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {
	
	private double temperatura;
    private double enfriamiento = 0.03;
    //private double ka = 1.38064852e-23;

	public SimulatedAnnealing(double temperatura, double enfriamiento) {
		this.temperatura = temperatura;
		this.enfriamiento = enfriamiento;
	}
	
	public int solucionar(Grafo g) {
		        
		Clique actual = new Clique();
		actual.generarClique(g);
		Clique mejor = new Clique();
		
		Random rand = new Random();
		
		// Lista de vertices con el numero de aristas adyacentes
		List<Tupla> verticesOrdenados = new ArrayList<Tupla>();
		for (Vertice v : g.getVertices().values()) {
			List<Vertice> adyacentes = v.getVerticesAdyacentes();
			verticesOrdenados.add(new Tupla(v, adyacentes.size()));
		}
		
		// Ordenar por el número de vertices adyacentes
		Collections.sort(verticesOrdenados, new Comparator<Tupla>() {
	        @Override
	        public int compare(Tupla t2, Tupla t1) {
	        	if (t1.adyacentes > t2.adyacentes)
	        		return 1;
	        	else if (t1.adyacentes < t2.adyacentes)
	        		return -1;
	        	else
	        		return 0;
	        }
	    });
		
		while (temperatura > 1) {
			// Heuristica
			
			Clique nuevo = new Clique(actual.getClique());
			
			Vertice mejorVertice = null;
			if (verticesOrdenados.size() > 0)
				mejorVertice = verticesOrdenados.get(0).vertice;	
			

			if (mejorVertice != null) {
				//System.out.println(mejorVertice);
				nuevo.getClique().put(mejorVertice.getIndice(), mejorVertice);
				verticesOrdenados.remove(0);
			}
			
			int energiaVecino = -1;
            if (nuevo.comprobarClique())
            	energiaVecino = nuevo.getMaxClique();
			
            //int energiaVecino = nuevo.getMaxClique();
			int energiaActual = actual.getMaxClique();
			
			if (probabilidadAceptar(energiaActual, energiaVecino, temperatura) > rand.nextDouble()) {
                actual = new Clique(nuevo.getClique());
            }
			else {
				if (mejorVertice != null)
					actual.getClique().remove(mejorVertice.getIndice());
			}
			
			if (energiaVecino > 0 && actual.getClique().size() > mejor.getClique().size()) {
                mejor = new Clique(actual.getClique());
            }
			
			temperatura *= 1 - enfriamiento;
		}
		
        return mejor.getClique().size();
	}
	
	public double probabilidadAceptar(int energia, int nuevaEnergia, double temperatura) {
        if (nuevaEnergia > energia) {
            return 1.0;
        }
        //System.out.println(Math.exp(-((energia - nuevaEnergia) / temperatura)));
        return (energia - nuevaEnergia) / temperatura;
    }
}

class Tupla {
	int adyacentes;
	Vertice vertice;
	
	public Tupla(Vertice v, int size) {
		this.vertice = v;
		this.adyacentes = size;
	}
}