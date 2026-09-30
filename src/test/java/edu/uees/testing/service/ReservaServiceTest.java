package edu.uees.testing.service;

import org.junit.jupiter.api.Test;

import edu.uees.testing.availability.DisponibilidadClient;
import edu.uees.testing.domain.EstadoReserva;
import edu.uees.testing.domain.Reserva;
import edu.uees.testing.notification.Notificador;
import edu.uees.testing.repository.ReservaRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

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

    @Test
    void reservaDisponibleSeConfirmaGuardaYNotifica() {
        // Arrange
        DisponibilidadClient disponibilidad = mock(DisponibilidadClient.class);
        ReservaRepository repository = mock(ReservaRepository.class);
        Notificador notificador = mock(Notificador.class);

        ReservaService service = new ReservaService(disponibilidad, repository, notificador);

        Reserva reserva = new Reserva("R-001", "NORMAL");

        when(disponibilidad.estaDisponible(reserva)).thenReturn(true);

        // Act
        service.confirmar(reserva);

        // Assert
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        verify(disponibilidad).estaDisponible(reserva);
        verify(repository).guardar(reserva);
        verify(notificador).enviarConfirmacion(reserva);
    }

    @Test
    void reservaNoDisponibleLanzaExcepcionYNoGeneraEfectos() {
        // Arrange
        DisponibilidadClient disponibilidad = mock(DisponibilidadClient.class);
        ReservaRepository repository = mock(ReservaRepository.class);
        Notificador notificador = mock(Notificador.class);

        ReservaService service = new ReservaService(disponibilidad, repository, notificador);

        Reserva reserva = new Reserva("R-002", "NORMAL");

        when(disponibilidad.estaDisponible(reserva)).thenReturn(false);

        // Act
        assertThrows(
                IllegalStateException.class,
                () -> service.confirmar(reserva));

        // Assert
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
        verify(disponibilidad).estaDisponible(reserva);
        verify(repository, never()).guardar(reserva);
        verify(notificador, never()).enviarConfirmacion(reserva);
    }

    @Test
    void reservaNulaLanzaExcepcionSinConsultarDependencias() {
        // Arrange
        DisponibilidadClient disponibilidad = mock(DisponibilidadClient.class);
        ReservaRepository repository = mock(ReservaRepository.class);
        Notificador notificador = mock(Notificador.class);

        ReservaService service = new ReservaService(disponibilidad, repository, notificador);

        // Act
        assertThrows(
                IllegalArgumentException.class,
                () -> service.confirmar(null));

        // Assert
        verifyNoInteractions(disponibilidad, repository, notificador);
    }

    @Test
    void reservaConIdVacioLanzaExcepcion() {
        // Arrange
        String id = "";
        String tipo = "NORMAL";

        // Act
        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> new Reserva(id, tipo));

        // Assert
        assertEquals("Id obligatorio", excepcion.getMessage());
    }

}