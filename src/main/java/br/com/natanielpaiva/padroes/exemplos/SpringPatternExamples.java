package br.com.natanielpaiva.padroes.exemplos;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public final class SpringPatternExamples {
    private SpringPatternExamples() {
    }

    public interface FreightStrategy {
        String name();

        BigDecimal calculate(BigDecimal total);
    }

    @Component
    public static class StandardFreight implements FreightStrategy {
        @Override
        public String name() {
            return "standard";
        }

        @Override
        public BigDecimal calculate(BigDecimal total) {
            return new BigDecimal("10.00");
        }
    }

    @Component
    public static class ExpressFreight implements FreightStrategy {
        @Override
        public String name() {
            return "express";
        }

        @Override
        public BigDecimal calculate(BigDecimal total) {
            return new BigDecimal("20.00");
        }
    }

    @Service
    public static class FreightSelector {
        private final List<FreightStrategy> strategies;

        public FreightSelector(List<FreightStrategy> strategies) {
            this.strategies = strategies;
        }

        public BigDecimal calculate(String name, BigDecimal total) {
            return strategies.stream()
                    .filter(strategy -> strategy.name().equals(name))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Frete desconhecido: " + name))
                    .calculate(total);
        }
    }

    public record OrderRegistered(String orderId) {
    }

    @Component
    public static class OrderPublisher {
        private final ApplicationEventPublisher publisher;

        public OrderPublisher(ApplicationEventPublisher publisher) {
            this.publisher = publisher;
        }

        public void publish(String orderId) {
            publisher.publishEvent(new OrderRegistered(orderId));
        }
    }

    @Component
    public static class OrderAuditListener {
        private String lastOrder;

        @EventListener
        public void onOrderRegistered(OrderRegistered event) {
            lastOrder = event.orderId();
        }

        public String lastOrder() {
            return lastOrder;
        }
    }

    public interface TaxCalculator {
        BigDecimal calculate(BigDecimal total);
    }

    @Component
    public static class LegacyTaxSdk {
        public long calculateTaxInCents(long totalInCents) {
            return Math.round(totalInCents * 0.1);
        }
    }

    @Component
    public static class TaxAdapter implements TaxCalculator {
        private final LegacyTaxSdk sdk;

        public TaxAdapter(LegacyTaxSdk sdk) {
            this.sdk = sdk;
        }

        @Override
        public BigDecimal calculate(BigDecimal total) {
            long totalInCents = total.movePointRight(2).longValueExact();
            return BigDecimal.valueOf(sdk.calculateTaxInCents(totalInCents), 2);
        }
    }

    @Component
    public static class StockService {
        public String reserve(String orderId) {
            return "estoque:" + orderId;
        }
    }

    @Component
    public static class PaymentService {
        public String charge(String orderId) {
            return "pagamento:" + orderId;
        }
    }

    @Component
    public static class DeliveryService {
        public String schedule(String orderId) {
            return "entrega:" + orderId;
        }
    }

    @Service
    public static class OrderFacade {
        private final StockService stock;
        private final PaymentService payment;
        private final DeliveryService delivery;

        public OrderFacade(StockService stock, PaymentService payment, DeliveryService delivery) {
            this.stock = stock;
            this.payment = payment;
            this.delivery = delivery;
        }

        public String checkout(String orderId) {
            return String.join(" -> ", stock.reserve(orderId), payment.charge(orderId), delivery.schedule(orderId));
        }
    }
}
