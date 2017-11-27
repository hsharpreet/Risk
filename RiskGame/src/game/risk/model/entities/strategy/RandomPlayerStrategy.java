package game.risk.model.entities.strategy;

public class RandomPlayerStrategy implements PlayerStrategy {

	@Override
	public void reinforcement() {
		System.out.println("Random Player strategy reinforcement");
		
	}

	@Override
	public void attack() {
		System.out.println("Random Player strategy attack");
		
	}

	@Override
	public void fortification() {
		System.out.println("Random Player strategy fortify");
		
	}
}
