package Rozamiento;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

// Referenced classes of package rozamiento9:
//            MiCanvas, Hilo, Accion

public class Rozamiento extends JFrame
{
    class _cls1
        implements AdjustmentListener
    {

        public void adjustmentValueChanged(AdjustmentEvent e)
        {
            sbMasa_adjustmentValueChanged(e);
        }

        _cls1()
        {
        }
    }


    public Rozamiento()
    {
        Panel1 = new Panel();
        taDatos = new TextArea();
        Panel2 = new Panel();
        btnGrafica = new Button();
        Panel5 = new Panel();
        flowLayout3 = new FlowLayout();
        borderLayout2 = new BorderLayout();
        btnNuevo = new Button();
        label1 = new Label();
        textMasa = new TextField();
        bevelPanel5 = new Panel();
        borderLayout5 = new BorderLayout();
        bevelPanel7 = new Panel();
        bevelPanel8 = new Panel();
        grupo = new CheckboxGroup();
        flowLayout6 = new FlowLayout();
        borderLayout6 = new BorderLayout();
        sbMasa = new Scrollbar();
        borderLayout4 = new BorderLayout();
        flowLayout1 = new FlowLayout();
        btnPausa = new Button();
        btnPaso = new Button();
        bPausa = true;
        btnRespuesta = new Button();
        try
        {
            jbInit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void jbInit()
        throws Exception
    {
        int ancho = 600;
        int alto = 475;
        
        canvas = new MiCanvas(this);
        hilo = new Hilo(this);
        setTitle("Fuerza con fricción o rozamiento ");
        Panel1.setBackground(Color.lightGray);
        Panel1.setLayout(borderLayout2);
        taDatos.setRows(10);
        taDatos.setColumns(5);
        Panel2.setBackground(Color.gray);
        Panel2.setLayout(flowLayout3);
        btnGrafica.setLabel("Gr\341fica");
        Accion accion = new Accion(this);
        btnGrafica.addActionListener(accion);
        btnNuevo.addActionListener(accion);
        btnPaso.addActionListener(accion);
        btnPausa.addActionListener(accion);
        btnRespuesta.addActionListener(accion);
        Panel5.setBackground(Color.lightGray);
        Panel5.setLayout(borderLayout6);
        flowLayout3.setHgap(15);
        flowLayout3.setVgap(1);
        btnNuevo.setLabel("Nuevo");
        label1.setText("Masa (kg)");
        textMasa.setEditable(false);
        textMasa.setText("1.0");
        textMasa.setColumns(4);
        bevelPanel5.setBackground(Color.gray);
        bevelPanel5.setLayout(flowLayout1);
        setLayout(borderLayout5);
        flowLayout6.setAlignment(0);
        bevelPanel7.setLayout(borderLayout4);
        flowLayout6.setVgap(1);
        sbMasa.setValue(22);
        btnPausa.setLabel("  Pausa  ");
        btnPaso.setLabel("Paso");
        btnRespuesta.setLabel("Respuesta");
        sbMasa.addAdjustmentListener(new _cls1());
        sbMasa.setOrientation(0);
        bevelPanel8.setLayout(flowLayout6);        
        Panel5.add(bevelPanel5, "East");
        bevelPanel5.add(btnNuevo, null);
        bevelPanel5.add(btnPausa, null);
        bevelPanel5.add(btnPaso, null);
        bevelPanel5.add(btnRespuesta, null);
        Panel5.add(bevelPanel7, "Center");
        bevelPanel7.add(bevelPanel8, "Center");
        bevelPanel8.add(label1, null);
        bevelPanel8.add(textMasa, null);
        bevelPanel7.add(sbMasa, "South");
        Panel1.add(taDatos, "Center");
        Panel1.add(Panel2, "South");
        Panel2.add(btnGrafica, null);
        btnGrafica.setEnabled(false);
        btnPausa.setEnabled(false);
        btnPaso.setEnabled(false);
        btnRespuesta.setEnabled(false);
        add(Panel1, "West");
        add(canvas, "Center");
        add(Panel5, "South");
        setSize(ancho, alto);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-550)/2, (screenSize.height-387)/2, 550, 387);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        hilo.start();
    }

    void setDatos(double fuerza, double z)
    {
        String abscisa = String.valueOf((double)Math.round(fuerza * (double)100) / (double)100);
        taDatos.append(abscisa + "\n");
    }

    void btnGrafica_actionPerformed(ActionEvent e)
    {
        canvas.setGrafica();
    }

    void btnNuevo_actionPerformed(ActionEvent e)
    {
        hilo.putMsg(0);
        btnRespuesta.setEnabled(true);
        btnPausa.setEnabled(false);
        btnPaso.setEnabled(false);
        btnPausa.setLabel("  Pausa  ");
        bPausa = true;
        btnGrafica.setEnabled(true);
        double masa = Double.valueOf(textMasa.getText()).doubleValue();
        taDatos.setText("");
        canvas.setNuevo(masa);
    }

    void btnPausa_actionPerformed(ActionEvent e)
    {
        if(bPausa)
        {
            hilo.putMsg(0);
            btnPausa.setLabel("Continua");
            bPausa = false;
        } else
        {
            btnPausa.setLabel("  Pausa  ");
            hilo.putMsg(2);
            bPausa = true;
        }
    }

    void btnPaso_actionPerformed(ActionEvent e)
    {
        hilo.putMsg(1);
        btnPausa.setLabel("Continua");
        bPausa = false;
    }

    public String getAppletInfo()
    {
        return "(C) Angel Franco Garc\355a. Universidad del Pa\355s Vasco (Espa\361a)";
    }

    public void sbMasa_adjustmentValueChanged(AdjustmentEvent e)
    {
        double valor = 0.5D + ((double)sbMasa.getValue() * 2D) / (double)90;
        textMasa.setText(String.valueOf((double)Math.round(valor * (double)100) / (double)100));
    }

    void btnRespuesta_actionPerformed(ActionEvent e)
    {
        hilo.putMsg(0);
        canvas.respuesta();
    }

    Panel Panel1;
    TextArea taDatos;
    Panel Panel2;
    Button btnGrafica;
    Panel Panel5;
    FlowLayout flowLayout3;
    BorderLayout borderLayout2;
    Button btnNuevo;
    Label label1;
    TextField textMasa;
    Panel bevelPanel5;
    BorderLayout borderLayout5;
    Panel bevelPanel7;
    Panel bevelPanel8;
    CheckboxGroup grupo;
    FlowLayout flowLayout6;
    BorderLayout borderLayout6;
    Scrollbar sbMasa;
    BorderLayout borderLayout4;
    MiCanvas canvas;
    FlowLayout flowLayout1;
    Hilo hilo;
    Button btnPausa;
    Button btnPaso;
    boolean bPausa;
    Button btnRespuesta;

}

