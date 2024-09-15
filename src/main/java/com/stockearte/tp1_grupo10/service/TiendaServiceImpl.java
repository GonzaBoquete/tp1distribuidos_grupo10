package com.stockearte.tp1_grupo10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp1_grupo10.model.Tienda;
import com.stockearte.tp1_grupo10.repository.TiendaRepository;

@Service("tiendaService")
public class TiendaServiceImpl implements TiendaService {

	@Autowired
	@Qualifier("tiendaRepository")
	private TiendaRepository tiendaRepository;
	
	@Override
	public Tienda add(Tienda tienda) {
		return productoRepository.save(tienda);
	}

	@Override
	public Tienda getOneById(Long codigo) {
		Optional<Tienda> tienda = tiendaRepository.findById(codigo);
		return tienda.isEmpty() ? null : tienda.get();
	}
	
	@Override
	public List<Tienda> getAll() {
		return (List<Tienda>) tiendaRepository.findAll();
	}

	@Override
	public Tienda update(Tienda tienda, Long codigo) {
		Optional<Tienda> foundTienda = tiendaRepository.findById(codigo);
		if (!foundTienda.isEmpty()) {
			foundTienda.get().setDireccion(tienda.getDireccion());
			foundTienda.get().setCiudad(tienda.getCiudad());
			foundTienda.get().setProvincia(tienda.getProvincia());
			foundTienda.get().setHabilitada(tienda.getHabilitada());
			return tiendaRepository.save(foundTienda.get());
		}
		return null;
	}
	
	@Override
	public void delete(long codigo) {
		Optional<Tienda> foundTienda = tiendaRepository.findById(codigo);
		if (!foundTienda.isEmpty()) {
			tiendaRepository.delete(foundTienda.get());
		}
	}

}
