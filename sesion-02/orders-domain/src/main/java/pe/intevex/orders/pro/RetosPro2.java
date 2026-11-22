package pe.intevex.orders.pro;

import pe.intevex.orders.discount.DiscountPolicy;
import pe.intevex.orders.domain.Customer;
import pe.intevex.orders.domain.CustomerType;
import pe.intevex.orders.domain.Money;
import pe.intevex.orders.domain.Order;
import pe.intevex.orders.domain.OrderStatus;
import pe.intevex.orders.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** SOLUCIONES DE LOS RETOS PRO DE LA SESIÓN 2 (solo para el docente). */
public class RetosPro2 {

    /** Reto Pro 1: repartir un monto en N partes sin perder ni un céntimo. 100 / 3 = 33.34 + 33.33 + 33.33. */
    public static List<Money> allocate(Money total, int parts) {
        BigDecimal cents = total.amount().movePointRight(2);
        BigDecimal base = cents.divide(BigDecimal.valueOf(parts), 0, RoundingMode.DOWN);
        int remainder = cents.subtract(base.multiply(BigDecimal.valueOf(parts))).intValue();
        List<Money> result = new ArrayList<>();
        for (int i = 0; i < parts; i++) {
            BigDecimal share = i < remainder ? base.add(BigDecimal.ONE) : base;
            result.add(new Money(share.movePointLeft(2), total.currency()));
        }
        return result;
    }

    /** Reto Pro 4: las transiciones como datos, con EnumMap y EnumSet. */
    private static final Map<OrderStatus, Set<OrderStatus>> TRANSITIONS = new EnumMap<>(OrderStatus.class);
    static {
        TRANSITIONS.put(OrderStatus.CREATED, EnumSet.of(OrderStatus.CONFIRMED, OrderStatus.CANCELLED));
        TRANSITIONS.put(OrderStatus.CONFIRMED, EnumSet.of(OrderStatus.PAID, OrderStatus.CANCELLED));
        TRANSITIONS.put(OrderStatus.PAID, EnumSet.noneOf(OrderStatus.class));
        TRANSITIONS.put(OrderStatus.CANCELLED, EnumSet.noneOf(OrderStatus.class));
    }

    public static boolean canTransition(OrderStatus from, OrderStatus to) {
        return TRANSITIONS.get(from).contains(to);
    }

    public static void main(String[] args) {
        System.out.println("Reto Pro 1 · allocate(S/ 100.00, 3) = " + allocate(Money.soles("100"), 3));

        // Reto Pro 2: equals/hashCode. Con equals por SKU, el Set tiene 1 elemento, no 2.
        Set<Product> set = new HashSet<>();
        set.add(Product.of("KB-001", "Teclado mecánico", "199.90", 15));
        set.add(Product.of("KB-001", "Teclado mecánico", "199.90", 15));
        System.out.println("Reto Pro 2 · productos distintos en el Set = " + set.size());

        // Reto Pro 3: DiscountPolicy es una interfaz funcional, así que acepta una lambda.
        DiscountPolicy blackFriday = order -> order.subtotal().percentage(new BigDecimal("0.30"));
        var order = new Order(new Customer("C-9", "Ana Torres", "ana@correo.com", CustomerType.FREQUENT));
        order.addItem(Product.of("MN-003", "Monitor 27\"", "1099.00", 5), 1);
        System.out.println("Reto Pro 3 · Black Friday 30% -> total " + order.total(blackFriday));

        System.out.println("Reto Pro 4 · CREATED -> PAID permitido? " + canTransition(OrderStatus.CREATED, OrderStatus.PAID));
    }
}
