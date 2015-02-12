package br.com.dao;

import br.com.code.Growl;
import br.com.entity.MovEstoque;
import br.com.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

public class MovEstoqueDao implements Serializable {

    private static final long serialVersionUID = 1L;

    MovEstoque movEstoque = new MovEstoque();
    Growl growl = new Growl();
    private List<MovEstoque> lista;

    public MovEstoqueDao(Session sessao, Transaction transacao) {
        this.sessao = sessao;
        this.transacao = transacao;
    }

    public MovEstoqueDao() {

    }

    private Session sessao;
    private Transaction transacao;

    public void adicionaMov(MovEstoque movEstoqueBean) {
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            movEstoque.setDataDeCadastro(movEstoqueBean.getDataDeCadastro());
            movEstoque.setMovItemId(movEstoqueBean.getMovItemId());
            movEstoque.setNome(movEstoqueBean.getNome());
            movEstoque.setObs(movEstoqueBean.getObs());
            movEstoque.setQuantFinal(movEstoqueBean.getQuantFinal());
            movEstoque.setQuantInicial(movEstoqueBean.getQuantInicial());
            movEstoque.setQuantRetirada(movEstoqueBean.getQuantRetirada());
            movEstoque.setVendaEfetiva(movEstoqueBean.getVendaEfetiva());
            movEstoque.setMarkupEfetivo(movEstoqueBean.getMarkupEfetivo());
            movEstoque.setGanhoEfetivo(movEstoqueBean.getGanhoEfetivo());

            sessao.save(movEstoque);

            transacao.commit();
            //growl.addMessage("Sucesso!", "Alteração no estoque realizada com sucesso!");

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao Alterar estoque do item:" + e);

        } finally {

            sessao.close();
        }
    }

    public void alteraMov(MovEstoque movEstoqueBean) {

        try {

            movEstoque = movEstoqueBean;

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            sessao.update(movEstoque);
            transacao.commit();

            growl.addMessage("Sucesso!", "Alteração realzada com sucesso!");

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao alterar estoque:" + e);

        } finally {

            sessao.close();
        }
    }

    public void deletaMov(MovEstoque movEstoqueBean) {

        try {

            movEstoque = movEstoqueBean;

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            sessao.delete(movEstoque);
            transacao.commit();

            growl.addMessage("Sucesso!", "Exclusão realizada com sucesso!");

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro excluir Item no estoque: " + e);

        } finally {

            sessao.close();
        }
    }

    public List<MovEstoque> pegaListaMov() {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            Criteria cri = sessao.createCriteria(MovEstoque.class);

            this.lista = cri.addOrder(Order.asc("id")).list();

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao atualizar: " + e);

            System.out.println("Erro ao atualizar: " + e);

        } finally {

            sessao.close();
            //growl.addMessage("Lista de Itens atualizada!");
        }

        return lista;
    }

}
