package com.stockearte.tp1_grupo10.enumerators;

public enum Rol {
	CASA_CENTRAL("Casa Central"),
	TIENDA("Tienda");

	private final String value;
	
	Rol(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
