package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorgebavaresco@ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Named(value = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {
    
    private Usuario usuarioAutenticado;
    @EJB
    private UsuarioDAO<Usuario> dao;
    private String usuario;
    private String senha;
    
    public ControleLogin(){
        
    }
    
    public String irPaginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin(){
        try {
            HttpServletRequest request = (HttpServletRequest)
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(this.usuario, this.senha);
            if (request.getUserPrincipal() != null){
                usuarioAutenticado = 
                        dao.getObjectByID(request.getUserPrincipal().getName());
                Util.mensagemInformacao("Login realizado com sucesso!");               
                usuario = "";
                senha = "";                        
            }            
            return "/index?faces-redirect=true";
        } catch (Exception e){
            Util.mensagemErro("Usuario ou senha inválidos! " + Util.getMensagemErro(e));
            return "/login";
        }            
    }
    
    public String logout(){
        try {
            usuarioAutenticado = null;
            HttpServletRequest request = (HttpServletRequest)
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();          
            return "/index?faces-redirect=true";
        } catch (Exception e){
            Util.mensagemErro("Erro ao fazer logout! " + Util.getMensagemErro(e));
            return "/index?faces-redirect=true";
        }          
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    

}
