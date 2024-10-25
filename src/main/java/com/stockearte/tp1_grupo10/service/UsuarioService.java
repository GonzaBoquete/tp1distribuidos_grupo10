package com.stockearte.tp1_grupo10.service;

import java.util.List;
import java.util.Optional;

import com.stockearte.tp1_grupo10.model.Tienda;
import com.stockearte.tp1_grupo10.model.Usuario;

public interface UsuarioService {

	Usuario add(Usuario tienda);

	Usuario getOneById(Long id);

	List<Usuario> getAll();

	Usuario update(Usuario tienda, Long id);
	
	Usuario login(String nombreUsuario, String contrasena);
	
	Usuario buscarUsuario(String nombre, Tienda tienda);
	
	Usuario buscarUsuario(String nombre);

}
