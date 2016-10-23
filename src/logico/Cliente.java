package logico;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente extends Persona implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Plan planes;
	private ArrayList<Factura> facturas;
	private boolean activo = true; //@maxwell: agregado nuevo con set/get
	
	public Cliente(String cedula, String nombre, String apellido, String direccion, String telefono, String correo,
			Sexo sexo, String contra, int id) {
		super(cedula, nombre, apellido, direccion, telefono, correo, sexo, contra, id);
		this.facturas = new ArrayList<Factura>();
		// TODO Auto-generated constructor stub
	}
	
	public Cliente() {
		super();
		this.facturas = new ArrayList<Factura>();
	}
	
	//@maxwell: cambiado de set generico a add


	public boolean isActivo() {
		return activo;
	}
	
	//@maxwell: agregado nuevo: set activo con validacion en base a facturas vencidas
	public void setActivo() {
		int cont = 0;
		for (Factura f : facturas) {
			if(f.getEstado() == EstadoFactura.vencida)
				cont++;
		}
		if(cont >= 3)
			this.activo = false;
	}

	public ArrayList<Factura> getFacturas() {
		return facturas;
	}

	public void registrarFactura(Factura f){
		this.facturas.add(f);
	}

	public Plan getPlanes() {
		return planes;
	}

	public void setPlanes(Plan planes) {
		this.planes = planes;
	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return "Cliente";
	}
	
	public void generarFacturas(){
		//this.planes.getDiaFacturacion()
		if(LocalDate.now().getDayOfMonth() == 22 ){
			Factura fact = new Factura(this);
			this.facturas.add(fact);
		}
	}

}
