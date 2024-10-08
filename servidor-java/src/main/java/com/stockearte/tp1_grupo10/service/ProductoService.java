package com.stockearte.tp1_grupo10.service;

import java.util.List;

import com.stockearte.tp1_grupo10.model.Producto;

public interface ProductoService {

	Producto add(Producto producto);

	Producto getOneById(Long codigo);

	List<Producto> getAll();

	Producto update(Producto producto, Long codigo);
	
	List<Producto> buscarProducto(String nombre, Long codigo, String talle, String color);
}
