#language: pt
Funcionalidade: Listar Problemas

  @anonimo
  Cenario: Obter lista de problemas
    Quando Envio requisição GET para "/problema/sumario"
    Então o status deve ser 200
    E o tamanho da lista deve ser 5
    E o resultado deve conter os codigos "cod1,cod2,cod3,cod4,cod5"