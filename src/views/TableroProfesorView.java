package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class TableroProfesorView extends JFrame {

	private JPanel contentPane;
	protected JMenuItem mntmManualDeUsuario;
	protected JMenuItem mntmDetallesExamen;
	protected JMenuItem mntmNuevoExamen;
	protected JMenuItem mntmEliminarExamen;
	protected JMenuItem mntmEditarExamen;
	protected JMenuItem mntmCrearPdfExamen;
	protected JMenuItem mntmEditarAlumno;
	protected JMenuItem mntmEliminarAlumno;
	protected JMenuItem mntmNuevoAlumno;
	protected JMenuItem mntmVerAlumno;
	protected JMenuItem mntmSalir;
	protected JMenuItem mntmAlumnos;
	protected JMenuItem mntmExamenes;
	protected JTable table;
	protected JMenu mnAlumno;
	protected JMenu mnExamen;

	public TableroProfesorView() {
		setTitle("Tablero del profesor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		mnAlumno = new JMenu("Alumno");
		menuBar.add(mnAlumno);
		
		mntmEditarAlumno = new JMenuItem("Editar");
		mnAlumno.add(mntmEditarAlumno);
		
		mntmEliminarAlumno = new JMenuItem("Eliminar");
		mnAlumno.add(mntmEliminarAlumno);
		
		mntmNuevoAlumno = new JMenuItem("Nuevo");
		mnAlumno.add(mntmNuevoAlumno);
		
		mntmVerAlumno = new JMenuItem("Ver");
		mnAlumno.add(mntmVerAlumno);
		
		mnExamen = new JMenu("Examen");
		menuBar.add(mnExamen);
		
		mntmCrearPdfExamen = new JMenuItem("Crear PDF");
		mnExamen.add(mntmCrearPdfExamen);
		
		mntmEditarExamen = new JMenuItem("Editar");
		mnExamen.add(mntmEditarExamen);
		
		mntmEliminarExamen = new JMenuItem("Eliminar");
		mnExamen.add(mntmEliminarExamen);
		
		mntmNuevoExamen = new JMenuItem("Nuevo");
		mnExamen.add(mntmNuevoExamen);
		
		mntmDetallesExamen = new JMenuItem("Detalles");
		mnExamen.add(mntmDetallesExamen);
		
		JMenu mnVer = new JMenu("Ver");
		menuBar.add(mnVer);
		
		mntmAlumnos = new JMenuItem("Alumnos");
		mnVer.add(mntmAlumnos);
		
		mntmExamenes = new JMenuItem("Exámenes");
		mnVer.add(mntmExamenes);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		mntmManualDeUsuario = new JMenuItem("Manual de usuario");
		mnAyuda.add(mntmManualDeUsuario);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table= new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		setVisible(true);
	}
	
	public void agregarContenidoTabla(String[][] contenido, String[] titulos){
		table.setModel(new DefaultTableModel(contenido, titulos));
	}
}
