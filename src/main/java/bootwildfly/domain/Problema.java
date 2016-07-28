package bootwildfly.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class Problema {
 
  private Integer id;
  private String dica;
  private List<Teste> teste;
  private SumarioDeProblema sumario;

    public SumarioDeProblema getSumario() {
        return sumario;
    }

    public Problema(SumarioDeProblema sumario) {
        this.sumario = sumario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Dica do problema", required = true)
    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Testes do problema", required = true)
    public List<Teste> getTeste() {
        return teste;
    }

    public void setTeste(List<Teste> teste) {
        this.teste = teste;
    }  
 
}