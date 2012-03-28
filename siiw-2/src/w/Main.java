package w;

public class Main {

	public static double MUTATE_PROB = 0.01;
	public static double CROSS_PROB = 0.2;
	public static int POPULATION_SIZE= 100;
	public static int MAX_GENERATIONS= 100;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		POPULATION_SIZE = Integer.parseInt(args[0]);
		MAX_GENERATIONS = Integer.parseInt(args[1]);
		MUTATE_PROB = Double.parseDouble(args[2]);
		CROSS_PROB = Double.parseDouble(args[3]);
		
		Graph g = GraphReader.read();
			
		Path.setPathSize(g.getSize());
		
		Population pp = new Population();
		Path bestPath = new Path();
		pp.init(g);
		
		double bestDist = pp.getPath(0).getDistance();
		double currDist = 0;
		
		//for(int i = 0; i < Main.POPULATION_SIZE; ++i){
		//	System.out.print(p.getPath(i).getDistance() + " ,");
		//	p.getPath(i).print();
		//}
		
		int generation = 0;
		while (generation < Main.MAX_GENERATIONS){
			
			//selection
			Population p = pp.select();
			
			//crossing
			for(int i = 0; i < Main.POPULATION_SIZE; ++i){
				
				if(Math.random() < Main.CROSS_PROB){
					int p1 =  (int) (Main.POPULATION_SIZE * Math.random());
					int p2 =  (int) (Main.POPULATION_SIZE * Math.random());
					
					Path[] tmpPaths = Cross.OXCross(p.getPath(p1), p.getPath(p2));
					p.setPath(p1, tmpPaths[0]);
					p.setPath(p2, tmpPaths[1]);
				}
				
			}
			
			//mutation
			for(int i = 0; i < Main.POPULATION_SIZE; ++i){
				if(Math.random() < Main.MUTATE_PROB){
					Mutator.Mutate(p.getPath(i));
				}
			}
			
			double sumDist = 0.0;
			
			double currMin = p.getPath(0).getDistance();
			
			for(int i = 0; i < Main.POPULATION_SIZE; ++i){
				currDist = p.getPath(i).getDistance();
				if(currDist < bestDist){
					bestDist = currDist;
					bestPath = p.getPath(i);
				}
				if(currDist < currMin){
					currMin = currDist;
				}
				
				sumDist += p.getPath(i).getDistance();
				
				//System.out.println();
			}
			//System.out.println("-----------------------");
			//System.out.println(sumDist / Main.POPULATION_SIZE + "\t min: " + currMin);
			
			++generation;
		}
		
		System.out.println(bestDist);
		//bestPath.print();
		

		
		
		//Path firstPath = new Path();
		//for(int i = 0; i < g.getSize(); ++i){
		//	firstPath.addCity(g.getCity(i));
		//}
		
		//Path secondPath = new Path();
		//for(int i = g.getSize()-1; i >= 0; --i){
		//	secondPath.addCity(g.getCity(i));
		//}
		
		//Path[] p = Cross.OXCross(firstPath, secondPath);
		
		
		//p[0].print();
		//p[1].print();
		
		//Mutator.Mutate(p[0]).print();
		/*
		for(int i = 0; i < g.getSize(); ++i){
			for(int j = i+1; j < g.getSize(); ++j){
				System.out.print(g.getDistance(i, j) + " ");
			}
			System.out.println();
		}
		*/
	}

}
