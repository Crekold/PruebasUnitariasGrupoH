# Pruebas Unitarias TDD - Ignacio Tapia

Este paquete contiene **10 pruebas unitarias** desarrolladas siguiendo la metodología **TDD (Test-Driven Development)** con la estructura **Arrange-Act-Assert**.

## 📋 Estructura de las Pruebas

Cada prueba sigue el patrón de tres fases:

1. **PREPARACIÓN (Arrange)**: Configuración de datos y mocks necesarios
2. **LÓGICA DE LA PRUEBA (Act)**: Ejecución del método a probar
3. **VERIFICACIÓN CON ASSERT (Assert)**: Validación de resultados esperados

## 📂 Archivos de Prueba

### UsuarioControllerTest.java (4 pruebas)

Pruebas para el controlador de gestión de usuarios:

1. **deberiaObtenerTodosLosUsuariosExitosamente**
   - Verifica la obtención de lista completa de usuarios
   - Valida el código de estado HTTP 200
   - Confirma que se retornan todos los usuarios registrados
   - Verifica la interacción correcta con el servicio

2. **deberiaObtenerUsuarioPorIdCuandoExiste**
   - Verifica la búsqueda de usuario por ID existente
   - Valida el código de estado HTTP 200
   - Confirma que se retornan los datos correctos del usuario
   - Valida el nombre de usuario correcto

3. **deberiaRetornar404CuandoUsuarioNoExiste**
   - Verifica el manejo de usuarios inexistentes
   - Valida el código de estado HTTP 404 (Not Found)
   - Confirma que se maneja correctamente la ausencia de datos
   - Previene errores de referencia nula

4. **deberiaActualizarUsuarioExitosamente**
   - Verifica la actualización de datos de usuario
   - Valida el código de estado HTTP 200
   - Confirma que los cambios se aplican correctamente
   - Valida la actualización del correo electrónico

### RoleControllerTest.java (3 pruebas)

Pruebas para el controlador de roles y permisos:

1. **deberiaCrearRolExitosamente**
   - Verifica la creación de nuevos roles
   - Valida el código de estado HTTP 201 (Created)
   - Confirma que el rol se guarda con nombre y descripción
   - Valida la asignación correcta de propiedades

2. **deberiaObtenerPermisosDeRolExitosamente**
   - Verifica la obtención de permisos asociados a un rol
   - Valida el código de estado HTTP 200
   - Confirma que se retornan los permisos correctos
   - Valida la estructura de los permisos

3. **deberiaActualizarPermisosDeRolExitosamente**
   - Verifica la actualización de permisos de un rol
   - Valida el código de estado HTTP 200
   - Confirma que se actualizan múltiples permisos
   - Valida la persistencia de cambios

### StatsControllerTest.java (3 pruebas)

Pruebas para el controlador de estadísticas del dashboard:

1. **deberiaObtenerEstadisticasDashboardExitosamente**
   - Verifica la obtención de estadísticas completas
   - Valida el código de estado HTTP 200
   - Confirma que se retornan pacientes atendidos, citas del día y vacunas aplicadas
   - Valida la estructura del mapa de estadísticas

2. **deberiaRetornarEstadisticasConValoresEnCeroCuandoNoHayDatos**
   - Verifica el manejo de casos sin datos
   - Valida que se retornen valores en cero correctamente
   - Confirma el comportamiento con sistema vacío
   - Previene errores de valores nulos

3. **deberiaRetornarTodasLasClavesEsperadasEnMapaEstadisticas**
   - Verifica la estructura completa del mapa de respuesta
   - Valida que existan todas las claves requeridas
   - Confirma el tamaño correcto del mapa (3 claves)
   - Asegura la consistencia de la API

## 🎯 Cobertura de Funcionalidades

Las pruebas cubren:
- ✅ **Gestión de usuarios** (CRUD y búsquedas)
- 🔐 **Control de roles y permisos** (creación y actualización)
- 📊 **Estadísticas del sistema** (dashboard y métricas)
- ❌ **Manejo de errores** (recursos no encontrados)
- 📋 **Validación de datos** (estructura y contenido)

## 🛠️ Tecnologías Utilizadas

- **JUnit 5**: Framework de pruebas unitarias
- **Mockito**: Framework para crear mocks y stubs
- **Spring Boot Test**: Soporte para pruebas en Spring Boot
- **MockitoExtension**: Integración JUnit 5 con Mockito

## ▶️ Ejecución de las Pruebas

### Desde Maven:
```bash
mvn test -Dtest="com.backend.vet.ignaciotapia.*"
```

### Desde IDE:
- Click derecho en el paquete `ignaciotapia`
- Seleccionar "Run Tests"

### Ejecutar una prueba específica:
```bash
mvn test -Dtest="UsuarioControllerTest#deberiaObtenerTodosLosUsuariosExitosamente"
```

## ✅ Resultados

**Total de pruebas: 10**
- ✅ UsuarioControllerTest: 4 pruebas pasadas
- ✅ RoleControllerTest: 3 pruebas pasadas
- ✅ StatsControllerTest: 3 pruebas pasadas

**Cobertura:**
- UsuarioController: Operaciones CRUD y búsquedas
- RoleController: Gestión de roles y permisos
- StatsController: Estadísticas del dashboard

## 📝 Buenas Prácticas Aplicadas

1. **Nombres descriptivos**: Cada prueba describe claramente qué verifica
2. **Aislamiento**: Uso de mocks para aislar la unidad bajo prueba
3. **Verificación completa**: Se verifican tanto el resultado como las interacciones
4. **Setup centralizado**: Uso de `@BeforeEach` para configuración común
5. **Assertions múltiples**: Se verifica exhaustivamente cada escenario
6. **Estados HTTP correctos**: Validación de códigos de respuesta apropiados
7. **DisplayName**: Uso de anotaciones para mejorar la legibilidad

## 🔍 Cobertura de Escenarios

### Casos exitosos:
- ✅ Obtención de listas completas
- ✅ Búsqueda por ID
- ✅ Creación de recursos (roles)
- ✅ Actualización de datos (usuarios y permisos)
- ✅ Consulta de estadísticas

### Casos de error:
- ❌ Recursos no encontrados (404)
- ❌ Usuarios inexistentes

### Casos límite:
- 📊 Estadísticas con valores en cero
- 📋 Validación de estructura de datos
- 🔍 Verificación de claves esperadas en respuestas

## 📊 Distribución de Pruebas por Tipo

### Operaciones CRUD:
- 📖 Read (GET): 5 pruebas
- ✏️ Update (PUT): 2 pruebas
- ➕ Create (POST): 1 prueba

### Validaciones:
- ✅ Códigos HTTP: 10 pruebas
- ✅ Estructura de datos: 3 pruebas
- ✅ Interacciones con servicios: 10 pruebas

## 👤 Autor

**Ignacio Tapia**

---

*Pruebas desarrolladas siguiendo metodología TDD y principios SOLID*
