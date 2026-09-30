# Matriz de casos

| ID | Regla | Escenario | Entrada | Esperado | Tipo | Riesgo |
|---|---|---|---|---|---|---|
| CP-01 | Cancelación | Anticipación normal | 5 horas | true | Normal | Verifica la regla general de cancelación con anticipación suficiente. |
| CP-02 | Cancelación | Límite válido | 2 horas | true | Límite | Protege el límite exacto permitido por la regla >= 2. |
| CP-03 | Cancelación | Debajo del límite | 1 hora | false | Límite | Detecta errores en la frontera inferior de cancelación. |
| CP-04 | Cancelación | Sin anticipación | 0 horas | false | Inválido/Límite | Evita permitir una cancelación sin anticipación. |
| CP-05 | Descuento | Cliente NORMAL | NORMAL, 100 | 100.00 | Normal | Verifica que un cliente normal conserve el total base. |
| CP-06 | Descuento | Cliente VIP | VIP, 100 | 85.00 | Alternativo | Verifica la aplicación correcta del 15% de descuento VIP. |
| CP-07 | Descuento | Cliente ESTUDIANTE | ESTUDIANTE, 100 | 90.00 | Alternativo | Verifica la aplicación correcta del 10% de descuento para estudiante. |
| CP-08 | Descuento | Total negativo | NORMAL, -1 | IllegalArgumentException | Inválido/Excepción | Evita procesar un total base negativo. |
| CP-09 | Confirmación | Horario disponible | Reserva + disponibilidad true | CONFIRMADA, guardar y notificar | Normal | Verifica el flujo completo de una confirmación válida. |
| CP-10 | Confirmación | Horario no disponible | Reserva + disponibilidad false | IllegalStateException | Alternativo/Excepción | Evita confirmar una reserva cuando el horario no está disponible. |
| CP-11 | Confirmación | Reserva nula | null | IllegalArgumentException | Inválido/Excepción | Evita procesar una confirmación sin reserva. |
| CP-12 | Descuento | Tipo VIP en minúsculas | vip, 100 | 85.00 | Alternativo | Verifica que el tipo de cliente no dependa de mayúsculas o minúsculas. |
| CP-13 | Reserva | Identificador vacío | id vacío, tipo NORMAL | IllegalArgumentException | Inválido/Excepción | Verifica la validación del constructor detectada como cobertura parcial mediante JaCoCo. |