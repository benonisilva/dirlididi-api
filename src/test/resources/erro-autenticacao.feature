#language: pt
Funcionalidade: Erro de autenticação

  @anonimo
  Cenario: Usuario tenta receber permissão mas com credenciais invalidas
    Dado que o usuario entra com login e password "user@user.com,12345"
    Quando o usuario envia POST com dados para url "/auth"
    Mas as credenciais são invalidas
