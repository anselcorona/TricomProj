package logico;

import java.io.Serializable;

public class Servicio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Atributos
	private static int idCont = 0; //deberia ser UNSIGNED
	private String idServicio;
	private String nombre;
	private TipoServicio tipo;
	private String descripcion;
	private float precioBruto;
	private float precioNeto;
	//@Maxwell: necesito constante ITEBIS + ISC + ETC en clase controladora
	
	//Constructores
	public Servicio() {
		this.setIdServicio();
	}
	
	//Getters-Setters
	public String getIdServicio() {
		return idServicio;
	}

	void setIdServicio() {
		idCont++;
		this.idServicio = "PROD-0"+idCont;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoServicio getTipo() {
		return tipo;
	}

	public void setTipo(TipoServicio tipo) {
		this.tipo = tipo;
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

	public void setPrecioBruto(float precioBruto) {
		if(precioBruto > 0)
			this.precioBruto = precioBruto;
	}

	public float getPrecioNeto() {
		return precioNeto;
	}

	public void setPrecioNeto() {
		this.precioNeto = precioBruto * 1.28f;
	}
	
	//Metodos

}
