/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Federico
 */
class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    public DateLabelFormatter() {
    }

    @Override
    public Object stringToValue(String text) throws ParseException {
        return text;
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value!=null) {
        Calendar data=(GregorianCalendar)value;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(data.getTime());
        }
        else {
            return null;
        }
            
    }
    
}
