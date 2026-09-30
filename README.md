# UEES UCOM0310 — Semana 7 — Ae6

Proyecto individual correspondiente a la actividad Ae6: Suite de pruebas, cobertura y Pull Request documentado.

## Objetivo

Diseñar e implementar una suite de pruebas automatizadas para las reglas de negocio del sistema de reservas utilizando JUnit 5, aplicando el patrón AAA, Stub y Mocks, y utilizando JaCoCo para analizar la cobertura obtenida.

## Requisitos

- Java 21
- Maven 3.9+
- Git

## Ejecutar las pruebas

Desde la raíz del proyecto:

```bash
mvn clean test
```

Resultado final obtenido:

- Pruebas ejecutadas: 13
- Fallos: 0
- Errores: 0
- Omitidas: 0
- Resultado: BUILD SUCCESS

## Casos de prueba

La matriz contiene 13 casos que cubren:

- Cancelación con escenarios normales y valores límite.
- Descuentos para clientes NORMAL, VIP y ESTUDIANTE.
- Validación de total base negativo.
- Tratamiento del tipo VIP sin distinción entre mayúsculas y minúsculas.
- Confirmación con disponibilidad.
- Confirmación sin disponibilidad.
- Validación de reserva nula.
- Validación de identificador vacío detectada a partir del análisis de cobertura.

La matriz completa se encuentra en:

`docs/01_MATRIZ_CASOS_PLANTILLA.md`

## Cobertura con JaCoCo

La cobertura se genera automáticamente al ejecutar:

```bash
mvn clean test
```

Luego se puede abrir el reporte:

`target/site/jacoco/index.html`

Cobertura final:

- Total del proyecto: 91% de instrucciones y 88% de ramas.
- `edu.uees.testing.service`: 100% de instrucciones y 100% de ramas.
- `edu.uees.testing.domain`: 81% de instrucciones y 66% de ramas.

El análisis detallado se encuentra en:

`docs/02_ANALISIS_COBERTURA_PLANTILLA.md`

## Stub y Mocks

`DisponibilidadClient` se utiliza como Stub al controlar su respuesta mediante Mockito para representar disponibilidad o falta de disponibilidad.

`ReservaRepository` y `Notificador` se utilizan como Mocks para verificar que las interacciones esperadas ocurran durante una confirmación válida y que no existan efectos secundarios en los escenarios de error.

## Flujo Git

Rama utilizada para el desarrollo de Ae6:

`ae6/suite-pruebas`

El trabajo se registró mediante commits incrementales para mantener la trazabilidad de la matriz, las pruebas, el análisis de cobertura y la documentación.

## Pull Request

El Pull Request integra:

`ae6/suite-pruebas` → `main`

La descripción preparada para el Pull Request se encuentra en:

`docs/03_PULL_REQUEST_PLANTILLA.md`

Pull Request: https://github.com/charlielopez0396/semana7-ae6-LopezCharlie/pull/1

## Regla de trabajo

No se modificó el código productivo únicamente para hacer pasar una prueba. El proceso seguido fue diseñar los casos, implementar las pruebas, ejecutar la suite y analizar posteriormente la cobertura obtenida.

## Uso de IA

Se utilizaron herramientas de inteligencia artificial como apoyo para revisar la estructura de la actividad, orientar la organización de la matriz de casos, revisar la implementación de las pruebas y apoyar la redacción de la documentación. Las decisiones, ejecución de comandos, validación de resultados y revisión final del proyecto fueron realizadas por el estudiante.