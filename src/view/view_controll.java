/*package view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class view_controll {
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	
	public view_controll() {
		mainPane=new AnchorPane();
		mainScene=new Scene(mainPane,800,600);
		mainStage =new Stage();
		mainStage.setScene(mainScene);
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	public void createMenuButtons() {
		Button[] buttons;
		buttons=new Button[3];
		for(int i=0;i<1;i++) {
			buttons[i] = new Button();
			buttons[i].setLayoutX(200);
			buttons[i].setLayoutY(200);
			buttons[i].setStyle("-fx-background-color:yellow");
			/*buttons[i].setOnMousePressed(new EventHandler<MouseEvent> (){
				@Override
				public void handle(MouseEvent event) {
						AnchorPane myPane = new  AnchorPane();
						Scene myScene=new Scene(myPane,800,600);
						Stage mystage= new Stage();
						mystage.setScene(myScene);
				}
			});*/
					
				//}
				/*mainPane.getChildren().add(buttons[0]);
			};*/
			

		/*buttons[0].setOnMouseEntered(new EventHandler<Event>() {
			@Override
			public void handle(Event event)
			{
				System.out.println("You are pidor");
			}
		}*/
		//);
/*}
	
		
	
	
	
	
	
}*/
