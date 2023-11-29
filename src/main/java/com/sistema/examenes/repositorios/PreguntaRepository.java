package com.sistema.examenes.repositorios;

import com.sistema.examenes.modelo.Pregunta;
import com.sistema.examenes.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta,Long> {

    Set<Pregunta> findByProducto(Producto producto);

}

