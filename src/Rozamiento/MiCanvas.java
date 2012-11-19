// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 04/10/2009 17:29:21
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   MiCanvas.java

package Rozamiento;

import java.awt.*;
import java.awt.event.*;

// Referenced classes of package rozamiento9:
//            Pesa, RozamientoApplet9, Hilo

public class MiCanvas extends Canvas
    implements MouseListener, MouseMotionListener
{

    public MiCanvas(Rozamiento p)
    {
        pol = new Polygon();
        xBloque = 0.0D;
        masaBloque = 1.0D;
        colores = (new Color[] {
            Color.red, Color.gray, Color.blue
        });
        bArrastrar = false;
        rectPesa = new Rectangle[masas.length];
        rectEnganche = new Rectangle();
        nColorPesas = new int[masas.length];
        orgXpesa = new int[masas.length];
        orgYpesa = new int[masas.length];
        pesa = new Pesa[masas.length * 4];
        x = new double[4 * masas.length];
        y = new double[4 * masas.length];
        bGrafica = false;
        bMover = false;
        parent = p;
        setBackground(Color.white);
        addMouseListener(this);
        addMouseMotionListener(this);
        for(int i = 0; i < masas.length; i++)
            rectPesa[i] = new Rectangle();

        mCinetico = (double)Math.round((0.29999999999999999D + 0.40000000000000002D * Math.random()) * (double)100) / (double)100;
    }

    void origenEscalas(Graphics g)
    {
        wAncho = getSize().width;
        wAlto = getSize().height;
        cAlto = g.getFontMetrics().getHeight();
        cAncho = g.getFontMetrics().stringWidth("0");
        orgX = 3 * cAlto;
        escalaX = (double)((3 * wAncho) / 4 - orgX) / 1.0D;
        orgY = wAlto / 4;
        xExtremo = orgX + (int)(escalaX * 1.0D) + 2 * cAlto;
        yExtremo = orgY + cAlto;
        for(int i = 0; i < masas.length; i++)
        {
            orgXpesa[i] = orgX + cAncho + i * 3 * cAlto;
            orgYpesa[i] = wAlto - 2 * cAlto - cAncho;
        }

    }

    void setNuevo(double masaBloque)
    {
        this.masaBloque = masaBloque;
        yExtremo = orgY + cAlto;
        xExtremo = orgX + (int)(escalaX * 1.0D) + 2 * cAlto;
        rectEnganche.setBounds(xExtremo, yExtremo, cAncho, cAncho);
        bGrafica = false;
        bMover = false;
        fuerza = 0.0D;
        totalPesas = 0;
        for(int i = 0; i < masas.length; i++)
        {
            rectPesa[i].setBounds(orgXpesa[i], orgYpesa[i], 2 * cAlto, cAncho);
            nColorPesas[i] = 0;
        }

        xBloque = 0.0D;
        t = 0.0D;
        repaint();
    }

    void setGrafica()
    {
        bGrafica = true;
        repaint();
    }

    void dispositivo(Graphics g)
    {
        g.setColor(Color.lightGray);
        int x1 = orgX + (int)(1.0D * escalaX);
        g.fillRect(0, orgY, x1, cAlto + cAncho);
        g.setColor(Color.black);
        g.drawRect(0, orgY, x1, cAlto + cAncho);
        for(int i = 0; i < 10; i++)
        {
            x1 = orgX + (int)((escalaX * (double)i) / (double)10);
            g.drawLine(x1, orgY, x1, orgY + cAncho);
            String str = String.valueOf((double)i / (double)10);
            g.drawString(str, x1 - g.getFontMetrics().stringWidth(str) / 2, (orgY + cAncho + cAlto) - 2);
        }

        x1 = orgX + (int)(1.0D * escalaX);
        g.drawOval(x1, orgY - cAlto, 2 * cAlto, 2 * cAlto);
        double angulo = -xBloque / ((double)(2 * cAlto) / escalaX);
        int y1;
        int x2;
        for(int i = 0; i < 8; i++)
        {
            x2 = x1 + cAlto + (int)((double)cAlto * Math.cos(angulo + ((double)i * 3.1415926535897931D) / (double)4));
            y1 = orgY - (int)((double)cAlto * Math.sin(angulo + ((double)i * 3.1415926535897931D) / (double)4));
            g.drawLine(x1 + cAlto, orgY, x2, y1);
        }

        g.setColor(Color.gray);
        y1 = cAlto + (int)((masaBloque - 0.5D) * (double)cAlto);
        x2 = orgX + (int)(xBloque * escalaX);
        g.fillRect(x2 - cAlto, orgY - y1, cAlto, y1);
        g.setColor(Color.black);
        g.drawRect(x2 - cAlto, orgY - y1, cAlto, y1);
        g.drawLine(x2, orgY - cAlto, x1 + cAlto, orgY - cAlto);
        yExtremo = orgY + cAlto + (int)(xBloque * escalaX);
        for(int i = 0; i < masas.length; i++)
            if((!bArrastrar || i != indicePesa) && nColorPesas[i] < 4)
            {
                pesaInicial(g, rectPesa[i], colores[i]);
                g.setColor(Color.black);
                g.drawString(String.valueOf(masas[i]) + " g", orgXpesa[i], orgYpesa[i] + 2 * cAlto);
            }

        if(bMover)
        {
            for(int i = 0; i < totalPesas; i++)
            {
                Point p = new Point(xExtremo - cAlto, yExtremo + cAncho + i * (2 * cAncho + cAncho / 2));
                pesa[i].mover(p);
                pesa[i].muestraPesa(g);
            }

        } else
        {
            for(int j = 0; j < totalPesas; j++)
                pesa[j].muestraPesa(g);

        }
        yExtremo = orgY + cAlto + (int)(xBloque * escalaX);
        g.setColor(Color.black);
        g.drawLine(xExtremo, orgY, xExtremo, yExtremo);
        g.drawLine(xExtremo, yExtremo - cAlto, xExtremo, yExtremo);
        g.setColor(Color.red);
        g.drawOval(xExtremo - cAncho / 2, yExtremo - cAncho / 2, cAncho, cAncho);
        if(bMover)
        {
            g.setColor(Color.black);
            Font oldFont = g.getFont();
            Font fuente = new Font("Times", 1, 14);
            g.setFont(fuente);
            String str = String.valueOf((double)Math.round(t * (double)100) / (double)100);
            int alto = g.getFontMetrics().getHeight();
            g.drawString("t(s): " + str, cAlto, orgY + 2 * cAlto + alto);
            str = String.valueOf((double)Math.round(xBloque * (double)100) / (double)100);
            g.drawString("x(m): " + str, cAlto, orgY + 2 * cAlto + 2 * alto);
            g.setFont(oldFont);
        }
    }

    void pesaInicial(Graphics g, Rectangle rect, Color color)
    {
        g.setColor(color);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        g.setColor(Color.black);
        g.drawArc((rect.x + rect.width / 2) - cAncho / 2, rect.y - cAncho, cAncho, cAncho, 270, 270);
        g.drawOval((rect.x + rect.width / 2) - cAncho / 2, rect.y + rect.height, cAncho, cAncho);
    }

    void mover()
    {
        t += 0.0050000000000000001D;
        xBloque = (((fuerza - mCinetico * masaBloque * 10D) / (fuerza / 10D + masaBloque)) * t * t) / (double)2;
        if(xBloque > 1.0D)
            parent.hilo.putMsg(0);
        repaint();
    }

    public void paint(Graphics g)
    {
        origenEscalas(g);
        dispositivo(g);
    }

    public void update(Graphics g)
    {
        Dimension d = getSize();
        if(gBuffer == null || d.width != dim.width || d.height != dim.height)
        {
            dim = d;
            imag = createImage(d.width, d.height);
            gBuffer = imag.getGraphics();
        }
        gBuffer.setColor(getBackground());
        gBuffer.fillRect(0, 0, d.width, d.height);
        if(bGrafica)
        {
            grafica(gBuffer);
            bGrafica = false;
        } else
        {
            dispositivo(gBuffer);
        }
        g.drawImage(imag, 0, 0, null);
    }

    public void mousePressed(MouseEvent ev)
    {
        int i = 0;
        do
        {
            if(i >= masas.length)
                break;
            if(rectPesa[i].contains(ev.getX(), ev.getY()))
            {
                bArrastrar = true;
                pesa[totalPesas] = new Pesa(cAncho, masas[i], colores[i], rectPesa[i]);
                totalPesas++;
                indicePesa = i;
                break;
            }
            i++;
        } while(true);
    }

    public void mouseReleased(MouseEvent ev)
    {
        if(bArrastrar)
        {
            bArrastrar = false;
            boolean bEnganchada = false;
            if(rectEnganche.intersects(pesa[totalPesas - 1].engancheSup))
            {
                fuerza += (10D * (double)pesa[totalPesas - 1].masa) / (double)1000;
                double fRoza = (mCinetico + 0.20000000000000001D) * masaBloque * 10D;
                if(fuerza > fRoza)
                {
                    fRoza = mCinetico * masaBloque * 10D;
                    bMover = true;
                    parent.btnPausa.setEnabled(true);
                    parent.btnPaso.setEnabled(true);
                } else
                {
                    fRoza = fuerza;
                    parent.setDatos(fuerza, fRoza);
                    x[totalPesas - 1] = fuerza;
                    y[totalPesas - 1] = fRoza;
                }
                yExtremo = orgY + cAlto;
                Point p = new Point(xExtremo - cAlto, yExtremo + cAncho + (totalPesas - 1) * (2 * cAncho + cAncho / 2));
                pesa[totalPesas - 1].mover(p);
                rectEnganche.setLocation(xExtremo - cAncho / 2, yExtremo + totalPesas * (2 * cAncho + cAncho / 2));
                nColorPesas[indicePesa]++;
                rectEnganche = pesa[totalPesas - 1].engancheInf;
                bEnganchada = true;
            }
            if(!bEnganchada)
                totalPesas--;
            if(bMover)
                parent.hilo.putMsg(2);
        }
        repaint();
    }

    public void mouseDragged(MouseEvent ev)
    {
        if(bArrastrar)
        {
            pesa[totalPesas - 1].mover(ev.getPoint());
            repaint();
        }
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
    }

    void grafica(Graphics g)
    {
        int orgXX = 5 * cAncho;
        int orgYY = wAlto - 2 * cAlto;
        double escalaX = (double)(wAncho - orgXX - 3 * cAncho) / (double)30;
        double escalaY = (double)(wAlto - 3 * cAlto) / 25D;
        g.setColor(Color.black);
        g.drawLine(orgXX - cAncho, orgYY, wAncho, orgYY);
        g.drawString("F (N)", wAncho - 4 * cAncho, orgYY - cAncho);
        int x1;
        for(int i = 0; i <= 30; i += 5)
        {
            x1 = orgXX + (int)((double)i * escalaX);
            g.drawLine(x1, orgYY + cAncho, x1, orgYY);
            String str = String.valueOf(i);
            g.drawString(str, x1 - g.getFontMetrics().stringWidth(str) / 2, orgYY + cAlto);
            for(int j = 1; j < 5; j++)
            {
                x1 = orgXX + (int)((double)(i + j) * escalaX);
                g.drawLine(x1, orgYY + cAncho / 2, x1, orgYY);
            }

        }

        g.drawLine(orgXX, 0, orgXX, wAlto - cAlto);
        g.drawString("Fr (N)", orgXX + cAncho, cAlto);
        int y1;
        for(int i = 0; i <= 25; i += 5)
        {
            y1 = orgYY - (int)((double)i * escalaY);
            g.drawLine(orgXX, y1, orgXX - cAncho, y1);
            String str = String.valueOf(i);
            g.drawString(str, orgXX - g.getFontMetrics().stringWidth(str) - cAncho, y1 + cAlto / 2);
            for(int j = 1; j < 5; j++)
            {
                y1 = orgYY - (int)((double)(i + j) * escalaY);
                g.drawLine(orgXX - cAncho / 2, y1, orgXX, y1);
            }

        }

        g.setColor(Color.red);
        for(int i = 0; i < totalPesas; i++)
        {
            x1 = orgXX + (int)(x[i] * escalaX);
            y1 = orgYY - (int)(y[i] * escalaY);
            g.fillOval(x1 - 2, y1 - 2, 4, 4);
        }

        double fRoza = (mCinetico + 0.20000000000000001D) * masaBloque * 10D;
        x1 = orgXX + (int)(fRoza * escalaX);
        y1 = orgYY - (int)(fRoza * escalaY);
        g.setColor(Color.blue);
        g.drawLine(orgXX, orgYY, x1, y1);
        fRoza = mCinetico * masaBloque * 10D;
        y1 = orgYY - (int)(fRoza * escalaY);
        g.drawLine(x1, y1, wAncho, y1);
    }

    void respuesta()
    {
        Graphics g = getGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, wAncho, wAlto);
        g.setColor(Color.black);
        Font oldFont = getFont();
        Font fuente = new Font("Times", 1, 16);
        g.setFont(fuente);
        int alto = g.getFontMetrics().getHeight();
        String texto = "Coeficiente cin\351tico: " + (double)Math.round(mCinetico * (double)100) / (double)100;
        int ancho = g.getFontMetrics().stringWidth(texto);
        g.drawString(texto, wAncho / 2 - ancho / 2, wAlto / 2 - (3 * alto) / 4);
        texto = "Coeficiente est\341tico: " + (double)Math.round((mCinetico + 0.20000000000000001D) * (double)100) / (double)100;
        ancho = g.getFontMetrics().stringWidth(texto);
        g.drawString(texto, wAncho / 2 - ancho / 2, wAlto / 2 + (3 * alto) / 4);
        g.setFont(oldFont);
        g.dispose();
    }

    Rozamiento parent;
    int wAncho;
    int wAlto;
    int cAlto;
    int cAncho;
    int orgX;
    int orgY;
    double escalaX;
    double escalaY;
    Polygon pol;
    double fuerza;
    double xBloque;
    double t;
    final double dt = 0.0050000000000000001D;
    double masaBloque;
    double mCinetico;
    Color colores[];
    int masas[] = {
        25, 100, 500
    };
    boolean bArrastrar;
    final int NPESAS = 4;
    Rectangle rectPesa[];
    Rectangle rectEnganche;
    int xExtremo;
    int yExtremo;
    int nColorPesas[];
    int orgXpesa[];
    int orgYpesa[];
    int indicePesa;
    Pesa pesa[];
    int totalPesas;
    double x[];
    double y[];
    boolean bGrafica;
    boolean bMover;
    Image imag;
    Graphics gBuffer;
    Dimension dim;
}
