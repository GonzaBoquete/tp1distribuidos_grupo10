package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import model.Producto;
import repository.ProductoRepository;

@Service("productoService")
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	@Qualifier("productoRepository")
	private ProductoRepository productoRepository;

	@Override
	public void add(Producto producto) {
		productoRepository.save(producto);
	}
	
	@Override
	public void update(Producto producto, Long codigo) {
		Producto productToModify = this.geyByCode(codigo);
		productToModify.setColor(producto.getColor());
		productToModify.setFoto(producto.getFoto());
		productToModify.setNombre(producto.getNombre());
		productToModify.setStockList(producto.getStockList());
		productToModify.setTalle(producto.getTalle());
		productoRepository.save(productToModify);
	}

	@Override
	public void delete(long codigo) {
		productoRepository.deleteById(codigo);
	}
	
	@Override
	public Producto geyByCode(long codigo) {
		return productoRepository.findById(codigo).orElse(null);
	}
	
	@Override
	public List<Producto> getAll() {
		return (List<Producto>) productoRepository.findAll();
	}

}
