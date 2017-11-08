/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.risk.util;

public class CurrentGameStatics
{

    public int infantries; // available infantries
    public Territory territory;
    public int player;
    

    public CurrentGameStatics(int infantries, Territory territory)
    {
        this.infantries = infantries;
        this.territory = territory;
    }
    
    public CurrentGameStatics(int infantries, Territory territory, int player)
    {
        this.infantries = infantries;
        this.territory = territory;
        this.player = player;
    }
}
