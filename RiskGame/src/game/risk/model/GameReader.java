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
 * Class to create Game Reader
 * 
 * @author Team
 *
 */
public class GameReader {

	String fileName;

	/**
	 * a constructor
	 * 
	 * @param fileName
	 *            name of game file
	 */
	public GameReader(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Method to read the game file
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
	 * The main class
	 * 
	 * @param args
	 *            a String parameter
	 */
	public static void main(String[] args) {
		GameWriter gameWriter = new GameWriter("World_testingReinforcement");

		Player[] playerLoaded = gameWriter.readGame();
		if (playerLoaded != null) {
			for (Player player : playerLoaded) {
				System.out.println(player.getName() + " " + player.getStrategy() + " " + player.isComputer());
				for (CurrentGameStatics cgs : player.currentGameStaticsList) {
					System.out.println(cgs.territory.getContinent() + " " + cgs.territory.getName());
				}

			}
		}
	}
}
