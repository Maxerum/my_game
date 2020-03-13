

package application;
	
public class snake_block {
 
    int x, y;

    public snake_block(int x, int y) {
        this.x = x;
        this.y = y;
    }
 
}



/*
 * package application;
 * 
 * import javafx.scene.shape.Rectangle; import javafx.scene.paint.Color; import
 * javafx.scene.text.Text;
 * 
 * public class snake_block extends Rectangle { static final int UP=0, RIGHT =
 * 1, DOWN = 2, LEFT = 3; int posX, posY, oldPosX, oldPosY;
 * 
 * snake_block previous;
 * 
 * int direction = LEFT;
 * 
 * public snake_block(int x,int y,snake_block p) {
 * super(Main.block_size,Main.block_size); posX = x; posY = y;
 * setTranslateX(posX * Main.block_size); setTranslateY(posY * Main.block_size);
 * 
 * }
 * 
 * public void update(){ oldPosX =posX; oldPosY = posY;
 * 
 * if(previous == null) { switch(direction) { case RIGHT: moveRIGHT();break;
 * case UP: moveUp();break; case DOWN: moveDown();break; case LEFT:
 * moveLeft();break; } } }
 * 
 * public void moveLeft() { posX--;
 * 
 * }
 * 
 * private void moveDown() { posY--;
 * 
 * }
 * 
 * private void moveUp() { posY++;
 * 
 * }
 * 
 * private void moveRIGHT() { posX++;
 * 
 * }
 * 
 * 
 * }
 */
 
