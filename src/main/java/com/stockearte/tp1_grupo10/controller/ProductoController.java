package com.stockearte.tp1_grupo10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stockearte.tp1_grupo10.model.Producto;
import com.stockearte.tp1_grupo10.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@SuppressWarnings("rawtypes")
	@PostMapping("/add")
	public ResponseEntity add(@RequestBody Producto producto) {
		return ResponseEntity.ok(productoService.add(producto));
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/update/{codigo}")
	public ResponseEntity update(@RequestBody Producto producto, @PathVariable Long codigo) {
		return ResponseEntity.ok(productoService.update(producto, codigo));
	}

	@DeleteMapping("/delete/{codigo}")
	public ResponseEntity<String> deleteProducto(@PathVariable Long codigo) {
		productoService.delete(codigo);
	    return ResponseEntity.ok().build();
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Producto>> getAll() {
		return ResponseEntity.ok(productoService.getAll());
	}
}
