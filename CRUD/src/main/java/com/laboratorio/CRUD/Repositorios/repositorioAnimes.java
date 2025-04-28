package com.laboratorio.CRUD.Repositorios;


import com.laboratorio.CRUD.Entidades.Animes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositorioAnimes extends JpaRepository<Animes, Integer>
{

}
