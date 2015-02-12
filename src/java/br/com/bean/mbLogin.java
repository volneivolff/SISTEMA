package br.com.bean;

import br.com.code.Growl;
import br.com.dao.UsuarioDao;
import br.com.entity.Usuario;
import java.util.List;

public class mbLogin {

    
    private Usuario usr;
    private Growl growl;
    private UsuarioDao usrDao;
    private List<Usuario> listaDeUsuarios;
    boolean msgErro;

    //CABEÇALHO DE PERMISSOES
    String usuarioCorrente;
    boolean privilegiosUsuario;
    
    public String logaUsuario() {

        String retorno;

        String usuario = usr.getUsuario();
        String senha = usr.getSenha();

        listaDeUsuarios = usrDao.confereUser(usuario, senha);

        int listaus = listaDeUsuarios.size();

        if (listaus == 1) {

            usuarioCorrente = usr.getUsuario();
            privilegiosUsuario = listaDeUsuarios.get(0).isAdministrador();
            usr.setUsuario("");
            growl.addMessage("Sucesso!", "Bem Vindo " + usuarioCorrente);
            setMsgErro(false);
            retorno = "welcome.xhtml?faces-redirect=true";
            System.out.println("Ação do Usuário: LOGON");

        } else {

            growl.addMessage("Erro!", "Não foram encontradas suas credenciais, tente novamente!");
            setMsgErro(true);
            retorno = "index.xhtml?faces-redirect=true";
        }

        return retorno;
    }

//   GETS AND SETS
    public boolean isMsgErro() {
        return msgErro;
    }

    public void setMsgErro(boolean msgErro) {
        this.msgErro = msgErro;
    }

}
