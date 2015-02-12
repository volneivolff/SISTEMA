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

@Entity(name = "USUARIO_DO_SISTEMA")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    //Auto Incremento
    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "id", sequenceName = "USUARIO_SEQUENCE")
    @Column(name = "USUARIO_ID")
    private Integer id;

    @Column(name = "USUARIO_NOME")
    private String nome;

    @Column(name = "USUARIO_EMAIL")
    private String email;

    @Column(name = "USUARIO_TELEFONE")
    private String telefone;

    @Column(name = "USUARIO_USUARIO")
    private String usuario;

    @Column(name = "USUARIO_SENHA")
    private String senha;

    @Column(name = "USUARIO_CADASTRANTE")
    private String cadastrante;

    @Column(name = "USUARIO_DATA_DE_CADASTRO")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataDeCadastro;

    @Column(name = "USUARIO_ADMINISTRADOR")
    private boolean administrador;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCadastrante() {
        return cadastrante;
    }

    public void setCadastrante(String cadastrante) {
        this.cadastrante = cadastrante;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario.toUpperCase();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Usuario other = (Usuario) obj;
        return this.id == other.id || (this.id != null && this.id.equals(other.id));
    }
}
