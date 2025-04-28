package com.laboratorio.CRUD.Controladores;

import com.laboratorio.CRUD.Entidades.Animes;
import com.laboratorio.CRUD.Service.servicioAnime;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class AnimesController {
    private final servicioAnime servAni;

    @PostMapping("/animes")
    public Animes postAnime(@RequestBody Animes anime)
    {
        return servAni.postAnimes(anime);
    }

    @GetMapping("/MostrarAnimes")
    public List<Animes> getAllAnimes() {
        return servAni.getAllAnimes();
    }

    @GetMapping("/MostrarAnimes/{nombre}")
    public List<Animes> getAnimesByName(@PathVariable String nombre) {
        return servAni.getAnimesByName(nombre);
    }


    @DeleteMapping("/animes/{id}")
    public ResponseEntity<?> deleteAnimes(@PathVariable Integer id)
    {
        try {
            servAni.deleteAnimes(id);
            return new ResponseEntity<>(STR."El Anime con el Id: \{id} Fue Eliminado", HttpStatus.OK);
        } catch(EntityNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/animes/{id}")
    public ResponseEntity<?> getAnimeById(@PathVariable Integer id)
    {
        Animes anime =  servAni.getAnimeById(id);
        if(anime == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(anime);

    }

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
