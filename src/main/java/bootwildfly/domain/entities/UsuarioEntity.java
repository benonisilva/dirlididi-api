/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.domain.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author benoni
 */
@Entity
public class UsuarioEntity {
    
    //privates
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    @Column(nullable = false)
    private String senha;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String role;
    
    //Quais problemas foram criados pelo Usuario
//    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//    private List<ProblemaEntity> problemasCriados;
//    
//    @ManyToMany(cascade = CascadeType.DETACH)
//    @JoinTable(name="usuario_soluc_problema", 
//               joinColumns=  @JoinColumn( name = "usuario_id"), 
//               inverseJoinColumns= @JoinColumn(name = "problema_id") )
//    private Set<ProblemaEntity> problemasResolvidos = new HashSet<>();
//
//    public List<ProblemaEntity> getProblemasCriados() {
//        return problemasCriados;
//    }
//
//    public void setProblemasCriados(List<ProblemaEntity> problemasCriados) {
//        this.problemasCriados = problemasCriados;
//    }

//    public Set<ProblemaEntity> getProblemasResolvidos() {
//        return problemasResolvidos;
//    }
//
//    public void setProblemasResolvidos(Set<ProblemaEntity> problemasResolvidos) {
//        this.problemasResolvidos = problemasResolvidos;
//    }

    //end 
    
    public UsuarioEntity(){}
}
