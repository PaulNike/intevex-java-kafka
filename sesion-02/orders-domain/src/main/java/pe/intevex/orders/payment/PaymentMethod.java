package pe.intevex.orders.payment;

/**
 * SOLUCIÓN DE LA TAREA 2.
 * Sealed: solo estos tres tipos pueden implementar PaymentMethod. Nadie más.
 */
public sealed interface PaymentMethod permits Card, Yape, BankTransfer {
}
