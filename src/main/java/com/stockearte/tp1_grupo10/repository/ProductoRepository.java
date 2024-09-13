package com.stockearte.tp1_grupo10.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stockearte.tp1_grupo10.model.Producto;

@Repository("productoRepository")
public interface ProductoRepository extends CrudRepository<Producto, Long> {

}