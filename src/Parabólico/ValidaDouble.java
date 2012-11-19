// Decompiled by DJ v3.11.11.95 Copyright 2009 Atanas Neshkov  Date: 22/09/2009 09:37:24 p.m.
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   BaloncestoApplet3.java

package Parabólico;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.EventObject;

class ValidaDouble extends FocusAdapter
{

    ValidaDouble()
    {
    }

    public void focusLost(FocusEvent ev)
    {
        TextField tEntrada = (TextField)ev.getSource();
        try
        {
            Double.valueOf(tEntrada.getText()).doubleValue();
        }
        catch(NumberFormatException e)
        {
            tEntrada.requestFocus();
            tEntrada.selectAll();
        }
    }
}
