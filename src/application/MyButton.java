package application;

import javafx.scene.control.Button;

public class MyButton extends Button {
	Button button;
	
	MyButton(String text){
		button.setText(text);
		button.setStyle("-fx-background-color:rgb(11, 54, 82);-fx-text-fill:rgb(0, 250, 154)");
		button.setMaxWidth(140);
		button.setMaxHeight(50);
	}
	
	
}
