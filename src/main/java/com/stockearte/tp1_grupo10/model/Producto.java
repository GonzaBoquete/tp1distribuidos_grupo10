package com.stockearte.tp1_grupo10.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@Column(name = "codigo", length = 10, nullable = false, unique = true)
	private Long codigo;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "talle", nullable = false)
	private String talle;

	@Column(name = "foto")
	private String foto;

	@Column(name = "color", nullable = false)
	private String color;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private List<Stock> stockList;

	public Producto(Long codigo, String nombre, String talle, String foto, String color, List<Stock> stockList) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.talle = talle;
		this.foto = foto;
		this.color = color;
		this.stockList = stockList;
	}

	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTalle() {
		return talle;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}

}
