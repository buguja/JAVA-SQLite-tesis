package views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Box;

public class ExamenAltaCambioView extends JDialog {
	protected JTextField textFieldPregunta;
	protected JButton btnAgregar;
	protected JButton btnEliminar;
	protected JButton btnGuardar;
	protected JPanel panelRespuestas;
	protected Box verticalBoxPreguntas;
	
	public ExamenAltaCambioView() {
		setBounds(100, 100, 600, 450);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane);
		
		verticalBoxPreguntas = Box.createVerticalBox();
		scrollPane.setViewportView(verticalBoxPreguntas);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane_1);
		
		panelRespuestas = new JPanel();
		scrollPane_1.setViewportView(panelRespuestas);
		
		JPanel panelTop = new JPanel();
		getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPregunta = new JLabel("Pregunta");
		lblPregunta.setHorizontalAlignment(SwingConstants.LEFT);
		panelTop.add(lblPregunta, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panelTop.add(panel, BorderLayout.SOUTH);
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		textFieldPregunta = new JTextField();
		textFieldPregunta.setColumns(35);
		panel.add(textFieldPregunta);
		
		btnAgregar = new JButton("Agregar");
		panel.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		panel.add(btnEliminar);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		panel_2.add(btnGuardar);
		setVisible(true);
	}
}
