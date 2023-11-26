package com.sistema.examenes.controladores;

import com.sistema.examenes.modelo.Pregunta;
import com.sistema.examenes.modelo.Producto;
import com.sistema.examenes.servicios.IproductoService;
import com.sistema.examenes.servicios.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pregunta")
@CrossOrigin("*")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @Autowired
    private IproductoService iproductoService;

    @PostMapping("/")
    public ResponseEntity<Pregunta> guardarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.agregarPregunta(pregunta));
    }

    @PutMapping("/")
    public ResponseEntity<Pregunta> actualizarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.actualizarPregunta(pregunta));
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<?> listarPreguntasDelProducto(@PathVariable("productoId") Long productoId){
        Producto producto = iproductoService.obtenerProducto(productoId);
        Set<Pregunta> preguntas = producto.getPreguntas();

        List productos = new ArrayList(preguntas);
        if(productos.size() > Integer.parseInt(producto.getExistencia())){
            productos = productos.subList(0,Integer.parseInt(producto.getExistencia() + 1));
        }

        Collections.shuffle(productos);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{preguntaId}")
    public Pregunta listarPreguntaPorId(@PathVariable("preguntaId") Long preguntaId){
        return preguntaService.obtenerPregunta(preguntaId);
    }

    @DeleteMapping("/{preguntaId}")
    public void eliminarPregunta(@PathVariable("preguntaId") Long preguntaId){
        preguntaService.eliminarPregunta(preguntaId);
    }

    @GetMapping("/producto/todos/{productoId}")
    public ResponseEntity<?> listarPreguntaDelProductoComoAdministrador(@PathVariable("productoId") Long productoId){
        Producto producto = new Producto();
        producto.setProductoId(productoId);
        Set<Pregunta> preguntas = preguntaService.obtenerPreguntasDelProducto(producto);
        return ResponseEntity.ok(preguntas);
    }

    @PostMapping("/evaluar-producto")
    public ResponseEntity<?> evaluarProducto(@RequestBody List<Pregunta> preguntas){
        double existencia = 0;
        Integer respuestasCorrectas = 0;
        Integer intentos = 0;

        for(Pregunta p : preguntas){
            Pregunta pregunta = this.preguntaService.listarPregunta(p.getPreguntaId());
            if(pregunta.getRespuesta().equals(p.getRespuestaDada())){
                respuestasCorrectas ++;
                double puntos = Double.parseDouble(preguntas.get(0).getProducto().getExistencia())/preguntas.size();
                existencia += puntos;
            }
            if(p.getRespuestaDada() != null){
                intentos ++;
            }
        }

        Map<String,Object> respuestas = new HashMap<>();
        respuestas.put("Existencias",existencia);
        respuestas.put("comprasCorrectas",respuestasCorrectas);
        respuestas.put("intentos",intentos);
        return ResponseEntity.ok(respuestas);
    }
}
