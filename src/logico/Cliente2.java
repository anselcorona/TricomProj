package logico;

import java.util.ArrayList;

public class Cliente2 {
	//@Maxwell: CLASE DE PRUEBA
	private String idCliente;
	private String nombreCliente;
	private ArrayList<Plan> planes;
	
	public Cliente2(){
		
	}
	
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public ArrayList<Plan> getPlanes() {
		return planes;
	}
	public void setPlanes(ArrayList<Plan> planes) {
		this.planes = planes;
	}
	
}
