/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.risk.listener;

import game.risk.model.Player;
import game.risk.util.LoggerUtility;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import javax.swing.JOptionPane;

public class PlaceInfantryClickListener implements ActionListener
{

    int i;
    Player player[];

    public PlaceInfantryClickListener(int i, Player player[])
    {
        this.i = i;
        this.player = player;
    }

    public void actionPerformed(ActionEvent e)
    {
        if (player[i].infantriesAvailable > 0)
        {
            int index = player[i].getPlayerPanel().jtCountriesAndArmies.getSelectedRow();
            if (index == -1)
            {
                JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "Select terriotary first");
            } else
            {
                player[i].currentGameStaticsList.get(index).infantries++;
                player[i].infantriesAvailable--;
                player[i].getPlayerPanel().lbAvailableArmies.setText("Available Infantries : " + player[i].infantriesAvailable);
                player[i].currentGameStaticsTableModel.fireTableDataChanged();
                player[i].getPlayerPanel().btPlaceInfantry.setEnabled(false);
                player[i].nextIndexToEnableButton(i);

                player[i].setMessage("Initial Phase\r\nPlayer - " + (i + 1) + " has placed infantry in " + player[i].currentGameStaticsList.get(index).territory.getName().toUpperCase() + " and turn switched to next player");
                player[i].notifyObservers();

                LogRecord logRecord = new LogRecord(Level.INFO, "Player - " + (i + 1) + " has placed infantry in " + player[i].currentGameStaticsList.get(index).territory.getName().toUpperCase() + " and turn switched to next player");
                LoggerUtility.consoleHandler.publish(logRecord);

            }
        } else
        {
            JOptionPane.showMessageDialog(player[i].getPlayerPanel(), "No army available");

        }
    }
}