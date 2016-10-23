package logico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Controladora implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Persona>listapersona;
	private static Controladora miControladora;
	private ArrayList<Plan>listaplan;
	private ArrayList<Factura>listafactura;
	private Administrador defaultAdmin;
	private Persona usuarioActual;
	private LocalDate fechaSistema;
	
	private Controladora() {
		super();
		this.listapersona = new ArrayList<Persona>();
		this.listaplan = new ArrayList<Plan>();
		this.listafactura = new ArrayList<Factura>();
		this.defaultAdmin = new Administrador("000-0000000-0", "admin", "user", "none", "111-111-1111", "admin@gmail.com", Sexo.masculino, "0000", 0);
		this.listapersona.add(defaultAdmin);
		this.setFechaSistema(LocalDate.now());
	}
	
	public LocalDate getFechaSistema() {
		return fechaSistema;
	}

	public void setFechaSistema(LocalDate fechaSistema) {
		this.fechaSistema = fechaSistema;
	}

	public Administrador getdefaultAdmin(){
		return this.defaultAdmin;
	}
	
	
	public Persona getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Persona usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public static Controladora getControladora()
	{
		if(miControladora == null)
		{
			miControladora = new Controladora();
			
		}
		
		return miControladora;
	}

	public ArrayList<Persona> getListapersona() {
		return listapersona;
	}

	public void setListapersona(ArrayList<Persona> listapersona) {
		this.listapersona = listapersona;
	}

	public void registrarPersona(Persona p)
	{
		listapersona.add(p);
	}
	
	public void registrarPlan(Plan p)
	{
		listaplan.add(p);
	}
	
	public int getultimoId()
	{
		int id = 0;
		
		for(int i =0; i < listapersona.size();i++)
		{
			id = listapersona.get(i).getId();
		}
		
		return id+1;
	}
	

	
	public Cliente encontrarCliente(String cedula)
	{
		Cliente c = null;
		
		for(int i =0; i < listapersona.size();i++)
		{
			if(listapersona.get(i).getCedula().equalsIgnoreCase(cedula))
			{
				c = (Cliente) listapersona.get(i);
			}
		}
		
		
		return c;
	}
	
	public Cliente encontrarClienteporNombre(String nombre)
	{
		Cliente c = null;
		
		for(int i =0; i < listapersona.size();i++)
		{
			if (listapersona.get(i) instanceof Cliente) {
				
				if(listapersona.get(i).getNombre().equalsIgnoreCase(nombre))
				{
					c = (Cliente)listapersona.get(i);
				}
			}
		}
		
		return c;
	}
	
	public Vendedor encontrarVendedor(String cedula)
	{
		Vendedor v = null;
		
		for(int i =0; i < listapersona.size();i++)
		{
			if(listapersona.get(i).getCedula().equalsIgnoreCase(cedula))
			{
				v = (Vendedor) listapersona.get(i);
			}
		}
		
		return v;
	}
	
	public Administrador encontrarAdm(String cedula)
	{
		Administrador a = null;
		
		for(int i =0; i < listapersona.size();i++)
		{
			if(listapersona.get(i).getCedula().equalsIgnoreCase(cedula))
			{
				a = (Administrador) listapersona.get(i);
			}
		}
		
		return a;
	}
	
	public Plan encontrarPlan(int id)
	{
		Plan p = null;
		
		for (Plan plan : listaplan) {
			if(plan.getIdPlan() == id)
			{
				p = plan;
			}
		}
		
		return p;
	}
	
	public int ultimoIDPlan()
	{
		int p = 0;
		
		for(int i =0; i < listaplan.size();i++)
		{
			p = listaplan.get(i).getIdPlan();
		}
		
		return p+1;
	}

	public void modificarCliente(Cliente client)
	{
		int pos = -1;
		int real = -1;
		
		for (Persona persona : listapersona) {
			
			pos++;
			if(persona.getCedula().equalsIgnoreCase(client.getCedula()))
			{
				real = pos;
			}
		}
		
		listapersona.remove(real);
		listapersona.add(real, client);
	}
	
	public void modificarVendedor(Persona myvend)
	{
		int pos = -1;
		int real = -1;
		
		for (Persona persona : listapersona) {
			
			pos++;
			if(persona.getCedula().equalsIgnoreCase(myvend.getCedula()))
			{
				real = pos;
			}
		}
		
		listapersona.remove(real);
		listapersona.add(real, myvend);
	}
	
	public void modificarPlan(Plan p)
	{
		int pos =-1;
		int real = -1;
		
		for (Plan plan : listaplan) {
			pos++;
			if(plan.getIdPlan() == p.getIdPlan())
			{
				real = pos;
			}
		}
		
		listaplan.remove(real);
		listaplan.add(real, p);
	}
	
	public void borrarCliente(Cliente c)
	{
		listapersona.remove(c);
	}
	
	public void borrarPlan(Plan p)
	{
		listaplan.remove(p);
	}

	public ArrayList<Plan> getListaplan() {
		return listaplan;
	}

	public void setListaplan(ArrayList<Plan> listaplan) {
		this.listaplan = listaplan;
	}
	
	//Metodos de ficheros
	public boolean guardarFicheros() {
		boolean resultado = false;
		FileOutputStream fos;
		ObjectOutputStream personasOS = null; 
		ObjectOutputStream planesOS = null;
		ObjectOutputStream controladoraOS = null; 
		try {
			/*fos = new FileOutputStream("personas.bin");
			personasOS = new ObjectOutputStream(fos);
			personasOS.writeObject(this.listapersona);
			personasOS.close();
			fos = new FileOutputStream("planes.bin");
			planesOS = new ObjectOutputStream(fos);
			planesOS.writeObject(this.listaplan);
			planesOS.close();*/
			fos = new FileOutputStream("controladora.bin");
			controladoraOS = new ObjectOutputStream(fos);
			this.defaultAdmin = null;
			controladoraOS.writeObject(this);
			controladoraOS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(personasOS != null && planesOS != null || controladoraOS != null)
			resultado = true;
		return resultado;
	}
	
	
	public void leerFicheros(){
		///boolean resultado = false;
		File f;
		FileInputStream fis;
		ObjectInputStream ois;
		//Leer usuarios desde fichero binario
	    	try {
				f = new File("personas.bin");
				if(f.exists()){
					fis = new FileInputStream(f);
					ois = new ObjectInputStream(fis);
					this.listapersona = (ArrayList<Persona>)ois.readObject();
					ois.close();	
				}
				f = new File("planes.bin");
				if(f.exists()){
					fis = new FileInputStream(f);
					ois = new ObjectInputStream(fis);
					this.listaplan = (ArrayList<Plan>)ois.readObject();
					ois.close();	
				}
				f = new File("controladora.bin");
				if(f.exists()){
					fis = new FileInputStream(f);
					ois = new ObjectInputStream(fis);
					miControladora = (Controladora)ois.readObject();
					ois.close();	
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Controladora.getControladora().guardarFicheros();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}

	public ArrayList<Factura> getListafactura() {
		return listafactura;
	}

	public void setListafactura(ArrayList<Factura> listafactura) {
		this.listafactura = listafactura;
	}
	
	public void registrarFactura(Factura f)
	{
		listafactura.add(f);
	}
	public void procesarFacturas(){
		this.listafactura = new ArrayList<Factura>();
		for (Persona c : listapersona) {
			if(c instanceof Cliente){
				((Cliente) c).generarFacturas();
				for (Factura f : ((Cliente) c).getFacturas()) {
					if(f.getEstado() == EstadoFactura.generada)
						this.listafactura.add(f);
					f.setEstado(EstadoFactura.activa);
				}
				//if(!((Cliente) c).getFacturas().isEmpty()){
					//this.listafactura.addAll(((Cliente) c).getFacturas());
				//}
					
			}
		}
	}
}
