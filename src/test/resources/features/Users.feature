# language: pt
# charset: UTF-8

@Plan_Id=1
@Des_Suite_Id=4
@Qa_Suite_Id=4
@Hom_Suite_Id=4
Funcionalidade: Api Users
   Eu como cliente gostaria de consultar o os dados de um usuário

  #uri: /users/
  @Test_Id=8
   Cenario: Consultar dados de um usuário
    Quando eu consultar um usuario
    Entao sera apresentado todos os dados deste usuario
