package bootwildfly.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class Usuario {
 
    private Integer id;
    private String email;
    private String senha;
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "senha Usuario", required = true)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

  private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "email Usuario", required = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
      return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
 
}