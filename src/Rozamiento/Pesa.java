// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 04/10/2009 17:22:54
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Pesa.java

package Rozamiento;

import java.awt.*;

public class Pesa
{

    public Pesa(int cAncho, int masa, Color color, Rectangle rect1)
    {
        this.cAncho = cAncho;
        this.masa = masa;
        this.color = color;
        rect = new Rectangle(rect1);
        engancheSup = new Rectangle((rect.x + rect.width / 2) - cAncho / 2, rect.y - cAncho, cAncho, cAncho);
        engancheInf = new Rectangle((rect.x + rect.width / 2) - cAncho / 2, rect.y + rect.height, cAncho, cAncho);
    }

    void muestraPesa(Graphics g)
    {
        g.setColor(color);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        g.setColor(Color.black);
        g.drawArc((rect.x + rect.width / 2) - cAncho / 2, rect.y - cAncho, cAncho, cAncho, 270, 270);
        g.drawOval((rect.x + rect.width / 2) - cAncho / 2, rect.y + rect.height, cAncho, cAncho);
    }

    void mover(Point p)
    {
        int dx = p.x - rect.x;
        int dy = p.y - rect.y;
        rect.translate(dx, dy);
        engancheSup.translate(dx, dy);
        engancheInf.translate(dx, dy);
    }

    Color color;
    int masa;
    Rectangle rect;
    Rectangle engancheSup;
    Rectangle engancheInf;
    int cAncho;
}