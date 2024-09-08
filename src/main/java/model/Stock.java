package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "tienda_id", nullable = false)
	private Tienda tienda;

	@ManyToOne
	@JoinColumn(name = "producto_codigo", nullable = false)
	private Producto producto;

	@Column(name = "talle", nullable = false)
	private String talle;

	@Column(name = "color", nullable = false)
	private String color;

	@Column(name = "cantidad", nullable = false)
	private int cantidad;

	public Stock(Long id, Tienda tienda, Producto producto, String talle, String color, int cantidad) {
		super();
		this.id = id;
		this.tienda = tienda;
		this.producto = producto;
		this.talle = talle;
		this.color = color;
		this.cantidad = cantidad;
	}

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getTalle() {
		return talle;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
