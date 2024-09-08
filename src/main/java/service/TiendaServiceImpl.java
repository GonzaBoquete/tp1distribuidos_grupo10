package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import model.Tienda;
import repository.TiendaRepository;

@Service("tiendaService")
public class TiendaServiceImpl implements TiendaService {

	@Autowired
	@Qualifier("tiendaRepository")
	private TiendaRepository tiendaRepository;

	@Override
	public List<Tienda> getAll() {
		return (List<Tienda>) tiendaRepository.findAll();
	}

	@Override
	public void save(Tienda tienda) {
		tiendaRepository.save(tienda);
	}

	@Override
	public Tienda buscar(long codigo) {
		return tiendaRepository.findById(codigo).orElse(null);
	}

	@Override
	public void eliminar(long codigo) {
		tiendaRepository.deleteById(codigo);
	}

}
