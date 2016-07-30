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
public class Resposta {
    private  String entrada;
    private  String saida;

    public Resposta() {
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Entrada de teste", required = true)
    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Saida de teste", required = true)
    public String getSaida() {
        return saida;
    }
}
