package com.stockearte.tp1_grupo10.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp1_grupo10.model.Producto;
import com.stockearte.tp1_grupo10.repository.ProductoRepository;

@Service("productoService")
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	@Qualifier("productoRepository")
	private ProductoRepository productoRepository;

	@Override
	public Producto add(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Producto getOneById(Long codigo) {
		Optional<Producto> producto = productoRepository.findById(codigo);
		return producto.isEmpty() ? null : producto.get();
	}

	@Override
	public List<Producto> getAll() {
		return (List<Producto>) productoRepository.findAll();
	}

	@Override
	public Producto update(Producto producto, Long codigo) {
		Optional<Producto> foundProducto = productoRepository.findById(codigo);
		if (!foundProducto.isEmpty()) {
			foundProducto.get().setColor(producto.getColor());
			foundProducto.get().setFoto(producto.getFoto());
			foundProducto.get().setNombre(producto.getNombre());
			foundProducto.get().setTalle(producto.getTalle());
			return productoRepository.save(foundProducto.get());
		}
		return null;
	}

	@Override
	public void delete(Long codigo) {
		Optional<Producto> foundProducto = productoRepository.findById(codigo);
		if (!foundProducto.isEmpty()) {
			productoRepository.delete(foundProducto.get());
		}
	}
	
	@Override
	public List<Producto> buscarProducto(String nombre, Long codigo, String talle, String color) {
		return productoRepository.findByNombreContainingAndCodigoAndTalleAndColor(nombre, codigo, talle, color);
	}

}
