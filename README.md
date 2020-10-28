# README

Prefira ler o README.md em um leitor online como https://dillinger.io/ ou no git do bitbucket https://bitbucket.org/cred_system/credsystem-loja-pre-pagos/src/develop/README.md

FOLHA DE PONTO COM SPRING BOOT

 ● Apenas 4 horários podem ser registrados por dia.
 ● Deve haver no mínimo 1 hora de almoço.
 ● Sábado e domingo não são permitidos como dia de trabalho.
 Alocar horas trabalhadas, de um dia de trabalho, em um projeto.
 ● Não pode alocar um tempo maior que o tempo trabalhado no dia.
 ● A soma do tempo de todas as alocações, referentes à um dia, não pode ser maior
que o tempo trabalhado no dia.

"Não tinha reparado na regra de 8 horas de trabalho, a qual foi descoberta hoje por isso não foi implementado".


# ARQUITETURA
A aplicação foi desenvolvida com framework SpringBoot e estrutura de pacotes hexagonal não modular

* SPRING BOOT   
    - Spring-boot-2.1.5.RELEASE
    - Spring-boot-starter-actuator
    - Spring-boot-starter-data-jpa
    - Spring-boot-starter-web
    
# INSTALÇÃO E RUNNING

Para fazer a instalação, devera usar Maven como ferramenta de build e java 11 ou superior para a compilação.
Para fazer o deploy rode o clean install do maven e o java -jar de cada projeto
```sh
$ mvn clean instal 
$ java -jar cloud-eureka-server/target/cloud-eureka-server-0.0.1-SNAPSHOT.jar
$ java -jar cloud-auth/target/auth-0.0.1-SNAPSHOT.jar
$ java -jar ponto-batidas/target/ponto-batidas-0.0.1-SNAPSHOT.jar
$ java -jar ponto-alocacoes/target/ponto-alocacoes-0.0.1-SNAPSHOT.jar
$ java -jar ponto-relatorio/target/ponto-relatorio-0.0.1-SNAPSHOT.jar
```

# OBSERVAÇÕES

As aplicações irão subir em portas diferentes era para usar Zuul como apigateway e centralisar as chamada, mas como não foi possivel temos o seguinte cenario.
BATIDAS = http://localhost:8080/batidas/
ALOCACOES = http://localhost:8081/alocacoes/
FOLHAS DE PONTO = http://localhost:8082/folha-de-ponto/

# BANCO DE DADOS

Mongodb embutido em memoria, ou seja não precisa se preocupar com o mesmo.



[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)