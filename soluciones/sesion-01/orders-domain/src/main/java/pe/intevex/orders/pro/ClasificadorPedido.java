package pe.intevex.orders.pro;

import java.math.BigDecimal;

/**
 * RETO PRO 3: clasificar un pedido por monto con pattern matching en switch (Java 21).
 */
public class ClasificadorPedido {

    private static final BigDecimal CIEN = new BigDecimal("100");
    private static final BigDecimal QUINIENTOS = new BigDecimal("500");

    public static String clasificar(BigDecimal monto) {
        return switch (monto) {
            case BigDecimal m when m.signum() <= 0 -> "INVALIDO";
            case BigDecimal m when m.compareTo(CIEN) < 0 -> "PEQUENO";
            case BigDecimal m when m.compareTo(QUINIENTOS) < 0 -> "MEDIANO";
            case BigDecimal m -> "GRANDE";
        };
    }

    public static void main(String[] args) {
        for (String valor : new String[]{"0", "59.90", "279.80", "1099.00"}) {
            System.out.println("S/ " + valor + " -> " + clasificar(new BigDecimal(valor)));
        }
        try {
            new ProductRecord("", "Sin SKU", BigDecimal.TEN, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("ProductRecord rechazado: " + e.getMessage());
        }
    }
}
