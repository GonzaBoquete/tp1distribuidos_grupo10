package com.stockearte.tp1_grupo10.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stockearte.tp1_grupo10.model.Producto;
import com.stockearte.tp1_grupo10.model.Stock;

@Repository("stockRepository")
public interface StockRepository extends JpaRepository<Stock, Long> {
}
