package models;

public class RespuestaModel {
	private boolean tienePregunta= false;
	private String respuesta= "";
	private String idRespuesta= "";
	
	public boolean isTienePregunta() {
		return tienePregunta;
	}
	public void setTienePregunta(boolean tienePregunta, String respuesta) {
		this.tienePregunta = tienePregunta;
		setRespuesta(respuesta);
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getIdRespuesta() {
		return idRespuesta;
	}
	public void setIdRespuesta(String idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	public void generarNuevoId(){
		setIdRespuesta(Tool.generarId("R"));
	}
}
