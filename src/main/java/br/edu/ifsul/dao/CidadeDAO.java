package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Cidade;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorgebavaresco@ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class CidadeDAO<TIPO>  extends DAOGenerico<Cidade> implements Serializable {
    
    public CidadeDAO(){
        super();
        classePersistente = Cidade.class;
        // definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        listaOrdem.add(new Ordem("estado.nome", "Estado", "like"));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);          
    }

}
