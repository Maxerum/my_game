package application;



import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuItem extends StackPane{
	public MenuItem(String name) {
			Rectangle bg = new Rectangle(200, 40, Color.BLUE);

			Text text = new Text(name);
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Arial", FontWeight.BOLD, 14));

			getChildren().addAll(bg, text);
	}
}

