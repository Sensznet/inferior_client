/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author ssens
 */
public class ConnectionThread extends Thread {
    private Socket server = null;
    private DataInputStream in;
    private DataOutputStream out;
    private long last = 0;
    protected Boolean running = true;
    ConnectionThread(String ServerIp, int port) {
        try {
            server = new Socket(ServerIp, port);
            in = new DataInputStream(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected boolean tick() {
        if(last + 20 < System.currentTimeMillis()) {
            last = System.currentTimeMillis();
            return true;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(WorldThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    protected void sendData(JSONObject obj) {
        try {
            this.out.writeUTF(obj.toString());
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected JSONObject getData() {
        JSONObject obj = null;
        try {
            String data = in.readUTF();
            obj = new JSONObject(data);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
    public void stopThread() {
        this.running = false;
        try {
            this.server.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
