package br.com.dao;

import br.com.entity.Pedido;
import br.com.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class PedidoDao implements Serializable {

    private static final long serialVersionUID = 1L;

    private Session sessao;
    private Transaction transacao;
    Pedido pedido = new Pedido();
    private List lista;

    public PedidoDao(Session sessao, Transaction transacao) {
        this.sessao = sessao;
        this.transacao = transacao;
    }

    public PedidoDao() {

    }

    public void addItemAoPedido(Pedido pedidoBean) {

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            //pedido.setId(pedidoBean.getId());
            pedido.setClienteNome(pedidoBean.getClienteNome());
            pedido.setChavePedido(pedidoBean.getChavePedido());
            pedido.setItemId(pedidoBean.getItemId());
            pedido.setMarca(pedidoBean.getMarca());
            pedido.setModelo(pedidoBean.getModelo());
            pedido.setDescricao(pedidoBean.getDescricao());
            pedido.setDescription(pedidoBean.getDescription());
            pedido.setPeso(pedidoBean.getPeso());
            pedido.setCodOem(pedidoBean.getCodOem());
            pedido.setFabricante(pedidoBean.getFabricante());
            pedido.setFornecedor(pedidoBean.getFornecedor());
            pedido.setQuantidade(pedidoBean.getQuantidade());
            pedido.setTaxaDeCambio(pedidoBean.getTaxaDeCambio());
            pedido.setPrecoMinimo(pedidoBean.getPrecoMinimo());
            pedido.setPrecoMaximo(pedidoBean.getPrecoMaximo());
            pedido.setCustoDoProduto(pedidoBean.getCustoDoProduto());
            pedido.setMarkup(pedidoBean.getMarkup());
            pedido.setReposicao(pedidoBean.getReposicao());
            pedido.setImpostos(pedidoBean.getImpostos());
            pedido.setFrete(pedidoBean.getFrete());
            pedido.setCustoOperacional(pedidoBean.getCustoOperacional());
            pedido.setCustoIndireto(pedidoBean.getCustoIndireto());
            pedido.setPrevisaoDePerdas(pedidoBean.getPrevisaoDePerdas());
            pedido.setOrigem(pedidoBean.getOrigem());
            pedido.setNfeEntrada(pedidoBean.getNfeEntrada());
            pedido.setNcm(pedidoBean.getNcm());
            pedido.setIi(pedidoBean.getIi());
            pedido.setIpi(pedidoBean.getIpi());
            pedido.setPis(pedidoBean.getPis());
            pedido.setCofins(pedidoBean.getCofins());
            pedido.setIcms(pedidoBean.getIcms());
            pedido.setSt(pedidoBean.getSt());
            pedido.setValorDaVenda(pedidoBean.getValorDaVenda());
            pedido.setLucroDaVenda(pedidoBean.getLucroDaVenda());
            pedido.setValorTotal(pedidoBean.getValorTotal());
            pedido.setLucroTotal(pedidoBean.getLucroTotal());
            pedido.setObs(pedidoBean.getObs());
            pedido.setCadastrante(pedidoBean.getCadastrante());
            pedido.setFoto(pedidoBean.getFoto());
            pedido.setCategoria(pedidoBean.getCategoria());
            pedido.setReserva(pedidoBean.getReserva());
            pedido.setSolicitante(pedidoBean.getSolicitante());
            pedido.setUsuarioUltimaModificacao(pedidoBean.getUsuarioUltimaModificacao());
            pedido.setDataUltimaModificacao(pedidoBean.getDataUltimaModificacao());
            pedido.setDataDeCadastro(pedidoBean.getDataDeCadastro());
            pedido.setClienteId(pedidoBean.getClienteId());
            pedido.setVenda(pedidoBean.isVenda());

            sessao.save(pedido);
            transacao.commit();

            System.out.println("Item do Pedido adicionado com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro: " + e);
        } finally {

            sessao.close();
        }

    }

    public void removeItemDoPedido(Pedido pedidoBean) {

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            sessao.delete(pedidoBean);
            transacao.commit();

            System.out.println("Item do Pedido removido com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro: " + e);
        } finally {

            sessao.close();
        }
    }

    public void editaItemDoPedido(Pedido pedidoBean) {

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            sessao.update(pedidoBean);
            transacao.commit();

            System.out.println("Item do Pedido alterado com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro: " + e);
        } finally {

            sessao.close();
        }
    }

    public List<Pedido> listaItensPedido() {

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            Criteria cri = sessao.createCriteria(Pedido.class);

            this.lista = cri.addOrder(Order.asc("id")).list();

            System.out.println("Lista de Ites do Pedido crada com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro: " + e);
        } finally {

            sessao.close();
        }

        return lista;
    }

    public List<Pedido> consultaItensPedido(String chave) {

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            Criteria cri = sessao.createCriteria(Pedido.class);
            cri.add(Restrictions.eq("chavePedido", chave));

            this.lista = cri.list();

            System.out.println("Lista de Ites do Pedido crada com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro: " + e);
        } finally {

            sessao.close();
        }

        return lista;
    }
}
