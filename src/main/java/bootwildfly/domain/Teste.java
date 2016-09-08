package bootwildfly.domain;

public class Teste {
 
  private Integer id;
  private String nome;
  private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public TipoDeTeste getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeTeste tipo) {
        this.tipo = tipo;
    }
  private String dica;
  private String codigo;
  private String entrada;
  private String saida;

    public Teste() {
       
    }
  private TipoDeTeste tipo;

    public Teste(String nome, String descricao, String dica, String codigo, String entrada, String saida, TipoDeTeste tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.dica = dica;
        this.codigo = codigo;
        this.entrada = entrada;
        this.saida = saida;
        this.tipo = tipo;
    }

 
}