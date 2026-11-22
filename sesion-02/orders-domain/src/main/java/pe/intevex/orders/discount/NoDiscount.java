package pe.intevex.orders.discount;

import pe.intevex.orders.domain.Money;
import pe.intevex.orders.domain.Order;

public class NoDiscount implements DiscountPolicy {

    @Override
    public Money discountFor(Order order) {
        return Money.zero();
    }
}
