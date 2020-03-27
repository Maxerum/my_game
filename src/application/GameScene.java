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
	// private GraphicsContext gc;

	final int[] boarderX = new int[484];
	final int[] boarderY = new int[484];

	/*
	 * final int[] boarderX = new int[582]; final int[] boarderY = new int[582];
	 */

	// Snake variables
	int lengthOfSnake = 3;

	int[] fruitXPos = { 20, 40, 60, 80, 100, 120, 140, 160, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420,
			440, 460 };
	int[] fruitYPos = { 20, 40, 60, 80, 100, 120, 140, 160, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420,
			440, 460 };

	int moves = 0;

	int totalScore = 0;
	int fruitEaten = 0;
	int bestScore = 1;
	int speed = 1;

	Timeline timeline = new Timeline();

	static List<Corner> snake = new ArrayList<>();

	Image snakeBodyImage = new Image(getClass().getResourceAsStream("body2.png"));
	Image fruitImage = new Image(getClass().getResourceAsStream("my_fruit.png"));

	boolean gameOver = false;
	Random random = new Random();

	int posX = random.nextInt(7);
	int posY = 1 + random.nextInt(6);

	boolean left = false;
	boolean right = false;
	boolean up = false;
	boolean down = false;

	public static class Corner {
		int x;
		int y;

		public Corner(int x, int y) {
			this.x = x;
			this.y = y;

		}
	}

	public GameScene() {

		/* createKeyListeners(); */
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

		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.1), (ActionEvent event) -> {
			createBackground(gc);/* drawShapes(gc); */
		}));

		timeline.setCycleCount(Timeline.INDEFINITE);

		timeline.play();

		// computerPlayer();

		sceneGame.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {

			if (null != e.getCode()) {
				switch (e.getCode()) {

				case SPACE:

					if (timeline.getStatus() == Timeline.Status.RUNNING && gameOver == false) {
						timeline.stop();
					}

					else if (timeline.getStatus() != Timeline.Status.RUNNING && gameOver == false) {
						timeline.play();
					}

					else if (timeline.getStatus() != Timeline.Status.RUNNING && gameOver == true) {
						gameOver = false;
						timeline.play();
						moves = 0;
						totalScore = 0;
						/*
						 * totalScore = 0; fruitEaten = 0;
						 */
						lengthOfSnake = 3;
						right = true;
						left = false;
						posX = random.nextInt(7);
						posY = 1 + random.nextInt(6);
					}
					break;

				case RIGHT:

					moves++;
					right = true;
					if (!left) {
						right = true;
					} else {
						right = false;
						left = true;
					}
					up = false;
					down = false;
					break;

				case LEFT:
					moves++;
					left = true;
					if (!right) {
						left = true;
					} else {
						left = false;
						right = true;
					}
					up = false;
					down = false;
					break;

				case UP:

					moves++;
					up = true;
					if (!down) {
						up = true;
					} else {
						up = false;
						down = true;
					}
					left = false;
					right = false;
					break;

				case DOWN:

					moves++;
					down = true;
					if (!up) {
						down = true;
					} else {
						up = true;
						down = false;
					}
					left = false;
					right = false;
					break;
				}
			}
		});
	}

	public void createBackground(GraphicsContext gc) {

		// gc = canvas.getGraphicsContext2D();

		if (moves == 0) {
			boarderX[2] = 40;
			boarderX[1] = 60;
			boarderX[0] = 80;

			boarderY[2] = 100;
			boarderY[1] = 100;
			boarderY[0] = 100;
			timeline.play();
		}

		if (totalScore > bestScore) {
			bestScore = totalScore;
		}

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
		gc.fillText(bestScore + "", 670 + (142 - new Text(bestScore + "").getLayoutBounds().getWidth()) / 2, 142);

		gc.setFill(Color.rgb(11, 54, 82));
		gc.fillText("Total Score", 696, 190);
		gc.fillRect(670, 200, 140, 30);
		gc.setFill(Color.rgb(11, 54, 82));
		gc.fillRect(671, 201, 138, 28);
		gc.setFill(Color.rgb(0, 250, 154));
		gc.fillText(totalScore + "", 670 + (142 - new Text(totalScore + "").getLayoutBounds().getWidth()) / 2, 222);

		gc.setFill(Color.rgb(11, 54, 82));
		gc.fillText("    Fruits ", 696, 270);
		gc.fillRect(670, 280, 140, 30);
		gc.setFill(Color.rgb(11, 54, 82));
		gc.fillRect(671, 281, 138, 28);
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

		button.setOnMouseClicked(click -> {
			this.gameStage.hide();
			this.menuStage.show();
		});
		layout2.getChildren().add(button);

		gc.drawImage(snakeBodyImage, boarderX[0], boarderY[0]);
		snake.clear();
		// Drawing snake
		for (int i = 0; i < lengthOfSnake; i++) {

			gc.drawImage(snakeBodyImage, boarderX[i], boarderY[i]);
			snake.add(new Corner(boarderX[i], boarderY[i]));
		}

		// Snake's smashing
		for (int i = 1; i < lengthOfSnake; i++) {

			if (boarderX[i] == boarderX[0] && boarderY[i] == boarderY[0]) {

				gc.drawImage(snakeBodyImage, boarderX[1], boarderY[1]);

				gameOver = true;

				timeline.stop();

				// Game Over
				gc.setFill(Color.RED);
				gc.setFont(Font.font("Arial", FontWeight.BOLD, 50));
				gc.fillText("YOU LOSE", 150, 240);

				// Press Space To Restart
				gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));
				gc.fillText("Press Space To Restart", 175, 280);
			}
		}

		if ((fruitXPos[posX] == boarderX[0]) && fruitYPos[posY] == boarderY[0]) {
			totalScore++;
			lengthOfSnake++;
		}

		for (int i = 0; i < snake.size(); i++) {
			if (snake.get(i).x == fruitXPos[posX] && snake.get(i).y == fruitYPos[posY]) {
				posX = random.nextInt(7);
				posY = random.nextInt(7);
			}
		}

		gc.drawImage(fruitImage, fruitXPos[posX], fruitYPos[posY]);

		if (right) {
			for (int i = lengthOfSnake - 1; i >= 0; i--)
				boarderY[i + 1] = boarderY[i];

			for (int i = lengthOfSnake; i >= 0; i--) {
				if (i == 0)
					boarderX[i] = boarderX[i] + 20;
				else
					boarderX[i] = boarderX[i - 1];

				if (boarderX[i] > 560)
					boarderX[i] = 20;
			}
		} else if (left) {
			for (int i = lengthOfSnake - 1; i >= 0; i--)
				boarderY[i + 1] = boarderY[i];

			for (int i = lengthOfSnake; i >= 0; i--) {
				if (i == 0)
					boarderX[i] = boarderX[i] - 20;

				else
					boarderX[i] = boarderX[i - 1];

				if (boarderX[i] < 20)
					boarderX[i] = 560;
			}
		} else if (up) {
			for (int i = lengthOfSnake - 1; i >= 0; i--)
				boarderX[i + 1] = boarderX[i];

			for (int i = lengthOfSnake; i >= 0; i--) {
				if (i == 0)
					boarderY[i] = boarderY[i] - 20;
				else
					boarderY[i] = boarderY[i - 1];

				if (boarderY[i] < 20)
					boarderY[i] = 560;
			}
		} else if (down) {
			for (int i = lengthOfSnake - 1; i >= 0; i--)
				boarderX[i + 1] = boarderX[i];

			for (int i = lengthOfSnake; i >= 0; i--) {
				if (i == 0)
					boarderY[i] = boarderY[i] + 20;
				else
					boarderY[i] = boarderY[i - 1];

				if (boarderY[i] > 560)
					boarderY[i] = 20;
			}
		}

	}

}
