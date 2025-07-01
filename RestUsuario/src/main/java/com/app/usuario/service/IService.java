package com.app.usuario.service;

import java.util.List;

import com.app.usuario.model.entity.Usuario;

public interface IService {
	Usuario post(Usuario usuario);

	List<Usuario> findAll();

	Usuario findById(Long id);

	void deleteById(Long id);

	Usuario update(Usuario usuario);
}
