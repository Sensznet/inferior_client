/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Objects;

import Game.Main;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author ssens
 */
public class Player extends Creature {
    protected int lvl;
    protected Boolean attack = false;
    private int attackcount = 0;
    private int attackduration = 15;
    private final Main client;
    private final Image playerpic1, playerpic2, playerpic3, playerpic4, 
            playerpic5, playerpic6, playerpic7, playerpic8, attack1;
    
    
    public Player(Main client, int id, String name, int xpos, int ypos, int direction, int maxHp, int curHp, int lvl) {
        super(id, name, xpos, ypos, direction, maxHp, curHp);
        
        playerpic1 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler.png");
        playerpic2 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler1.png");
        playerpic3 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler2.png");
        playerpic4 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler3.png");
        playerpic5 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler4.png");
        playerpic6 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler5.png");
        playerpic7 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler6.png");
        playerpic8 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler7.png");
        attack1 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/spielerkampfanimationN.jpeg");
        this.client = client;
    }

    public int getXpos() {
        return xpos;
    }
        
    public int getYpos() {
        return ypos;
    }
        
    public int getId() {
        return this.id;
    }
    public int getDirection() {
        return this.direction;
    }
    public int getMaxHp() {
        return this.maxHp;
    }
    public int getCurHp() {
        return this.curHp;
    }
    public int getLvl() {
        return this.lvl;
    }
    public void setXpos(int xpos) {
        this.xpos = xpos;
    }
    public void setYpos(int ypos) {
        this.ypos = ypos;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
    public void getCurHp(int curHp) {
        this.curHp = curHp;
    }
    public void setCurHp(int curHp) {
        this.curHp = curHp;
    }
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    
    public void setAttackAnimation(Boolean attack)
    {
        this.attackcount = 0;
        this.attack=attack;
    }
    
    public void paintp(Graphics g)
    {
        if(attack)
        {
            g.drawImage(attack1, xpos-5, ypos-5, client);
            attackcount++;
            if(attackcount >= 15)
            {
                attack=false;
            }
            
        }
        switch(direction)
        {
            case 1:
            g.drawImage(playerpic1, xpos-5, ypos-5, client); break;
            case 2:
            g.drawImage(playerpic2, xpos-5, ypos-5, client); break;
            case 3:
            g.drawImage(playerpic3, xpos-5, ypos-5, client); break; 
            case 4:
            g.drawImage(playerpic4, xpos-5, ypos-5, client); break; 
            case 5:
            g.drawImage(playerpic5, xpos-5, ypos-5, client); break; 
            case 6:
            g.drawImage(playerpic6, xpos-5, ypos-5, client); break; 
            case 7:
            g.drawImage(playerpic7, xpos-5, ypos-5, client); break; 
            case 8:
            g.drawImage(playerpic8, xpos-5, ypos-5, client); break; 
        }
        if(this instanceof OwnPlayer) {
            
        } else {
            g.drawString(name, xpos+15, ypos+20);
        }
    }
}
