package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import model.Usuario;
import repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> getAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public Usuario buscar(long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(long id) {
		usuarioRepository.deleteById(id);
	}

}
