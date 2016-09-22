#language: pt
Funcionalidade: Pegar um problema especifico usando codigo

  @anonimo
  Cenario: Usuario anonimo pega um Problema usando codigo
    Quando Envio requisição GET para "/problema/cod5"
    Então o status deve ser 200
    E o prolema deve ter nome igual a "nome5"
    E o problema deve ter descricao igual a "desc5"