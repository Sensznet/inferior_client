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
public class Skillbar extends Panel{
    Image skillbar;
    Skillbar()
    {
        this.setVisible(false);
        this.setBounds(300,685,500,63);
        skillbar = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Skillbar.png");
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
        g.drawImage(skillbar, 0, 0, this);
    }
}
