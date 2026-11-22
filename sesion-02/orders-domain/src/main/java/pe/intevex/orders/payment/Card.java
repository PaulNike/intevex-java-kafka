package pe.intevex.orders.payment;

public record Card(String brand, String lastFourDigits) implements PaymentMethod {
}
