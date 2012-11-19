package regresion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Referenced classes of package regresion:
//            RegresionApplet

class Accion
    implements ActionListener
{

    public Accion(RegresionApplet applet)
    {
        this.applet = applet;
    }

    public void actionPerformed(ActionEvent e)
    {
        String titulo = e.getActionCommand();
        if(titulo.equals("Calcular"))
            applet.btnCalcular_actionPerformed(e);
        else
            applet.btnBorrar_actionPerformed(e);
    }

    RegresionApplet applet;
}
