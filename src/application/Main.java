package application;
	
/*import javafx.application.Application;
import javafx.stage.Stage;
import view.view_controll;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;*/

/*package tutorialfx.tutorial20;*/

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
	
	static int block_size=10;
	int width = 20, height = 10;
	
	
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Image image = new Image(getClass().getResourceAsStream("black_mamb.png"));
        ImageView img = new ImageView(image);
        img.setFitHeight(600);
        img.setFitWidth(900);
        root.getChildren().add(img);

        
        
        Label label = new Label("SNAKE");
        label.setFont(new Font("ItalicC", 100));
        label.setTranslateX(310);
        label.setTextFill(Color.web("#0076a3"));
        root.getChildren().add(label);
        
        MenuItem newGame = new MenuItem("NEW GAME");
        MenuItem options = new MenuItem("SETTINGS");
        MenuItem exitGame = new MenuItem("EXIT");
        
        SubMenu mainMenu = new SubMenu(
                newGame,options,exitGame
        );
        
        MenuItem sound = new MenuItem("KILLERS");
        MenuItem video = new MenuItem("RECORDS");
        MenuItem keys = new MenuItem("INFORMATION");
        MenuItem optionsBack = new MenuItem("BACK");
        SubMenu optionsMenu = new SubMenu(
                sound,video,keys,optionsBack
        );
    
        MenuBox menuBox = new MenuBox(mainMenu);
        Scene scene = new Scene(root,900,600);
        
        
        StackPane layout2 = new StackPane();
        Scene sceneGame= new Scene(layout2,900,600);
        newGame.setOnMouseClicked(event->primaryStage.setScene(sceneGame));
        
        
        gamaField f= new gamaField(width,height) ;
        layout2.getChildren().add(f);
        Image backImage = new Image(getClass().getResourceAsStream("green_baby.png"));
        ImageView backImg = new ImageView(backImage);
        img.setFitHeight(600);
        img.setFitWidth(900);
        layout2.getChildren().add(backImg);
       // Button button = new Button();
        //button.setText("Back");
       // layout2.getChildren().add(button);
        //snake_block block= new snake_block(); 
       //
        //layout2.getChildren().add(event->primaryStage)
        
        //button.setOnMouseClicked(event->primaryStage.setScene(scene));
        
        options.setOnMouseClicked(event->menuBox.setSubMenu(optionsMenu));
        exitGame.setOnMouseClicked(event-> System.exit(0));
        optionsBack.setOnMouseClicked(event->menuBox.setSubMenu(mainMenu));
        
        //NG4.setOnMouseClicked(event-> menuBox.setSubMenu(mainMenu));
        root.getChildren().addAll(menuBox);

        
        /*scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
               
                if (!menuBox.isVisible()) {
                  
                }
                else{
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt ->  menuBox.setVisible(false));
                    ft.play();

                }
            }
        }); */
        FadeTransition ft = new FadeTransition(Duration.seconds(1),menuBox);
        ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    menuBox.setVisible(true);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Pause");
        primaryStage.setScene(scene);
        primaryStage.show();
        
       
   }
    private static class MenuItem extends StackPane{
        public  MenuItem(String name){
            Rectangle bg = new Rectangle(200,40,Color.BLUE);
           // bg.setOpacity(0.5);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Arial",FontWeight.BOLD,14));

            //setAlignment(Pos.CENTER);
            getChildren().addAll(bg,text);
            //FillTransition st = new FillTransition(Duration.seconds(0.5),bg);
            /*setOnMouseEntered(event -> {
                st.setFromValue(Color.DARKGRAY);
                st.setToValue(Color.DARKGOLDENROD);
                //st.setCycleCount(Animation.INDEFINITE);
                st.setAutoReverse(true);
                st.play();
            });*/
            /*setOnMouseExited(event -> {
                st.stop();
                bg.setFill(Color.BLUE);
            });*/
        }
    }
    
    
    private static class MenuBox extends Pane{
        static SubMenu subMenu;
        public MenuBox(SubMenu subMenu){
            MenuBox.subMenu = subMenu;

            setVisible(false);
            //Rectangle bg = new Rectangle(900,600,Color.LIGHTBLUE);
           // bg.setOpacity(0.4);
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
}
/*
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			view_controll manager= new view_controll();
			manager.createMenuButtons();
			primaryStage=manager.getMainStage();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}*/
