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
public class SumarioDeProblema {
 
  private String dataCriacao;
  private boolean resolvido;
  private String nome;
  private String codigo;
  private String descricao;

    public SumarioDeProblema(String dataCriacao, boolean resolvido, String nome, String codigo, String descricao) {
        this.dataCriacao = dataCriacao;
        this.resolvido = resolvido;
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Data da criação do problema. Formato dd-MM-yy", required = true)
    public String getDataCriacao() {
        return dataCriacao;
    }
    
        @JsonProperty(required = true)
    @ApiModelProperty(notes = "Problema resolvido", required = true)
    public boolean isResolvido() {
        return resolvido;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Nome do problema", required = true)
    public String getNome() {
        return nome;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Codigo do problema", required = true)
    public String getCodigo() {
        return codigo;
    }
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Descricao do problema", required = true)
    public String getDescricao() {
        return descricao;
    }

  
}
