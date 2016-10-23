package logico;

import java.io.Serializable;

public class Vendedor extends Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Vendedor(String cedula, String nombre, String apellido, String direccion, String telefono, String correo,
			Sexo sexo, String contra, int id) {
		super(cedula, nombre, apellido, direccion, telefono, correo, sexo, contra, id);
		// TODO Auto-generated constructor stub
	}

	public Vendedor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return "Vendedor";
	}

}
