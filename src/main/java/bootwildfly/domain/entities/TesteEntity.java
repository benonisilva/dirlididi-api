/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.domain.entities;

import javax.persistence.*;
import javax.persistence.Id;

/**
 *
 * @author benoni
 */
@Entity
public class TesteEntity {

    public TesteEntity(String entrada, String saida, String dica, String tipo) {
        this.entrada = entrada;
        this.saida = saida;
        this.dica = dica;
        this.tipo = tipo;
    }
    
    protected TesteEntity() {}
     
    //privates
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(nullable = false)
    private String entrada;
    
    @Column(nullable = false)
    private String saida;
    
    @Column(nullable = false)
    private String dica;
    
    @Column(nullable = false)
    private String tipo;
    
    @ManyToOne
    @JoinColumn(name = "problema_id",insertable = true,updatable = true)
    private ProblemaEntity problema;
    //end privates

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ProblemaEntity getProblema() {
        return problema;
    }

    public void setProblema(ProblemaEntity problema) {
        this.problema = problema;
    }
}
