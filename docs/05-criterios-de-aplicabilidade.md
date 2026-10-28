# Critérios de aplicabilidade

**Unidade 2 · Aula 2 — Prof. Nataniel Paiva**

Comece pela pergunta **“qual é exatamente o meu problema?”**, não pela busca de um padrão. Depois verifique a seção de aplicabilidade das alternativas e compare suas consequências com o contexto real.

```mermaid
flowchart TD
    A([Tenho um problema de projeto]) --> B[Identificar o problema com precisão]
    B --> C{Já se manifestou<br/>ou está claramente previsto?}
    C -- não --> X[Não aplicar padrão<br/>evitar over-engineering]
    C -- sim --> D{Algum padrão descreve o problema<br/>na seção Aplicabilidade?}
    D -- não --> Y[Resolver com o mais simples possível]
    D -- sim --> E[Avaliar complexidade · desempenho · nº de classes]
    E --> F{O benefício compensa o custo<br/>neste contexto?}
    F -- não --> Y
    F -- sim --> G([Aplicar e documentar a decisão])
```

Considere, no mínimo, complexidade, desempenho, quantidade de classes e custo de manutenção. Um padrão conhecido não é automaticamente a solução adequada; se o ganho não superar o custo, escolha a solução mais simples.

O [exercício de decisão](../exercicios/) propõe situações em que a resposta correta pode ser não usar padrão algum.
