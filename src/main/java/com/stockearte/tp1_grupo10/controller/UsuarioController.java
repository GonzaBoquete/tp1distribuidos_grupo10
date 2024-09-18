package com.stockearte.tp1_grupo10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stockearte.tp1_grupo10.model.Usuario;
import com.stockearte.tp1_grupo10.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/add")
	public ResponseEntity add(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioService.add(usuario));
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/update/{codigo}")
	public ResponseEntity update(@RequestBody Usuario usuario, @PathVariable Long codigo) {
		return ResponseEntity.ok(usuarioService.update(usuario, codigo));
	}


	@GetMapping("/listAll")
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(usuarioService.getAll());
	}
	
		
}
