package com.stockearte.tp1_grupo10.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp1_grupo10.model.Tienda;
import com.stockearte.tp1_grupo10.model.Usuario;
import com.stockearte.tp1_grupo10.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario add(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario getOneById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.isEmpty() ? null : usuario.get();
	}
	
	@Override
	public List<Usuario> getAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Usuario update(Usuario usuario, Long id) {
		Optional<Usuario> foundUsuario = usuarioRepository.findById(id);
		if (!foundUsuario.isEmpty()) {
			foundUsuario.get().setNombreUsuario(usuario.getNombreUsuario());
			foundUsuario.get().setContrasena(usuario.getContrasena());
			foundUsuario.get().setTienda(usuario.getTienda());
			foundUsuario.get().setNombre(usuario.getNombre());
			foundUsuario.get().setApellido(usuario.getApellido());
			foundUsuario.get().setRol(usuario.getRol());
			foundUsuario.get().setHabilitado(usuario.isHabilitado());
			return usuarioRepository.save(foundUsuario.get());
		}
		return null;
	}
	
	@Override
	public Usuario login(String nombreUsuario, String contrasena) {
		Optional<Usuario> optionalUser = usuarioRepository.findByNombreUsuario(nombreUsuario);
		
		if(optionalUser.isPresent()) {
			Usuario user = optionalUser.get();
			
			if(user.getContrasena().equals(contrasena)) {
				return user;
			}else {
				throw new RuntimeException("Contraseña incorrecta");
			}
		} else {
			throw new RuntimeException("Usuario no encontrado");
		}
	}
	
	@Override
	public Usuario buscarUsuario(String nombre, Tienda tienda) {
		return usuarioRepository.findByNombreAndTienda(nombre, tienda);
	}

}
