package com.laboratorio.CRUD.Controladores;

import com.laboratorio.CRUD.Entidades.Animes;
import com.laboratorio.CRUD.Service.servicioAnime;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de recursos de Animes.
 * Expone endpoints para crear, leer, actualizar y eliminar información de animes.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class AnimesController {
    private final servicioAnime servAni;

    /**
     * Endpoint encargado de la funcion Create
     *
     * @param anime El Objeto que se creara
     * El cuerpo de la petición debe contener la representación JSON del anime.
     * @return El Objeto tipo anime creado
     */
    @PostMapping("/animes")
    public Animes postAnime(@RequestBody Animes anime)
    {
        return servAni.postAnimes(anime);
    }

    /**
     *  Endpoint que se encarga del Get
     * @return Devuelve una lista con todos los animes almacenados en la DB
     */
    @GetMapping("/MostrarAnimes")
    public List<Animes> getAllAnimes() {
        return servAni.getAllAnimes();
    }

    /**
     * Endpoint que se encarga del Get con un filtro
     * Esta funcion se usa para filtrar por parametro de busqueda
     *
     * @param nombre el dato con el que se buscara el anime
     * @return una lista con todos los animes que coincidan con la busqueda
     */
    @GetMapping("/MostrarAnimes/{nombre}")
    public List<Animes> getAnimesByName(@PathVariable String nombre) {
        return servAni.getAnimesByName(nombre);
    }

    /**
     * Endpoint que se encarga del Delete
     * Elimina un dato de la base de Datos
     * @param id el identificados del objeto
     * @return Una respuesta que indica el resultado de la operacion
     * -  HttpStatus.OK Si el Objeto Fue Eliminado
     * -  HttpStatus.NOT_FOUND Si el Objeto no se encontro
     */
    @DeleteMapping("/animes/{id}")
    public ResponseEntity<?> deleteAnimes(@PathVariable Integer id)
    {
        try {
            servAni.deleteAnimes(id);
            return new ResponseEntity<>("El Anime con el Id:" + id + " Fue Eliminado", HttpStatus.OK);
        } catch(EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    /**
     * EndPoint que se encarga de obtener un anime por su id
     *
     * @param id el identificador del objeto
     * @return una respuesta que indica el resultado de la operacion
     * -  HttpStatus.OK Si el Objeto Fue Eliminado
     * -  HttpStatus.NOT_FOUND Si el Objeto no se encontro
     */
    @GetMapping("/animes/{id}")
    public ResponseEntity<?> getAnimeById(@PathVariable Integer id)
    {
        Animes anime =  servAni.getAnimeById(id);
        if(anime == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(anime);

    }

    /**
     * Endpint que se encarga de la funcion Update
     * Actualiza un objeto anime existente
     * @param id el identificador del anime a actualizar
     * @param anime el objeto que contiene los cambios
     * @return Una respuesta que indica el resultado de la operacion
     * -  HttpStatus.OK Si el Objeto Fue Eliminado
     * -  HttpStatus.NOT_FOUND Si el Objeto no se encontro
     */
    @PatchMapping("/animes/{id}")
    public ResponseEntity<?> UpdateAnime(
           @PathVariable Integer id,
           @RequestBody Animes anime)
    {
        Animes updateAnime = servAni.UpdateAnime(id, anime);
        if(updateAnime == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.ok(updateAnime);
    }

}
