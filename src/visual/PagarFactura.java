package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import logico.Cliente;
import logico.Controladora;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

public class PagarFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField correoCliente;
	private static JTable table;
	private static DefaultTableModel tableModel;
	static Object[] fila;
	private String nombre;
	private Cliente client;
	private Cliente myclient;
	private JTextField cedulaCliente;
	private JComboBox comboBoxNombre;
	private MaskFormatter patron;
	private MaskFormatter tele;
	private JTextField telefonoCliente;
	private JButton okButton;
	
	/**
	 * Create the dialog.
	 */
	public PagarFactura(String title) {
		setTitle(title);
		setBounds(100, 100, 576, 425);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		String[] columnheaders = {"ID", "Cedula", "Nombre", "Plan", "Precio"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnheaders);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 540, 122);
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
		lblCedula.setBounds(10, 41, 57, 14);
		panel.add(lblCedula);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(291, 44, 57, 14);
		panel.add(lblNombre);
		
		JLabel lblCorreo = new JLabel("Correo: ");
		lblCorreo.setBounds(10, 77, 46, 14);
		panel.add(lblCorreo);
		
		correoCliente = new JTextField();
		correoCliente.setEditable(false);
		correoCliente.setBounds(77, 74, 127, 20);
		panel.add(correoCliente);
		correoCliente.setColumns(10);
		
		cedulaCliente = new JFormattedTextField(patron);
		cedulaCliente.setEditable(false);
		cedulaCliente.setBounds(77, 38, 129, 17);
		panel.add(cedulaCliente);
		cedulaCliente.setColumns(10);
		
		comboBoxNombre = new JComboBox();
		comboBoxNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxNombre.getSelectedIndex() != 0)
				{
					nombre = comboBoxNombre.getSelectedItem().toString();
					client = Controladora.getControladora().encontrarClienteporNombre(nombre);
					
					if(client != null)
					{
						cedulaCliente.setText(client.getCedula());
						correoCliente.setText(client.getCorreo());
						telefonoCliente.setText(client.getTelefono());
						
						cargarFacturas();
					}
				}
				
			}
		});
		comboBoxNombre.setBounds(358, 38, 127, 20);
		panel.add(comboBoxNombre);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(291, 77, 57, 14);
		panel.add(lblTelefono);
		
		telefonoCliente = new JFormattedTextField(tele);
		telefonoCliente.setEditable(false);
		telefonoCliente.setBounds(358, 74, 127, 20);
		panel.add(telefonoCliente);
		telefonoCliente.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 144, 530, 198);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow() >= 0)
				{
					okButton.setEnabled(true);
				}
			}
		});
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Pagar");
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
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
		cargarCliente();
		
	}
	
	private void cargarCliente()
	{
		comboBoxNombre.removeAllItems();
		for(int i =0;i < Controladora.getControladora().getListapersona().size();i++)
		{
			if(Controladora.getControladora().getListapersona().get(i) instanceof Cliente)
			{
				comboBoxNombre.addItem(Controladora.getControladora().getListapersona().get(i).getNombre());
			}
		}
		comboBoxNombre.insertItemAt("<Seleccione>", 0);
		comboBoxNombre.setSelectedIndex(0);
	}
	
	public void cargarFacturas()
	{
		tableModel.setRowCount(0);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		table.getColumnModel().getColumn(4).setCellRenderer(tcr);
		
		fila = new Object[tableModel.getColumnCount()];
		
		for(int i=0; i < Controladora.getControladora().getListafactura().size();i++)
		{
			if(Controladora.getControladora().getListafactura().get(i).getCliente().getCedula().equalsIgnoreCase(client.getCedula()))
			{
				fila[0] = Controladora.getControladora().getListafactura().get(i).getIdFactura();
				fila[1] = Controladora.getControladora().getListafactura().get(i).getCliente().getCedula();
				fila[2] = Controladora.getControladora().getListafactura().get(i).getCliente().getNombre();
				
				if(Controladora.getControladora().getListafactura().get(i).getCliente().getPlanes().getTipo().toString().equalsIgnoreCase("Cable"))
				{
					fila[3] = "Cable";
				}
				else if(Controladora.getControladora().getListafactura().get(i).getCliente().getPlanes().getTipo().toString().equalsIgnoreCase("Internet"))
				{
					fila[3] = "Internet";
				}
				else if(Controladora.getControladora().getListafactura().get(i).getCliente().getPlanes().getTipo().toString().equalsIgnoreCase("Telefonia"))
				{
					fila[3] = "Telefono";
				}
				else if(Controladora.getControladora().getListafactura().get(i).getCliente().getPlanes().getTipo().toString().equalsIgnoreCase("todo"))
				{
					fila[3] = "Todos";
				}
				else
				{
					fila[3] = "Combinado";
				}
				
				fila[4] = Controladora.getControladora().getListafactura().get(i).getCliente().getPlanes().getPrecioNeto();
				
				tableModel.addRow(fila);
			}
			
		}
		
			
	}
}
