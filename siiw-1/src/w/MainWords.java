package w;

import java.util.ArrayList;

import JaCoP.constraints.*;
import JaCoP.core.*;
import JaCoP.search.DepthFirstSearch;
import JaCoP.search.IndomainMin;
import JaCoP.search.InputOrderSelect;
import JaCoP.search.Search;
import JaCoP.search.SelectChoicePoint;

public class MainWords {

	Store store;
	ArrayList<IntVar> v;

	public static void main(String[] args) {
		MainWords main = new MainWords();
		main.run();
	}

	private void run() {
		
		store = new Store();
		ArrayList<IntVar> vars = new ArrayList<IntVar>();
		ArrayList<IntVar> SumVars = new ArrayList<IntVar>();
		ArrayList<IntVar> SumVars2 = new ArrayList<IntVar>();
		
		vars.add(new IntVar(store, new String("S"), 1000, 9000)); //0
		vars.add(new IntVar(store, new String("E"), 0, 900)); //1
		vars.add(new IntVar(store, new String("N"), 10, 900)); //2
		vars.add(new IntVar(store, new String("D"), 0, 9)); //3
		vars.add(new IntVar(store, new String("M"), 1000, 19999)); //4
		vars.add(new IntVar(store, new String("O"), 100, 9000)); //5
		vars.add(new IntVar(store, new String("R"), 10, 90)); //6
		vars.add(new IntVar(store, new String("Y"), 0, 9)); //7
		
		store.impose(new Alldistinct(vars.toArray(new IntVar[0])));
		
		IntVar sum = new IntVar(store, "suma", 10000, 19999);
		SumVars.add(vars.get(0));
		SumVars.add(vars.get(1));
		SumVars.add(vars.get(2));
		SumVars.add(vars.get(3));
		
		SumVars.add(vars.get(4));
		SumVars.add(vars.get(5));
		SumVars.add(vars.get(6));
		SumVars.add(vars.get(1));
		
		SumVars2.add(vars.get(4));
		SumVars2.add(vars.get(5));
		SumVars2.add(vars.get(2));
		SumVars2.add(vars.get(1));
		SumVars2.add(vars.get(7));
		
		store.impose(new Sum(SumVars, sum));
		store.impose(new Sum(SumVars2, sum));
		
		 /*
		store = new Store();
		ArrayList<IntVar> vars = new ArrayList<IntVar>();
		ArrayList<IntVar> SumVars = new ArrayList<IntVar>();
		ArrayList<IntVar> SumVars2 = new ArrayList<IntVar>();
		
		vars.add(new IntVar(store, new String("S"), 1, 9)); //0
		vars.add(new IntVar(store, new String("E"), 0, 9)); //1
		vars.add(new IntVar(store, new String("N"), 0, 9)); //2
		vars.add(new IntVar(store, new String("D"), 0, 9)); //3
		vars.add(new IntVar(store, new String("M"), 1, 9)); //4
		vars.add(new IntVar(store, new String("O"), 0, 9)); //5
		vars.add(new IntVar(store, new String("R"), 0, 9)); //6
		vars.add(new IntVar(store, new String("Y"), 0, 9)); //7
		
		store.impose(new Alldistinct(vars.toArray(new IntVar[0])));
		
		
		IntVar sum = new IntVar(store, "suma", 10000, 19999);
		IntVar s1000 = new IntVar(store, "S*1000", 1000, 9000);
		IntVar e100 = new IntVar(store, "E*100", 100, 900);
		IntVar n10 = new IntVar(store, "N*10", 10, 90);
		SumVars.add(s1000);
		SumVars.add(e100);
		SumVars.add(n10);
		SumVars.add(vars.get(3));
		Constraint c0 = new XmulCeqZ(vars.get(0), 1000, s1000);
		Constraint c1 = new XmulCeqZ(vars.get(1), 100, e100);
		Constraint c2 = new XmulCeqZ(vars.get(2), 10, n10);
		store.impose(c0);
		store.impose(c1);
		store.impose(c2);
		
		IntVar m1000 = new IntVar(store, "M*1000", 1000, 9000);
		IntVar o100 = new IntVar(store, "O*100", 100, 900);
		IntVar r10 = new IntVar(store, "R*10", 10, 90);
		SumVars.add(m1000);
		SumVars.add(o100);
		SumVars.add(r10);
		SumVars.add(vars.get(1));
		Constraint c3 = new XmulCeqZ(vars.get(4), 1000, m1000);
		Constraint c4 = new XmulCeqZ(vars.get(5), 100, o100);
		Constraint c5 = new XmulCeqZ(vars.get(6), 10, r10);
		store.impose(c3);
		store.impose(c4);
		store.impose(c5);
		
		IntVar m10000 = new IntVar(store, "M*10000", 10000, 19999);
		IntVar o1000 = new IntVar(store, "O*1000", 1000, 9000);
		IntVar n100 = new IntVar(store, "N*100", 100, 900);
		IntVar e10 = new IntVar(store, "E*10", 10, 90);
		SumVars2.add(m10000);
		SumVars2.add(o1000);
		SumVars2.add(n100);
		SumVars2.add(e10);
		SumVars2.add(vars.get(7));
		Constraint c6 = new XmulCeqZ(vars.get(4), 10000, m10000);
		Constraint c7 = new XmulCeqZ(vars.get(5), 1000, o1000);
		Constraint c8 = new XmulCeqZ(vars.get(2), 100, n100);
		Constraint c9 = new XmulCeqZ(vars.get(1), 10, e10);
		store.impose(c6);
		store.impose(c7);
		store.impose(c8);
		store.impose(c9);
		
		store.impose(new Sum(SumVars, sum));
		store.impose(new Sum(SumVars2, sum));
		*/
		
		System.out.println(store.toString());
		Search<IntVar> label = new DepthFirstSearch<IntVar>(); 
		SelectChoicePoint<IntVar> select = new InputOrderSelect<IntVar>(store, vars.toArray(new IntVar[0]), new IndomainMin<IntVar>()); 
		//boolean result = 
			label.labeling(store, select);
		
		
		//System.out.println(store.consistency());
	}

}
