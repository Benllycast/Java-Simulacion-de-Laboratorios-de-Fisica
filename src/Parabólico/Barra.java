package Parabólico;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

class Barra
    implements AdjustmentListener
{

    public Barra(Baloncesto ap)
    {
        this.a = ap;
    }

    public void adjustmentValueChanged(AdjustmentEvent e)
    {
        Object obj = e.getSource();
        if(obj == a.sbAltura)
            a.sbAltura_adjustmentValueChanged(e);
        else
            a.sbDistancia_adjustmentValueChanged(e);
    }

    Baloncesto a;
}
