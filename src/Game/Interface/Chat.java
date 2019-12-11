package Game.Interface;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.ChatThreat;
import Game.ChatThreat;
import java.net.*;
import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;


/**
 *
 * @author Derok
 */
public class Chat extends JPanel
{
    private String text, nick;
    public Login login;
    private ChatThreat chatT;
    private Socket server = null;
    private FlowLayout FLc = new FlowLayout();
    private Button BTs = new Button("Send");
    private JTextField TFt = new JTextField(20);
    private TextArea TA = new TextArea("",5,10,1);
    String ip;
    public Chat(String ip)
    {
        
        this.ip = ip;
        this.setVisible(false);
        this.setBounds(0,562,300,180);
        TA.setBounds(0, 0, 290, 140);
        setLayout(FLc);
        add(TFt);
        TFt.addKeyListener(
         new KeyAdapter() {
            public void keyPressed(KeyEvent event)
            {
                if(event.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    text=TFt.getText();
                    chatT.writeMessage(nick+": "+text);
                    TFt.setText("");
                    repaint(); 
                }
            }
        });
        BTs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text=TFt.getText();
                chatT.writeMessage(nick+": "+text);
                TFt.setText("");
                repaint(); 
            }
        });
        add(BTs);
        add(TA);
    }
   
    public void active(String nick1)
    {
        this.setVisible(true);
        nick = nick1;
        chatT = new ChatThreat(this,ip);
        chatT.start();
    }
    public void offline()
    {
        chatT.offline();
    }
    public void setnewText(String text)
    {
        String oldText = TA.getText();
        TA.setText(text+" \n" + oldText);
        repaint(); 
    }
}
