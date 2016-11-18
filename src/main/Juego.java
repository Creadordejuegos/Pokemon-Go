package main;
import graficos.Buscando;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Juego extends Application implements EventHandler<KeyEvent>{
	public static final Integer ANCHO = 600;
	public static final Integer ALTO = 600;
	public static void main(String[] args) {
		System.out.println("Empieza el programa");
		Application.launch(args);

	}

	@Override
	public void start(Stage ventana) throws Exception {
		// TODO Auto-generated method stub
      ventana.setTitle("Pokemon Go");
//	  ventana.setWidth(1000);
//	  ventana.setHeight(700);
	  ventana.setScene(new Buscando());
	  ventana.show();
	  
	}

	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Han pulsado una tecla"+ event.getCode());
	
	
	
	
	
	
	
	}

}
