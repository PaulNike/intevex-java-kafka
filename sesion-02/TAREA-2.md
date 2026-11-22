# Tarea 2 · Value objects con `record`

**Entrega:** jueves 26 de noviembre, antes de las 7:00 p.m., por Google Classroom (pega el link de tu repo).

Trabaja sobre el proyecto `orders-domain` de la sesión 2. En la primera parte agregas comportamiento al record `Money`. En la segunda creas tu propio record, `ExchangeRate`, aplicando las mismas ideas: validar en el constructor compacto, normalizar, crear con métodos de fábrica y operar sin modificar el objeto.

Todas las pruebas van en una clase nueva con `main`: `src/main/java/pe/intevex/orders/tarea2/TareaMoney.java`.

---

## Parte 1 · Más comportamiento para `Money`

Agrega estos métodos a `Money`. Ninguno puede modificar el objeto: si el resultado es otro monto, devuelve un `Money` nuevo.

### 1. `static Money sum(List<Money> montos)`

Suma una lista de montos. Si la lista está vacía, devuelve `S/ 0.00`.

```java
Money.sum(List.of(Money.soles("199.90"), Money.soles("79.90"), Money.soles("1099.00")))  // S/ 1378.80
Money.sum(List.of())                                                                      // S/ 0.00
```

> Antes de resolverlo, escribe en tu `main` un acumulador que **no** reasigne el resultado (`total.add(m);`) y observa qué imprime. Déjalo comentado con una línea que explique por qué falla.

### 2. `List<Money> split(int partes)`

Reparte un monto en partes iguales **sin perder ni inventar céntimos**. Si no se puede dividir exacto, los céntimos que sobran se asignan uno a uno a las primeras partes.

```java
Money.soles("100.00").split(3)   // [S/ 33.34, S/ 33.33, S/ 33.33]
Money.soles("10.00").split(4)    // [S/ 2.50, S/ 2.50, S/ 2.50, S/ 2.50]
```

- La suma de las partes debe ser **exactamente** el monto original. Compruébalo con `Money.sum(...)`.
- Si `partes` es 0 o negativo, lanza `IllegalArgumentException`.

> Pista: divide con `RoundingMode.DOWN` para obtener la parte base y calcula cuántos céntimos sobran.

### 3. `Money[] splitIgv(BigDecimal tasa)`

Recibe un monto que **ya incluye** IGV y devuelve dos montos: la base imponible y el IGV.

```java
Money.soles("118.00").splitIgv(new BigDecimal("0.18"))   // base S/ 100.00 · IGV S/ 18.00
Money.soles("100.00").splitIgv(new BigDecimal("0.18"))   // base S/ 84.75  · IGV S/ 15.25
```

- `base + IGV` debe ser **exactamente** el total.
- Piensa qué pasa si calculas el IGV multiplicando la base por 0.18 en vez de restar. Pruébalo con S/ 100.00 y explica en un comentario por qué no conviene.

### 4. `Money` comparable

Haz que `Money` implemente `Comparable<Money>`. Al comparar monedas distintas debe lanzar `IllegalArgumentException`.

```java
List<Money> precios = new ArrayList<>(List.of(
        Money.soles("50"), Money.soles("9.90"), Money.soles("120.5"), Money.soles("9.9")));
Collections.sort(precios);   // [S/ 9.90, S/ 9.90, S/ 50.00, S/ 120.50]
Collections.max(precios);    // S/ 120.50
```

Imprime también estas dos comparaciones y explica en un comentario por qué dan resultados distintos:

```java
Money.soles("10").equals(Money.soles("10.00"))            // true
new BigDecimal("10").equals(new BigDecimal("10.00"))      // false
```

---

## Parte 2 · Crea el record `ExchangeRate`

Crea `src/main/java/pe/intevex/orders/domain/ExchangeRate.java`: un tipo de cambio entre dos monedas.

```java
public record ExchangeRate(String from, String to, BigDecimal rate)
```

### Reglas del constructor compacto

| Regla | Si no se cumple |
|---|---|
| `from` y `to` son códigos de 3 letras | `IllegalArgumentException("Moneda inválida: ...")` |
| Se normalizan sin espacios y en mayúsculas: `" usd "` → `"USD"` | — |
| `from` y `to` deben ser distintas | `IllegalArgumentException("Las monedas deben ser distintas")` |
| `rate` no es null y es mayor a 0 | `IllegalArgumentException("El tipo de cambio debe ser mayor a 0")` |
| `rate` se normaliza a 4 decimales con `HALF_UP` | — |

### Métodos

- `static ExchangeRate of(String from, String to, String rate)`: método de fábrica.
- `ExchangeRate inverse()`: devuelve el tipo de cambio en sentido contrario, con 4 decimales. De `USD → PEN 3.75` pasa a `PEN → USD 0.2667`.
- `Money convert(Money monto)`: convierte el monto a la moneda `to`. Si la moneda del monto no es `from`, lanza `IllegalArgumentException("Este tipo de cambio convierte USD, no PEN")`.
- `toString()`: `1 USD = 3.7500 PEN`.

> Para crear dólares necesitas un método de fábrica nuevo en `Money`: `Money.of("100", "USD")`.

### Pruébalo en tu `main`

```java
ExchangeRate usdPen = ExchangeRate.of("usd", " PEN ", "3.75");
Money dolares = Money.of("100", "USD");
Money soles = usdPen.convert(dolares);
ExchangeRate penUsd = usdPen.inverse();
```

Y prueba que lancen error:

```java
usdPen.convert(Money.soles("10"));
ExchangeRate.of("USD", "usd", "3.75");
ExchangeRate.of("USD", "PEN", "0");
ExchangeRate.of("DOLAR", "PEN", "3.75");
```

Al convertir S/ 375.00 de vuelta a dólares obtendrás `USD 100.01`, no `USD 100.00`. Explica en un comentario de dónde sale ese céntimo y qué harías en un sistema real para evitarlo.

---

## Salida esperada

Organiza tu `main` para que la salida quede así:

```
=== 1. Suma ===
S/ 1378.80
S/ 0.00

=== 2. Repartir ===
[S/ 33.34, S/ 33.33, S/ 33.33]
[S/ 2.50, S/ 2.50, S/ 2.50, S/ 2.50]
Suma de las partes: S/ 100.00

=== 3. IGV ===
S/ 118.00 -> base S/ 100.00 | IGV S/ 18.00
S/ 100.00 -> base S/ 84.75 | IGV S/ 15.25

=== 4. Ordenar y comparar ===
[S/ 9.90, S/ 9.90, S/ 50.00, S/ 120.50]
Mayor: S/ 120.50
Money equals: true
BigDecimal equals: false

=== 5. Tipo de cambio ===
1 USD = 3.7500 PEN
USD 100.00 -> S/ 375.00
1 PEN = 0.2667 USD
S/ 375.00 -> USD 100.01
Error: Este tipo de cambio convierte USD, no PEN
Error: Las monedas deben ser distintas
Error: El tipo de cambio debe ser mayor a 0
Error: Moneda inválida: DOLAR
```

---

## Qué se revisa

- `Money` y `ExchangeRate` siguen siendo inmutables: ningún método cambia el objeto.
- Las validaciones están en el constructor compacto, no repartidas por el código.
- Ningún céntimo se pierde ni aparece de la nada en `split` y `splitIgv`.
- Los comentarios que se piden en los ejercicios 1, 3, 4 y en la parte 2 están escritos con tus palabras.
- Commits con mensajes claros, por ejemplo `feat(money): agrega split sin perder céntimos`.
