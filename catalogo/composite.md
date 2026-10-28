# Composite

**Categoria:** Estrutural

## Nome

Composite — trata elementos individuais e composições por uma interface comum.

## Problema

Clientes operam sobre uma árvore e não deveriam distinguir continuamente folhas de grupos.

## Solução

Folhas e composições implementam uma interface; a composição delega operações a seus filhos.

**Exemplo adicional — Java.**

```java
interface FileNode { long size(); }
record File(long size) implements FileNode {}
```

Exemplo compilável: `GoFExamples.FileNode`, `File` e `Folder`; teste: `compositeTreatsLeavesAndGroupsUniformly`.

## Consequências

Simplifica operações recursivas, mas a interface comum pode não representar bem operações específicas de folhas ou composições.

**Smell:** condicionais distinguem nós internos de elementos terminais para a mesma operação.
**SOLID:** não possui vínculo direto obrigatório.
**Quando não usar:** não há hierarquia ou os clientes precisam de operações radicalmente diferentes para cada tipo.
