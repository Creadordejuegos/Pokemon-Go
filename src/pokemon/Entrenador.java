package pokemon;

public class Entrenador {
private String nombre;
	private Integer experiencia;
	private Pokemon acompañante;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}
	public Pokemon getAcompañante() {
		return acompañante;
	}
	public void setAcompañante(Pokemon acompañante) {
		this.acompañante = acompañante;
	}
	
}
