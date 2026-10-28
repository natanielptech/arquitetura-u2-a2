# Template Method

**Categoria:** Comportamental

## Nome

Template Method — fixa o esqueleto de um algoritmo e permite variar etapas.

## Problema

Fluxos semelhantes repetem uma sequência estável, com algumas etapas diferentes.

## Solução

Uma classe-base controla a ordem e delega etapas específicas a métodos sobrescritos.

**Exemplo adicional — Java.**

```java
abstract class DataExporter {
    final String export(String data) {
        return "validado:" + transform(data) + ":salvo";
    }
    protected abstract String transform(String data);
}
```

Exemplo compilável: `GoFExamples.DataExporter`; teste: `templateMethodKeepsTheAlgorithmSequence`.

## Consequências

Evita duplicação e controla a sequência; herança pode acoplar subclasses ao algoritmo-base e limitar variação.

**Smell:** algoritmos repetem a mesma ordem com poucas etapas diferentes.
**SOLID:** pode apoiar OCP se o esqueleto for estável.
**Quando não usar:** as etapas variam muito ou composição é mais simples que herança.
