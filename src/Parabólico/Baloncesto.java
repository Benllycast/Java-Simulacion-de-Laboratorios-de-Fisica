package Parabólico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import javax.swing.JFrame;

public class Baloncesto extends JFrame
{

    public Baloncesto()
    {
        Panel2 = new Panel();
        Panel3 = new Panel();
        Panel6 = new Panel();
        Panel7 = new Panel();
        Panel8 = new Panel();
        Panel10 = new Panel();
        flowLayout3 = new FlowLayout();
        borderLayout5 = new BorderLayout();
        flowLayout2 = new FlowLayout();
        flowLayout5 = new FlowLayout();
        Label1 = new Label();
        Label3 = new Label();
        textAltura = new TextField();
        textDistancia = new TextField();
        btnPaso = new Button();
        btnPausa = new Button();
        btnEmpieza = new Button();
        borderLayout1 = new BorderLayout();
        borderLayout3 = new BorderLayout();
        sbAltura = new Scrollbar();
        borderLayout2 = new BorderLayout();
        sbDistancia = new Scrollbar();
        borderLayout4 = new BorderLayout();
        bPausa = true;
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


        setSize(new Dimension(800, 600));
        canvas = new MiCanvas(this);
        hilo = new Hilo(this);
        Panel2.setLayout(borderLayout3);
        Panel6.setBackground(Color.lightGray);
        Panel7.setBackground(Color.GRAY);
        Panel3.setLayout(flowLayout2);
        Panel7.setLayout(flowLayout5);
        Panel8.setLayout(flowLayout3);
        Panel6.setLayout(borderLayout4);
        flowLayout3.setAlignment(2);
        flowLayout3.setVgap(1);
        Panel10.setBackground(Color.lightGray);
        Panel10.setLayout(borderLayout2);
        flowLayout2.setAlignment(2);
        flowLayout2.setVgap(1);
        flowLayout5.setVgap(10);
        sbAltura.setValue(60);
        sbAltura.setMaximum(98);
        sbAltura.setOrientation(0);
        sbDistancia.setValue(60);
        sbDistancia.setOrientation(0);
        flowLayout5.setHgap(2);
        setLayout(borderLayout5);
        add(canvas, "Center");
        add(Panel2, "South");
        Label1.setText("Altura(m)");
        ValidaDouble valDouble = new ValidaDouble();
        textAltura.setColumns(4);
        textAltura.setText("1.8");
        textAltura.addFocusListener(valDouble);
        Label3.setText("Distancia(m)");
        textDistancia.setColumns(4);
        textDistancia.setText("3.0");
        textDistancia.addFocusListener(valDouble);
        Barra barra = new Barra(this);
        sbAltura.addAdjustmentListener(barra);
        sbDistancia.addAdjustmentListener(barra);
        Accion accion = new Accion(this);
        btnPaso.addActionListener(accion);
        btnEmpieza.addActionListener(accion);
        btnPausa.addActionListener(accion);
        btnPaso.setLabel("Paso");
        btnEmpieza.setLabel("Lanza");
        btnPausa.setLabel("  Detener  ");
        Panel10.add(Panel3, "Center");
        Panel3.add(Label1, null);
        Panel3.add(textAltura, null);
        Panel10.add(sbAltura, "South");
        Panel2.add(Panel10, "Center");
        Panel2.add(Panel6, "West");
        Panel2.add(Panel7, "East");
        Panel7.add(btnEmpieza, null);
        Panel7.add(btnPausa, null);
        Panel7.add(btnPaso, null);
        Panel6.add(Panel8, "Center");
        Panel8.add(Label3, null);
        Panel8.add(textDistancia, null);
        Panel6.add(sbDistancia, "South");
        btnPausa.setEnabled(false);
        btnPaso.setEnabled(false);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-550)/2, (screenSize.height-387)/2, 550, 387);
        hilo.start();
    }

    void btnEmpieza_actionPerformed(ActionEvent e)
    {
        btnPausa.setEnabled(true);
        btnPaso.setEnabled(true);
        btnPausa.setLabel("  Pausa  ");
        bPausa = true;
        inicio();
        hilo.putMsg(2);
    }

    void inicio()
    {
        double distancia = Double.valueOf(textDistancia.getText()).doubleValue();
        double altura = Double.valueOf(textAltura.getText()).doubleValue();
        canvas.setNuevo(distancia, altura);
    }

    void btnPausa_actionPerformed(ActionEvent e)
    {
        if(bPausa)
        {
            hilo.putMsg(0);
            btnPausa.setLabel("Lanza");
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



    void sbAltura_adjustmentValueChanged(AdjustmentEvent e)
    {
        double valor = 0.5 + (double)(sbAltura.getValue() * 2) / (double)90;
        textAltura.setText(String.valueOf((double)Math.round(valor * (double)100) / (double)100));
        inicio();
    }

    void sbDistancia_adjustmentValueChanged(AdjustmentEvent e)
    {
        double valor = 1.0 + (double)(sbDistancia.getValue() * 3) / (double)90;
        textDistancia.setText(String.valueOf((double)Math.round(valor * (double)100) / (double)100));
        inicio();
    }

    Panel Panel2;
    Panel Panel3;
    Panel Panel6;
    Panel Panel7;
    Panel Panel8;
    Panel Panel10;
    FlowLayout flowLayout3;
    BorderLayout borderLayout5;
    FlowLayout flowLayout2;
    FlowLayout flowLayout5;
    Label Label1;
    Label Label3;
    TextField textAltura;
    TextField textDistancia;
    Button btnPaso;
    Button btnPausa;
    Button btnEmpieza;
    MiCanvas canvas;
    BorderLayout borderLayout1;
    BorderLayout borderLayout3;
    Scrollbar sbAltura;
    BorderLayout borderLayout2;
    Scrollbar sbDistancia;
    BorderLayout borderLayout4;
    Hilo hilo;
    boolean bPausa;
}
