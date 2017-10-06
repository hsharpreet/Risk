package game.risk.model;
import java.util.ArrayList;
import java.util.HashMap;

import game.risk.util.MapReader;
import game.risk.util.RiskMap;

public class Randomize {
	static ArrayList list = new ArrayList();
	
	static ArrayList P1 = new ArrayList();
	static ArrayList P2 = new ArrayList();
	static ArrayList P3 = new ArrayList();
	static ArrayList P4 = new ArrayList();
	
	public static void main(String[] args) throws Exception {
		
		MapReader mapreader = new MapReader();
		RiskMap riskMap = mapreader.readMap("World.map");
		HashMap<String, Territory> t = riskMap.getTerritories();
		System.out.println(t.size());
		
		for(String s: t.keySet()){
			list.add(s);
		}
		
		/*for(int i=0; i<t.size(); i++){
			if(!t.get(i).equals(null) && t.get(i) !=null)
			list.add(t.get(i).getName());
		}*/
		System.out.println(list);
		
		random2(list);
		System.out.println("-1-"+P1.size()+"---"+P1);
		System.out.println("-2-"+P2.size()+"---"+P2);
		System.out.println("-3-"+P3.size()+"---"+P3);
		System.out.println("-4-"+P4.size()+"---"+P4);
		/*random3(list);
		
		random4(list);*/
		
	}
	
	static int c = 0;
	public static void random2(ArrayList as){
		
		//for(int i=0; i<as.size()-1;i++){
			int select = (int) (Math.random()*as.size());
			
			if(as.size() != 0){
				if(c %2 ==0){
					P1.add(as.get(select));
					//as.remove(select);
					as.remove(as.get(select));
					c = 1;
				}else{
					P3.add(as.get(select));
					as.remove(as.get(select));
					c = 0;
				}
				random2(as);
			}else{
				
			}
			
		//}
		
		
	}
	
	public static void random3(ArrayList as){
		
		//for(int i=0; i<as.size()-1;i++){
			int select = (int) (Math.random()*as.size());
			
			if(as.size() != 0){
				if(c ==0){
					P1.add(as.get(select));
					//as.remove(select);
					as.remove(as.get(select));
					c = 1;
				}else if(c ==1){
					P2.add(as.get(select));
					as.remove(as.get(select));
					c = 2;
				}else if(c ==2){
					P3.add(as.get(select));
					as.remove(as.get(select));
					c = 0;
				}
				random3(as);
			}else{
				
			}
			
		//}
		
		
	}
	
public static void random4(ArrayList as){
		
		//for(int i=0; i<as.size()-1;i++){
			int select = (int) (Math.random()*as.size());
			
			if(as.size() != 0){
				if(c ==0){
					P1.add(as.get(select));
					//as.remove(select);
					as.remove(as.get(select));
					c = 1;
				}else if(c ==1){
					P2.add(as.get(select));
					as.remove(as.get(select));
					c = 2;
				}else if(c ==2){
					P3.add(as.get(select));
					as.remove(as.get(select));
					c = 3;
				}else if(c ==3){
					P4.add(as.get(select));
					as.remove(as.get(select));
					c = 0;
				}
				random4(as);
			}else{
				
			}
			
		//}
		
		
	}
}
