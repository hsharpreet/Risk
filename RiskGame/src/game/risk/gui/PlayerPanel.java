
package game.risk.gui;

import java.io.Serializable;

/**
 * This class is carries each player information
 * 
 * @author Team
 *
 */
public class PlayerPanel extends javax.swing.JPanel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor to Initialize object and it has the fields which saves the
	 * present panel of the player playing
	 * 
	 */
	public PlayerPanel() {
		initComponents();
	}

	/**
	 * Initializing components for each player.
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {
		// Declaration of buttons
		lbPlayer = new javax.swing.JLabel();
		lbTotalArmies = new javax.swing.JLabel();
		lbAvailableArmies = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jtCountriesAndArmies = new javax.swing.JTable();
		jScrollPane2 = new javax.swing.JScrollPane();
		lsNeighbour = new javax.swing.JList<>();
		jLabelNeighbours = new javax.swing.JLabel();
		btPlaceInfantry = new javax.swing.JButton();
		btOk = new javax.swing.JButton();
		lbMessage1 = new javax.swing.JLabel();
		btReinforcement = new javax.swing.JButton();
		btFortification = new javax.swing.JButton();

		lbMessage4 = new javax.swing.JLabel();
		lbMessage2 = new javax.swing.JLabel();
		lbMessage3 = new javax.swing.JLabel();
		lbMessage5 = new javax.swing.JLabel();

		setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		setLayout(null);

		lbPlayer.setFont(new java.awt.Font("Tahoma", 1, 11));
		lbPlayer.setForeground(new java.awt.Color(0, 153, 153));
		lbPlayer.setText("Player : 1");
		add(lbPlayer);
		lbPlayer.setBounds(10, 10, 170, 14);

		lbTotalArmies.setText("Total Armies : 25");
		add(lbTotalArmies);
		lbTotalArmies.setBounds(10, 40, 180, 14);

		lbAvailableArmies.setText("Armies Available now  : 12");
		add(lbAvailableArmies);
		lbAvailableArmies.setBounds(10, 60, 210, 14);

		jtCountriesAndArmies
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane1.setViewportView(jtCountriesAndArmies);

		add(jScrollPane1);
		jScrollPane1.setBounds(10, 90, 230, 190);

		jScrollPane2.setViewportView(lsNeighbour);

		add(jScrollPane2);
		jScrollPane2.setBounds(250, 90, 110, 190);

		jLabelNeighbours.setText("Neighbours ");
		add(jLabelNeighbours);
		jLabelNeighbours.setBounds(250, 70, 110, 20);

		btPlaceInfantry.setText("Place Infantry");

		add(btPlaceInfantry);
		btPlaceInfantry.setBounds(10, 290, 110, 30);

		btOk.setText("OK");
		add(btOk);
		btOk.setBounds(310, 330, 50, 30);

		lbMessage1.setBackground(new java.awt.Color(255, 255, 153));
		lbMessage1.setForeground(new java.awt.Color(204, 0, 0));
		add(lbMessage1);
		lbMessage1.setBounds(10, 330, 280, 30);

		btReinforcement.setText("Reinforcement");
		add(btReinforcement);
		btReinforcement.setBounds(130, 290, 110, 30);

		btFortification.setText("Fortification");
		add(btFortification);
		btFortification.setBounds(250, 290, 110, 30);

		lbMessage4.setBackground(new java.awt.Color(255, 255, 153));
		lbMessage4.setForeground(new java.awt.Color(204, 0, 0));
		lbMessage4.setText("-----");
		add(lbMessage4);
		lbMessage4.setBounds(10, 410, 280, 20);

		lbMessage2.setBackground(new java.awt.Color(255, 255, 153));
		lbMessage2.setForeground(new java.awt.Color(204, 0, 0));
		lbMessage2.setText("-----");
		add(lbMessage2);
		lbMessage2.setBounds(10, 350, 280, 20);

		lbMessage3.setBackground(new java.awt.Color(255, 255, 153));
		lbMessage3.setForeground(new java.awt.Color(204, 0, 0));
		lbMessage3.setText("-----");
		add(lbMessage3);
		lbMessage3.setBounds(10, 370, 280, 20);

		lbMessage5.setBackground(new java.awt.Color(255, 255, 153));
		lbMessage5.setForeground(new java.awt.Color(0, 0, 255));
		lbMessage5.setText("-------------------------");
		add(lbMessage5);
		lbMessage5.setBounds(10, 390, 280, 20);
	}

	// Variables declaration
	public javax.swing.JButton btFortification;
	public javax.swing.JButton btOk;
	public javax.swing.JButton btPlaceInfantry;
	public javax.swing.JButton btReinforcement;
	private javax.swing.JLabel jLabelNeighbours;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	public javax.swing.JTable jtCountriesAndArmies;
	public javax.swing.JLabel lbAvailableArmies;
	public javax.swing.JLabel lbMessage1;
	public javax.swing.JLabel lbMessage2;
	public javax.swing.JLabel lbMessage3;
	public javax.swing.JLabel lbMessage4;
	public javax.swing.JLabel lbMessage5;
	public javax.swing.JLabel lbPlayer;
	public javax.swing.JLabel lbTotalArmies;
	public javax.swing.JList<String> lsNeighbour;

}
