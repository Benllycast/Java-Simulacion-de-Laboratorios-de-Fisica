/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package caida_libre;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.EventObject;
import javax.swing.JTextField;

class ValidaDouble extends FocusAdapter
{

    ValidaDouble()
    {
    }

    @Override
    public void focusLost(FocusEvent focusevent)
    {
        JTextField textfield = (JTextField)focusevent.getSource();
        try
        {
            Double.valueOf(textfield.getText()).doubleValue();
        }
        catch(NumberFormatException numberformatexception)
        {
            textfield.requestFocus();
            textfield.selectAll();
        }
    }
}