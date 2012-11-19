package Semi_parabolico;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author River
 */
public class Avion {

    private float Vel;
    private float Alt;

    public Avion() {
    }

    public Avion(float Vel, float Alt) {
        this.Vel = Vel;
        this.Alt = Alt;
    }

    public float getAlt() {
        return Alt;
    }

    public float getVel() {
        return Vel;
    }
    
  
}
