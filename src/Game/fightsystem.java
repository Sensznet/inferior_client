package Game;

import Game.Objects.Creature;
import Game.Objects.OwnPlayer;
import Game.Objects.World;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Derok
 */
public class fightsystem extends Thread{
    private World world;
    private MonsterThread MT;
    private boolean cooltime=false;
    fightsystem(World world, MonsterThread MT)
    {
        this.world = world;
        this.MT = MT;
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
        Creature target = world.getTarget();
        OwnPlayer player = world.getPlayer();
        if(cooltime==false && target != null)
        {
            cooltime=true;
            if(skill==1)
            {
                
                pxpos = player.getXpos();
                pypos = player.getYpos();
                
                xdist = pxpos - target.getXpos();
                ydist = pypos - target.getYpos();
                dist = (int)Math.sqrt((xdist*xdist)+(ydist*ydist));
                if(dist<=50)
                {
                    player.setAttackAnimation(true);
                    dmg = (int)(Math.random()*5)+5;
                    MT.attackCreature(target, dmg);
                }
            }
        }        
    }
}
