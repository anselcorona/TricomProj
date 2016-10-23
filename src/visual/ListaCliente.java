package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import logico.Cliente;
import logico.Controladora;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridLayout;

public class ListaCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel tableModel;
	static Object[] fila;
	private static JTable table;
	private String cedula;
	private String cedulaBuscar;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JTextField cedulaCliente;
	private MaskFormatter patron;
	private MaskFormatter tele;
	
	
	/**
	 * Create the dialog.
	 */
	public ListaCliente(String title) {
		setTitle(title);
		setBounds(100, 100, 578, 351);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		String[]  columnHeaders = { "Cedula", "Nombre", "Telefono", "Correo"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnHeaders);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCedulaDeCliente = new JLabel("Cedula de Cliente:");
		lblCedulaDeCliente.setBounds(58, 20, 102, 14);
		panel.add(lblCedulaDeCliente);
		
		cedulaCliente = new JFormattedTextField(patron);
		cedulaCliente.setBounds(218, 17, 125, 20);
		panel.add(cedulaCliente);
		cedulaCliente.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(401, 16, 89, 23);
		panel.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cedulaBuscar = cedulaCliente.getText();
				Cliente c = Controladora.getControladora().encontrarCliente(cedulaBuscar);
				
				if(c != null)
				{
					boolean option = false;
					RegistrarCliente regclient = new RegistrarCliente("Modificar Cliente", option, c);
					regclient.setModal(true);
					regclient.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Cliente no encontrado", null, JOptionPane.INFORMATION_MESSAGE, null);

				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPanel.add(scrollPane);
		
		try {
			patron = new MaskFormatter("###-#######-#");
			tele = new MaskFormatter("(###)-###-####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(table.getSelectedRow() >= 0)
				{
					btnEliminar.setEnabled(true);
					btnModificar.setEnabled(true);
					int index = table.getSelectedRow();
					cedula = (String)table.getModel().getValueAt(index, 0);
					
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
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Cliente client = Controladora.getControladora().encontrarCliente(cedula);
						Controladora.getControladora().borrarCliente(client);
						JOptionPane.showMessageDialog(null, "Cliente eliminado satisfactoriamente", null, JOptionPane.INFORMATION_MESSAGE, null);
						cargarListaClientes();
					}
				});
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Cliente client = Controladora.getControladora().encontrarCliente(cedula);
						boolean option = false;
						RegistrarCliente regclient = new RegistrarCliente("Modificar Cliente", option, client);
						regclient.setModal(true);
						regclient.setVisible(true);
					}
				});
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);
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
		
		cargarListaClientes();
	}
	
	public static void cargarListaClientes()
	{
		tableModel.setRowCount(0);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		
		
		
		fila = new Object[tableModel.getColumnCount()];
		
		for(int i =0; i < Controladora.getControladora().getListapersona().size();i++)
		{
			if(Controladora.getControladora().getListapersona().get(i) instanceof Cliente)
			{
				fila[0] = Controladora.getControladora().getListapersona().get(i).getCedula();
				fila[1] = Controladora.getControladora().getListapersona().get(i).getNombre();
				fila[2] = Controladora.getControladora().getListapersona().get(i).getTelefono();
				fila[3] = Controladora.getControladora().getListapersona().get(i).getCorreo();
				tableModel.addRow(fila);
			}
			
					
		}
	}
}
