/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.risk.listener;

import game.risk.model.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OkClickListener implements ActionListener
    {

        int i;
        Player player[];

        public OkClickListener(int i,Player player[])
        {
            this.i = i;
            this.player = player;
        }

        public void actionPerformed(ActionEvent e)
        {

            player[i].nextPlayerTurn(i);
        }
    }