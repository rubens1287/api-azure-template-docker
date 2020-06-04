# language: pt
# charset: UTF-8

  @PlanId=1
  @SuiteId=4
Funcionalidade: Api Users
   Eu como cliente gostaria de consultar o os dados de um usuário

  #uri: /users/
  @dev
  @TestId=8
   Cenario: Consultar dados de um usuário
    Quando eu consultar um usuario
    Entao sera apresentado todos os dados deste usuario
