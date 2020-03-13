package application;
	
import java.util.LinkedList;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
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
import javafx.util.Duration;
import javafx.scene.control.Button;

public class Main extends Application {
	final int[] boardX = new int[484];
    final int[] boardY = new int[484];
	
	
	static int block_size = 10;
	int width = 30, height = 10;
	
	int scoreReverseCounter = 99;
	int bestScore = 99;
	
	int il = 5;
	
	int lengthOfSnake = 3;
	
	LinkedList<snake_block> snake = new LinkedList();
	
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        
        
        Image image = new Image(getClass().getResourceAsStream("black_mamb.png"));
        ImageView img = new ImageView(image);
        img.setFitHeight(600);
        img.setFitWidth(900);
        root.getChildren().add(img);
        
        Image snakeBodyImage = new Image(getClass().getResourceAsStream("body2.png"));

        Canvas canvas = new Canvas(900, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();;
        Label label = new Label("SNAKE");
        label.setFont(new Font("Helvetica", 100));
        label.setTranslateX(310);
        label.setTextFill(Color.web("#0076a3"));
        root.getChildren().add(label);
        
        MenuItem newGame = new MenuItem("NEW GAME");
        MenuItem options = new MenuItem("SETTINGS");
        MenuItem exitGame = new MenuItem("EXIT");
        
        SubMenu mainMenu = new SubMenu(
                newGame,options,exitGame
        );
        
        MenuItem other = new MenuItem("OTHER");
        MenuItem records = new MenuItem("RECORDS");
        MenuItem information = new MenuItem("INFORMATION");
        MenuItem optionsBack = new MenuItem("BACK");
        SubMenu optionsMenu = new SubMenu(
        		other,records,information,optionsBack
        );
    
        MenuBox menuBox = new MenuBox(mainMenu);
        Scene scene = new Scene(root,900,600,Color.TRANSPARENT);
        
        Button button = new Button();
       
        
        StackPane layout2 = new StackPane();
        Scene sceneGame= new Scene(layout2,900,600);
        layout2.setStyle("-fx-background-color: rgb(0, 250, 154);");
        layout2.getChildren().add(canvas);
        
        gc.setFill(Color.rgb(0, 250, 154));
        //draw rectangle
        gc.fillRect(0, 0, 900, 500);
        
        gc.setFill(Color.rgb(11, 54, 82));
        for (int i = 6; i <= 582; i += 17) {
            for (int j = 6; j <= 600; j += 17) {
                gc.fillRect(i, j, 13, 13);
            }
        }
        
        gc.setFill(Color.rgb(0, 250, 154));
        gc.fillRect(20, 20, 545, 562);
        
        gc.setFill(Color.rgb(11, 54, 82));
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gc.fillText(" SNAKE ", 660, 35);
        
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
		/* gc.fillText("+ " + scoreReverseCounter, 510, 222); */
        
        gc.setFill(Color.rgb(11, 54, 82));
        
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
		
        
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        
        gc.fillText("Best Score", 696, 110);
        gc.fillRect(670, 120, 140, 30);
        gc.setFill(Color.rgb(11, 54, 82));
		gc.fillRect(671, 121, 138, 28); 
        gc.setFill(Color.rgb(11, 54, 82));
		/*
		 * gc.fillText(bestScore + "", 550 + (142 - new Text(bestScore +
		 * "").getLayoutBounds().getWidth()) / 2, 142);
		 */
        
        
        
        gc.fillText("Total Score", 696, 190);
        gc.fillRect(670, 200, 140, 30);
        gc.setFill(Color.rgb(11, 54, 82));
        gc.fillRect(671, 201, 138, 28);
        gc.setFill(Color.rgb(11, 54, 82));
		/*
		 * gc.fillText(totalScore + "", 550 + (142 - new Text(totalScore +
		 * "").getLayoutBounds().getWidth()) / 2, 222);
		 */
        gc.fillText("Fruit Eaten",696, 270);
        gc.fillRect(670, 280, 140, 30);
        gc.setFill(Color.rgb(11, 54, 82));
        gc.fillRect(671, 281, 138, 28);
        gc.setFill(Color.rgb(11, 54, 82));
        
        gc.fillText("   Speed", 696, 350);
        gc.fillRect(670, 360, 140, 30);
        gc.setFill(Color.rgb(11, 54, 82));
        gc.fillRect(671, 361, 138, 28);
        gc.setFill(Color.rgb(11, 54, 82));
        
        newGame.setOnMouseClicked(event->primaryStage.setScene(sceneGame));
        
        options.setOnMouseClicked(event->menuBox.setSubMenu(optionsMenu));
        exitGame.setOnMouseClicked(event-> System.exit(0));
        optionsBack.setOnMouseClicked(event->menuBox.setSubMenu(mainMenu));
		
        button.setText("BACK");
        button.setStyle("-fx-background-color:rgb(11, 54, 82);-fx-text-fill:rgb(0, 250, 154)");
        button.setTranslateX(290);
        button.setTranslateY(200);
        button.setMaxWidth(140);
        button.setMaxHeight(50);
        
        
        layout2.getChildren().add(button);
        button.setOnMouseClicked(event->primaryStage.setScene(scene));
        
        snake.clear();
        
        for (int i = 0; i < lengthOfSnake; i++) {
        	 gc.drawImage(snakeBodyImage, boardX[i], boardY[i]);
            snake.add(new snake_block(boardX[i], boardY[i]));
        }


        //NG4.setOnMouseClicked(event-> menuBox.setSubMenu(mainMenu));
        root.getChildren().addAll(menuBox);
        FadeTransition ft = new FadeTransition(Duration.seconds(1),menuBox);
        ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    menuBox.setVisible(true);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        
		/* primaryStage.initStyle(); */ 
        primaryStage.show();
        
       
   }
    private static class MenuItem extends StackPane{
        public  MenuItem(String name){
            Rectangle bg = new Rectangle(200,40,Color.BLUE);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Arial",FontWeight.BOLD,14));

            getChildren().addAll(bg,text);

        }
    }
    
    
    private static class MenuBox extends Pane{
        static SubMenu subMenu;
        public MenuBox(SubMenu subMenu){
            MenuBox.subMenu = subMenu;

            setVisible(false);
            getChildren().addAll(subMenu);
        }
        public void setSubMenu(SubMenu subMenu){
            getChildren().remove(MenuBox.subMenu);
            MenuBox.subMenu = subMenu;
            getChildren().add(MenuBox.subMenu);
        }
    }

   private static class SubMenu extends VBox{
        public SubMenu(MenuItem...items){
            setSpacing(15);
            setTranslateY(150);
            setTranslateX(100);
            for(MenuItem item : items){
                getChildren().addAll(item);
            }
        }
    }

	
	public static void main(String[] args) {
		launch(args);
	}
}
