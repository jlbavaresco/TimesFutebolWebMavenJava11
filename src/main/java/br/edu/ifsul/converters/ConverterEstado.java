package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Estado;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorgebavaresco@ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Named(value = "converterEstado")
@RequestScoped
public class ConverterEstado implements Serializable, Converter {

    @PersistenceContext(unitName = "TimesFutebolWebPU")
    protected EntityManager em;    
    
    // converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Estado.class, Integer.parseInt(string));
    }

    // converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Estado obj = (Estado) t;
        return obj.getId().toString();
    }

}
