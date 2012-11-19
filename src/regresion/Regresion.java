
package regresion;

import java.awt.*;

// Referenced classes of package regresion:
//            RegresionApplet, Estado

public class Regresion extends Canvas
{

    public Regresion(RegresionApplet p)
    {
        ok = false;
        parent = p;
        n = 0;
        setBackground(Color.black);
    }

    void setDatos(String texto)
    {
        n = 0;
        for(int i = 0; i < texto.length(); i++)
            if(texto.charAt(i) == ',')
                n++;

        if(n < 3)
        {
            ok = false;
        } else
        {
            ok = true;
            x = new double[n];
            y = new double[n];
            String dato[] = new String[n];
            int ini = 0;
            for(int i = 0; i < n - 1; i++)
            {
                int fin = texto.indexOf('\n', ini);
                dato[i] = new String(texto.substring(ini, fin));
                ini = fin + 1;
            }

            dato[n - 1] = new String(texto.substring(ini));
            for(int i = 0; i < n; i++)
            {
                ini = dato[i].indexOf(',');
                x[i] = Double.valueOf(dato[i].substring(0, ini).trim()).doubleValue();
                y[i] = Double.valueOf(dato[i].substring(ini + 1).trim()).doubleValue();
            }

        }
    }

    void calcular()
    {
        double pxy = 0.0D;
        double sx = 0.0D;
        double sy = 0.0D;
        double sx2 = 0.0D;
        for(int i = 0; i < n; i++)
        {
            pxy += x[i] * y[i];
            sx += x[i];
            sy += y[i];
            sx2 += x[i] * x[i];
        }

        a = ((double)n * pxy - sx * sy) / ((double)n * sx2 - sx * sx);
        b = (sy - a * sx) / (double)n;
        double sd2 = 0.0D;
        for(int i = 0; i < n; i++)
            sd2 += (y[i] - a * x[i] - b) * (y[i] - a * x[i] - b);

        sigma = Math.sqrt(sd2 / (double)(n - 2));
        double errorA = sigma / Math.sqrt(sx2 - (sx * sx) / (double)n);
        double errorB = Math.sqrt(sx2 / (double)n) * errorA;
        parent.estado.setEstado(a, b, errorA, errorB);
    }

    @Override
    public void paint(Graphics g)
    {
        if(ok)
        {
            dibujaEjes(g);
            dibujaPuntos(g);
            dibujaRecta(g);
        }
    }

    void dibujaEjes(Graphics g)
    {
        xMax = -10000D;
        xMin = 10000D;
        double yMax = -10000D;
        double yMin = 10000D;
        for(int i = 0; i < n; i++)
        {
            if(x[i] > xMax)
                xMax = x[i];
            if(x[i] < xMin)
                xMin = x[i];
            if(y[i] > yMax)
                yMax = y[i];
            if(y[i] < yMin)
                yMin = y[i];
        }

        if(xMin > (double)0)
            xMin = 0.0D;
        if(yMin > (double)0)
            yMin = 0.0D;
        if(yMin > b)
            yMin = b;
        int w = getSize().width;
        int h = getSize().height;
        int cAlto = g.getFontMetrics().getHeight();
        int cAncho = g.getFontMetrics().stringWidth("0");
        escalaX = (double)(w - 4 * cAncho) / (xMax - xMin);
        escalaY = (double)(h - 4 * cAlto) / (yMax - yMin);
        orgX = 2 * cAncho + (int)(Math.abs(xMin) * escalaX);
        orgY = 2 * cAlto + (int)(Math.abs(yMax) * escalaY);
        g.setColor(Color.white);
        g.drawLine(orgX, 0, orgX, h);
        g.drawLine(0, orgY, w, orgY);
    }

    void dibujaPuntos(Graphics g)
    {
        for(int i = 0; i < n; i++)
        {
            int x1 = orgX + (int)(x[i] * escalaX);
            int y1 = orgY - (int)(y[i] * escalaY);
            g.setColor(Color.white);
            g.fillOval(x1 - 2, y1 - 2, 4, 4);
            int y2 = y1 - (int)(sigma * escalaY);
            int y3 = y1 + (int)(sigma * escalaY);
            g.drawLine(x1, y2, x1, y3);
            g.drawLine(x1 - 4, y2, x1 + 4, y2);
            g.drawLine(x1 - 4, y3, x1 + 4, y3);
        }

    }

    void dibujaRecta(Graphics g)
    {
        double y = a * xMin + b;
        int x1 = orgX + (int)(xMin * escalaX);
        int y1 = orgY - (int)(y * escalaY);
        y = a * xMax + b;
        int x2 = orgX + (int)(xMax * escalaX);
        int y2 = orgY - (int)(y * escalaY);
        g.setColor(Color.cyan);
        g.drawLine(x1, y1, x2, y2);
    }

    RegresionApplet parent;
    int n;
    double x[];
    double y[];
    double xMin;
    double xMax;
    double a;
    double b;
    double sigma;
    boolean ok;
    double escalaX;
    double escalaY;
    int orgX;
    int orgY;
}
