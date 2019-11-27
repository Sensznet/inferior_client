package Game;

import Game.Objects.Monster;
import Game.Objects.Player;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Derok
 */
public class fightsystem extends Thread{
    
    private MonsterThread mobs;
    private Player player;
    private boolean cooltime=false;
    fightsystem(MonsterThread mobs, Player player)
    {
        this.mobs = mobs;
        this.player = player;
        this.start();
    }
    public void run()
    {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        while(true)
        {
            if(cooltime==true)
            {
                try
                {
                    Thread.sleep (1000);
                    cooltime = false;
                }
                catch (InterruptedException ex)
                {
                }
            }
            else
            {
                try
                {
                    Thread.sleep (25);
                }
                catch (InterruptedException ex)
                {
                }
            }
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }
    public void attack(int skill)
    {
        int dmg;
        int pxpos, pypos, mxpos, mypos;
        int xdist, ydist, dist;
        Monster monster = mobs.getSelectedMonster();
        if(cooltime==false && monster != null)
        {
            cooltime=true;
            if(skill==1)
            {
                pxpos = player.getxpos();
                pypos = player.getypos();
                
                xdist = pxpos - monster.getXpos();
                ydist = pypos - monster.getYpos();
                dist = (int)Math.sqrt((xdist*xdist)+(ydist*ydist));
                if(dist<=50)
                {
                    player.setattackanimationtrue();
                    dmg = (int)(Math.random()*5)+5;
                    mobs.attackmob(monster, dmg);
                }
            }
        }        
    }
}
