package game.risk.model;

import java.util.List;
import javax.swing.AbstractListModel;

/**
 * This class is a list model which is used to make the displaying list dynamic
 * 
 */
public class NeighbourListModel extends AbstractListModel
{
    public List<String> neighbours;

    public NeighbourListModel(List<String> neighbours)
    {
        this.neighbours = neighbours;
    }

    @Override
    public int getSize()
    {
        return neighbours.size();
    }

    @Override
    public Object getElementAt(int index)
    {
        return neighbours.get(index);
    }

    
    
}
