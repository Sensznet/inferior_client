/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Game.Interface;
import Game.Objects.OwnPlayer;
import Game.Objects.World;
import java.awt.*;
import javax.swing.JPanel;
/**
 *
 * @author Derok
 */
public class Skillbar extends JPanel
{
    //private Image minimap;
    private World world;
    private Image minimap;
    private int height, width;
    private int sizex = 160, sizey = 160;
    public Skillbar(int height,int width, World world)
    {
        this.setVisible(false);
        
    }
    public void setActive()
    {
        this.setVisible(true);
    }
    public void paint(Graphics g)
    {
        
    }
}
