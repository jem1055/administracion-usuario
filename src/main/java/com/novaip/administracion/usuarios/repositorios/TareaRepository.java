package com.novaip.administracion.usuarios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novaip.administracion.usuarios.entidades.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Integer>{

}
