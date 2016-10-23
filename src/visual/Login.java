package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import logico.Controladora;
import logico.Persona;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	private MaskFormatter patron;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void crearPrincipal(){
		try {
			Principal frame = new Principal();
			frame.setVisible(true);
			//frame.setIconImage(img.getImage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean verificarLogin(){
		boolean resultado = false;
		Controladora.getControladora().leerFicheros();
		for (Persona p : Controladora.getControladora().getListapersona()) {
			if(p.getCedula().compareTo(txtUsuario.getText()) == 0 && p.getContra().compareTo(txtContrasena.getText()) == 0){
				resultado = true;
				Controladora.getControladora().setUsuarioActual(p);
				break;
			}
		}
		if(Controladora.getControladora().getdefaultAdmin() != null){
			if(Controladora.getControladora().getdefaultAdmin().getCedula().compareTo(txtUsuario.getText()) == 0 && Controladora.getControladora().getdefaultAdmin().getContra().compareTo(txtContrasena.getText()) == 0){
				resultado = true;
				Controladora.getControladora().setUsuarioActual(Controladora.getControladora().getdefaultAdmin());
			}
		}
		return resultado;
	}
	
	/**
	 * Create the dialog.
	 */
	public Login() {
		try {
			patron = new MaskFormatter("###-#######-#");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("Tricom SA");
		setResizable(false);
		setBounds(100, 100, 303, 179);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		JLabel lblBienvenidoAlSistema = new JLabel("Bienvenido al sistema de gestion de Tricom SA");
		lblBienvenidoAlSistema.setBounds(12, 13, 279, 16);
		contentPanel.add(lblBienvenidoAlSistema);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(22, 42, 56, 16);
		contentPanel.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(12, 71, 66, 16);
		contentPanel.add(lblContrasea);
		
		txtUsuario = new JFormattedTextField(patron);
		txtUsuario.setBounds(90, 42, 116, 22);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(90, 68, 116, 22);
		contentPanel.add(txtContrasena);
		txtContrasena.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(verificarLogin()){
							crearPrincipal();
							dispose();
						}else{
							JOptionPane.showMessageDialog(contentPanel, "Usuario y/o contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
