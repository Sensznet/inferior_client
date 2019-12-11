package Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.Objects.Creature;
import Game.Objects.Monster;
import Game.Objects.World;
import java.awt.*;
import java.util.ArrayList;
import org.json.JSONObject;
/**
 *
 * @author Derok
 */
public class MonsterThread extends ConnectionThread
{
    private Main client;
    private World world;
    MonsterThread(String ServerIp, World world)    
    {
        super(ServerIp,3118);
        this.world = world;
    }
    public void run()
    {
        while(running)
        {
            if(this.tick()) {
                JSONObject obj = new JSONObject();
                obj.put("type", "refresh");
                this.sendData(obj);
                JSONObject response = this.getData();
                for(Object tmonster : response.getJSONArray("monsters")) {
                    JSONObject monster = (JSONObject) tmonster;
                    int id = monster.getInt("id");
                    boolean found = false;
                    for(Monster cmonster : world.getMonsters()) {
                        if(id == cmonster.getId()) {
                            found = true;
                            cmonster.setXpos(monster.getInt("xpos"));
                            cmonster.setYpos(monster.getInt("ypos"));
                            cmonster.setDirection(monster.getInt("direction"));
                            cmonster.setMaxHp(monster.getInt("maxHp"));
                            cmonster.setCurHp(monster.getInt("curHp"));
                        }
                    }
                    if(!found) {
                        Monster tempmonster = new Monster(client, id,monster.getInt("type"), monster.getString("name"), 
                                monster.getInt("xpos"), monster.getInt("ypos"), monster.getInt("direction"), 
                                monster.getInt("maxHp"), monster.getInt("curHp"));
                        world.addMonster(tempmonster);
                    }
                }
                
                /*if(mobclicked==true)
                {
                    face.sethpmob(mobhpmax, mobhpmom);
                }*/
            }
        }
    }
    public void offline()
    {
        JSONObject obj = new JSONObject();
        obj.put("type", "offline");
        this.sendData(obj);
        this.stopThread();
    }
    public void clickedonMob(int x, int y)
    {
        /*if(mobxpos-10<x)
        {
            if(mobxpos+40>x)
            {
                if(mobypos-10<y)
                {
                    if(mobypos+40>y)
                    {
                        mobclicked = true;
                    }
                    else
                    {
                        mobclicked = false;
                        face.sethpmob(1,0);
                    }
                }
                else
                {
                    mobclicked = false;
                    face.sethpmob(1,0);
                }
            }
            else
            {
                mobclicked = false;
                face.sethpmob(1,0);
            }            
        }
        else
        {
            mobclicked = false;
            face.sethpmob(1,0);
        }*/
    }
    public void attackCreature(Creature creature, int att)
    {
        /*if(mobclicked=true)
        {
            JSONObject obj = new JSONObject();
            obj.put("type", "attack");
            obj.put("dmg", att);
            this.sendData(obj);
        }*/
    }
}
