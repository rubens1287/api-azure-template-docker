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

| Diretório                    	| finalidade       	                                                                                        | 
|------------------------------	|---------------------------------------------------------------------------------------------------------- |
| src\main\java\core 			| Metodos genéricos que apoiam as classes de testes      	                                                |
| src\main\java\model    		| Metodos do tipo models, para trabalhar com objetos de dados                                               |
| src\main\java\request 		| Metodos do tipo models, que representa objetos a serem serealizado em json para as requisições de apis   	|
| src\main\java\respose 		| Metodos do tipo models, que representa objetos que recebem a deserializaçao do json de reposta das apis  	|
| src\main\java\services		| Local onde deve ser criado os objetos que executam requisições e validações das respotas               	|
| src\test\java\hooks          	| Metodos que executam antes e depois de cada teste (@Before, @After)                                   	|
| src\test\java\runner         	| Metodo prinicipal que inicia os testes via cucumber                                                      	|
| src\test\java\steps         	| Local onde deve ser criado as classes que representam os steps definition do cucumber                    	|
| src\test\resources\model      	| Massa de dados segregada por ambiente, escritos em arquivos yaml                                      	|
| src\test\resources\features 	| Funcionalidade e cenarios de teste escritos em linguagem DSL (Gherkin language)                        	| 
| src\test\resources\schema 	| Local para armazenamento dos arquivos de schema do json utilizados para validação de contrato           	| 

## DOWNLOAD DO PROJETO TEMPLATE PARA SUA MÁQUINA LOCAL

Faça o donwload do template no repositório de código para utilizar no seu projeto em especifico, 
feito isso, fique a vontande para usufruir dos recursos disponíveis e 
também customizar de acordo com sua necessidade. 


## FRAMEWORKS UTILIZADOS

Abaixo está a lista de frameworks utilizados nesse projeto

* Jackson - Responsável pela leitura de dados de arquivo yaml file
* Gson - Responsável pela serializacao e deserializacao de objetos
* Allure - Responsável pelo report em HTML
* Java Faker - Responsável pela geracao de dados sintéticos
* Rest Assured - Responsável pelos interação com a camada HTTP para teste de API (Json, Soap, Xml)
* Cucumber - Responsável pela especificação executável dos cenários
* AssertJ - Especializado em validações com mais tipos e formatos de verificação
* Lombok - Otimizacao de classes modelos
* Log4j - Responsável pelo Log do projeto

## COMANDO PARA EXECUTAR OS TESTES

Com o prompt de comando acesse a pasta do projeto, onde esta localizado o arquivo pom.xml, execute o comando abaixo para rodar os testes automatizados.

```
mvn clean test
```

## COMANDO PARA GERAR EVIDÊNCIAS EM HTML (ALLURE)

Com o prompt de comando acesse a pasta do projeto, onde esta localizado o arquivo pom.xml, execute o comando abaixo para gerar as evidências em HTML

```
mvn allure:report
```

## MULTIPLOS COMANDOS 

Você também pode mesclar a linha de comando maven com options do cucumber, 
sendo assim você pode escolher uma determinada tag que se deseja executar do cucumber, 
podendo escolher também a massa de dados que irá utilizar e juntamente aplicar o linha de comando para gerar o report HTML.

```
mvn clean test -Dcucumber.options="--tags @dev" -Denv=des allure:report
```

## TESTES CONTINUOS

### JENKINS

Executar testes de forma continua vem se tornado fundamental para agregar valor junto a seu time,
para isto foi configurado o pipeline para ser aplicado ao jenkins chamando "Jenkinsfile"
localizado na raiz do projeto

### PRE-REQUISITOS

Configurações necessárias para rodar o pipeline no Jenkins

*   [Allure configurado no Jenkins](https://docs.qameta.io/allure/#_jenkins)
*   [Docker instalado na máquina agente](https://www.docker.com/products/docker-desktop)
*   Plugins
    * [Allure Jenkins Plugin](https://plugins.jenkins.io/allure-jenkins-plugin)
   
### ETAPAS

* Java e Maven no contexto do jenkins
* Execução dos testes
* Geração do Report com Allure
* Upload dos arquivos junit.xml e exec_logs.log

## EVIDÊNCIAS

Os arquivos com as evidências ficam localizados na pasta target do projeto, esta pasta só é criada depois da primeira execução.

```
 Report HTML: target\site\index.html
 Json Cucumber: target\json-cucumber-reports\cucumber.json
 Xml Junit: tagert\xml-junit\junit.xml
```
