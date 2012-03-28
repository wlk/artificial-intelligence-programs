package w;

public class Edge {
	private Verticle start, end;
	
	//public Edge
	
	public Edge(Verticle start, Verticle end){
		this.start = start;
		this.end = end;
	}

	public void setEnd(Verticle end) {
		this.end = end;
	}

	public Verticle getEnd() {
		return end;
	}

	public void setStart(Verticle start) {
		this.start = start;
	}

	public Verticle getStart() {
		return start;
	}
}
