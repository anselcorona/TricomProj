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

import logico.Administrador;
import logico.Cliente;
import logico.Controladora;
import logico.Persona;
import logico.Vendedor;

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

public class ListaUsuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel tableModel;
	static Object[] fila;
	private static JTable table;
	private String cedula;
	private String tipo;
	private String cedulaBuscar;
	private JButton btnModificar;
	private MaskFormatter patron;
	private MaskFormatter tele;
	
	
	/**
	 * Create the dialog.
	 */
	public ListaUsuarios(String title) {
		setTitle(title);
		setBounds(100, 100, 578, 351);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		String[]  columnHeaders = { "Cedula", "Nombre", "Telefono", "Correo", "Tipo"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnHeaders);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
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
				
					btnModificar.setEnabled(true);
					int index = table.getSelectedRow();
					cedula = (String)table.getModel().getValueAt(index, 0);
					tipo = (String)table.getModel().getValueAt(index, 4);
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
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(tipo.compareTo("Vendedor") == 0){
							Vendedor vend = Controladora.getControladora().encontrarVendedor(cedula);
							boolean option = false;
							RegistrarUsuario regvend = new RegistrarUsuario("Modificar Usuario", option, vend);
							regvend.setModal(true);
							regvend.setVisible(true);
						}
						else if(tipo.compareTo("Administrador") == 0){
							Persona adm = Controladora.getControladora().encontrarAdm(cedula);
							boolean option = false;
							RegistrarUsuario regvend = new RegistrarUsuario("Modificar Usuario", option, adm);
							regvend.setModal(true);
							regvend.setVisible(true);	
						}
						
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
		
		cargarListaVendedores();
	}
	
	public static void cargarListaVendedores()
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
		
		for(int i =0; i < Controladora.getControladora().getListapersona().size();i++)
		{
			if(Controladora.getControladora().getListapersona().get(i) instanceof Vendedor || Controladora.getControladora().getListapersona().get(i) instanceof Administrador)
			{
				fila[0] = Controladora.getControladora().getListapersona().get(i).getCedula();
				fila[1] = Controladora.getControladora().getListapersona().get(i).getNombre();
				fila[2] = Controladora.getControladora().getListapersona().get(i).getTelefono();
				fila[3] = Controladora.getControladora().getListapersona().get(i).getCorreo();
				fila[4] = Controladora.getControladora().getListapersona().get(i).getTipo();
				tableModel.addRow(fila);
			}
			
					
		}
	}
}
