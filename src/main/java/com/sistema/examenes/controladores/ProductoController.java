package com.sistema.examenes.controladores;

import com.sistema.examenes.excepcion.RecursoNoEncontradoExcepcion;
import com.sistema.examenes.modelo.Categoria;
import com.sistema.examenes.modelo.Producto;
import com.sistema.examenes.servicios.IproductoService;
import com.sistema.examenes.servicios.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@CrossOrigin(value = "*")
public class ProductoController {

    @Autowired
    private IproductoService iproductoService;

    @PostMapping("/")
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(iproductoService.agregarProducto(producto));
    }

    @PutMapping("/")
    public ResponseEntity<Producto> actualizarProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(iproductoService.actualizarProducto(producto));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarProductos(){
        return ResponseEntity.ok(iproductoService.obtenerProductos());
    }

    @GetMapping("/{productoId}")
    public Producto listarProducto(@PathVariable("productoId") Long productoId){
        return iproductoService.obtenerProducto(productoId);
    }

    @DeleteMapping("/{productoId}")
    public void eliminarProducto(@PathVariable("productoId") Long productoId){
        iproductoService.eliminarProducto(productoId);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Producto> listarProductosDeUnaCategoria(@PathVariable("categoriaId") Long categoriaId){
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        return iproductoService.listarProductosDeUnaCategoria(categoria);
    }

    @GetMapping("/activo")
    public List<Producto> listarProductosActivos(){
        return iproductoService.obtenerProductosActivos();
    }

    @GetMapping("/categoria/activo/{categoriaId}")
    public List<Producto> listarProductosActivosDeUnaCategoria(@PathVariable("categoriaId") Long categoriaId){
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        return iproductoService.obtenerProductosActivosDeUnaCategoria(categoria);
    }
}