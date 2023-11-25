package com.sistema.examenes.servicios;

import com.sistema.examenes.modelo.Categoria;
import com.sistema.examenes.modelo.Producto;

import java.util.List;
import java.util.Set;

public interface IproductoService {

    Producto agregarProducto(Producto producto);

    Producto actualizarProducto(Producto producto);

    Set<Producto> obtenerProductos();

    Producto obtenerProducto(Long productoId);

    void eliminarProducto(Long productoId);

    List<Producto> listarProductosDeUnaCategoria(Categoria categoria);

    List<Producto> obtenerProductosActivos();

    List<Producto> obtenerProductosActivosDeUnaCategoria(Categoria categoria);
}
