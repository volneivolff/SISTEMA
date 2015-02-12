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

@Entity(name = "MOVIMENTACAO_DO_ESTOQUE")
public class MovEstoque implements Serializable {

    private static final long serialVersionUID = 1L;

    //Auto Incremento
    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "id", sequenceName = "MOVIMENTACAO_SEQUENCE")
    @Column(name = "MOV_ID")
    private Integer id;

    @Column(name = "MOV_ITEM_ID")
    private Integer movItemId;

    @Column(name = "MOV_UAUARIO_ALTERADOR")
    private String nome;

    @Column(name = "MOV_QUANT_INICIAL")
    private double quantInicial;

    @Column(name = "MOV_QUANT_FINAL")
    private double quantFinal;

    @Column(name = "MOV_QUANT_RETIRADA")
    private double quantRetirada;

    @Column(name = "MOV_DATA_DE_ALTERACAO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataDeCadastro;

    @Column(name = "MOV_OBS")
    private String obs;

    @Column(name = "VENDA_EFETIVA")
    private double vendaEfetiva;

    @Column(name = "MARKUP_EFETIVO")
    private double markupEfetivo;

    @Column(name = "GANHO_EFETIVO")
    private double ganhoEfetivo;
     
    public double getGanhoEfetivo() {
        return ganhoEfetivo;
    }

    public void setGanhoEfetivo(double ganhoEfetivo) {
        this.ganhoEfetivo = ganhoEfetivo;
    }

    public double getVendaEfetiva() {
        return vendaEfetiva;
    }

    public void setVendaEfetiva(double vendaEfetiva) {
        this.vendaEfetiva = vendaEfetiva;
    }

    public double getMarkupEfetivo() {
        return markupEfetivo;
    }

    public void setMarkupEfetivo(double markupEfetivo) {
        this.markupEfetivo = markupEfetivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantInicial() {
        return quantInicial;
    }

    public void setQuantInicial(double quantInicial) {
        this.quantInicial = quantInicial;
    }

    public double getQuantFinal() {
        return quantFinal;
    }

    public void setQuantFinal(double quantFinal) {
        this.quantFinal = quantFinal;
    }

    public double getQuantRetirada() {
        return quantRetirada;
    }

    public void setQuantRetirada(double quantRetirada) {
        this.quantRetirada = quantRetirada;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getMovItemId() {
        return movItemId;
    }

    public void setMovItemId(Integer movItemId) {
        this.movItemId = movItemId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final MovEstoque other = (MovEstoque) obj;
        return !(this.id != other.id && (this.id == null || !this.id.equals(other.id)));
    }

}
