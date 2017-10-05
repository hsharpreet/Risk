package game.risk.gui;

import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;
 
public class circle extends JFrame 
{
    public circle()
    {
        setTitle("Tutorial");
        setSize(600,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
 
    public void paint(Graphics g)
    {
    	
        g.setColor(Color.GREEN);
        g.drawOval(280-10,480-10,20,20);
        g.fillOval(280-10, 480-10, 20, 20);
        g.setColor(Color.BLUE);
        g.fillOval(80-10, 100-10, 20, 20);
        g.setColor(Color.PINK);
        g.drawLine(280, 480, 80, 100);
        g.setColor(Color.BLACK);
        
        g.drawString("abc", 270, 500);
        
    }
 
    public static void main(String args[])
    {
        circle t = new circle();
        t.paint(null);
    }
}
