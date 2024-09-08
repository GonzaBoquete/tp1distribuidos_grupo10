package service;

import java.util.List;

import model.Producto;

public interface ProductoService {

	public List<Producto> getAll();

	public void save(Producto producto);

	public Producto buscar(long codigo);

	public void eliminar(long codigo);
}
