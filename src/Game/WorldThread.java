package Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.Objects.OwnPlayer;
import Game.Objects.Player;
import Game.Objects.World;
import java.util.ArrayList;
import org.json.*;
/**
 *
 * @author Derok
 */
public class WorldThread extends ConnectionThread
{
    private World world;
    private final Main client;
    WorldThread(String ServerIp, Main client, World world)
    {
        super(ServerIp, 3114);
        this.client = client;
        this.world = world;
    }
    @Override
    public void run()
    {
        while(running) {
            if(this.tick()) {
                OwnPlayer player = world.getPlayer();
                if(player instanceof OwnPlayer) {
                    JSONObject obj = new JSONObject();
                    obj.put("type", "refresh");
                    obj.put("xpos", player.getXpos());
                    obj.put("ypos", player.getYpos());
                    obj.put("direction", player.getDirection());
                    this.sendData(obj);
                    JSONObject response = this.getData();
                    player.setMaxHp(response.getInt("maxHp"));
                    player.setCurHp(response.getInt("curHp"));
                    player.setMaxMana(response.getInt("maxMana"));
                    player.setCurMana(response.getInt("curMana"));
                    player.setCurExp(response.getInt("curExp"));
                    player.setMaxExp(response.getInt("maxExp"));
                    JSONArray players = response.getJSONArray("players");
                    ArrayList<Integer> activeIds = new ArrayList<>();
                    for(Object OPlayer : players) {
                        JSONObject jPlayer = (JSONObject)OPlayer;
                        Boolean found = false;
                        int id = jPlayer.getInt("id");
                        activeIds.add(id);
                        for(Player tplayer : world.getPLayers()) {
                            if(tplayer.getId() == id) {
                                found = true;
                                tplayer.setXpos(jPlayer.getInt("xpos"));
                                tplayer.setYpos(jPlayer.getInt("ypos"));
                                tplayer.setDirection(jPlayer.getInt("direction"));
                                tplayer.setMaxHp(jPlayer.getInt("maxHp"));
                                tplayer.setCurHp(jPlayer.getInt("curHp"));

                            }
                        }
                        if(!found) {
                            world.addPlayer(new Player(client, id, jPlayer.getString("name"), jPlayer.getInt("xpos"), jPlayer.getInt("ypos"), 
                                    jPlayer.getInt("direction"), jPlayer.getInt("maxHp"), jPlayer.getInt("curHp"), jPlayer.getInt("lvl")));
                        }
                    }
                    for(Player tplayer : world.getPLayers()) {
                        boolean found = false;
                        for(Integer activeId : activeIds) {
                            if(activeId == player.getId()) {
                                found = true;
                            }
                        }
                        if(!found) {
                            world.removePlayer(tplayer);
                        }
                    }
                }
            }
        }
        
    }
    public void getstats()
    {
        OwnPlayer player = world.getPlayer();
        JSONObject obj = new JSONObject();
        obj.put("type", "attributes");
        this.sendData(obj);
        JSONObject response = this.getData();
        player.setAgi(response.getInt("agi"));
        player.setInt(response.getInt("int"));
        player.setStr(response.getInt("str"));
    }
    public void loadData(int id) {
        JSONObject obj = new JSONObject();
        obj.put("type", "initial");
        obj.put("id", id);
        this.sendData(obj);
        JSONObject response = this.getData();
        OwnPlayer player = new OwnPlayer(world, client, id, response.getString("name"), response.getInt("xpos"), response.getInt("ypos"), response.getInt("direction"), response.getInt("maxHp"),
                response.getInt("curHp"), response.getInt("maxMana"), response.getInt("curMana"), response.getInt("maxExp"), response.getInt("curExp"), response.getInt("lvl"), 
                response.getInt("agi"), response.getInt("str"), response.getInt("int"));
        player.setXpos(response.getInt("xpos"));
        player.setYpos(response.getInt("ypos"));
        world.setPlayer(player);
        
        
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
    }
    public void offline()
    {
        JSONObject obj = new JSONObject();
            obj.put("type", "offline");
            this.sendData(obj);
            this.stopThread();
    }
}
