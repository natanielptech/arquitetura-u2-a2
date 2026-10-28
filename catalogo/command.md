# Command

**Categoria:** Comportamental

## Nome

Command — representa uma solicitação como objeto executável.

## Problema

Uma operação precisa ser enfileirada, registrada, executada depois ou desfeita; o disparador não deve conhecer o receptor.

## Solução

O comando encapsula a ação e referencia o receptor; o invocador executa a interface comum.

**Exemplo adicional — Java.**

```java
interface Command { void execute(); }
```

Exemplo compilável: `GoFExamples.AppendCommand`; teste: `commandSeparatesRequestFromItsReceiver`.

## Consequências

Separa quem solicita de quem executa e abre espaço para filas e histórico, mas adiciona classes e ciclo de vida por operação.

**Smell:** o invocador conhece detalhes do receptor ou mistura disparo e execução.
**SOLID:** pode apoiar SRP ao separar solicitação e execução.
**Quando não usar:** a ação é imediata, simples e não precisa de fila, histórico ou desfazer.
