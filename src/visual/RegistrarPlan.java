package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Controladora;
import logico.Plan;
import logico.Servicio;
import logico.TipoServicio;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegistrarPlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nombrePlan;
	private JTextField idPlan;
	private JTextField precioPlan;
	private JTextPane descripcionPlan ;
	private JRadioButton botonCable;
	private JRadioButton botonInternet;
	private JRadioButton botonTelefono;
	private Plan myplan = null;
	private boolean option;
	private JButton okButton;
	private JScrollPane scrollPane_1;
	private JTextField precioCable;
	private JTextField precioInternet;
	private JTextField precioTelefono;
	private int cont =0;
	
	
	/**
	 * Create the dialog.
	 * @param string 
	 */
	public RegistrarPlan(String title, Plan plan, boolean op) {
		setTitle(title);
		setBounds(100, 100, 592, 424);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		this.myplan = plan;
		this.option = op;
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 566, 335);
		panel.setBorder(new TitledBorder(null, "Datos del Plan", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreDelPlan = new JLabel("Nombre del Plan: ");
		lblNombreDelPlan.setToolTipText("");
		lblNombreDelPlan.setBounds(157, 31, 108, 14);
		panel.add(lblNombreDelPlan);
		
		nombrePlan = new JTextField();
		nombrePlan.setBounds(296, 28, 144, 20);
		panel.add(nombrePlan);
		nombrePlan.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Total a pagar: ");
		lblPrecio.setBounds(6, 310, 86, 14);
		panel.add(lblPrecio);
		
		precioPlan = new JTextField();
		precioPlan.setEditable(false);
		precioPlan.setForeground(Color.RED);
		precioPlan.setBounds(95, 307, 86, 20);
		panel.add(precioPlan);
		precioPlan.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Servicios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		panel_1.setBounds(6, 56, 552, 103);
		panel.add(panel_1);
		
		botonCable = new JRadioButton("Cable");
		botonCable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				precioCable.setEnabled(true);
				if(botonCable.isSelected() == false)
				{
					float m =0, c=0;
					m = Float.valueOf(precioPlan.getText());
					if(!(precioCable.getText().equalsIgnoreCase("")))
					{
						c = Float.valueOf(precioCable.getText());
					}
				
					if(c != 0)
					{
							m = m - c;
					}
				
					precioPlan.setText(String.valueOf(m));
					precioCable.setText("");
					precioCable.setEnabled(false);
				}
				
				
			}
		});
		botonCable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//botonCable.setSelected(true);
				System.out.println(	botonCable.isSelected());
				//System.out.println(	!(botonCable.isSelected()));
			}
		});
		botonCable.setBounds(63, 17, 109, 23);
		panel_1.add(botonCable);
		
		botonInternet = new JRadioButton("Internet");
		botonInternet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				precioInternet.setEnabled(true);
				if(botonInternet.isSelected() == false)
				{
					float m =0, c=0;
					m = Float.valueOf(precioPlan.getText());
					if(!(precioInternet.getText().equalsIgnoreCase("")))
					{
						c = Float.valueOf(precioInternet.getText());
					}
					
					m = m - c;
					precioPlan.setText(String.valueOf(m));
					precioInternet.setText("");
					precioInternet.setEnabled(false);
					
				}
			}
		});
		botonInternet.setBounds(237, 17, 109, 23);
		panel_1.add(botonInternet);
		
		botonTelefono = new JRadioButton("Telefono");
		botonTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				precioTelefono.setEnabled(true);
				if(botonTelefono.isSelected() == false)
				{
					float m =0, c=0;
					m = Float.valueOf(precioPlan.getText());
					if(!(precioTelefono.getText().equalsIgnoreCase("")))
					{
						c = Float.valueOf(precioTelefono.getText());
					}
					
					m = m - c;
					precioPlan.setText(String.valueOf(m));
					precioTelefono.setText("");
					precioTelefono.setEnabled(false);
				}
			}
		});
		botonTelefono.setBounds(411, 17, 109, 23);
		panel_1.add(botonTelefono);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(6, 170, 71, 14);
		panel.add(lblDescripcion);
		
		botonCable.setSelected(false);
		botonInternet.setSelected(false);
		botonTelefono.setSelected(false);
		
		JLabel lblPrecio_1 = new JLabel("Precio:");
		lblPrecio_1.setBounds(10, 57, 46, 14);
		panel_1.add(lblPrecio_1);
		
		precioCable = new JTextField();
		precioCable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(precioCable.isEnabled())
				{
					float c=0, i=0, t=0;
					c = revisarPrecioCable();
					i = revisarPrecioInternet();
					t = revisarPrecioTelefono();
					if(c != 0 && c != -1 )
					{
						if(i != -1)
						{
							c += i;
						}
						
						if(t != -1)
						{
							c += t;
						}
						
						
						precioPlan.setText(String.valueOf(c));
						
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Datos incorrectos", null, JOptionPane.INFORMATION_MESSAGE, null);
						precioCable.requestFocus();
					}
				}
			}
		});
		
		precioCable.setEnabled(false);
		precioCable.setBounds(63, 54, 86, 20);
		panel_1.add(precioCable);
		precioCable.setColumns(10);
		
		precioInternet = new JTextField();
		precioInternet.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if(precioInternet.isEnabled())
				{
					float c=0, i=0, t=0;
					c = revisarPrecioCable();
					i = revisarPrecioInternet();
					t = revisarPrecioTelefono();
					if(i != 0 && i != -1 )
					{
						if(c != -1)
						{
							i += c;
						}
						if(t != -1)
						{
							i += t;
						}
						
						
						precioPlan.setText(String.valueOf(i));
						
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Datos incorrectos", null, JOptionPane.INFORMATION_MESSAGE, null);
						precioInternet.requestFocus();
					}
				}
			}
		});
		
		precioInternet.setEnabled(false);
		precioInternet.setBounds(226, 54, 86, 20);
		panel_1.add(precioInternet);
		precioInternet.setColumns(10);
		
		precioTelefono = new JTextField();
		precioTelefono.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(precioTelefono.isEnabled())
				{
					float c=0, i=0, t=0;
					c = revisarPrecioCable();
					i = revisarPrecioInternet();
					t = revisarPrecioTelefono();
					if(t != 0 && t != -1 )
					{
						if( i != -1)
						{
							t += i;
						}
						if( c != -1)
						{
							t += c;
						}
						
						precioPlan.setText(String.valueOf(t));
						
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Datos incorrectos", null, JOptionPane.INFORMATION_MESSAGE, null);
						precioTelefono.requestFocus();
					}
				}
			}
		});
		
		precioTelefono.setEnabled(false);
		precioTelefono.setBounds(411, 54, 86, 20);
		panel_1.add(precioTelefono);
		precioTelefono.setColumns(10);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(275, 31, 11, 14);
		panel.add(label);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(81, 170, 11, 14);
		panel.add(label_2);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 195, 552, 104);
		panel.add(scrollPane_1);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		descripcionPlan = new JTextPane();
		scrollPane_1.setViewportView(descripcionPlan);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 31, 36, 14);
		panel.add(lblId);
		
		
		
		idPlan = new JTextField();
		idPlan.setBounds(36, 28, 42, 20);
		panel.add(idPlan);
		idPlan.setEditable(false);
		idPlan.setColumns(10);
		

	
		
		
		idPlan.setText(String.valueOf(Controladora.getControladora().ultimoIDPlan()));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Salvar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(myplan == null)
						{
							if(!(nombrePlan.getText().equalsIgnoreCase("")) && !(descripcionPlan.getText().equalsIgnoreCase("")) && (botonCable.isSelected() == true )|| (botonInternet.isSelected() == true) || (botonTelefono.isSelected() == true) && Float.valueOf(precioPlan.getText()) != 0)
							{
								
								Plan plan = new Plan();
								
								plan.setDiaFacturacion(15);
								plan.setDescripcion(descripcionPlan.getText());
								plan.setNombre(nombrePlan.getText());
								plan.setIdPlan(Integer.valueOf(idPlan.getText()));
								
								
								if(botonCable.isSelected() && (botonInternet.isSelected()== false && botonTelefono.isSelected()==false))
								{
									
									plan.setTipo(TipoServicio.cable);
									
									
									
								}
								else if(botonInternet.isSelected() && (botonCable.isSelected() == false && botonTelefono.isSelected() == false))
								{
									plan.setTipo(TipoServicio.internet);
									
									
								}
								else if(botonTelefono.isSelected() && botonCable.isSelected() == false && botonInternet.isSelected() == false)
								{
									plan.setTipo(TipoServicio.telefonia);
								
									
								}
								else if(botonCable.isSelected() && botonTelefono.isSelected() && (botonInternet.isSelected() == false))
								{
									plan.setTipo(TipoServicio.telefonoecable);
									
								}
								else if(botonTelefono.isSelected() && botonInternet.isSelected() && (botonCable.isSelected()== false))
								{
									plan.setTipo(TipoServicio.telefonoeinternet);
									
								}
								else if(botonInternet.isSelected() && botonCable.isSelected() && (botonTelefono.isSelected()== false))
								{
									plan.setTipo(TipoServicio.internetecable);
									
								}
								else if(botonCable.isSelected() && botonInternet.isSelected() && botonTelefono.isSelected())
								{
									plan.setTipo(TipoServicio.todo);
								}
								
								float c=0, i=0, t=0;
								c = revisarPrecioCable();
								i = revisarPrecioInternet();
								t = revisarPrecioTelefono();
								
								if( t != -1 )
								{
									plan.setPrecioTelefono(t);
								}
								if( i != -1)
								{
									plan.setPrecioInternet(i);
								}
								if( c != -1)
								{
									plan.setPrecioCable(c);
								}
									
									
									
								
								
								float f = Float.valueOf(precioPlan.getText());
								System.out.println("Precio: " + f);
								plan.setPrecioNeto(f);
								
								Controladora.getControladora().registrarPlan(plan);
								JOptionPane.showMessageDialog(null,"Plan registrado satisfactoriamente", null, JOptionPane.INFORMATION_MESSAGE, null);
								limpiarcampos();
								idPlan.setText(String.valueOf(Controladora.getControladora().ultimoIDPlan()));
								cont++;
								
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Datos incompletos", null, JOptionPane.INFORMATION_MESSAGE, null);
								
							}
							
						}
						else
						{
							myplan.setNombre(nombrePlan.getText());
							
							myplan.setDescripcion(descripcionPlan.getText());
							myplan.setIdPlan(Integer.valueOf(idPlan.getText()));
							if(botonCable.isSelected() && (botonInternet.isSelected()== false && botonTelefono.isSelected()==false))
							{
								
								myplan.setTipo(TipoServicio.cable);
								
							}
							else if(botonInternet.isSelected() && (botonCable.isSelected() == false && botonTelefono.isSelected() == false))
							{
								myplan.setTipo(TipoServicio.internet);
							}
							else if(botonTelefono.isSelected() && (botonCable.isSelected() == false && botonInternet.isSelected() == false))
							{
								myplan.setTipo(TipoServicio.telefonia);
							}
							else if(botonCable.isSelected() && botonTelefono.isSelected() && (botonInternet.isSelected() == false))
							{
								myplan.setTipo(TipoServicio.telefonoecable);
							}
							else if(botonTelefono.isSelected() && botonInternet.isSelected() && (botonCable.isSelected()== false))
							{
								myplan.setTipo(TipoServicio.telefonoeinternet);
							}
							else if(botonInternet.isSelected() && botonCable.isSelected() && (botonTelefono.isSelected()== false))
							{
								myplan.setTipo(TipoServicio.internetecable);
							}
							else if(botonCable.isSelected() && botonInternet.isSelected() && botonTelefono.isSelected())
							{
								myplan.setTipo(TipoServicio.todo);
							}
							
							float c=0, i=0, t=0;
							c = revisarPrecioCable();
							i = revisarPrecioInternet();
							t = revisarPrecioTelefono();
							
							if( t != -1 )
							{
								myplan.setPrecioTelefono(t);
							}
							if( i != -1)
							{
								myplan.setPrecioInternet(i);
							}
							if( c != -1)
							{
								myplan.setPrecioCable(c);
							}
								
								
								
							
							
							Controladora.getControladora().modificarPlan(myplan);
							JOptionPane.showMessageDialog(null, "Plan modificado satisfactoriamente", null, JOptionPane.INFORMATION_MESSAGE, null);
							dispose();
							ListaPlan.cargarPlanes();
							
							
						}
					
					
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cont != 0)
						{
							RegistrarCliente.nombrePlanes();
						}
						
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		cargarPlan();
		if(option)
		{
				precioPlan.setText(String.valueOf(0.00));
		}
	
		
		JLabel lblpreciosNoIncluyen = new JLabel("*Precios no incluyen impuestos*");
		lblpreciosNoIncluyen.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblpreciosNoIncluyen.setForeground(Color.RED);
		lblpreciosNoIncluyen.setBounds(219, 310, 233, 14);
		panel.add(lblpreciosNoIncluyen);
	}
	
	
	public void limpiarcampos()
	{
		nombrePlan.setText("");
		precioPlan.setText("");
		botonCable.setSelected(false);
		botonInternet.setSelected(false);
		botonTelefono.setSelected(false);
		descripcionPlan.setText("");
		precioCable.setText("");
		precioInternet.setText("");
		precioTelefono.setText("");
		
	}
	
	public void cargarPlan()
	{
		if(!option)
		{
			
			idPlan.setText(String.valueOf(myplan.getIdPlan()));
			nombrePlan.setText(myplan.getNombre());
			precioPlan.setText(String.valueOf(myplan.getPrecioNeto()));
			
			if(myplan.getTipo().toString().equalsIgnoreCase("Cable"))
			{
				botonCable.setSelected(true);
				precioCable.setEnabled(true);
				precioCable.setText(String.valueOf(myplan.getPrecioCable()));
				
			}
			else if(myplan.getTipo().toString().equalsIgnoreCase("Internet"))
			{
				botonInternet.setSelected(true);
				precioInternet.setEnabled(true);
				precioInternet.setText(String.valueOf(myplan.getPrecioInternet()));
				
			}
			else if(myplan.getTipo().toString().equalsIgnoreCase("telefonia"))
			{
				botonTelefono.setSelected(true);
				precioTelefono.setEnabled(true);
				precioTelefono.setText(String.valueOf(myplan.getPrecioTelefono()));
				
			}
			else if(myplan.getTipo().toString().equalsIgnoreCase("telefonoeinternet"))
			{
				botonTelefono.setSelected(true);
				botonInternet.setSelected(true);
				precioTelefono.setEnabled(true);
				precioInternet.setEnabled(true);
				precioInternet.setText(String.valueOf(myplan.getPrecioInternet()));
				precioTelefono.setText(String.valueOf(myplan.getPrecioTelefono()));
				
			}
			else if(myplan.getTipo().toString().equalsIgnoreCase("telefonoecable"))
			{
				botonCable.setSelected(true);
				botonTelefono.setSelected(true);
				precioCable.setEnabled(true);
				precioTelefono.setEnabled(true);
				precioCable.setText(String.valueOf(myplan.getPrecioCable()));
				precioTelefono.setText(String.valueOf(myplan.getPrecioTelefono()));
				
				
			}
			else if(myplan.getTipo().toString().equalsIgnoreCase("internetecable"))
			{
				botonInternet.setSelected(true);
				botonCable.setSelected(true);
				precioInternet.setEnabled(true);
				precioCable.setEnabled(true);
				precioInternet.setText(String.valueOf(myplan.getPrecioInternet()));
				precioCable.setText(String.valueOf(myplan.getPrecioCable()));
				
			}
			else if (myplan.getTipo().toString().equalsIgnoreCase("todo"))
			{
				botonTelefono.setSelected(true);
				botonInternet.setSelected(true);
				botonCable.setSelected(true);
				precioCable.setEnabled(true);
				precioInternet.setEnabled(true);
				precioTelefono.setEnabled(true);
				precioCable.setText(String.valueOf(myplan.getPrecioCable()));
				precioInternet.setText(String.valueOf(myplan.getPrecioInternet()));
				precioTelefono.setText(String.valueOf(myplan.getPrecioTelefono()));
			}
			descripcionPlan.setText(myplan.getDescripcion());
			
		}
		
		
	}
	
	public float revisarPrecioCable()
	{
		float f=0;
		if(!(precioCable.getText().equalsIgnoreCase("")) )
		{
			try
			{
				if(isNumeric(precioCable.getText()))
				{
					f= Float.valueOf(precioCable.getText());
					
					return f;
				}
				else
					return 0;
				
			}catch(Exception e)
			{
			e.printStackTrace();
			return 0;
			}
		}	
		else 
			return -1;

		
	}
	
	public float revisarPrecioInternet()
	{
		float f=0;
		if(!(precioInternet.getText().equalsIgnoreCase("")) )
		{
			try
			{
				if(isNumeric(precioInternet.getText()))
				{
					f= Float.valueOf(precioInternet.getText());
					return f;
				}
				else
				{
					return 0;
				}
				
				
				
				
				
			}catch(Exception e)
			{
			e.printStackTrace();
			return 0;
			}
		}
		else 
			return -1;

		
	}
	
	public float revisarPrecioTelefono()
	{
		float f=0;
		if(!(precioTelefono.getText().equalsIgnoreCase("")) )
		{
			try
			{
				if(isNumeric(precioTelefono.getText()))
				{
					f= Float.valueOf(precioTelefono.getText());
					return f;
				}
				else
				{
					return 0;
				}
				
				
				
				
			}catch(Exception e)
			{
			e.printStackTrace();
			return 0;
			}
		}
		else 
			return -1;

		
	}
	
	  public static boolean isNumeric(String str) {
	        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("")==false);
	    }
	
}


