package Parabólico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Accion
    implements ActionListener
{

    public Accion(Baloncesto a)
    {
        this.ap = a;
    }

    public void actionPerformed(ActionEvent e)
    {
        String titulo = e.getActionCommand();
        if(titulo.equals("Lanza"))
            ap.btnEmpieza_actionPerformed(e);
        else
        if(titulo.equals("Paso"))
            ap.btnPaso_actionPerformed(e);
        else
            ap.btnPausa_actionPerformed(e);
    }

    Baloncesto ap;
}
