package graficos;

import graficos.Sprite.Direccion;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Buscando extends Scene implements EventHandler<KeyEvent> {
	public static final Integer ANCHO_PANTALLA = 800;
	public static final Integer ALTO_PANTALLA = 600;
	private static Group raiz = new Group();
	private Mapa mapa = new Mapa(this);
	private Node nodoMapa;
	private Group nubes = new Group();
	private Sprite entrenador = new Sprite();
	private Animador animador;
	private Integer puntos = 0;
	private SubScene subescena;
	private Text tPuntos;

	public Buscando() {
		super(raiz, ANCHO_PANTALLA, ALTO_PANTALLA, Color.GREEN);

		this.nodoMapa = this.mapa.verMapa();
		this.raiz.getChildren().add(this.nodoMapa);
//		this.nodoMapa.setTranslateX(Juego.ANCHO_PANTALLA / 2 - this.nodoMapa.getBoundsInParent().getWidth() / 2);
//		this.nodoMapa.setTranslateY(Juego.ALTO_PANTALLA / 2 - this.nodoMapa.getBoundsInParent().getHeight() / 2);
		
		this.entrenador.setLayoutX(ANCHO_PANTALLA / 2 - 32/2 );
		this.entrenador.setLayoutY(ALTO_PANTALLA / 2 - 25);
		this.raiz.getChildren().add(this.entrenador);
		
		Image nube1 = new Image(this.getClass().getClassLoader().getResourceAsStream("recursos/nube1.png"));
		Image nube2 = new Image(this.getClass().getClassLoader().getResourceAsStream("recursos/nube2.png"));
		ImageView ivNube1 = new ImageView(nube1);
		ImageView ivNube2 = new ImageView(nube2);
		this.nubes.getChildren().addAll(ivNube1, ivNube2);
		ivNube2.setTranslateX(100);
		ivNube2.setTranslateY(50);
		this.raiz.getChildren().add(this.nubes);
		this.nubes.setTranslateX(-100);
		this.nubes.setTranslateY(ALTO_PANTALLA / 2);

		this.setOnKeyPressed(this);
		this.setOnKeyReleased(this);
		
		//Creamos la subescena con los puntos
		Group raizSubescena = new Group();
		this.tPuntos = new Text("Puntos:");
		this.tPuntos.setFont(Font.font("Verdana", FontWeight.BOLD, 54));
		this.tPuntos.setFill(Color.WHITE);
		raizSubescena.getChildren().add(this.tPuntos);
		this.subescena = new SubScene(raizSubescena, ANCHO_PANTALLA, ALTO_PANTALLA);
		raizSubescena.setTranslateX(100);
		raizSubescena.setTranslateY(100);
		this.raiz.getChildren().add(raizSubescena);
		
		
		this.animador = new Animador(this);
		this.animador.start();
	}

	@Override
	public void handle(KeyEvent event) {
		if (event.getEventType().equals(KeyEvent.KEY_RELEASED)){
			this.entrenador.setDireccion(Direccion.Quieto);
			return;
		}
			
		//System.out.println("Se ha pulsado la tecla " + event.getCode());
		switch (event.getCode()) {
		case UP:
			this.entrenador.setDireccion(Direccion.Arriba);
			break;
		case DOWN:
			this.entrenador.setDireccion(Direccion.Abajo);
			break;
		case RIGHT:
			this.entrenador.setDireccion(Direccion.Derecha);
			break;
		case LEFT:
			this.entrenador.setDireccion(Direccion.Izquierda);
			break;
		default:
			System.out.println("Tecla no programada.");
		}
	}
	
	public void animar(long now){
		this.nubes.setTranslateX(this.nubes.getTranslateX() + 2);
		this.entrenador.animar(now);
		this.mapa.animar(this.entrenador.getDireccion());
	}

	public void mostrarpokemon(IVPokemon pokemon) {
		pokemon.setFitWidth(200);
		pokemon.setPreserveRatio(true);
		this.raiz.getChildren().add(pokemon);
		puntos = puntos+1;
		this.tPuntos.setText("puntos: "+puntos);
	}

}
