package w;

import java.util.ArrayList;

import JaCoP.core.IntVar;
import JaCoP.core.Store;
import JaCoP.search.*;

public class Main {
	
	Graph g;
	Store store;
	ArrayList<IntVar> v;

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	
	private void run(){
		//System.out.println("początek wczytywania");
		g = GraphReader.read();
		//System.out.println("koniec wczytywania");
		Search<IntVar> search = new DepthFirstSearch<IntVar>(); 
        //SelectChoicePoint<IntVar> select = new InputOrderSelect<IntVar>(GraphReader.getStore(), GraphReader.getV(), new IndomainMin<IntVar>()); 
		SelectChoicePoint<IntVar> select = new SimpleSelect<IntVar>(GraphReader.getV(), new LargestDomain<IntVar>(), new IndomainMin<IntVar>());
		//System.out.println(select.toString());
        search.labeling(GraphReader.getStore(), select); 
        
        int max = 0;
        for(int i=0; i<GraphReader.getV().length;i++){
               if(GraphReader.getV()[i].value()>max){
                        max = GraphReader.getV()[i].value();
               }
        }
        
        
        System.out.println("ilość kolorów: " + max);
	}
	
}
