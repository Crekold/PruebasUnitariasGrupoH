package com.backend.vet.ignaciotapia;

import com.backend.vet.controller.StatsController;
import com.backend.vet.service.CitaService;
import com.backend.vet.service.HistorialClinicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias TDD para StatsController
 * Estructura: Arrange-Act-Assert
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para StatsController")
class StatsControllerTest {

    @Mock
    private HistorialClinicoService historialClinicoService;

    @Mock
    private CitaService citaService;

    @InjectMocks
    private StatsController statsController;

    @Test
    @DisplayName("debería obtener estadísticas del dashboard exitosamente")
    void deberiaObtenerEstadisticasDashboardExitosamente() {
        // 1. PREPARACIÓN
        when(historialClinicoService.countPacientesAtendidos()).thenReturn(25L);
        when(citaService.countCitasDelDia()).thenReturn(8L);
        when(historialClinicoService.countVacunasAplicadasHoy()).thenReturn(12L);

        // 2. LÓGICA DE LA PRUEBA
        ResponseEntity<Map<String, Object>> response = statsController.getDashboardStats();

        // 3. VERIFICACIÓN CON ASSERT
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        
        Map<String, Object> stats = response.getBody();
        assertEquals(25L, stats.get("pacientesAtendidos"));
        assertEquals(8L, stats.get("citasDelDia"));
        assertEquals(12L, stats.get("vacunasAplicadas"));
        
        verify(historialClinicoService, times(1)).countPacientesAtendidos();
        verify(citaService, times(1)).countCitasDelDia();
        verify(historialClinicoService, times(1)).countVacunasAplicadasHoy();
    }

    @Test
    @DisplayName("debería retornar estadísticas con valores en cero cuando no hay datos")
    void deberiaRetornarEstadisticasConValoresEnCeroCuandoNoHayDatos() {
        // 1. PREPARACIÓN
        when(historialClinicoService.countPacientesAtendidos()).thenReturn(0L);
        when(citaService.countCitasDelDia()).thenReturn(0L);
        when(historialClinicoService.countVacunasAplicadasHoy()).thenReturn(0L);

        // 2. LÓGICA DE LA PRUEBA
        ResponseEntity<Map<String, Object>> response = statsController.getDashboardStats();

        // 3. VERIFICACIÓN CON ASSERT
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        
        Map<String, Object> stats = response.getBody();
        assertEquals(0L, stats.get("pacientesAtendidos"));
        assertEquals(0L, stats.get("citasDelDia"));
        assertEquals(0L, stats.get("vacunasAplicadas"));
        
        verify(historialClinicoService, times(1)).countPacientesAtendidos();
        verify(citaService, times(1)).countCitasDelDia();
        verify(historialClinicoService, times(1)).countVacunasAplicadasHoy();
    }

    @Test
    @DisplayName("debería retornar todas las claves esperadas en el mapa de estadísticas")
    void deberiaRetornarTodasLasClavesEsperadasEnMapaEstadisticas() {
        // 1. PREPARACIÓN
        when(historialClinicoService.countPacientesAtendidos()).thenReturn(10L);
        when(citaService.countCitasDelDia()).thenReturn(5L);
        when(historialClinicoService.countVacunasAplicadasHoy()).thenReturn(3L);

        // 2. LÓGICA DE LA PRUEBA
        ResponseEntity<Map<String, Object>> response = statsController.getDashboardStats();

        // 3. VERIFICACIÓN CON ASSERT
        assertNotNull(response);
        Map<String, Object> stats = response.getBody();
        assertNotNull(stats);
        
        assertTrue(stats.containsKey("pacientesAtendidos"));
        assertTrue(stats.containsKey("citasDelDia"));
        assertTrue(stats.containsKey("vacunasAplicadas"));
        assertEquals(3, stats.size());
    }
}
