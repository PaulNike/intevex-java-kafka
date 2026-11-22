package pe.intevex.orders.discount;

import pe.intevex.orders.domain.Money;
import pe.intevex.orders.domain.Order;
import pe.intevex.orders.retos.MiniRetos;

/** Reutiliza el mini reto 2 de la sesión 1: NEW 0%, FREQUENT 5%, VIP 10%. */
public class CustomerTypeDiscount implements DiscountPolicy {

    @Override
    public Money discountFor(Order order) {
        var rate = MiniRetos.porcentajeDescuento(order.getCustomer().getType());
        return order.subtotal().percentage(rate);
    }
}
