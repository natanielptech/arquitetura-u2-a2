# Abstract Factory

**Categoria:** Criacional

## Nome

Abstract Factory — cria famílias de objetos relacionados sem revelar suas classes concretas.

## Problema

Variantes de uma aplicação precisam trocar componentes relacionados de forma consistente.

## Solução

Uma interface declara os métodos de criação da família; cada fábrica concreta produz produtos compatíveis.

**Exemplo adicional — Java.**

```java
interface UiFactory {
    Button createButton();
    TextField createTextField();
}
```

Exemplo compilável: `GoFExamples.LightUiFactory`; teste: `abstractFactoryCreatesAConsistentFamily`.

## Consequências

Evita combinações incompatíveis e facilita trocar famílias. Adicionar uma nova espécie de produto exige atualizar as fábricas e suas abstrações.

**Smell:** clientes instanciam componentes relacionados em vários lugares e podem misturar variantes.
**SOLID:** pode apoiar OCP na troca de famílias, com custo adicional de abstrações.
**Quando não usar:** há uma única família estável ou a criação de objetos é trivial.
