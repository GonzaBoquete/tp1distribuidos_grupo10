package com.stockearte.tp1_grupo10.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp1_grupo10.model.Producto;
import com.stockearte.tp1_grupo10.model.Stock;
import com.stockearte.tp1_grupo10.model.Tienda;
import com.stockearte.tp1_grupo10.repository.StockRepository;

@Service("stockService")
public class StockServiceImpl implements StockService {

	@Autowired
	@Qualifier("stockRepository")
	private StockRepository stockRepository;

	@Autowired
	private TiendaService tiendaService;

	@Autowired
	private ProductoService productoService;

	@Override
	public Stock add(Stock stock, Long idTienda, Long idProducto) {
		Tienda tienda = getTiendaService().getOneById(idTienda);
		if (tienda != null) {
			stock.setTienda(tienda);
		} else {
			throw new ServiceException("No se encontro la tienda");
		}
		Producto producto = getProductoService().getOneById(idProducto);
		if (producto != null) {
			stock.setProducto(producto);
		} else {
			throw new ServiceException("No se encontro el producto");
		}
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
	public void delete(Long id) {
		Optional<Stock> foundStock = stockRepository.findById(id);
		if (!foundStock.isEmpty()) {
			stockRepository.delete(foundStock.get());
		}
	}

	public TiendaService getTiendaService() {
		return tiendaService;
	}

	public void setTiendaService(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

}
