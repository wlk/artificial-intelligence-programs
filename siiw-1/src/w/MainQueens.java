package w;

import java.util.ArrayList;

import JaCoP.core.IntVar;
import JaCoP.core.Store;
import JaCoP.search.*;

public class MainQueens {
	
	Graph g;
	Store store;
	ArrayList<IntVar> v;

	public static void main(String[] args) {
		MainQueens main = new MainQueens();
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
        //System.out.println("ilość kolorów: " + search.get);
        
        String a[][] = new String[16][16];
        int max = 0;
        for(int i=1; i<GraphReader.getV().length+1;i++){
               if(GraphReader.getV()[i-1].value()>max){
                        max = GraphReader.getV()[i-1].value();
               }
        }
        
        for(int i=0; i<5;i++){
        	for(int j=0; j<5;j++){
        		a[i][j] = GraphReader.getV()[(i+1)*(j+1)].value() == 1 ? "X" : "O";
        	}
        }
        
        for(int i=0; i<5;i++){
        	for(int j=0; j<5;j++){
        		System.out.print(a[i][j]);
        	}
        	System.out.println("");
        }
        //System.out.println(max);
        
	}
	
}
