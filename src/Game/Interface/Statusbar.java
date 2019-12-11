/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Game.Interface;
import Game.Objects.World;
import java.awt.*;
import javax.swing.JPanel;
/**
 *
 * @author Derok
 */
public class Statusbar extends JPanel
{
    private Image hpmanaexp;
    private World world;
    public Statusbar(int height,int width, World world)
    {
        this.setVisible(false);
        this.setBounds(0,0,400,150);
        hpmanaexp = Toolkit.getDefaultToolkit().getImage("./build/Bilder/HPMPEXP3.png");
        this.world = world;
    }
    public void setActive()
    {
        this.setVisible(true);
    }
    public void paint(Graphics g)
    {
        this.setBackground(Color.red);
        g.drawImage(hpmanaexp, 0, 0, this);
    }
}
