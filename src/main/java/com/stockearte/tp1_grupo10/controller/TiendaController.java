package com.stockearte.tp1_grupo10.controller;

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

import com.stockearte.tp1_grupo10.model.Tienda;
import com.stockearte.tp1_grupo10.service.TiendaService;

@Controller
@RequestMapping("/tienda")
public class TiendaController {

	@Autowired
	private TiendaService tiendaService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/add")
	public ResponseEntity add(@RequestBody Tienda tienda) {
		return ResponseEntity.ok(tiendaService.add(tienda));
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/update/{codigo}")
	public ResponseEntity update(@RequestBody Tienda tienda, @PathVariable Long codigo) {
		return ResponseEntity.ok(tiendaService.update(tienda, id));
	}

	@DeleteMapping("/delete/{codigo}")
	public ResponseEntity<String> deleteTienda(@PathVariable Long codigo) {
		tiendaService.delete(id);
	    return ResponseEntity.ok().build();
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Tienda>> getAll() {
		return ResponseEntity.ok(tiendaService.getAll());
	}
}
