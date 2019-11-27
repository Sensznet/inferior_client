/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Objects;

/**
 *
 * @author ssens
 */
public class Monster {
    private int id;
    private int xpos;
    private int ypos;
    private int type;
    private int direction;
    private int curHp;
    private int maxHp;
    private String name;
    private int width = 5;
    private int height = 15;
    
    
    public Monster(int id, int type, String name, int xpos, int ypos, int direction, int maxHp, int curHp) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.xpos = xpos;
        this.ypos = ypos;
        this.direction = direction;
        this.curHp = curHp;
        this.maxHp = maxHp;
    }
    
    public int getId() {
        return this.id;
    }

    public int getXpos() {
        return this.xpos;
    }
    
    public void setXpos(int xpos) {
        this.xpos = xpos;
    }
    
    public int getYpos() {
        return this.ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public int getType() {
        return this.type;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getName() {
        return this.name;
    }

    public int getCurHp() {
        return curHp;
    }

    public void setCurHp(int curHp) {
        this.curHp = curHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
}
