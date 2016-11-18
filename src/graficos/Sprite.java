package graficos;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {
	public enum Direccion {
		Quieto, Arriba, Abajo, Izquierda, Derecha
	};

	private Integer xInicial = 69;
	private Integer yInicial = 103;
	private Integer anchoFrame = 32;
	private Integer altoFrame = 37;
	private Direccion direccion;
	private Integer coordX = xInicial, coordY = yInicial;
	private long tiempoUltimaAnimacion = 0;

	public Sprite() {
		super();
		this.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream("recursos/entrenadores.png")));
		this.setViewport(new Rectangle2D(xInicial, yInicial, anchoFrame, altoFrame));
		this.direccion = Direccion.Quieto;
	}

	public void animar(long now) {
		if (now - tiempoUltimaAnimacion < 200000000)
			return;
		else
			this.tiempoUltimaAnimacion = now;

		switch (this.direccion) {
		case Abajo:
			this.coordY = this.yInicial;
			break;
		case Arriba:
			this.coordY = this.yInicial + this.altoFrame * 3;
			break;
		case Derecha:
			this.coordY = this.yInicial + this.altoFrame * 2;
			break;
		case Izquierda:
			this.coordY = this.yInicial + this.altoFrame * 1;
			break;
		case Quieto:
			this.coordX = xInicial;
			break;
		default:
			break;
		}
		if (this.direccion != Direccion.Quieto) {
			this.coordX += this.anchoFrame;
			if (this.coordX > this.xInicial + this.anchoFrame * 3)
				this.coordX = this.xInicial;
		}

		this.setViewport(new Rectangle2D(coordX, coordY, anchoFrame, altoFrame));
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	
}
