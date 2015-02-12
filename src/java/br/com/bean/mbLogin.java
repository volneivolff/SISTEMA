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

        } else {

            growl.addMessage("Erro!", "Não foram encontradas suas credenciais, tente novamente!");

            setMsgErro(true);

            retorno = "index.xhtml?faces-redirect=true";

            System.out.println("Ação do Usuário: FALHA DO LOGON");

        }

        return retorno;
    }

    public void errToFalse() {
        setMsgErro(false);
    }

    //GETS AND SETS

    public boolean isMsgErro() {
        return msgErro;
    }

    public void setMsgErro(boolean msgErro) {
        this.msgErro = msgErro;
    }

    public Usuario getUsuarioDeUsoRecorrente() {
        return usRec;
    }

    public void setUsuarioDeUsoRecorrente(Usuario usuarioDeUsoRecorrente) {
        this.usRec = usuarioDeUsoRecorrente;
    }

    public Usuario getUsuarioPermantenteNaSessao() {
        return usPerm;
    }

    public void setUsuarioPermantenteNaSessao(Usuario usuarioPermantenteNaSessao) {
        this.usPerm = usuarioPermantenteNaSessao;
    }

}
