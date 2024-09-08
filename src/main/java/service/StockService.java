package service;

import java.util.List;

import model.Stock;

public interface StockService {

	public List<Stock> getAll();

	public void save(Stock producto);

	public Stock buscar(long id);

	public void eliminar(long id);
}
