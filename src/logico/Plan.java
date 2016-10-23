package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Plan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Atributos
	private static int idCont = 0; //deberia ser UNSIGNED
	private int idPlan;
	private String nombre;
	private String descripcion;
	private float precioBruto;
	private float precioNeto;
	private TipoServicio tipo;
	private float precioCable;
	private float precioInternet;
	private float precioTelefono;
	private int diaFacturacion;
	
	//Constructores
	public Plan() {
		
	}

	//Getters-Setters

	

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public float getPrecioBruto() {
		return precioBruto;
	}



/*
	public float getPrecioNeto() {
		return precioNeto;
	}


	public void setPrecioNeto() {
		this.precioNeto = this.precioBruto * 1.28f; //SHOULD BE CONST VARIABLE
	}

*/
	

	//@Maxwell: not necessary?
	//public void setServicios(ArrayList<Servicio> servicios) {
	//	this.servicios = servicios;
	//}
	
	
	//Metodos


	public TipoServicio getTipo() {
		return tipo;
	}

	public void setTipo(TipoServicio tipo) {
		this.tipo = tipo;
	}

	public int getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public void setPrecioBruto(float precioBruto) {
		this.precioBruto = precioBruto;
	}

	public void setPrecioNeto(float precioNeto) {
		this.precioNeto = precioNeto * 1.28f;
	}
	public float getPrecioNeto()
	{
		return precioNeto;
	}

	public float getPrecioCable() {
		return precioCable;
	}

	public void setPrecioCable(float precioCable) {
		this.precioCable = precioCable;
	}

	public float getPrecioInternet() {
		return precioInternet;
	}

	public void setPrecioInternet(float precioInternet) {
		this.precioInternet = precioInternet;
	}

	public float getPrecioTelefono() {
		return precioTelefono;
	}

	public void setPrecioTelefono(float precioTelefono) {
		this.precioTelefono = precioTelefono;
	}

	public int getDiaFacturacion() {
		return diaFacturacion;
	}

	public void setDiaFacturacion(int diaFacturacion) {
		this.diaFacturacion = diaFacturacion;
	}
}
