package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

//import application.GameScene.Corner;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Algorithms {
	final static int[] boarderX = new int[484];
	final static int[] boarderY = new int[484];
	Stage recordStage = new Stage();
	Pane pane = new Pane();
	Scene scene = new Scene(pane, 300, 150);

	Saving saver = new Saving();

	// Snake variables
	static int lengthOfSnake = 3;

	int[] fruitXPos = { 20, 40, 60, 80, 100, 120, 140, 160, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420,
			440, 460 };
	int[] fruitYPos = { 20, 40, 60, 80, 100, 120, 140, 160, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420,
			440, 460 };

	int moves = 0;
	String nameRecord;
	static int totalScore = 0;
	static int flag = 0;
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
	static Random random = new Random();

	static int posX = random.nextInt(22);
	static int posY = random.nextInt(22);

	static boolean left = false;
	static boolean right = false;
	static boolean up = false;
	static boolean down = false;

	public Algorithms() {

	}

	static int bestScore = readBestScorefromTheFile();

	public void save() {
		try {
			FileWriter writer = new FileWriter("notes3.txt", false);

			writer.write(totalScore + " ");
			writer.write(bestScore + " ");
			// writer.write(lengthOfSnake + " ");

			writer.write(posX + " ");
			writer.write(posY + " ");

			for (int i = 0; i < lengthOfSnake; i++) {
				writer.write(boarderX[i] + " ");
				writer.write(boarderY[i] + " ");
			}
			int direction = 0;
			if (up == true)
				direction = 1;
			if (right == true)
				direction = 2;
			if (down == true)
				direction = 3;
			if (left == true)
				direction = 4;
			writer.write(direction + " ");
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getSaveInfo() {
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream("notes3.txt"), "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			int output = 0;
			String str = new String();
			int c;
			while ((c = br.read()) != -1) {
				str += (char) c;
			}

			br.close();
			// System.out.println(str);
			return str;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void writeBestScoreInTheFile(String nameRecord) {
		if (totalScore >= bestScore) {
			try {
				FileWriter writer = new FileWriter("./snake-game-best-score.txt", true);

				/*
				 * FileOutputStream fos = new FileOutputStream("./snake-game-best-score.txt");
				 * OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
				 */
				writer.write("\r\n");
				writer.write(nameRecord);
				writer.write("\r\n");
				writer.write(bestScore + "");
				writer.flush();
				writer.close();
			} catch (IOException e) {

			}
		}
	}

	private static int readBestScorefromTheFile() {
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream("./snake-game-best-score.txt"), "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			int output = 0;

			int c;
			for (int i = 0; i < 5; i++) {
				String str = "";
				while ((c = br.read()) != '\n') {

				}
				while ((c = br.read()) != '\n') {
					if (c == -1) {
						i = 5;
						break;
					}
					if (Character.isDigit(c)) {
						str += (char) c;
					}
				}
				if (str.equals("")) {
					str = "0";
				}
				output = Integer.parseInt(str);
			}
			br.close();
			return output;
		} catch (IOException e) {
		}
		return 0;
	}

	public static void parseSaveInformation(String str) {
		/* char[] strArray = new char[4]; */
		/*
		 * char[] strArray = str.toCharArray(); for (int i = 0; strArray[i] != ' '; i++)
		 * { }
		 */
		/*
		 * int i, j, k; for (i = 0; str.charAt(i) != ' '; i++) { } totalScore =
		 * Integer.parseInt(str.substring(0, i)); System.out.println(totalScore);
		 */
		String[] subStr;
		String delim = " ";
		subStr = str.split(delim);

		/*
		 * for (int i = 0; i < subStr.length; i++) { System.out.println(subStr[i]); }
		 */
		totalScore = Integer.parseInt(subStr[0]);
		bestScore = Integer.parseInt(subStr[1]);
		lengthOfSnake = 3 + totalScore;

		posX = Integer.parseInt(subStr[2]);
		posY = Integer.parseInt(subStr[3]);
		/* for (int j = 4; j < lengthOfSnake; j++) { */
		int j = 4;
		for (int k = 0; k < lengthOfSnake; k++) {
			boarderX[k] = Integer.parseInt(subStr[j]);
			j++;
			boarderY[k] = Integer.parseInt(subStr[j]);
			j++;
		}
		int direction = Integer.parseInt(subStr[j]);
		System.out.println(direction);
		switch (direction) {
		case 1:
			up = true;
		case 2:
			right = true;
		case 3:
			down = true;
		case 4:
			left = true;
		}
		/* } */
		// System.out.println(i);
		// System.out.println(str);
		/*
		 * for (j = i; str.charAt(j) != ' '; j++) { } bestScore =
		 * Integer.parseInt(str.substring(i, j)); System.out.println(bestScore);
		 */

		/*
		 * int j = i; for (j = i; str.charAt(j) != ' '; j++) { } bestScore =
		 * Integer.parseInt(str.substring(i, j)); System.out.println(totalScore);
		 * 
		 * 
		 * int k = j; for (k = j; str.charAt(k) != ' '; k++) { } posX =
		 * Integer.parseInt(str.substring(j, k));
		 * 
		 * // i = k; for (i = k; str.charAt(i) != ' '; i++) { } posY =
		 * Integer.parseInt(str.substring(k, i)); int p; for (int f = 0; f <
		 * lengthOfSnake; f++) {
		 * 
		 * for (p = i; str.charAt(p) != ' '; p++) { } boarderX[f] =
		 * Integer.parseInt(str.substring(i, p)); for (i = p; str.charAt(i) != ' '; i++)
		 * { } boarderY[f] = Integer.parseInt(str.substring(p, i)); }
		 */

		// totalScore = Integer.parseInt(strArray);
	}

	// public void func(int )

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
					// down = true;
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

	public void play(GraphicsContext gc,
			/* Stage menuStageScene sceneGame,Stage gameStage ,StackPane layout2, */Timeline timeline) {
		if (flag == 0) {
			if (moves == 0) {
				boarderX[2] = 40;
				boarderX[1] = 60;
				boarderX[0] = 80;

				boarderY[2] = 100;
				boarderY[1] = 100;
				boarderY[0] = 100;
				timeline.play();
			}
		} else {

			/*
			 * boarderX[2] = 40; boarderX[1] = 60; boarderX[0] = 80;
			 * 
			 * boarderY[2] = 100; boarderY[1] = 100; boarderY[0] = 100;
			 */
			// timeline.play();
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
		// Press space to pause game
		gc.setFill(Color.rgb(11, 54, 82));
		gc.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
		gc.fillText(" Press space ", 685, 300);
		gc.fillText("  to pause this game ", 650, 320);
		/*
		 * Button button = new Button();
		 * 
		 * button.setText("BACK"); button.
		 * setStyle("-fx-background-color:rgb(11, 54, 82);-fx-text-fill:rgb(0, 250, 154)"
		 * ); button.setTranslateX(290); button.setTranslateY(200);
		 * button.setMaxWidth(140); button.setMaxHeight(50);
		 */

//		button.setOnMouseClicked(click -> {
//			//timeline.stop();
//			gameStage.hide();
//		
//			menuStage.show();
//			
//			
//		});

		// layout2.getChildren().add(button);

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

				if (totalScore >= bestScore) {

					recordStage.setTitle("RECORD WINDOW");
					recordStage.setResizable(false);
					recordStage.show();
					/* pane.setAlignment(Pos.CENTER); */
					pane.setStyle("-fx-background-color: rgb(0, 250, 154);");
					recordStage.setScene(scene);
					Label label = new Label("NEW RECORD");
					label.setFont(new Font("Helvetica", 15));
					label.setTranslateX(100);
					label.setTextFill(Color.web("#0076a3"));
					pane.getChildren().add(label);

					TextField userTextField = new TextField();
					Pattern p = Pattern.compile("(\\d+\\.?\\d*)?");
					userTextField.textProperty().addListener((observable, oldValue, newValue) -> {
						if (newValue.length() >= 11)
							userTextField.setText(oldValue);
						// if (!p.matcher(newValue).matches()) userTextField.setText(oldValue);
					});
					pane.getChildren().add(userTextField);
					userTextField.setTranslateX(75);
					userTextField.setTranslateY(50);

					Button btn = new Button("OK");
					btn.setTranslateX(130);
					btn.setTranslateY(100);
					btn.setStyle("-fx-background-color:rgb(11, 54, 82);-fx-text-fill:rgb(0, 250, 154)");
					pane.getChildren().add(btn);
					btn.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							nameRecord = userTextField.getText();
							recordStage.close();
							writeBestScoreInTheFile(nameRecord);
						}

					});
				}

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
