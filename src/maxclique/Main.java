/** 
 *  @file    Main.java
 *  @author  Rafael Marc�n Altarriba (650435)
 *  @date    07/01/2017  
 *  @version 1.0 
 */

package maxclique;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	
	private static double temperatura = 20000;
    private static double enfriamiento = 0.03;
    private static int repeticiones = 100;
    private static String fichero = "grafos/frb50-23-1.clq";
    private static int tipoFichero = 1; // 0 -> matriz adyacencia, 1 -> grafo ASCII DIMACS


	public static void main(String[] args) {
		
		// java maxclique.Main -f <fichero> -t <tipo> -r <repeticiones> -
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-f")) 
				fichero = args[i + 1];
			else if (args[i].equals("-t"))
				tipoFichero = Integer.parseInt(args[i + 1]);
			else if (args[i].equals("-r"))
				repeticiones = Integer.parseInt(args[i + 1]);			
		}
			
		int[][] array = null;
		System.out.print("Leyendo grafo...");
		if (tipoFichero == 0)
			array = getMatriz(fichero);
		else if (tipoFichero == 1)		
			array = getGrafo(fichero);		
		Grafo grafo = new Grafo(array);
		System.out.println(" HECHO");

		long tiempo;
		SimulatedAnnealing sa;
		System.out.println("Simulated Annealing - " + repeticiones + " repeticiones");
		int[] resultados = new int[repeticiones];
		long[] tiempos = new long[repeticiones];
		for (int i = 0; i < repeticiones; i++) {
			sa = new SimulatedAnnealing(temperatura, enfriamiento);
			tiempo = System.currentTimeMillis();
			
			System.out.print("Ejecucion " + i + "...");
			resultados[i] = sa.solucionar(grafo);
			System.out.println(" HECHO");

	        System.out.println("MAXCLIQUE: " + resultados[i]);
			tiempo = System.currentTimeMillis() - tiempo;
			tiempos[i] = tiempo;
			System.out.println("Tiempo total: " + tiempo + " ms");
		}
		
		double media = 0;
		long mediaTiempo = 0;
		for (int i = 0; i < repeticiones; i++) {
			media += resultados[i];
			mediaTiempo += tiempos[i];
		}
		System.out.println();
		System.out.println("MEDIA MAXCLIQUE: " + media / repeticiones);
		System.out.println("MEDIA TIEMPO: " + mediaTiempo / repeticiones + " ms");
	}
	
	/**
	 * Formato de matriz adyacente
	 * @param fichero
	 * @return
	 */
	public static int[][] getGrafo(String fichero) {
		try(BufferedReader in = new BufferedReader(new FileReader(fichero))) {
			Map<Integer, List<Integer>> grafo = new LinkedHashMap<Integer, List<Integer>>();
			
			int vertices = -1;
			final Pattern p = Pattern.compile("p\\s+edge\\s+(\\d+)\\s+(\\d+)\\s*");
			final Matcher mp = p.matcher("");
			for(String line = in.readLine(); line != null; line = in.readLine()) { 
				mp.reset(line);
				if(mp.matches()) { 
					vertices = Integer.parseInt(mp.group(1));
					break;
				} 
			}
			
			if (vertices < 0) throw new IllegalArgumentException("Bad header line: " + fichero);
			for(int i = 0; i < vertices; i++) { 
				grafo.put(i + 1, new ArrayList<Integer>(4));
			}
			
			final Pattern e = Pattern.compile("e\\s+(\\d+)\\s+(\\d+)\\s*");
			final Matcher me = e.matcher("");
			
			for(String line = in.readLine(); line != null; line = in.readLine()) { 
				me.reset(line);
				if(me.matches()) { 
					final int from = Integer.parseInt(me.group(1)), to = Integer.parseInt(me.group(2));
					if (!grafo.containsKey(from)) throw new IllegalArgumentException("Bad vertex: " + from + " in " + line);
					if (!grafo.containsKey(to)) throw new IllegalArgumentException("Bad vertex: " + to + " in " + line);
					grafo.get(from).add(to); 
					grafo.get(to).add(from);
				} 			
			}
			
			int[][] array = new int[vertices][];
			for (Map.Entry<Integer, List<Integer>> entry : grafo.entrySet()) {
				List<Integer> adjList = entry.getValue();
				int[] adj = new int[adjList.size()];
				for (int i = 0; i < adj.length; i++) {
					adj[i] = adjList.get(i);
				}

				array[entry.getKey() - 1] = adj;
			}
			return array;
		} catch (IOException e) {
			throw new IllegalArgumentException("Bad file: " + fichero);	
		}
	}

	/**
	 * Formato DIMACS ASCII
	 * @param relPath
	 * @return
	 */
	public static int[][] getMatriz(String relPath) {

		Map<Integer, List<Integer>> vertices = new LinkedHashMap<Integer, List<Integer>>();

		FileReader fr;
		try {
			fr = new FileReader(relPath);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				String[] split = line.trim().split("(\\s)+");
				List<Integer> adjList = new ArrayList<Integer>();
				for (int i = 1; i < split.length; i++) {
					adjList.add(Integer.parseInt(split[i]) - 1);
				}
				vertices.put(Integer.parseInt(split[0]) - 1, adjList);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int[][] array = new int[vertices.size()][];
		for (Map.Entry<Integer, List<Integer>> entry : vertices.entrySet()) {
			List<Integer> adjList = entry.getValue();
			int[] adj = new int[adjList.size()];
			for (int i = 0; i < adj.length; i++) {
				adj[i] = adjList.get(i);
			}
			array[entry.getKey()] = adj;
		}
		return array;
	}
}