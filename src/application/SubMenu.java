package application;

import javafx.scene.layout.VBox;

public class SubMenu extends VBox{
		
		public SubMenu(MenuItem... items) {
			setSpacing(15);
			setTranslateY(150);
			setTranslateX(100);
			for (MenuItem item : items) {
				getChildren().addAll(item);
			}
		}

}
