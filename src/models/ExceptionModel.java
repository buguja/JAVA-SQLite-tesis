package models;

public class ExceptionModel extends Exception {
	public final static String ELEJIR_FILA_DE_TABLA= "Por favor elija una fila de la tabla";
	public final static String DATOS_DE_ACCESO_INCORRECTOS= "El usuario o contraseña es incorrecto";
	public final static String VER_ALUMNO_NULL= "Na hay contenido del alumno para mostrar";
	
	public ExceptionModel(String msj) {
		super(msj);
	}
}
