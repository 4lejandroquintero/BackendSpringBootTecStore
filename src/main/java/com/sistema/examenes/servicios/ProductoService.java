package com.sistema.examenes.servicios;

import com.sistema.examenes.modelo.Producto;
import com.sistema.examenes.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IproductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listarProductos() {
        return this.productoRepository.findAll();
    }

    @Override
    public Producto buscarProductoPorId(Integer idProducto) {
        Producto producto = this.productoRepository.findById(idProducto).orElse(null);
        return producto;
    }

    @Override
    public Producto buscarProductoPorDescripcion(String descripcion) {
        Producto producto = this.productoRepository.findByDescripcion(descripcion);
        return producto;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return this.productoRepository.save(producto);
    }

    @Override
    public void eliminiarProductoPorId(Integer idProducto) {
        this.productoRepository.deleteById(idProducto);
    }
}
