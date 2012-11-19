
package Parabólico;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.ImageIcon;


public class MiCanvas extends Canvas
    implements MouseListener, MouseMotionListener
{
     int a=0;
  private ImageIcon imagen2,imagen22,imagen222;
    public MiCanvas(Baloncesto p)
    {
        pol = new Polygon();
        region = new Polygon();
        tray = new Polygon();
        d = 3;
        h = 1.25;
        x = d;
        qw=0;
        wq=0;
        y = 1.8;
        v0 = 8;
        angulo = 1.2217304763960306;
        t = 0.0;
        dt = 0.01;
        rectBola = new Rectangle();
        rectCM = new Rectangle();
        bArrastrar = false;
        parent = p;
        setBackground(Color.white);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    void origenEscalas(Graphics g)
    {

       
        wAncho = getSize().width;
        wAlto = getSize().height;
        cAlto = g.getFontMetrics().getHeight();
        cAncho = g.getFontMetrics().stringWidth("0");
        orgX = wAncho / 2 + 3 * cAncho;
        orgY = wAlto - cAlto - cAncho;
        escalaY = (double)orgY / 3;
        escalaX = (double)(wAncho - orgX) / 30;
        orgYY = wAlto - 2 * cAlto;
        escYY = (double)orgYY / (double)6;
        escXX = (double)(wAncho / 2 - 5 * cAncho) / (double)5;
        orgXX = wAncho / 2 - 5 * cAncho - (int)(0.325 * escXX);
        int x1 = orgX;
        int y1 = orgYY - (int)(2.5 * escYY);
        int ladoX = (int)(3.25 * escXX);
        int ladoY = (int)((double)2 * escYY);
        rectCM.setBounds(orgX + cAncho, cAncho, wAncho - orgX - 2 * cAncho, orgY - cAncho);
        inicioBola();
        curvaVelocidad();
    }

    void setNuevo(double x0, double y0)
    {
        d = x0;
        h = 3.05 - y0;
        bArrastrar = false;
        tray.npoints = 0;
        x = x0;
        y = y0;
        t = 0.0;
        curvaVelocidad();
        repaint();
    }

    void inicioBola()
    {
        int y1 = orgY / 2;
        int x1 = orgX + (wAncho - orgX) / 2;
        rectBola.setBounds(x1 - cAncho / 2, y1 - cAncho / 2, cAncho, cAncho);
    }

    void mover()
    {
        x = d - v0 * Math.cos(angulo) * t;
        y = ((3.05 - h) + v0 * Math.sin(angulo) * t) - (9.8 * t * t) / (double)2;
        if(y < (double)-2 || x < -3)
            parent.hilo.putMsg(0);
        t += dt;
        repaint();
    }

    void dibujaTrayectoria(Graphics g)
    {

        g.setColor(Color.WHITE);
        g.fillRect(wAncho / 2, 0, wAncho / 2, wAlto);
        g.setColor(Color.gray);
        g.fillRect(0, orgYY, wAncho / 2, cAlto);
        g.setColor(Color.black);
        int x2;
        int y2;
        for(int i = 0; i < 6; i++)
        {
            x2 = orgXX - (int)((double)i * escXX);
            y2 = orgYY + cAlto;
            g.drawLine(x2, y2, x2, y2 + 4);
            g.drawString(String.valueOf(i), x2 - cAncho / 2, y2 + cAlto);
        }

        y2 = orgYY - (int)(3.05 * escYY);
        x2 = orgXX + (int)(0.375 * escXX);
        g.drawLine(x2, orgYY, x2, y2);
        for(int i = 1; i < 4; i++)
        {
            y2 = orgYY - (int)((double)i * escYY);
            g.drawLine(x2, y2, x2 + cAncho, y2);
            g.drawString(String.valueOf(i), x2 + cAncho, y2 + cAlto / 2);
        }

        y2 = orgYY - (int)(3.05 * escYY);
        x2 = orgXX + (int)(0.225 * escXX);
       
        g.fillRect(x2, y2 - 1, (int)(0.15 * escXX), 2);
        g.fillOval(x2, y2 - cAncho / 2, cAncho, cAncho);
        x2 = orgXX - (int)(0.225 * escXX);
        g.fillOval(x2 - cAncho, y2 - cAncho / 2, cAncho, cAncho);


         imagen22 = new ImageIcon("src/Parabólico/cesta.jpg");
         g.drawImage(imagen22.getImage(),x2-cAncho-60 , y2-15 ,this);


        if(!bArrastrar )
        {

            x2 = orgXX - (int)(x * escXX);
            y2 = orgYY - (int)(y * escYY);
           /* if(qw==x2 && wq==y2){
             imagen222 = new ImageIcon("src/proyectosimulacion/lanzar2.jpg");
        g.drawImage(imagen222.getImage(),x2-30 , y2-5 ,this);
         
            }
            qw=x2;wq=y2;*/

            tray.addPoint(x2, y2);
            g.setColor(Color.red);
            g.drawPolyline(tray.xpoints, tray.ypoints, tray.npoints);
            int radio = (int)((escXX * 0.25) / (double)2);
           
             imagen2 = new ImageIcon("src/Parabólico/balon2.jpg");
             g.drawImage(imagen2.getImage(),x2-radio , y2-radio ,this);
        }

         g.setColor(Color.black);

        Font oldFont = g.getFont();
        Font fuente = new Font("Times", 1, 14);
        g.setFont(fuente);
        String texto = String.valueOf((double)Math.round(t * (double)100) / (double)100);
        g.drawString("t: " + texto, 2 * cAncho, g.getFontMetrics().getHeight());
        g.setFont(oldFont);
        texto = String.valueOf((double)Math.round(x * (double)100) / (double)100);
        g.drawString("x: " + texto, 2 * cAncho, g.getFontMetrics().getHeight() + cAlto + cAncho);
        texto = String.valueOf((double)Math.round(y * (double)100) / (double)100);
        g.drawString("y: " + texto, 2 * cAncho, g.getFontMetrics().getHeight() + 2 * cAlto + cAncho);
    }

    void velocidadAngulo(Graphics g)
    {

        g.drawLine(orgX, orgY, wAncho, orgY);
        int i = an;
        int x1;
        do
        {
            if(i >= an + 30)
                break;
            x1 = orgX + (int)((double)(i - an) * escalaX);
            if(i >= 90)
                break;
            g.drawLine(x1, orgY, x1, orgY + cAncho);
            String texto = String.valueOf(i);
            g.drawString(texto, x1 - g.getFontMetrics().stringWidth(texto) / 2, (orgY + cAncho + cAlto) - 2);
            for(int j = 1; j < 5; j++)
            {
                x1 = orgX + (int)((double)((i + j) - an) * escalaX);
                g.drawLine(x1, orgY, x1, orgY + cAncho / 2);
            }

            i += 5;
        } while(true);
        g.drawString("Ángulo", wAncho - 6 * cAncho, orgY - 4);
        g.drawLine(orgX, orgY, orgX, 0);
        int y1;
        for(int p = vMin; p < vMin + 5; p++)
        {
            y1 = orgY - (int)((double)(p - vMin) * escalaY);
            g.drawLine(orgX - cAncho, y1, orgX, y1);
            String texto = String.valueOf(p);
            g.drawString(texto, orgX - cAncho - g.getFontMetrics().stringWidth(String.valueOf(p)), y1 + cAlto / 2);
            for(int j = 1; j < 10; j++)
            {
                y1 = orgY - (int)(((double)(p - vMin) + (double)j / (double)10) * escalaY);
                g.drawLine(orgX - cAncho / 2, y1, orgX, y1);
                if(j == 5)
                    g.drawLine(orgX - (3 * cAncho) / 4, y1, orgX, y1);
            }

        }

        g.drawString("Velocidad", orgX + cAncho, cAlto);
        g.setColor(Color.WHITE);
        g.fillPolygon(region);
        g.setColor(Color.WHITE);//color linea de relacion v/angulo
        g.drawPolyline(pol.xpoints, pol.ypoints, pol.npoints);
        g.setColor(Color.blue);
        g.fillOval(rectBola.x, rectBola.y, rectBola.width, rectBola.height);
        x1 = rectBola.x + rectBola.width / 2;
        y1 = rectBola.y + rectBola.height / 2;
        int y2 = y1;
        do
        {
            g.drawLine(x1, y2, x1, y2 + cAncho / 2);
            y2 += cAncho;
        } while(y2 < orgY);
        int x2 = x1;
        do
        {
            g.drawLine(x2, y1, x2 - cAncho / 2, y1);
            x2 -= cAncho;
        } while(x2 > orgX);
    }

    void curvaVelocidad()
    {
        double b = -392 / (9.8 * d);
        double c = (double)1 + (392 * h) / (9.8 * d * d);
        double anFin = ((double)180 * Math.atan((-b + Math.sqrt(b * b - (double)4 * c)) / (double)2)) / 3.1415926535897931;
        double anLimi = ((double)180 * Math.atan((-b - Math.sqrt(b * b - (double)4 * c)) / (double)2)) / 3.1415926535897931;
        double anIni = ((double)180 * Math.atan(Math.tan(Math.asin(0.555)) + ((double)2 * h) / d)) / 3.1415926535897931;
        double anMin = ((double)180 * Math.atan(h / d + Math.sqrt((double)1 + (h * h) / (d * d)))) / 3.1415926535897931;
        double temp = Math.sqrt(9.8 * (Math.sqrt(d * d + h * h) + h));
        vMin = (int)temp;
        if(temp - (double)vMin < 0.25)
            vMin--;
        an = (int)((anIni <= anMin ? anIni : anMin) / (double)5) * 5;
        pol.npoints = 0;
        for(double ang = an; ang <= (double)(an + 30) && ang <= anFin; ang += 0.5D)
            if(ang >= anLimi)
            {
                int y1 = orgY - (int)((funcion(ang, d) - (double)vMin) * escalaY);
                int x1 = orgX + (int)((ang - (double)an) * escalaX);
                pol.addPoint(x1, y1);
            }

        region.npoints = 0;
        for(double ang = anIni; ang <= (double)(an + 30) && ang <= anFin; ang += 0.5)
        {
            int x1 = orgX + (int)((ang - (double)an) * escalaX);
            double anEntrada = Math.atan(Math.tan((ang * 3.1415926535897931) / (double)180) - ((double)2 * h) / d);
            double errAlcance = (0.45 - 0.25 / Math.sin(anEntrada)) / (double)2;
            int y1 = orgY - (int)((funcion(ang, d + errAlcance) - (double)vMin) * escalaY);
            region.addPoint(x1, y1);
        }

        for(double ang = an + 30; ang >= anIni; ang -= 0.5D)
            if(ang <= anFin)
            {
                int x1 = orgX + (int)((ang - (double)an) * escalaX);
                double anEntrada = Math.atan(Math.tan((ang * 3.1415926535897931) / (double)180) - ((double)2 * h) / d);
                double errAlcance = (0.45 - 0.25 / Math.sin(anEntrada)) / (double)2;
                int y1 = orgY - (int)((funcion(ang, d - errAlcance) - (double)vMin) * escalaY);
                region.addPoint(x1, y1);
            }

    }

    double funcion(double an, double L)
    {
        double fi = (an * 3.1415926535897931) / (double)180;
        double v2 = (9.8 * L) / ((double)2 * Math.cos(fi) * Math.cos(fi) * (Math.tan(fi) - h / L));
        return Math.sqrt(v2);
    }

    void dispositivo(Graphics g)
    {
       
        dibujaTrayectoria(g);
        velocidadAngulo(g);
    }

    @Override
    public void paint(Graphics g)
    {
        
        origenEscalas(g);
        update(g);
    }

    @Override
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
        gBuffer.setColor(Color.black);
        dispositivo(gBuffer);
        g.drawImage(imag, 0, 0, null);
    }

    public void mousePressed(MouseEvent ev)
    {
        if(rectBola.contains(ev.getX(), ev.getY()))
        {
            xBola = ev.getX();
            yBola = ev.getY();
            bArrastrar = true;
            parent.hilo.putMsg(0);
            parent.btnEmpieza.setEnabled(false);
            x = d;
            y = 3.05 - h;
            tray.npoints = 0;
        }
    }

    public void mouseReleased(MouseEvent ev)
    {
        if(bArrastrar)
        {
            bArrastrar = false;
            xBola = ev.getX();
            yBola = ev.getY();
            if(xBola < rectCM.x)
                rectBola.x = rectCM.x;
            if(xBola > rectCM.x + rectCM.width)
                rectBola.x = (rectCM.x + rectCM.width) - rectBola.width;
            if(yBola < rectCM.y)
                rectBola.y = rectCM.y;
            if(yBola > rectCM.y + rectCM.height)
                rectBola.y = (rectCM.y + rectCM.height) - rectBola.height;
            v0 = (double)vMin + (double)(orgY - rectBola.y - rectBola.height / 2) / escalaY;
            angulo = (((double)an + (double)((rectBola.x + rectBola.width / 2) - orgX) / escalaX) * 3.1415926535897931) / (double)180;
            parent.btnEmpieza.setEnabled(true);
            repaint();
        }
    }

    public void mouseDragged(MouseEvent ev)
    {
        if(bArrastrar && rectBola.x + rectBola.width / 2 >= rectCM.x && rectBola.x + rectBola.width / 2 <= rectCM.x + rectCM.width && rectBola.y + rectBola.height / 2 >= rectCM.y && rectBola.y + rectBola.height / 2 <= rectCM.y + rectCM.height)
        {
            rectBola.y += ev.getY() - yBola;
            rectBola.x += ev.getX() - xBola;
        }
        xBola = ev.getX();
        yBola = ev.getY();
        repaint();
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

    Baloncesto parent;
    int wAncho;
    int wAlto;
    int cAlto;
    int cAncho;
    double escalaX;
    double escalaY;
    double escXX;
    double escYY;
    int orgX;
    int orgY;
    int orgXX;
    int orgYY;
    Polygon pol;
    Polygon region;
    Polygon tray;
    double d;
    double h;
    double x;
    double y;
    double v0;
    double angulo;
    double t,qw,wq;
    double dt;
    int an;
    int vMin;
    URL url;
    Rectangle rectBola;
    int xBola;
    int yBola;
    Rectangle rectCM;
    boolean bArrastrar;
    Image imag,image;
    Graphics gBuffer;
    Dimension dim;
}
