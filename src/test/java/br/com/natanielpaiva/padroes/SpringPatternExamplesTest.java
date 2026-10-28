package br.com.natanielpaiva.padroes;

import br.com.natanielpaiva.padroes.exemplos.SpringPatternExamples.FreightSelector;
import br.com.natanielpaiva.padroes.exemplos.SpringPatternExamples.OrderFacade;
import br.com.natanielpaiva.padroes.exemplos.SpringPatternExamples.OrderAuditListener;
import br.com.natanielpaiva.padroes.exemplos.SpringPatternExamples.OrderPublisher;
import br.com.natanielpaiva.padroes.exemplos.SpringPatternExamples.TaxAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringPatternExamplesTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FreightSelector freightSelector;

    @Autowired
    private OrderPublisher orderPublisher;

    @Autowired
    private OrderAuditListener auditListener;

    @Autowired
    private TaxAdapter taxAdapter;

    @Autowired
    private OrderFacade orderFacade;

    @Test
    void strategySelectsOneOfTheInjectedSpringBeans() {
        assertEquals(new BigDecimal("20.00"), freightSelector.calculate("express", new BigDecimal("80.00")));
        assertThrows(IllegalArgumentException.class,
                () -> freightSelector.calculate("unknown", new BigDecimal("80.00")));
    }

    @Test
    void observerUsesSpringApplicationEvents() {
        orderPublisher.publish("pedido-42");
        assertEquals("pedido-42", auditListener.lastOrder());
    }

    @Test
    void adapterImplementsTheApplicationPortAroundTheLegacySdk() {
        assertEquals(new BigDecimal("10.00"), taxAdapter.calculate(new BigDecimal("100")));
    }

    @Test
    void facadeOrchestratesSubsystemServices() {
        assertEquals("estoque:pedido-9 -> pagamento:pedido-9 -> entrega:pedido-9",
                orderFacade.checkout("pedido-9"));
    }

    @Test
    void homePageRendersAllTwelvePatterns() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("12 padrões")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Factory Method")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Observer")));
    }

    @Test
    void patternPageShowsItsExampleAndTest() throws Exception {
        mockMvc.perform(get("/catalogo/strategy"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "strategyCanBeChangedWithoutChangingTheService")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("EXEMPLO ADICIONAL")));
    }

    @Test
    void unknownPatternReturnsNotFound() throws Exception {
        mockMvc.perform(get("/catalogo/not-a-pattern"))
                .andExpect(status().isNotFound());
    }
}
