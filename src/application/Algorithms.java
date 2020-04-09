package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import application.GameScene.Corner;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Algorithms {
	final int[] boarderX = new int[484];
	final int[] boarderY = new int[484];

	// Snake variables
	int lengthOfSnake = 3;

	int[] fruitXPos = { 20, 40, 60, 80, 100, 120, 140, 160, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420,
			440, 460 };
	int[] fruitYPos = { 20, 40, 60, 80, 100, 120, 140, 160, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420,
			440, 460 };

	int moves = 0;

	int totalScore = 0;
	int fruitEaten = 0;
	// int bestScore = 1;
	int speed = 1;

	public class Corner {
		int x;
		int y;

		public Corner(int x, int y) {
			this.x = x;
			this.y = y;

		}
	}

	List<Corner> snake = new ArrayList<>();

	Image snakeBodyImage = new Image(getClass().getResourceAsStream("body2.png"));
	Image fruitImage = new Image(getClass().getResourceAsStream("my_fruit.png"));

	boolean gameOver = false;
	Random random = new Random();

	int posX = random.nextInt(22);
	int posY = random.nextInt(22);

	boolean left = false;
	boolean right = false;
	boolean up = false;
	boolean down = false;

	public Algorithms() {

	}

	int bestScore = readBestScorefromTheFile();

	private void writeBestScoreInTheFile() {
		if (totalScore >= bestScore) {
			try {
				FileOutputStream fos = new FileOutputStream("./snake-game-best-score.txt");
				OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
				osw.write(bestScore + "");
				osw.flush();
				osw.close();
			} catch (IOException e) {
			}
		}
	}

	private int readBestScorefromTheFile() {
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream("./snake-game-best-score.txt"), "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			String str = "";
			int c;
			while ((c = br.read()) != -1) {
				if (Character.isDigit(c)) {
					str += (char) c;
				}
			}
			if (str.equals("")) {
				str = "0";
			}

			br.close();
			return Integer.parseInt(str);
		} catch (IOException e) {
		}
		return 0;
	}

	public void reload(Scene sceneGame, Timeline timeline) {
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
						// Нужно вызвать класс с алгоритмом для змеи
						gameOver = false;
						timeline.play();
						moves = 0;
						totalScore = 0;
						fruitEaten = 0;
						/*
						 * totalScore = 0;
						 */
						lengthOfSnake = 3;
						right = true;
						left = false;
						posX = random.nextInt(22);
						posY = 5 + random.nextInt(17);
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

	public void play(GraphicsContext gc, Scene sceneGame, Timeline timeline) {
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

		/*
		 * button.setOnMouseClicked(click -> { this.gameStage.hide();
		 * this.menuStage.show(); }); layout2.getChildren().add(button);
		 */

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

				writeBestScoreInTheFile();
			}
		}

		if ((fruitXPos[posX] == boarderX[0]) && fruitYPos[posY] == boarderY[0]) {
			totalScore++;
			lengthOfSnake++;
		}

		for (int i = 0; i < snake.size(); i++) {
			if (snake.get(i).x == fruitXPos[posX] && snake.get(i).y == fruitYPos[posY]) {
				posX = random.nextInt(22);
				posY = random.nextInt(22);
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
