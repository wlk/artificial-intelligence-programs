package w;

import java.util.Scanner;

public class GraphReader {

	public static Graph read() {
		int size = 0;
		// System.out.println("GraphReader.read");
		String line = "costam";
		String[] tokens;
		Scanner in = new Scanner(System.in);

		// first 6 lines of meta information
		for (int i = 0; i < 6; ++i) {
			line = in.nextLine();
			if (i == 3) {
				tokens = line.split(" ");
				size = Integer.parseInt(tokens[1]);
			}
		}

		Graph g = new Graph(size);

		//int id;
		double x, y;
		City c;
		
		
		//while (!(line.equals("EOF") || line.equals("") || line.isEmpty() || !in.hasNext())) {
		for(int i = 0; i < size; ++i){
			// System.out.println("linia");
			line = in.nextLine();
			line = line.trim();
			tokens = line.split("\\s+");
			
			//System.out.println(tokens[0] + ", " + tokens[1] + ", " + tokens[2]);
			
			//id = Integer.parseInt(tokens[0]);
			x = Double.parseDouble(tokens[1]);
			y = Double.parseDouble(tokens[2]);
			
			c = new City(i, x, y);
			g.setCity(c.getId(), c);
			
		}

		// System.out.println("GraphReader.read.exit");
		return g;
	}

}
