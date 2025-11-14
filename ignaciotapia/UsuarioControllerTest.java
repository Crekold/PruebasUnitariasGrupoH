package com.backend.vet.ignaciotapia;

import com.backend.vet.controller.UsuarioController;
import com.backend.vet.dto.UsuarioDto;
import com.backend.vet.dto.UsuarioUpdateDto;
import com.backend.vet.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias TDD para UsuarioController
 * Estructura: Arrange-Act-Assert
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para UsuarioController")
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private UsuarioDto usuarioDto;
    private UsuarioUpdateDto usuarioUpdateDto;

    @BeforeEach
    void setUp() {
        usuarioDto = new UsuarioDto();
        usuarioDto.setId(1L);
        usuarioDto.setNombreUsuario("veterinario1");
        usuarioDto.setCorreo("vet1@test.com");
        usuarioDto.setRolNombre("VETERINARIO");
        usuarioDto.setActivo(true);

        usuarioUpdateDto = new UsuarioUpdateDto();
        usuarioUpdateDto.setCorreo("vet1_updated@test.com");
        usuarioUpdateDto.setNombre("Veterinario");
        usuarioUpdateDto.setApellido("Actualizado");
    }

    @Test
    @DisplayName("debería obtener todos los usuarios exitosamente")
    void deberiaObtenerTodosLosUsuariosExitosamente() {
        // 1. PREPARACIÓN
        List<UsuarioDto> usuarios = Arrays.asList(usuarioDto, new UsuarioDto());
        when(usuarioService.getAllUsuarios()).thenReturn(usuarios);

        // 2. LÓGICA DE LA PRUEBA
        ResponseEntity<List<UsuarioDto>> response = usuarioController.getAllUsuarios();

        // 3. VERIFICACIÓN CON ASSERT
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(usuarioService, times(1)).getAllUsuarios();
    }

    @Test
    @DisplayName("debería obtener usuario por ID cuando existe")
    void deberiaObtenerUsuarioPorIdCuandoExiste() {
        // 1. PREPARACIÓN
        when(usuarioService.getUsuarioById(1L)).thenReturn(usuarioDto);

        // 2. LÓGICA DE LA PRUEBA
        ResponseEntity<UsuarioDto> response = usuarioController.getUsuarioById(1L);

        // 3. VERIFICACIÓN CON ASSERT
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("veterinario1", response.getBody().getNombreUsuario());
        verify(usuarioService, times(1)).getUsuarioById(1L);
    }

    @Test
    @DisplayName("debería retornar 404 cuando usuario no existe")
    void deberiaRetornar404CuandoUsuarioNoExiste() {
        // 1. PREPARACIÓN
        when(usuarioService.getUsuarioById(999L)).thenReturn(null);

        // 2. LÓGICA DE LA PRUEBA
        ResponseEntity<UsuarioDto> response = usuarioController.getUsuarioById(999L);

        // 3. VERIFICACIÓN CON ASSERT
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(usuarioService, times(1)).getUsuarioById(999L);
    }

    @Test
    @DisplayName("debería actualizar usuario exitosamente")
    void deberiaActualizarUsuarioExitosamente() {
        // 1. PREPARACIÓN
        UsuarioDto usuarioActualizado = new UsuarioDto();
        usuarioActualizado.setId(1L);
        usuarioActualizado.setCorreo("vet1_updated@test.com");
        
        when(usuarioService.updateUsuario(eq(1L), any(UsuarioUpdateDto.class)))
                .thenReturn(usuarioActualizado);

        // 2. LÓGICA DE LA PRUEBA
        ResponseEntity<UsuarioDto> response = usuarioController.updateUsuario(1L, usuarioUpdateDto);

        // 3. VERIFICACIÓN CON ASSERT
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("vet1_updated@test.com", response.getBody().getCorreo());
        verify(usuarioService, times(1)).updateUsuario(eq(1L), any(UsuarioUpdateDto.class));
    }
}
