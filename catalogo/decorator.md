# Decorator

**Categoria:** Estrutural

## Nome

Decorator — adiciona responsabilidades opcionais envolvendo um objeto compatível.

## Problema

Funcionalidades combináveis provocam uma explosão de subclasses ou não devem ser fixadas em uma hierarquia.

## Solução

Cada decorador implementa a mesma interface e delega ao componente envolvido, acrescentando comportamento.

**Exemplo adicional — Java.**

```java
final class Milk implements Coffee {
    private final Coffee coffee;
    public BigDecimal price() { return coffee.price().add(new BigDecimal("1.50")); }
}
```

Exemplo compilável: `GoFExamples.Milk`; teste: `decoratorAddsOptionalBehaviorWithoutChangingTheBase`.

## Consequências

Combina responsabilidades em tempo de execução; cadeias longas podem ser difíceis de inspecionar e depurar.

**Smell:** subclasses são criadas para cada combinação de funcionalidades opcionais.
**SOLID:** composição pode favorecer SRP; não há correspondência exclusiva.
**Quando não usar:** variações são poucas, estáveis e mais claras como implementação direta.
