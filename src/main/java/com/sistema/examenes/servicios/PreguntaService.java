package com.sistema.examenes.servicios;

import com.sistema.examenes.modelo.Pregunta;
import com.sistema.examenes.modelo.Producto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface PreguntaService {

    Pregunta agregarPregunta(Pregunta pregunta);

    Pregunta actualizarPregunta(Pregunta pregunta);

    Set<Pregunta> obtenerPreguntas();

    Pregunta obtenerPregunta(Long preguntaId);

    Set<Pregunta> obtenerPreguntasDelProducto(Producto producto);

    void eliminarPregunta(Long preguntaId);

    Pregunta listarPregunta(Long preguntaId);

}

