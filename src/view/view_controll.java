package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class view_controll {
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	
	public view_controll() {
		mainPane=new AnchorPane();
		mainScene=new Scene(mainPane,800,700);
		mainStage =new Stage();
		mainStage.setScene(mainScene);
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	public void createMenuButtons() {
		Button button = new Button();
		mainPane.getChildren().add(button);
		
	}
	
	
	
}
