package Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
/**
 *
 * @author Derok
 */
public class Objektss {
    private int x,y, breite,hoehe;
    private int art=0;
    private Image a;
    private Main client;
    Objektss(int art, Image a, int x, int y, int breite, int hoehe, Main client)
    {
        this.art = art;
        this.x = x;
        this.y = y;
        this.breite = breite;
        this.hoehe = hoehe;
        this.a = a;
        this.client = client;
    }
    public int getart()
    {
        return art;
    }
    public int getxpos()
    {
        return x;
    }
    public int getypos()
    {
        return y;
    }
    public int getbreite()
    {
        return breite;
    }
    public int gethoehe()
    {
        return hoehe;
    }
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(a, x,y, client);
    }
}
