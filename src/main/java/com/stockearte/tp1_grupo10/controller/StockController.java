package com.stockearte.tp1_grupo10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
}
