# Catálogo dos 12 padrões

Cada ficha usa os quatro elementos formais — nome, problema, solução e consequências — e aponta um sinal de código, relação com SOLID, exemplo adicional em Java e teste.

| Criacionais | Estruturais | Comportamentais |
|---|---|---|
| [Singleton](singleton.md) | [Adapter](adapter.md) | [Strategy](strategy.md) |
| [Factory Method](factory-method.md) | [Decorator](decorator.md) | [Observer](observer.md) |
| [Abstract Factory](abstract-factory.md) | [Facade](facade.md) | [Template Method](template-method.md) |
| [Builder](builder.md) | [Composite](composite.md) | [Command](command.md) |

Todos os exemplos compiláveis e os testes estão em [`GoFExamples.java`](../src/main/java/br/com/natanielpaiva/padroes/exemplos/GoFExamples.java) e [`GoFExamplesTest.java`](../src/test/java/br/com/natanielpaiva/padroes/GoFExamplesTest.java). As fichas anotam o método de teste correspondente. Integrações adicionais com Spring estão em [`SpringPatternExamples.java`](../src/main/java/br/com/natanielpaiva/padroes/exemplos/SpringPatternExamples.java).
