/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Objects;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author ssens
 */
public class Object {
    private Image Haus1, Haus2, Haus3, Haus4, baum1, baum2; 
    public Object() {
        Haus1 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/haus1.png");
        Haus2 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/haus2.png");
        Haus3 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/haus3.png");
        Haus4 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/haus4.png");
        baum1 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Baum1.png");
        baum2 = Toolkit.getDefaultToolkit().getImage("./build/Bilder/Baum2.png");
    }
}
