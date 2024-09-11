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
	public CompletableFuture<ResponseEntity> postRecord(@RequestBody Producto producto) {
		productoService.save(producto);
		return CompletableFuture.completedFuture(ResponseEntity.ok("El usuario fue guardado con exito."));
	}

	@GetMapping("/listAll")
	public CompletableFuture<ResponseEntity> getAll() {
		return ((CompletableFuture<ResponseEntity>) productoService.getAll()).thenApply(ResponseEntity::ok);
	}

	@PutMapping("/update/{id}")
	public CompletableFuture<ResponseEntity> update(@RequestBody Producto producto, @PathVariable Long codigo) {
		Producto productToModify = productoService.buscar(codigo);
		productToModify.setColor(producto.getColor());
		productToModify.setFoto(producto.getFoto());
		productToModify.setNombre(producto.getNombre());
		productToModify.setStockList(producto.getStockList());
		productToModify.setTalle(producto.getTalle());
		productoService.save(productToModify);
		return CompletableFuture.completedFuture(ResponseEntity.ok("El usuario fue actualizado con exito."));
	}

	@DeleteMapping("/delete/{id}")
    public CompletableFuture<ResponseEntity> delete(@PathVariable Long codigo){
    	try {
    		productoService.eliminar(codigo);
    	} catch(Exception e) {
    		return CompletableFuture.completedFuture(ResponseEntity.internalServerError().body("Se proujo un error eliminando el usuario."));
    	}
    	return CompletableFuture.completedFuture(ResponseEntity.ok("El usuario fue eliminado con exito."));
    }
}
