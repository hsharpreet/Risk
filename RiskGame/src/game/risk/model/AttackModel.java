package game.risk.model;

import game.risk.gui.AttackGUIPanel;
import game.risk.util.MapDetails;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AttackModel
{

    Player[] player;
    int myIndex;
    CurrentGameStaticsTableModel tm;
    List<TempGameStatics>tempList;
    AttackGUIPanel attackPanel;
    TempTableModel tempTableModel;
    MapDetails mapDetails;

    public AttackModel(AttackGUIPanel attackPanel, Player[] player, int myIndex,
            CurrentGameStaticsTableModel tm, List<CurrentGameStatics> list, MapDetails mapDetails)
    {
        this.mapDetails = mapDetails;
        this.attackPanel = attackPanel;
        this.player = player;
        this.myIndex = myIndex;
        this.tm = tm;
//        this.list = list;
        this.tempList = new ArrayList<>();
        this.attackPanel.jtMain.setModel(this.tm);
        this.tempTableModel = new TempTableModel(tempList);
        this.attackPanel.jtOther.setModel(tempTableModel);

        this.attackPanel.lbplayer1.setText("Player - "+(myIndex+1));
        
        this.attackPanel.jtMain.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if (e.getValueIsAdjusting())
                {
                    attackPanel.lbplayer2.setText("");
                    AttackModel.this.tempList.clear();
                    int index = AttackModel.this.attackPanel.jtMain.getSelectedRow();
                    updateTempList(index);
                }
            }
        });

       
    }

    
    
}
