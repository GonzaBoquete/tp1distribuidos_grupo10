package enumerators;

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
