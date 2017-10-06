package game.risk.model;

import java.util.HashMap;

public class Player {
	
	private String name;
	private int noOfArmies;
	private HashMap<Territory, Integer> territorAndArmies ;//= new HashMap<String, String>();
	private boolean turn;
	private boolean isComputer;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNoOfArmies() {
		return noOfArmies;
	}
	public void setNoOfArmies(int noOfArmies) {
		this.noOfArmies = noOfArmies;
	}
	
	public HashMap<Territory, Integer> getTerritorAndArmies() {
		return territorAndArmies;
	}
	public void setTerritorAndArmies(HashMap<Territory, Integer> territorAndArmies) {
		this.territorAndArmies = territorAndArmies;
	}
	public boolean isTurn() {
		return turn;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	public boolean isComputer() {
		return isComputer;
	}
	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}
	
	
}
