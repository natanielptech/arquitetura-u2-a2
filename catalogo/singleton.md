# Singleton

**Categoria:** Criacional

## Nome

Singleton — restringe a criação a uma instância acessível por um ponto definido.

## Problema

Um recurso precisa ter uma única instância por requisito real, como uma configuração imutável compartilhada.

## Solução

Uma implementação Java concisa e segura para instâncias sem estado é um `enum` com um único membro:

**Exemplo adicional — Java.**

```java
enum Registro { INSTANCIA }
```

O projeto demonstra `GoFExamples.Singleton`; teste: `singletonUsesOneInstance`.

## Consequências

Centraliza o acesso, mas pode introduzir estado global, dependências ocultas e testes difíceis. Em aplicações Spring, o escopo singleton do container e a injeção explícita costumam ser preferíveis ao controle manual.

**Smell:** vários objetos independentes são criados para um recurso cuja unicidade é um requisito.
**SOLID:** nenhum vínculo direto; evite confundir escopo singleton com justificativa para estado global.
**Quando não usar:** quando o objeto é simples, não precisa ser único ou pode ser fornecido por injeção de dependência.
