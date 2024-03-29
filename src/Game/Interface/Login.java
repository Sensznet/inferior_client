package Game.Interface;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.Main;
import Game.Registrations;
import java.awt.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONObject;
public class Login extends Panel {
    private int version, oldversion=0;
    private String nick, nick1;
    private String pw;
    private int id=0,idds=0, k=0, fortschritt=0;
    private FlowLayout FLl = new FlowLayout();
    private Label LBn = new Label("Nickname:");
    private Label LBp = new Label("Passwort:");
    private Label LBu = new Label("CheckUpdate");
    private JTextField TFn = new JTextField(8);
    private JPasswordField TFp = new JPasswordField(8);
    private Button BTl = new Button("login");
    private Label LBe = new Label("                       ");
    private Button BTr = new Button("Registration");
    private Button BTe = new Button("Exit");
    private Socket server = null;
    private String ServerIp, fortschritt2="0%";
    private boolean update=false;
    private Registrations regist;
    private Main main;
    public Login(String ServerIp, Main main)
    {
        this.ServerIp=ServerIp;
        this.main = main;
        regist = new Registrations(ServerIp);
        this.setBounds(400,234,200,300);
        this.setVisible(true);
        setLayout(FLl);
        add(LBn);
        add(TFn);
        add(LBp);
        add(TFp);
        add(BTl);
        add(LBe);
        add(BTr);
        add(BTe);
        add(LBu);
        try {
            connte();
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    private void sendData(DataOutputStream  out, JSONObject obj) throws IOException {
        out.writeUTF(obj.toString());
    }
    
    private JSONObject getData(DataInputStream in) throws IOException {
        JSONObject obj = null;
        String data = in.readUTF();
        obj = new JSONObject(data);
        return obj;
    }
    
    public boolean action(Event e, Object o)
    {
        if(e.target==BTl)
        {
            if(update==true)
            {
                nick = TFn.getText();
                pw = TFp.getText();
                id = this.connt(nick,pw);
                if(id!=0)
                {
                    nick1=nick;
                    main.setId(id);
                    this.setVisible(false);
                }
                else
                {
                    LBe.setText("wrong nick/pw");
                }
            }
        }
        if(e.target==BTr)
        {
            regist.setVisible(true);
        }
        if(e.target==BTe)
        {
            System.exit(0);
        }
        return true;
    }
    public int getid()
    {
        return id;
    }
    public String getnick()
    {
        return nick1;
    }
    public int connt(String name, String pw) 
    {
        int id = 0;
        try
            {
                server = new Socket(ServerIp, 3115);
                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream  out = new DataOutputStream(server.getOutputStream());
                JSONObject obj = new JSONObject();
                obj.put("type", "login");
                obj.put("name", name);
                obj.put("pw", pw);
                this.sendData(out, obj);
                JSONObject response = this.getData(in);
                if(response.getBoolean("status")) {
                    id = response.getInt("id");
                }
                server.close();
            }
            catch( UnknownHostException e ) 
            { 
                e.printStackTrace(); 
            }
            catch ( IOException e ) 
            { 
                e.printStackTrace(); 
            }
        return id;
    }
    public void connte() throws Exception
    {
        try
            {
                BufferedReader test =
                new BufferedReader(new FileReader("./version.txt"));
                String tmp = test.readLine();
                oldversion = Integer.parseInt(tmp);
                System.out.println(tmp); 
                test.close();
                update=true;
            }
            catch( UnknownHostException e ) 
            { 
                e.printStackTrace(); 
            }
            catch ( IOException e ) 
            { 
                e.printStackTrace(); 
            }
        
    }
    public void extractArchive(File archive, File destDir) throws Exception {
		if (!destDir.exists()) {
			destDir.mkdir();
		}

		ZipFile zipFile = new ZipFile(archive);
		Enumeration entries = zipFile.entries();

		byte[] buffer = new byte[16384];
		int len;
		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();

			String entryFileName = entry.getName();

			File dir = dir = buildDirectoryHierarchyFor(entryFileName, destDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			if (!entry.isDirectory()) {
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(new File(destDir, entryFileName)));

				BufferedInputStream bis = new BufferedInputStream(zipFile
						.getInputStream(entry));

				while ((len = bis.read(buffer)) > 0) {
					bos.write(buffer, 0, len);
				}

				bos.flush();
				bos.close();
				bis.close();
                                
			}
		}
                LBu.setText("Update Completed");
                System.out.println("finished2");
                System.exit(0);
	}

	private File buildDirectoryHierarchyFor(String entryName, File destDir) {
		int lastIndex = entryName.lastIndexOf('/');
		String entryFileName = entryName.substring(lastIndex + 1);
		String internalPathToEntry = entryName.substring(0, lastIndex + 1);
		return new File(destDir, internalPathToEntry);
	}
        public void paint(Graphics g)
        {
            
        }
}
