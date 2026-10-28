# Adapter

**Categoria:** Estrutural

## Nome

Adapter — traduz uma interface existente para a interface que o cliente espera.

## Problema

Uma biblioteca externa útil possui operações ou formatos incompatíveis com o domínio da aplicação.

## Solução

O adaptador implementa a abstração da aplicação, traduz a chamada e delega à dependência externa.

**Exemplo adicional — Java.**

```java
final class PaymentAdapter implements Payment {
    private final LegacyPaymentSdk sdk;
    public BigDecimal charge(BigDecimal amount) {
        return BigDecimal.valueOf(sdk.chargeInCents(amount.movePointRight(2).longValueExact()), 2);
    }
}
```

Exemplo compilável: `GoFExamples.PaymentAdapter`; teste: `adapterTranslatesAmountToLegacyCents`.

## Consequências

Isola o cliente de um formato externo; cria uma camada de tradução e pode esconder recursos específicos do SDK.

**Smell:** classes de domínio conhecem SDKs ou traduzem formatos externos repetidamente.
**SOLID:** aplica DIP, pois o cliente depende da abstração da aplicação.
**Quando não usar:** interfaces já são compatíveis ou a adaptação é uma conversão trivial e única.

**Exemplo adicional — Spring:** um `@Component` implementa a porta de pagamento da aplicação e envolve o SDK externo, mantendo a dependência apontando para a abstração do domínio.
