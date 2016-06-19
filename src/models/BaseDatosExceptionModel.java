package models;

public class BaseDatosExceptionModel extends Exception {
	private static final long serialVersionUID = 1L;

	public BaseDatosExceptionModel(String mensaje) {
		super(mensaje);
	}
}
