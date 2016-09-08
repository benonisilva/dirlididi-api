package bootwildfly.domain.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author benoni
 */
@Entity
@Table(name="SOLUCAO")
public class SolucaoEntity {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProblemaEntity getProblemaSolucionado() {
        return problemaSolucionado;
    }

    public void setProblemaSolucionado(ProblemaEntity problemaSolucionado) {
        this.problemaSolucionado = problemaSolucionado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    private ProblemaEntity problemaSolucionado;
    
    @OneToOne(cascade = CascadeType.ALL)
    private UsuarioEntity usuarioSolucionador;

    public UsuarioEntity getUsuarioSolucionador() {
        return usuarioSolucionador;
    }

    public void setUsuarioSolucionador(UsuarioEntity usuarioSolucionador) {
        this.usuarioSolucionador = usuarioSolucionador;
    }
    
    @Column(nullable = false)
    private String descricao;
}
