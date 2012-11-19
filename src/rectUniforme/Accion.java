package rectUniforme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Referenced classes of package rectUniforme:
//            UniformeApplet

class Accion
    implements ActionListener
{

    public Accion(UniformeApplet applet)
    {
        this.applet = applet;
    }

    public void actionPerformed(ActionEvent e)
    {
        String titulo = e.getActionCommand();
        if(titulo.equals("Empieza"))
            applet.btnEmpezar_actionPerformed(e);
        else
        if(titulo.equals("Enviar"))
            applet.btnEnvia_actionPerformed(e);
        else
        if(titulo.equals("Borrar"))
            applet.btnBorra_actionPerformed(e);
        else
            applet.btnNuevo_actionPerformed(e);

        

    }

    UniformeApplet applet;
   
}