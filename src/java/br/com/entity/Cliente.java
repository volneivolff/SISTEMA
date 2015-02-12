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

@Entity(name = "CLIENTES_REVESTEBEM")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "id", sequenceName = "CLIENTE_SEQUENCE")
    @Column(name = "CLIENTE_ID")
    private Integer id;

    @Column(name = "CLIENTE_NOME")
    private String nome;

    @Column(name = "CLIENTE_CONTATO")
    private String contato;

    @Column(name = "CLIENTE_EMAIL")
    private String email;

    @Column(name = "CLIENTE_MARCAS")
    private String marcas;

    @Column(name = "CLIENTE_OWNER")
    private String owner;

    @Column(name = "CLIENTE_CHAT")
    private String chat;

    @Column(name = "CLIENTE_TELEFONE")
    private String telefone;

    @Column(name = "CLIENTE_CADASTRANTE")
    private String cadastrante;

    @Column(name = "CLIENTE_DATA_DE_CADASTRO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date cadastro;

    @Column(name = "CLIENTE_INTERACAO", length = 5000, columnDefinition = "TEXT")
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

    public String getMarcas() {
        return marcas;
    }

    public void setMarcas(String marcas) {
        this.marcas = marcas;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
        final Cliente other = (Cliente) obj;
        return this.id == other.id || (this.id != null && this.id.equals(other.id));
    }

}
