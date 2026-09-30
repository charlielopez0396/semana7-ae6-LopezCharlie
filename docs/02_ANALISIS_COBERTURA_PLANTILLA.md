# Análisis de cobertura JaCoCo

## 1. Ejecución de la suite

La suite de pruebas se ejecutó mediante:

`mvn clean test`

La ejecución final obtuvo:

- Pruebas ejecutadas: 13
- Fallos: 0
- Errores: 0
- Omitidas: 0
- Resultado: BUILD SUCCESS

## 2. Cobertura inicial

Después de implementar la suite inicial de 12 pruebas, JaCoCo reportó:

| Elemento | Instrucciones | Ramas |
|---|---:|---:|
| edu.uees.testing.domain | 73% | 50% |
| edu.uees.testing.service | 100% | 100% |
| Total del proyecto | 87% | 83% |

El servicio ReservaService alcanzó cobertura completa de instrucciones y ramas. Sin embargo, el paquete domain presentó una cobertura menor.

Al revisar la clase Reserva se observó:

- 60% de cobertura de instrucciones.
- 50% de cobertura de ramas.
- El constructor Reserva(String, String) presentaba cobertura parcial.
- Los métodos cancelar(), getId() y getTipo() no habían sido ejecutados por la suite inicial.

## 3. Comportamiento relevante identificado

El análisis mostró que el constructor de Reserva contiene una validación para impedir la creación de una reserva con un identificador nulo o vacío.

Esta validación es relevante porque protege la creación de objetos Reserva inválidos. Por este motivo se decidió priorizar esta regla antes que agregar pruebas únicamente destinadas a ejecutar getters y aumentar el porcentaje de cobertura.

## 4. Prueba incorporada a partir del análisis

Se incorporó el caso CP-13:

**Reserva con identificador vacío**

Entrada:

- id = ""
- tipo = "NORMAL"

Resultado esperado:

- IllegalArgumentException con el mensaje "Id obligatorio".

La prueba verifica explícitamente que una reserva no pueda crearse con un identificador vacío.

## 5. Cobertura posterior

Después de incorporar CP-13 y ejecutar nuevamente `mvn clean test`, JaCoCo reportó:

| Elemento | Instrucciones | Ramas |
|---|---:|---:|
| edu.uees.testing.domain | 81% | 66% |
| edu.uees.testing.service | 100% | 100% |
| Total del proyecto | 91% | 88% |

La cobertura total de instrucciones aumentó de 87% a 91%, mientras que la cobertura de ramas aumentó de 83% a 88%.

En el paquete domain, las instrucciones aumentaron de 73% a 81% y las ramas de 50% a 66%.

## 6. Interpretación

El incremento de cobertura confirma que CP-13 ejercita comportamiento que no estaba completamente recorrido por la suite inicial.

Sin embargo, una cobertura alta no garantiza por sí sola que el software sea correcto. JaCoCo permite conocer qué código fue ejecutado durante las pruebas, pero no demuestra que todas las reglas de negocio estén correctamente definidas ni que las aserciones utilizadas sean suficientes.

Por esta razón, la calidad de la suite se sustentó también en la matriz de casos, escenarios normales, alternativos, de límite, inválidos y de excepción, además de verificaciones de estado e interacciones con dependencias.

## 7. Cobertura pendiente

Después de CP-13 todavía existen elementos de Reserva que no están completamente cubiertos, entre ellos cancelar(), getId() y getTipo(), además de ramas adicionales del constructor.

No se agregaron pruebas únicamente para alcanzar 100% de cobertura, ya que el criterio utilizado fue priorizar comportamientos relevantes y verificables. La cobertura se utilizó como una herramienta para detectar vacíos, no como un objetivo aislado.