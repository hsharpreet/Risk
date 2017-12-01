package game.risk.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import game.risk.gui.RiskGame;
import game.risk.model.entities.CurrentGameStatics;
import game.risk.model.entities.Player;
import game.risk.model.entities.RiskMap;
import game.risk.model.entities.Territory;
import game.risk.model.entities.strategy.AggressivePlayerStrategy;
import game.risk.model.entities.strategy.HumanStrategy;

/**
 * Class to create Game writer
 *
 * 
 * @author Team
 *
 */
public class GameWriter {

	String fileName;

	/**
	 * A constructor
	 * 
	 * @param mapFileName
	 *            The name of the map file
	 */
	public GameWriter(String mapFileName) {
		fileName = mapFileName.split(".map")[0]+ "_Game_Status.ser";
	}

	/**
	 * Method to create a method to save the game.
	 * 
	 * @param players
	 *            an array of player class
	 */
	public void saveGame(Player[] players) {
		try {
			OutputStream file = new FileOutputStream(fileName);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			// serialize players
			try {
				output.writeObject(players);
			} finally {
				output.close();
				buffer.close();
				file.close();
			}
		}

		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * Method to read the game
	 * 
	 * @return an array of player class
	 */
	public Player[] readGame() {
		Player[] loadedPlayers = null;
		try {
			InputStream file = new FileInputStream(fileName);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			// deserialize players
			try {
				loadedPlayers = (Player[]) input.readObject();
			} finally {
				input.close();
				buffer.close();
				file.close();
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return loadedPlayers;
	}

	/**
	 * The Main method
	 * 
	 * @param args
	 *            a String parameter
	 */
	public static void main(String[] args) {
		Player players[];
		RiskMap mapDetails = MapReader.readMapFile("World_testingReinforcement.map");
		Territory t1 = new Territory();
		t1.setName("test1");
		t1.setContinent("hello");

		Territory t2 = new Territory();
		t2.setName("test2");
		t2.setContinent("hello");

		Territory t3 = new Territory();
		t3.setName("Peru");
		t3.setContinent("South America");

		Territory t4 = new Territory();
		t4.setName("Brazil");
		t4.setContinent("South America");

		Territory t5 = new Territory();
		t5.setName("Argentina");
		t5.setContinent("South America");

		CurrentGameStatics gamestat1 = new CurrentGameStatics(1, t1);
		CurrentGameStatics gamestat2 = new CurrentGameStatics(2, t2);
		CurrentGameStatics gamestat3 = new CurrentGameStatics(3, t3);
		CurrentGameStatics gamestat4 = new CurrentGameStatics(2, t4);
		CurrentGameStatics gamestat5 = new CurrentGameStatics(2, t5);

		players = new Player[2];
		players[0] = new Player(new RiskGame(), 0, players, mapDetails);
		players[0].setComputer(false);
		players[0].setStrategy(new HumanStrategy());
		players[0].setName("Human");
		players[1] = new Player(new RiskGame(), 1, players, mapDetails);
		players[1].setComputer(true);
		players[1].setStrategy(new AggressivePlayerStrategy());
		players[1].setName("Aggressive");
		players[0].currentGameStaticsList = new ArrayList<>();
		players[1].currentGameStaticsList = new ArrayList<>();
		players[0].currentGameStaticsList.add(gamestat1);
		players[0].currentGameStaticsList.add(gamestat2);
		players[0].currentGameStaticsList.add(gamestat3);
		// players[0].currentGameStaticsList.add(gamestat4);
		players[1].currentGameStaticsList.add(gamestat5);

		GameWriter gameWriter = new GameWriter("World_testingReinforcement");
		gameWriter.saveGame(players);

	}
}
