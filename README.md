# PROJETO MODELO PARA TESTE API

Projeto desenvolvido com proposito de ser um modelo base para teste de API

## PRÉ-REQUISITOS

Requisitos de software e hardware necessários para executar este projeto de automação

*   Java 1.8 SDK
*   Maven 3.5.*
*   Intellij IDE
*   Plugins do Intellij
    * Cumcuber for java
    * Lombok
    * Ideolog 

## ESTRUTURA DO PROJETO

| Diretório                    	| finalidade       	                                                                                                | 
|------------------------------	|------------------------------------------------------------------------------------------------------------------ |
| src\main\java\core    		| Responsável por gerenciar as configurações padões da spec do RestAssured e também do padrão criado pela interface |
| src\main\java\core\azure 		| Responsável por enviar os resultados para o test management do Azure Devops                                       |
| src\main\java\core\config 	| Interface com as propriedades dos arquivos de ambiente 'Properties'                                               |
| src\main\java\core\data       | Reponsável por ler arquivos yaml file e retonar objeto HashMap com os valores dos campos                          |
| src\main\java\model    		| Metodos do tipo models, para trabalhar com objetos de dados                                                       |
| src\main\java\request 		| Metodos do tipo models, que representa objetos a serem serealizado em json para as requisições de apis   	        |
| src\main\java\respose 		| Metodos do tipo models, que representa objetos que recebem a deserializaçao do json de reposta das apis  	        |
| src\main\java\services		| Local onde deve ser criado os objetos que executam requisições e validações das respotas               	        |
| src\main\java\support 		| Metodos genéricos que apoiam as classes de testes                                                        	        |
| src\main\resources\conf	    | Arquivos de configuração segregados por ambiente                                                                  |
| src\test\java\hooks          	| Metodos que executam antes e depois de cada teste (@Before, @After)                                   	        |
| src\test\java\runner         	| Metodo prinicipal que inicia os testes via cucumber                                                      	        |
| src\test\java\steps         	| Local onde deve ser criado as classes que representam os steps definition do cucumber                    	        |
| src\test\resources\model      | Massa de dados segregada por ambiente, escritos em arquivos yaml                                                  |
| src\test\resources\features 	| Funcionalidade e cenarios de teste escritos em linguagem DSL (Gherkin language)                        	        |       
| src\test\resources\schema 	| Local para armazenamento dos arquivos de schema do json utilizados para validação de contrato           	        | 

## DOWNLOAD DO PROJETO TEMPLATE PARA SUA MÁQUINA LOCAL

Faça o donwload do template no repositório de código para utilizar no seu projeto em especifico, 
feito isso, fique a vontande para usufruir dos recursos disponíveis e 
também customizar de acordo com sua necessidade. 


## FRAMEWORKS UTILIZADOS

Abaixo está a lista de frameworks utilizados nesse projeto

| Framework                    	| Finalidade       	                                                                                        | 
|------------------------------	|---------------------------------------------------------------------------------------------------------- |
| Jackson                  		| Responsável pela leitura de dados de arquivo yaml file                                                    |
| Gson                     	    | Responsável pela serializacao e deserializacao de objetos                                                 |
| RestAssured               	| RResponsável pelos interação com a camada HTTP para teste de API (Json, Soap, Xml)						|
| Java Faker             		| Geracão de dados sintéticos                                                                             	|
| Cucumber                  	| Responsável pela especificação executável de cenários                                                   	|
| Cucumber Junit             	| Responsável pela especificação executável de cenários                                                     |
| Lombok                     	| Otimização de classes modelos                                                                         	|
| AssertJ						| Especializado em validações com mais tipos e formatos de verificação										|
| Log4j2                     	| Responsável pelo Log do projeto                                                                          	|
| AeonBits          			| Responsável por gerenciar as properties                                                                  	|
| Mssql-jdbc                    | Conector de banco de dados SQL Server                                                                     |
| Jooq                          | Criar querys SQL seguras do tipo por meio de sua API fluente                                              |

## INTEGRAÇÃO COM AZURE DEVOPS

