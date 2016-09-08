/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 *
 * @author benoni
 */
public class SumarioDeProblema {
 
  private String dataCriacao;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SumarioDeProblema other = (SumarioDeProblema) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setResolvido(boolean resolvido) {
        this.resolvido = resolvido;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public SumarioDeProblema(String dataCriacao, String nome, String codigo, String descricao) {
        this.dataCriacao = dataCriacao;
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
    }
  private boolean resolvido;
  private String nome;
  private String codigo;
  private String descricao;

    public SumarioDeProblema() {
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
