package com.stockearte.tp1_grupo10.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stockearte.tp1_grupo10.model.Tienda;
import com.stockearte.tp1_grupo10.model.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	Usuario findByNombreAndTienda(String nombre, Tienda tienda);
	Usuario findByNombre(String nombre);
}


	