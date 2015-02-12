package br.com.dao;

import br.com.code.Growl;
import br.com.entity.Usuario;
import br.com.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class UsuarioDao implements Serializable {

    private static final long serialVersionUID = 1L;

    Usuario usr = new Usuario();
    Growl growl = new Growl();
    private List<Usuario> lista;

    public UsuarioDao(Session sessao, Transaction transacao) {
        this.sessao = sessao;
        this.transacao = transacao;
    }

    public UsuarioDao() {

    }

    private Session sessao;
    private Transaction transacao;

    public void addUsuario(Usuario usrBean) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            usr = usrBean;

            sessao.save(usr);

            transacao.commit();

            growl.addMessage("Sucesso!", "Usuario adicionado com sucesso!");
        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao adicionar Usuário:" + e);
        } finally {

            sessao.close();
        }
    }

    public void removeUsuario(Usuario usrBean) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            usr = usrBean;

            sessao.delete(usr);

            transacao.commit();

            growl.addMessage("Sucesso!", "Usuario removido com sucesso!");

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao remover Usuário:" + e);
        } finally {

            sessao.close();
        }
    }

    public void editaUsuario(Usuario usrBean) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            usr = usrBean;

            sessao.update(usr);

            transacao.commit();

            growl.addMessage("Sucesso!", "Usuario editado com sucesso!");

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao edita Usuário:" + e);
        } finally {

            sessao.close();
        }
    }

    public List<Usuario> listUsr() {
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            Criteria cri = sessao.createCriteria(Usuario.class);

            this.lista = cri.addOrder(Order.asc("id")).list();

        } catch (HibernateException e) {

            System.out.println(e);

        } finally {

            sessao.close();
        }

        return lista;
    }

    public List<Usuario> confereUser(String usuarioBean, String senhaBean) {
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            Criteria cri = sessao.createCriteria(Usuario.class);

            cri.add(Restrictions.eq("usuario", usuarioBean));
            cri.add(Restrictions.eq("senha", senhaBean));

            this.lista = cri.list();

            //growl.addMessage("Sucesso!", "Login realizado com sucesso!");
        } catch (Exception e) {

            System.out.println(e);

            growl.addMessage("Erro!", "Erro ao realizar Login:" + e);

        } finally {

            sessao.close();
        }
        return lista;
    }

}
