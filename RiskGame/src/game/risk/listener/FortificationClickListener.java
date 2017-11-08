/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.risk.listener;

import game.risk.model.Player;
import game.risk.util.CustomLogRecord;
import game.risk.util.LoggerUtility;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import javax.swing.JOptionPane;

public class FortificationClickListener implements ActionListener
{

    int i;
    Player player[];

    public FortificationClickListener(int i, Player player[])
    {
        this.i = i;
        this.player = player;
    }

    public void actionPerformed(ActionEvent e)
    {
        int tableIndex = player[i].getPlayerPanel().jtCountriesAndArmies.getSelectedRow();
        int listIndex = player[i].getPlayerPanel().lsNeighbour.getSelectedIndex();
        if (tableIndex == -1)
        {
            JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "Select source territory first");
        } else if (listIndex == -1)
        {
            JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "Select destination territory first");
        } else
        {
            String destinationTerritory = player[i].getPlayerPanel().lsNeighbour.getSelectedValue();
            boolean isDestinationMyOwnCountry = false;
            for (int j = 0; j < player[i].currentGameStaticsList.size(); j++)
            {
                if (player[i].currentGameStaticsList.get(j).territory.getName().equals(destinationTerritory))
                {
                    isDestinationMyOwnCountry = true;
                    break;
                }
            }
            if (isDestinationMyOwnCountry)
            {
                if (player[i].currentGameStaticsList.get(tableIndex).infantries > 1)
                {

                    player[i].currentGameStaticsList.get(tableIndex).infantries--;

                    for (int j = 0; j < player[i].currentGameStaticsList.size(); j++)
                    {
                        if (player[i].currentGameStaticsList.get(j).territory.getName().equals(destinationTerritory))
                        {
                            player[i].currentGameStaticsList.get(j).infantries++;
                            player[i].setMessage("Fortification Phase\r\nPlayer - " + (i + 1) 
                                    + " has transfered 1 infantry from "+player[i].currentGameStaticsList.get(tableIndex).territory.getName()
                                    + " to "+destinationTerritory);
                            player[i].notifyObservers();
                            
                            CustomLogRecord logRecord = new CustomLogRecord(Level.INFO, "Player - " + (i + 1) 
                                    + " has transfered 1 infantry from "+player[i].currentGameStaticsList.get(tableIndex).territory.getName()
                                    + " to "+destinationTerritory);
                            LoggerUtility.consoleHandler.publish(logRecord);
                            
                            player[i].currentGameStaticsTableModel.fireTableDataChanged();
                            break;
                        }
                    }
                } else
                {
                    JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "Source territory must have more that 1 infantries for fortification");
                }
            } else
            {
                JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "Destination territory must be your territory");
            }
        }
    }
}
