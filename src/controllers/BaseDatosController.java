package controllers;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import models.AlumnoModel;
import models.BaseDatosExceptionModel;
import models.BaseDatosModel;
import models.ExceptionModel;
import models.Tool;

public class BaseDatosController extends BaseDatosModel{
	
	public BaseDatosController() throws BaseDatosExceptionModel{
		super("config.tesis");
	}
	
	public BaseDatosController(String archivo) throws BaseDatosExceptionModel {
		super(archivo);
	}
	
	public void altaAlumno(AlumnoModel alumno) throws BaseDatosExceptionModel{
		String sql= "INSERT INTO usuario(grupo, tipoUsuario, pass, nombreCompleto, idUsuario) "
				+ "VALUES('"+ alumno.getGrupo()+ "','alumno', '" + alumno.getPass()+ "', '"+ alumno.getNombreCompleto()+ "', '"+ alumno.getIdentificacion()+ "');";
		super.realizarConexion();
		super.realizarAccion(sql);
		super.cerrarConexion();
	}
	
	public String consultarUsuario(String user, String pass) throws BaseDatosExceptionModel, SQLException{
		String tipoUsuario= "";
		String sql= "SELECT tipoUsuario "
				+ "FROM usuario "
				+ "WHERE idUsuario='"+ user+ "' and pass='"+ pass+ "';";
		realizarConexion();
		ResultSet resultados= realizarConsulta(sql);
		if(resultados.next()){
			tipoUsuario= resultados.getString("tipoUsuario");
		}
		resultados.close();
		cerrarConexion();
		
		return tipoUsuario.toLowerCase(); 
	}

	public String[][] obtenerListaAlumnos() throws BaseDatosExceptionModel, SQLException {
		String sql= "SELECT nombreCompleto, idUsuario, grupo "
				+ "FROM usuario "
				+ "WHERE tipoUsuario= 'alumno'";
		Vector<String[]> vector= new Vector<String[]>();
		realizarConexion();
		ResultSet resultados = realizarConsulta(sql);
		while (resultados.next()) {	
			vector.add(new String[] {resultados.getString("nombreCompleto"),
					resultados.getString("grupo"),
					resultados.getString("idUsuario")});
		}
		resultados.close();
		cerrarConexion();
		
		String[][] result= new String[vector.size()][];
		
		for(int i=0; i<vector.size(); i++){
			result[i]= vector.get(i);
		}
		
		return result;
	}

	public String[][] obtenerExamenes() {
		return new String[][] {
			{"Biología", "Parcial 1", "10.00"}
		};
	}

	public AlumnoModel obtenerAlumno(String id) throws SQLException, BaseDatosExceptionModel, NoSuchAlgorithmException {
		AlumnoModel alumno= null;
		
		String sql= "SELECT nombreCompleto, grupo "
				+ "FROM usuario "
				+ "WHERE tipoUsuario= 'alumno' AND idUsuario='"+ id+ "'";
		realizarConexion();
		ResultSet resultados = realizarConsulta(sql);
		
		if(resultados.next()){
			alumno= new AlumnoModel();
			alumno.setIdentificacion(id);
			alumno.setNombreCompleto(resultados.getString("nombreCompleto"));
			alumno.setGrupo(resultados.getString("grupo"));
			alumno.setPass("1991"); //clase alumno en el metodo setPass convierte a MD5
		}
		
		resultados.close();
		cerrarConexion();
		
		return alumno;
	}

	public void bajaAlumno(String identificacion) throws BaseDatosExceptionModel {
		String sql= "DELETE FROM usuario WHERE idUsuario=\""+ identificacion+ "\"";
		realizarConexion();
		realizarAccion(sql);
		cerrarConexion();
	}

	public void editarAlumno(AlumnoModel alumno) throws BaseDatosExceptionModel {
		String sql= "UPDATE usuario SET grupo='"+ alumno.getGrupo()+ "', "
				+ "pass='"+ alumno.getPass()+ "', "
				+ "nombreCompleto='"+ alumno.getNombreCompleto()+ "' "
				+ "WHERE idUsuario='"+ alumno.getIdentificacion()+ "'";
		super.realizarConexion();
		super.realizarAccion(sql);
		super.cerrarConexion();
	}
	
/*
	
	public void eliminarBicicleta(String noSerie) throws BaseDatosExceptionModel{
		realizarConexion();
		realizarAccion("DELETE FROM bicicleta WHERE noSerie='" + noSerie + "'");
		cerrarConexion();
	}
	
	public void insertarBicicleta(Model_Bicicleta bicicleta) throws BaseDatosExceptionModel {
		realizarConexion();
		realizarAccion("INSERT INTO bicicleta(noSerie, precio, cantidad, fabricante, sexo, accesorios, entregaDomicilio, comentarios) "
				+ "VALUES('" + bicicleta.getNumeroSerie() + "', " + bicicleta.getPrecio() + ", " + bicicleta.getCantidad() + ", '"
						+ bicicleta.getMarca() + "', '" + bicicleta.getSexo() + "', 0, 1, '" + bicicleta.getComentarios() + "')");
		cerrarConexion();
	}
	
	public ArrayList<Model_Bicicleta> consultarBicicleta() throws BaseDatosExceptionModel, Model_ExceptionBicicleta, SQLException {
		String[] accesorios= {"a", "b", "c", "d"};
		ArrayList<Model_Bicicleta> bicicletas= new ArrayList<Model_Bicicleta>();
		realizarConexion();
		ResultSet resultados = realizarConsulta("SELECT noSerie, precio, cantidad, fabricante, sexo, accesorios, entregaDomicilio, comentarios "
				+ "FROM bicicleta");
		while (resultados.next()) {
			bicicletas.add(new Model_Bicicleta(
					resultados.getInt("cantidad"),
					resultados.getString("comentarios"),
					Model_EnumFabricante.valueOf(resultados.getString("fabricante")),
					"rojo-azul",
					Model_EnumSexo.valueOf(resultados.getString("sexo")),
					true,
					resultados.getString("noSerie"),
					resultados.getFloat("precio"),
		}
		resultados.close();
		cerrarConexion();
		return bicicletas;
	}*/
}
