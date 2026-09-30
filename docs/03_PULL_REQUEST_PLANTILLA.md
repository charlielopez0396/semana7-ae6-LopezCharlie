# Pull Request Ae6

## Objetivo

Implementar una suite de pruebas automatizadas para las reglas de negocio del sistema de reservas utilizando JUnit 5, aplicando el patrón AAA, Stub y Mocks, y analizando la cobertura mediante JaCoCo.

## Cambios realizados

- Se diseñó una matriz con 13 casos de prueba que contempla escenarios normales, alternativos, de límite, inválidos y de excepción.
- Se implementó la suite de pruebas con JUnit 5 siguiendo la estructura Arrange, Act, Assert (AAA).
- Se utilizaron Stub y Mocks con Mockito para controlar la disponibilidad y verificar las interacciones con el repositorio y el notificador.
- Se analizó la cobertura con JaCoCo y se incorporó CP-13 a partir de un comportamiento parcialmente cubierto detectado en la clase Reserva.
- Se documentó el análisis de cobertura antes y después de incorporar CP-13.

## Casos de prueba

La suite contempla:

- Cancelación con 5, 2, 1 y 0 horas de anticipación.
- Cálculo de total para clientes NORMAL, VIP y ESTUDIANTE.
- Validación de total base negativo.
- Validación del tipo VIP utilizando minúsculas.
- Confirmación de una reserva con horario disponible.
- Rechazo de una reserva cuando el horario no está disponible.
- Validación de una reserva nula.
- Validación de una reserva con identificador vacío.

## Cómo verificar

```bash
mvn clean test
```

Resultado obtenido:

- Pruebas ejecutadas: 13
- Fallos: 0
- Errores: 0
- Omitidas: 0
- Resultado: BUILD SUCCESS

El reporte de JaCoCo se genera en:

`target/site/jacoco/index.html`

## Cobertura

Cobertura final obtenida con JaCoCo:

- Total del proyecto: 91% de instrucciones y 88% de ramas.
- `edu.uees.testing.service`: 100% de instrucciones y 100% de ramas.
- `edu.uees.testing.domain`: 81% de instrucciones y 66% de ramas.

El análisis inicial mostró 87% de cobertura de instrucciones y 83% de ramas. A partir de este resultado se identificó como comportamiento relevante la validación del identificador de `Reserva`, por lo que se incorporó CP-13. Después de esta prueba, la cobertura aumentó a 91% de instrucciones y 88% de ramas.

La cobertura se utilizó para detectar comportamientos no ejercitados y no únicamente como una métrica que debía alcanzar el 100%.

## Limitaciones

La clase `Reserva` todavía contiene elementos no completamente cubiertos, entre ellos `cancelar()`, `getId()` y `getTipo()`, además de ramas adicionales del constructor. No se añadieron pruebas únicamente para incrementar artificialmente el porcentaje de cobertura, priorizando los comportamientos relevantes definidos en la matriz.

## Autorrevisión

- [x] Compila
- [x] Pruebas en verde
- [x] Sin archivos accidentales
- [x] Commits descriptivos
- [x] Documentación actualizada

La revisión confirma que los casos implementados corresponden con la matriz, que las pruebas verifican resultados e interacciones relevantes y que el historial Git permite identificar incrementalmente el trabajo realizado.

## Uso de IA

Se utilizaron herramientas de inteligencia artificial como apoyo para revisar la estructura de la actividad, orientar la organización de la matriz de casos, revisar la implementación de las pruebas y apoyar la redacción de la documentación. Las decisiones, ejecución de comandos, validación de resultados y revisión final del proyecto fueron realizadas por el estudiante.