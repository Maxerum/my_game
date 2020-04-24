package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/*import application.MenuItem.MenuBox;
import application.MenuItem.SubMenu;*/
/*import application.Main.MenuBox;
import application.Main.MenuItem;
import application.Main.SubMenu;*/
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Menu {

	private Pane mainPane;
	private Scene menuScene;
	private Stage mainStage;

	String firstRecord;
	String secondRecord;
	String thirdRecord;

	public Menu() {
		mainPane = new Pane();
		menuScene = new Scene(mainPane, 900, 600, Color.TRANSPARENT);
		mainStage = new Stage();
		mainStage.setScene(menuScene);
		mainStage.setResizable(false);
		setMenuBackground();
		createMenu();
	}

	Stage getMainStage() {
		return mainStage;
	}

	public void getNameOfRecord(ObservableList<Person> persons) {
		try {

			InputStreamReader isr = new InputStreamReader(new FileInputStream("./snake-game-best-score.txt"), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			for (int i = 0; i < 5; i++) {
				Person f = new Person("", 0);
				String names = new String();
				int c;
				while ((c = br.read()) != '\n') {
					names += (char) c;
				}

				String record = new String();

				while ((c = br.read()) != '\n') {

					if (c == -1) {
						i = 5;
						break;
					}

					if (Character.isDigit(c)) {
						record += (char) c;
					}
				}
				
				f.setName(names);
				f.setAge(Integer.parseInt(record));
				persons.add(f);
			}

		} catch (IOException e) {

		}
	}

	private void setMenuBackground() {
		Image image = new Image(getClass().getResourceAsStream("black_mamb.png"));
		ImageView img = new ImageView(image);
		img.setFitHeight(600);
		img.setFitWidth(900);
		mainPane.getChildren().add(img);
	}

	private void createMenu() {
		Label label = new Label("SNAKE");
		label.setFont(new Font("Helvetica", 100));
		label.setTranslateX(310);
		label.setTextFill(Color.web("#0076a3"));
		mainPane.getChildren().add(label);

		MenuItem newGame = new MenuItem("NEW GAME");
		MenuItem options = new MenuItem("SETTINGS");
		MenuItem exitGame = new MenuItem("EXIT");
		MenuItem other = new MenuItem("OTHER");
		MenuItem records = new MenuItem("RECORDS");
		MenuItem information = new MenuItem("INFORMATION");
		MenuItem optionsBack = new MenuItem("BACK");

		MenuItem hardMode = new MenuItem("HARD");
		MenuItem easyMode = new MenuItem("EASY");
		MenuItem mediumMode = new MenuItem("MEDIUM");
		MenuItem resumeLastGame = new MenuItem("Load last game");
		MenuItem modesBack = new MenuItem("BACK");

		// MenuItem recordFirst = new MenuItem(getNameOfRecord()/* "Top 1" */);
		MenuItem recordsBack = new MenuItem("Back");

		SubMenu optionsMenu = new SubMenu(other, information, optionsBack);
		SubMenu modeList = new SubMenu(easyMode, mediumMode, hardMode, resumeLastGame, modesBack);
		SubMenu mainMenu = new SubMenu(newGame, options, records, exitGame);
		/*
		 * SubMenu recordList = new SubMenu( recordFirst,recordsBack , recordSecond,
		 * recordThird, );
		 */
		MenuBox menuBox = new MenuBox(mainMenu);

		newGame.setOnMouseClicked(event -> menuBox.setSubMenu(modeList));

		easyMode.setOnMouseClicked(click -> {
			GameScene gameScene = new GameScene();
			gameScene.speed = 0.1;
			gameScene.start(mainStage);
		});

		mediumMode.setOnMouseClicked(click -> {
			GameScene gameScene = new GameScene();
			gameScene.speed = 0.05;
			gameScene.start(mainStage);
		});

		hardMode.setOnMouseClicked(click -> {
			// speed = 0.01;
			GameScene gameScene = new GameScene();
			gameScene.speed = 0.01;
			gameScene.start(mainStage);
		});
		/*
		 * newGame.setOnMouseClicked(click -> { GameScene gameScene = new GameScene();
		 * gameScene.start(mainStage); });
		 */

		options.setOnMouseClicked(event -> menuBox.setSubMenu(optionsMenu));

		records.setOnMouseClicked(event -> {
			/* Person f = new Person(); */

			ObservableList<Person> people = FXCollections.observableArrayList();
			getNameOfRecord(people);
			TableView<Person> table = new TableView<Person>(people);
			TableColumn<Person, String> nameColumn = new TableColumn<Person, String>("Name");
			TableColumn<Person, Integer> ageColumn = new TableColumn<Person, Integer>("Record");
			nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
			// nameColumn.setCellValueFactory(getName());
			table.setTranslateX(350);
			table.setTranslateY(150);
			table.setPrefWidth(250);
			table.setPrefHeight(205);
			ageColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
			/*
			 * TableColumn sortcolumn = null; SortType st = null; if
			 * (table.getSortOrder().size()>0) { sortcolumn = (TableColumn)
			 * table.getSortOrder().get(0); st = sortcolumn.getSortType(); }
			 */
			
			table.getColumns().add(nameColumn);
			table.getColumns().add(ageColumn);
			
			mainPane.getChildren().add(table);
			table.getSortOrder().add(ageColumn);
		});

		/* records.setOnMouseClicked(event -> menuBox.setSubMenu(recordList)); */
		recordsBack.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
		modesBack.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
		exitGame.setOnMouseClicked(event -> System.exit(0));
		optionsBack.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
		menuBox.setVisible(true);
		mainPane.getChildren().addAll(menuBox);

		// mainPane.getChildren().add(menuBox);
	}
}
