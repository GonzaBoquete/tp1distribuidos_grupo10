package com.stockearte.tp1_grupo10.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tienda")
public class Tienda {

	@Id
	@Column(name = "codigo", length = 10, nullable = false, unique = true)
	private Long codigo;

	@Column(name = "direccion", nullable = false)
	private String direccion;

	@Column(name = "ciudad", nullable = false)
	private String ciudad;

	@Column(name = "provincia", nullable = false)
	private String provincia;

	@Column(name = "habilitada", nullable = false)
	private boolean habilitada;
	
	public Tienda(Long codigo, String direccion, String ciudad, String provincia, boolean habilitada) {
		super();
		this.codigo = codigo;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.habilitada = habilitada;
	}

	public Tienda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}

}
