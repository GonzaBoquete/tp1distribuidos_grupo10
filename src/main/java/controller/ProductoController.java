package controller;

import java.util.concurrent.CompletableFuture;

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

import model.Producto;
import service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@PostMapping("/add")
	public CompletableFuture<ResponseEntity> add(@RequestBody Producto producto) {
		productoService.add(producto);
		return CompletableFuture.completedFuture(ResponseEntity.ok("El usuario fue guardado con exito."));
	}

	@PutMapping("/update/{id}")
	public CompletableFuture<ResponseEntity> update(@RequestBody Producto producto, @PathVariable Long codigo) {
		productoService.update(producto, codigo);
		return CompletableFuture.completedFuture(ResponseEntity.ok("El usuario fue actualizado con exito."));
	}

	@DeleteMapping("/delete/{id}")
	public CompletableFuture<ResponseEntity> delete(@PathVariable Long codigo) {
		productoService.delete(codigo);
		return CompletableFuture.completedFuture(ResponseEntity.ok("El usuario fue eliminado con exito."));
	}

	@GetMapping("/listAll")
	public CompletableFuture<ResponseEntity> getAll() {
		return ((CompletableFuture<ResponseEntity>) productoService.getAll()).thenApply(ResponseEntity::ok);
	}
}
