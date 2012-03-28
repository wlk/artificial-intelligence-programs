package w;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import JaCoP.constraints.XneqY;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class GraphReader {
	
	static Store store;
	static ArrayList<IntVar> v;
	
	public static Graph read() {
		//System.out.println("GraphReader.read");
		Graph g = new Graph();
		String line = "costam";
		String[] tokens;
		String command;
		Scanner in = new Scanner(System.in);
		
		int //validEdgesCount,
		validVerticlesCount;
		//int edgesCount = 0, verticlesCount = 0;
		
		Verticle v1, v2;
		store = new Store();
		v = new ArrayList<IntVar>();
		int firstVerticle, secondVerticle;
		while (!(line.equals("") || line.isEmpty() || !in.hasNext())) {
			//System.out.println("linia");
			try {
				line = in.nextLine();
				tokens = line.split(" ");
				
				command = tokens[0];
				//System.out.println("command=" + command);
				if(!command.equals("c")){// do not skip this line
					if(command.equals("p")){
						validVerticlesCount = Integer.parseInt(tokens[2]);
						//validEdgesCount = Integer.parseInt(tokens[3]);
						//System.out.println(validEdgesCount);
						//System.out.println(validVerticlesCount);
						for(int i = 0; i < validVerticlesCount+1; ++i){
							v.add(new IntVar(store, "v"+i, 1, validVerticlesCount));
						}
					}
					else{//read edge
						if(command.equals("e")){//check for exceptions
							
							//++edgesCount;
							//++verticlesCount;
							firstVerticle = Integer.parseInt(tokens[1]);
							secondVerticle = Integer.parseInt(tokens[2]);
							
							v1 = new Verticle(firstVerticle, v.get(firstVerticle));
							v2 = new Verticle(secondVerticle, v.get(secondVerticle));
							
							//System.out.println(v1.getColor());
							//System.out.println(v2.getId());
							store.impose(new XneqY(v1.getColor(), v2.getColor()));
							// g.addEdge(new Edge(v1, v2));
						} else {
							if (!command.equals("")) {

								throw new Exception("exception reading file");
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println("GraphReader.read.exit");
		return g;
	}
	
	static Store getStore(){
		return store;
	}
	
	static IntVar[] getV(){
		return v.toArray(new IntVar[0]);
	}

}
