package com.app.usuario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.usuario.exception.ResourceNotFoundException;
import com.app.usuario.model.entity.Usuario;
import com.app.usuario.model.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario post(Usuario usuario) {
		try {
			return usuarioRepository.save(usuario);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException("No se encontró el usuario con ID: " + id);
		});
	}

	@Override
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public Usuario update(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

}
