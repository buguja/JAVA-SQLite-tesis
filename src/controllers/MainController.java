package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import client.Cliente;
import models.AlumnoModel;
import models.BaseDatosExceptionModel;
import models.ExamenModel;
import models.ExceptionModel;
import models.PreguntaModel;
import models.Tool;

public class MainController extends Cliente implements ActionListener, WindowListener, MouseListener, Runnable{
	private AccesoController accesoController= null;
	private TableroAlumnoController tableroAlumnoController= null;
	private TableroProfesorController tableroProfesorController= null;
	private AlumnoController alumnoController= null;
	private ExamenAltaCambioController examenAltaCambioController= null;
	
	public MainController() throws UnknownHostException, IOException{
		this.accesoController= new AccesoController(this);
	}
	
	private void destruirAccesoController(){
		this.accesoController.cerrarVentana();
		this.accesoController= null;
	}
	
	private void destruirTableroAlumnoController(){
		this.tableroAlumnoController.cerrarVentana();
		this.tableroAlumnoController= null;
	}
	
	private void destruirTableroProfesorController(){
		this.tableroProfesorController.cerrarVentana();
		this.tableroProfesorController= null;
	}
	
	private void destruirAlumnoController(){
		this.alumnoController.cerrarVentana();
		this.alumnoController= null;
	}
	
	private void destruirExamenAltaCambioController(){
		this.examenAltaCambioController.cerrarVentana();
		this.examenAltaCambioController= null;
	}
	
	/**
	 * Convierte el json recibido a una matriz de objeto
	 * @param json objeto tipo json recibido
	 * @return matriz de objeto[][]
	 */
	private Object[][] convertirJsonEnMatriz(JSONObject json){
		JSONArray jsonArray= (JSONArray) json.get("contenido");
		JSONArray tmp= null;
		Object[][] contenido= null;
		
		contenido= new Object[jsonArray.size()][];
		for(int i=0; i<jsonArray.size(); i++){
			tmp= (JSONArray) jsonArray.get(i);
			contenido[i]= tmp.toArray();
		}
		return contenido;
	}
	
	/**
	 * Cambia el contenido de la tabla en el tablero del alumno
	 * @param json Objeto con el contenido de la tabla
	 */
	private void cambiarTablaMateriaEnAlumno(JSONObject json){		
		this.tableroAlumnoController.crearTabla(
				convertirJsonEnMatriz(json), new String[] {"Materia", "Descripción", "Calificación"}
		);
	}
	
	/**
	 * cambia el contenido de la tabla materias, en el tablero del profesor
	 * @param json Objeto con el contenido de la tabla
	 */
	private void cambiarTablaMateriaEnEvaluador(JSONObject json){
		this.tableroProfesorController.agregarContenidoTabla(
			convertirJsonEnMatriz(json), new String[] {"Nombre completo", "Grupo", "Identificación"} 
		);
	}
	
	/**
	 * Cambia el contenido de la tabla alumnos, en el tablero del profesor
	 * @param json Objeto con el contenido de la tabla
	 */
	private void cambiarDatosTablaAlumnoEnEvaluador(JSONObject json) {
		this.tableroProfesorController.agregarContenidoTabla(
			convertirJsonEnMatriz(json), new String[] {"Nombre completo", "Grupo", "Identificación"} 
		);
	}
	
	private void accesar(String result)throws ExceptionModel, BaseDatosExceptionModel, SQLException, IOException{
		switch(result){
			case "profesor":
				this.destruirAccesoController();
				this.tableroProfesorController= new TableroProfesorController(this);
				super.enviarMsg(
					this.tableroProfesorController.crearTablaExamenes()
				);
			break;
			case "alumno":
				this.destruirAccesoController();
				this.tableroAlumnoController= new TableroAlumnoController(this, null, null);
				super.enviarMsg(this.tableroAlumnoController.funcionalidadPedirMaterias());
			break;
			default: throw new ExceptionModel(ExceptionModel.DATOS_DE_ACCESO_INCORRECTOS);
		}
	}
	
