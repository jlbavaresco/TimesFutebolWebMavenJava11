package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Time;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorgebavaresco@ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class TimeDAO<TIPO>  extends DAOGenerico<Time> implements Serializable {
    
    public TimeDAO(){
        super();
        classePersistente = Time.class;
        // definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        listaOrdem.add(new Ordem("cidade.nome", "Cidade", "like"));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);                        
    }
    
    @Override
    public Time getObjectByID(Object id) throws Exception {
        Time obj = em.find(Time.class, id);
        // uso para evitar o erro de lazy inicialization exception
        obj.getJogadores().size();
        return obj;
    }    
    
    public List<Time> getListaObjetosCompleta(){
        String jpql = "select distinct t from Time t left join fetch t.jogadores order by t.id";
        return em.createQuery(jpql).getResultList();
    }

}
