package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Tienda;

@Repository("tiendaRepository")
public interface TiendaRepository extends CrudRepository<Tienda, Long> {

}