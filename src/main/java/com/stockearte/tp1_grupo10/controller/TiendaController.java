package com.stockearte.tp1_grupo10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stockearte.tp1_grupo10.service.TiendaService;

@Controller
@RequestMapping("/tienda")
public class TiendaController {

	@Autowired
	private TiendaService tiendaService;
}
