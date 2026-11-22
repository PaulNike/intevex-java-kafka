package pe.intevex.orders.payment;

public record BankTransfer(String bank, String operationNumber) implements PaymentMethod {
}
