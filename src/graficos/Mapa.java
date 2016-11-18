package graficos;

import java.util.HashMap;

import graficos.Sprite.Direccion;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow.AnchorLocation;

public class Mapa {
	// Constantes
	private static final Integer FILAS = Buscando.ALTO_PANTALLA / CeldaMapa.ALTO_CELDA + 4;
	private static final Integer COLS = Buscando.ANCHO_PANTALLA / CeldaMapa.ANCHO_CELDA + 4;
	private static final Integer PASO_VERTICAL = 1; // Tamaño de scroll de cada
	private Buscando buscando;
													// paso
	private static final Integer PASO_HORIZONTAL = 1;

	// Atributos
	private HashMap<Coordenadas, CeldaMapa> mapa = new HashMap<>(); // Mapa de
																	// Celdas
	private Group raiz = new Group(); // Nodo raíz del mapa
	private Integer fila, col; // Posición actual en el mapa
	private Integer scrollVertical, scrollHorizontal; // Scroll acumulado

	// private Text posicion = new Text("posición");

	// Constructor
	public Mapa(Buscando buscando) {
		this.buscando = buscando;
		// Creación del Grupo inicial (centrado en 0,0)
		for (int i = -FILAS / 2; i < FILAS / 2; i++) {
			for (int j = -COLS / 2; j < COLS / 2; j++) {
				CeldaMapa celda = this.verCelda(i, j);
				Node nodoCelda = celda.getNodo();
				nodoCelda.setTranslateY((i + FILAS / 2) * CeldaMapa.ANCHO_CELDA);
				nodoCelda.setTranslateX((j + COLS / 2) * CeldaMapa.ALTO_CELDA);
				raiz.getChildren().add(nodoCelda);
				// if (i == 0 && j == 0)
				// celda.ponerCirculo();
			}
		}

		// Inicializamos la posición y el scroll acumulado
		this.col = 0;
		this.fila = 0;
		this.scrollVertical = 0;
		this.scrollHorizontal = 0;

		// Centramos el mapa
		this.raiz.setTranslateY(-2 * CeldaMapa.ALTO_CELDA);
		this.raiz.setTranslateX(-2 * CeldaMapa.ANCHO_CELDA);

		// this.posicion.setX(300);
		// this.posicion.setY(300);
		// this.posicion.setStroke(Color.WHITE);
		// this.raiz.getChildren().add(posicion);
	}

	public CeldaMapa verCelda(Integer fila, Integer col) {
		Coordenadas clave = new Coordenadas(fila, col);
		CeldaMapa celda = this.mapa.get(clave);
		if (celda == null) { // Si la celda no está en el mapa
			celda = new CeldaMapa(); // La creamos
			mapa.put(clave, celda); // y la guardamos en el mapa
		}
		return celda;
	}

	public Node verMapa() {
		return this.raiz;
	}

	public void animar(Direccion direccion) {
		switch (direccion) {
		case Abajo:
			this.raiz.setTranslateY(this.raiz.getTranslateY() - Mapa.PASO_VERTICAL);
			this.scrollVertical -= Mapa.PASO_VERTICAL;
			break;
		case Arriba:
			this.raiz.setTranslateY(this.raiz.getTranslateY() + Mapa.PASO_VERTICAL);
			this.scrollVertical += Mapa.PASO_VERTICAL;
			break;
		case Derecha:
			this.raiz.setTranslateX(((int) this.raiz.getTranslateX()) - Mapa.PASO_HORIZONTAL);
			this.scrollHorizontal -= Mapa.PASO_HORIZONTAL;
			break;
		case Izquierda:
			this.raiz.setTranslateX(this.raiz.getTranslateX() + Mapa.PASO_HORIZONTAL);
			this.scrollHorizontal += Mapa.PASO_HORIZONTAL;
			break;
		case Quieto:
		default:
		}
		// Veamos si hay que cargar nuevas celdas porque nos hemos hecho
		// demasiado scroll
		if (this.scrollHorizontal >= CeldaMapa.ANCHO_CELDA / 2)
			this.cargar(Direccion.Izquierda);
		else if (this.scrollHorizontal <= -CeldaMapa.ANCHO_CELDA / 2)
			this.cargar(Direccion.Derecha);
		else if (this.scrollVertical >= CeldaMapa.ALTO_CELDA / 2)
			this.cargar(Direccion.Arriba);
		else if (this.scrollVertical <= -CeldaMapa.ALTO_CELDA / 2)
			this.cargar(Direccion.Abajo);

		// this.posicion.setText("Fila: " + fila + ", " + "Col: " + col);
	}

