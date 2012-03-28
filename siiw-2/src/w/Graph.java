package w;

public class Graph {
	private int size;
	private City[] cities;
	
	public Graph(int size){
		this.size = size;
		cities = new City[this.size];
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void setCity(int i, City c){
		cities[i] = c;
	}
	
	public City getCity(int i){
		return cities[i];
	}
}
