package com.novaip.administracion.usuarios.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novaip.administracion.usuarios.entidades.Usuario;
import com.novaip.administracion.usuarios.repositorios.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
		
	@PostMapping("/crear")
	public Usuario crear(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
}
