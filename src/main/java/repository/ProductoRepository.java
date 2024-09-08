package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Producto;

@Repository("productoRepository")
public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
