package br.edu.ifsul.converters;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorgebavaresco@ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@FacesConverter(value = "converterCalendar")
public class ConverterCalendar implements Serializable, Converter{
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            Calendar retorno = Calendar.getInstance();
            retorno.setTime(sdf.parse(string));
            return retorno;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Calendar obj = (Calendar) t;
        return sdf.format(obj.getTime());
    }

}
