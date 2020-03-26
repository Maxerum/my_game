package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameScene {
	private StackPane layout2;
	private Scene sceneGame;
	private Stage gameStage;
	private Stage menuStage;
	
	//Snake variables
	static int width = 580;
	static int height = 560;
	static int speed = 5;
	static int foodX = 0;
	static int foodY = 0;
	static List<Corner> snake = new ArrayList<>();
	static Vect vector = Vect.right;
	static boolean gameOver = false;
	static Random rand = new Random();
	
	public enum Vect{
		left,right,down,up
	}
	
	public static class Corner{
		int x;
		int y;
		
		public Corner(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	/*public static void replaceFood() {
		while(true){
			foodX = rand.nextInt(width);
			foodY = rand.nextInt(height);
			
			for(Corner c:snake) {
				if(c.x == foodX && c.y == foodY) {
					
				}
			}
			
		}
	}*/
	
	sceneGame.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
		if (key.getCode() == KeyCode.W) {
			vector = Vect.up;
		}
		if (key.getCode() == KeyCode.A) {
			vector = Vect.left;
		}
		if (key.getCode() == KeyCode.S) {
			vector = Vect.down;
		}
		if (key.getCode() == KeyCode.D) {
			vector = Vect.right;
		}

	});
	
	
	public static void replaceFood() {
		start: while (true) {
			foodX = rand.nextInt(width);
			foodY = rand.nextInt(height);

			for (Corner c : snake) {
				if (c.x == foodX && c.y == foodY) {
					continue start;
				}
			}
			speed++;
			break;

		}
	}
	
	public GameScene() {
		layout2 = new StackPane();
		sceneGame = new Scene(layout2, 900, 600, Color.TRANSPARENT);
		gameStage = new Stage();
		
		gameStage.setScene(sceneGame);
		
		/* createKeyListeners(); */
	}

	/*
	 * private void createKeyListeners() { sceneGame.setOnKeyPressed(new
	 * EventHandler<KeyEvent>() {
	 * 
	 * }
	 * 
	 * }
	 */
	
	public void loadNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		gameStage.show();
		replaceFood();
	}
	
	
	public void createBackground() {
		Canvas canvas = new Canvas(900, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        layout2.setStyle("-fx-background-color: rgb(0, 250, 154);");
        layout2.getChildren().add(canvas);
        gc.setFill(Color.rgb(0, 250, 154));
        //draw rectangle
        gc.fillRect(0, 0, 900, 500);
        
        gc.setFill(Color.rgb(11, 54, 82));
        for (int i = 6; i <= 582; i += 17) {
            for (int j = 6; j <= 600; j += 17) {
                gc.fillRect(i, j, 13, 13);
            }
        }
        
        gc.setFill(Color.rgb(0, 250, 154));
        gc.fillRect(20, 20, 545, 562);
        
        gc.setFill(Color.rgb(11, 54, 82));
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gc.fillText(" SNAKE ", 660, 35);
        
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 13));  
        gc.setFill(Color.rgb(11, 54, 82));
        
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
		
        
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        
        gc.fillText("Best Score", 696, 110);
        gc.fillRect(670, 120, 140, 30);
        gc.setFill(Color.rgb(11, 54, 82));
		gc.fillRect(671, 121, 138, 28); 
        gc.setFill(Color.rgb(11, 54, 82));
		
        gc.fillText("Total Score", 696, 190);
        gc.fillRect(670, 200, 140, 30);
        gc.setFill(Color.rgb(11, 54, 82));
        gc.fillRect(671, 201, 138, 28);
        gc.setFill(Color.rgb(11, 54, 82));
		
        gc.fillText("    Fruits ",696, 270);
        gc.fillRect(670, 280, 140, 30);
        gc.setFill(Color.rgb(11, 54, 82));
        gc.fillRect(671, 281, 138, 28);
        gc.setFill(Color.rgb(11, 54, 82));
        
        gc.fillText("   Speed", 696, 350);
        gc.fillRect(670, 360, 140, 30);
        gc.setFill(Color.rgb(11, 54, 82));
        gc.fillRect(671, 361, 138, 28);
        gc.setFill(Color.rgb(11, 54, 82));
        
        Button button = new Button();
        
        button.setText("BACK");
        button.setStyle("-fx-background-color:rgb(11, 54, 82);-fx-text-fill:rgb(0, 250, 154)");
        button.setTranslateX(290);
        button.setTranslateY(200);
        button.setMaxWidth(140);
        button.setMaxHeight(50);
        
        button.setOnMouseClicked(click-> {
			this.gameStage.hide();
			menuStage.show();
        });
        layout2.getChildren().add(button);
        
        
	}
	

}
