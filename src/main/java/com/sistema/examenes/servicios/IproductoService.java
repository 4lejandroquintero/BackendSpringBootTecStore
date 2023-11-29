package com.sistema.examenes.servicios;

import com.sistema.examenes.modelo.Categoria;
import com.sistema.examenes.modelo.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface IproductoService {

    Producto agregarProducto(Producto producto);

    Producto actualizarProducto(Producto producto);

    Set<Producto> obtenerProductos();

    Producto obtenerProducto(Long productoId);

    void eliminarProducto(Long productoId);

    List<Producto> listarProductosDeUnaCategoria(Categoria categoria);

    List<Producto> obtenerProductosActivos();

    List<Producto> obtenerProductosActivosDeUnaCategoria(Categoria categoria);

    List<Producto> findByCodigo(String codigo);

    public Producto buscarProductoPorId(Long productoId);

    public Producto guardarProducto(Producto producto);
}
