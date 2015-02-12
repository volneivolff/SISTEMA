package br.com.dao;

import br.com.code.Growl;
import br.com.entity.Cliente;
import br.com.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

public class ClienteDao implements Serializable {

    private static final long serialVersionUID = 1L;

    Cliente cliente = new Cliente();
    private Session sessao;
    private Transaction transacao;
    Growl growl = new Growl();
    List<Cliente> clienteList;

    public ClienteDao(Session sessao, Transaction transacao) {
        this.sessao = sessao;
        this.transacao = transacao;
    }

    public ClienteDao() {
    }

    public void adicionaCliente(Cliente clienteBean) {
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();

            transacao = sessao.beginTransaction();

            cliente.setNome(clienteBean.getNome());
            cliente.setEmail(clienteBean.getEmail());
            cliente.setCadastrante(clienteBean.getCadastrante());
            cliente.setCadastro(clienteBean.getCadastro());
            cliente.setContato(clienteBean.getContato());
            cliente.setTelefone(clienteBean.getTelefone());
            cliente.setChat(clienteBean.getChat());
            cliente.setInteracao(clienteBean.getInteracao());
            cliente.setOwner(clienteBean.getOwner());
            cliente.setMarcas(clienteBean.getMarcas());
            sessao.save(cliente);

            transacao.commit();

            growl.addMessage("Sucesso!", "Adição de Cliente realizada com sucesso!");

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao adicionar Cliente:" + e);

        } finally {

            sessao.close();
        }

    }

    public void removeCliente(Cliente clienteBean) {
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            cliente = clienteBean;

            sessao.delete(cliente);

            transacao.commit();

            growl.addMessage("Sucesso!", "Cliente removido com sucesso!");

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao remover Cliente:" + e);

        } finally {

            sessao.close();
        }

    }

    public void editaCliente(Cliente clienteBean) {
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            cliente = clienteBean;

            sessao.update(cliente);

            transacao.commit();

            growl.addMessage("Sucesso!", "Cliente alterado com sucesso!");

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao alterar Cliente:" + e);

        } finally {

            sessao.close();
        }

    }

    public List<Cliente> listaClientes() {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            Criteria cri = sessao.createCriteria(Cliente.class);

            this.clienteList = cri.addOrder(Order.asc("id")).list();

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao atualizar a Lista:" + e);

        } finally {

            sessao.close();
            //growl.addMessage("Lista de Itens atualizada!");
        }

        return clienteList;
    }

}
