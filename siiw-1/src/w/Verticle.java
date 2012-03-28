package w;

import JaCoP.core.IntVar;

public class Verticle {
	private IntVar color;
	private int id;
	
	public Verticle(int id, IntVar color){
		this.color = color;
		this.id = id;
	}

	public void setColor(IntVar color) {
		this.color = color;
	}

	public IntVar getColor() {
		return this.color;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
}