	private void cargar(Direccion direccion) {
		switch (direccion) {
		case Abajo: // Cargamos fila inferior y borramos fila superior
			if (!this.verCelda(fila + 1, col).esObstaculo()) {
				for (int j = col - COLS / 2; j < col + COLS / 2; j++) {
					Integer filaNueva = fila + FILAS / 2;
					CeldaMapa celda = this.verCelda(filaNueva, j);
					Node nodoCelda = celda.getNodo();

					nodoCelda.setTranslateY((fila + FILAS) * CeldaMapa.ALTO_CELDA);
					nodoCelda.setTranslateX((j + COLS / 2) * CeldaMapa.ANCHO_CELDA);
					this.raiz.getChildren().add(nodoCelda);

					Integer filaVieja = fila - FILAS / 2;
					this.raiz.getChildren().remove(this.verCelda(filaVieja, j).getNodo());
				}
				this.scrollVertical = CeldaMapa.ALTO_CELDA / 2;
				this.fila++;
			} else {
				// Deshacemos el último paso
				this.raiz.setTranslateY(this.raiz.getTranslateY() + Mapa.PASO_VERTICAL);
				this.scrollVertical += Mapa.PASO_VERTICAL;
			}
			break;
		case Arriba: // Cargamos fila superior y borramos la inferior
			if (!this.verCelda(fila - 1, col).esObstaculo()) {
				for (int j = col - COLS / 2; j < col + COLS / 2; j++) {
					Integer filaNueva = fila - FILAS / 2 - 1;
					CeldaMapa celda = this.verCelda(filaNueva, j);
					Node nodoCelda = celda.getNodo();

					nodoCelda.setTranslateY((fila - 1) * CeldaMapa.ALTO_CELDA);
					nodoCelda.setTranslateX((j + COLS / 2) * CeldaMapa.ANCHO_CELDA);
					this.raiz.getChildren().add(nodoCelda);

					Integer filaVieja = fila + FILAS / 2 - 1;
					this.raiz.getChildren().remove(this.verCelda(filaVieja, j).getNodo());
				}
				this.scrollVertical = -CeldaMapa.ALTO_CELDA / 2;
				this.fila--;
			} else {
				// Deshacemos el último paso
				this.raiz.setTranslateY(this.raiz.getTranslateY() - Mapa.PASO_VERTICAL);
				this.scrollVertical -= Mapa.PASO_VERTICAL;
			}
			break;
		case Derecha:
			if (!this.verCelda(fila, col + 1).esObstaculo()) {
				for (int i = fila - FILAS / 2; i < fila + FILAS / 2; i++) {
					Integer colNueva = col + COLS / 2;
					CeldaMapa celda = this.verCelda(i, colNueva);
					Node nodoCelda = celda.getNodo();

					nodoCelda.setTranslateY((i + FILAS / 2) * CeldaMapa.ALTO_CELDA);
					nodoCelda.setTranslateX((col + COLS - 1) * CeldaMapa.ANCHO_CELDA);
					this.raiz.getChildren().add(nodoCelda);

					Integer colVieja = col - COLS / 2;
					this.raiz.getChildren().remove(this.verCelda(i, colVieja).getNodo());
				}
				this.scrollHorizontal = CeldaMapa.ANCHO_CELDA / 2;
				this.col++;
			} else {
				// Deshacemos el último paso
				this.raiz.setTranslateX(this.raiz.getTranslateX() + Mapa.PASO_HORIZONTAL);
				this.scrollHorizontal += Mapa.PASO_HORIZONTAL;
			}
			break;
		case Izquierda:
			if (!this.verCelda(fila, col - 1).esObstaculo()) {
				for (int i = fila - FILAS / 2; i < fila + FILAS / 2; i++) {
					Integer colNueva = col - COLS / 2 - 1;
					CeldaMapa celda = this.verCelda(i, colNueva);
					Node nodoCelda = celda.getNodo();

					nodoCelda.setTranslateY((i + FILAS / 2) * CeldaMapa.ALTO_CELDA);
					nodoCelda.setTranslateX((col - 1) * CeldaMapa.ANCHO_CELDA);
					this.raiz.getChildren().add(nodoCelda);

					Integer colVieja = col + COLS / 2 - 1;
					this.raiz.getChildren().remove(this.verCelda(i, colVieja).getNodo());
				}
				this.scrollHorizontal = -CeldaMapa.ANCHO_CELDA / 2;
				this.col--;
			} else {
				// Deshacemos el último paso
				this.raiz.setTranslateX(this.raiz.getTranslateX() - Mapa.PASO_HORIZONTAL);
				this.scrollHorizontal -= Mapa.PASO_HORIZONTAL;
			}
			break;
		case Quieto:
		default:
		}
		if(verCelda(fila,col).tienePokemon()){
			System.out.println("te pillé");
			
			IVPokemon pokemon = verCelda(fila,col).getPokemon();
			//this.raiz.getChildren().add(pokemon);
			
			buscando.mostrarpokemon(pokemon);
		}
	}

}
