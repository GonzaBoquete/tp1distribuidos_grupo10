package com.stockearte.tp1_grupo10.service;

import java.util.List;

import com.stockearte.tp1_grupo10.model.Tienda;
import com.stockearte.tp1_grupo10.model.Usuario;

public interface UsuarioService {

	Usuario add(Usuario usuario, Long idTienda);

	Usuario getOneById(Long id);

	List<Usuario> getAll();

	Usuario update(Usuario tienda, Long id);
	
	Usuario login(String nombreUsuario, String contrasena);
	
	List<Usuario> buscarUsuario(String nombre, Tienda tienda);
	
	List<Usuario> buscarUsuario(String nombre);
	
	List<Usuario> buscarUsuario(Tienda tienda);
}
