package game.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistributeCountries {
	
	static int c = 0; 
	static Map<Integer,List<Territory>> countriesPerPlayer = new HashMap<>();
	
	public static Map<Integer,List<Territory>> getCountriesPerPlayer(int noOfPlayers, List<Territory> totalTerritories){
		 if(totalTerritories==null|| totalTerritories.isEmpty()){
		 return null;
		 }
		 loadCountriesPerPlayerWithEmptyValues(noOfPlayers);
		 if(noOfPlayers==2){
			 random2(totalTerritories);
			 System.out.println(countriesPerPlayer.get(1).size());
			 System.out.println(countriesPerPlayer.get(2).size());
		 }
		 if(noOfPlayers==3){
			 random3(totalTerritories);
			 System.out.println(countriesPerPlayer.get(1).size());
			 System.out.println(countriesPerPlayer.get(2).size());
			 System.out.println(countriesPerPlayer.get(3).size());
		 }
		 if(noOfPlayers==4){
			 random4(totalTerritories);
		 }
		 return countriesPerPlayer;
	 }

	private static void loadCountriesPerPlayerWithEmptyValues(int noOfPlayers){
		for (int i=1;i<= noOfPlayers;i++){
			countriesPerPlayer.put(i, new ArrayList<>());
		}	
	}
	
	public static void random2(List<Territory> totalTerritories){
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
		
	}
	
	public static void random3(List<Territory> totalTerritories){
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

	}
	
	
public static void random4(List<Territory> totalTerritories){
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
