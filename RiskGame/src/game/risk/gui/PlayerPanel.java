/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.risk.gui;

import game.risk.main.RiskGame;
import game.risk.model.Territory;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class PlayerPanel extends javax.swing.JPanel
{

    public int infantriesAvailable;
    public int infantriesTotal;
    public List<CurrentGameStatics> list;
    public CurrentGameStaticsTableModel tm;
    public NeighbourListModel lm;
    public List<String> neighbours;

    public PlayerPanel()
    {
        initComponents();
        list = new ArrayList<>();
        neighbours = new ArrayList<>();
        tm = new CurrentGameStaticsTableModel();
        lm = new NeighbourListModel();
        jtCountriesAndArmies.setModel(tm);
        lsNeighbour.setModel(lm);

        jtCountriesAndArmies.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if (e.getValueIsAdjusting())
                {
                    int index = jtCountriesAndArmies.getSelectedRow();
                    if (index >= 0)
                    {
                        neighbours = list.get(index).territory.getNeighbouringTerritories();
                        lsNeighbour.updateUI();
                    }
                }
            }
        });

    }
    
    public class CurrentGameStaticsTableModel extends AbstractTableModel
    {

        String columnNames[] =
        {
            "Territory", "Continent", "Infantries"
        };

        public String getColumnName(int index)
        {
            return columnNames[index];
        }

        @Override
        public int getRowCount()
        {
            return list.size();
        }

        @Override
        public int getColumnCount()
        {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            if (columnIndex == 0)
            {
                return list.get(rowIndex).territory.getName();
            } else if (columnIndex == 1)
            {
                return list.get(rowIndex).territory.getContinent();
            } else
            {
                return list.get(rowIndex).infantries;
            }
        }

    }


   
}
