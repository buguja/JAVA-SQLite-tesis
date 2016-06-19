package views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Window.Type;
import java.awt.Dialog.ModalityType;

public class AccesoView extends JDialog{
	protected JTextField usuario;
	protected JButton btnCancelar;
	protected JButton btnAcceder;
	protected JPasswordField contraseña;

	/**
	 * Create the dialog.
	 */
	public AccesoView(){
		setTitle("Acceso");
		setBounds(100, 100, 330, 175);
		getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(12, 12, 70, 15);
		getContentPane().add(lblUsuario);
		
		usuario = new JTextField();
		usuario.setBounds(12, 28, 300, 19);
		getContentPane().add(usuario);
		usuario.setColumns(10);
		
		JLabel lblPass = new JLabel("Contraseña:");
		lblPass.setBounds(12, 59, 99, 15);
		getContentPane().add(lblPass);
		
		contraseña = new JPasswordField();
		contraseña.setBounds(12, 75, 300, 19);
		getContentPane().add(contraseña);
		
		btnAcceder = new JButton("Acceder");
		btnAcceder.setBounds(213, 103, 99, 25);
		getContentPane().add(btnAcceder);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(84, 103, 117, 25);
		getContentPane().add(btnCancelar);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
