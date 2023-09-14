# ms-user-bci
Proyecto para evaluacion en proceso de seleccion

# Stack
Proyecto realizado con:
  Spring Boot v 3.1.3
  Java 8 (openjdk-18)
  Gradle
  JPA (h2)
  JUnit 5

# Modo de uso
Inicializar el proyecto con IDE de preferencia
Actualizar las dependencias gradle
Dentro de la carpeta ms-user-bci/src/test/resources/json se encuentran archivo 
ms-user-bci.postman_collection.json el cual se debe importar a Postman.

Ahi se encuentran los ejemplos para probar la api en el puerto 8080.

Los servicios Login y SignUp no estan securitizados; en cambio el servicio
Auth login si lo esta mediante Bearer Token.

Para ejecutar primero realizar el sing up del usuario, este retornara un token el cual
se debe poner de argumento en el header de Authorization del servicio Login.

El mismo token se puede utilizar para el servicio Auth login, tomando en consideracion que este cambia
por cada login realizado.
