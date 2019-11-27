package Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.Objects.Player;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
/**
 *
 * @author Derok
 */
public class WorldThread extends ConnectionThread
{
    private int id=0, k=0, s=0,o=1, xpos, ypos, hpmax=1, hpmom=1, 
                manamax=1, manamom=1, richtung=1, exp=0, expmax=1, agi=0, 
                intel=0, str=0, u;
    private int[] gxpos= new int[100];
    private int[] gypos= new int[100];
    private int[] ghpmax= new int[100];
    private int[] ghpmom= new int[100];
    private int[] grichtung = new int[100];
    private String[] nick= new String[100];
    private boolean[] online= new boolean[100];
    private boolean aktive=false;
    private Main client;
    private Image Spieler1, Spieler2, Spieler3, Spieler4, 
            Spieler5, Spieler6, Spieler7, Spieler8;
    WorldThread(String ServerIp)
    {
        super(ServerIp, 3114);
        do
        {
            nick[o]="";
            o++;
        }while(o!=99);       
        do
        {
            online[k]=false;
            k++;
        }while(k!=99);
        Spieler1 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler.png");
        Spieler2 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler1.png");
        Spieler3 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler2.png");
        Spieler4 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler3.png");
        Spieler5 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler4.png");
        Spieler6 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler5.png");
        Spieler7 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler6.png");
        Spieler8 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Spieler7.png");
    }
    @Override
    public void run()
    {
        while(running) {
            if(this.tick() && aktive==true) {
                    JSONObject obj = new JSONObject();
                    obj.put("type", "refresh");
                    obj.put("xpos", xpos);
                    obj.put("ypos", ypos);
                    obj.put("direction", richtung);
                    this.sendData(obj);
                    JSONObject response = this.getData();
                    hpmax = response.getInt("maxHp");
                    hpmom = response.getInt("curHp");
                    manamax = response.getInt("maxMana");
                    manamom = response.getInt("curMana");
                    exp = response.getInt("curExp");
                    expmax = response.getInt("maxExp");
                    JSONArray players = response.getJSONArray("players");
                    int count = 0;
                    k=0;
                    do
                    {
                        online[k]=false;
                        k++;
                    }while(k!=99);
                    for(Object tplayer : players) {
                        JSONObject player = (JSONObject)tplayer;
                        gxpos[count] = player.getInt("xpos");
                        gypos[count] = player.getInt("ypos");
                        grichtung[count] = player.getInt("direction");
                        nick[count] = player.getString("name");
                        ghpmax[count] = player.getInt("maxHp");
                        ghpmom[count] = player.getInt("curHp");
                        online[count] = true;
                    }
            }
        }
        
    }
    int gethpmax()
    {
        return hpmax;
    }
    int gethpom()
    {
        return hpmom;
    }
    int getmanamax()
    {
        return manamax;
    }
    int getmanamom()
    {
        return manamom;
    }
    int getexp()
    {
        return exp;
    }
    int getexpmax()
    {
        return expmax;
    }
    public void setpos(int x, int y, int richtung, boolean aktive)
    {
        this.richtung = richtung;
        this.aktive = aktive;
        xpos=x;
        ypos=y;
    }
    public void getstats()
    {
        if(aktive==true)
        {
            JSONObject obj = new JSONObject();
            obj.put("type", "attributes");
            this.sendData(obj);
            JSONObject response = this.getData();
            agi = response.getInt("agi");
            intel = response.getInt("int");
            str = response.getInt("str");
        }
    }
    public void loadData(Player player) {
        JSONObject obj = new JSONObject();
        obj.put("type", "initial");
        obj.put("id", id);
        this.sendData(obj);
        JSONObject response = this.getData();
        int countObjects = response.getInt("objectCount");
        /**System.out.println(zaehler1);
         * objektss = new Objektss[zaehler1];
         * player.createobjektss(zaehler1);
         * int zaehler2 = zaehler1;
         * while(zaehler2>=1)
         * {
         * String a = in.readUTF();
         * System.out.println(a);
         * String[] kk = a.split(",");
         * System.out.println(a);
         * int breite = Integer.parseInt(kk[3]);
         * breite = breite/2;
         * kk[3]=String.valueOf(breite);
         * int hoehe = Integer.parseInt(kk[4]);
         * hoehe = hoehe/2;
         * kk[4]=String.valueOf(hoehe);
         * if(kk[0].equals("haus1"))
         * {
         * objektss[zaehler2-1] = new Objektss(0,Haus1,Integer.parseInt(kk[1]),
         * Integer.parseInt(kk[2]), Integer.parseInt(kk[3]),
         * Integer.parseInt(kk[4]), this);
         * player.createobjektss2(zaehler2-1, objektss[zaehler2-1]);
         * }
         * if(kk[0].equals("haus2"))
         * {
         * objektss[zaehler2-1] = new Objektss(1,Haus2,Integer.parseInt(kk[1]),
         * Integer.parseInt(kk[2]), Integer.parseInt(kk[3]),
         * Integer.parseInt(kk[4]), this);
         * player.createobjektss2(zaehler2-1, objektss[zaehler2-1]);
         * }
         * if(kk[0].equals("haus3"))
         * {
         * objektss[zaehler2-1] = new Objektss(0,Haus3,Integer.parseInt(kk[1]),
         * Integer.parseInt(kk[2]), Integer.parseInt(kk[3]),
         * Integer.parseInt(kk[4]), this);
         * player.createobjektss2(zaehler2-1, objektss[zaehler2-1]);
         * }
         * if(kk[0].equals("haus4"))
         * {
         * objektss[zaehler2-1] = new Objektss(1,Haus4,Integer.parseInt(kk[1]),
         * Integer.parseInt(kk[2]), Integer.parseInt(kk[3]),
         * Integer.parseInt(kk[4]), this);
         * player.createobjektss2(zaehler2-1, objektss[zaehler2-1]);
         * }
         * if(kk[0].equals("baum1"))
         * {
         * objektss[zaehler2-1] = new Objektss(2,baum1,Integer.parseInt(kk[1]),
         * Integer.parseInt(kk[2]), Integer.parseInt(kk[3]),
         * Integer.parseInt(kk[4]), this);
         * player.createobjektss2(zaehler2-1, objektss[zaehler2-1]);
         * }
         * if(kk[0].equals("baum2"))
         * {
         * objektss[zaehler2-1] = new Objektss(2,baum2,Integer.parseInt(kk[1]),
         * Integer.parseInt(kk[2]), Integer.parseInt(kk[3]),
         * Integer.parseInt(kk[4]), this);
         * player.createobjektss2(zaehler2-1, objektss[zaehler2-1]);
         * }
         * zaehler2--;
         * }**/
        xpos = response.getInt("xpos");
        ypos = response.getInt("ypos");
        player.position(xpos, ypos);
    }
    public void offline()
    {
        if(aktive==true)
        {
            JSONObject obj = new JSONObject();
            obj.put("type", "offline");
            this.sendData(obj);
            this.stopThread();
            this.stop();
        }
    }
    void setid(int i)
    {
        id=i;
    }
    void paintp(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        for(int i=1; i<100;i++)
        {
            if(online[i]==true)
            {
                g2.setColor(Color.red);
                switch(grichtung[i])
                {
                    case 1:
                    g2.drawImage(Spieler1, gxpos[i]-5, gypos[i]-5, client); break;
                    case 2:
                    g2.drawImage(Spieler2, gxpos[i]-5, gypos[i]-5, client); break;
                    case 3:
                    g2.drawImage(Spieler3, gxpos[i]-5, gypos[i]-5, client); break; 
                    case 4:
                    g2.drawImage(Spieler4, gxpos[i]-5, gypos[i]-5, client); break; 
                    case 5:
                    g2.drawImage(Spieler5, gxpos[i]-5, gypos[i]-5, client); break; 
                    case 6:
                    g2.drawImage(Spieler6, gxpos[i]-5, gypos[i]-5, client); break; 
                    case 7:
                    g2.drawImage(Spieler7, gxpos[i]-5, gypos[i]-5, client); break; 
                    case 8:
                    g2.drawImage(Spieler8, gxpos[i]-5, gypos[i]-5, client); break; 
                }
                g2.drawString(nick[i], gxpos[i]+15, gypos[i]+20);
            }
        }
    }
}
