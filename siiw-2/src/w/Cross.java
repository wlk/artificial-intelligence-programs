package w;

import java.util.HashSet;
import java.util.Set;

public class Cross {
	
	public static Path[] OXCross(Path p1, Path p2){
		//System.out.println("c: " + p1.getDistance() + ", " + p2.getDistance());
		int start = (int) (Math.random() * Path.size);
		int end = start + (int) (Math.random() * (Path.size - start));
		
		//this should prevent too much crossing
		//if(end - start > Path.size/4.0){
		//	end = start + Path.size/4;
		//}
		
		//p1.print();
		//p2.print();
		
		//System.out.println("start: " + start);
		//System.out.println("end: " + end);
		
		Set<Integer> set1 = new HashSet<Integer>();
		Set<Integer> set2 = new HashSet<Integer>();
		
		Path rp1 = new Path();
		Path rp2 = new Path();
		
		
		for(int i = start; i < end+1; ++i){
			set1.add(p1.getCity(i).getId());
			set2.add(p2.getCity(i).getId());
			rp1.addCity(p1.getCity(i));
			rp2.addCity(p2.getCity(i));
		}
		
		for(int i = end+1; i < Path.size; ++i){
			if(!set1.contains(p2.getCity(i).getId())){
				set1.add(p2.getCity(i).getId());
				rp1.addCity(p2.getCity(i));
			}
			if(!set2.contains(p1.getCity(i).getId())){
				set2.add(p1.getCity(i).getId());
				rp2.addCity(p1.getCity(i));
			}
		}
		
		for(int i = 0; i < end+1; ++i){
			if(!set1.contains(p2.getCity(i).getId())){
				set1.add(p2.getCity(i).getId());
				rp1.addCity(p2.getCity(i));
			}
			if(!set2.contains(p1.getCity(i).getId())){
				set2.add(p1.getCity(i).getId());
				rp2.addCity(p1.getCity(i));
			}
		}
		
		//rp1.print();
		//rp2.print();
		//System.out.println("cr: " + rp1.getDistance() + ", " + rp2.getDistance());
		return new Path[]  {rp1, rp2};
		
	}
}
