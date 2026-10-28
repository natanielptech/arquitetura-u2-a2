# Factory Method

**Categoria:** Criacional

## Nome

Factory Method — delega a subclasses a escolha do produto concreto.

## Problema

Um cliente precisa criar produtos, mas não deve acoplar-se à classe concreta nem concentrar condicionais de criação.

## Solução

O criador declara o método de fábrica e usa seu produto em uma operação. Criadores concretos escolhem produtos compatíveis.

**Exemplo adicional — Java.**

```java
abstract class CanalCreator {
    protected abstract Canal createChannel();
    String notify(String message) { return createChannel().send(message); }
}
```

Exemplo compilável: `GoFExamples.EmailCreator`; teste: `factoryMethodCreatesTheSelectedProduct`.

## Consequências

Facilita acrescentar produtos sem alterar o cliente, mas introduz hierarquias de criadores e produtos.

**Smell:** condicionais extensas escolhem qual classe concreta instanciar.
**SOLID:** apoia OCP.
**Quando não usar:** uma criação direta e estável é suficiente.
