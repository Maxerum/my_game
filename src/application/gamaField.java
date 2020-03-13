/*
 * package application;
 * 
 * import java.io.FileInputStream; import java.io.FileNotFoundException; import
 * java.util.ArrayList;
 * 
 * import javafx.scene.control.Button; import javafx.scene.control.Label; import
 * javafx.scene.image.Image; import javafx.scene.image.ImageView; import
 * javafx.scene.layout.Background; import javafx.scene.layout.BackgroundFill;
 * import javafx.scene.layout.Border; import javafx.scene.layout.BorderStroke;
 * import javafx.scene.layout.BorderStrokeStyle; import
 * javafx.scene.layout.BorderWidths; import javafx.scene.layout.HBox; import
 * javafx.scene.layout.Pane; import javafx.scene.paint.Color; import
 * javafx.scene.shape.Rectangle; import javafx.scene.text.Font; import
 * javafx.scene.text.FontWeight; import javafx.scene.text.Text;
 * 
 * public class gamaField extends Pane { private int w, h;
 * 
 * Rectangle tryField;
 * 
 * ArrayList<snake_block> blocks = new ArrayList<snake_block>();
 * 
 * Snake mySnake;
 * 
 * Rectangle back, back2; Label score = new Label(); Button button;
 * 
 * HBox myBox = new HBox(); private final String path =
 * "-fx-background-color:transparent;-fx-background-image: url('/src/application/my_button.png');"
 * ;
 * 
 * 
 * 
 * public gamaField(int width, int height) { w = width; h = height; button = new
 * Button();
 * 
 * back = new Rectangle(70, 70, Color.BLUE); back2 = new Rectangle(70, 70,
 * Color.BLUE);
 * 
 * back.setStyle(" -fx-background-color: #1d1d1d;"); // Field for snake tryField
 * tryField = new Rectangle(800, 500, Color.rgb(0, 250, 154));
 * tryField.setTranslateX(50); tryField.setTranslateY(75);
 * score.setText("Score"); // Yeah, you're monster . score.setTranslateX(300);
 * score.setTranslateY(-25); score.setFont(new Font("Helvetica", 65));
 * score.setTextFill(Color.web("#0076a3"));
 * 
 * // How to create field setMinSize(w * Main.block_size, h * Main.block_size );
 * this.getChildren().addAll();
 * 
 * myBox.getChildren().add(score); myBox.getChildren().add(back);
 * myBox.getChildren().add(back2);
 * 
 * this.getChildren().add(tryField);
 * 
 * this.getChildren().add(back); this.getChildren().add(back2);
 * this.getChildren().add(score);
 * 
 * back.setArcHeight(40); back.setArcWidth(40); back2.setArcHeight(40);
 * back2.setArcWidth(40); back2.setTranslateX(100); back2.setTranslateY(0);
 * 
 * 
 * 
 * tryField.setArcHeight(40); tryField.setArcWidth(40);
 * 
 * 
 * try { button.setFont(Font.loadFont(new FileInputStream(path), 23)); } catch
 * (FileNotFoundException e) { e.printStackTrace(); }
 * 
 * 
 * 
 * button.setPrefHeight(260); button.setPrefWidth(260); button.setStyle(path);
 * this.getChildren().add(button);
 * 
 * 
 * // setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
 * // setBorder(new Border(new
 * 
 * BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(5))));
 * 
 * }
 * 
 * 
 * public void addSnake(Snake s) { mySnake = s; for (snake_block b : s.blocks) {
 * this.addBlock(b); } }
 * 
 * private void addBlock(snake_block b) { getChildren().add(b); blocks.add(b); }
 * 
 * public int getW() { return w; }
 * 
 * public int getH() { return h; }
 * 
 * }
 */
