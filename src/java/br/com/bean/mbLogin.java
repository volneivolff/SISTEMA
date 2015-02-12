package br.com.bean;

import br.com.code.Growl;
import br.com.dao.UsuarioDao;
import br.com.entity.Usuario;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class mbLogin {

    private Usuario usRec;
    private Growl growl;
    private UsuarioDao usrDao;
    private List<Usuario> listaDeUsuarios;
    boolean msgErro;

    //CABEÇALHO DE PERMISSOES
    private Usuario usPerm;
    private boolean usLogado;
    
    public String logaUsuario() {

        String retorno;

        String usuario = usRec.getUsuario();
        String senha = usRec.getSenha();

        listaDeUsuarios = usrDao.confereUser(usuario, senha);

        int listaus = listaDeUsuarios.size();

        if (listaus == 1) {

            usPerm = listaDeUsuarios.get(0);

            usRec.setUsuario("");

            growl.addMessage("Sucesso!", "Bem Vindo " + usPerm.getNome());

            setMsgErro(false);

            retorno = "welcome.xhtml?faces-redirect=true";

            System.out.println("Ação do Usuário: LOGOU NO SISTEMA");
            
            setUsLogado(true); 
            
        } else {

            growl.addMessage("Erro!", "Não foram encontradas suas credenciais, tente novamente!");

            setMsgErro(true);

            retorno = "index.xhtml?faces-redirect=true";

            System.out.println("Ação do Usuário: FALHA DO LOGON");
            
            setUsLogado(false);
            
        }

        return retorno;
    }

    public void errToFalse() {
        setMsgErro(false);
    }

    //GETS AND SETS

    public boolean isUsLogado() {
        return usLogado;
    }

    public void setUsLogado(boolean usLogado) {
        this.usLogado = usLogado;
    }
    
    public boolean isMsgErro() {
        return msgErro;
    }

    public void setMsgErro(boolean msgErro) {
        this.msgErro = msgErro;
    }

    public Usuario getUsRec() {
        return usRec;
    }

    public void setUsRec(Usuario usRec) {
        this.usRec = usRec;
    }

    public Usuario getUsPerm() {
        return usPerm;
    }

    public void setUsPerm(Usuario usPerm) {
        this.usPerm = usPerm;
    }


}
