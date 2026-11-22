package pe.intevex.orders.domain;

import pe.intevex.orders.discount.DiscountPolicy;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * El pedido. Es una ENTIDAD: tiene identidad (id) y su estado cambia con el tiempo.
 * Toda regla de negocio vive aquí dentro, no afuera.
 */
public class Order {

    private final String id;
    private final Customer customer;
    private final Instant createdAt;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.CREATED;

    public Order(Customer customer) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.customer = Objects.requireNonNull(customer, "El cliente es obligatorio");
        this.createdAt = Instant.now();
    }

    /** Solo se agregan productos mientras el pedido está en CREATED. */
    public void addItem(Product product, int quantity) {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("No se puede modificar un pedido en estado " + status);
        }
        if (quantity > product.getStock()) {
            throw new IllegalArgumentException("Stock insuficiente de " + product.getName() + ": hay " + product.getStock());
        }
        items.add(OrderItem.of(product, quantity));
    }

    public Money subtotal() {
        Money total = Money.zero();
        for (OrderItem item : items) {
            total = total.add(item.subtotal());
        }
        return total;
    }

    /** Total aplicando una política de descuento. Nunca queda negativo. */
    public Money total(DiscountPolicy policy) {
        Money total = subtotal().subtract(policy.discountFor(this));
        return total.isNegative() ? Money.zero() : total;
    }

    public void confirm() {
        if (items.isEmpty()) {
            throw new IllegalStateException("No se puede confirmar un pedido vacío");
        }
        changeStatus(OrderStatus.CONFIRMED);
    }

    public void markAsPaid() {
        changeStatus(OrderStatus.PAID);
    }

    public void cancel() {
        changeStatus(OrderStatus.CANCELLED);
    }

    private void changeStatus(OrderStatus next) {
        if (!status.canTransitionTo(next)) {
            throw new IllegalStateException("No se puede pasar de " + status + " a " + next);
        }
        status = next;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    /** Copia de solo lectura: nadie puede agregar ítems por fuera de addItem. */
    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }

    @Override
    public String toString() {
        return "Pedido %s | %s | %s | %d ítems | subtotal %s".formatted(id, customer.getFullName(), status, items.size(), subtotal());
    }
}
