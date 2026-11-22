package pe.intevex.orders.pro;

import java.math.BigDecimal;

/**
 * RETO PRO 4: Product como record, con validaciones en el constructor compacto.
 * Adelanto de la sesión 2.
 */
public record ProductRecord(String sku, String name, BigDecimal price, int stock) {

    public ProductRecord {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("El SKU es obligatorio");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (price == null || price.signum() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
}
