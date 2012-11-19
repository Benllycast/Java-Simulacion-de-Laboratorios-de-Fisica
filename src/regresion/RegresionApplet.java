
package regresion;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
// Referenced classes of package regresion:
//            Estado, Regresion, Accion

public class RegresionApplet extends JFrame
{

    public RegresionApplet()
    {
        bevelPanel1 = new Panel();
        taDatos = new TextArea(10, 3);
        bevelPanel2 = new Panel();
        bevelPanel3 = new Panel();
        btnCalcular = new Button();
        btnBorrar = new Button();
        flowLayout1 = new FlowLayout();
        borderLayout2 = new BorderLayout();
        borderLayout1 = new BorderLayout();
        borderLayout3 = new BorderLayout();
        borderLayout4 = new BorderLayout();
        init();
    }

    //@Override
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
        estado = new Estado();
        regresion = new Regresion(this);
        int ancho = 700;
        int alto = 600;
        setSize(new Dimension(ancho, alto));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Representación grafica de los resultados!!!");
        bevelPanel1.setBackground(Color.lightGray);
        bevelPanel3.setBackground(Color.gray);
        bevelPanel1.setLayout(borderLayout2);
        bevelPanel2.setLayout(borderLayout1);
        setLayout(borderLayout4);
        add(bevelPanel1, "West");
        add(bevelPanel2, "Center");
        bevelPanel3.setLayout(flowLayout1);
        btnCalcular.setLabel("Calcular");
        Accion accion = new Accion(this);
        btnCalcular.addActionListener(accion);
        btnBorrar.addActionListener(accion);
        btnBorrar.setLabel("Borrar");
        bevelPanel1.add(taDatos, "Center");
        bevelPanel1.add(bevelPanel3, "South");
        bevelPanel3.add(btnCalcular, null);
        bevelPanel3.add(btnBorrar, null);
        bevelPanel2.add(estado, "North");
        bevelPanel2.add(regresion, "Center");
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-550)/2, (screenSize.height-387)/2, 550, 387);
        taDatos.requestFocus();
    }

    void btnCalcular_actionPerformed(ActionEvent e)
    {
        String entrada = taDatos.getText();
        regresion.setDatos(entrada);
        if(regresion.ok)
        {
            regresion.calcular();
            regresion.repaint();
            estado.repaint();
        } else
        {
            taDatos.append("\nEl n\372mero de pares \nde datos ha de ser\n mayor que 2");
            return;
        }
    }

    void btnBorrar_actionPerformed(ActionEvent e)
    {
        taDatos.setText("");
    }

    public void ponDatos(String datos)
    {
        taDatos.setText(datos);
    }
    

    Estado estado;
    Regresion regresion;
    Panel bevelPanel1;
    TextArea taDatos;
    Panel bevelPanel2;
    Panel bevelPanel3;
    Button btnCalcular;
    Button btnBorrar;
    FlowLayout flowLayout1;
    BorderLayout borderLayout2;
    BorderLayout borderLayout1;
    BorderLayout borderLayout3;
    BorderLayout borderLayout4;
}

