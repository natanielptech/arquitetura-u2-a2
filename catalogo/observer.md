# Observer

**Categoria:** Comportamental

## Nome

Observer — notifica interessados quando um evento ocorre.

## Problema

Vários consumidores reagem à mesma mudança, mas o emissor não deve depender diretamente de cada um.

## Solução

O emissor publica um evento; observadores inscritos recebem a notificação.

**Exemplo adicional — Java.**

```java
events.subscribe(listener);
events.publish(new OrderCreated("pedido-7"));
```

Exemplo compilável: `GoFExamples.OrderEvents`; teste: `observerNotifiesSubscribersWithoutCouplingPublisherToThem`.

## Consequências

Desacopla emissor e consumidores, mas efeitos indiretos dificultam rastrear o fluxo; ordem de execução e falhas precisam ser consideradas.

**Smell:** uma classe central chama diretamente muitos consumidores depois de cada mudança.
**SOLID:** favorece baixo acoplamento, sem relação única com um princípio.
**Quando não usar:** existe um único consumidor síncrono e uma chamada direta é mais clara.

**Exemplo adicional — Spring:** `ApplicationEventPublisher` publica eventos de domínio e métodos `@EventListener` recebem as notificações.
