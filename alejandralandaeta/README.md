# 📘 **README.md – Pruebas Unitarias TDD – Alejandra Landaeta**

# Pruebas Unitarias TDD - Alejandra Landaeta

Este paquete contiene **10 pruebas unitarias** desarrolladas siguiendo la metodología **TDD (Test-Driven Development)** con la estructura **Arrange-Act-Assert**.

## 📋 Estructura de las Pruebas

Cada prueba sigue el patrón de tres fases:

1. **PREPARACIÓN (Arrange)**: Configuración de datos y mocks necesarios  
2. **LÓGICA DE LA PRUEBA (Act)**: Ejecución del método a probar  
3. **VERIFICACIÓN CON ASSERT (Assert)**: Validación de resultados esperados  


## 📂 Archivos de Prueba

# **HistorialClinicoControllerTest.java (5 pruebas principales)**

Pruebas críticas para el controlador de historial clínico:

1. **deberiaObtenerListaVaciaParaMascotaSinHistorial**
   - 🐾 Caso base cuando no existen registros
   - Verifica que se retorne lista vacía
   - Valida estado HTTP 200
   - Confirma que el servicio fue invocado una sola vez

2. **deberiaCrearHistorialConRelacionACitaYValidarIntegridad**
   - 🔗 Validación de integridad relacional
   - Crea historial asociado a una cita
   - Valida retornos completos (mascota, cita, cliente)
   - Escenario clave para consistencia TDD

3. **deberiaObtenerHistorialPorClienteConMultiplesMascotas**
   - 👤 Filtrado por cliente con varias mascotas
   - Verifica la correcta agrupación de historiales
   - Valida coherencia de datos retornados

4. **deberiaActualizarHistorialCorrectamente**
   - ✏️ Escenario de actualización completa
   - Verifica diagnóstico, tratamiento, fecha y usuario
   - Confirma estado HTTP 200 y valores actualizados

5. **deberiaEliminarHistorialYRetornarNoContent**
   - ❌ Eliminación lógica
   - Valida estado HTTP 204 (No Content)
   - Confirma eliminación exitosa en el servicio

# **PermissionControllerTest.java (3 pruebas)**

Pruebas para el controlador de permisos del sistema:

1. **deberiaAgruparPermisosYContar**
   - 🔐 Verifica agrupación por módulo (CITA, SERVICIO, HISTORIAL)
   - Conteo correcto de permisos por categoría
   - Garantiza estructura consistente del sistema de roles

2. **deberiaValidarIntegridadDeIds**
   - 🧩 Control de integridad
   - IDs deben ser únicos, positivos y no nulos
   - Valida estructura base del sistema de permisos

3. **deberiaValidarNombresDePermisosUnicos**
   - 🔠 Validación estricta del formato
   - Nombres no repetidos, no vacíos
   - Criterio: MAYÚSCULAS_CON_GUIONES


# **ServicioControllerTest.java (2 pruebas)**

Pruebas para el catálogo de servicios veterinarios:

1. **deberiaFiltrarPorNombreConCoincidenciasParciales**
   - 🔎 Búsqueda flexible (case-insensitive)
   - Verifica coincidencias parciales
   - Asegura comportamiento robusto del filtro de búsqueda

2. **deberiaRetornarVacioSiNoHayServiciosEnRango**
   - 💸 Filtrado por precio
   - Cuando no existen servicios bajo el precio indicado → lista vacía
   - Valida estado HTTP 200


## 🎯 ¿Por qué estas 10 pruebas?

Las pruebas seleccionadas cubren:

- 🐾 **Operaciones esenciales del historial clínico**  
- 🔐 **Integridad y consistencia del sistema de permisos**  
- 💸 **Búsqueda y filtrado de servicios**  
- ✔️ Validaciones funcionales y de negocio  
- 🚫 Casos de vacíos, límites y datos no encontrados  

Estas pruebas cubren los escenarios más importantes y críticos del módulo veterinario.

## 🛠️ Tecnologías Utilizadas

- **JUnit 5**: Framework de pruebas unitarias  
- **Mockito**: Mocks y stubs  
- **Spring Boot Test**: Soporte para test con contexto Spring  
- **AssertJ** (implícito): Aserciones expresivas  

## ▶️ Ejecución de las Pruebas

### Desde Maven:
```bash
mvn test -Dtest="com.backend.vet.alejandralandaeta.*"
````

### Desde IDE:

* Click derecho en el paquete `alejandralandaeta`
* Seleccionar **Run Tests**

### Ejecutar una prueba específica:

```bash
mvn test -Dtest="ServicioControllerTest#deberiaFiltrarPorNombreConCoincidenciasParciales"
```

## ✅ Resultados

**Total de pruebas: 10**

* 🧪 HistorialClinicoControllerTest: 5 pruebas pasadas
* 🔐 PermissionControllerTest: 3 pruebas pasadas
* 💸 ServicioControllerTest: 2 pruebas pasadas

**Cobertura:**

* Historial clínico: Escenarios completos CRUD
* Permisos: Integridad, agrupación y formato
* Servicios: Búsquedas y filtros


## 📝 Buenas Prácticas Aplicadas

1. **Nombres descriptivos**: Cada prueba explica claramente qué valida
2. **Aislamiento**: Uso de mocks para independizar controladores
3. **Verificación completa**: Validación del cuerpo, estado HTTP e interacciones
4. **Setup centralizado**: Reutilización de datos con `@BeforeEach`
5. **Assertions múltiples**: Verificaciones detalladas por escenario
6. **Patrón AAA**: Pruebas limpias, legibles y predecibles


## 🔍 Cobertura de Escenarios

### Casos exitosos:

* ✔️ Creación de historial
* ✔️ Actualización
* ✔️ Eliminación
* ✔️ Búsqueda de servicios
* ✔️ Permisos agrupados correctamente

### Casos de error o límite:

* ❌ Listas vacías
* ❌ Filtros sin resultados
* ❌ Validaciones de IDs
* ❌ Nombres de permisos incorrectos



## 👤 Autor

**Alejandra Landaeta**

