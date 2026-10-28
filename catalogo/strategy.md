# Strategy

**Categoria:** Comportamental

## Nome

Strategy — encapsula algoritmos intercambiáveis sob uma interface comum.

## Problema

Uma regra varia por contexto ou condicionais escolhem algoritmos; mudanças frequentes obrigam a editar o cliente.

## Solução

O cliente depende da interface da estratégia e delega a execução ao algoritmo selecionado.

**Exemplo adicional — Java.**

```java
interface ShippingStrategy {
    BigDecimal calculate(BigDecimal orderTotal);
}
```

Exemplo compilável: `GoFExamples.ShippingStrategy`; teste: `strategyCanBeChangedWithoutChangingTheService`.

## Consequências

Permite acrescentar algoritmos sem alterar o cliente, ao custo de estratégias, seleção e configuração adicionais. Uma abstração para uma regra que nunca varia é over-engineering.

**Smell:** condicionais por tipo crescem ou novos algoritmos exigem editar o cliente.
**SOLID:** aplica OCP.
**Quando não usar:** a regra é trivial, única e não há variação claramente prevista.

**Exemplo adicional — Spring:** beans que implementam a mesma interface podem ser injetados como `List<FreightStrategy>`; o exemplo deste projeto seleciona um bean pelo nome.
