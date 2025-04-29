# CRUD de Animes con Spring Boot (Backend) y React (Frontend)

Este proyecto contiene una API RESTful simple. 
Permite realizar las operaciones básicas de Crear, Leer, Actualizar y Eliminar (CRUD) a través de diferentes endpoints.

## Tecnologías Utilizadas

* **Java:** Lenguaje de programación principal.
* **Spring Boot:** Framework de Java para un desarrollo rápido de aplicaciones.
* **Spring Data JPA:** Para la persistencia de datos utilizando JPA (Java Persistence API).
* **Hibernate:** Implementación de JPA utilizada por Spring Data JPA.
* **MySQL:** Para almacenar la información de los animes.
* **Maven:** Herramienta de gestión de dependencias y construcción del proyecto.
* **Lombok:** Librería para reducir el código boilerplate (como getters, setters, constructores).

## Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado lo siguiente:

* **Java Development Kit (JDK):** Version 21
* **Maven:** Para gestionar las dependencias del proyecto.
* **Base de datos MySQL**
* **IDE**: De Preferencia Idea Intelillij

## Configuración de la Base de Datos

1.  El proyecto una una base de datos llamada "laboratorio", la cual contiene una tabla "animes".

La estructura de la tabla es: 
                create table Animes(id int not null AUTO_INCREMENT  primary key,
                    nombre varchar(50),
                    descripcion text,
                    categoria varchar(50),
                    capitulos varchar(50),
                    estado varchar(50),
                    valoracion int
                   );

## Ejecución de la Aplicación

1.  **Clona el repositorio:**
    ```bash
    git clone [https://github.com/esfiallos/crud-laboratorio](https://github.com/esfiallos/crud-laboratorio)

    ```

2. **Ejecuta el proyecto**    

    La aplicación estará disponible en `http://localhost:8080`.

## Endpoints de la API

La API está disponible bajo el prefijo `/api`. A continuación, se describen los endpoints principales:

* **`POST /api/animes`**: Crea un nuevo anime.
    * **Cuerpo de la petición (JSON):**
        ```json
        {
            "nombre": "Nombre del Anime",
            "descripcion": "Breve descripción del anime",
            "categoria": "Género del anime",
            "capitulos": 12,
            "estado": "En emisión",
            "valoracion": 4.5
        }
        ```
    * **Respuesta (JSON):** El anime creado con su ID asignado.

* **`GET /api/MostrarAnimes`**: Obtiene una lista de todos los animes.
    * **Respuesta (JSON):** Un array de objetos anime.

* **`GET /api/MostrarAnimes/{nombre}`**: Obtiene una lista de animes cuyo nombre contiene la cadena proporcionada (insensible a mayúsculas y minúsculas).
    * **Parámetro de ruta:** `{nombre}` (la cadena a buscar en el nombre).
    * **Respuesta (JSON):** Un array de objetos anime que coinciden con el nombre.

* **`DELETE /api/animes/{id}`**: Elimina un anime por su ID.
    * **Parámetro de ruta:** `{id}` (el ID del anime a eliminar).
    * **Respuesta (JSON):**
        * `200 OK`: Si el anime fue eliminado exitosamente. El cuerpo de la respuesta contendrá el mensaje: `"El Anime con el Id: {id} Fue Eliminado"`.
        * `404 Not Found`: Si no se encontró ningún anime con el ID proporcionado. El cuerpo de la respuesta contendrá el mensaje de error.

* **`GET /api/animes/{id}`**: Obtiene un anime por su ID.
    * **Parámetro de ruta:** `{id}` (el ID del anime a obtener).
    * **Respuesta (JSON):** El objeto anime con el ID especificado.
    * **Respuesta (Código de estado):**
        * `200 OK`: Si se encontró el anime.
        * `404 Not Found`: Si no se encontró ningún anime con el ID proporcionado.

* **`PATCH /api/animes/{id}`**: Actualiza parcialmente la información de un anime existente por su ID.
    * **Parámetro de ruta:** `{id}` (el ID del anime a actualizar).
    * **Cuerpo de la petición (JSON):** Un objeto JSON con los campos que deseas actualizar (no es necesario incluir todos los campos). Por ejemplo:
        ```json
        {
            "estado": "Finalizado",
            "valoracion": 4.8
        }
        
    * **Respuesta (JSON):** El anime actualizado.
    * **Respuesta (Código de estado):**
        * `200 OK`: Si el anime fue actualizado exitosamente.
        * `400 Bad Request`: Si la petición no es válida o no se encontró el anime.

## Estructura del Proyecto