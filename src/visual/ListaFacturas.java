package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logico.Controladora;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListaFacturas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel tableModel;
	static Object[] fila;

	/**
	 * Create the dialog.
	 */
	public ListaFacturas(String title) {
		setTitle(title);
		setBounds(100, 100, 618, 364);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		String[] columnheaders = {"ID", "Cedula", "Nombre", "Plan", "Precio"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnheaders);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
		
		cargarFacturas();
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
