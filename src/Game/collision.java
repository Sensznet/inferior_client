package Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Derok
 */
public class collision {
    boolean collside = false;
    public boolean collisiontestobejct(int px, int py, int pbreite, int phoehe,
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
