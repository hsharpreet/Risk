package game.risk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;;

public class MapFromScratch extends javax.swing.JPanel{

	public MapFromScratch() {
        initComponents();
    }
	
	private String mapName, authorVal, contName, contVal, territoryName,
			territoryCordX, territoryCordY, territoryContinent;

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getAuthorVal() {
		return authorVal;
	}

	public void setAuthorVal(String authorVal) {
		this.authorVal = authorVal;
	}

	
	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getContVal() {
		return contVal;
	}

	public void setContVal(String contVal) {
		this.contVal = contVal;
	}

	public String getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	public String getTerritoryCordX() {
		return territoryCordX;
	}

	public void setTerritoryCordX(String territoryCordX) {
		this.territoryCordX = territoryCordX;
	}

	public String getTerritoryCordY() {
		return territoryCordY;
	}

	public void setTerritoryCordY(String territoryCordY) {
		this.territoryCordY = territoryCordY;
	}

	public String getTerritoryContinent() {
		return territoryContinent;
	}

	public void setTerritoryContinent(String territoryContinent) {
		this.territoryContinent = territoryContinent;
	}

	
	private void initComponents() {

        lbMapName = new javax.swing.JLabel();
        tfMapName = new javax.swing.JTextField();
        lbAuthorName = new javax.swing.JLabel();
        tfAuthorName = new javax.swing.JTextField();
        lbContinentName = new javax.swing.JLabel();
        tfContinentName = new javax.swing.JTextField();
        lbContinentValue = new javax.swing.JLabel();
        tfContinentValue = new javax.swing.JTextField();
        lbTerritoryName = new javax.swing.JLabel();
        tfTerritoryName = new javax.swing.JTextField();
        lbTerritoryXAxis = new javax.swing.JLabel();
        tfTerritoryXAxis = new javax.swing.JTextField();
        lbTerritoryYAxis = new javax.swing.JLabel();
        tfTerritoryYAxis = new javax.swing.JTextField();
        lbTerritoryContinent = new javax.swing.JLabel();
        tfTerritoryContinent = new javax.swing.JTextField();
        btSubmit = new javax.swing.JButton();
        btGoBack = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setName("MapFromScratch"); // NOI18N

        lbMapName.setText("Map Name");
        lbMapName.setName(""); // NOI18N

        tfMapName.setName("tfMapName"); // NOI18N
        tfMapName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMapNameActionPerformed(evt);
            }
        });

        lbAuthorName.setText("Author Name");

        tfAuthorName.setName("tfAuthorName"); // NOI18N
        tfAuthorName.setOpaque(false);

        lbContinentName.setText("Continent Name");

        lbContinentValue.setText("Continent Value");

        lbTerritoryName.setText("Territory Name");

        tfTerritoryName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTerritoryNameActionPerformed(evt);
            }
        });

        lbTerritoryXAxis.setText("Territory Cord X");

        lbTerritoryYAxis.setText("Territory Cord Y");

        lbTerritoryContinent.setText("Territory's Continent");

        btSubmit.setText("Submit");

        btGoBack.setText("Go Back");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lbMapName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(tfMapName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 282, Short.MAX_VALUE)
                        .addComponent(lbAuthorName)
                        .addGap(26, 26, 26)
                        .addComponent(tfAuthorName, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbContinentName)
                            .addComponent(lbContinentValue)
                            .addComponent(lbTerritoryName)
                            .addComponent(lbTerritoryXAxis)
                            .addComponent(lbTerritoryYAxis)
                            .addComponent(lbTerritoryContinent)
                            .addComponent(btSubmit))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfContinentName)
                                .addComponent(tfContinentValue)
                                .addComponent(tfTerritoryName)
                                .addComponent(tfTerritoryXAxis)
                                .addComponent(tfTerritoryYAxis)
                                .addComponent(tfTerritoryContinent, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                            .addComponent(btGoBack))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMapName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAuthorName)
                    .addComponent(tfAuthorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMapName))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbContinentName)
                    .addComponent(tfContinentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbContinentValue)
                    .addComponent(tfContinentValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTerritoryName)
                    .addComponent(tfTerritoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTerritoryXAxis)
                    .addComponent(tfTerritoryXAxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTerritoryYAxis)
                    .addComponent(tfTerritoryYAxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTerritoryContinent)
                    .addComponent(tfTerritoryContinent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSubmit)
                    .addComponent(btGoBack))
                .addGap(117, 117, 117))
        );
    }// </editor-fold>                        

    private void tfMapNameActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void tfTerritoryNameActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               


    // Variables declaration - do not modify                     
    private javax.swing.JButton btGoBack;
    private javax.swing.JButton btSubmit;
    private javax.swing.JLabel lbAuthorName;
    private javax.swing.JLabel lbContinentName;
    private javax.swing.JLabel lbContinentValue;
    private javax.swing.JLabel lbMapName;
    private javax.swing.JLabel lbTerritoryContinent;
    private javax.swing.JLabel lbTerritoryName;
    private javax.swing.JLabel lbTerritoryXAxis;
    private javax.swing.JLabel lbTerritoryYAxis;
    private javax.swing.JTextField tfAuthorName;
    private javax.swing.JTextField tfContinentName;
    private javax.swing.JTextField tfContinentValue;
    private javax.swing.JTextField tfMapName;
    private javax.swing.JTextField tfTerritoryContinent;
    private javax.swing.JTextField tfTerritoryName;
    private javax.swing.JTextField tfTerritoryXAxis;
    private javax.swing.JTextField tfTerritoryYAxis;
}
