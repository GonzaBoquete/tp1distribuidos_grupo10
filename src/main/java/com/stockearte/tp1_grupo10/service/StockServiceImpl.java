package com.stockearte.tp1_grupo10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp1_grupo10.model.Stock;
import com.stockearte.tp1_grupo10.repository.StockRepository;

@Service("stockService")
public class StockServiceImpl implements StockService {

	@Autowired
	@Qualifier("stockRepository")
	private StockRepository stockRepository;
	
	@Override
	public Stock add(Stock stock) {
		return stockRepository.save(stock);
	}
	
	@Override
	public Stock getOneById(Long id) {
		Optional<Stock> stock = stockRepository.findById(id);
		return stock.isEmpty() ? null : stock.get();
	}	
	
	@Override
	public List<Stock> getAll() {
		return (List<Stock>) stockRepository.findAll();
	}

	@Override
	public Stock update(Stock stock, Long id) {
		Optional<Stock> foundStock = stockRepository.findById(id);
		if (!foundStock.isEmpty()) {
			foundStock.get().setTienda(stock.getTienda());
			foundStock.get().setProducto(stock.getProducto());
			return stockRepository.save(foundStock.get());
		}
		return null;
	}
	
	public Stock update(int cantidad, Long id) {
		Optional<Stock> foundStock = stockRepository.findById(id);
		if (!foundStock.isEmpty()) {
			foundStock.get().setCantidad(cantidad);
		}
		return null;
	}
	
	

	@Override
	public void eliminar(long id) {
		Optional<Stock> foundStock = stockRepository.findById(id);
		if (!foundStock.isEmpty()) {
			stockRepository.delete(foundStock.get());
		}
	}

}
