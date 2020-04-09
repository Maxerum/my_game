package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Background {
	/* private Canvas canvas; */
	// private GraphicsContext gc;
	
	
	public Background() {
	}

	public void createBackground(GraphicsContext gc) {
		
		
		
		gc.setFill(Color.rgb(0, 250, 154));
		// draw rectangle
		gc.fillRect(0, 0, 900, 500);

		gc.setFill(Color.rgb(11, 54, 82));
		for (int i = 6; i <= 600; i += 17) {
			for (int j = 6; j <= 600; j += 17) {
				gc.fillRect(i, j, 13, 13);
			}
		}

		gc.setFill(Color.rgb(0, 250, 154));
		gc.fillRect(20, 20, 562, 562);

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
		gc.setFill(Color.rgb(0, 250, 154));
		//gc.fillText(bestScore + "", 670 + (142 - new Text(bestScore + "").getLayoutBounds().getWidth()) / 2, 142);

		gc.setFill(Color.rgb(11, 54, 82));
		gc.fillText("Total Score", 696, 190);
		gc.fillRect(670, 200, 140, 30);
		gc.setFill(Color.rgb(11, 54, 82));
		gc.fillRect(671, 201, 138, 28);
		gc.setFill(Color.rgb(0, 250, 154));
		//gc.fillText(totalScore + "", 670 + (142 - new Text(totalScore + "").getLayoutBounds().getWidth()) / 2, 222);

		gc.setFill(Color.rgb(11, 54, 82));
		gc.fillText("    Fruits ", 696, 270);
		gc.fillRect(670, 280, 140, 30);
		gc.setFill(Color.rgb(11, 54, 82));
		gc.fillRect(671, 281, 138, 28);
		// gc.setFill(Color.rgb(11, 54, 82));
		gc.setFill(Color.rgb(0, 250, 154));
		//gc.fillText(fruitEaten + "", 670 + (142 - new Text(fruitEaten + "").getLayoutBounds().getWidth()) / 2, 222);

		gc.setFill(Color.rgb(11, 54, 82));
		gc.fillText("   Speed", 696, 350);
		gc.fillRect(670, 360, 140, 30);
		gc.setFill(Color.rgb(11, 54, 82));
		gc.setFill(Color.rgb(0, 250, 154));
		// gc.fillText(speed + "", 670 + (142 - new Text(totalScore +
		// "").getLayoutBounds().getWidth()) / 2, 222);

		Button button = new Button();

		button.setText("BACK");
		button.setStyle("-fx-background-color:rgb(11, 54, 82);-fx-text-fill:rgb(0, 250, 154)");
		button.setTranslateX(290);
		button.setTranslateY(200);
		button.setMaxWidth(140);
		button.setMaxHeight(50);

		/*
		 * button.setOnMouseClicked(click -> { this.gameStage.hide();
		 * this.menuStage.show(); });
		 */
		
		
	}
}

