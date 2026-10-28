package br.com.natanielpaiva.padroes.exemplos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class GoFExamples {
    private GoFExamples() {
    }

    public enum Singleton {
        INSTANCE
    }

    public interface Channel {
        String send(String message);
    }

    public static final class EmailChannel implements Channel {
        @Override
        public String send(String message) {
            return "email:" + message;
        }
    }

    public abstract static class ChannelCreator {
        protected abstract Channel createChannel();

        public String notify(String message) {
            return createChannel().send(message);
        }
    }

    public static final class EmailCreator extends ChannelCreator {
        @Override
        protected Channel createChannel() {
            return new EmailChannel();
        }
    }

    public interface Button {
        String render();
    }

    public interface TextField {
        String render();
    }

    public record LightButton() implements Button {
        @Override
        public String render() {
            return "botao claro";
        }
    }

    public record LightTextField() implements TextField {
        @Override
        public String render() {
            return "campo claro";
        }
    }

    public interface UiFactory {
        Button createButton();

        TextField createTextField();
    }

    public static final class LightUiFactory implements UiFactory {
        @Override
        public Button createButton() {
            return new LightButton();
        }

        @Override
        public TextField createTextField() {
            return new LightTextField();
        }
    }

    public record Report(String title, String author, boolean confidential) {
        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private String title;
            private String author;
            private boolean confidential;

            public Builder title(String title) {
                this.title = title;
                return this;
            }

            public Builder author(String author) {
                this.author = author;
                return this;
            }

            public Builder confidential(boolean confidential) {
                this.confidential = confidential;
                return this;
            }

            public Report build() {
                if (title == null || title.isBlank()) {
                    throw new IllegalStateException("O título é obrigatório");
                }
                return new Report(title, author, confidential);
            }
        }
    }

    public interface Payment {
        BigDecimal charge(BigDecimal amount);
    }

    public static final class LegacyPaymentSdk {
        public long chargeInCents(long cents) {
            return cents;
        }
    }

    public static final class PaymentAdapter implements Payment {
        private final LegacyPaymentSdk sdk;

        public PaymentAdapter(LegacyPaymentSdk sdk) {
            this.sdk = sdk;
        }

        @Override
        public BigDecimal charge(BigDecimal amount) {
            long cents = amount.movePointRight(2).longValueExact();
            return BigDecimal.valueOf(sdk.chargeInCents(cents), 2);
        }
    }

    public interface Coffee {
        BigDecimal price();
    }

    public record PlainCoffee() implements Coffee {
        @Override
        public BigDecimal price() {
            return new BigDecimal("5.00");
        }
    }

    public static final class Milk implements Coffee {
        private final Coffee coffee;

        public Milk(Coffee coffee) {
            this.coffee = coffee;
        }

        @Override
        public BigDecimal price() {
            return coffee.price().add(new BigDecimal("1.50"));
        }
    }

    public static final class OrderFacade {
        public String checkout(String orderId) {
            return String.join(" -> ", List.of(
                    "estoque:" + orderId,
                    "pagamento:" + orderId,
                    "entrega:" + orderId
            ));
        }
    }

    public interface FileNode {
        long size();
    }

    public record File(long size) implements FileNode {
    }

    public static final class Folder implements FileNode {
        private final List<FileNode> children = new ArrayList<>();

        public Folder add(FileNode child) {
            children.add(child);
            return this;
        }

        @Override
        public long size() {
            return children.stream().mapToLong(FileNode::size).sum();
        }
    }

    public interface ShippingStrategy {
        String name();

        BigDecimal calculate(BigDecimal orderTotal);
    }

    public record StandardShipping() implements ShippingStrategy {
        @Override
        public String name() {
            return "standard";
        }

        @Override
        public BigDecimal calculate(BigDecimal orderTotal) {
            return new BigDecimal("10.00");
        }
    }

    public static final class ShippingService {
        public BigDecimal calculate(ShippingStrategy strategy, BigDecimal orderTotal) {
            return strategy.calculate(orderTotal);
        }
    }

    public record OrderCreated(String orderId) {
    }

    @FunctionalInterface
    public interface OrderListener {
        void onOrderCreated(OrderCreated event);
    }

    public static final class OrderEvents {
        private final List<OrderListener> listeners = new ArrayList<>();

        public void subscribe(OrderListener listener) {
            listeners.add(listener);
        }

        public void publish(OrderCreated event) {
            listeners.forEach(listener -> listener.onOrderCreated(event));
        }
    }

    public abstract static class DataExporter {
        public final String export(String data) {
            return "validado:" + transform(data) + ":salvo";
        }

        protected abstract String transform(String data);
    }

    public static final class CsvExporter extends DataExporter {
        @Override
        protected String transform(String data) {
            return data.replace(" ", ",");
        }
    }

    @FunctionalInterface
    public interface Command {
        void execute();
    }

    public static final class TextEditor {
        private String text = "";

        public void append(String value) {
            text += value;
        }

        public String text() {
            return text;
        }
    }

    public static final class AppendCommand implements Command {
        private final TextEditor editor;
        private final String value;

        public AppendCommand(TextEditor editor, String value) {
            this.editor = editor;
            this.value = value;
        }

        @Override
        public void execute() {
            editor.append(value);
        }
    }
}
