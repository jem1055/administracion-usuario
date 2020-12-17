package com.novaip.administracion.usuarios.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.novaip.administracion.usuarios.entidades.Usuario;
import com.novaip.administracion.usuarios.repositorios.UsuarioRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuarios")
public class UsuarioController {

	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/")
	public List<Usuario> obtenerTodos(){
		return usuarioRepository.findAll();
	}
		
	@PostMapping("/crear")
	public Usuario crear(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@GetMapping("/obtener/{usuarioId}")
	public Usuario obtener(@PathVariable int usuarioId){
		return usuarioRepository.findById(usuarioId).get();
	}

	@DeleteMapping("/eliminar/{usuarioId}")
	public void eliminar(@PathVariable int usuarioId){
		usuarioRepository.deleteById(usuarioId);
	}

	@PutMapping("/actualizar/{usuarioId}")
	public Usuario actualizar(@PathVariable int usuarioId, @RequestBody Usuario usuario){
		Usuario user = usuarioRepository.findById(usuarioId).get();

		if(usuario.getNombres() != null && !usuario.getNombres().isEmpty()) user.setNombres(usuario.getNombres());
		if(usuario.getApellidos() != null && !usuario.getApellidos().isEmpty()) user.setApellidos(usuario.getApellidos());
		if(usuario.getEmail() != null && !usuario.getEmail().isEmpty()) user.setEmail(usuario.getEmail());
		if(usuario.getEstado() != null && !usuario.getEstado().isEmpty()) user.setEstado(usuario.getEstado());
		if(usuario.getFechaCreacion() != null) user.setFechaCreacion(usuario.getFechaCreacion());

		return usuarioRepository.save(user);
	}
}
