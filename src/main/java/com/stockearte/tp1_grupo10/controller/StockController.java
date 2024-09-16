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

import com.stockearte.tp1_grupo10.model.Stock;
import com.stockearte.tp1_grupo10.service.StockService;

@Controller
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private StockService stockService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/add")
	public ResponseEntity add(@RequestBody Stock stock) {
		return ResponseEntity.ok(stockService.add(stock));
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/update/{id}")
	public ResponseEntity update(@RequestBody Stock stock, @PathVariable Long id) {
		return ResponseEntity.ok(stockService.update(stock, id));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStock(@PathVariable Long id) {
		stockService.delete(id);
	    return ResponseEntity.ok().build();
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Stock>> getAll() {
		return ResponseEntity.ok(stockService.getAll());
	}
}
