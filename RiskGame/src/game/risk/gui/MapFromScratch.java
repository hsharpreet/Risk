package game.risk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;;

public class MapFromScratch {

	private String mapName, authorVal, warnVal, imageVal, wrapVal, scrollVal, contName, contVal, territoryName,
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

	public String getWarnVal() {
		return warnVal;
	}

	public void setWarnVal(String warnVal) {
		this.warnVal = warnVal;
	}

	public String getImageVal() {
		return imageVal;
	}

	public void setImageVal(String imageVal) {
		this.imageVal = imageVal;
	}

	public String getWrapVal() {
		return wrapVal;
	}

	public void setWrapVal(String wrapVal) {
		this.wrapVal = wrapVal;
	}

	public String getScrollVal() {
		return scrollVal;
	}

	public void setScrollVal(String scrollVal) {
		this.scrollVal = scrollVal;
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

	public JPanel getMapFromScratchPanel() {
		JLabel lbMapName, lbPartMap, lbPartContinents, lbPartTerritory, lbAuthor, lbWarn, lbImage, lbWrap, lbScroll,
				lbContName, lbContVal, lbTerritoryName, lbTerritoryCordX, lbTerritoryCordY, lbTerritoryCont;

		JTextField tfMapName, tfAuthorVal, tfWarnVal, tfImageVal, tfWrapVal, tfScrollVal, tfContName, tfContVal,
				tfTerritoryName, tfTerritoryCordX, tfTerritoryCordY, tfTerritoryContinent;

		JButton btSubmit, btCancel;

		lbMapName = new JLabel("Map Name");
		tfMapName = new JTextField();

		lbPartMap = new JLabel("Part 1: Map");
		lbAuthor = new JLabel("Author");
		tfAuthorVal = new JTextField();

		lbWarn = new JLabel("Warn");
		tfWarnVal = new JTextField();

		lbImage = new JLabel("Image");
		tfImageVal = new JTextField();

		lbWrap = new JLabel("Wrap");
		tfWrapVal = new JTextField();

		lbScroll = new JLabel("Scroll");
		tfScrollVal = new JTextField();

		lbPartContinents = new JLabel("Part 2: Continents");
		lbContName = new JLabel("Continent Name");
		tfContName = new JTextField();

		lbContVal = new JLabel("Continent Value");
		tfContVal = new JTextField();

		lbPartTerritory = new JLabel("Part 3: Territorries");
		lbTerritoryName = new JLabel("Territory Name");
		tfTerritoryName = new JTextField();

		lbTerritoryCordX = new JLabel("Territory Cord X");
		tfTerritoryCordX = new JTextField();

		lbTerritoryCordY = new JLabel("Territory Cord Y");
		tfTerritoryCordY = new JTextField();

		lbTerritoryCont = new JLabel("Continent");
		tfTerritoryContinent = new JTextField();

		btSubmit = new JButton("Submit");
		btCancel = new JButton("Cancel");

		JPanel panel = new JPanel();

		panel.add(lbMapName);
		panel.add(tfMapName);

		panel.add(lbPartMap);
		panel.add(new JLabel("MAP"));
		panel.add(lbAuthor);
		panel.add(tfAuthorVal);

		panel.add(lbWarn);
		panel.add(tfWarnVal);

		panel.add(lbImage);
		panel.add(tfImageVal);

		panel.add(lbWrap);
		panel.add(tfWrapVal);

		panel.add(lbScroll);
		panel.add(tfScrollVal);

		panel.add(lbPartContinents);
		panel.add(new JLabel("CONTINETNS"));
		panel.add(lbContName);
		panel.add(tfContName);

		panel.add(lbContVal);
		panel.add(tfContVal);

		panel.add(lbPartTerritory);
		panel.add(new JLabel("TERR"));
		panel.add(lbTerritoryName);
		panel.add(tfTerritoryName);

		panel.add(lbTerritoryCordX);
		panel.add(tfTerritoryCordX);

		panel.add(lbTerritoryCordY);
		panel.add(tfTerritoryCordY);

		panel.add(lbTerritoryCont);
		panel.add(tfTerritoryContinent);

		panel.add(btSubmit);
		panel.add(btCancel);
		panel.setSize(1000, 600);
		
		btSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkEmptyTextFields()){
					
				}
			}

			private boolean checkEmptyTextFields() {
				boolean check = false;
				
				
				
				return check;
			}});
		return panel;
	}
}
/*
 * JLabel lbMapName, lbPartMap, lbPartContinents, lbPartTerritory, lbAuthor,
 * lbWarn, lbImage, lbWrap, lbScroll, lbContName, lbContVal, lbTerritoryName,
 * lbTerritoryCordX, lbTerritoryCordY, lbTerritoryCont;
 * 
 * JTextField tfMapName, tfAuthorVal, tfWarnVal, tfImageVal, tfWrapVal,
 * tfScrollVal, tfTerritoryName, tfTerritoryCordX, tfTerritoryCordY,
 * tfTerritoryContinent;
 * 
 * JButton btAdditionContinent, btAdditionTerritory, btSubmit, btCancel, btNext;
 * 
 * lbMapName = new JLabel("Map Name"); tfMapName = new JTextField();
 * 
 * lbPartMap = new JLabel("Part 1: Map"); lbPartContinents = new
 * JLabel("Part 2: Continents"); lbPartTerritory = new
 * JLabel("Part 3: Territorries");
 * 
 * lbAuthor = new JLabel("Author"); tfAuthorVal = new JTextField();
 * 
 * lbWarn = new JLabel("Warn"); tfWarnVal = new JTextField();
 * 
 * lbImage = new JLabel("Image"); tfImageVal = new JTextField();
 * 
 * lbWrap = new JLabel("Wrap"); tfWrapVal = new JTextField();
 * 
 * lbScroll = new JLabel("Scroll"); tfScrollVal = new JTextField();
 * 
 * tfTerritoryName = new JTextField(); tfTerritoryCordX = new JTextField();
 * tfTerritoryCordY = new JTextField(); tfTerritoryContinent = new JTextField();
 * 
 * btAdditionContinent = new JButton("Add"); btAdditionTerritory = new
 * JButton("Add"); btSubmit = new JButton("Submit"); btCancel = new
 * JButton("Cancel"); btNext = new JButton("Next");
 * 
 * Object[] message = {"Map name: ", tfMapName, "Author :", tfAuthorVal,
 * "Warn :", tfWarnVal, "Image :", tfImageVal, "Wrap :", tfWrapVal, "Scroll :",
 * tfScrollVal };
 * 
 * int option = JOptionPane.showConfirmDialog(mapEditorFrame, message,
 * "Map from Scratch", JOptionPane.OK_CANCEL_OPTION); if (option ==
 * JOptionPane.OK_OPTION) {
 * 
 * }
 */