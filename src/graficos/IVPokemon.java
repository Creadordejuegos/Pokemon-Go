package graficos;

import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IVPokemon extends ImageView {
	private static final Image IMG_POKEMONS = new Image(IVPokemon.class.getClassLoader().getResourceAsStream("recursos/pokemons_1.png"));
	private static final Integer NUM_POKEMONS = 3;
	private static final Integer ANCHO_FRAME = 25;
	private static final Integer ALTO_FRAME = 33;
	private static final Integer ALTO_POKEMON = ALTO_FRAME * 3;
	private static final Integer LISTA_POS_Y[] = {0, 230, 330, 500, 585, 832, 1120, 1252, 1316, 1744};
	
	private final Integer xInicial = 0;
	private final Integer yInicial;
	private final Random dado = new Random();

	public IVPokemon() {
		super();
		this.setImage(IMG_POKEMONS);
		this.yInicial = LISTA_POS_Y[this.dado.nextInt(LISTA_POS_Y.length)];
		this.setViewport(new Rectangle2D(xInicial, yInicial, ANCHO_FRAME, ALTO_FRAME));
	}
	
}
