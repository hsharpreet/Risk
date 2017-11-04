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

        this.attackPanel.btAttack.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int index1 = AttackModel.this.attackPanel.jtMain.getSelectedRow();
                int index2 = AttackModel.this.attackPanel.jtOther.getSelectedRow();
                
                if(index1==-1 || index2==-1)
                {
                    JOptionPane.showMessageDialog(AttackModel.this.attackPanel,"Please select both SOURCE and DESTINATION");
                }
                else
                {
                    if(tempList.get(index2).isOwn)
                    {
                        JOptionPane.showMessageDialog(AttackModel.this.attackPanel,"DESTINATION is your own territory.\nYou can't attack on your own territory");
                    }
                    else if(list.get(index1).infantries==1)
                    {
                        JOptionPane.showMessageDialog(AttackModel.this.attackPanel,"SOURCE has only 1 infantry. So can't attack from this territory");
                    }
                    else
                    {
                        int player1OptionsForDice,player2OptionsForDice;
                        if(list.get(index1).infantries==2)
                            player1OptionsForDice = 1;
                        else if(list.get(index2).infantries==3)
                            player1OptionsForDice = 2;
                        else
                            player1OptionsForDice = 3;
                    }
                }
                
            }
        });
        
        this.attackPanel.jtOther.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if(e.getValueIsAdjusting())
                {
                    int index = AttackModel.this.attackPanel.jtOther.getSelectedRow();
                    if(index!=-1)
                    {
                        attackPanel.lbplayer2.setText("Player - "+(tempList.get(index).player+1));
                    }
                }
            }
        });
        
    }

    void updateTempList(int index)
    {

        HashMap<String, Territory> territories = mapDetails.getTerritories();
        Iterator it = territories.keySet().iterator();
        List<String> ls = player[myIndex].currentGameStaticsList.get(index).territory.getNeighbouringTerritories();
        while (it.hasNext())
        {
            try
            {
                Territory t = territories.get(it.next());
                for (int j = 0; j < ls.size(); j++)
                {
                    if (ls.get(j).equals(t.getName()))
                    {

                        TempGameStatics tgs = new TempGameStatics(t);
                        tempList.add(tgs);
                        break;
                    }
                }
            } catch (Exception ex)
            {
                break;
            }
        }
        
        for(int i=0; i<tempList.size(); i++)
        {
            for(int j=0; j<player.length; j++)
            {
               String tn1 = tempList.get(i).territory.getName();
               for(int k=0; k<player[j].currentGameStaticsList.size(); k++)
               {
                   String tn2 = player[j].currentGameStaticsList.get(k).territory.getName();
                   if(tn1.equals(tn2))
                   {
                       tempList.get(i).infantries = player[j].currentGameStaticsList.get(k).infantries;
                       tempList.get(i).player = j;
                       
                       if(j==myIndex)
                       {
                           tempList.get(i).isOwn = true;
                       }
                      
                       break;
                   }
               }
            }
        }
        
        tempTableModel.fireTableDataChanged();
    }

    
    
}
