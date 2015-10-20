/**
 * Autor : Gaspar Soto 
 * Clase Punto para usar en algoritmo kNN
 * implementa la clase Comparable
 * */


package kNN;

public class Punto implements Comparable<Punto>{

	private double[] features; 
	private String[] labels;
	private String target;
	private String labelTarget;
	private double distanceToTarget;
	
	public Punto(String[] l,double[] f){
		this.features = f;
		this.labels = l;
		this.labelTarget = "";
	}
	
	
	
	public Punto(String[] l,double[] f, String lt, String t){
		this.features = f;
		this.labels = l;
		this.labelTarget = lt;
		this.target = t;
	}
	
	
	public double getDistance(Punto a){
		double sum = 0;
		double[] featuresA = a.getFeatures();
		for(int i=0;i<amountOfFeatures();i++)
			sum+=Math.pow(this.features[i]-featuresA[i],2);
		return Math.sqrt(sum);
	}
	
	public double[] getFeatures(){
		return features;
	}

	public String[] getLabels(){
		return labels;
	}

	public String getLabelInPosition(int i){
		return labels[i];
	}

	public double getFeatureInPosition(int i){
		return features[i];
	}
	
	public int amountOfFeatures(){
		return features.length;
	}
	
	public String getTarget(){
		return this.target;
	}
	
	public void setTarget(String t){
		this.target = t;
	}
	
	public String getLabelTarget(){
		return this.target;
	}
	
	public void setLabelTarget(String t){
		this.target = t;
	}
	
	public void setDistanceToTarget(double d){
		this.distanceToTarget = d;
	}
	
	public double getDistanceToTarget(){
		return this.distanceToTarget;
	}

	public int compareTo(Punto o) {
		 return new Double(this.distanceToTarget).compareTo( o.distanceToTarget);
	}
	
	
}