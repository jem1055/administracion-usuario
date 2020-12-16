package com.novaip.administracion.usuarios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novaip.administracion.usuarios.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
