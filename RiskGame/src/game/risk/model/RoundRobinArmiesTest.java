package game.risk.model ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.risk.util.MapReader;
import game.risk.util.RiskMap;

public class RoundRobinArmiesTest {
	
	public static void main(String[] args) throws Exception {
		MapReader mapreader = new MapReader();
		RiskMap riskMap = mapreader.readMap("World.map");
		HashMap<String, Territory> t = riskMap.getTerritories();
		
		Map<Integer, List<Territory>> listOfAssignedTerritories = DistributeCountries.getCountriesPerPlayer(3, 
				 new ArrayList<Territory>(t.values()));
		HashMap<Integer, List<Territory>> copyOfListofAssignedTerritories = createCopy(listOfAssignedTerritories);
		System.out.println("Round Robin armies Assignment...............");
		for (Map.Entry<Integer, List<Territory>> entry : listOfAssignedTerritories.entrySet())
		{
		    System.out.println("Player"+ entry.getKey()+" Territories value:"+ entry.getValue().size());
		}
		Player p1 = new Player();
		p1.setTerritorAndArmies(new HashMap<>());
		p1.setCurrentNoOfArmies(15);
		Player p2 = new Player();
		p2.setTerritorAndArmies(new HashMap<>());
		p2.setCurrentNoOfArmies(15);
		Player p3 = new Player();
		p3.setTerritorAndArmies(new HashMap<>());
		p3.setCurrentNoOfArmies(15);
		
		List<Player> playerList = new ArrayList<>();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		
		//System.out.println(areTerritoriesLeftToBeAssignedwithArmies(listOfAssignedTerritories));
		int round = 1;
		while(isAmryLeftforAnyPlayer(playerList)){
			reinitializeTerritoriesPerPlayer(listOfAssignedTerritories, copyOfListofAssignedTerritories);
			System.out.println("\nRound "+round);
			for(int i=0;i<playerList.size();i++){
				List<Territory> territoriesOfPlayer_i= listOfAssignedTerritories.get(i+1);
				if(playerList.get(i).getCurrentNoOfArmies()>0){
					Territory territoryPicked = territoriesOfPlayer_i.get(0);
					Map<Territory,Integer> territoriesWithArmies_player_i = playerList.get(i).getTerritorAndArmies();
					int increasedArmy= 0;
					if(territoriesWithArmies_player_i.get(territoryPicked)==null){
						int armiesInTerritoryPicked = 0;
						increasedArmy = armiesInTerritoryPicked+1;
						
					}
					else{
						increasedArmy = territoriesWithArmies_player_i.get(territoryPicked)+1;
					}
					territoriesWithArmies_player_i.put(territoryPicked, increasedArmy);
					playerList.get(i).setCurrentNoOfArmies(playerList.get(i).getCurrentNoOfArmies()-1);
					System.out.println("Player"+(i+1)+" Territory:"+territoryPicked.getName()+" Armies now:"+ increasedArmy);
					listOfAssignedTerritories.get(i+1).remove(0);
					Thread.sleep(300);
				}
			}
			round++;
		}
		
		displayArmiesPerTerritoryPlayers(playerList);
		
	}
	
	private static void displayArmiesPerTerritoryPlayers(List<Player> playerList) {
		int i=1;
		for(Player p : playerList){
			System.out.println("Player "+i);
			for(Map.Entry<Territory, Integer> entry: p.getTerritorAndArmies().entrySet()){
				System.out.println("  Territory:"+entry.getKey().getName()+" Armies:"+entry.getValue());
			}
			i++;
		}
		
	}

	private static HashMap<Integer, List<Territory>> createCopy(
			Map<Integer, List<Territory>> listOfAssignedTerritories) {
		HashMap<Integer,  List<Territory>> copyMap = new HashMap<>();
		for (Map.Entry<Integer, List<Territory>> entry : listOfAssignedTerritories.entrySet())
		{  
			copyMap.put(entry.getKey(), new ArrayList<>(entry.getValue()));
		}
		return copyMap;
	}

	public static boolean isAmryLeftforAnyPlayer(List<Player> playerList){
		for(Player p : playerList){
			if(p.getCurrentNoOfArmies()>0){
				return true;
			}
		}
		return false;

	}
	
	public static void reinitializeTerritoriesPerPlayer(Map<Integer, List<Territory>> listOfAssignedTerritories , Map<Integer, List<Territory>> originalListofAssignedTerritories ){
		for (Map.Entry<Integer, List<Territory>> entry : listOfAssignedTerritories.entrySet())
		{  //System.out.println("Player"+ entry.getKey()+" Territories size:"+ entry.getValue().size());
		    if(entry.getValue().isEmpty()){
		    //	System.out.println("originalListofAssignedTerritories: Player"+entry.getKey()+" Terrirtory count :"+originalListofAssignedTerritories.get(entry.getKey()).size());
		    	listOfAssignedTerritories.put(entry.getKey(), originalListofAssignedTerritories.get(entry.getKey()));
		    }
		}
		

	}
	

}
