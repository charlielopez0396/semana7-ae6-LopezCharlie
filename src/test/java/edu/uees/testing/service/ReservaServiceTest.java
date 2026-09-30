package edu.uees.testing.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservaServiceTest {

    @Test
    void cincoHorasPermitenCancelar() {
        // Arrange
        ReservaService service = new ReservaService(null, null, null);

        int horasAnticipacion = 5;

        // Act
        boolean resultado = service.puedeCancelar(horasAnticipacion);

        // Assert
        assertTrue(resultado);
    }

    @Test
    void dosHorasPermitenCancelar() {
        // Arrange
        ReservaService service = new ReservaService(null, null, null);

        int horasAnticipacion = 2;

        // Act
        boolean resultado = service.puedeCancelar(horasAnticipacion);

        // Assert
        assertTrue(resultado);
    }

    @Test
    void unaHoraNoPermiteCancelar() {
        // Arrange
        ReservaService service = new ReservaService(null, null, null);

        int horasAnticipacion = 1;

        // Act
        boolean resultado = service.puedeCancelar(horasAnticipacion);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void ceroHorasNoPermitenCancelar() {
        // Arrange
        ReservaService service = new ReservaService(null, null, null);

        int horasAnticipacion = 0;

        // Act
        boolean resultado = service.puedeCancelar(horasAnticipacion);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void clienteNormalMantieneTotalBase() {
        // Arrange
        ReservaService service = new ReservaService(null, null, null);

        String tipo = "NORMAL";
        double totalBase = 100.0;

        // Act
        double resultado = service.calcularTotal(tipo, totalBase);

        // Assert
        assertEquals(100.0, resultado, 0.001);
    }

    @Test
    void clienteVipRecibeQuincePorCientoDescuento() {
        // Arrange
        ReservaService service = new ReservaService(null, null, null);

        String tipo = "VIP";
        double totalBase = 100.0;

        // Act
        double resultado = service.calcularTotal(tipo, totalBase);

        // Assert
        assertEquals(85.0, resultado, 0.001);
    }

    @Test
    void estudianteRecibeDiezPorCientoDescuento() {
        // Arrange
        ReservaService service = new ReservaService(null, null, null);

        String tipo = "ESTUDIANTE";
        double totalBase = 100.0;

        // Act
        double resultado = service.calcularTotal(tipo, totalBase);

        // Assert
        assertEquals(90.0, resultado, 0.001);
    }

    @Test
    void totalBaseNegativoLanzaExcepcion() {
        // Arrange
        ReservaService service = new ReservaService(null, null, null);

        double totalBase = -1.0;

        // Act
        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> service.calcularTotal("NORMAL", totalBase));

        // Assert
        assertEquals("Total base inválido", excepcion.getMessage());
    }

    @Test
    void tipoVipEnMinusculasAplicaDescuento() {
        // Arrange
        ReservaService service = new ReservaService(null, null, null);

        String tipo = "vip";
        double totalBase = 100.0;

        // Act
        double resultado = service.calcularTotal(tipo, totalBase);

        // Assert
        assertEquals(85.0, resultado, 0.001);
    }

}