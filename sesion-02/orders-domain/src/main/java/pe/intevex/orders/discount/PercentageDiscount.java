package pe.intevex.orders.discount;

import pe.intevex.orders.domain.Money;
import pe.intevex.orders.domain.Order;

import java.math.BigDecimal;

/** Un porcentaje fijo sobre el subtotal. Ej.: new PercentageDiscount(new BigDecimal("0.15")) = 15%. */
public record PercentageDiscount(BigDecimal rate) implements DiscountPolicy {

    @Override
    public Money discountFor(Order order) {
        return order.subtotal().percentage(rate);
    }
}
