package com.laboratorio.CRUD.Service;

import com.laboratorio.CRUD.Entidades.Animes;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.laboratorio.CRUD.Repositorios.repositorioAnimes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio que proporciona la lógica de negocio para la gestión de entidades {@link Animes}.
 * Interactúa con el {@link repositorioAnimes} para realizar operaciones de persistencia.
 */
@Service
@RequiredArgsConstructor
public class servicioAnime {

    private final repositorioAnimes repoAnimes;

    /**
     * Guarda un anime en la base de datos
     * @param anime
     * @return El objeto {@link Animes}
     */
    public Animes postAnimes(Animes anime)
    {
        return repoAnimes.save(anime);
    }

    /**
     * Obtiene una lista de todos los elementos almacenados en la base de datos
     * @return Una {@link List} que contiene todos los objetos {@link Animes} encontrados.
     * Retorna una lista vacía si no hay animes en la base de datos.
     */
    public List<Animes> getAllAnimes() {
        return repoAnimes.findAll();
    }

    /**
     * Elimina un anime de la base de datos por su Id
     * @param id
     *  @throws EntityNotFoundException Si no se encuentra ningún anime
     */
    public void deleteAnimes(Integer id)
    {
        if(!repoAnimes.existsById(id))
        {
            throw new EntityNotFoundException("Anime con el Id: " + id + " No encontrado");
        }

        repoAnimes.deleteById(id);
    }

    /**
     * Obtiene  un anime por su Identificador
     * @param id
     * @return El objeto {@link Animes} encontrado con el ID especificado,
     */
    public Animes getAnimeById(Integer id)
    {
        return repoAnimes.findById(id).orElse(null);
    }

    /**
     * Funcion Filtro para Obtener animes por su nombre
     * Busca animes cuyo nombre contiene la cadena de búsqueda
     *
     * @param nombre del anime a buscar
     * @return Una lista de animes cuyo nombre contenga los datos del @param
     */
    public List<Animes> getAnimesByName(String nombre) {
       List<Animes> AllAnimes = repoAnimes.findAll();
        return AllAnimes.stream().filter(
                animes -> animes.getNombre()
                        .toLowerCase()
                        .contains(nombre.toLowerCase()))
                        .collect(Collectors.toList());
    }

    /**
     * Actualiza un anime de la base de datos
     *
     * @param id
     * @param anime
     * @return El objeto Actualizado
     */
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
