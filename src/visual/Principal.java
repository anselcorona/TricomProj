package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.OptionPaneUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logico.Controladora;
import logico.Factura;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	public static ImageIcon img = new ImageIcon("C:\\Users\\Maxwell Tatem\\Desktop\\2Tricom_logo2011.png");
	
	/**
	 * Launch the application.
	 */
	//removido para que solo se acceda mediante login
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					frame.setIconImage(img.getImage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		//Controladora.getControladora().leerFicheros();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(Controladora.getControladora().guardarFicheros())
					JOptionPane.showMessageDialog(null, "Ficheros Actualizados", "Tricom SA", JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		
		setTitle("Tricom S A");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmNuevoCliente = new JMenuItem("Nuevo Cliente");
		mntmNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean option = true;	
				RegistrarCliente regclient = new RegistrarCliente("Registrar Cliente", option, null);
				regclient.setModal(true);
				regclient.setVisible(true);
				
			}
		});
		mnClientes.add(mntmNuevoCliente);
		
		JMenuItem mntmVerClientes = new JMenuItem("Clientes Actuales");
		mntmVerClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ListaCliente listclient = new ListaCliente("Lista de Clientes");
				listclient.setModal(true);
				listclient.setVisible(true);
				
				
				
				
			}
		});
		mnClientes.add(mntmVerClientes);
		
		JMenu mnPlanes = new JMenu("Planes");
		menuBar.add(mnPlanes);
		
		JMenuItem mntmCrearNuevoPlan = new JMenuItem("Nuevo Plan");
		mntmCrearNuevoPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean option = true;	
				RegistrarPlan plan = new RegistrarPlan("Registrar Plan", null, option);
				plan.setModal(true);
				plan.setVisible(true);
			}
		});
		mnPlanes.add(mntmCrearNuevoPlan);
		
		JMenuItem mntmVerPlanesActuales = new JMenuItem("Planes Actuales");
		mntmVerPlanesActuales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPlan listplan = new ListaPlan("Lista de Planes");
				listplan.setModal(true);
				listplan.setVisible(true);
			}
		});
		mnPlanes.add(mntmVerPlanesActuales);
		
		JMenu mnFacturacion = new JMenu("Facturacion");
		menuBar.add(mnFacturacion);
		
		JMenuItem mntmPagarFactura = new JMenuItem("Pagar");
		mntmPagarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PagarFactura pagf = new PagarFactura("Pagar Factura");
				pagf.setModal(true);
				pagf.setVisible(true);
			}
		});
		mnFacturacion.add(mntmPagarFactura);
		
		JMenuItem mntmVerFacturas = new JMenuItem("Ver Facturas");
		mntmVerFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaFacturas listf= new ListaFacturas("Lista de Facturas");
				listf.setModal(true);
				listf.setVisible(true);
				
			}
		});
		mnFacturacion.add(mntmVerFacturas);
		
		JMenuItem mntmExportarAPdf = new JMenuItem("Exportar a PDF");
		mnFacturacion.add(mntmExportarAPdf);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenuItem mntmGenerarReporte = new JMenuItem("Generar");
		mntmGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("# de Planes", Controladora.getControladora().getListaplan().size());
				data.setValue("# de Clientes", Controladora.getControladora().getListaplan().size());
				data.setValue("# de Usuarios", Controladora.getControladora().getListaplan().size());
				//Creating piechart
				JFreeChart chart = ChartFactory.createPieChart(
						"Resumen ",
						data,
						true, // legend?
						true, // tooltips?
						false // URLs?
						);
				
				// create and display a frame...
				ChartFrame frame = new ChartFrame("First", chart);
				frame.pack();
				frame.setVisible(true);
			}
		});
		mnReportes.add(mntmGenerarReporte);
		
		JMenu mnConfiguracion = new JMenu("Configuracion");
		menuBar.add(mnConfiguracion);
		
		JMenuItem mntmVerUsuarios = new JMenuItem("Ver Usuarios");
		mntmVerUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaUsuarios listus = new ListaUsuarios("Lista de Usuarios");
				listus.setModal(true);
				listus.setVisible(true);
			}
		});
		mnConfiguracion.add(mntmVerUsuarios);
		
		JMenuItem mntmNuevoUsuario = new JMenuItem("Nuevo Usuario");
		mntmNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean option = true;	
				RegistrarUsuario regus= new RegistrarUsuario("Registrar Vendedor", option, null);
				regus.setModal(true);
				regus.setVisible(true);
				
			}
		});
		mnConfiguracion.add(mntmNuevoUsuario);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelInfo, BorderLayout.SOUTH);
		panelInfo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblUsername = new JLabel(Controladora.getControladora().getUsuarioActual().getCedula());
		panelInfo.add(lblUsername);
		
		JLabel lblFechahora = new JLabel("Fecha/Hora");
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd MMM yyyy - h:mm a");
		lblFechahora.setText(formato.format(fecha));
		panelInfo.add(lblFechahora, BorderLayout.EAST);
		
		JLabel lblBienvenidousername = new JLabel("Bienvenido "+ Controladora.getControladora().getUsuarioActual().getNombre());
		contentPane.add(lblBienvenidousername, BorderLayout.NORTH);
		lblBienvenidousername.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Controladora.getControladora().setListafactura(new ArrayList<Factura>());
		Controladora.getControladora().procesarFacturas();
		
		//PRUEBA JCHART
		//Creating dataset
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("# de Planes", Controladora.getControladora().getListaplan().size());
				data.setValue("# de Clientes", Controladora.getControladora().getListaplan().size());
				data.setValue("# de Usuarios", Controladora.getControladora().getListaplan().size());
				//Creating piechart
				JFreeChart chart = ChartFactory.createPieChart(
						"Resumen ",
						data,
						true, // legend?
						true, // tooltips?
						false // URLs?
						);
				// create and display a frame...
				ChartPanel cp = new ChartPanel(chart);
				contentPane.add(cp,BorderLayout.CENTER);
				validate();
	}

}
