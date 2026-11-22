package pe.intevex.orders.domain;

import java.util.Objects;

/** Un cliente de la tienda. Dos clientes son el mismo si tienen el mismo id. */
public class Customer {

    private final String id;
    private final String fullName;
    private final String email;
    private final CustomerType type;

    public Customer(String id, String fullName, String email, CustomerType type) {
        this.id = Objects.requireNonNull(id, "El id es obligatorio");
        this.fullName = Objects.requireNonNull(fullName, "El nombre es obligatorio");
        this.email = email;
        this.type = Objects.requireNonNull(type, "El tipo de cliente es obligatorio");
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public CustomerType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "%s (%s) | %s".formatted(fullName, type, email);
    }
}
