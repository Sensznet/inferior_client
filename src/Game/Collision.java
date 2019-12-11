package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Derok
 */
public class Collision {
    boolean collside = false;
    private BufferedImage collpic;
    public Collision() {
         try {
            collpic = ImageIO.read(new File("./build/Bilder/wasser1.bmp"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkCollisionWorld(int xpos, int ypos) {
        int collpixel = collpic.getRGB(xpos, ypos);
        if(collpixel<-1)
        {
            return true;
        }
        return false;
    }
    
    public boolean checkCollisionObejct(int px, int py, int pbreite, int phoehe,
            int ox, int oy, int obreite, int ohoehe)
    {
        if(px<=(ox+obreite)&(px+pbreite)>=ox&py<=(oy+ohoehe)&(py+phoehe)>=oy)
        {
            collside = true;
        }
        else
        {
            collside = false;
        }
        return collside;
    }
    public boolean collisiontestobejctschr(int px, int py, int pbreite, int phoehe,
            int ox, int oy, int obreite, int ohoehe)
    {
        int schraege = (int)(Math.sqrt(((obreite/2)*(obreite/2))+((ohoehe/2)*(ohoehe/2))));
        
        return collside;
    }
}
