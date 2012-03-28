package w;

import java.util.Arrays;
import java.util.Collections;

public class Population {
	private Path[] paths;

	public Population() {
		paths = new Path[Main.POPULATION_SIZE];
	}

	public void init(Graph g) {
		for (int i = 0; i < Main.POPULATION_SIZE; ++i) {
			paths[i] = Path.generateRandom(g);
		}
	}

	public Population select() {
		Population newPop = new Population();

		double sum = 0.0;
		for (int i = 0; i < Main.POPULATION_SIZE; ++i) {
			sum += paths[i].getDistance();
		}

		for (int i = 0; i < Main.POPULATION_SIZE; ++i) {
			paths[i].setNormalizedCost(paths[i].getDistance() / sum);
		}

		// sort desc by normalized cost

		Collections.sort(Arrays.asList(paths));

		for (int i = 1; i < Main.POPULATION_SIZE; ++i) {
			paths[i].setNormalizedCost(paths[i].getNormalizedCost()
					+ paths[i - 1].getNormalizedCost());
		}

		//for (int i = 0; i < Main.POPULATION_SIZE; ++i) {
		//	System.out.print(paths[i].getNormalizedCost() + ", ");
		//}
		//System.out.println();
		//for (int i = 0; i < Main.POPULATION_SIZE; ++i) {
		//	System.out.print(paths[i].getDistance() + ", ");
		//}
		//System.out.println();

		
		//System.out.println("rand: " + rand);
		int selected = 0;
		boolean b = false;
		while (selected != Main.POPULATION_SIZE) {
			double rand = 0.0;
			b = false;
			rand = Math.random();
			for (int j = 0; j < Main.POPULATION_SIZE && !b; ++j) {
				
				//System.out.println(paths[j].getNormalizedCost() + "," + rand);
				if (paths[j].getNormalizedCost() > rand) {
					// accepted into population
					newPop.setPath(selected, paths[j]);
					//System.out.println("selected: " + j + "\trand: " + rand + "\tc: " + paths[j].getDistance());
					++selected;
					b = true;
				}
			}
		}
		
		newPop.shuffle();

		return newPop;
	}
	
	private void shuffle(){
		Collections.shuffle((Arrays.asList(paths)));
		//System.out.print("as: ");
		//for (int i = 0; i < Main.POPULATION_SIZE; ++i) {
		//	System.out.print(paths[i].getDistance() + ", ");
		//}
		//System.out.println();
	}

	public void setPath(int i, Path p) {
		paths[i] = p;
	}

	public Path getPath(int i) {
		return paths[i];
	}

}
