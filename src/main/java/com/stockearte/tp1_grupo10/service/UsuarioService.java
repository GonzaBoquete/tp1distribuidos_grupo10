package com.stockearte.tp1_grupo10.service;

import java.util.List;


import com.stockearte.tp1_grupo10.model.Usuario;

public interface UsuarioService {

	Usuario add(Usuario tienda);

	Usuario getOneById(Long id);

	List<Usuario> getAll();

	Usuario update(Usuario tienda, Long id);

}
