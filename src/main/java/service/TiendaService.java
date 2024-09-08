package service;

import java.util.List;

import model.Tienda;

public interface TiendaService {

	public List<Tienda> getAll();

	public void save(Tienda producto);

	public Tienda buscar(long codigo);

	public void eliminar(long codigo);
}
