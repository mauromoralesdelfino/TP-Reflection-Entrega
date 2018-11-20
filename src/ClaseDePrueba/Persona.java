package ClaseDePrueba;


import Anotaciones.Columna;
import Anotaciones.Id;
import Anotaciones.Tabla;

@Tabla(nombre="Persona")
public class Persona {

	@Id
	@Columna(nombre="Id")
	private Integer id;
	@Columna(nombre="nombre")
	private String nombre;
	@Columna(nombre="apellido")
	private String apellido;
	@Columna(nombre="dni")
	private String dni;
	@Columna(nombre="edad")
	private Integer edad;
	
	public Persona()
	{}
	
	public Persona(String nombre, String apellido, String dni, Integer edad) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", edad=" + edad + "]";
	}
	
	

	
}
