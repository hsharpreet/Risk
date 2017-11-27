package game.risk.model.entities.strategy;

import game.risk.model.entities.Player;

public class AggressivePlayerStrategy  implements PlayerStrategy{

	@Override
	public int placeInfantory(int i, Player player, int army) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int reinforcement() {
		System.out.println("Aggressive Player strategy reinforcement");
		return 0;
	}

	@Override
	public int attack() {
		System.out.println("Aggressive Player strategy attack");
		return 0;
	}

	@Override
	public int fortification() {
		System.out.println("Aggressive Player strategy attack");
		return 0;
	}
	
}