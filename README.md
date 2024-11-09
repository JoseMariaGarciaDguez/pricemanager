# PriceManager

**PriceManager** es una aplicación de comercio electrónico para la gestión de precios de productos. Este servicio permite obtener el precio final de venta de un producto específico de una cadena en una fecha determinada, aplicando la tarifa con mayor prioridad en caso de solapamiento de rangos de fechas.

## Descripción de la tabla `PRICES`

La tabla `PRICES` contiene la información de las tarifas y precios aplicables para cada producto en distintas fechas. Los campos relevantes son:

- **BRAND_ID**: Identificador de la cadena del grupo (ej. 1 = ZARA).
- **START_DATE** y **END_DATE**: Rango de fechas en el cual aplica el precio de la tarifa indicada.
- **PRICE_LIST**: Identificador único de la tarifa aplicable.
- **PRODUCT_ID**: Código identificador del producto.
- **PRIORITY**: Indicador de prioridad para la tarifa. Si dos tarifas se solapan, se aplica la de mayor prioridad (mayor valor numérico).
- **PRICE**: Precio final de venta al público.
- **CURR**: ISO de la moneda utilizada (ej. EUR).

### Ejemplo de datos de la tabla `PRICES`

| BRAND_ID | START_DATE             | END_DATE               | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|-------------------------|------------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14 00:00:00     | 2020-12-31 23:59:59   | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14 15:00:00     | 2020-06-14 18:30:00   | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15 00:00:00     | 2020-06-15 11:00:00   | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15 16:00:00     | 2020-12-31 23:59:59   | 4          | 35455      | 1        | 38.95 | EUR  |

## Requisitos Funcionales

La aplicación ofrece un endpoint REST para realizar consultas de precios. Este endpoint:

- Acepta como parámetros de entrada:
    - **Fecha de aplicación**
    - **Identificador de producto**
    - **Identificador de cadena**
- Devuelve como salida:
    - **Identificador de producto**
    - **Identificador de cadena**
    - **Tarifa aplicable**
    - **Fechas de aplicación**
    - **Precio final aplicable**

### Ejemplos de Peticiones

La aplicación incluye pruebas automáticas que verifican el funcionamiento correcto del servicio de consulta de precios con los siguientes casos:

1. **Test 1**: Consulta el 14 de junio a las 10:00 para el producto 35455 de la cadena 1 (ZARA).
2. **Test 2**: Consulta el 14 de junio a las 16:00 para el producto 35455 de la cadena 1 (ZARA).
3. **Test 3**: Consulta el 14 de junio a las 21:00 para el producto 35455 de la cadena 1 (ZARA).
4. **Test 4**: Consulta el 15 de junio a las 10:00 para el producto 35455 de la cadena 1 (ZARA).
5. **Test 5**: Consulta el 16 de junio a las 21:00 para el producto 35455 de la cadena 1 (ZARA).

Estas pruebas aseguran que el precio y la tarifa aplicable se determinen correctamente en función de las fechas y prioridades establecidas en la base de datos.

## Instalación y Configuración

1. Clona el repositorio:

   ```bash
   git clone https://github.com/josemariagarciadguez/pricemanager.git
   cd pricemanager
Compila el proyecto con Maven:

    mvn clean install
Ejecuta la aplicación:

    mvn spring-boot:run
## Configuración de la Base de Datos en H2
El proyecto utiliza H2, una base de datos en memoria. Se inicializa automáticamente con los datos de ejemplo en la tabla PRICES. Puedes acceder a la consola de H2 en http://localhost:8080/h2-console con la siguiente configuración:

- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Password: (dejar en blanco)
## Dependencias
El proyecto utiliza las siguientes dependencias principales, configuradas en el archivo **pom.xml**:

- Spring Boot Starter Web: 3.3.5 - Para crear APIs REST.
- Spring Boot Starter Data JPA: 3.3.5 - Para la persistencia de datos.
- H2 Database: 1.4.200 - Base de datos en memoria para pruebas y desarrollo.
- ModelMapper: 2.4.4 - Para realizar conversiones entre entidades.
- Lombok: 1.18.24 - Simplificación del código mediante anotaciones.
- Spring Boot Starter Test: 3.3.5 - Librería de test para pruebas unitarias.
## Entorno de Desarrollo
- IDE: Se recomienda el uso de IntelliJ IDEA Community Edition para el desarrollo de este proyecto. IntelliJ proporciona soporte para proyectos Spring Boot y facilita la gestión de dependencias y ejecución de pruebas.
[!NOTE]
La versión Community Edition de IntelliJ es gratuita y compatible con el desarrollo de aplicaciones Spring Boot.
## Requisitos
- Java: 17 o superior
- Maven: Para la gestión de dependencias
## Licencia
- Este proyecto está licenciado bajo XYZ.