/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.risk.listener;

import game.risk.model.Player;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyListSelectionListener implements ListSelectionListener
    {

        int i;
        Player player[];

        public MyListSelectionListener(int i,Player player[])
        {
            this.i = i;
            this.player = player;
        }

        @Override
        public void valueChanged(ListSelectionEvent e)
        {
            if (e.getValueIsAdjusting())
            {
                int index = player[i].getPlayerPanel().jtCountriesAndArmies.getSelectedRow();
                if (index >= 0)
                {
                    player[i].neighbourListModel.neighbours = player[i].currentGameStaticsList.get(index).territory.getNeighbouringTerritories();
                    player[i].getPlayerPanel().lsNeighbour.updateUI();

                }
            }
        }

    }