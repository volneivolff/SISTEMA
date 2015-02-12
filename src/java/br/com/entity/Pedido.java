package br.com.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

@Entity(name = "ITENS_DO_PEDIDO")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "id", sequenceName = "PEDIDO_SEQUENCE")
    @Column(name = "PEDIDO_ID")
    private Integer id;

    @Column(name = "PEDIDO_PEDIDO")
    private String chavePedido;

    @Column(name = "PEDIDO_ITEM_ID")
    private Integer itemId;

    @Column(name = "PEDIDO_ITEM_MARCA")
    private String marca;

    @Column(name = "PEDIDO_ITEM_MODELO")
    private String modelo;

    @Column(name = "PEDIDO_ITEM_DESCRICAO")
    private String descricao;

    @Column(name = "PEDIDO_ITEM_DESCRIPTION")
    private String description;

    @Column(name = "PEDIDO_ITEM_PESO")
    private double peso;

    @Column(name = "PEDIDO_ITEM_COIGO_OEM")
    private String codOem;

    @Column(name = "PEDIDO_ITEM_FABRICANTE")
    private String fabricante;

    @Column(name = "PEDIDO_ITEM_FORNECEDOR")
    private String fornecedor;

    @Column(name = "PEDIDO_ITEM_QUANTIDADE")
    private double quantidade;

    @Column(name = "PEDIDO_ITEM_TAXA_DE_CAMBIO")
    private double taxaDeCambio;

    @Column(name = "PEDIDO_ITEM_PRECO_MINIMO")
    private double precoMinimo;

    @Column(name = "PEDIDO_ITEM_PRECO_MAXIMO")
    private double precoMaximo;

    @Column(name = "PEDIDO_ITEM_CUSTO_DO_PRODUTO")
    private double custoDoProduto;

    @Column(name = "PEDIDO_ITEM_MARKUP")
    private double markup;

    @Column(name = "PEDIDO_ITEM_REPOSICAO")
    private double reposicao;

    @Column(name = "PEDIDO_ITEM_IMPOSTOS")
    private double impostos;

    @Column(name = "PEDIDO_ITEM_FRETE")
    private double frete;

    @Column(name = "PEDIDO_ITEM_CUSTO_OPERACIONAL")
    private double custoOperacional;

    @Column(name = "PEDIDO_ITEM_CUSTO_INDIRETO")
    private double custoIndireto;

    @Column(name = "PEDIDO_ITEM_PREVISAO_DE_PERDAS")
    private double previsaoDePerdas;

    @Column(name = "PEDIDO_ITEM_ORIGEM_DO_ITEM")
    private String origem;

    @Column(name = "PEDIDO_ITEM_NOTA_FISCAL_DE_ENTRADA")
    private String nfeEntrada;

    @Column(name = "PEDIDO_ITEM_NCM")
    private String ncm;

    @Column(name = "PEDIDO_ITEM_II")
    private double ii;

    @Column(name = "PEDIDO_ITEM_IPI")
    private double ipi;

    @Column(name = "PEDIDO_ITEM_PIS")
    private double pis;

    @Column(name = "PEDIDO_ITEM_COFINS")
    private double cofins;

    @Column(name = "PEDIDO_ITEM_ICMS")
    private double icms;

    @Column(name = "PEDIDO_ITEM_ST")
    private double st;

    @Column(name = "PEDIDO_ITEM_VALOR_DA_VENDA")
    private double valorDaVenda;

    @Column(name = "PEDIDO_ITEM_LUCRO_DA_VENDA")
    private double lucroDaVenda;

    @Column(name = "PEDIDO_ITEM_VALOR_TOTAL")
    private double valorTotal;

    @Column(name = "PEDIDO_ITEM_LUCRO_TOTAL")
    private double lucroTotal;

    @Column(name = "PEDIDO_ITEM_OBS")
    private String obs;

    @Column(name = "PEDIDO_ITEM_CADASTRANTE")
    private String cadastrante;

    @Column(name = "PEDIDO_ITEM_FOTO")
    private String foto;

    @Column(name = "PEDIDO_ITEM_CATEGORIA")
    private String categoria;

    @Column(name = "PEDIDO_ITEM_DESTINO")
    private String reserva;

    @Column(name = "PEDIDO_ITEM_SOLICITANTE")
    private String solicitante;

    @Column(name = "PEDIDO_ITEM_USUARIO_ULTIMA_MODIFICACAO")
    private String usuarioUltimaModificacao;

    @Column(name = "PEDIDO_ITEM_DATA_ULTIMA_MODIFICACAO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataUltimaModificacao;

    @Column(name = "PEDIDO_ITEM_DATA_DE_CADASTRO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataDeCadastro;

    @Column(name = "PEDIDO_CLIENTE_ID")
    private Integer clienteId;

    @Column(name = "PEDIDO_CLIENTE_NOME")
    private String clienteNome;

    @Column(name = "PEDIDO_ITEM_VENDA")
    private boolean venda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getChavePedido() {
        return chavePedido;
    }

    public void setChavePedido(String chavePedido) {
        this.chavePedido = chavePedido;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCodOem() {
        return codOem;
    }

    public void setCodOem(String codOem) {
        this.codOem = codOem;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getTaxaDeCambio() {
        return taxaDeCambio;
    }

    public void setTaxaDeCambio(double taxaDeCambio) {
        this.taxaDeCambio = taxaDeCambio;
    }

    public double getPrecoMinimo() {
        return precoMinimo;
    }

    public void setPrecoMinimo(double precoMinimo) {
        this.precoMinimo = precoMinimo;
    }

    public double getPrecoMaximo() {
        return precoMaximo;
    }

    public void setPrecoMaximo(double precoMaximo) {
        this.precoMaximo = precoMaximo;
    }

    public double getCustoDoProduto() {
        return custoDoProduto;
    }

    public void setCustoDoProduto(double custoDoProduto) {
        this.custoDoProduto = custoDoProduto;
    }

    public double getMarkup() {
        return markup;
    }

    public void setMarkup(double markup) {
        this.markup = markup;
    }

    public double getReposicao() {
        return reposicao;
    }

    public void setReposicao(double reposicao) {
        this.reposicao = reposicao;
    }

    public double getImpostos() {
        return impostos;
    }

    public void setImpostos(double impostos) {
        this.impostos = impostos;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public double getCustoOperacional() {
        return custoOperacional;
    }

    public void setCustoOperacional(double custoOperacional) {
        this.custoOperacional = custoOperacional;
    }

    public double getCustoIndireto() {
        return custoIndireto;
    }

    public void setCustoIndireto(double custoIndireto) {
        this.custoIndireto = custoIndireto;
    }

    public double getPrevisaoDePerdas() {
        return previsaoDePerdas;
    }

    public void setPrevisaoDePerdas(double previsaoDePerdas) {
        this.previsaoDePerdas = previsaoDePerdas;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getNfeEntrada() {
        return nfeEntrada;
    }

    public void setNfeEntrada(String nfeEntrada) {
        this.nfeEntrada = nfeEntrada;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public double getIi() {
        return ii;
    }

    public void setIi(double ii) {
        this.ii = ii;
    }

    public double getIpi() {
        return ipi;
    }

    public void setIpi(double ipi) {
        this.ipi = ipi;
    }

    public double getPis() {
        return pis;
    }

    public void setPis(double pis) {
        this.pis = pis;
    }

    public double getCofins() {
        return cofins;
    }

    public void setCofins(double cofins) {
        this.cofins = cofins;
    }

    public double getIcms() {
        return icms;
    }

    public void setIcms(double icms) {
        this.icms = icms;
    }

    public double getSt() {
        return st;
    }

    public void setSt(double st) {
        this.st = st;
    }

    public double getValorDaVenda() {
        return valorDaVenda;
    }

    public void setValorDaVenda(double valorDaVenda) {
        this.valorDaVenda = valorDaVenda;
    }

    public double getLucroDaVenda() {
        return lucroDaVenda;
    }

    public void setLucroDaVenda(double lucroDaVenda) {
        this.lucroDaVenda = lucroDaVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getLucroTotal() {
        return lucroTotal;
    }

    public void setLucroTotal(double lucroTotal) {
        this.lucroTotal = lucroTotal;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getCadastrante() {
        return cadastrante;
    }

    public void setCadastrante(String cadastrante) {
        this.cadastrante = cadastrante;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getReserva() {
        return reserva;
    }

    public void setReserva(String reserva) {
        this.reserva = reserva;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getUsuarioUltimaModificacao() {
        return usuarioUltimaModificacao;
    }

    public void setUsuarioUltimaModificacao(String usuarioUltimaModificacao) {
        this.usuarioUltimaModificacao = usuarioUltimaModificacao;
    }

    public Date getDataUltimaModificacao() {
        return dataUltimaModificacao;
    }

    public void setDataUltimaModificacao(Date dataUltimaModificacao) {
        this.dataUltimaModificacao = dataUltimaModificacao;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public boolean isVenda() {
        return venda;
    }

    public void setVenda(boolean venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        return !(this.id != other.id && (this.id == null || !this.id.equals(other.id)));
    }

}
