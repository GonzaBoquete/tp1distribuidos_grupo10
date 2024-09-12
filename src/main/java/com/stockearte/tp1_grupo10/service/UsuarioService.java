package com.stockearte.tp1_grupo10.service;

import java.util.List;

import com.stockearte.tp1_grupo10.model.Usuario;

public interface UsuarioService {

	public List<Usuario> getAll();

	public void save(Usuario producto);

	public Usuario buscar(long id);

	public void eliminar(long id);
}
