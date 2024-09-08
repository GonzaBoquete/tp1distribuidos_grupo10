package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Stock;

@Repository("stockRepository")
public interface StockRepository extends CrudRepository<Stock, Long> {

}
