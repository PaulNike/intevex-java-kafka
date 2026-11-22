package pe.intevex.orders.domain;

import java.util.Objects;

/** Una línea del pedido: qué producto, a qué precio y cuántas unidades. */
public record OrderItem(String sku, String name, Money unitPrice, int quantity) {

    public OrderItem {
        Objects.requireNonNull(sku, "El SKU es obligatorio");
        Objects.requireNonNull(unitPrice, "El precio es obligatorio");
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
    }

    /** Copiamos el precio del producto: si mañana cambia, el pedido ya hecho no cambia. */
    public static OrderItem of(Product product, int quantity) {
        return new OrderItem(product.getSku(), product.getName(), Money.soles(product.getPrice()), quantity);
    }

    public Money subtotal() {
        return unitPrice.multiply(quantity);
    }
}
