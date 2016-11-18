package graficos;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

public class Animador extends AnimationTimer{
	
	private Scene escena;
	
	public Animador(Scene escena){
		this.escena = escena;
	}
	
	@Override
	public void handle(long now) {
		//System.out.println("Animando...");
		((Buscando)(this.escena)).animar(now);
	}

	public Scene getEscena() {
		return escena;
	}

	public void setEscena(Scene escena) {
		this.escena = escena;
	}

}
