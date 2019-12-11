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
public class Overlay extends JPanel
{
    private World world;
    private Minimap map;
    private Skillbar skillbar;
    private Statusbar statusbar;
    public Overlay(int width, int height, World world)
    {
        this.setVisible(false);
        this.setBounds(0,0,width,height);
        this.map = new Minimap(width, height, world);
        this.skillbar = new Skillbar(width, height, world);
        this.statusbar = new Statusbar(width, height, world);
        this.world = world;
        add(map);
        add(skillbar);
    }
    public void setActive()
    {
        this.map.setActive();
        this.skillbar.setActive();
        this.statusbar.setActive();
        this.setVisible(true);
    }
    public void paint(Graphics g)
    {
        map.paint(g);
        skillbar.paint(g);
        statusbar.paint(g);
    }
}
