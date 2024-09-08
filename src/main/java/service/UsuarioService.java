package service;

import java.util.List;

import model.Usuario;

public interface UsuarioService {

	public List<Usuario> getAll();

	public void save(Usuario producto);

	public Usuario buscar(long id);

	public void eliminar(long id);
}
