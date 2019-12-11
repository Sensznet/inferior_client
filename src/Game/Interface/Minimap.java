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
public class Minimap extends JPanel
{
    //private Image minimap;
    private World world;
    private Image minimap;
    private int heightoffset;
    private int widthoffset;
    private int sizex = 160;
    private int sizey = 160;
    private int xpos;
    private int ypos;
    private int width;
    private int height;
    public Minimap(int width,int height, World world)
    {
        this.setVisible(false);
        this.setBounds(width-sizex,height,sizex,sizey);
        this.world = world;
        this.heightoffset = -(height/2)+3;
        this.widthoffset = (width/2)-sizex-16;
        this.width = width/2;
        this.height = height/2;
        minimap = Toolkit.getDefaultToolkit().getImage("./build/Bilder/InterfaceMinimap.png");
       
    }
    public void setActive()
    {
        this.setVisible(true);
    }
    public void paint(Graphics g)
    {
        OwnPlayer player = world.getPlayer();
        if(width<player.getXpos()) {
            xpos = player.getXpos() + widthoffset;
        }
        if(height<player.getYpos()) {
            ypos = player.getYpos() + heightoffset;
        }
        int mapPosX = xpos+18+(player.getXpos()/300);
        int mapPosY = xpos+18+(player.getYpos()/300);
        g.drawImage(minimap, xpos, ypos, this);
        g.setColor(Color.red);
        g.drawRect(mapPosX, mapPosY, 1, 1);
    }
}
