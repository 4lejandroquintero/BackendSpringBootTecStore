package com.sistema.examenes.servicios;

import com.sistema.examenes.modelo.Producto;

import java.util.List;

public interface IproductoService {
    public List<Producto> listarProductos();

    public Producto buscarProductoPorId(Integer idProducto);

    public Producto buscarProductoPorDescripcion(String descripcion);

    public Producto guardarProducto(Producto producto);

    public void eliminiarProductoPorId(Integer idProducto);
}