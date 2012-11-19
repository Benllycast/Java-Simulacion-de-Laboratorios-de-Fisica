package rectUniforme;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

// Referenced classes of package rectUniforme:
//            UniformeApplet, Hilo

public class MiCanvas extends Canvas
    implements MouseListener, MouseMotionListener
{

    
    public MiCanvas(UniformeApplet p)
    {
        t = 0.0D;
        tReloj = 0.0D;
        dt = 0.050000000000000003D;
        marca = new Rectangle();
        bNuevo = true;
        rnd = new Random();
        bArrastrar = false;
        bTerminar = false;
        v = 1.0D + 4D * rnd.nextDouble();
        xInicial = x = -3D;
        parent = p;
        setBackground(Color.black);
        marca.setSize(8, 16);
        addMouseListener(this);
        addMouseMotionListener(this);

    }

   
    void setNuevo()
    {
        
        v = 1.0D + 4D * rnd.nextDouble();
        xInicial = x = -3D;
        bNuevo = true;
        bTerminar = false;
        repaint();
    }
    /*void setNuevo()
    {

        v = 1.0D + 4D * rnd.nextDouble();
        xInicial = x = -3D;
        bNuevo = true;
        bTerminar = false;
        repaint();
    }*/

    void setInicial()
    {
        
        int signo = rnd.nextDouble() <= 0.5D ? -1 : 1;
        xInicial = x = -3D + (double)signo * (0.5D * rnd.nextDouble());
        t = 0.0D;
        tReloj = 0.0D;
        bNuevo = false;
        bTerminar = false;
        repaint();
    }

    

    void origenEscalas(Graphics g)
    {
        wAncho = getSize().width;
        wAlto = getSize().height;
        cAlto = g.getFontMetrics().getHeight();
        cAncho = g.getFontMetrics().stringWidth("0");
        orgX = 6 * cAncho;
        orgY = wAlto / 3;
        orgYY = wAlto - cAlto / 2;
        escalaX = (double)(wAncho - orgX - 4 * cAncho) / (double)50;
    }

    void dibujaRegla(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, wAncho, wAlto);
        g.setColor(Color.yellow);
        g.fillRect(0, orgY, wAncho - 2 * cAncho, 4 * cAlto);
        g.setColor(Color.black);
        int i = 0;
        do
        {
            if(i > 50)
                break;
            int x1 = orgX + (int)((double)i * escalaX);
            g.drawLine(x1, orgY + cAlto, x1, orgY);
            g.drawString(String.valueOf(i), x1 - cAncho / 2, orgY + cAlto + cAlto);
            if(i == 50)
                break;
            for(int j = 1; j < 5; j++)
            {
                int x2 = x1 + (int)((double)j * escalaX);
                g.drawLine(x2, orgY + cAlto / 2, x2, orgY);
            }

            i += 5;
        } while(true);
        g.drawString("cm", wAncho - 5 * cAncho, orgY + 3 * cAlto);
        g.setColor(Color.lightGray);
        g.fillOval(wAncho - 3 * cAncho, orgY - 2 * cAncho, 2 * cAncho, 2 * cAncho);
        g.fillRect(wAncho - 4 * cAncho, orgYY, 4 * cAncho, cAncho / 2);
        g.setColor(Color.black);
        g.fillOval(wAncho - 2 * cAncho - 2, orgY - cAncho - 2, 4, 4);
        g.drawImage(parent.marca1.getImage(), orgX - marca.width / 2, orgY + 2 * cAlto + 3, this);
        marca.y = orgY + 2 * cAlto + 3;
        if(bNuevo) {
            marca.x = (orgX + (int) ((double)5 * escalaX)) - marca.width / 2;
        }
        g.drawImage(parent.marca2.getImage(), marca.x, marca.y, this);
    }

    void mover()
    {
        if(x < (double)0)
        {
            x = xInicial - (v * v * t * t) / ((double)2 * xInicial);
            t0 = t;
        } else
        {
            x = v * (t - t0);
        }
        t += dt;
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        origenEscalas(g);
        update(g);
    }

    void dibujaMovil(Graphics g)
    {
        int x1 = orgX + (int)(x * escalaX);
        if(x1 < orgX) {
            tReloj = 0.0D;
        }
        if(x1 >= orgX && x1 <= marca.x + marca.width / 2) {
            tReloj += dt;
        }
        g.setColor(Color.black);
        double tFin = (double)Math.round(tReloj * (double)10) / (double)10;
        Font font = g.getFont();
        g.setFont(new Font("TimesRoman", 1, 16));
        g.drawString(String.valueOf(tFin) + " s", wAncho / 2 - 3 * cAncho, cAlto + cAlto / 2);
        g.setFont(font);
        int xMovil = parent.movil.getImage().getWidth(this);
        int yMovil = parent.movil.getImage().getHeight(this);
        if(x < (double)0)
        {
            g.setColor(Color.black);
            g.drawLine(x1 + xMovil / 2, orgY - 2 * cAncho, wAncho - 2 * cAncho, orgY - 2 * cAncho);
            int y1 = orgYY + (int)(x * escalaX);
            g.drawLine(wAncho - cAncho, y1, wAncho - cAncho, orgY - cAncho);
            g.setColor(Color.cyan);
            int peso = (int)(v * v);
            g.fillRect((wAncho - 2 * cAncho) + 1, y1 - peso, 2 * cAncho - 2, peso);
        } else
        {
            g.setColor(Color.cyan);
            int peso = (int)(v * v);
            g.fillRect((wAncho - 2 * cAncho) + 1, orgYY - peso, 2 * cAncho - 2, peso);
        }
        g.drawImage(parent.movil.getImage(), x1 - xMovil / 2, orgY - yMovil, this);
        if(x1 > wAncho - xMovil - cAncho)
        {
            if(!bTerminar)
            {
                parent.hilo.putMsg(0);
                t = 0.0D;
                parent.btnEmpezar.setEnabled(true);
                parent.btnNuevo.setEnabled(true);
                double posMarca = (double)((marca.x + marca.width / 2) - orgX) / escalaX;
                String sMarca = String.valueOf(Math.round(posMarca));
                String sTiempo = String.valueOf((double)Math.round(tReloj * (double)10) / (double)10);
                parent.textDatos.append(sTiempo + "," + sMarca + "\n");
            }
            bTerminar = true;
        }
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
        dibujaRegla(gBuffer);
        if(!bArrastrar) {
            dibujaMovil(gBuffer);
        }
        g.drawImage(imag, 0, 0, null);
    }

    public void mousePressed(MouseEvent ev)
    {
        if(marca.contains(ev.getX(), ev.getY())) {
            bArrastrar = true;
        }
    }

    public void mouseReleased(MouseEvent ev)
    {
        int x1 = orgX + (int)(escalaX * (double)50);
        if(bArrastrar)
        {
            if(marca.x < orgX) {
                marca.x = orgX;
            }
            if(marca.x > x1) {
                marca.x = x1;
            }
            bArrastrar = false;
            repaint();
        }
        setInicial();
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseDragged(MouseEvent ev)
    {
        int x1 = orgX + (int)(escalaX * (double)50);
        if(ev.getX() < orgX || ev.getX() > x1) {
            return;
        }
        if(bArrastrar)
        {
            marca.x = ev.getX();
            repaint();
        }
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

    UniformeApplet parent;
    int wAncho;
    int wAlto;
    int cAlto;
    int cAncho;
    int orgX;
    int orgY;
    int orgYY;
    double escalaX;
    double xInicial;
    double x;
    double v;
    double t;
    double tReloj;
    double dt;
    double t0;
    Rectangle marca;
    boolean bNuevo;
    Random rnd;
    Image imag;
    Graphics gBuffer;
    Dimension dim;
    boolean bArrastrar;
    boolean bTerminar;


}