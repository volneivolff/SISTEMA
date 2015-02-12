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

@Entity(name = "FORNECEDORES_REVESTEBEM")
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "id", sequenceName = "FORNECEDOR_SEQUENCE")
    @Column(name = "FORNECEDOR_ID")
    private Integer id;

    @Column(name = "FORNECEDOR_NOME")
    private String nome;

    @Column(name = "FORNECEDOR_CONTATO")
    private String contato;

    @Column(name = "FORNECEDOR_EMAIL")
    private String email;

    @Column(name = "FORNECEDOR_CHAT")
    private String chat;

    @Column(name = "FORNECEDOR_TELEFONE")
    private String telefone;

    @Column(name = "FORNECEDOR_CADASTRANTE")
    private String cadastrante;

    @Column(name = "FORNECEDOR_DATA_DE_CADASTRO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date cadastro;

    @Column(name = "FORNECEDOR_INTERACAO", length = 10000)
    private String interacao;
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCadastrante() {
        return cadastrante;
    }

    public void setCadastrante(String cadastrante) {
        this.cadastrante = cadastrante;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getInteracao() {
        return interacao;
    }

    public void setInteracao(String interacao) {
        this.interacao = interacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Fornecedor other = (Fornecedor) obj;
        return this.id == other.id || (this.id != null && this.id.equals(other.id));
    }

}
