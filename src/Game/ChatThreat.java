package Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import java.io.*;
import java.awt.*;
import org.json.JSONObject;
/**
 *
 * @author Derok
 */
public class ChatThreat extends ConnectionThread
{
    private Chat chat;
    ChatThreat(Chat chat, String ServerIp)
    {
        super(ServerIp, 3116);
        this.chat = chat;
        this.setPriority(MIN_PRIORITY);
    }
    public void run()
    {
        while(running)
        {
            JSONObject response = this.getData();
            switch(response.getString("type")) {
                case "message":
                    String text = response.getString("text");
                    System.out.println(text);
                    this.chat.setnewText(text);
                    break;
            }
        }
    }
    public void writeMessage(String text)
    {
        JSONObject obj = new JSONObject();
        obj.put("type", "write");
        obj.put("text", text);
        System.out.println(text);
        this.sendData(obj);
    } 
    public void offline()
    {
        JSONObject obj = new JSONObject();
        obj.put("type", "offline");
        this.sendData(obj);
        this.stopThread();
    }
}
