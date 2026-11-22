package pe.intevex.orders.discount;

import pe.intevex.orders.domain.Money;
import pe.intevex.orders.domain.Order;

/**
 * Una política de descuento: dado un pedido, cuánto se descuenta.
 * El pedido no sabe QUÉ descuento se aplica; solo sabe que alguien se lo calcula.
 */
public interface DiscountPolicy {

    Money discountFor(Order order);
}
