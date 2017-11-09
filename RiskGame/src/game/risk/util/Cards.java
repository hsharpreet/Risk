package game.risk.util;

public class Cards
{
    private Territory territory;
    private String cardDesign;
    private int player;

    public Cards(Territory territory, String cardDesign)
    {
        this.territory = territory;
        this.cardDesign = cardDesign;
        this.player = -1;
    }

    public Territory getTerritory()
    {
        return territory;
    }

    public void setTerritory(Territory territory)
    {
        this.territory = territory;
    }

    public String getCardDesign()
    {
        return cardDesign;
    }

    public void setCardDesign(String cardDesign)
    {
        this.cardDesign = cardDesign;
    }

    public int getPlayer()
    {
        return player;
    }

    public void setPlayer(int player)
    {
        this.player = player;
    }
    
    
    
}
