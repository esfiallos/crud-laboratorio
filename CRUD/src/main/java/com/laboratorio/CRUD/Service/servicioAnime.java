package com.laboratorio.CRUD.Service;

import com.laboratorio.CRUD.Entidades.Animes;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.laboratorio.CRUD.Repositorios.repositorioAnimes;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class servicioAnime {

    private final repositorioAnimes repoAnimes;

    public Animes postAnimes(Animes anime)
    {
        return repoAnimes.save(anime);
    }

    public List<Animes> getAllAnimes() {
        return repoAnimes.findAll();
    }

    public void deleteAnimes(Integer id)
    {
        if(!repoAnimes.existsById(id))
        {
            throw new EntityNotFoundException("Anime con el Id: " + id + " No encontrado");
        }

        repoAnimes.deleteById(id);
    }

    public Animes getAnimeById(Integer id)
    {
        return repoAnimes.findById(id).orElse(null);
    }

    public Animes UpdateAnime(Integer id, Animes anime)
    {
        Optional<Animes> AnimeOpcional = repoAnimes.findById(id);

        if(AnimeOpcional.isPresent()) {
            Animes AnimeExistente = AnimeOpcional.get();

            AnimeExistente.setNombre(anime.getNombre());
            AnimeExistente.setDescripcion(anime.getDescripcion());
            AnimeExistente.setCategoria(anime.getCategoria());
            AnimeExistente.setCapitulos(anime.getCapitulos());
            AnimeExistente.setEstado(anime.getEstado());
            AnimeExistente.setValoracion(anime.getValoracion());

            return repoAnimes.save(AnimeExistente);
        }

        return null;
    }

}
