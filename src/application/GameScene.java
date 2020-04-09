package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameScene extends Application {
	private StackPane layout2;
	private Scene sceneGame;
	private Stage gameStage;
	private Stage menuStage;
	public double speed ;


	Timeline timeline = new Timeline();
	Background back = new Background();
	Algorithms algo = new Algorithms();


	public GameScene() {

	}

	@Override
	public void start(Stage menuStage) {

		this.menuStage = menuStage;
		this.menuStage.hide();
		layout2 = new StackPane();
		sceneGame = new Scene(layout2, 900, 600, Color.TRANSPARENT);
		gameStage = new Stage();

		gameStage.setScene(sceneGame);
		gameStage.show();

		Canvas canvas = new Canvas(900, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		layout2.setStyle("-fx-background-color: rgb(0, 250, 154);");
		layout2.getChildren().add(canvas);


		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(speed), (ActionEvent event) -> {
			// back.createBackground(gc);/* drawShapes(gc);
			algo.play(gc, sceneGame, timeline);

		}));

		timeline.setCycleCount(Timeline.INDEFINITE);

		timeline.play();

		algo.reload(sceneGame, timeline);

	}

}
