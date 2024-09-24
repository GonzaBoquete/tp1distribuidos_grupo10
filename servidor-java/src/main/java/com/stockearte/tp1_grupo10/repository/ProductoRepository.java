package com.stockearte.tp1_grupo10.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stockearte.tp1_grupo10.model.Producto;
import com.stockearte.tp1_grupo10.model.Tienda;

@Repository("productoRepository")
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	List<Producto> findByNombreContainingAndCodigoAndTalleAndColor(String nombre, Long codigo, String talle, String color);

}
