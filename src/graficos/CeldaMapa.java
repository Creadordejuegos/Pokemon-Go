package graficos;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CeldaMapa{
	//Atributos estáticos (de la Clase)
	public static final Integer ANCHO_CELDA = 32;
	public static final Integer ALTO_CELDA = 32;
	private static final Image ficheroCelda = new Image(CeldaMapa.class.getClassLoader().getResourceAsStream("recursos/celda.png")); 
	private static final Image ficheroHierba = new Image(CeldaMapa.class.getClassLoader().getResourceAsStream("recursos/hierba.png"));
	private static final Image ficheroPiedra = new Image(CeldaMapa.class.getClassLoader().getResourceAsStream("recursos/piedra2.png"));
	private static final Image ficheroTronco = new Image(CeldaMapa.class.getClassLoader().getResourceAsStream("recursos/tronco1.png"));
	private static final Image ficheroPlanta1 = new Image(CeldaMapa.class.getClassLoader().getResourceAsStream("recursos/planta1.png"));
	private static final Image ficheroPlanta2 = new Image(CeldaMapa.class.getClassLoader().getResourceAsStream("recursos/planta3.png"));
	private static Random dado = new Random();
	
	

	//Atributos de Objetos
	private Group grupo;
	private Boolean tienePokemon = false;
	private Boolean esObstaculo = false;
	private IVPokemon pokemon;
	//Constructor
	public CeldaMapa(){
		this.grupo = new Group(); 
		this.grupo.getChildren().add(new ImageView(ficheroCelda));
//		Rectangle r = new Rectangle(0, 0, ANCHO_CELDA, ALTO_CELDA);
//		r.setStroke(Color.RED);
//		this.grupo.getChildren().add(r);
		
		int aleatorio = dado.nextInt(10);
		if (aleatorio == 1){	//Ponemos en la celda algo especial
			ImageView especial = null;
			switch (dado.nextInt(4)){
			case 0:
				especial = new ImageView(ficheroHierba);
				break;
			case 1:
				especial = new ImageView(ficheroPiedra);
				this.esObstaculo = true;
				break;
			case 2:
				especial = new ImageView(ficheroTronco);
				this.esObstaculo = true;
				break;
			case 3:
				if (dado.nextInt(2) == 0)
					especial = new ImageView(ficheroPlanta2);
				else
					especial = new ImageView(ficheroPlanta2);
				break;
			}
			especial.setPreserveRatio(true);
			especial.setFitWidth(ANCHO_CELDA);
			this.grupo.getChildren().add(especial);
		}
		if (aleatorio == 2){	
			if (dado.nextInt(6) == 0){//Ponemos un Pokémon
				pokemon = new IVPokemon();
				this.grupo.getChildren().add(this.pokemon);
				this.tienePokemon = true;
			}
		}
	}
	
	public IVPokemon getPokemon() {
		if (!tienePokemon)return null;
		this.grupo.getChildren().remove(pokemon);
		this.tienePokemon = false;
		return pokemon;
	}

	public Boolean tienePokemon(){

		return this.tienePokemon;
	}
	
	public Node getNodo(){
		return this.grupo;
	}

	public Boolean esObstaculo() {
		return this.esObstaculo ;
	}

	public void ponerCirculo(){
		Circle c = new Circle(15);
		c.setFill(Color.BLUE);
		c.setTranslateY(ANCHO_CELDA / 2);
		c.setTranslateX(ALTO_CELDA / 2);
		grupo.getChildren().add(c);
	}
}
