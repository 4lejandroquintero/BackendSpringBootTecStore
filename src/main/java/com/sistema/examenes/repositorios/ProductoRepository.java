package com.sistema.examenes.repositorios;

import com.sistema.examenes.modelo.Categoria;
import com.sistema.examenes.modelo.Producto;
import com.sistema.examenes.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria(Categoria categoria);

    List<Producto> findByActivo(Boolean estado);

    List<Producto> findByCategoriaAndActivo(Categoria categoria,Boolean estado);

    List<Producto> findByCodigo(String codigo);
}