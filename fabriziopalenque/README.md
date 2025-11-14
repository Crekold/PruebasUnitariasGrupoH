# Pruebas Unitarias - Paquete fabriziopalenque

Este README documenta las pruebas unitarias dentro del paquete `fabriziopalenque`. Está estructurado siguiendo el ejemplo de referencia: descripción general, archivo por archivo, instrucciones de ejecución y buenas prácticas.

## Resumen rápido
- Marco de pruebas: JUnit 5 + Mockito (tests unitarios sobre controladores)
- Archivos de prueba en el paquete:
  - ClienteControllerTest.java
  - MascotaControllerTest.java
  - ServicioControllerTest.java
- Total aproximado de pruebas: 10 (5 Cliente, 3 Mascota, 2 Servicio)

## Estructura y patrón usado
Todas las pruebas siguen el patrón Arrange — Act — Assert:
1. PREPARACIÓN (Arrange): creación de DTOs/entidades y configuración de mocks.
2. EJECUCIÓN (Act): llamada al método del controlador (a veces vía MockMvc, a veces directa).
3. VERIFICACIÓN (Assert): comprobación de código HTTP, cuerpos de respuesta y/o interacciones con servicios mockeados.

---

## Detalle por archivo

### ClienteControllerTest.java
Descripción: pruebas enfocadas en las operaciones CRUD del controlador de clientes y manejo de errores.

Pruebas principales (nombres representativos y propósito):
- deberiaListarTodosLosClientes  
  - Valida que se retorne la lista completa de clientes y código HTTP 200.
- deberiaObtenerClientePorId  
  - Verifica la obtención correcta de un cliente por su ID (200) y campos devueltos.
- deberiaDevolver404CuandoClienteNoExiste  
  - Simula que el servicio retorna null; el controlador debe responder 404.
- deberiaCrearNuevoCliente  
  - Verifica la creación: código 201, id y contenido del cliente creado.
- deberiaActualizarClienteExistente  
  - Valida la actualización: código 200 y que los campos sean modificados correctamente.

Notas:
- Usa Mockito para mockear el servicio de cliente.
- En el setup se instancia MockMvc pero muchas aserciones comprueban las respuestas del controlador invocado directamente.

---

### MascotaControllerTest.java
Descripción: pruebas de listado, obtención por ID y creación de mascotas; validación de campos (fechas, tipo, raza).

Pruebas principales:
- deberiaListarTodasLasMascotas  
  - Valida retorno de lista completa con código 200 y cantidad esperada.
- deberiaObtenerMascotaPorId  
  - Comprueba obtención por ID y valores esperados en MascotaDto.
- deberiaCrearNuevaMascota  
  - Verifica creación (201) y que los campos (fechaNacimiento, nombre, especie) se transfieran correctamente.

Notas:
- Construcción de DTOs con fechas reales para comprobar formateo y contenido.
- Mock del servicio de mascotas para controlar respuestas.

---

### ServicioControllerTest.java
Descripción: validación de operaciones sobre servicios (precio, nombre, creación y listado).

Pruebas principales:
- deberiaListarTodosLosServicios  
  - Verifica que la lista de servicios se devuelva con código 200 y que incluyan los nombres esperados.
- deberiaCrearNuevoServicio  
  - Valida la creación con código 201; comprueba ID, nombre y precio (BigDecimal) del servicio creado.

Notas:
- Uso de BigDecimal en DTOs para verificar precisión de precios.
- Mocks del servicio de dominio para aislar el controlador.

---

## Tecnologías y utilidades
- JUnit 5
- Mockito
- Spring Boot Test / MockMvc (setup en tests)
- Uso de DTOs para assertions (ClienteDto, MascotaDto, ServicioDto)
- Validaciones sobre códigos HTTP y contenido JSON (cuando MockMvc se utiliza)

## Cómo ejecutar las pruebas

Desde la raíz del proyecto:
- Ejecutar todas las pruebas del paquete:
  ```bash
  mvn test -Dtest="com.backend.vet.fabriziopalenque.*"
  ```
- Ejecutar una clase de prueba específica:
  ```bash
  mvn test -Dtest="com.backend.vet.fabriziopalenque.ClienteControllerTest"
  ```
- Ejecutar un método de prueba concreto (ejemplo):
  ```bash
  mvn test -Dtest="com.backend.vet.fabriziopalenque.ClienteControllerTest#deberiaCrearNuevoCliente"
  ```
Desde VSCode/IDE:
- Abrir el paquete `fabriziopalenque` en el explorador de pruebas y ejecutar clases o métodos individuales.

---

## Resultados (resumen esperado)
- ClienteControllerTest: ~5 pruebas (happy path, not found, create, update)
- MascotaControllerTest: ~3 pruebas (list, getById, create)
- ServicioControllerTest: ~2 pruebas (list, create)

Cobertura objetivo: validar principalmente la lógica del controlador y la correcta interacción con los servicios mockeados.

---

## Buenas prácticas aplicadas
- Nombres de métodos descriptivos que explican el comportamiento esperado.
- Aislamiento de capas con Mockito para pruebas unitarias puras.
- Setup centralizado (@BeforeEach) para inicializar mocks y MockMvc.
- Combinación de aserciones sobre status HTTP y contenido de los DTOs.

---

## Autor
- Paquete: fabriziopalenque
- Tests: ClienteControllerTest, MascotaControllerTest, ServicioControllerTest
- Creado por: Fabrizio Palenque