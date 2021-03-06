
package game.risk.gui;

/**
 * Class to create a panel for card exchange
 * 
 * @author Team
 */
public class ExchangeCardPanel extends javax.swing.JPanel {

	/**
	 * Creates new form ExchangeCardPanel
	 */
	public ExchangeCardPanel() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 *
	 */
	@SuppressWarnings("unchecked")

	private void initComponents() {

		btCancel = new javax.swing.JButton();
		btTrade = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jpAvailableCards = new javax.swing.JPanel();

		btCancel.setText("Close");

		btTrade.setText("Trade");

		jpAvailableCards.setPreferredSize(new java.awt.Dimension(300, 400));
		jpAvailableCards.setLayout(new javax.swing.BoxLayout(jpAvailableCards, javax.swing.BoxLayout.PAGE_AXIS));
		jScrollPane1.setViewportView(jpAvailableCards);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jScrollPane1).addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup()
												.addComponent(btCancel, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(btTrade, javax.swing.GroupLayout.PREFERRED_SIZE, 153,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(btTrade, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
								.addComponent(btCancel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
	}

	// Variables declaration
	public javax.swing.JButton btCancel;
	public javax.swing.JButton btTrade;
	private javax.swing.JScrollPane jScrollPane1;
	public javax.swing.JPanel jpAvailableCards;
	// End of variables declaration
}
