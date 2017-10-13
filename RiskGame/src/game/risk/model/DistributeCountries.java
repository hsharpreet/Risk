package game.risk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DistributeCountries {
	
	static int c = 0; 
	static Map<Integer,List<Territory>> countriesPerPlayer;
	public static Map<Integer,List<Territory>> getCountriesPerPlayer(int noOfPlayers, List<Territory> totalTerritories){
		 if(totalTerritories==null|| totalTerritories.isEmpty()){
		 return null;
		 }
		 if(noOfPlayers==2){
			 random2(totalTerritories);
		 }
		 if(noOfPlayers==3){
			 random3(totalTerritories);
		 }
		 if(noOfPlayers==4){
			 random4(totalTerritories);
		 }
		 return countriesPerPlayer;
	 }

	public static void random2(List<Territory> totalTerritories){
		
		//for(int i=0; i<as.size()-1;i++){
		    List<Territory> p1territories = new ArrayList<>();
		    List<Territory> p2territories = new ArrayList<>();
		    countriesPerPlayer.put(1, p1territories);
		    countriesPerPlayer.put(2, p2territories);
			int select = (int) (Math.random()*totalTerritories.size());
			if(totalTerritories.size() != 0){
				if(c %2 ==0){
					countriesPerPlayer.get(1).add(totalTerritories.get(select));
					totalTerritories.remove(totalTerritories.get(select));
					c = 1;
				}else{
					countriesPerPlayer.get(2).add(totalTerritories.get(select));
					totalTerritories.remove(totalTerritories.get(select));
					c = 0;
				}
				random2(totalTerritories);
			}else{
				
			}
			
		//}
		
		
	}
	
	public static void random3(List<Territory> totalTerritories){
		 List<Territory> p1territories = new ArrayList<>();
		    List<Territory> p2territories = new ArrayList<>();
		    List<Territory> p3territories = new ArrayList<>();
		    countriesPerPlayer.put(1, p1territories);
		    countriesPerPlayer.put(2, p2territories);
		    countriesPerPlayer.put(3, p3territories);
		
			int select = (int) (Math.random()*totalTerritories.size());
			
			if(totalTerritories.size() != 0){
				if(c ==0){
					countriesPerPlayer.get(1).add(totalTerritories.get(select));
					totalTerritories.remove(totalTerritories.get(select));
					c = 1;
				}else if(c ==1){
					countriesPerPlayer.get(2).add(totalTerritories.get(select));
					totalTerritories.remove(totalTerritories.get(select));
					c = 2;
				}else if(c ==2){
					countriesPerPlayer.get(3).add(totalTerritories.get(select));
					totalTerritories.remove(totalTerritories.get(select));
					c = 0;
				}
				random3(totalTerritories);
			}else{
				
			}
			
		//}
		
		
	}
	
/**
 * @param totalTerritories
 */
public static void random4(List<Territory> totalTerritories){
	 List<Territory> p1territories = new ArrayList<>();
	    List<Territory> p2territories = new ArrayList<>();
	    List<Territory> p3territories = new ArrayList<>();
	    List<Territory> p4territories = new ArrayList<>();
	    countriesPerPlayer.put(1, p1territories);
	    countriesPerPlayer.put(2, p2territories);
	    countriesPerPlayer.put(3, p3territories);
	    countriesPerPlayer.put(4, p4territories);
			int select = (int) (Math.random()*totalTerritories.size());
			
			if(totalTerritories.size() != 0){
				if(c ==0){
					countriesPerPlayer.get(1).add(totalTerritories.get(select));
					totalTerritories.remove(totalTerritories.get(select));
					c = 1;
				}else if(c ==1){
					countriesPerPlayer.get(2).add(totalTerritories.get(select));
					totalTerritories.remove(totalTerritories.get(select));
					c = 2;
				}else if(c ==2){
					countriesPerPlayer.get(3).add(totalTerritories.get(select));
					totalTerritories.remove(totalTerritories.get(select));
					c = 3;
				}else if(c ==3){
					countriesPerPlayer.get(4).add(totalTerritories.get(select));
					totalTerritories.remove(totalTerritories.get(select));
					c = 0;
				}
				random4(totalTerritories);
			}else{
				
			}
			
		
	}
}
