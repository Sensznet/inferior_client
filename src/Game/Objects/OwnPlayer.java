package Game.Objects;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.Main;
import Game.Collision;
import java.awt.*;
import java.awt.Graphics2D;

/**
 *
 * @author Derok
 */
public class OwnPlayer extends Player
{
    private int agi;
    private int intel;
    private int str;
    private int maxMana;
    private int curMana;
    private int maxExp;
    private int curExp;
    private int xmove = 0;
    private int ymove = 0;
    private boolean left = false, right = false, up = false, down = false;
    private final Collision collision;
    private World world;
    public OwnPlayer(World world, Main client, int id, String name, int xpos, int ypos, int direction, int maxHp, int curHp, int maxMana, int curMana, int maxExp, int curExp, int lvl, int agi, int str, int intel)
    {
        super(client, id, name, xpos, ypos, direction, maxHp, curHp, lvl);
        this.world = world;
        this.maxMana = maxMana;
        this.curMana = curMana;
        this.maxExp = maxExp;
        this.curExp = curExp;
        this.agi = agi;
        this.str = str;
        this.intel = intel;
        this.collision = new Collision();
    }
    public int getAgi() {
        return agi;
    }
    public void setAgi(int agi) {
        this.agi = agi;
    }
    public int getInt() {
        return intel;
    }
    public void setInt(int intel) {
        this.intel = intel;
    }
    public int getStr() {
        return str;
    }
    public void setStr(int str) {
        this.str = str;
    }
    public int getMaxMana() {
        return this.maxMana;
    }
    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }
    public int getCurMana() {
        return this.curMana;
    }
    public void setCurMana(int curMana) {
        this.curMana = curMana;
    }
    public int getMaxExp() {
        return this.maxExp;
    }
    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }
    public int getCurExp() {
        return this.curExp;
    }
    public void setCurExp(int curExp) {
        this.curExp = curExp;
    }
    public void left(boolean a)
    {
        left = a;
    }
    public void right(boolean a)
    {
        right = a;
    }
    public void up(boolean a)
    {
        up = a;
    }
    public void down(boolean a)
    {
        down = a;
    }
    public void move()
    {
        if(left==true)
        {
            xmove=-4;
            direction=7;           
            
        }
        if(right==true)
        {
            xmove= 4;
            direction=3;
        }
        if(up==true)
        {
            ymove =-4;
            direction=1;
        }
        if(down==true)
        {
            ymove= 4;
            direction=5;
        } 
        if(left==true&up==true)
        {
            xmove=-3;
            ymove=-3;
            direction=8;
        }
        if(left==true&down==true)
        {
            xmove=-3;
            ymove=3;
            direction=6;
        }  
        if(right==true&up==true)
        {
            xmove=3;
            ymove=-3;
            direction=2;
        }  
        if(right==true&down==true)
        {
            xmove=3;
            ymove=3;
            direction=4;
        }
        if(xmove != 0 || ymove != 0) {
            if(collision.checkCollisionWorld(xpos+xmove, ypos)) {
            xmove = 0;
            }
            if(collision.checkCollisionWorld(xpos, ypos+ymove)) {
                ymove = 0;
            }
            if(collision.checkCollisionWorld(xpos+xmove, ypos+ymove)) {
                xmove = 0;
                ymove = 0;
            }
        }
    }
    
    public int getXMove(int width) {
        int temp = xmove;
        xmove = 0;
        if(xpos-temp<width) {
            temp = 0;
        }else if(xpos-temp<width) {
            temp=width-xpos;
        }
        return temp;
    }
    public int getYMove(int height) {
        int temp = ymove;
        ymove = 0;
        if(ypos<height) {
            temp = 0;
        }else if(ypos-temp<height) {
            temp=height-ypos;
        }
        return temp;
    }
    
    public void paintp(Graphics g)
    {
        xpos+=xmove;
        ypos+=ymove;
        super.paintp(g);
    }
}
