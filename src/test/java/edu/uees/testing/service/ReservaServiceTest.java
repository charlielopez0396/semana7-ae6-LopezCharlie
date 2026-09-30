package edu.uees.testing.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservaServiceTest {

    @Test
    void cincoHorasPermitenCancelar() {
        // Arrange
        ReservaService service =
                new ReservaService(null, null, null);

        int horasAnticipacion = 5;

        // Act
        boolean resultado =
                service.puedeCancelar(horasAnticipacion);

        // Assert
        assertTrue(resultado);
    }

    @Test
    void dosHorasPermitenCancelar() {
        // Arrange
        ReservaService service =
                new ReservaService(null, null, null);

        int horasAnticipacion = 2;

        // Act
        boolean resultado =
                service.puedeCancelar(horasAnticipacion);

        // Assert
        assertTrue(resultado);
    }

    @Test
    void unaHoraNoPermiteCancelar() {
        // Arrange
        ReservaService service =
                new ReservaService(null, null, null);

        int horasAnticipacion = 1;

        // Act
        boolean resultado =
                service.puedeCancelar(horasAnticipacion);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void ceroHorasNoPermitenCancelar() {
        // Arrange
        ReservaService service =
                new ReservaService(null, null, null);

        int horasAnticipacion = 0;

        // Act
        boolean resultado =
                service.puedeCancelar(horasAnticipacion);

        // Assert
        assertFalse(resultado);
    }
}