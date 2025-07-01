package com.app.usuario.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.usuario.model.entity.Usuario;
import com.app.usuario.service.IService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	private final IService usuarioService;

	public UsuarioController(IService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public List<Usuario> findAll() {// RequestBody transforma el Json en objeto
		return usuarioService.findAll();
	}

	@PostMapping("/post")
	public Usuario post(@RequestBody Usuario usuario) { // RequestBody transforma el Json en objeto
		return usuarioService.post(usuario);
	}

	@GetMapping("/{id}")
	public Usuario findById(@PathVariable Long id) {
		return usuarioService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		usuarioService.deleteById(id);
	}

	@PutMapping
	public Usuario update(@RequestBody Usuario usuario) {
		Usuario usuarioDb = usuarioService.findById(usuario.getId());

		usuarioDb.setNombre(usuario.getNombre());
		usuarioDb.setCorreo(usuario.getCorreo());
		usuarioDb.setContraseña(usuario.getContraseña());

		return usuarioService.update(usuarioDb);
	}
}
