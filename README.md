# Prueba de Concepto - Implementación de Circuit Breaker con Spring Boot

## Descripción de la Prueba

Esta prueba de concepto tiene como objetivo implementar el patrón **Circuit Breaker** en una aplicación **Spring Boot** utilizando la librería **Resilience4j**. Se busca demostrar cómo el Circuit Breaker puede ayudar a manejar fallos en servicios externos, mejorando la resiliencia de la aplicación.

## Objetivo(s) de la Prueba

- Demostrar cómo aplicar el patrón **Circuit Breaker** en Spring Boot para gestionar fallos en servicios externos.
- Probar la transición del estado del Circuit Breaker entre **CLOSED**, **OPEN** y **HALF_OPEN**, según la respuesta del servicio externo.
- Mostrar cómo la aplicación se comporta ante fallos del servicio y cuándo se detiene la comunicación con el servicio externo.
- Evaluar el impacto del Circuit Breaker en la resiliencia y disponibilidad de la aplicación.

## Pasos Implementados para Llevar a Cabo la Prueba

1. **Crear una aplicación Spring Boot**: 
   - Generar un proyecto Spring Boot utilizando Spring Initializr, añadiendo las dependencias de **Spring Web**, **Resilience4j**, y **Spring Boot Actuator**.
  
2. **Configurar el servicio externo**:
   - Crear un endpoint simulado que represente un servicio externo que puede fallar de manera intermitente.
  
3. **Implementar el Circuit Breaker con Resilience4j**:
   - Usar la anotación `@CircuitBreaker` para aplicar el Circuit Breaker en el método que llama al servicio externo.
   - Configurar un método de fallback para manejar los fallos del servicio externo.

4. **Configurar Resilience4j y Actuator en `application.properties`**:
   - Definir parámetros como `failure-rate-threshold`, `minimum-number-of-calls`, `permitted-number-of-calls-in-half-open-state`, y `wait-duration-in-open-state` para controlar el comportamiento del Circuit Breaker.
   - Habilitar el monitoreo del Circuit Breaker a través de Spring Actuator.

5. **Ejecutar pruebas**:
   - Realizar múltiples solicitudes al servicio protegido por el Circuit Breaker para observar cómo cambia su estado según el número de fallos registrados.

## Tecnologías Usadas en la Prueba

- **Lenguaje**: Java
- **Framework**: Spring Boot
- **Librería de Circuit Breaker**: Resilience4j
- **Herramientas**: Spring Boot Actuator, Spring Boot AOP, Spring Boot DevTools y Maven

## Resultados

- Se observó que el Circuit Breaker cambió correctamente entre los estados **CLOSED**, **OPEN**, y **HALF_OPEN** dependiendo de las fallas en el servicio externo.
- Durante el estado **OPEN**, todas las solicitudes al servicio fueron redirigidas al método de **fallback**, evitando sobrecargar el servicio.
- El Circuit Breaker pasó a **HALF_OPEN** después del tiempo configurado y permitió un número limitado de llamadas de prueba para verificar si el servicio se había recuperado. Cuando las llamadas fueron exitosas, el Circuit Breaker volvió a **CLOSED**.

## Conclusiones

- El uso de **Resilience4j** en Spring Boot facilita la implementación del patrón **Circuit Breaker**, ayudando a mejorar la tolerancia a fallos y la resiliencia en aplicaciones distribuidas.
- El Circuit Breaker permite proteger a la aplicación de la sobrecarga causada por servicios externos fallidos, mejorando la disponibilidad general del sistema.
- Monitorear el estado del Circuit Breaker mediante **Spring Boot Actuator** fue útil para obtener visibilidad en tiempo real sobre el comportamiento del Circuit Breaker.