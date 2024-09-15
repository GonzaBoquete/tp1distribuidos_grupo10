package com.stockearte.tp1_grupo10.service;

import java.util.List;

import com.stockearte.tp1_grupo10.model.Tienda;

public interface TiendaService {

	Tienda add(Tienda tienda);

	Tienda getOneById(Long codigo);

	List<Tienda> getAll();

	Tienda update(Tienda tienda, Long codigo);

	void delete(Long codigo);
}
