package com.stockearte.tp1_grupo10.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.stockearte.tp1_grupo10.model.Usuario;
import com.stockearte.tp1_grupo10.repository.TiendaRepository;
import com.stockearte.tp1_grupo10.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TiendaRepository tiendaRepository;
	
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
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestParam String nombreUsuario, @RequestParam String contrasena) {
		try {
			// Llama al servicio para autenticar al usuario
			Usuario usuarioLogueado = usuarioService.login(nombreUsuario, contrasena);
			
			// Retorna el usuario con un código de estado 200 OK si el inicio de sesión es exitoso
            return new ResponseEntity<>(usuarioLogueado, HttpStatus.OK);
		} catch (RuntimeException e) {
			// Retorna un error 401 UNAUTHORIZED si el inicio de sesión falla
        	return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<Usuario> buscarUsuario(@RequestParam String nombre, @RequestParam Long tiendaId) {
		Optional<Tienda> optionalTienda = tiendaRepository.findById(tiendaId);
		
		if(!optionalTienda.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		Tienda tienda = optionalTienda.get();
		
		Usuario usuario = usuarioService.buscarUsuario(nombre, tienda);
		
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(usuario);
	}
	
	
		
}
