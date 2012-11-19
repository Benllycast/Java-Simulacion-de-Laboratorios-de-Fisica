package rectUniforme;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import regresion.*;


// Referenced classes of package rectUniforme:
//            Hilo, MiCanvas, Accion

public class UniformeApplet extends JFrame
{
 //creador puro elementos de pantalla
    public UniformeApplet()
    {
        Panel1 = new Panel();
        Panel2 = new Panel();
        Panel3 = new Panel();
        Panel4 = new Panel();
        Panel7 = new Panel();
        borderLayout1 = new BorderLayout();
        borderLayout2 = new BorderLayout();
        flowLayout1 = new FlowLayout();
        flowLayout8 = new FlowLayout();
        borderLayout7 = new BorderLayout();
        borderLayout8 = new BorderLayout();
        btnEmpezar = new Button();
        textDatos = new TextArea();
        btnBorra = new Button();
        btnEnvia = new Button();
        label1 = new Label();
        btnNuevo = new Button();
        init();
    }

    public void init()
    {
        try
        {
            jbInit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void jbInit()
        throws Exception
    {
        int ancho = 800;
        int alto = 500;
        setSize(new Dimension(ancho, alto));
        hilo = new Hilo(this);
        canvas = new MiCanvas(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SIMULACIÓN DE MOVIMIENTO RECTILÍNIO UNIFORME!!!");
        Panel2.setBackground(Color.lightGray);
        Panel7.setBackground(Color.gray);
        Panel4.setBackground(Color.gray);
        Panel2.setLayout(borderLayout7);
        Panel3.setLayout(borderLayout2);
        Panel1.setLayout(borderLayout1);
        Panel7.setLayout(flowLayout8);
        Panel4.setLayout(flowLayout1);
        setLayout(borderLayout8);
        flowLayout8.setVgap(1);
        textDatos.setRows(10);
        textDatos.setColumns(10);
        btnEmpezar.setFont(new Font("Dialog", 1, 12));
        btnEmpezar.setLabel("Empieza");
        btnBorra.setLabel("Borrar");
        add(Panel1, "Center");
        Panel1.add(Panel3, "West");
        Panel1.add(canvas, "Center");
        Panel3.add(textDatos, "Center");
        Panel4.add(btnBorra, null);
        Panel4.add(btnEnvia, null);
        add(Panel2, "South");
        Panel2.add(Panel7, "East");
        Panel7.add(btnEmpezar, null);
        Panel7.add(btnNuevo, null);
        //Panel7.add(p,null);
        Panel2.add(Panel4, "West");
        Panel2.add(label1, "Center");
        btnNuevo.setLabel("Nuevo");
        label1.setText("  Mover la flecha roja");
        btnEnvia.setLabel("Enviar");
        textDatos.setText("");
        Accion accion = new Accion(this);
        btnNuevo.addActionListener(accion);
        btnEmpezar.addActionListener(accion);
        btnEnvia.addActionListener(accion);
        btnBorra.addActionListener(accion);
        movil = new ImageIcon("src/rectUniforme/movil.gif");
        marca1 = new ImageIcon("src/rectUniforme/marca1.gif");
        marca2 = new ImageIcon("src/rectUniforme/marca2.gif");
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-550)/2, (screenSize.height-387)/2, 550, 387);
        hilo.start();
        R= new RegresionApplet();
        R.setVisible(true);
        

    }
    
    void btnNuevo_actionPerformed(ActionEvent e)
    {
        canvas.setNuevo();
        textDatos.setText("");
    }

    void btnBorra_actionPerformed(ActionEvent e)
    {
        textDatos.setText("");
    }

    void btnEnvia_actionPerformed(ActionEvent e)
    {
        R.ponDatos(textDatos.getText());
        //R= new RegresionApplet();
        R.setVisible(true);
    }



    void btnEmpezar_actionPerformed(ActionEvent e)
    {

        btnEmpezar.setEnabled(false);
        btnNuevo.setEnabled(false);
        hilo.putMsg(2);
        canvas.setInicial();

    }

    MiCanvas canvas;
    ImageIcon movil;
    ImageIcon marca1;
    ImageIcon marca2;
    Hilo hilo;
    Panel Panel1;
    Panel Panel2;
    Panel Panel3;
    Panel Panel4;
    Panel Panel7;
    BorderLayout borderLayout1;
    BorderLayout borderLayout2;
    FlowLayout flowLayout1;
    FlowLayout flowLayout8;
    BorderLayout borderLayout7;
    BorderLayout borderLayout8;
    Button btnEmpezar;
    TextArea textDatos;
    Button btnBorra;
    Button btnEnvia;
    Label label1;
    Button btnNuevo;
    RegresionApplet R;
}