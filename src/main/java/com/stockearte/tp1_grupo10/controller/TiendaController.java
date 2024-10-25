package com.stockearte.tp1_grupo10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		return ResponseEntity.ok(tiendaService.update(tienda, codigo));
	}


	@GetMapping("/listAll")
	public ResponseEntity<List<Tienda>> getAll() {
		return ResponseEntity.ok(tiendaService.getAll());
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Tienda>> buscarTienda(
            @RequestParam(required = false) Long codigo,
            @RequestParam(required = false) Boolean habilitada) {
        
        List<Tienda> tiendas = tiendaService.buscarTienda(codigo, habilitada);
        
        if (tiendas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        return ResponseEntity.ok(tiendas);
    }
	
	
}
