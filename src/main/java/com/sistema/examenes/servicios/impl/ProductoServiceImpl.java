package com.sistema.examenes.servicios.impl;

import com.sistema.examenes.modelo.Categoria;
import com.sistema.examenes.modelo.Producto;
import com.sistema.examenes.repositorios.ProductoRepository;
import com.sistema.examenes.servicios.IproductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductoServiceImpl implements IproductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Set<Producto> obtenerProductos() {
        return new LinkedHashSet<>(productoRepository.findAll());
    }

    @Override
    public Producto obtenerProducto(Long productoId) {
        return productoRepository.findById(productoId).get();
    }

    @Override
    public void eliminarProducto(Long productoId) {
        Producto producto = new Producto();
        producto.setProductoId(productoId);
        productoRepository.delete(producto);
    }

    @Override
    public List<Producto> listarProductosDeUnaCategoria(Categoria categoria) {
        return this.productoRepository.findByCategoria(categoria);
    }



    @Override
    public Producto buscarProductoPorId(Long productoId) {
        Producto producto = this.productoRepository.findById(productoId).orElse(null);
        return producto;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return this.productoRepository.save(producto);
    }

    @Override
    public List<Producto> obtenerProductosActivos() {
        return productoRepository.findByActivo(true);
    }

    @Override
    public List<Producto> obtenerProductosActivosDeUnaCategoria(Categoria categoria) {
        return productoRepository.findByCategoriaAndActivo(categoria,true);
    }

    @Override
    public List<Producto> findByCodigo(String codigo) {
        return this.productoRepository.findByCodigo(codigo);
    }
}