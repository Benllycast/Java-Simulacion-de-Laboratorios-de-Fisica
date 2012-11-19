/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package caida_libre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Benlly
 */
public class Ventana extends JFrame {
    private static final long serialVersionUID = 1L;

    private JLabel lblGravedad;
    private JLabel lblPosicion;
    private JLabel lblVelocidad;
    private JTextField txtPosicion;
    private JTextField txtVelocidad;
    private JTextField txtGravedad;
    private JPanel Panel1;
    private JPanel PanelTexto;
    private JPanel PanelBotones;
    JButton btnEmpieza;
    JButton btnPausa;
    JButton btnPaso;    
    MiCanvas canvas;
    Hilo hilo;
    boolean bPausa;
    
    public Ventana() {
       
       this.lblGravedad = new JLabel();
       this.lblPosicion = new JLabel();
       this.lblVelocidad = new JLabel();

       this.txtGravedad = new JTextField();
       this.txtPosicion = new JTextField();
       this.txtVelocidad = new JTextField();

       this.btnEmpieza = new JButton();
       this.btnPaso = new JButton();
       this.btnPausa = new JButton();
       
       this.Panel1 = new JPanel();
       this.PanelTexto = new JPanel();       
       this.PanelBotones = new JPanel();   

       this.canvas = new MiCanvas(this);
       this.hilo = new Hilo(this);
       init();
    }

    

    private void init() {
        Accion accion = new Accion(this);
        ValidaDouble validadouble = new ValidaDouble();
        this.lblGravedad.setText("Gravedad");
        this.lblPosicion.setText("Posici\363n (m)");
        this.lblVelocidad.setText("Velocidad (m/s)");
        
        this.txtPosicion.setColumns(5);
        this.txtPosicion.setText("200");        
        this.txtPosicion.addFocusListener(validadouble);

        this.txtVelocidad.setColumns(5);
        this.txtVelocidad.setText("40");
        this.txtVelocidad.addFocusListener(validadouble);

        this.txtGravedad.setColumns(5);
        this.txtGravedad.setText("9.8");
        this.txtGravedad.addFocusListener(validadouble);
        
        this.btnEmpieza.addActionListener(accion);
        this.btnEmpieza.setText("Empieza");
        
        this.btnPaso.addActionListener(accion);
        this.btnPaso.setText("Paso");
        this.btnPaso.setEnabled(false);

        this.btnPausa.addActionListener(accion);
        this.btnPausa.setText("  Pausa  ");
        this.btnPausa.setEnabled(false);

        this.PanelTexto.setLayout(new FlowLayout());
        this.PanelTexto.add(this.lblPosicion);
        this.PanelTexto.add(this.txtPosicion);
        this.PanelTexto.add(this.lblGravedad);
        this.PanelTexto.add(this.txtGravedad);
        this.PanelTexto.add(this.lblVelocidad);
        this.PanelTexto.add(this.txtVelocidad);

        this.PanelBotones.setBackground(Color.gray);
        this.PanelBotones.setLayout(new FlowLayout());
        this.PanelBotones.add(btnEmpieza);
        this.PanelBotones.add(btnPausa);
        this.PanelBotones.add(btnPaso);

        this.Panel1.setBackground(new Color(192, 192, 192));
        this.Panel1.setLayout(new FlowLayout());
        this.Panel1.add(PanelTexto);
        this.Panel1.add(PanelBotones);
        
        setLayout(new BorderLayout(1, 1));
        setTitle("CAIDA LIBRE");
        add(Panel1, BorderLayout.SOUTH);
        add(this.canvas, BorderLayout.CENTER);
        //java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        //setBounds((screenSize.width-550)/2, (screenSize.height-387)/2, 550, 387);
        setBounds(300, 100 , 700, 450);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);        
        this.hilo.start();
    }

    void btnEmpieza_actionPerformed(ActionEvent actionevent) {
        //javax.swing.JOptionPane.showMessageDialog(this, "Boton Empieza","EMPIEZA",1);
        btnPausa.setEnabled(true);
        btnPaso.setEnabled(true);
        btnPausa.setText("  Pausa  ");
        bPausa = true;
        double d = Double.valueOf(this.txtPosicion.getText().trim()).doubleValue();
        double d1 = Double.valueOf(this.txtVelocidad.getText().trim()).doubleValue();
        double d2 = Double.valueOf(this.txtGravedad.getText().trim()).doubleValue();
        canvas.setNuevo(d, d1, d2);
        hilo.putMsg(2);
    }

    void btnPaso_actionPerformed(ActionEvent actionevent) {
        //javax.swing.JOptionPane.showMessageDialog(this, "Boton Paso","PASO",1);
        if(bPausa)
        {
            hilo.putMsg(0);
            btnPausa.setText("Continua");
            bPausa = false;
        } else
        {
            btnPausa.setText("  Pausa  ");
            hilo.putMsg(2);
            bPausa = true;
        }
    }

    void btnPausa_actionPerformed(ActionEvent actionevent) {
        //javax.swing.JOptionPane.showMessageDialog(this, "Boton Pausa","PAUSA",1);
        hilo.putMsg(1);
        btnPausa.setText("Continua");
        bPausa = false;
    }
}
