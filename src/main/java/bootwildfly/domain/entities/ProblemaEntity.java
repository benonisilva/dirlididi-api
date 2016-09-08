/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.domain.entities;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author benoni
 */
@Entity
public class ProblemaEntity {
    
    //privates
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    public ProblemaEntity(String codigo, String nome, String descricao, Date dataCriacao, boolean isPublicado, String dica, UsuarioEntity usuarioCriador) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.isPublicado = isPublicado;
        this.dica = dica;
        this.usuarioCriador = usuarioCriador;
    }

    public Long getId() {
        return id;
    }
    
    @Column(nullable = false)
    private String codigo;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String descricao;
    
    @Column(nullable = false)
    private Date dataCriacao;
    
    @Column(nullable = false)
    private boolean isPublicado;
    
    private String dica;
//    private boolean isResolvido;
    
    public boolean isIsPublicado() {
        return isPublicado;
    }

    public void setIsPublicado(boolean isPublicado) {
        this.isPublicado = isPublicado;
    }
    
    //end privates
    
    //Identifica o Usuario que criou o problema
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuarioCriador;

    public UsuarioEntity getUsuarioCriador() {
        return usuarioCriador;
    }

    public void setUsuarioCriador(UsuarioEntity usuarioCriador) {
        this.usuarioCriador = usuarioCriador;
    }
    
//    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//    private List<TesteEntity> listaTestes;
    
    protected ProblemaEntity(){}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

//    public boolean isResolvido() {
//        return isResolvido;
//    }
//
//    public void setIsResolvido(boolean isResolvido) {
//        this.isResolvido = isResolvido;
//    }

    public void setId(Long id) {
       this.id = id;
    }

}
