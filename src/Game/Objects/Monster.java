/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Objects;

import Game.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author ssens
 */
public class Monster extends Creature {
    private int type;
    private int width = 5;
    private int height = 15;
    private final Main client;
    private final Image Mobpic1, Mobpic2, Mobpic3, Mobpic4, Mobpic5, Mobpic6, Mobpic7, Mobpic8;
    
    public Monster(Main client, int id, int type, String name, int xpos, int ypos, int direction, int maxHp, int curHp) {
        super(id, name, xpos, ypos, direction, maxHp, curHp);
        this.type = type;
        this.client = client;
        Mobpic1 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf4.png");
        Mobpic2 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf5.png");
        Mobpic3 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf6.png");
        Mobpic4 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf7.png");
        Mobpic5 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf.png");
        Mobpic6 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf1.png");
        Mobpic7 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf2.png");
        Mobpic8 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf3.png");
    }
    
    public int getType() {
        return this.type;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
   
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void paintp(Graphics g)
    {
        switch(direction)
        {
            case 1:
                g.drawImage(Mobpic1, xpos-20, ypos-20, client);break;
            case 2:
                g.drawImage(Mobpic2, xpos-20, ypos-20, client);break;
            case 3:
                g.drawImage(Mobpic3, xpos-20, ypos-20, client);break;
            case 4:
                g.drawImage(Mobpic4, xpos-20, ypos-20, client);break;
            case 5:
                g.drawImage(Mobpic5, xpos-20, ypos-20, client);break;
            case 6:
                g.drawImage(Mobpic6, xpos-20, ypos-20, client);break;
            case 7:
                g.drawImage(Mobpic7, xpos-20, ypos-20, client);break;
            case 8:
                g.drawImage(Mobpic8, xpos-20, ypos-20, client);break;
        };
        g.setColor(Color.ORANGE);
        g.drawString(name, xpos, ypos-15);      
    }
}
