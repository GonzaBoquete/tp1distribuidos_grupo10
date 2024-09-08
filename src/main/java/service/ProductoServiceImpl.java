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
	public List<Producto> getAll() {
		return (List<Producto>) productoRepository.findAll();
	}

	@Override
	public void save(Producto producto) {
		productoRepository.save(producto);
	}

	@Override
	public Producto buscar(long codigo) {
		return productoRepository.findById(codigo).orElse(null);
	}

	@Override
	public void eliminar(long codigo) {
		productoRepository.deleteById(codigo);
	}

}
