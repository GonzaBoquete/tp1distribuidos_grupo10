package com.stockearte.tp1_grupo10.model;

import com.stockearte.tp1_grupo10.enumerators.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "nombre_usuario", nullable = false, unique = true)
	private String nombreUsuario;

	@Column(name = "contrasena", nullable = false)
	private String contrasena;

	@ManyToOne
	@JoinColumn(name = "tienda_id", nullable = true)
	private Tienda tienda;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "apellido", nullable = false)
	private String apellido;

	@Enumerated(EnumType.STRING)
	@Column(name = "rol", nullable = false)
	private Rol rol;

	@Column(name = "habilitado", nullable = false)
	private boolean habilitado;

	public Usuario(Long id, String nombreUsuario, String contrasena, Tienda tienda, String nombre, String apellido,
			Rol rol, boolean habilitado) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.tienda = tienda;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
		this.habilitado = habilitado;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

}
