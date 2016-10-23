package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logico.Cliente;
import logico.Controladora;
import logico.Plan;
import logico.Sexo;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import java.awt.Color;

public class RegistrarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField cedulaCliente;
	private JTextField nombreCliente;
	private JTextField apellidoCliente;
	private JTextField correoCliente;
	private JTextField direccionCliente;
	private JTextField telefonoCliente;
	private JComboBox combosexoCliente;
	private MaskFormatter patron;
	private MaskFormatter tele;
	private Cliente myclient;
	private boolean option = true;
	private JButton okButton;
	private static String[] nombrePlan;
	private static  JComboBox<String> comboBoxPlanes;
	private static int cant=6;
	private static DefaultComboBoxModel<String> combonombres;
	private String numberOnly1;
	
	
	/*
	 //Felixal: Revisar Registrar Cliente

	 -Validar datos
	


	/**
	 * Create the dialog.
	 */
	

	public RegistrarCliente(String title, boolean option, Cliente client) {
		setTitle(title);
		setBounds(100, 100, 557, 372);
		setLocationRelativeTo(null);
		setResizable(false);
		this.option = option;
		myclient = client;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 23, 531, 167);
		contentPanel.add(panel);
		panel.setLayout(null);
		try {
			patron = new MaskFormatter("###-#######-#");
			tele = new MaskFormatter("(###)-###-####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(10, 37, 49, 14);
		panel.add(lblCedula);
		
		cedulaCliente = new JFormattedTextField(patron);
		cedulaCliente.setBounds(85, 34, 120, 20);
		panel.add(cedulaCliente);
		cedulaCliente.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(10, 68, 57, 14);
		panel.add(lblNombre);
		
		nombreCliente = new JTextField();
		nombreCliente.setBounds(85, 65, 120, 20);
		panel.add(nombreCliente);
		nombreCliente.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(299, 68, 57, 14);
		panel.add(lblApellido);
		
		apellidoCliente = new JTextField();
		apellidoCliente.setBounds(386, 65, 120, 20);
		panel.add(apellidoCliente);
		apellidoCliente.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(10, 100, 49, 14);
		panel.add(lblCorreo);
		
		correoCliente = new JTextField();
		correoCliente.setBounds(85, 97, 168, 20);
		panel.add(correoCliente);
		correoCliente.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(299, 100, 57, 14);
		panel.add(lblSexo);
		
		combosexoCliente = new JComboBox();
		
		combosexoCliente.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Femenino", "Masculino"}));
		combosexoCliente.setBounds(386, 99, 120, 18);
		panel.add(combosexoCliente);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(10, 128, 57, 14);
		panel.add(lblDireccion);
		
		direccionCliente = new JTextField();
		direccionCliente.setBounds(85, 125, 421, 20);
		panel.add(direccionCliente);
		direccionCliente.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(299, 37, 57, 14);
		panel.add(lblTelefono);
		
		telefonoCliente = new JFormattedTextField(tele);
		telefonoCliente.setBounds(386, 34, 120, 20);
		panel.add(telefonoCliente);
		telefonoCliente.setColumns(10);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(69, 37, 11, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(69, 68, 11, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(69, 100, 11, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(69, 128, 11, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(365, 37, 11, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setBounds(365, 71, 11, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setBounds(366, 100, 11, 14);
		panel.add(label_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Planes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 201, 531, 86);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPlanesDisponibles = new JLabel("Planes Disponibles:");
		lblPlanesDisponibles.setBounds(10, 40, 117, 14);
		panel_1.add(lblPlanesDisponibles);
		
		
		
		
		nombrePlanes();
		
		comboBoxPlanes.setBounds(150, 37, 130, 20);
		panel_1.add(comboBoxPlanes);
		
		
		
		JButton btnAgregarNuevo = new JButton("Agregar Nuevo Plan");
		btnAgregarNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean option = true;	
				RegistrarPlan plan = new RegistrarPlan("Registrar Plan", null, option);
				plan.setModal(true);
				plan.setVisible(true);
				
			}
		});
		btnAgregarNuevo.setBounds(359, 36, 148, 23);
		panel_1.add(btnAgregarNuevo);
		
		JLabel label_7 = new JLabel("*");
		label_7.setForeground(Color.RED);
		label_7.setBounds(129, 40, 11, 14);
		panel_1.add(label_7);
		
	
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				 okButton = new JButton("Salvar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(myclient == null)
						{
							if(!(nombreCliente.getText().equalsIgnoreCase("")) && (cedulaCliente.getText().length() == 13) && !(direccionCliente.getText().equalsIgnoreCase("") && !(apellidoCliente.getText().equalsIgnoreCase("")) && !(correoCliente.getText().equalsIgnoreCase("")) && !(telefonoCliente.getText().equalsIgnoreCase("")) && combosexoCliente.getSelectedIndex()!= 0) && comboBoxPlanes.getSelectedItem()!= null)
							{
								Cliente cliente = new Cliente();
								Plan plan = new Plan();
								
								cliente.setNombre(nombreCliente.getText());
								cliente.setApellido(apellidoCliente.getText());
								cliente.setCedula(cedulaCliente.getText());
								cliente.setDireccion(direccionCliente.getText());
								cliente.setTelefono(telefonoCliente.getText());
								
								if(combosexoCliente.getSelectedIndex() != 0)
								{
									switch(combosexoCliente.getSelectedIndex()){
									
									case 1:
										cliente.setSexo(Sexo.femenino);
										break;
									case 2:
										cliente.setSexo(Sexo.masculino);
										break;
									
									}
								}
								
								String m = correoCliente.getText();
								
								if(validarCorreo(m))
								{
									cliente.setCorreo(correoCliente.getText());
									plan = encontrarPlan();
									cliente.setPlanes(plan);
									
									Cliente q = Controladora.getControladora().encontrarCliente(cedulaCliente.getText());
									
									if( q == null)
									{
										Controladora.getControladora().registrarPersona(cliente);
										JOptionPane.showMessageDialog(null, "Cliente registrado satisfactoriamente", null, JOptionPane.INFORMATION_MESSAGE, null);
										limpiarDialogos();

									}
									else
									{
										JOptionPane.showMessageDialog(null, "Ya existe un cliente con esta cedula", null, JOptionPane.INFORMATION_MESSAGE, null);
										cedulaCliente.requestFocus();
										
									}
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Este correo no es valido", null, JOptionPane.INFORMATION_MESSAGE, null);
									correoCliente.requestFocus();
								}
								
								
								
								
								
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Los campos con * son obligatorios", null, JOptionPane.INFORMATION_MESSAGE, null);

							}

							
						}
						else
						{
							Plan q = new Plan();
							myclient.setNombre(nombreCliente.getText());
							myclient.setApellido(apellidoCliente.getText());
							myclient.setCedula(cedulaCliente.getText());
							myclient.setDireccion(direccionCliente.getText());
							myclient.setTelefono(telefonoCliente.getText());

							if(combosexoCliente.getSelectedIndex() != 0)
							{
								switch(combosexoCliente.getSelectedIndex()){
								
								case 1:
									myclient.setSexo(Sexo.femenino);
									break;
								case 2:
									myclient.setSexo(Sexo.masculino);
									break;
								
								}
							}
							myclient.setCorreo(correoCliente.getText());
							q = encontrarPlan();
							myclient.setPlanes(q);
							
							Controladora.getControladora().modificarCliente(myclient);
							JOptionPane.showMessageDialog(null, "Cliente modificado satisfactoriamente", null, JOptionPane.INFORMATION_MESSAGE, null);

							dispose();
							ListaCliente.cargarListaClientes();
							
							
						}
					}
				});
				okButton.setActionCommand("Salvar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		cargarModific();
		
		
		
	}
	
	public void limpiarDialogos()
	{
		nombreCliente.setText("");
		apellidoCliente.setText("");
		cedulaCliente.setText("");
		telefonoCliente.setText("");
		combosexoCliente.setSelectedIndex(0);
		correoCliente.setText("");
		direccionCliente.setText("");
		
	}
	
	public void cargarModific()
	{
		if(!option)
		{
			
			cedulaCliente.setEditable(false);
			nombreCliente.setText(myclient.getNombre());
			apellidoCliente.setText(myclient.getApellido());
			cedulaCliente.setText(myclient.getCedula());
			telefonoCliente.setText(myclient.getTelefono());
			direccionCliente.setText(myclient.getDireccion());
			correoCliente.setText(myclient.getCorreo());
			
			int p = myclient.getPlanes().getIdPlan();
			
			comboBoxPlanes.setSelectedIndex(p-1);
			
			
			if(myclient.getSexo().toString().equalsIgnoreCase("femenino"))
			{
				combosexoCliente.setSelectedIndex(1);
			}
			else
			{
				combosexoCliente.setSelectedIndex(2);
			}
		}
	}
	
	public static void nombrePlanes()
	{
			nombrePlan = new String[cant];
			comboBoxPlanes = new JComboBox();
			int tam = nombrePlan.length;
			
			for (int i =0; i < Controladora.getControladora().getListaplan().size();i++) {
				
				
				
				if(Controladora.getControladora().getListaplan().get(i).getTipo().toString().equalsIgnoreCase("Cable"))
				{
					nombrePlan[i] = "Cable " + Controladora.getControladora().getListaplan().get(i).getIdPlan();
				}
				else if(Controladora.getControladora().getListaplan().get(i).getTipo().toString().equalsIgnoreCase("Telefonia"))
				{
					nombrePlan[i] = "Telefono "+ Controladora.getControladora().getListaplan().get(i).getIdPlan();
				}
				else if(Controladora.getControladora().getListaplan().get(i).getTipo().toString().equalsIgnoreCase("Internet"))
				{
					nombrePlan[i] = "Internet "+ Controladora.getControladora().getListaplan().get(i).getIdPlan();
				}
				else if(Controladora.getControladora().getListaplan().get(i).getTipo().toString().equalsIgnoreCase("telefonoeinternet"))
				{
					nombrePlan[i] = "Telefono e Internet "+ Controladora.getControladora().getListaplan().get(i).getIdPlan();
				}
				else if(Controladora.getControladora().getListaplan().get(i).getTipo().toString().equalsIgnoreCase("telefonoecable"))
				{
					nombrePlan[i] = "Telefono y Cable "+ Controladora.getControladora().getListaplan().get(i).getIdPlan();
				}
				else if(Controladora.getControladora().getListaplan().get(i).getTipo().toString().equalsIgnoreCase("internetecable"))
				{
					nombrePlan[i] = "Internet y Cable "+ Controladora.getControladora().getListaplan().get(i).getIdPlan();
				}
				else if(Controladora.getControladora().getListaplan().get(i).getTipo().toString().equalsIgnoreCase("todo"))
				{
					nombrePlan[i] = "Todos " + Controladora.getControladora().getListaplan().get(i).getIdPlan();
				}
				
				if(tam != nombrePlan.length)
				{
					cant++;
				}
				
			}
			combonombres = new DefaultComboBoxModel<>(nombrePlan);
			comboBoxPlanes.setModel(combonombres);
			
		
		
	}
	
	public Plan encontrarPlan()
	{
		String sel = (String) comboBoxPlanes.getSelectedItem();
		String numberOnly= sel.replaceAll("[^0-9]", "");
		 
		System.out.println("Numero: " + numberOnly);
		Plan p = new Plan();
		
		for (Plan plan : Controladora.getControladora().getListaplan()) {
			
			if(numberOnly.equalsIgnoreCase(String.valueOf(plan.getIdPlan())))
			{
				p = plan;
			}
		}
		
		return p;
	}
	
	public boolean validarCorreo(String n)
	{
		String p;
		boolean op = false;
		
		
		if(n.endsWith("@gmail.com") || n.endsWith("@outlook.com") || n.endsWith("@yahoo.com") || n.endsWith("@hotmail.com"))
		{
			op = true;
			
			System.out.println("Correo: " + n);
		}
		
		
		return op;
	}

	
	
}
