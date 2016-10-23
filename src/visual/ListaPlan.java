package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logico.Controladora;
import logico.Plan;
import logico.TipoServicio;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class ListaPlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel tableModel;
	static Object[] fila;
	private int id;
	private JButton Modificarbtn;
	private JButton Eliminarbtn ;

	/**
	 * Create the dialog.
	 */
	public ListaPlan(String title) {
		setTitle(title);
		setBounds(100, 100, 575, 363);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		String[] columnheaders = {"ID", "Nombre", "Servicio", "Precio"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnheaders);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow() >= 0)
				{
					
					Eliminarbtn.setEnabled(true);
					Modificarbtn.setEnabled(true);
					int index = table.getSelectedRow();
					id = (Integer)table.getModel().getValueAt(index, 0);
					
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
				{
					Eliminarbtn = new JButton("Eliminar");
					Eliminarbtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Plan p = Controladora.getControladora().encontrarPlan(id);
							Controladora.getControladora().borrarPlan(p);
							JOptionPane.showMessageDialog(null, "Plan eliminado satisfactoriamente", null, JOptionPane.INFORMATION_MESSAGE, null);
							cargarPlanes();

						}
					});
					Eliminarbtn.setEnabled(false);
					buttonPane.add(Eliminarbtn);
				}
				{
					Modificarbtn = new JButton("Modificar");
					Modificarbtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Plan p = Controladora.getControladora().encontrarPlan(id);
							boolean option = false;	
							RegistrarPlan regp = new RegistrarPlan("Modificar Plan", p, option);
							regp.setModal(true);
							regp.setVisible(true);
							
						}
					});
					Modificarbtn.setEnabled(false);
					buttonPane.add(Modificarbtn);
				}
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		cargarPlanes();
	}
	
	public static void cargarPlanes()
	{
		tableModel.setRowCount(0);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		
		
		fila = new Object[tableModel.getColumnCount()];
		
		for(int i =0; i < Controladora.getControladora().getListaplan().size();i++)
		{
			fila[0] = Controladora.getControladora().getListaplan().get(i).getIdPlan();
			fila[1] = Controladora.getControladora().getListaplan().get(i).getNombre();
			if(Controladora.getControladora().getListaplan().get(i).getTipo().toString().equalsIgnoreCase("Cable"))
			{
				fila[2] = "Cable";
			}
			else if(Controladora.getControladora().getListaplan().get(i).getTipo().toString().equalsIgnoreCase("Internet"))
			{
				fila[2] = "Internet";
			}
			else if(Controladora.getControladora().getListaplan().get(i).getTipo().toString().equalsIgnoreCase("Telefonia"))
			{
				fila[2] = "Telefono";
			}
			else if(Controladora.getControladora().getListaplan().get(i).getTipo().toString().equalsIgnoreCase("todo"))
			{
				fila[2] = "Todos";
			}
			else
			{
				fila[2] = "Combinado";
			}
			
			fila[3] = String.valueOf(Controladora.getControladora().getListaplan().get(i).getPrecioNeto());
				
			tableModel.addRow(fila);
		}
	}
}
