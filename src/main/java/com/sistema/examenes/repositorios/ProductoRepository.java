package com.sistema.examenes.repositorios;

import com.sistema.examenes.modelo.Producto;
import com.sistema.examenes.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    public Producto findByDescripcion(String descripcion);
}
