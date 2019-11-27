package Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.net.*;
import javax.swing.*;
/**
 *
 * @author Derok
 */
public class Menue extends Panel
{
    private FlowLayout FLr = new FlowLayout();
    private Button BTe = new Button("back to game");
    private Button BTee = new Button("Exit");
    private WorldThread welt;
    private MonsterThread mobs;
    private Chat chat;
    private Image background;
    private Main client;
    Menue(WorldThread welt, MonsterThread mobs, Main client)
    {
        this.welt = welt;
        this.mobs = mobs;
        this.client = client;
        this.setBounds(462,250,100,200);
        this.setVisible(false);
        background = Toolkit.getDefaultToolkit().getImage("./build/Bilder/HintergrundOptions.png");
        setLayout(FLr);
        add(BTe);
        add(BTee);
        
    }
    public void setchat(Chat chat)
    {
        this.chat = chat;
    }
    public boolean action(Event e, Object o)
    {
        if(e.target==BTe)
        {
            this.setVisible(false);
        }
        if(e.target==BTee)
        {
            welt.offline();
            chat.offline();
            mobs.offline();
            System.exit(0);
        }
        return true;
    }
     public boolean keyDown(Event e, int key)
    {
        if(key==Event.ESCAPE)
        {
           this.setVisible(false); 
        }
        return true;
    }
    void paintg(Graphics g)
    {
        
    }
}