	private void accionEventosAccesar(Object source) throws BaseDatosExceptionModel, SQLException, NoSuchAlgorithmException, ExceptionModel, IOException{
		// ACCESO
		if(source == this.accesoController.getBtnCancelar()){
			this.accesoController.funcionalidadCancelar();
			this.accesoController= null;
			System.exit(0);
		}else if(source == this.accesoController.getBtnAcceder()){
			//this.accesar();
			super.enviarMsg(this.accesoController.funcionalidadAcceder());
		}
	}
	
	private void accionEventosTableroAlumno(Object source){
		// TABLERO - ALUMNO
		if(source == this.tableroAlumnoController.getMntmSalir()){
			//System.out.println("Salir");
			this.destruirTableroAlumnoController();
			System.exit(0);
		}else if(source == this.tableroAlumnoController.getMntmCrearPDF()){
			System.out.println("CREAR PDF");
		}else if(source == this.tableroAlumnoController.getMntmResolver()){
			System.out.println("Resolver");
		}else if(source == this.tableroAlumnoController.getMntmDetalles()){
			System.out.println("Detalles");
		}else if(source == this.tableroAlumnoController.getMntmManualDeUsuario()){
			System.out.println("Manual");
		}/*else // ************* EXAMEN - ALUMNO - PROFESOR *************
		if(e.getSource().equals(this.tableroAlumnoController.getMntmManualDeUsuario())){
			//System.out.println("Manual");
		}*/
	}
	
	private void accionEventosTableroProfesor(Object source) throws BaseDatosExceptionModel, SQLException, ExceptionModel, NoSuchAlgorithmException, IOException{
		// TABLERO - PROFESOR
		if(source == this.tableroProfesorController.getMntmSalir()){
			this.destruirTableroProfesorController();
			//System.exit(0);
		}else if(source == this.tableroProfesorController.getMntmEditarAlumno()){
			//Menu item. Editar alumno
			this.alumnoController= new AlumnoController(this, this,
					this.tableroProfesorController.funcionalidadObtenerAlumno(),
					true);
		}else if(source == this.tableroProfesorController.getMntmEliminarAlumno()){
			//Menu item. Eliminar alumno
			this.tableroProfesorController.funcionalidadEliminarAlumno();
		}else if(source == this.tableroProfesorController.getMntmNuevoAlumno()){
			//Menu item. Nuevo alumno
			this.alumnoController= new AlumnoController(this, this);
		}else if(source == this.tableroProfesorController.getMntmVerAlumno()){
			//Menu item. Ver alumno
			//BaseDatosController bdController= new BaseDatosController();
			this.alumnoController= new AlumnoController(this, this, 
					this.tableroProfesorController.funcionalidadObtenerAlumno(), 
					false);
		}else if(source == this.tableroProfesorController.getMntmCrearPdfExamen()){
			//Menu item crear pdf
			System.out.println("Crear PDF examen");
		}else if(source == this.tableroProfesorController.getMntmEditarExamen()){
			//Menu item editar examen
			ExamenModel examenModel= new ExamenModel("Matemáticas");
			this.examenAltaCambioController= new ExamenAltaCambioController(this, this, this, examenModel);
		}else if(source == this.tableroProfesorController.getMntmEliminarExamen()){
			//Menu item eliminar examen
			System.out.println("Eliminar examen");
		}else if(source == this.tableroProfesorController.getMntmNuevoExamen()){
			//Menu item nuevo examen
			this.examenAltaCambioController= new ExamenAltaCambioController(this, this, this);
		}else if(source == this.tableroProfesorController.getMntmDetallesExamen()){
			//Menu item detalles de examen
			System.out.println("Detalles examen");
		}else if(source == this.tableroProfesorController.getMntmAlumnos()){
			//Menu item ver alumnos
			super.enviarMsg(
				this.tableroProfesorController.crearTablaAlumnos()
			);
		}else if(source == this.tableroProfesorController.getMntmExamenes()){
			//Menu item ver examenes
			super.enviarMsg(
				this.tableroProfesorController.crearTablaExamenes()
			);
		}else if(source == this.tableroProfesorController.getMntmManualDeUsuario()){
			System.out.println("Manual de usuario");
		}
	}
	
