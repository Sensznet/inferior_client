/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;
import java.awt.*;
/**
 *
 * @author Derok
 */
public class Minimap extends Panel
{
    Image minimap;
    Minimap()
    {
        this.setVisible(false);
        this.setBounds(864,0,160,160);
        minimap = Toolkit.getDefaultToolkit().getImage("./build/Bilder/InterfaceMinimap.png");
    }
    public void setaktive()
    {
        this.setVisible(true);
    }
    public void painter()
    {
        repaint();
    }
    public void paint(Graphics g)
    {
        g.drawImage(minimap, 0, 0, this);
    }
}
