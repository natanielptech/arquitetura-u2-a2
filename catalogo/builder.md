# Builder

**Categoria:** Criacional

## Nome

Builder — constrói um objeto em etapas legíveis e controladas.

## Problema

Construtores telescópicos, muitos parâmetros opcionais ou invariantes tornam a criação propensa a erros.

## Solução

Um builder acumula os valores e valida o estado antes de produzir o objeto.

**Exemplo adicional — Java.**

```java
Report report = Report.builder().title("Resumo").author("Ana").build();
```

Exemplo compilável: `GoFExamples.Report.Builder`; teste: `builderCreatesValidObjectsAndRejectsMissingRequiredFields`.

## Consequências

Melhora a legibilidade e concentra validação, adicionando uma API e estado temporário de construção.

**Smell:** parâmetros booleanos ou construtores longos dificultam entender os argumentos.
**SOLID:** pode separar a responsabilidade de construção do objeto construído.
**Quando não usar:** objetos pequenos com poucos campos e criação clara.
