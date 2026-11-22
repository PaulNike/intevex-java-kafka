# Java y Microservicios con Kafka

Repositorio oficial del curso de **[Intevex Academy](https://www.intevexacademy.com)**.

Durante 18 sesiones construimos, paso a paso, un **sistema de pedidos orientado a eventos**: empieza como un modelo en Java, se convierte en una API REST, se divide en microservicios de pedidos, pagos e inventario, y termina comunicándose por eventos con Apache Kafka, con Outbox, idempotencia y una saga que revierte el pedido si el pago falla.

| | |
|---|---|
| **Duración** | 54 horas en vivo · 18 sesiones |
| **Horario** | Jueves y sábados, de 7:00 a 10:00 p.m. (hora de Lima) |
| **Inicio** | Sábado 14 de noviembre de 2026 |
| **Fin** | Sábado 30 de enero de 2027 |
| **Vacaciones** | Del 24 de diciembre al 2 de enero · retomamos el jueves 7 de enero |
| **Modalidad** | En vivo por Google Meet · [enlace de la clase](https://meet.google.com/nmc-vktq-kgo) |
| **Material** | [Google Classroom](https://classroom.google.com/c/ODgzNjIxNTI4NjYy?cjc=ylwrv32x) · código `ylwrv32x` |
| **Docente** | Paul Rodríguez · Technical Lead |

---

## Temario

| Módulo | Tema | Entregable |
|---|---|---|
| 01 | Fundamentos de Java | Modelo de dominio de pedidos en Java |
| 02 | APIs REST con Spring Boot | API de pedidos |
| 03 | Diseño de microservicios | Proyecto dividido en orders, payments e inventory |
| 04 | Fundamentos de Apache Kafka | Kafka con Docker, CLI y Kafka UI |
| 05 | Kafka con Spring Boot | Evento OrderCreated de orders a payments e inventory |
| 06 | Patrones de arquitectura orientada a eventos | Saga de pedido con compensación |
| 07 | Proyecto integrador | Sistema de pedidos orientado a eventos |

**Stack:** Java 21 · Maven · Spring Boot · PostgreSQL · Docker · Apache Kafka

---

## Requisitos

Antes de la primera clase, instala y verifica:

| Herramienta | Comando para verificar | Debes ver |
|---|---|---|
| JDK 21 | `java -version` | `openjdk version "21..."` |
| Maven 3.9+ | `mvn -v` | `Apache Maven 3.9...` y `Java version: 21...` |
| Git | `git --version` | `git version 2...` |
| IntelliJ IDEA | — | Community o Ultimate |
| Docker Desktop | `docker --version` | Lo usamos desde el Módulo 2 |

> ¿`mvn -v` muestra otra versión de Java? Tu variable `JAVA_HOME` apunta a otro JDK: corrígela.

---

## Cómo usar este repositorio

### 1. Clona el repo del curso

```bash
git clone https://github.com/PaulNike/intevex-java-kafka.git
```

Cada sesión tiene su carpeta con el proyecto **tal como quedó al cerrar esa clase**. Antes de cada clase, actualiza con:

```bash
git pull
```

### 2. Crea tu propio repositorio

Tu código **no** va en este repo. Crea en tu cuenta de GitHub un repositorio público llamado `orders-domain` y copia ahí el proyecto de la sesión 1. Ese repo es tu proyecto durante todo el curso y, al final, tu portafolio.

### 3. Entrega tus tareas

Sube tus cambios a tu repo y entrega el link en la tarea correspondiente de **Google Classroom** antes de la fecha de entrega.

---

## Estructura

```
├── sesion-01/
│   ├── orders-domain/     ← proyecto al cierre de la sesión 1
│   ├── RETOS-PRO.md       ← retos extra para quienes ya dominan Java
│   └── TAREA-1.md
├── sesion-02/
│   ├── orders-domain/     ← parte de la sesión 1 y suma lo de la sesión 2
│   ├── RETOS-PRO.md
│   └── TAREA-2.md
└── soluciones/            ← se publican después de cada fecha de entrega
    └── sesion-01/
```

---

## Sesiones y entregas

| Sesión | Fecha | Tema | Tarea | Entrega |
|---|---|---|---|---|
| 1 | Sáb 14 nov | Arranque, Java 21 y Maven | [Tarea 1 · Inventario](sesion-01/TAREA-1.md) | Mié 18 nov, 11:59 p.m. |
| 2 | Sáb 21 nov | POO, records e interfaces | [Tarea 2 · Medios de pago](sesion-02/TAREA-2.md) | Se anuncia en Classroom |

*Esta tabla se actualiza cada semana.*

---

## Reglas del curso

- **Preguntas en el chat** de la clase o en el grupo de WhatsApp.
- **Tu código siempre en GitHub**, desde el primer día.
- **¿Trabado más de 15 minutos?** Pregunta. Nadie aprende solo frente a un error.
- **¿Ya dominas Java?** En cada sesión hay un archivo `RETOS-PRO.md` con trabajo extra para ti.

---

## Contacto

- 🌐 Web: [intevexacademy.com](https://www.intevexacademy.com)
- 📢 Canal de WhatsApp: [Intevex Academy](https://whatsapp.com/channel/0029VbDPTXIId7nM49doVr2G)

---

© 2026 Intevex Academy. Material de uso exclusivo para los alumnos del curso.
