package Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Derok
 */
public class Interfacee {
    private int hpmax, hpmom, xpos0, xpos3, ypos0,
            ypos3, manamax, manamom, exp, expmax, mobhpmax=1, mobhpmom=0;
    private WorldThread welt;
    private int breite1,breite2,breite3, breite4;
    private boolean aktive;
    private Image hpmanaexp, mobanzeige;
    private Main client;
    
    Interfacee(WorldThread welt)
    {
        hpmanaexp = Toolkit.getDefaultToolkit().getImage("./build/Bilder/HPMPEXP3.png");
        mobanzeige = Toolkit.getDefaultToolkit().getImage("./build/Bilder/mobhp.png");
        this.welt = welt;
    }
    public void setpos(int xpos, int ypos)
    {
        xpos-=10;
        ypos-=25;
        xpos0 = xpos;
        ypos0 = ypos;
        xpos3 = xpos;
        ypos3 = ypos;
        aktive = true;
    }
    void sethpmob(int max, int mom)
    {
        mobhpmax = max;
        mobhpmom = mom;
    }
    void paintg(Graphics g)
    {
        if(aktive==true)
        {
            hpmax = welt.gethpmax();                                        //Bekommen der Werte mana/hp usw für den Spieler
            hpmom = welt.gethpom();
            manamax = welt.getmanamax();
            manamom = welt.getmanamom();
            exp = welt.getexp();
            expmax = welt.getexpmax();
            if(hpmax!=0)
            breite1 = 20000000/hpmax*hpmom/100000; //Ausrichtung der Werte für den balken
            if(manamax!=0)
            breite2 = 20000000/manamax*manamom/100000;
            if(expmax!=0)
            breite3 = 20000000/expmax*expmax/100000;
            breite4 = 20000000/mobhpmax*mobhpmom/100000;
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255,0,0,192));                                         //HP balken zeichnen
            g2.drawRect(xpos0-396, ypos0-333, 200, 15);
            g2.fillRect(xpos0-396, ypos0-333, breite1, 15);
            g2.setColor(new Color(0,0,255,192));                                        //Mana balken zeichnen
            g2.drawRect(xpos0-396, ypos0-311, 200, 15);
            g2.fillRect(xpos0-396, ypos0-311, breite2, 15);
            g2.setColor(new Color(0,255,0,192));                           //Exp balken zeichnen
            g2.drawRect(xpos0-396, ypos0-289, 200, 15);
            g2.fillRect(xpos0-396, ypos0-289, breite3, 15);
            g2.drawImage(hpmanaexp, xpos0-532, ypos0-377, client);          //MANAHPEXP bild zeichnen
            g2.setColor(Color.white);                                  
            g2.drawString(String.valueOf(hpmom), xpos0-346, ypos0-322);     //Schrift für HP
            g2.drawString("/", xpos0-296, ypos0-322);
            g2.drawString(String.valueOf(hpmax), xpos0-276, ypos0-322);
            g2.drawString(String.valueOf(manamom), xpos0-346, ypos0-300);   //Schrift für Mana
            g2.drawString("/", xpos0-296, ypos0-300);
            g2.drawString(String.valueOf(manamax), xpos0-276, ypos0-300);
            g2.drawString(String.valueOf(exp), xpos0-346, ypos0-277);       //Schrift für Exp
            g2.drawString("/", xpos0-296, ypos0-277);
            g2.drawString(String.valueOf(expmax), xpos0-276, ypos0-277);
            g2.setColor(Color.red);
            g2.fillRect(xpos3-58, ypos3-350, breite4, 15);
            g2.drawImage(mobanzeige, xpos3-130, ypos3-358, client);         //mobhp
        }
    }
}
