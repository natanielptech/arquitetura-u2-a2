package br.com.natanielpaiva.padroes;

import br.com.natanielpaiva.padroes.exemplos.GoFExamples;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GoFExamplesTest {
    @Test
    void singletonUsesOneInstance() {
        assertSame(GoFExamples.Singleton.INSTANCE, GoFExamples.Singleton.valueOf("INSTANCE"));
    }

    @Test
    void factoryMethodCreatesTheSelectedProduct() {
        assertEquals("email:Olá", new GoFExamples.EmailCreator().notify("Olá"));
    }

    @Test
    void abstractFactoryCreatesAConsistentFamily() {
        GoFExamples.UiFactory factory = new GoFExamples.LightUiFactory();
        assertEquals("botao claro", factory.createButton().render());
        assertEquals("campo claro", factory.createTextField().render());
    }

    @Test
    void builderCreatesValidObjectsAndRejectsMissingRequiredFields() {
        var report = GoFExamples.Report.builder().title("Resumo").author("Ana").build();
        assertEquals("Resumo", report.title());
        assertThrows(IllegalStateException.class, () -> GoFExamples.Report.builder().build());
    }

    @Test
    void adapterTranslatesAmountToLegacyCents() {
        var payment = new GoFExamples.PaymentAdapter(new GoFExamples.LegacyPaymentSdk());
        assertEquals(new BigDecimal("12.34"), payment.charge(new BigDecimal("12.34")));
    }

    @Test
    void decoratorAddsOptionalBehaviorWithoutChangingTheBase() {
        GoFExamples.Coffee plain = new GoFExamples.PlainCoffee();
        GoFExamples.Coffee withMilk = new GoFExamples.Milk(plain);
        assertEquals(new BigDecimal("5.00"), plain.price());
        assertEquals(new BigDecimal("6.50"), withMilk.price());
    }

    @Test
    void facadeCoordinatesSubsystemSteps() {
        assertEquals("estoque:42 -> pagamento:42 -> entrega:42", new GoFExamples.OrderFacade().checkout("42"));
    }

    @Test
    void compositeTreatsLeavesAndGroupsUniformly() {
        var folder = new GoFExamples.Folder()
                .add(new GoFExamples.File(3))
                .add(new GoFExamples.Folder().add(new GoFExamples.File(5)));
        assertEquals(8, folder.size());
    }

    @Test
    void strategyCanBeChangedWithoutChangingTheService() {
        var service = new GoFExamples.ShippingService();
        assertEquals(new BigDecimal("10.00"),
                service.calculate(new GoFExamples.StandardShipping(), new BigDecimal("50.00")));
        var freeShipping = new GoFExamples.ShippingStrategy() {
            @Override
            public String name() {
                return "free";
            }

            @Override
            public BigDecimal calculate(BigDecimal orderTotal) {
                return BigDecimal.ZERO;
            }
        };
        assertEquals(BigDecimal.ZERO, service.calculate(freeShipping, new BigDecimal("50.00")));
    }

    @Test
    void observerNotifiesSubscribersWithoutCouplingPublisherToThem() {
        var events = new GoFExamples.OrderEvents();
        var received = new AtomicReference<String>();
        events.subscribe(event -> received.set(event.orderId()));
        events.publish(new GoFExamples.OrderCreated("pedido-7"));
        assertEquals("pedido-7", received.get());
    }

    @Test
    void templateMethodKeepsTheAlgorithmSequence() {
        assertEquals("validado:um,dois:salvo", new GoFExamples.CsvExporter().export("um dois"));
    }

    @Test
    void commandSeparatesRequestFromItsReceiver() {
        var editor = new GoFExamples.TextEditor();
        new GoFExamples.AppendCommand(editor, "Olá").execute();
        assertEquals("Olá", editor.text());
    }
}
