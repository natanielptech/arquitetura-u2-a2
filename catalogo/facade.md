# Facade

**Categoria:** Estrutural

## Nome

Facade — oferece uma entrada simples e focada para um subsistema.

## Problema

Clientes precisam coordenar repetidamente várias classes para uma operação comum.

## Solução

Uma fachada recebe a solicitação, coordena os serviços do subsistema e expõe ao cliente uma operação de nível mais alto.

**Exemplo adicional — Java.**

```java
String checkout(String id) {
    estoque.reservar(id);
    pagamento.cobrar(id);
    return entrega.agendar(id);
}
```

Exemplo compilável: `GoFExamples.OrderFacade`; teste: `facadeCoordinatesSubsystemSteps`.

## Consequências

Reduz acoplamento dos clientes ao subsistema, mas uma fachada que concentra muitos casos de uso pode virar um objeto grande.

**Smell:** o mesmo roteiro de chamadas ao subsistema se repete em diversos clientes.
**SOLID:** contribui para ISP ao apresentar uma interface menor e específica.
**Quando não usar:** o subsistema já oferece operações claras ou a fachada se tornaria um intermediário sem simplificação.

**Exemplo adicional — Spring:** um `@Service` de caso de uso pode orquestrar estoque, pagamento e entrega sem expor esses detalhes ao controlador.
