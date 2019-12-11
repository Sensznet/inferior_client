package Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.Interface.Menue;
import Game.Interface.Login;
import Game.Interface.Chat;
import Game.Interface.Gameview;
import Game.Interface.Overlay;
import Game.Objects.OwnPlayer;
import Game.Objects.World;
import java.applet.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Derok
 */
public class Main extends Panel implements Runnable
{
    private int id, xpos, ypos, zaehler1;
    private Image dbImage;
    private Graphics dbg;
    private Chat chat;
    private Menue menu;
    private World world;
    private WorldThread welt;
    private MonsterThread mobs;
    private Login win;
    private fightsystem fs;
    private boolean loggedin = false, start = true, menueaktive=false;
    private String nick, ServerIp= "localhost";
    private AudioClip sound;
    
    /** Interfaces */
    private Overlay overlay;
    private Gameview gameview;
    private int width;
    private int height;

    public Main(int width, int height)
    {
        this.setSize(width,height);
        world = new World();
        welt = new WorldThread(ServerIp, this, world);
        mobs = new MonsterThread(ServerIp, world);
        menu = new Menue(welt, mobs, this);
        win = new Login(ServerIp, this);
        fs = new fightsystem(world, mobs);       
        chat = new Chat(ServerIp);
        this.width = width;
        this.height = height;
        Cursor c = getToolkit().createCustomCursor( 
        new ImageIcon( "cursor1.png" ).getImage(), 
        new Point(10,10), "Cursor" ); 
        this.setCursor( c );
        File f = new File( "./build/Sound/evtl_inferior_theme.wav" );
        try {
            sound = Applet.newAudioClip(f.toURL());
        } catch (MalformedURLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        sound.loop();
        add(win);
        add(chat);
        add(menu);
        this.setVisible(true);
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
        OwnPlayer player = world.getPlayer();
        if(key==100)
        {
            player.right(true);
        }
        if(key==97)
        {
            player.left(true);
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
            player.up(true);
        }
        if(key==115)
        {
            player.down(true);
        }
        if(key==Event.RIGHT)
        {
            player.right(true);
        }
        if(key==Event.LEFT)
        {
            player.left(true);
        }
        if(key==Event.UP)
        {
            player.up(true);
        }
        if(key==Event.DOWN)
        {
            player.down(true);
        }
        if(key==49)
        {
            /*if(mobs.getclicked()==true)
            {
                fs.attack(1);
            }*/
        }
        return true;
    }
    public boolean keyUp(Event e, int key)
    {
        OwnPlayer player = world.getPlayer();
        if(key==100)
        {
            player.right(false);
        }
        if(key==97)
        {
            player.left(false);
        }
        if(key==119)
        {
            player.up(false);
        }
        if(key==115)
        {
            player.down(false);
        }    
        if(key==Event.RIGHT)
        {
            player.right(false);
        }
        if(key==Event.LEFT)
        {
            player.left(false);
        }
        if(key==Event.UP)
        {
            player.up(false);
        }
        if(key==Event.DOWN)
        {
            player.down(false);
        }   
        return true;
    }
    public boolean mouseUp (Event e, int x, int y)
    { 
        OwnPlayer player = world.getPlayer();
        if(player instanceof OwnPlayer) {
            int xx = player.getXpos();
            int yy = player.getYpos();
            xx = xx+x-512;
            yy = yy+y-384;
            mobs.clickedonMob(xx, yy);
        }
        return true;
    }
    public void run ()
    {
        
        while (true)
        {
            repaint();
            try
            {
                Thread.sleep (16);
            }
            catch (InterruptedException ex)
            {
            }
        }
    }
    
    public void setId(int id) {
        welt.loadData(id);
        gameview = new Gameview(width, height, world);
        overlay = new Overlay(width, height, world);
        add(overlay);
        add(gameview);
        chat.active(nick);
        menu.setchat(chat);
        gameview.setActive();
        overlay.setActive();
        start = false; 
        welt.start();
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
        if(!start) {
            gameview.paint(g2);
            overlay.paint(g2);
        } else {
            g2.fillRect(0,0,width,height);
        }
    }
}