	private void accionEventosAlumno(Object source) throws BaseDatosExceptionModel, NoSuchAlgorithmException, SQLException{
		// VENTANA ALUMNOS
		if(source == this.alumnoController.getBtnCancelar()){
			this.alumnoController.funcionalidadCancelar();
			this.alumnoController= null;
		}else if(source == this.alumnoController.getBtnLimpiar()){
			this.alumnoController.funcionalidadLimpiar();
		}else if(source == this.alumnoController.getBtnGuardar()){
			if(this.alumnoController.funcionalidadGuardar()){
				this.alumnoController.funcionalidadCancelar();
				this.alumnoController= null;
			}
			this.tableroProfesorController.crearTablaAlumnos();
			JOptionPane.showMessageDialog(null, "Acción terminada correctamente.");
		}
	}
	
	private void accionEventosExamenAltaCambioController(Object source){
		if(source == this.examenAltaCambioController.getBtnAgregar()){
			this.examenAltaCambioController.funcionalidadAgregar();
		}else if(source == this.examenAltaCambioController.getBtnEliminar()){
			this.examenAltaCambioController.funcionalidadEliminar();
		}
	}
	
	public void actionPerformed(ActionEvent e){
		try{
			if(this.accesoController != null){
				this.accionEventosAccesar(e.getSource());
			}else if(this.alumnoController != null){
				this.accionEventosAlumno(e.getSource());
			}else if(this.examenAltaCambioController != null){
				this.accionEventosExamenAltaCambioController(e.getSource());
			}else if(this.tableroAlumnoController != null){
				this.accionEventosTableroAlumno(e.getSource());
			}else if(this.tableroProfesorController != null){
				this.accionEventosTableroProfesor(e.getSource());
			}
		}catch(Exception exception){
			//exception.printStackTrace();
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}
	}
	
	public void run() {
    	while(true){
    		try {
				JSONObject json= Tool.stringToJSON(super.in.readUTF());
				switch(json.get("metodo").toString()){
					case "accederComo":
						this.accesar(json.get("resultadoMetodo").toString());
					break;
					case "cambiarDatosTablaMateriaEnAlumno":
						this.cambiarTablaMateriaEnAlumno(json);
					break;
					case "cambiarDatosTablaMateriaEnEvaluador":
						cambiarTablaMateriaEnEvaluador(json);
					break;
					case "cambiarDatosTablaAlumnoEnEvaluador":
						cambiarDatosTablaAlumnoEnEvaluador(json);
					break;
				}
        	}catch (IOException | ExceptionModel | BaseDatosExceptionModel | SQLException e) {
    			JOptionPane.showMessageDialog(null, e.getMessage());
    		}
		}
    }

	// WINDOW LISTENER
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub	
	}
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	public void windowClosing(WindowEvent e) {
		if(this.alumnoController != null){
			this.destruirAlumnoController();
		}else if(this.examenAltaCambioController != null){
			this.destruirExamenAltaCambioController();
		}
	}
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	
	//MOUSE LISTENER
	public void mouseClicked(MouseEvent e) {
		//System.out.println(this.examenAltaCambioController.jPanelPreguntas.indexOf(e.getSource()));
		this.examenAltaCambioController.getBtnGuardar().setEnabled(false);
		this.examenAltaCambioController.getBtnEliminar().setEnabled(true);
		this.examenAltaCambioController.colocarPreguntaEnTextField(this.examenAltaCambioController.jPanelPreguntas.indexOf(e.getSource()));
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}