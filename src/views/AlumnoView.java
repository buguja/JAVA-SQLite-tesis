package views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AlumnoView extends JDialog {
	protected JTextField textFieldNombreCompleto;
	protected JTextField textFieldIdentifiacion;
	protected JTextField textFieldGrupo;
	protected JButton btnLimpiar;
	protected JButton btnGuardar;
	protected JTextField textFieldPass;
	protected JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AlumnoView();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public AlumnoView() {
		setTitle("Título");
		setBounds(100, 100, 450, 279);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNombreCompleto = new JLabel("Nombre completo");
		lblNombreCompleto.setBounds(12, 12, 424, 15);
		getContentPane().add(lblNombreCompleto);
		
		textFieldNombreCompleto = new JTextField();
		textFieldNombreCompleto.setBounds(12, 30, 424, 19);
		getContentPane().add(textFieldNombreCompleto);
		textFieldNombreCompleto.setColumns(10);
		
		JLabel lblIdentificacinmattrculaO = new JLabel("Identificación (Mattrícula o E-Mail)");
		lblIdentificacinmattrculaO.setBounds(12, 61, 424, 15);
		getContentPane().add(lblIdentificacinmattrculaO);
		
		textFieldIdentifiacion = new JTextField();
		textFieldIdentifiacion.setBounds(12, 80, 424, 19);
		getContentPane().add(textFieldIdentifiacion);
		textFieldIdentifiacion.setColumns(10);
		
		JLabel lblGrupo = new JLabel("Grupo");
		lblGrupo.setBounds(12, 111, 70, 15);
		getContentPane().add(lblGrupo);
		
		textFieldGrupo = new JTextField();
		textFieldGrupo.setBounds(12, 129, 424, 19);
		getContentPane().add(textFieldGrupo);
		textFieldGrupo.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(319, 215, 117, 25);
		getContentPane().add(btnGuardar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(190, 215, 117, 25);
		getContentPane().add(btnLimpiar);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(12, 160, 424, 19);
		getContentPane().add(lblContrasea);
		
		textFieldPass = new JTextField();
		textFieldPass.setBounds(12, 181, 424, 19);
		getContentPane().add(textFieldPass);
		textFieldPass.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(61, 215, 117, 25);
		getContentPane().add(btnCancelar);
		setVisible(true);
	}
}
