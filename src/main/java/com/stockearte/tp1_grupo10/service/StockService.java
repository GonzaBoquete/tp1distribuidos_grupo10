package com.stockearte.tp1_grupo10.service;

import java.util.List;

import com.stockearte.tp1_grupo10.model.Stock;

public interface StockService {

	public List<Stock> getAll();

	public void save(Stock producto);

	public Stock buscar(long id);

	public void eliminar(long id);
}
