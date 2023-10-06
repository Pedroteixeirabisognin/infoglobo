# Introdução

Esta é uma api restful ao qual gerencia dados sobre noticias como data 
de publicação, titulo, conteudo. Utilizando Spring Security para autenticação. 

# Instalação

Link para instalar o WSL - https://learn.microsoft.com/pt-br/windows/wsl/install

Habilitar recurso de máquina virtual (para o docker) - https://learn.microsoft.com/pt-br/windows/wsl/install-manual#step-3---enable-virtual-machine-feature

Na pasta raiz do projeto 

Efetuar os seguintes comandos:



``` bash

docker-compose up --build

```

Esse ultimo comando executará o mongodb com a api pronta para ser consumida em localhost:8080, se quiser acessar ao mongodb também poderá a partir de localhost:27017

Até o momento a api só consegue ser testada localmente, para isso você deve fazer os seguinte:

Mudar em aplication-test.properties e application.properties o host para local

``` bash

docker-compose up -d mongo

mvnw clean install spring-boot:repackage


```

No fim da instalação você verá que foram efetuados 26 testes sem erros. 

# Overview

O presente projeto utiliza das seguintes tecnologias:

### Spring Boot 

Pois facilita a criação de aplicativos autônomos baseados em Spring de nível de produção que você pode "simplesmente executar".

### Spring Security 

Sendo uma estrutura de autenticação e controle de acesso poderosa e altamente personalizável. É o padrão de fato para proteger aplicativos baseados em Spring.

### Spring Data 

Para mapeamento e consulta de dados ao Mongo DB.

### Spring Validation 

Para fazer a validação do corpo das requisições. 

### Spring Test 

Conjunto de ferramentas para testes personalizadas para o Spring como Mock, Mockito e etc. Está sendo utilizado para fazer testes de integração e unitários. 

### Spring Cache 

Para controle de cache da aplicação.

### Mongo DB 

Banco de dados não relacional utilizado pela aplicação.

### Docker 

Para conteinerização do projeto.

### Docker Compose 

Para relacionamento entre os containers do projeto. 

# Autenticação

O tipo de autenticação é Basic

Usuário: "infoglobo"

Senha: "12345"

# Codigos de erro

500 - INTERNAL SERVER ERROR - Ocorre quando o servidor tem algum problema genérico como por exemplo a api não ter conexão com a database.

404 - NOT FOUND - Ocorre quando a noticia específica não é encontrada na base de dados.

201 - CREATED - Quando é adicionado uma nova noticia via método post.

200 - OK - Quando alguma solicitação específica é efetuada com sucesso como um get.

204 - NO CONTENT - Ocorre quando é efetuado um delete e os dados da notícia são excluidos da database.  

400 - BAD REQUEST - Ocorre quando o corpo da requisição está errado como por exemplo sendo enviado vazio no título em um método post ou put. 

405 - Method Not Allowed - Normalmente ocorre quando um método é executado de forma incorreta como por exemplo um put sem a id na url.
