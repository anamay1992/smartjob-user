# Smartjob - Api User
Api para la creación de un usuario con H2 y JPA Persistence

## Prerrequisitos
* IntelliJ IDEA
* Java 17

## Documentación
Swagger: http://localhost:8080/smart-job-api-user/swagger-ui/index.html

## H2
Console: http://localhost:8080/smart-job-api-user/h2-console
   * jdbc_url: jdbc:h2:mem:testdb
   * username: sa
   * password: sa

## Instrucciones de uso
1. Validar si la opcion de anotaciones en la compilación esta activa.
   * settings
   * build, execution, deployment
   * compiler
   * annotation processors
   * selecciona el proyecto 'smartjob-api-user'
   * marca la option 'Obtain processors from project classpath'
   * aplicar y ok
2. Click derecho en la clase main del proyecto y hacer click en RUN.
3. Importar la colección postman adjunta en el proyecto para poder cargar el endpoint listo.
   * DIR = ../resources/postman/smart-job.postman_collection.json
4. Ejecutar el endpoint 'create-user'.
5. Validar en la base de datos H2 que los datos se guardaron exitosamente.
