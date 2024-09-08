package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import model.Stock;
import repository.StockRepository;

@Service("stockService")
public class StockServiceImpl implements StockService {

	@Autowired
	@Qualifier("stockRepository")
	private StockRepository stockRepository;

	@Override
	public List<Stock> getAll() {
		return (List<Stock>) stockRepository.findAll();
	}

	@Override
	public void save(Stock stock) {
		stockRepository.save(stock);
	}

	@Override
	public Stock buscar(long id) {
		return stockRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(long id) {
		stockRepository.deleteById(id);
	}

}
