package pe.intevex.orders.retos;

import org.junit.jupiter.api.Test;
import pe.intevex.orders.domain.CustomerType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * RETO PRO 2: primer test con JUnit 5.
 * Va en: src/test/java/pe/intevex/orders/retos/MiniRetosTest.java
 */
class MiniRetosTest {

    @Test
    void calculaIgvRedondeadoADosDecimales() {
        assertEquals(new BigDecimal("50.36"), MiniRetos.calcularIgv(new BigDecimal("279.80")));
    }

    @Test
    void calculaTotalConIgv() {
        assertEquals(new BigDecimal("295.00"), MiniRetos.calcularTotal(new BigDecimal("250.00")));
    }

    @Test
    void clienteVipTieneDiezPorCiento() {
        assertEquals(new BigDecimal("0.10"), MiniRetos.porcentajeDescuento(CustomerType.VIP));
    }
}