A integração com test managment do azure, e feito atravês do arquivo de properties <b>"src\main\resources\azure.properties"</b> 
onde você deve informa os parametros abaixo;

```properties
# host do azure
host.azure = <Host do Servido>

# Nome do Projeto
project = <Nome do Projeto>

```

Você deve informar o seu personal access token no arquivo pom.xml, caso você não queira integrar as ferramentas basta deixar esse parametro sem valor.

```xml
<token>personal-access-token</token>
```

Para concluir a configuração, você deve aplicar as tags reservadas no arquivo de features do cucumber;

```gherkin
@Plan_Id=<Id do plano de teste no azure>
@Qa_Suite_Id=<Id do suite de teste no azure para regressão da suite de teste de qa>
@Hom_Suite_Id=<Id do suite de teste no azure para regressão da suite de teste de homologação>
@Des_Suite_Id=<Id do suite de teste no azure para regressão da suite de teste de desenvolvimento>
@Test_Id=<Id do caso de teste no azure>
```

Exemplo:

```gherkin
# language: pt
# charset: UTF-8

@Plan_Id=69226
@Des_Suite_Id=69235
@Qa_Suite_Id=69233
@Hom_Suite_Id=69234
Funcionalidade: Api Users
   Eu como cliente gostaria de consultar o os dados de um usuário

  #uri: /users/
  @Test_Id=69232
   Cenario: Consultar dados de um usuário
    Quando eu consultar um usuario
    Entao sera apresentado todos os dados deste usuario
```

Por fim, você deve chamar o objeto responsável para enviar os resultados para o Test Plan do Azure

```java
    RunTestController runTestController = new RunTestController();
    runTestController.runTestCase(scenario);
```

Exemplo: 
```java
 @After
    public void end(Scenario scenario){
        DriverManager.quit(scenario);
        RunTestController runTestController = new RunTestController();
        runTestController.runTestCase(scenario);
        log.info(String.format("TESTE FINALIZADO: %s",scenario.getName()));
        log.info(String.format("TESTE STATUS: %s",scenario.getStatus()));
    }
```

## TIPOS DE TESTE

Os tipos de teste são utilizados para serem classificados e executados de acordo com o ambiente, então podemos seguir a classificação abaixo;

| Tipo                     |Tag        | Finalidade       	                                                                                        | 
|------------------------- |---------- |----------------------------------------------------------------------------------------------------------  |
| Smoke Testing            |@smoke     | Responsável por garantir que as funcionalidade principais do sistema estão minimamente funcionado          |
| Teste Funcional          |@funcional | Responsável por garantir que todos os critérios de aceite do sistema estão em conformidade com os requsitos|
| Teste de Aceitação       |@aceitacao | Responsável por garantir que os fluxos funcionais da aplicação estão funcionando                           |

Veja abaixo a tabela DE-PARA de tipo de teste / tag versus ambiente.

| Tipo                     |Tag        | Ambiente       	                                                                                        | 
|------------------------- |---------- |------------------------------------------- |
| Smoke Testing            |@smoke     | Develop, QA ou PreProd                     |
| Teste Funcional          |@funcional | QA                                         |
| Teste de Aceitação       |@aceitacao | PreProd                                    |

## COMANDOS PARA EXECUTAR OS TESTES

Parametros pré definidos

| Parametro                |Valor                                            |  
|------------------------- |------------------------------------------------ |
| -Denv                    |des, hom, qa                                     |
| -Dtoken                  |Personal access token azure devops               |
| -Dcucumber.options       |tags, plugins, glues, features                   |

Com o prompt de comando acesse a pasta do projeto, onde esta localizado o arquivo pom.xml, execute o comando abaixo para rodar os testes automatizados.

```
mvn clean test
```


## MULTIPLOS COMANDOS PARA EXECUTAR OS TESTES 

Você também pode mesclar a linha de comando maven com options do cucumber, 
sendo assim você pode escolher uma determinada tag que se deseja executar do cucumber, 
podendo escolher também a massa de dados que irá utilizar por ambiente.

```
mvn clean test -Dcucumber.options="--tags @dev" -Denv=des
```

## RODANDO OS TESTE EM CONTAINER (DOCKER)

