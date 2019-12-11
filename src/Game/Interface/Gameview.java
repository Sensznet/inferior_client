package Game.Interface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssens
 */
import Game.Objects.OwnPlayer;
import Game.Objects.World;
import java.awt.*;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Derok
 */
public class Gameview extends JPanel
{
    private Image background, water, street;
    private World world;
    private int width;
    private int height;
    private Boolean firstrun = true;
    private int count = 500;
    public Gameview(int width, int height, World world)
    {
        this.setVisible(false);
        this.setBounds(0,0,width,height);
        background = Toolkit.getDefaultToolkit().getImage("./build/Bilder/BG3000.jpg");
        water = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wasser2.png");
        street = Toolkit.getDefaultToolkit().getImage("./build/Bilder/weg3000.png");
        this.world = world;
        this.width = width/2;
        this.height = height/2;
    }
    public void setActive()
    {
        this.setVisible(true);
    }
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        OwnPlayer player = world.getPlayer();
        g2.drawImage(background, 0, 0, this);
        g2.drawImage(street, 0, 0, this);
        g2.drawImage(water, 0, 0, this);
        player.move();
        world.paintp(g2);
        if(firstrun && count <= 0) {
            g2.translate((width)-player.getXpos(), (height)-player.getYpos());
            firstrun = false;
        } else if (firstrun) {
            count--;
        }
        if(firstrun) {
            g.setColor(Color.BLACK);
             g2.fillRect(0,0,width*2,height*2);
        }
        g2.translate(-player.getXMove(width+20), -player.getYMove(height+20));
    }
}