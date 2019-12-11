/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author ssens
 */
public class World {
    private ArrayList<Object> objects = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Monster> monsters = new ArrayList<>();
    private OwnPlayer player;
    private Creature target;
    public World() {

    }
    
    public void setPlayer(OwnPlayer player) {
        this.player = player;
    }
    
    public OwnPlayer getPlayer() {
        return player;
    }
    
    public ArrayList<Player> getPLayers() {
        return players;
    }
    
    public void addPlayer(Player player) {
        players.add(player);
    }
    
    public void removePlayer(Player player) {
        players.remove(player);
    }
    
    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void addObject(Object object) {
        this.objects.add(object);
    }

    public void removeObject(Object object) {
        this.objects.remove(object);
    }
    
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void addMonster(Monster monster) {
        this.monsters.add(monster);
    }

    public void removeObject(Monster monster) {
        this.monsters.remove(monster);
    }
    
    public void setTarget(Creature creature) {
        target = creature;
    }
    
    public Creature getTarget() {
        return this.target;
    }
    
    public void paintp(Graphics g)
    {
        for(Player player : players) {
            player.paintp(g);
        }
        for(Monster monster : monsters) {
            monster.paintp(g);
        }
        player.paintp(g);
    }
}
