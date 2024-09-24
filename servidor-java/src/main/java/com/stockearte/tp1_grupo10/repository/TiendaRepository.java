package com.stockearte.tp1_grupo10.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockearte.tp1_grupo10.model.Tienda;

@Repository("tiendaRepository")
public interface TiendaRepository extends JpaRepository<Tienda, Long> {	 
	List<Tienda> findByCodigoAndHabilitada(Long codigo, boolean habilitada);
}
