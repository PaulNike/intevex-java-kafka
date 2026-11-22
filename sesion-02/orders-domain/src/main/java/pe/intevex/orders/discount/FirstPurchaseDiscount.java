package pe.intevex.orders.discount;

import pe.intevex.orders.domain.CustomerType;
import pe.intevex.orders.domain.Money;
import pe.intevex.orders.domain.Order;

/** S/ 20 de descuento para clientes nuevos con compras desde S/ 100. */
public class FirstPurchaseDiscount implements DiscountPolicy {

    private static final Money MINIMUM = Money.soles("100");
    private static final Money DISCOUNT = Money.soles("20");

    @Override
    public Money discountFor(Order order) {
        boolean isNew = order.getCustomer().getType() == CustomerType.NEW;
        return isNew && order.subtotal().isGreaterOrEqualThan(MINIMUM) ? DISCOUNT : Money.zero();
    }
}
