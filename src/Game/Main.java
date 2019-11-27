package Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.Objects.Player;
import java.applet.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
/**
 *
 * @author Derok
 */
public class Main extends Panel implements Runnable
{
    private int id, xpos, ypos, zaehler1;
    private Image dbImage;
    private Graphics dbg;
    private Socket server = null;
    private Chat chat;
    private Menue menu;
    private WorldThread welt;
    private MonsterThread mobs;
    private Player player;
    private Login win;
    private fightsystem fs;
    private Interfacee face;
    private Skillbar bar;
    private Minimap map;
    private boolean loggedin = false, start = true, menueaktive=false;
    private Image Haus1, Haus2, Haus3, Haus4, Hintergrund, Wasser, Weg, baum1, baum2; 
    private String nick, ServerIp= "localhost";
    private AudioClip sound;
    public Main()
    {
        this.setSize(1024,768);
        Hintergrund = Toolkit.getDefaultToolkit().getImage("./build/Bilder/BG3000.jpg");
        Haus1 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/haus1.png");
        Haus2 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/haus2.png");
        Haus3 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/haus3.png");
        Haus4 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/haus4.png");
        Wasser = Toolkit.getDefaultToolkit().getImage("./build/Bilder/wasser2.png");
        baum1 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Baum1.png");
        baum2 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Baum2.png");
        Weg = Toolkit.getDefaultToolkit().getImage("./build/Bilder/weg3000.png");
        welt = new WorldThread(ServerIp);
        face = new Interfacee(welt);
        mobs = new MonsterThread(ServerIp, face);
        menu = new Menue(welt, mobs, this);
        player = new Player(welt, face, mobs, this);
        win = new Login(ServerIp);
        fs = new fightsystem(mobs, player);       
        chat = new Chat(ServerIp);
        bar = new Skillbar();
        map = new Minimap();
        Cursor c = getToolkit().createCustomCursor( 
        new ImageIcon( "cursor1.png" ).getImage(), 
        new Point(10,10), "Cursor" ); 
        this.setCursor( c );
        File f = new File( "./build/Sound/evtl_inferior_theme.wav" );
        try {
            sound = Applet.newAudioClip(f.toURL());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Inferior.class.getName()).log(Level.SEVERE, null, ex);
        }
        sound.loop();
        add(win);
        add(chat);
        add(bar);
        add(map);
        add(menu);
        start();
    } 
    public void start() 
    {
        Thread th = new Thread (this);
        th.start();
        mobs.start();
    }   
    public boolean keyDown(Event e, int key)
    {
        if(key==100)
        {
            player.kright(true);
        }
        if(key==97)
        {
            player.kleft(true);
        }
        if(key==Event.ESCAPE)
        {
           if(menueaktive==true)
           {
               menu.setVisible(false);
               menueaktive=false;
           }
           else
           {
               menu.setVisible(true);
               menueaktive=true;
           }
        }
        if(key==119)
        {
            player.kup(true);
        }
        if(key==115)
        {
            player.kdown(true);
        }
        if(key==Event.RIGHT)
        {
            player.kright(true);
        }
        if(key==Event.LEFT)
        {
            player.kleft(true);
        }
        if(key==Event.UP)
        {
            player.kup(true);
        }
        if(key==Event.DOWN)
        {
            player.kdown(true);
        }
        if(key==49)
        {
            if(mobs.getclicked()==true)
            {
                fs.attack(1);
            }
        }
        return true;
    }
    public boolean keyUp(Event e, int key)
    {
        if(key==100)
        {
            player.kright(false);
        }
        if(key==97)
        {
            player.kleft(false);
        }
        if(key==119)
        {
            player.kup(false);
        }
        if(key==115)
        {
            player.kdown(false);
        }    
        if(key==Event.RIGHT)
        {
            player.kright(false);
        }
        if(key==Event.LEFT)
        {
            player.kleft(false);
        }
        if(key==Event.UP)
        {
            player.kup(false);
        }
        if(key==Event.DOWN)
        {
            player.kdown(false);
        }   
        return true;
    }
    public boolean mouseUp (Event e, int x, int y)
    { 
        int xx = player.getxpos();
        int yy = player.getypos();
        xx = xx+x-512;
        yy = yy+y-384;
        mobs.clickedonMob(xx, yy);
        return true;
    }
    public void run ()
    {
        while (true)
        {
            if(loggedin==true)
            {
                if(start==true)
                {
                    welt.loadData(player);
                    chat.active(nick);
                    menu.setchat(chat);
                    bar.setaktive();
                    map.setaktive();
                    start = false; 
                    welt.start();
                }
                repaint();
            }
            else
            {
                id=win.getid();
                if(id!=0)
                {
                    welt.setid(id);
                    player.setid(id);
                    nick = win.getnick();                
                    loggedin=true; 
                }                
            }
            
            try
            {
                Thread.sleep (35);
            }
            catch (InterruptedException ex)
            {
               
            }
        }
    }
    
    public void update (Graphics g)
    {
        if (dbImage == null)
        {
                dbImage = createImage (this.getSize().width, this.getSize().height);
                dbg = dbImage.getGraphics ();
        }
        dbg.setColor (getBackground ());
        dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);
        dbg.setColor (getForeground());
        paint (dbg);
        g.drawImage (dbImage, 0, 0, this);
    }
    
    public void paint (Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        if(loggedin==false)
        {
            g2.fillRect(0,0,1024,768);
            g2.drawImage(Hintergrund, -30000, -30000, this);
            g2.drawImage(Weg, -30000, -30000, this);
            g2.drawImage(Wasser, -30000, -30000, this);
        }
        if(loggedin==true)
        {
            player.move();
            g2.drawImage(Hintergrund, -512, -384, this);
            g2.drawImage(Weg, -512, -384, this);
            g2.drawImage(Wasser, -512, -384, this);
            mobs.paintp(g);
            welt.paintp(g);
            int k=0;
            while(k<=zaehler1-1)
            {
                //objektss[k].paint(g);
                k++;
            }
            player.paintp(g);
            face.paintg(g);
            menu.paintg(g);
        }
    }

}
