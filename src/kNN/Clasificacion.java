/**
 * Autor: Gaspar Soto
 * Clase principal solo para prueba
 * 
 * 
 * 
 * 
 * */

package kNN;

public class Clasificacion {
	public static void main(String[]arg){
		String[] etiquetas = {"peso","edad","IMC","padres","come fruta?"};
		
		
		double[] valores =   {39,10,21,14,1};
		double[] valores2 =  {23,20,32,15,3};
		double[] valores3 =  {14,9,12,56,2};
		double[] valores4 =  {22,18,11,23,5};
		double[] valores5 =  {35,13,17,23,6};
		double[] valores6 =  {38,11,14,43,7};
		double[] valores7 =  {49,10,21,14,1};
		double[] valores8 =  {73,20,32,85,3};
		double[] valores9 =  {24,9,62,56,2};
		double[] valores10 = {28,18,61,43,2};
		double[] valores11 = {39,13,87,53,3};
		double[] valores12 = {18,51,34,23,17};
		
		Punto alumno1 = new Punto(etiquetas,valores,"salud","buena");
		Punto alumno2 = new Punto(etiquetas,valores2,"salud","buena");
		Punto alumno3 = new Punto(etiquetas,valores3,"salud","regular");
		Punto alumno4 = new Punto(etiquetas,valores4,"salud","mala");
		Punto alumno5 = new Punto(etiquetas,valores5,"salud","mala");
		Punto alumno6 = new Punto(etiquetas,valores6,"salud","buena");
		Punto alumno7 = new Punto(etiquetas,valores7,"salud","regular");
		Punto alumno8 = new Punto(etiquetas,valores8,"salud","buena");
		Punto alumno9 = new Punto(etiquetas,valores9,"salud","regular");
		Punto alumno10 = new Punto(etiquetas,valores10,"salud","mala");
		Punto alumno11 = new Punto(etiquetas,valores11,"salud","regular");
		Punto alumno12 = new Punto(etiquetas,valores12,"salud","buena");
		
		//se define la k
		NearestNeighbors n = new NearestNeighbors(7);
		
		//entrenamiento
		n.training(alumno1);
		n.training(alumno2);
		n.training(alumno3);
		n.training(alumno4);
		n.training(alumno5);
		n.training(alumno6);
		n.training(alumno7);
		n.training(alumno8);
		n.training(alumno9);
		n.training(alumno10);
		n.training(alumno11);
		n.training(alumno12);
		
		
		//alumno a analizar
		double[] valoresAnalizados = {52,121,13,12,3};
		Punto alumnoAnalizado = new Punto(etiquetas,valoresAnalizados);
		
		System.out.println("\nPor lo tanto, la salud del alumno es: "+ n.getClass(alumnoAnalizado));
		
		
	}
}
