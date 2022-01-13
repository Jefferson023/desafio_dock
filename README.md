# Desafio da Dock
Desafio realizado para a vaga de Software Engineer na empresa Dock

## Pré-requisitos
* Java 11 or mais recente (JDK).
* git (https://git-scm.com/)

## Rodando o projeto localmente
Este pequeno projeto foi desenvolvido utilizando Java 11, [Maven](https://spring.io/guides/gs/maven/) e o framework [Spring Boot](https://spring.io/guides/gs/spring-boot). 
Você pode executar esse projeto utilizando uma IDE como o EclipseIDE ou Intellij desde que estejam devidamente configurados para o Java 11 e [Lombok](https://projectlombok.org/). O projeto também pode ser executado via linha de comando seguindo os comandos mostrados abaixo. 

```
git clone https://github.com/Jefferson023/desafio_dock.git
cd desafio_dock
java -jar dock/target/dock-0.0.1-SNAPSHOT.jar
```
## Heroku
Uma versão da aplicação também se encontra funcionando na seguinte URL https://desafio-dock1.herokuapp.com/ 
Também é valido considerar que a cada alguns minutos o Heroku coloca as aplicações para dormir, o que pode atrasar algumas requisições ou apagar os dados do banco da memória. 

## Requisitos do desafio

a. Dado um post com o seguinte body para o seu endpoint:
```
  header Content-Type: text/html; charset=utf-8
  body 44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN
```

b. Desmembrar a entrada, armazenar em uma entidade e retornar um json com a seguinte
  estrutura:
```
  {
    "logic": 44332211,
    "serial": "123",
    "model": "PWWIN",
    "sam": 0,
    "ptid": "F04A2E4088B",
    "plat": 4,
    "version": "8.00b3",
    "mxr": 0,
    "mxf":16777216,
    "PVERFM":”PWWIN"
  }
```
c. Para que a entrada seja considerada válida, a mesma deve seguir o seguinte schema:
```
  {
    "title": "Terminal",
    "type": "object",
    "properties": {
      "logic": {
        "type": "integer"
      },
      "serial": {
        "type": "string"
      },
      "sam": {
        “type”: “integer”,
        “minimum”: 0
      },
      "ptid": {
      },
      "plat": {
        "type": "integer"
      },
      "version": {
        "type": "string"
      },
      "mxr": {
        "type": "integer"
      },
      "PVERFM": {
        "type": "string"
      }
    },
  "required": ["logic", "serial", "model", "version"]
}
```
d. O Endpoint criado deve aceitar consultas, porém não deve aceitar o verbo DELETE.

e. O Endpoint não deve aceitar o verbo POST em JSON.

f. O Endpoint deve aceitar consultas de um registro seguindo o seguinte padrão, [versão da aplicação]/[nome da entidade]/[atributo 'logic']

g. As alterações aos objetos armazenados devem ser feitas via JSON usando o padrão de url [versão da aplicação]/[nome da entidade]/[atributo 'logic']
