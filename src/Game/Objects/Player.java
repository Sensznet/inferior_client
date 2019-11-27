package Game.Objects;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.Interfacee;
import Game.Main;
import Game.MonsterThread;
import Game.Objektss;
import Game.WorldThread;
import Game.collision;
import Game.Objects.Monster;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.*;
import javax.imageio.ImageIO;

/**
 *
 * @author Derok
 */
public class Player
{
    private int id, richtung=1, ypos, xpos, hpmax, hpmom, ts1, ts2, i=0, mm=0;
    private int xschritt, xschritt2;
    private int yschritt, yschritt2;
    private boolean windows=true, collside = false, collside2 = false, attackanimation =false;
    private boolean left = false, right = false, up = false, down = false, aktive=false;
    private Main client;
    private Interfacee face;
    private WorldThread welt;
    private Objektss[] objektss;
    private MonsterThread mobs;
    private collision collis;
    private Image playerpic1, playerpic2, playerpic3, playerpic4, 
            playerpic5, playerpic6, playerpic7, playerpic8;
    private Image angriff1;
    private BufferedImage collpic;
    private int collpixel;
    private Boolean firstrun = true;
    public Player(WorldThread welt, Interfacee face, MonsterThread mobs, Main client)
    {
        collis = new collision();
        this.client = client;
        this.welt = welt;
        this.face = face;
        this.mobs = mobs;
        try {
            collpic = ImageIO.read(new File("./build/Bilder/wasser1.bmp"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerpic1 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler.png");
        playerpic2 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler1.png");
        playerpic3 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler2.png");
        playerpic4 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler3.png");
        playerpic5 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler4.png");
        playerpic6 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler5.png");
        playerpic7 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler6.png");
        playerpic8 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler7.png");
        angriff1 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/spielerkampfanimationN.jpeg");
    }
    public void createobjektss(int k)
    {
        objektss = new Objektss[k];
        mm=k-1;
    }
    public void createobjektss2(int k, Objektss objektss)
    {
        this.objektss[k] = objektss;
    }
    public void position(int x, int y)
    {
        xpos = x;
        ypos = y;
        aktive = true;
    }
    public void setid(int i)
    {
        id = i;
    }
    public void kleft(boolean a)
    {
        left = a;
    }
    public void kright(boolean a)
    {
        right = a;
    }
    public void kup(boolean a)
    {
        up = a;
    }
    public void kdown(boolean a)
    {
        down = a;
    }
    public void move()
    {
        if(aktive==true)
        {
        if(left==true)
        {
            xschritt=-4;
            xschritt2=4;
            richtung=7;           
            
        }
        if(right==true)
        {
            xschritt= 4;
            xschritt2=-4;
            richtung=3;
        }
        if(up==true)
        {
            yschritt =-4;
            yschritt2= 4;
            richtung=1;
        }
        if(down==true)
        {
            yschritt= 4;
            yschritt2=-4;
            richtung=5;
        } 
        if(left==true&up==true)
        {
            xschritt=-3;
            yschritt=-3;
            xschritt2=3;
            yschritt2=3;
            richtung=8;
        }
        if(left==true&down==true)
        {
            xschritt=-3;
            yschritt=3;
            xschritt2=3;
            yschritt2=-3;
            richtung=6;
        }  
        if(right==true&up==true)
        {
            xschritt=3;
            yschritt=-3;
            xschritt2=-3;
            yschritt2=3;
            richtung=2;
        }  
        if(right==true&down==true)
        {
            xschritt=3;
            yschritt=3;
            xschritt2=-3;
            yschritt2=-3;
            richtung=4;
        }
        
        collpixel = collpic.getRGB(xpos+xschritt+550, ypos+yschritt+420);
        if(collpixel<-1)
        {
            collside=true;
        }
        int s=0;
        while(s<mm)
        {
            if(collside==false)
            {
                switch(objektss[s].getart())
                {
                    case 0: 
                        collside = collis.collisiontestobejct(xpos+xschritt-100, ypos+yschritt-100, 
                        30, 30, objektss[s].getxpos(), 
                        objektss[s].getypos(), 
                        objektss[s].getbreite(), objektss[s].gethoehe());
                        break;
                    case 1:
                        collside = collis.collisiontestobejctschr(xpos-100, ypos-100, 30, 30, 
                                objektss[s].getxpos(), 
                                objektss[s].getypos(), 
                                objektss[s].getbreite(), objektss[s].gethoehe());
                        break;
                    case 2:
                        collside = collis.collisiontestobejct(xpos+xschritt, ypos+yschritt, 
                        30, 30, objektss[s].getxpos()+objektss[s].getbreite(), 
                        objektss[s].getypos()+objektss[s].gethoehe(), 
                        objektss[s].getbreite(), objektss[s].gethoehe());
                        break;
                };
            }
            s++;
        }
        if(collside==false)
        {
            for(Monster monster : this.mobs.getMonsters()) {
                collside = collis.collisiontestobejct(xpos+xschritt, ypos+yschritt, 30, 30, monster.getXpos(), 
                monster.getYpos(), monster.getWidth(), monster.getHeight());
            }
            
        }
        if(collside==true)
        {
            int ss=0;
            while(ss<mm)
            {
                switch(objektss[ss].getart())
                {
                    case 0:
                        if(collis.collisiontestobejct(xpos+xschritt-100, ypos-100, 30, 30, 
                            objektss[ss].getxpos(), 
                            objektss[ss].getypos(), 
                            objektss[ss].getbreite(), objektss[ss].gethoehe())==true)
                        {
                            ypos += yschritt;
                            ts1 = 1;
                        }
                        if(collis.collisiontestobejct(xpos-100, ypos+yschritt-100, 30, 30, 
                            objektss[ss].getxpos(), 
                            objektss[ss].getypos(), 
                            objektss[ss].getbreite(), objektss[ss].gethoehe())==true)
                        {
                            xpos += xschritt;
                            ts2 = 1;
                        } break;
                    case 1:
                        if(collis.collisiontestobejctschr(xpos-100, ypos-100, 30, 30, 
                                objektss[ss].getxpos(), 
                                objektss[ss].getypos(), 
                                objektss[ss].getbreite(), objektss[ss].gethoehe())==true)
                        {
                                    
                        }
                        break;
                    case 2:
                        if(collis.collisiontestobejct(xpos+xschritt, ypos, 30, 30, 
                            objektss[ss].getxpos()+objektss[ss].getbreite(), 
                            objektss[ss].getypos()+objektss[ss].gethoehe(), 
                            objektss[ss].getbreite(), objektss[ss].gethoehe())==true)
                        {
                            ypos += yschritt;
                            ts1 = 1;
                        }
                    if(collis.collisiontestobejct(xpos, ypos+yschritt, 30, 30, 
                                objektss[ss].getxpos()+objektss[ss].getbreite(),  
                            objektss[ss].getypos()+objektss[ss].gethoehe(),
                            objektss[ss].getbreite(), objektss[ss].gethoehe())==true)
                        {
                            xpos += xschritt;
                            ts2 = 1;
                        } break;
                };
                ss++;
            }
            
        }
        else
        {
            xpos += xschritt;
            ypos += yschritt;
            ts1 = 0;
            ts2 = 0;
            
        }
        face.setpos(xpos, ypos);
        welt.setpos(xpos, ypos, richtung, aktive);
        xschritt=0;
        yschritt=0;
        collside2 = collside;
        collside = false;
        }
    }
    public void windows()
    {
        windows=true;
    }
    public int getxpos()
    {
        return xpos;
    }
    public int getypos()
    {
        return ypos;
    }
    public void setattackanimationtrue()
    {
        attackanimation=true;
    }
    public void paintp(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        if(aktive==true)
        {
            if(windows==true && !firstrun)
            {
                
                g2.translate(512 -xpos ,384 -ypos);
                windows=false;
            } else {
                firstrun = false;
            }
            if(collside2==true)
            {
                if(ts1==1)
                {
                    g2.translate(0, yschritt2);
                    
                }
                if(ts2==1)
                {
                    g2.translate(xschritt2, 0);
                }
            }
            else
            {
                g2.translate(xschritt2, yschritt2);
            }
            xschritt2=0;
            yschritt2=0;
            collside2=false;
        }
        g2.setColor(Color.black);
        if(attackanimation==true)
        {
            g2.drawImage(angriff1, xpos-5, ypos-5, client);
            i++;
            if(i >= 15)
            {
                i=0;
                attackanimation=false;
            }
            
        }
        else
        {
            switch(richtung)
            {
                case 1:
                g2.drawImage(playerpic1, xpos-5, ypos-5, client); break;
                case 2:
                g2.drawImage(playerpic2, xpos-5, ypos-5, client); break;
                case 3:
                g2.drawImage(playerpic3, xpos-5, ypos-5, client); break; 
                case 4:
                g2.drawImage(playerpic4, xpos-5, ypos-5, client); break; 
                case 5:
                g2.drawImage(playerpic5, xpos-5, ypos-5, client); break; 
                case 6:
                g2.drawImage(playerpic6, xpos-5, ypos-5, client); break; 
                case 7:
                g2.drawImage(playerpic7, xpos-5, ypos-5, client); break; 
                case 8:
                g2.drawImage(playerpic8, xpos-5, ypos-5, client); break; 
            }
        }        
    }
}
