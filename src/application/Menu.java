package application;

import java.util.LinkedList;
import java.util.Random;

/*import application.MenuItem.MenuBox;
import application.MenuItem.SubMenu;*/
/*import application.Main.MenuBox;
import application.Main.MenuItem;
import application.Main.SubMenu;*/
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Menu {

	private Pane mainPane;
	private Scene menuScene;
	private Stage mainStage;

	public Menu() {
		mainPane = new Pane();
		menuScene = new Scene(mainPane, 900, 600, Color.TRANSPARENT);
		mainStage = new Stage();
		mainStage.setScene(menuScene);
		setMenuBackground();
		createMenu();
	}

	Stage getMainStage() {
		return mainStage;
	}
	
	
	private void setMenuBackground() {
		Image image = new Image(getClass().getResourceAsStream("black_mamb.png"));
        ImageView img = new ImageView(image);
        img.setFitHeight(600);
        img.setFitWidth(900); 
        mainPane.getChildren().add(img);
	}

	private void createMenu() {
		Label label = new Label("SNAKE");
        label.setFont(new Font("Helvetica", 100));
        label.setTranslateX(310);
        label.setTextFill(Color.web("#0076a3"));
        mainPane.getChildren().add(label);
		
		
        MenuItem newGame = new MenuItem("NEW GAME");
        MenuItem options = new MenuItem("SETTINGS");
        MenuItem exitGame = new MenuItem("EXIT");
		MenuItem other = new MenuItem("OTHER");
		MenuItem records = new MenuItem("RECORDS");
		MenuItem information = new MenuItem("INFORMATION");
		MenuItem optionsBack = new MenuItem("BACK");
		SubMenu optionsMenu = new SubMenu(other, records, information, optionsBack);
		SubMenu mainMenu = new SubMenu(newGame, options, exitGame);

		MenuBox menuBox = new MenuBox(mainMenu);
		
		newGame.setOnMouseClicked(click-> {
			GameScene gameScene = new GameScene();
			gameScene.start(mainStage);
		});
		
	        
	        options.setOnMouseClicked(event->menuBox.setSubMenu(optionsMenu));
	        exitGame.setOnMouseClicked(event-> System.exit(0));
	        optionsBack.setOnMouseClicked(event->menuBox.setSubMenu(mainMenu));
		menuBox.setVisible(true);
		mainPane.getChildren().addAll(menuBox);
		//mainPane.getChildren().add(menuBox);
	}
	
	/*
	 * private void createNewGameButton() { MenuItem newGame = new
	 * MenuItem("NEW GAME");
	 * newGame.setOnMouseClicked(event->mainStage.setScene(sceneGame));
	 * 
	 * }
	 */
	
	/*
	 * private MenuItem createExitButton() { MenuItem exitGame = new
	 * MenuItem("EXIT"); exitGame.setOnMouseClicked(event-> System.exit(0)); return
	 * exitGame; }
	 * 
	 * private MenuItem createOptionsButton() { MenuItem options = new
	 * MenuItem("SETTINGS");
	 * options.setOnMouseClicked(event->menuBox.setSubMenu(optionsMenu)); return
	 * options; }
	 */

}


