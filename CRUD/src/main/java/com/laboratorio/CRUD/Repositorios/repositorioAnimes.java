package com.laboratorio.CRUD.Repositorios;


import com.laboratorio.CRUD.Entidades.Animes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  * <p>
 *  * Esta interfaz extiende {@link JpaRepository}, que a su vez extiende {@link org.springframework.data.repository.CrudRepository}.
 *  * Esto proporciona automáticamente métodos para las operaciones CRUD básicas (Crear, Leer, Actualizar, Eliminar)
 *  * sin necesidad de escribir código adicional.
 *  * <p>
 */
@Repository
public interface repositorioAnimes extends JpaRepository<Animes, Integer>
{

}
