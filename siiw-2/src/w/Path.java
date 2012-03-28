package w;

import java.util.Arrays;
import java.util.Collections;

public class Path implements Comparable<Path>{
	static int size;
	City[] cities;
	private int used;
	private double normalizedCost;
	
	public City getCity(int i){
		return cities[i];
	}
	
	public City[] getAllCities(){
		return cities;
	}
	
	public void setNormalizedCost(double nc){
		normalizedCost = nc;
	}
	
	public double getNormalizedCost(){
		return normalizedCost;
	}
	
	public void print(){
		for(int i = 0; i < cities.length; ++i){
			System.out.print(cities[i].getId() + ", ");
		}
		System.out.println();
	}
	
	public void setAllCities(City[] cities){
		this.cities = cities;
	}
	
	public void setCity(int i, City c){
		cities[i] = c;
	}
	
	static void setPathSize(int size){
		Path.size = size;
	}
	
	Path(){
		cities = new City[Path.size];
		this.used = 0;
	}
	
	public void addCity(City c){
		cities[used] = c;
		++used;
	}
	
	public int getNumCities(){
		return used;
	}
	
	public double getDistance(){
		double sum = 0;
		for(int i = 0; i < cities.length-1; ++i){
			sum += cities[i].getDistance(cities[i+1]);
		}
		
		return sum;
	}
	
	public static Path generateRandom(Graph g){
		Path p = new Path();
		
		for(int i = 0; i < Path.size; ++i){
			p.addCity(g.getCity(i));
		}
		
		Collections.shuffle(Arrays.asList(p.getAllCities()));
		
		return p;
	}

	@Override
	public int compareTo(Path o) {
		if(getNormalizedCost() > o.getNormalizedCost()){
		//if(getDistance() > o.getDistance()){
			return 1;
		}
		else{
			return -1;
		}
	}

}
