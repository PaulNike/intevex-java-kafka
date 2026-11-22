package pe.intevex.orders.domain;

/**
 * Estados de un pedido y qué cambios están permitidos.
 *
 *   CREATED --> CONFIRMED --> PAID
 *      |            |
 *      +------------+--> CANCELLED
 */
public enum OrderStatus {
    CREATED,
    CONFIRMED,
    PAID,
    CANCELLED;

    public boolean canTransitionTo(OrderStatus next) {
        return switch (this) {
            case CREATED -> next == CONFIRMED || next == CANCELLED;
            case CONFIRMED -> next == PAID || next == CANCELLED;
            case PAID, CANCELLED -> false;
        };
    }
}
