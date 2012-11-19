// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 04/10/2009 17:28:42
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   RozamientoApplet9.java

package Rozamiento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Referenced classes of package rozamiento9:
//            RozamientoApplet9

class Accion
    implements ActionListener
{

    public Accion(Rozamiento applet)
    {
        this.applet = applet;
    }

    public void actionPerformed(ActionEvent e)
    {
        String titulo = e.getActionCommand();
        if(titulo.equals("Nuevo"))
            applet.btnNuevo_actionPerformed(e);
        else
        if(titulo.equals("Gr\341fica"))
            applet.btnGrafica_actionPerformed(e);
        else
        if(titulo.equals("Paso"))
            applet.btnPaso_actionPerformed(e);
        else
        if(titulo.equals("Respuesta"))
            applet.btnRespuesta_actionPerformed(e);
        else
            applet.btnPausa_actionPerformed(e);
    }

    Rozamiento applet;
}