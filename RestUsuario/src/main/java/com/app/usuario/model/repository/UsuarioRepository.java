package com.app.usuario.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.usuario.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

}
