/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Game.Interface;
import java.awt.*;
import javax.swing.JPanel;
/**
 *
 * @author Derok
 */
public class Target extends JPanel
{
    Image hpbar;
    public Target()
    {
        this.setVisible(false);
        this.setBounds(864,0,160,160);
        hpbar = Toolkit.getDefaultToolkit().getImage("./build/Bilder/mobhp.png");
    }
    public void setActive()
    {
        this.setVisible(true);
    }
    public void paint(Graphics g)
    {
        g.drawImage(hpbar, 0, 0, this);
    }
}
