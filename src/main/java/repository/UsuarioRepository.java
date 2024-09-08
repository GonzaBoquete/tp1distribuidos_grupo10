package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
