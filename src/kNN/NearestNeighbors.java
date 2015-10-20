/*
 * Autor: Gaspar Soto
 * Clase NearestNeighbors 
 * 
 * **/

package kNN;

import java.util.ArrayList;
import java.util.Collections;

public class NearestNeighbors {
	
	int k = 0;
	ArrayList<Punto> tuples = new ArrayList<Punto>();
	double[] distances;
	String[] definedClass;
	
	public NearestNeighbors(int k){
		this.k = k;
	}

	public NearestNeighbors(){
		this.k = calcularK();
	}
	
	//recibe objetos con clase ya establecida
	public void training(Punto p){
		tuples.add(p);
	}
	
	//implementar
	private int calcularK(){
		return 0;
	}
	
	//recibe objeto a predecir
	public String getClass(Punto p){
		setAllDistances(p);
		sortingDistances();
		p.setTarget(getMajority());
		training(p);
		System.out.println("Clase: "+p.getTarget());
		System.out.println("--------------------------------------------\n");
		return p.getTarget();
	}
	
	private String getMajority(){
		String aux ="";
		String clase = "";
		int cont = 0;
		int contAux = 0;
		System.out.println("\n****** LOS K HERMANOS MAS CERCANOS SON ****** ");
		for(int i=0;i<k;i++){
			
			aux=tuples.get(i).getTarget();
			
			for(int j=i;j<k;j++)
				if(aux.equalsIgnoreCase(tuples.get(j).getTarget()))
					contAux++;
			
			
				if(contAux>cont){
					cont = contAux;
					clase = aux;
					contAux = 0;
				}
			
			contAux=0;
			
			System.out.println("distancia al objetivo: " + tuples.get(i).getDistanceToTarget() 
					+ "\t clase: "+ tuples.get(i).getTarget());
		}
		System.out.println();
		return clase;
	}
	
	private void setAllDistances(Punto p){
		System.out.println("****** TODAS LAS DISTANCIAS *******");
		for(int i=0;i<tuples.size();i++){
			tuples.get(i).setDistanceToTarget(p.getDistance(tuples.get(i)));
			System.out.println("distancia alumno "+(i+1)+": "+ + p.getDistance(tuples.get(i)));
		}
	}
	
	private void sortingDistances(){
		Collections.sort(tuples);
	}
		
}
