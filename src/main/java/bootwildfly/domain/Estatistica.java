/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author benoni
 */
public class Estatistica {
   
   private Integer numeroProblemasResolvidos;

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Numero de problemas resolvidos", required = true)
    public Integer getNumeroProblemasResolvidos() {
        return numeroProblemasResolvidos;
    }

    public Estatistica(Integer numeroProblemasResolvidos, Integer numeroProblemasSubmetidos, Integer numeroResolucoesUsuario) {
        this.numeroProblemasResolvidos = numeroProblemasResolvidos;
        this.numeroProblemasSubmetidos = numeroProblemasSubmetidos;
        this.numeroResolucoesUsuario = numeroResolucoesUsuario;
    }
    
    
    public void setNumeroProblemasResolvidos(Integer numeroProblemasResolvidos) {
        this.numeroProblemasResolvidos = numeroProblemasResolvidos;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Total de usuario que submeteram problemas", required = true)
    public Integer getNumeroProblemasSubmetidos() {
        return numeroProblemasSubmetidos;
    }

    public void setNumeroProblemasSubmetidos(Integer numeroProblemasSubmetidos) {
        this.numeroProblemasSubmetidos = numeroProblemasSubmetidos;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Numero de resolucoes do usuario logado", required = true)
    public Integer getNumeroResolucoesUsuario() {
        return numeroResolucoesUsuario;
    }

    public void setNumeroResolucoesUsuario(Integer numeroResolucoesUsuario) {
        this.numeroResolucoesUsuario = numeroResolucoesUsuario;
    }
   private Integer numeroProblemasSubmetidos;
   private Integer numeroResolucoesUsuario;
    
}
