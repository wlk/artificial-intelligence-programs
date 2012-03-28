package w;

public class City {
	private double x, y;
	private int id;

	City(int id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public double getDistance(City c) {
		return Math.sqrt(Math.pow(Math.abs(c.x - this.x), 2.0)
				+ Math.pow(Math.abs(c.y - this.y), 2.0));
	}
	
	public int getId(){
		return this.id;
	}
}
