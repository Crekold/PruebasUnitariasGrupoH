package com.backend.vet.ignaciotapia;

import com.backend.vet.controller.RoleController;
import com.backend.vet.dto.PermissionDto;
import com.backend.vet.dto.RoleDto;
import com.backend.vet.service.RoleService;
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
import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias TDD para RoleController
 * Estructura: Arrange-Act-Assert
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para RoleController")
class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    private RoleDto roleDto;
    private PermissionDto permissionDto;

    @BeforeEach
    void setUp() {
        roleDto = new RoleDto();
        roleDto.setId(1L);
        roleDto.setNombre("VETERINARIO");
        roleDto.setDescripcion("Rol de veterinario");

        permissionDto = new PermissionDto();
        permissionDto.setId(1L);
        permissionDto.setNombre("USUARIO_READ");
    }

    @Test
    @DisplayName("debería crear rol exitosamente")
    void deberiaCrearRolExitosamente() {
        // 1. PREPARACIÓN
        when(roleService.createRole(any(RoleDto.class))).thenReturn(roleDto);

        // 2. LÓGICA DE LA PRUEBA
        ResponseEntity<RoleDto> response = roleController.createRole(roleDto);

        // 3. VERIFICACIÓN CON ASSERT
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("VETERINARIO", response.getBody().getNombre());
        verify(roleService, times(1)).createRole(any(RoleDto.class));
    }

    @Test
    @DisplayName("debería obtener permisos de un rol exitosamente")
    void deberiaObtenerPermisosDeRolExitosamente() {
        // 1. PREPARACIÓN
        List<PermissionDto> permisos = Arrays.asList(permissionDto);
        when(roleService.getPermissionsByRole(1L)).thenReturn(permisos);

        // 2. LÓGICA DE LA PRUEBA
        ResponseEntity<List<PermissionDto>> response = roleController.getPermissionsByRole(1L);

        // 3. VERIFICACIÓN CON ASSERT
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("USUARIO_READ", response.getBody().get(0).getNombre());
        verify(roleService, times(1)).getPermissionsByRole(1L);
    }

    @Test
    @DisplayName("debería actualizar permisos de rol exitosamente")
    void deberiaActualizarPermisosDeRolExitosamente() {
        // 1. PREPARACIÓN
        Set<Long> permissionIds = new HashSet<>(Arrays.asList(1L, 2L));
        List<PermissionDto> permisosActualizados = Arrays.asList(permissionDto, new PermissionDto());
        
        when(roleService.updatePermissions(eq(1L), any(Set.class)))
                .thenReturn(permisosActualizados);

        // 2. LÓGICA DE LA PRUEBA
        ResponseEntity<List<PermissionDto>> response = 
                roleController.updatePermissions(1L, permissionIds);

        // 3. VERIFICACIÓN CON ASSERT
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(roleService, times(1)).updatePermissions(eq(1L), any(Set.class));
    }
}