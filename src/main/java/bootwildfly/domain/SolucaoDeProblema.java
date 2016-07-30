/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 *
 * @author benoni
 */
@ApiModel(value="SolucaoDeProblema", description = "Solucao para problema" ,discriminator = "respostas", subTypes = {Resposta.class})
public class SolucaoDeProblema {

        
    private  String estrategia;
    private  List<Resposta> respostas;
    
    public SolucaoDeProblema() {
        
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Descricao de estrategias usada para resolução do problema", required = true)
    public String getEstrategia() {
        return estrategia;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Respostas(entrada, saida esperada) dos testes", required = true)
    public List<Resposta> getResposta() {
        return respostas;
    }

    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }
}
