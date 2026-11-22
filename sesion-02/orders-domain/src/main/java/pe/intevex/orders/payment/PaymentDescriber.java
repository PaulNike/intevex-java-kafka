package pe.intevex.orders.payment;

import pe.intevex.orders.domain.Money;

import java.math.BigDecimal;

/**
 * SOLUCIÓN DE LA TAREA 2: pattern matching con record patterns (Java 21).
 * No hay default: como PaymentMethod es sealed, el compilador sabe que cubrimos todos los casos.
 */
public class PaymentDescriber {

    public static String describe(PaymentMethod method) {
        return switch (method) {
            case Card(String brand, String last4) -> brand + " terminada en " + last4;
            case Yape(String phone) -> "Yape al " + phone.substring(0, 3) + "***" + phone.substring(phone.length() - 3);
            case BankTransfer(String bank, String operation) -> "Transferencia " + bank + " | operación " + operation;
        };
    }

    /** Comisión de ejemplo: tarjeta 3.5%, Yape y transferencia sin comisión. */
    public static Money commission(PaymentMethod method, Money total) {
        return switch (method) {
            case Card c -> total.percentage(new BigDecimal("0.035"));
            case Yape y -> Money.zero();
            case BankTransfer t -> Money.zero();
        };
    }
}
