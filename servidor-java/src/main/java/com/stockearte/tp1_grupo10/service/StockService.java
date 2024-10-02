package com.stockearte.tp1_grupo10.service;

import java.util.List;

import com.stockearte.tp1_grupo10.model.Stock;

public interface StockService {
	
	Stock add(Stock stock, Long idTienda, Long idProducto);

	Stock getOneById(Long id);

	List<Stock> getAll();

	Stock update(Stock stock, Long id);
	
	Stock update(int cantidad, Long id);

	void delete(Long id);
	
}
