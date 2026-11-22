package pe.intevex.orders;

import pe.intevex.orders.discount.CustomerTypeDiscount;
import pe.intevex.orders.discount.DiscountPolicy;
import pe.intevex.orders.discount.FirstPurchaseDiscount;
import pe.intevex.orders.discount.NoDiscount;
import pe.intevex.orders.discount.PercentageDiscount;
import pe.intevex.orders.domain.Customer;
import pe.intevex.orders.domain.CustomerType;
import pe.intevex.orders.domain.Order;
import pe.intevex.orders.domain.Product;
import pe.intevex.orders.payment.BankTransfer;
import pe.intevex.orders.payment.Card;
import pe.intevex.orders.payment.PaymentDescriber;
import pe.intevex.orders.payment.PaymentMethod;
import pe.intevex.orders.payment.Yape;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        var teclado = Product.of("KB-001", "Teclado mecánico", "199.90", 15);
        var mouse = Product.of("MS-002", "Mouse inalámbrico", "79.90", 3);
        var monitor = Product.of("MN-003", "Monitor 27\"", "1099.00", 5);

        var maria = new Customer("C-1", "María Rojas", "maria@correo.com", CustomerType.VIP);
        var juan = new Customer("C-2", "Juan Pérez", "juan@correo.com", CustomerType.NEW);

        System.out.println("=== Pedido de María (VIP) ===");
        var pedido = new Order(maria);
        pedido.addItem(teclado, 1);
        pedido.addItem(mouse, 2);
        pedido.getItems().forEach(i -> System.out.println("  " + i.quantity() + " x " + i.name() + " = " + i.subtotal()));
        System.out.println("Subtotal: " + pedido.subtotal());

        List<DiscountPolicy> politicas = List.of(
                new NoDiscount(),
                new PercentageDiscount(new BigDecimal("0.15")),
                new CustomerTypeDiscount(),
                new FirstPurchaseDiscount());
        for (DiscountPolicy p : politicas) {
            System.out.println("  " + p.getClass().getSimpleName() + " -> total " + pedido.total(p));
        }

        System.out.println("\n=== Ciclo de vida ===");
        pedido.confirm();
        System.out.println("Estado: " + pedido.getStatus());
        try {
            pedido.addItem(monitor, 1);
        } catch (IllegalStateException e) {
            System.out.println("Bloqueado: " + e.getMessage());
        }
        pedido.markAsPaid();
        System.out.println("Estado: " + pedido.getStatus());
        try {
            pedido.cancel();
        } catch (IllegalStateException e) {
            System.out.println("Bloqueado: " + e.getMessage());
        }

        System.out.println("\n=== Primera compra de Juan (NEW) ===");
        var pedidoJuan = new Order(juan);
        pedidoJuan.addItem(teclado, 1);
        System.out.println("Subtotal " + pedidoJuan.subtotal() + " -> con FirstPurchaseDiscount " + pedidoJuan.total(new FirstPurchaseDiscount()));

        System.out.println("\n=== Tarea 2 · Medios de pago ===");
        List<PaymentMethod> medios = List.of(
                new Card("Visa", "4321"),
                new Yape("987654321"),
                new BankTransfer("BCP", "00123456"));
        for (PaymentMethod m : medios) {
            System.out.println("  " + PaymentDescriber.describe(m) + " | comisión " + PaymentDescriber.commission(m, pedido.subtotal()));
        }
    }
}
