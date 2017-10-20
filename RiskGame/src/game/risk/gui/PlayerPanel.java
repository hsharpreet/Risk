
package game.risk.gui;

import game.risk.model.Territory;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
 * This class is carries each player information
 * 
 * @author Team
 *
 */
public class PlayerPanel extends javax.swing.JPanel {

	public int infantriesAvailable;
	public int infantriesTotal;
	public List<CurrentGameStatics> currentGameStaticsList;
	public CurrentGameStaticsTableModel currentGameStaticsTableModel;
	public NeighbourListModel neighbourListModel;
	public List<String> neighbours;

	/**
	 * Constructor to Initialize object
	 * 
	 */
	public PlayerPanel() {
		initComponents();
		currentGameStaticsList = new ArrayList<>();
		neighbours = new ArrayList<>();
		currentGameStaticsTableModel = new CurrentGameStaticsTableModel();
		neighbourListModel = new NeighbourListModel();
		jtCountriesAndArmies.setModel(currentGameStaticsTableModel);
		lsNeighbour.setModel(neighbourListModel);

		jtCountriesAndArmies.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override

			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					int index = jtCountriesAndArmies.getSelectedRow();
					if (index >= 0) {
						neighbours = currentGameStaticsList.get(index).territory.getNeighbouringTerritories();
						lsNeighbour.updateUI();
					}
				}
			}
		});

	}

	public class CurrentGameStaticsTableModel extends AbstractTableModel {

		String columnNames[] = { "Territory", "Continent", "Infantries" };

		public String getColumnName(int index) {
			return columnNames[index];
		}

		@Override
		public int getRowCount() {
			return currentGameStaticsList.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
				return currentGameStaticsList.get(rowIndex).territory.getName();
			} else if (columnIndex == 1) {
				return currentGameStaticsList.get(rowIndex).territory.getContinent();
			} else {
				return currentGameStaticsList.get(rowIndex).infantries;
			}
		}

	}

	public class NeighbourListModel extends AbstractListModel {

		@Override
		public int getSize() {
			return neighbours.size();
		}

		@Override
		public Object getElementAt(int index) {
			return neighbours.get(index);
		}

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

		lbPlayer.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
		btPlaceInfantry.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btPlaceInfantryActionPerformed(evt);
			}
		});
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

	private void btPlaceInfantryActionPerformed(java.awt.event.ActionEvent evt) {

	}

	public class CurrentGameStatics {

		public int infantries;
		public Territory territory;

		public CurrentGameStatics(int infantries, Territory territory) {
			this.infantries = infantries;
			this.territory = territory;
		}
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
