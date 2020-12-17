package com.novaip.administracion.usuarios.repositorios;

import com.novaip.administracion.usuarios.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.novaip.administracion.usuarios.entidades.Tarea;

import java.util.Date;
import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Integer>{

}
