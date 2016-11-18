package graficos;

public class Coordenadas {
	//Atributos
	private final Integer fila;
	private final Integer col;
	
	//Constructor
	public Coordenadas(Integer fila, Integer col) {
		super();
		this.fila = fila;
		this.col = col;
	}

	public Integer getFila() {
		return fila;
	}

	public Integer getCol() {
		return col;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((col == null) ? 0 : col.hashCode());
		result = prime * result + ((fila == null) ? 0 : fila.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenadas other = (Coordenadas) obj;
		if (col == null) {
			if (other.col != null)
				return false;
		} else if (!col.equals(other.col))
			return false;
		if (fila == null) {
			if (other.fila != null)
				return false;
		} else if (!fila.equals(other.fila))
			return false;
		return true;
	}

	
}
