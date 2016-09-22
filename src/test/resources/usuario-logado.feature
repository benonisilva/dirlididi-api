#language: pt
Funcionalidade: Autenticação e Autorização

  @usuario
  Cenario: Usuario se identifica e obtem acesso ao recurso
    Dado que o usuario entra com login e password "user@user.com,123456"
    Quando o usuario envia POST com dados para url "/auth"
    Então o recebe o token de autorização
    E o usuario logado edita o problema de 'cod1' com nome 'novonome'
    Quando Envio requisição GET para "/problema/cod1"
    E o prolema deve ter nome igual a "novonome"