
package regresion;

import java.awt.*;

public class Estado extends Canvas
{

    public Estado()
    {
        a = 0.0D;
        b = 0.0D;
        errorA = 0.0D;
        errorB = 0.0D;
        setBackground(Color.cyan);
    }

    void setEstado(double a, double b, double errorA, double errorB)
    {
        this.a = (double)Math.round(a * (double)1000) / (double)1000;
        this.b = (double)Math.round(b * (double)1000) / (double)1000;
        this.errorA = (double)Math.round(errorA * (double)1000) / (double)1000;
        this.errorB = (double)Math.round(errorB * (double)1000) / (double)1000;
        repaint();
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 20);
    }

    @Override
    public void paint(Graphics g)
    {
        int wAncho = getSize().width;
        int cAlto = g.getFontMetrics().getHeight();
        int cAncho = g.getFontMetrics().stringWidth("0");
        if(a != 0.0D || b != 0.0D)
        {
            String str = "a: " + String.valueOf(a);
            g.drawString(str, cAncho, cAlto);
            str = "b: " + String.valueOf(b);
            g.drawString(str, wAncho / 4 + cAncho, cAlto);
            str = "Ea: " + String.valueOf(errorA);
            g.drawString(str, wAncho / 2 + cAncho, cAlto);
            str = "Eb: " + String.valueOf(errorB);
            g.drawString(str, (3 * wAncho) / 4 + cAncho, cAlto);
        }
    }

    double a;
    double b;
    double errorA;
    double errorB;
}

