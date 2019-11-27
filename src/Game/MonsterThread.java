package Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.Objects.Monster;
import java.awt.*;
import java.util.ArrayList;
import org.json.JSONObject;
/**
 *
 * @author Derok
 */
public class MonsterThread extends ConnectionThread
{
    private boolean mobclicked=false;
    private Main client;
    private Image Mobpic1, Mobpic2, Mobpic3, Mobpic4, Mobpic5, Mobpic6, Mobpic7, Mobpic8;
    private Interfacee face;
    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    private int selectedMonster = -1;
    MonsterThread(String ServerIp, Interfacee face)    
    {
        super(ServerIp,3118);
        this.face = face;
        Mobpic1 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf4.png");
        Mobpic2 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf5.png");
        Mobpic3 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf6.png");
        Mobpic4 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf7.png");
        Mobpic5 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf.png");
        Mobpic6 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf1.png");
        Mobpic7 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf2.png");
        Mobpic8 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wolf3.png");
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
                    for(Monster cmonster : this.monsters) {
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
                        Monster tempmonster = new Monster(id,monster.getInt("type"), monster.getString("name"), 
                                monster.getInt("xpos"), monster.getInt("ypos"), monster.getInt("direction"), 
                                monster.getInt("maxHp"), monster.getInt("curHp"));
                        monsters.add(tempmonster);
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
    public boolean getclicked()
    {
        return mobclicked;
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
    public ArrayList<Monster> getMonsters() {
        return this.monsters;
    }
    public Monster getSelectedMonster() {
        if(this.selectedMonster < 0) {
            return null;
        }
        return this.monsters.get(selectedMonster);
    }
    public void attackmob(Monster monster, int att)
    {
        /*if(mobclicked=true)
        {
            JSONObject obj = new JSONObject();
            obj.put("type", "attack");
            obj.put("dmg", att);
            this.sendData(obj);
        }*/
    }
    public void paintp(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
            /*if(mobclicked==true)
            {
                g2.setColor(Color.red);
                g2.drawOval(mobxpos-10, mobypos-10, 60, 60);
            }*/
            for(Monster monster : this.monsters) {
                switch(monster.getDirection())
                {
                    case 1:
                        g2.drawImage(Mobpic1, monster.getXpos()-20, monster.getYpos()-20, client);break;
                    case 2:
                        g2.drawImage(Mobpic2, monster.getXpos()-20, monster.getYpos()-20, client);break;
                    case 3:
                        g2.drawImage(Mobpic3, monster.getXpos()-20, monster.getYpos()-20, client);break;
                    case 4:
                        g2.drawImage(Mobpic4, monster.getXpos()-20, monster.getYpos()-20, client);break;
                    case 5:
                        g2.drawImage(Mobpic5, monster.getXpos()-20, monster.getYpos()-20, client);break;
                    case 6:
                        g2.drawImage(Mobpic6, monster.getXpos()-20, monster.getYpos()-20, client);break;
                    case 7:
                        g2.drawImage(Mobpic7, monster.getXpos()-20, monster.getYpos()-20, client);break;
                    case 8:
                        g2.drawImage(Mobpic8, monster.getXpos()-20, monster.getYpos()-20, client);break;
                };
                g2.setColor(Color.ORANGE);
                g2.drawString(monster.getName(), monster.getXpos(), monster.getYpos()-15);
            }        
    }
    

}
