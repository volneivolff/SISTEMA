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

@Entity(name = "ITENS_DO_ESTOQUE")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    //Auto Incremento
    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "id", sequenceName = "ITEM_SEQUENCE")
    @Column(name = "ESTOQUE_ID")
    private Integer id;

    @Column(name = "ESTOQUE_ARMARIO")
    private String armario;

    @Column(name = "ESTOQUE_PRATELEIRA")
    private String prateleira;

    @Column(name = "ESTOQUE_LOCAL_PRATELEIRA")
    private String localPrateleira;

    @Column(name = "ESTOQUE_MARCA")
    private String marca;

    @Column(name = "ESTOQUE_MODELO")
    private String modelo;

    @Column(name = "ESTOQUE_EQUIVA")
    private String equivalencia;

    @Column(name = "ESTOQUE_DESCRICAO", length = 5000, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "ESTOQUE_DESCRIPTION")
    private String description;

    @Column(name = "ESTOQUE_PESO")
    private double peso;

    @Column(name = "ESTOQUE_COIGO_OEM")
    private String codOem;

    @Column(name = "ESTOQUE_FABRICANTE")
    private String fabricante;

    @Column(name = "ESTOQUE_FORNECEDOR")
    private String fornecedor;

    @Column(name = "ESTOQUE_QUANTIDADE")
    private double quantidade;

    @Column(name = "ESTOQUE_TAXA_DE_CAMBIO")
    private double taxaDeCambio;

    @Column(name = "ESTOQUE_PRECO_MINIMO")
    private double precoMinimo;

    @Column(name = "ESTOQUE_PRECO_MAXIMO")
    private double precoMaximo;

    @Column(name = "ESTOQUE_CUSTO_DO_PRODUTO")
    private double custoDoProduto;

    @Column(name = "ESTOQUE_REPOSICAO")
    private double reposicao;

    @Column(name = "ESTOQUE_IMPOSTOS")
    private double impostos;

    @Column(name = "ESTOQUE_FRETE")
    private double frete;

    @Column(name = "ESTOQUE_CUSTO_OPERACIONAL")
    private double custoOperacional;

    @Column(name = "ESTOQUE_CUSTO_INDIRETO")
    private double custoIndireto;

    @Column(name = "ESTOQUE_PREVISAO_DE_PERDAS")
    private double previsaoDePerdas;

    @Column(name = "ESTOQUE_ORIGEM_DO_ITEM")
    private String origem;

    @Column(name = "ESTOQUE_NOTA_FISCAL_DE_ENTRADA")
    private String nfeEntrada;

    @Column(name = "ESTOQUE_NCM")
    private String ncm;

    @Column(name = "ESTOQUE_II")
    private double ii;

    @Column(name = "ESTOQUE_IPI")
    private double ipi;

    @Column(name = "ESTOQUE_PIS")
    private double pis;

    @Column(name = "ESTOQUE_COFINS")
    private double cofins;

    @Column(name = "ESTOQUE_ICMS")
    private double icms;

    @Column(name = "ESTOQUE_ST")
    private double st;

    @Column(name = "ESTOQUE_CADASTRANTE")
    private String cadastrante;

    @Column(name = "ESTOQUE_FOTO")
    private String foto;

    @Column(name = "ESTOQUE_CATEGORIA")
    private String categoria;

    @Column(name = "ESTOQUE_DESTINO")
    private String reserva;

    @Column(name = "ESTOQUE_SOLICITANTE")
    private String solicitante;

    @Column(name = "ESTOQUE_USUARIO_ULTIMA_MODIFICACAO")
    private String usuarioUltimaModificacao;

    @Column(name = "ESTOQUE_DATA_ULTIMA_MODIFICACAO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataUltimaModificacao;

    @Column(name = "ESTOQUE_DATA_DE_CADASTRO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataDeCadastro;

    /*===========CATEGORIA Z===========*/
    @Column(name = "ESTOQUE_MARKUP")
    private double markup;

    @Column(name = "ESTOQUE_VALOR_DA_VENDA")
    private double valorDaVenda;

    @Column(name = "ESTOQUE_LUCRO_DA_VENDA")
    private double lucroDaVenda;

    @Column(name = "ESTOQUE_VALOR_TOTAL")
    private double valorTotal;

    @Column(name = "ESTOQUE_LUCRO_TOTAL")
    private double lucroTotal;

    @Column(name = "ESTOQUE_OBS")
    private String obs;
    /*=================================*/

    /*===========CATEGORIA A===========*/
    @Column(name = "ESTOQUE_MARKUP_A")
    private double markup_A;

    @Column(name = "ESTOQUE_VALOR_DA_VENDA_A")
    private double valorDaVenda_A;

    @Column(name = "ESTOQUE_LUCRO_DA_VENDA_A")
    private double lucroDaVenda_A;

    @Column(name = "ESTOQUE_VALOR_TOTAL_A")
    private double valorTotal_A;

    @Column(name = "ESTOQUE_LUCRO_TOTAL_A")
    private double lucroTotal_A;

    @Column(name = "ESTOQUE_OBS_A")
    private String obs_A;
    /*=================================*/

    /*===========CATEGORIA B===========*/
    @Column(name = "ESTOQUE_MARKUP_B")
    private double markup_B;

    @Column(name = "ESTOQUE_VALOR_DA_VENDA_B")
    private double valorDaVenda_B;

    @Column(name = "ESTOQUE_LUCRO_DA_VENDA_B")
    private double lucroDaVenda_B;

    @Column(name = "ESTOQUE_VALOR_TOTAL_B")
    private double valorTotal_B;

    @Column(name = "ESTOQUE_LUCRO_TOTAL_B")
    private double lucroTotal_B;

    @Column(name = "ESTOQUE_OBS_B")
    private String obs_B;
    /*=================================*/

    /*===========CATEGORIA C===========*/
    @Column(name = "ESTOQUE_MARKUP_C")
    private double markup_C;

    @Column(name = "ESTOQUE_VALOR_DA_VENDA_C")
    private double valorDaVenda_C;

    @Column(name = "ESTOQUE_LUCRO_DA_VENDA_C")
    private double lucroDaVenda_C;

    @Column(name = "ESTOQUE_VALOR_TOTAL_C")
    private double valorTotal_C;

    @Column(name = "ESTOQUE_LUCRO_TOTAL_C")
    private double lucroTotal_C;

    @Column(name = "ESTOQUE_OBS_C")
    private String obs_C;
    /*=================================*/

    public String getArmario() {
        return armario;
    }

    public void setArmario(String armario) {
        this.armario = armario;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    public String getLocalPrateleira() {
        return localPrateleira;
    }

    public void setLocalPrateleira(String localPrateleira) {
        this.localPrateleira = localPrateleira;
    }

    public double getMarkup_A() {
        return markup_A;
    }

    public void setMarkup_A(double markup_A) {
        this.markup_A = markup_A;
    }

    public double getValorDaVenda_A() {
        return valorDaVenda_A;
    }

    public void setValorDaVenda_A(double valorDaVenda_A) {
        this.valorDaVenda_A = valorDaVenda_A;
    }

    public double getLucroDaVenda_A() {
        return lucroDaVenda_A;
    }

    public void setLucroDaVenda_A(double lucroDaVenda_A) {
        this.lucroDaVenda_A = lucroDaVenda_A;
    }

    public double getValorTotal_A() {
        return valorTotal_A;
    }

    public void setValorTotal_A(double valorTotal_A) {
        this.valorTotal_A = valorTotal_A;
    }

    public double getLucroTotal_A() {
        return lucroTotal_A;
    }

    public void setLucroTotal_A(double lucroTotal_A) {
        this.lucroTotal_A = lucroTotal_A;
    }

    public String getObs_A() {
        return obs_A;
    }

    public void setObs_A(String obs_A) {
        this.obs_A = obs_A;
    }

    public double getMarkup_B() {
        return markup_B;
    }

    public void setMarkup_B(double markup_B) {
        this.markup_B = markup_B;
    }

    public double getValorDaVenda_B() {
        return valorDaVenda_B;
    }

    public void setValorDaVenda_B(double valorDaVenda_B) {
        this.valorDaVenda_B = valorDaVenda_B;
    }

    public double getLucroDaVenda_B() {
        return lucroDaVenda_B;
    }

    public void setLucroDaVenda_B(double lucroDaVenda_B) {
        this.lucroDaVenda_B = lucroDaVenda_B;
    }

    public double getValorTotal_B() {
        return valorTotal_B;
    }

    public void setValorTotal_B(double valorTotal_B) {
        this.valorTotal_B = valorTotal_B;
    }

    public double getLucroTotal_B() {
        return lucroTotal_B;
    }

    public void setLucroTotal_B(double lucroTotal_B) {
        this.lucroTotal_B = lucroTotal_B;
    }

    public String getObs_B() {
        return obs_B;
    }

    public void setObs_B(String obs_B) {
        this.obs_B = obs_B;
    }

    public double getMarkup_C() {
        return markup_C;
    }

    public void setMarkup_C(double markup_C) {
        this.markup_C = markup_C;
    }

    public double getValorDaVenda_C() {
        return valorDaVenda_C;
    }

    public void setValorDaVenda_C(double valorDaVenda_C) {
        this.valorDaVenda_C = valorDaVenda_C;
    }

    public double getLucroDaVenda_C() {
        return lucroDaVenda_C;
    }

    public void setLucroDaVenda_C(double lucroDaVenda_C) {
        this.lucroDaVenda_C = lucroDaVenda_C;
    }

    public double getValorTotal_C() {
        return valorTotal_C;
    }

    public void setValorTotal_C(double valorTotal_C) {
        this.valorTotal_C = valorTotal_C;
    }

    public double getLucroTotal_C() {
        return lucroTotal_C;
    }

    public void setLucroTotal_C(double lucroTotal_C) {
        this.lucroTotal_C = lucroTotal_C;
    }

    public String getObs_C() {
        return obs_C;
    }

    public void setObs_C(String obs_C) {
        this.obs_C = obs_C;
    }

    public String getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(String equivalencia) {
        this.equivalencia = equivalencia;
    }

    public String getReserva() {
        return reserva;
    }

    public void setReserva(String reserva) {
        this.reserva = reserva.toUpperCase();
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante.toUpperCase();
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria.toUpperCase();
    }

    public String getUsuarioUltimaModificacao() {
        return usuarioUltimaModificacao;
    }

    public void setUsuarioUltimaModificacao(String usuarioUltimaModificacao) {
        this.usuarioUltimaModificacao = usuarioUltimaModificacao.toUpperCase();
    }

    public Date getDataUltimaModificacao() {
        return dataUltimaModificacao;
    }

    public void setDataUltimaModificacao(Date dataUltimaModificacao) {
        this.dataUltimaModificacao = dataUltimaModificacao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public String getCadastrante() {
        return cadastrante;
    }

    public void setCadastrante(String cadastrante) {
        this.cadastrante = cadastrante.toUpperCase();
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
        this.obs = obs.toUpperCase();
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

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm.toUpperCase();
    }

    public String getNfeEntrada() {
        return nfeEntrada;
    }

    public void setNfeEntrada(String nfeEntrada) {
        this.nfeEntrada = nfeEntrada.toUpperCase();
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem.toUpperCase();
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

    public double getCustoDoProduto() {
        return custoDoProduto;
    }

    public void setCustoDoProduto(double custoDoPRoduto) {
        this.custoDoProduto = custoDoPRoduto;
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

    public double getTaxaDeCambio() {
        return taxaDeCambio;
    }

    public void setTaxaDeCambio(double taxaDeCambio) {
        this.taxaDeCambio = taxaDeCambio;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getCodOem() {
        return codOem;
    }

    public void setCodOem(String codOem) {
        this.codOem = codOem.toUpperCase();
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante.toUpperCase();
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor.toUpperCase();
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.toUpperCase();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.toUpperCase();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca.toUpperCase();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo.toUpperCase();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Item other = (Item) obj;
        return this.id == other.id || (this.id != null && this.id.equals(other.id));
    }
}
