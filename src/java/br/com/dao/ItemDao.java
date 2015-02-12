package br.com.dao;

import br.com.code.Growl;
import br.com.entity.Item;
import br.com.util.HibernateUtil;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ItemDao implements Serializable {

    private static final long serialVersionUID = 1L;

    Item item = new Item();
    Growl growl = new Growl();
    private List<Item> lista;
    private List<Item> listaItem;

    public ItemDao(Session sessao, Transaction transacao) {
        this.sessao = sessao;
        this.transacao = transacao;
    }

    public ItemDao() {
    }

    private Session sessao;
    private Transaction transacao;

    public void adicionaItem(Item itemBean) {
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            item.setMarca(itemBean.getMarca());
            item.setModelo(itemBean.getModelo());
            item.setDescricao(itemBean.getDescricao());
            item.setDescription(itemBean.getDescription());
            item.setPeso(itemBean.getPeso());
            item.setCodOem(itemBean.getCodOem());
            item.setFabricante(itemBean.getFabricante());
            item.setFornecedor(itemBean.getFornecedor());
            item.setQuantidade(itemBean.getQuantidade());
            item.setTaxaDeCambio(itemBean.getTaxaDeCambio());
            item.setPrecoMinimo(itemBean.getPrecoMinimo());
            item.setPrecoMaximo(itemBean.getPrecoMaximo());
            item.setCustoDoProduto(itemBean.getCustoDoProduto());
            item.setMarkup(itemBean.getMarkup());
            item.setReposicao(itemBean.getReposicao());
            item.setImpostos(itemBean.getImpostos());
            item.setFrete(itemBean.getFrete());
            item.setCustoOperacional(itemBean.getCustoOperacional());
            item.setCustoIndireto(itemBean.getCustoIndireto());
            item.setPrevisaoDePerdas(itemBean.getPrevisaoDePerdas());
            item.setOrigem(itemBean.getOrigem());
            item.setNfeEntrada(itemBean.getNfeEntrada());
            item.setNcm(itemBean.getNcm());
            item.setIi(itemBean.getIi());
            item.setIpi(itemBean.getIpi());
            item.setPis(itemBean.getPis());
            item.setCofins(itemBean.getCofins());
            item.setIcms(itemBean.getIcms());
            item.setSt(itemBean.getSt());
            item.setValorDaVenda(itemBean.getValorDaVenda());
            item.setLucroDaVenda(itemBean.getLucroDaVenda());
            item.setValorTotal(itemBean.getValorTotal());
            item.setLucroTotal(itemBean.getLucroTotal());
            item.setObs(itemBean.getObs());
            item.setCadastrante(itemBean.getCadastrante());
            item.setFoto(itemBean.getFoto());
            item.setDataDeCadastro(new Timestamp(System.currentTimeMillis()));
            item.setCategoria(itemBean.getCategoria());
            item.setReserva(itemBean.getReserva());
            item.setSolicitante(itemBean.getSolicitante());
            item.setEquivalencia(itemBean.getEquivalencia());

            //DIA 05/02/2015 - LOCALIZAÇÃO DOS PRODUTOS NAS PRATELEIRAS
            item.setArmario(itemBean.getArmario());
            item.setPrateleira(itemBean.getPrateleira());
            item.setLocalPrateleira(itemBean.getLocalPrateleira());

            item.setMarkup_A(itemBean.getMarkup_A());
            item.setMarkup_B(itemBean.getMarkup_A());
            item.setMarkup_C(itemBean.getMarkup_A());

            item.setValorDaVenda_A(itemBean.getValorDaVenda_A());
            item.setValorDaVenda_B(itemBean.getValorDaVenda_B());
            item.setValorDaVenda_C(itemBean.getValorDaVenda_C());

            item.setLucroDaVenda_A(itemBean.getLucroDaVenda_A());
            item.setLucroDaVenda_B(itemBean.getLucroDaVenda_B());
            item.setLucroDaVenda_C(itemBean.getLucroDaVenda_C());

            item.setValorTotal_A(itemBean.getValorTotal_A());
            item.setValorTotal_B(itemBean.getValorTotal_B());
            item.setValorTotal_C(itemBean.getValorTotal_C());

            item.setLucroTotal_A(itemBean.getLucroTotal_A());
            item.setLucroTotal_B(itemBean.getLucroTotal_B());
            item.setLucroTotal_C(itemBean.getLucroTotal_C());

            sessao.save(item);

            transacao.commit();

            growl.addMessage("Sucesso!", "Adição ao estoque realizada com sucesso!");

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao adicionar Item no estoque:" + e);

        } finally {

            sessao.close();
        }
    }

    public void alteraItem(Item itemBean) {

        try {

            item = itemBean;

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            sessao.update(item);
            transacao.commit();

            growl.addMessage("Sucesso!", "Alteração realzada com sucesso!");

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao alterar Item no estoque:" + e);

        } finally {

            sessao.close();
        }
    }

    public void deletaItem(Item itemBean) {

        try {

            item = itemBean;

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            sessao.delete(item);
            transacao.commit();

            growl.addMessage("Sucesso!", "Exclusão realizada com sucesso!");

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro excluir Item no estoque:" + e);

        } finally {

            sessao.close();
        }
    }

    public List<Item> pegaLista() {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            Criteria cri = sessao.createCriteria(Item.class);

            this.lista = cri.addOrder(Order.asc("id")).list();

        } catch (HibernateException e) {

            growl.addMessage("Erro!", "Erro ao atualizar a Lista:" + e
            );

        } finally {

            sessao.close();
            //growl.addMessage("Lista de Itens atualizada!");
        }

        return lista;
    }

    public List<Item> DadosDoItem(Integer ID) {
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            Criteria cri = sessao.createCriteria(Item.class);

            cri.add(Restrictions.eq("id", ID));

            this.listaItem = cri.list();

        } catch (Exception e) {

            System.out.println(e);

        } finally {

            sessao.close();
        }

        return listaItem;
    }
}