O projeto tem a capacidade de criar a infraestrutura de ambiente para executar os testes de forma autonoma, isso
significa que tudo que é necessário para a execução é criado dentro de containers.

Configure o arquivo <b>"docker-compose.yml"</b>

```yaml
# Usage:
#   docker-compose up --force-recreate
#   docker-compose stop
version: '3'

services:
  #--------------#
  teste:
      build: .
      working_dir: /usr
      volumes:
              - ./target:/usr/target
      container_name: <nome do projeto no artifactId do pom>_teste
      command: <Comando de execução do projeto, vide item (COMANDOS PARA EXECUTAR OS TESTES)>
```

## TESTES CONTINUOS

### AZURE DEVOPS

Executar testes de forma continua vem se tornado fundamental para agregar valor junto a seu time,
para isto foi configurado o pipeline para chamando "build-azure-pipeline.yml"
localizado na raiz do projeto

### PRE-REQUISITOS

Configurações necessárias para rodar o pipeline no Azure Devops

*   Agente com acesso as aplicações da companhia
   
### PIPELINE BUILD AND PUBLISH CONTAINER IMAGE

Neste seção eu mostro os detalhes de implementação do processo de build do container e publicação, atravês do arquivo yaml file,
o qual você encontra na raiz do projeto no arquivo <b>build-azure-pipeline.yml</b>.


* Checkout do código

```yaml
pool:
  vmImage: ubuntu-16.04

steps:
- task: DockerInstaller@0
  displayName: 'Install Docker 17.09.0-ce'

- task: Docker@2
  displayName: buildAndPush
  inputs:
    containerRegistry: '<Container-Registry>'
    repository: '<Container-Repository>'
    tags: |
     $(Build.BuildId)
     latest
```

### CONTINUOUS DELIVERY 

* Agent azure devops (Linux, Windows ou Mac)
* Install Docker 17.09.0-ce

* Task (Docker) Login docker private azure
    * containerRegistry: '<Container-Registry>'
    * repository: '<Container-Repository>'

* Task (Command line) docker run testing

Exemplo de linha de comando:
```dockerfile
    docker run  -v "$PWD/target:/usr/target" <image-container-name> mvn test -Denv=qa -Dtoken=<token>
```

* Task (Publish Test Results)
    * testResultsFiles: 'target/xml-junit/junit.xml'
    * failTaskOnFailedTests: true
    * testRunTitle: 'Testes Automatizados'
 
### PIPELINE CONTINUOUS DELIVERY 

```yaml
pool:
  vmImage: ubuntu-16.04

steps:
- task: DockerInstaller@0
  displayName: 'Install Docker 17.09.0-ce'

- task: Docker@2
  displayName: 'Login docker private azure'
  inputs:
    containerRegistry: 'service-containers'
    command: login

- script: 'docker run -v "$PWD/target:/usr/target" <Container-Repository>:latest mvn test -Dcucumber.options="--tags @smoke" -Denv=qa -Dtoken=<token>'
  displayName: 'docker run testing'
  
- task: PublishTestResults@2
  displayName: 'Publish Test Results'
  inputs:
    testResultsFiles: 'target/xml-junit/junit.xml'
    failTaskOnFailedTests: true
    testRunTitle: 'Testes Automatizados'
```

## EVIDÊNCIAS

As evidências são enviadas diretamente para o Azure Devops, garantido a centralização dos resultados de teste

Os arquivos com as evidências técnicas para a integração continua ficam localizados na pasta target do projeto, 
esta pasta só é criada depois da primeira execução.

```
 Json Cucumber: target/json-cucumber-reports/cucumber.json
 Xml Junit: tagert/xml-junit/junit.xml
```

## LOG DE EXECUÇÃO

Os logs de execução gerados pelo Log4j2 ficam disponíveis no console, seja executando localmete ou via uma ferramenta de CI.

## CARACTERISTICAS ESPECIAIS

* Download automatíco dos binários dos drivers para diferentes tipos de versões de navegadores e sistema operacional.
* Segregação de massa por ambiente via arquivos yaml file.
* Segregação das configurações de ambiente via arquivo properties file.