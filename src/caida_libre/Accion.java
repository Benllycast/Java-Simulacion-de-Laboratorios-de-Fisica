/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package caida_libre;

/**
 *
 * @author Benlly
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Referenced classes of package cinema1:
//            CinemaApplet1

class Accion implements ActionListener {

    Ventana applet;

    public Accion(Ventana caidaLibre)
    {
        applet = caidaLibre;
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        String s = actionevent.getActionCommand();
        if(s.equals("Empieza")) {
            applet.btnEmpieza_actionPerformed(actionevent);
        }
        else
        if(s.equals("Paso")) {
            applet.btnPaso_actionPerformed(actionevent);
        }
        else {
            applet.btnPausa_actionPerformed(actionevent);
        }
    }

    
}