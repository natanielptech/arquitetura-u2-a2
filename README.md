# Padrões de Projeto: conceitos, motivação e aplicabilidade

Guia visual em PT-BR para reconhecer padrões, escolher com critério e evitar over-engineering. O catálogo cobre os 12 padrões listados na tabela da Unidade 2 · Aula 2, com exemplos Java, testes JUnit 5 e equivalentes Spring quando naturais.

**Curso:** Arquitetura de Sistemas e Microsserviços · **Unidade 2 · Aula 2 — Prof. Nataniel Paiva**

## Executar

Requisitos: Java 21 e Maven 3.8 ou superior.

```bash
mvn spring-boot:run
```

Acesse `http://localhost:8080`. Para executar os testes:

```bash
mvn test
```

O projeto usa Java 21, Spring Boot 3.4, Maven e JUnit 5.

## Navegação

- [Origem: Alexander e GoF](docs/01-origem-alexander-gof.md)
- [Estrutura formal de um padrão](docs/02-estrutura-de-um-padrao.md)
- [Por que usar e como evitar over-engineering](docs/03-por-que-usar-e-over-engineering.md)
- [Classificação GoF: 12 padrões](docs/04-classificacao-gof.md)
- [Critérios de aplicabilidade](docs/05-criterios-de-aplicabilidade.md)
- [Padrões e SOLID](docs/06-padroes-e-solid.md)
- [Catálogo de padrões](catalogo/)
- [Diagramas Mermaid](diagramas/)
- [Exercícios](exercicios/)
- [Glossário](glossario.md)

## Exemplos e testes

As implementações didáticas ficam em [`GoFExamples.java`](src/main/java/br/com/natanielpaiva/padroes/exemplos/GoFExamples.java), com um teste de comportamento por padrão em [`GoFExamplesTest.java`](src/test/java/br/com/natanielpaiva/padroes/GoFExamplesTest.java). As integrações adicionais de Strategy e Observer com Spring e seus testes estão em [`SpringPatternExamples.java`](src/main/java/br/com/natanielpaiva/padroes/exemplos/SpringPatternExamples.java) e [`SpringPatternExamplesTest.java`](src/test/java/br/com/natanielpaiva/padroes/SpringPatternExamplesTest.java).

**Nota sobre o material:** um slide menciona “oito padrões representativos”, enquanto a tabela e a Aula 3 listam doze. Este projeto segue a tabela: Singleton, Factory Method, Abstract Factory, Builder, Adapter, Decorator, Facade, Composite, Strategy, Observer, Template Method e Command.
