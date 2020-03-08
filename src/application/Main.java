package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.view_controll;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			view_controll manager= new view_controll();
			manager.createMenuButtons();
			primaryStage=manager.getMainStage();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
