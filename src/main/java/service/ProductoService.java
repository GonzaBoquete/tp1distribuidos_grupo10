package service;

import java.util.List;

import model.Producto;

public interface ProductoService {

	public List<Producto> getAll();

	public void add(Producto producto);
	
	public void update(Producto producto, Long codigo);

	public Producto geyByCode(long codigo);

	public void delete(long codigo);
}
