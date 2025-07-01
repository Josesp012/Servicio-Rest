package com.app.usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.usuario.model.entity.Usuario;
import com.app.usuario.model.repository.UsuarioRepository;
import com.app.usuario.service.UsuarioServiceImpl;

class UsuarioServicioTest {
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
    private UsuarioServiceImpl usuarioService;
	
	private Usuario usuario;
	
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setCorreo("correo");
        usuario.setContraseña("contraseña");
    }
	
	@Test
    void testPost_Success() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.post(usuario);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(usuarioRepository).save(usuario);
    }
	
	@Test
    void testPost_Failure_ReturnsNull() {
        when(usuarioRepository.save(usuario)).thenThrow(RuntimeException.class);

        Usuario result = usuarioService.post(usuario);

        assertNull(result);
    }
	
	@Test
    void testFindAll() {
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.findAll();

        assertEquals(1, result.size());
        verify(usuarioRepository).findAll();
    }
	
	@Test
    void testFindById_Found() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.findById(1L);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(usuarioRepository).findById(1L);
    }
	/*
	@Test
    void testFindById_NotFound_ThrowsException() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            usuarioService.findById(1L);
        });
    }
	*/
	@Test
    void testDeleteById() {
        doNothing().when(usuarioRepository).deleteById(1L);

        usuarioService.deleteById(1L);

        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void testUpdate() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.update(usuario);

        assertEquals(usuario.getNombre(), result.getNombre());
        verify(usuarioRepository).save(usuario);
    }

}
