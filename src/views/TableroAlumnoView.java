package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class TableroAlumnoView extends JFrame {

	private JPanel contentPane;
	protected JMenuItem mntmSalir;
	protected JMenuItem mntmCrearPdf;
	protected JMenuItem mntmResolver;
	protected JMenuItem mntmDetalles;
	protected JMenuItem mntmManualDeUsuario;
	private JScrollPane scrollPane;
	protected JTable table;

	public TableroAlumnoView(String[][] contenido, String[] titulos) {
		setTitle("Tablero del alumno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		JMenu mnExamen = new JMenu("Examen");
		menuBar.add(mnExamen);
		
		mntmCrearPdf = new JMenuItem("Crear PDF");
		mnExamen.add(mntmCrearPdf);
		
		mntmResolver = new JMenuItem("Resolver");
		mnExamen.add(mntmResolver);
		
		mntmDetalles = new JMenuItem("Detalles");
		mnExamen.add(mntmDetalles);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		mntmManualDeUsuario = new JMenuItem("Manual de usuario");
		mnAyuda.add(mntmManualDeUsuario);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table= new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(table);
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void crearTabla(Object[][] contenido, String[] titulos){
		table.setModel(new DefaultTableModel(contenido, titulos));
	}
}
