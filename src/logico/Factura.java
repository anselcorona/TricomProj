package logico;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

public class Factura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Atributos
	private int idCont = 0;
	private String idFactura;
	private LocalDate fechaEmision;
	private LocalDate fechaVencimiento;
	private Cliente cliente;
	private float totalBruto;
	private float totalNeto;
	private boolean vencida = false;
	private EstadoFactura estado;
	
	//Constructores
	public Factura(Cliente c) {
		this.cliente = c;
		setIdFactura();
		setFechaEmision();
		setFechaVencimiento();
		setTotalBruto();
		this.setEstado(EstadoFactura.generada);
	}
	
	//Getters-Setters
	public String getIdFactura() {
		return idFactura;
	}

	void setIdFactura() {
		idCont++;
		this.idFactura = "FACT-0"+idCont;
	}

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	void setFechaEmision() {
		this.fechaEmision = LocalDate.now();
	}
	
	void setFechaEmisionPrueba(){
		this.fechaEmision = LocalDate.now().minusDays(16);
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	void setFechaVencimiento() {
		this.fechaVencimiento = fechaEmision.plusDays(15);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public float getTotalBruto() {
		return totalBruto;
	}

	void setTotalBruto() {
		float totalBruto = cliente.getPlanes().getPrecioNeto();
		this.totalBruto = totalBruto;
	}

	public float getTotalNeto() {
		return totalNeto;
	}

	void setTotalNeto() {
		this.totalNeto = totalBruto * 1.28f;
	}

	public boolean isVencida() {
		return vencida;
	}

	public void setVencida() {
		if(this.fechaVencimiento.compareTo(LocalDate.now()) > 0)
			this.vencida = true;
	}

	public EstadoFactura getEstado() {
		return estado;
	}

	public void setEstado(EstadoFactura estado) {
		this.estado = estado;
	}
	
	//Metodos
	
	public void procesarFactura(){
		Duration periodo = Duration.between(this.fechaEmision, LocalDate.now());
		if(periodo.toDays() >= 15){
			this.estado = EstadoFactura.vencida;
		}
		
	}

}
