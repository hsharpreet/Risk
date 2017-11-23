package game.risk.model.entities.strategy;

public class AggressivePlayerStrategy  implements PlayerStrategy{

	@Override
	public void reinforcement() {
		System.out.println("Aggressive Player strategy reinforcement");
		
	}

	@Override
	public void attack() {
		System.out.println("Aggressive Player strategy attack");
		
	}

	@Override
	public void fortification() {
		System.out.println("Aggressive Player strategy attack");
		
	}
	
}
