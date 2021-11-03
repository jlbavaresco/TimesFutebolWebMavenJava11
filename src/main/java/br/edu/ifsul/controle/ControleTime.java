package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.PosicaoDAO;
import br.edu.ifsul.dao.TimeDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Jogador;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Posicao;
import br.edu.ifsul.modelo.Time;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorgebavaresco@ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Named(value = "controleTime")
@ViewScoped
public class ControleTime implements Serializable {
    
    @EJB
    private TimeDAO<Time> dao;
    private Time objeto;
    @EJB
    private UsuarioDAO<Usuario> daoUsuario;
    @EJB
    private PessoaDAO<Pessoa> daoPessoa;
    @EJB
    private PosicaoDAO<Posicao> daoPosicao;
    @EJB
    private CidadeDAO<Cidade> daoCidade;
    private Jogador jogador;
    private Boolean novoJogador;
    private int abaAtiva;
    
    
    public ControleTime(){
        
    }
    
    public void imprimeTimes(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatoriosTimes", parametros, dao.getListaObjetosCompleta());
    }
    
    public void imprimeTime(Object id){
        try {
            objeto = dao.getObjectByID(id);
            List<Time> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatoriosTimes", parametros,lista);            
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao imprimir: " + Util.getMensagemErro(e));
        }
    }    
    
    public void novoJogador(){
        jogador = new Jogador();
        novoJogador = true;
    }
    
    public void alterarJogador(int index){
        jogador = objeto.getJogadores().get(index);
        novoJogador = false;
    }
    
    public void salvarJogador(){
        if (novoJogador){
            objeto.adicionarJogador(jogador);
        }
        Util.mensagemInformacao("Jogador adicionado ou alterado com sucesso!");
    }
    
    public void removerJogador(int index){
        objeto.removerJogador(index);
        Util.mensagemInformacao("Jogador removido com sucesso!");
    }
    
    
    public String listar(){
        return "/privado/time/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Time();
        abaAtiva = 0;
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectByID(id);
            abaAtiva = 0;
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id){
        try {
            objeto = dao.getObjectByID(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao salvar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public TimeDAO<Time> getDao() {
        return dao;
    }

    public void setDao(TimeDAO<Time> dao) {
        this.dao = dao;
    }

    public Time getObjeto() {
        return objeto;
    }

    public void setObjeto(Time objeto) {
        this.objeto = objeto;
    }

    public UsuarioDAO<Usuario> getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(UsuarioDAO<Usuario> daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public PosicaoDAO<Posicao> getDaoPosicao() {
        return daoPosicao;
    }

    public void setDaoPosicao(PosicaoDAO<Posicao> daoPosicao) {
        this.daoPosicao = daoPosicao;
    }

    public CidadeDAO<Cidade> getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Boolean getNovoJogador() {
        return novoJogador;
    }

    public void setNovoJogador(Boolean novoJogador) {
        this.novoJogador = novoJogador;
    }

    public int getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }

}
