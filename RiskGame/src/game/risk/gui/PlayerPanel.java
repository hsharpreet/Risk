
package game.risk.gui;

/**
 * This class is carries each player information
 * 
 * @author Team
 *
 */
public class PlayerPanel extends javax.swing.JPanel {

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
		lbMessage = new javax.swing.JLabel();
		btReinforcement = new javax.swing.JButton();
		btFortification = new javax.swing.JButton();

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

		lbMessage.setBackground(new java.awt.Color(255, 255, 153));
		lbMessage.setForeground(new java.awt.Color(204, 0, 0));
		add(lbMessage);
		lbMessage.setBounds(10, 330, 280, 30);

		btReinforcement.setText("Reinforcement");
		add(btReinforcement);
		btReinforcement.setBounds(130, 290, 110, 30);

		btFortification.setText("Fortification");
		add(btFortification);
		btFortification.setBounds(250, 290, 110, 30);
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
	public javax.swing.JLabel lbMessage;
	public javax.swing.JLabel lbPlayer;
	public javax.swing.JLabel lbTotalArmies;
	public javax.swing.JList<String> lsNeighbour;

}
