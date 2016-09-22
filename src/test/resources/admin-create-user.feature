#language: pt
Funcionalidade: Admin publica um problema

  @admin
  Cenario: Admin acessa o sistema e publica um problema
    Dado que o admin entra com login 'admin@admin.com' e password '123456'
    Quando o usuario envia POST com dados para url "/auth"
    Então o recebe o token de autorização