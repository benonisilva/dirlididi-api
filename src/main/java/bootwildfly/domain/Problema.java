package bootwildfly.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Objects;

public class Problema {
 
  private Integer id;
  private String dica;
  private List<Teste> teste;
  private SumarioDeProblema sumario;

    public SumarioDeProblema getSumario() {
        return sumario;
    }

    public Problema() {
       
    }

    public void setSumario(SumarioDeProblema sumario) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Problema other = (Problema) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
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