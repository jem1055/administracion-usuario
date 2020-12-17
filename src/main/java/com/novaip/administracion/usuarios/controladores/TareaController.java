package com.novaip.administracion.usuarios.controladores;

import com.novaip.administracion.usuarios.entidades.Tarea;
import com.novaip.administracion.usuarios.entidades.Usuario;
import com.novaip.administracion.usuarios.repositorios.TareaRepository;
import com.novaip.administracion.usuarios.repositorios.UsuarioRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/crear/{usuarioId}")
    public Tarea crear(@RequestBody Tarea tarea, @PathVariable int usuarioId) {

        tarea = tareaRepository.save(tarea);

        Usuario user = usuarioRepository.findById(usuarioId).get();
        if(user.getTareas() == null) user.setTareas(new ArrayList<>());

        user.getTareas().add(tarea);
        //tarea.setUsuario(user);
        usuarioRepository.save(user);
        return tarea;
    }

    @GetMapping("/obtener/{tareaId}")
    public Tarea obtener(@PathVariable int tareaId){
        return tareaRepository.findById(tareaId).get();
    }

    @DeleteMapping("/eliminar/{tareaId}")
    public void eliminar(@PathVariable int tareaId){
        tareaRepository.deleteById(tareaId);
    }

    @GetMapping("/filtrar/{usuarioId}/{campo}/{valor}/{columnaOrdenamiento}/{direccionOrdenamiento}")
    public List<Tarea> filtrar(@PathVariable("usuarioId") int usuarioId,
                               @PathVariable("campo") String campo,
                               @PathVariable("valor") String valor,
                               @PathVariable("columnaOrdenamiento") String columnaOrdenamiento,
                               @PathVariable("direccionOrdenamiento") String direccionOrdenamiento){

        Usuario user = usuarioRepository.findById(usuarioId).get();
        List<Tarea> tareas = user.getTareas();

        if(campo.equalsIgnoreCase("fechaCreacion")){
            LocalDate date = LocalDate.parse(valor);
            tareas = tareas.stream().filter(t -> t.getFechaCreacion().equals(date)).collect(Collectors.toList());
        }else if(campo.equalsIgnoreCase("estado")){
            tareas = tareas.stream().filter(t -> t.getEstado().equals(valor)).collect(Collectors.toList());
        }

        switch (columnaOrdenamiento){
            case "descripcion" :
                if(direccionOrdenamiento.equals("asc")){
                    tareas = tareas.stream().sorted((t1, t2) ->  t1.getDescripcion().compareTo(t2.getDescripcion())).collect(Collectors.toList());
                }else{
                    tareas = tareas.stream().sorted((t1, t2) ->  t2.getDescripcion().compareTo(t1.getDescripcion())).collect(Collectors.toList());
                }
                break;
            case "fechaEjecucion":
                if(direccionOrdenamiento.equals("asc")){
                    tareas = tareas.stream().sorted((t1, t2) ->  t1.getFechaEjecucion().compareTo(t2.getFechaEjecucion())).collect(Collectors.toList());
                }else{
                    tareas = tareas.stream().sorted((t1, t2) ->  t2.getFechaEjecucion().compareTo(t1.getFechaEjecucion())).collect(Collectors.toList());
                }
                break;
            case "fechaCreacion":
                if(direccionOrdenamiento.equals("asc")){
                    tareas = tareas.stream().sorted((t1, t2) ->  t1.getFechaCreacion().compareTo(t2.getFechaCreacion())).collect(Collectors.toList());
                }else{
                    tareas = tareas.stream().sorted((t1, t2) ->  t2.getFechaCreacion().compareTo(t1.getFechaCreacion())).collect(Collectors.toList());
                }
                break;
            case "estado":
                if(direccionOrdenamiento.equals("asc")){
                    tareas = tareas.stream().sorted((t1, t2) ->  t1.getEstado().compareTo(t2.getEstado())).collect(Collectors.toList());
                }else{
                    tareas = tareas.stream().sorted((t1, t2) ->  t2.getEstado().compareTo(t1.getEstado())).collect(Collectors.toList());
                }
                break;
        }
        return tareas;
    }

}
