/** 
 * file name: FruitLoop.java 
 * Author:   Guanyi Lu 
 * Date:     11/15/2018 
 * Sources of help: Piazza, Tutor, Textbook     
 */

import objectdraw.*;
import java.awt.Color;

/**
 * create the fruitLoops show in the canvas
 *
 */

public class FruitLoop {

  private FilledOval loopA;
  private FilledOval loopB;
  private GridCell gridCell;
  private int dx = 10;
  private int dy = 15;

/**
 * creating a fruit loop in a random gridCell with the color
 *
 * and it is in the canvas
 *
 * @param GridCell gridCell is the gridCell that fruit is in
 * @param Color color is the color of the fruit loop
 * @param DrawingCanvas canvas is the canvas that contain the fruit loop
 */

  public FruitLoop ( GridCell gridCell, Color color, DrawingCanvas canvas ) {

    this.gridCell = gridCell;

    //creating the big circle of the furit loop
    loopA = new FilledOval ( gridCell.loc().getX() + dx,
                             gridCell.loc().getY() + dx, 
                             PA8Constants.GRID_CELL_SIZE/ 
                             PA8Constants.OUTER_SIZE_DIVISOR, 
                             PA8Constants.GRID_CELL_SIZE / 
                             PA8Constants.OUTER_SIZE_DIVISOR, canvas );

    // create the small circle of the fuit loop
    loopB = new FilledOval ( gridCell.loc().getX() + dy,
                             gridCell.loc().getY() + dy, 
                             PA8Constants.GRID_CELL_SIZE / 
                             PA8Constants.INNER_SIZE_DIVISOR, 
                             PA8Constants.GRID_CELL_SIZE / 
                             PA8Constants.INNER_SIZE_DIVISOR, canvas );

    //set up the color for both of the circles
    loopA.setColor( color );
    loopB.setColor( PA8Constants.BACKGROUND_COLOR );
    //putting the fruitLoop insdie the cell
    this.gridCell.insertFruitLoop ( this );
  }

/**
 * remove the fruit loop from canvs
 *
 */

  public void removeLoops() {

    if( loopA != null && loopB != null ) {

      loopA.removeFromCanvas();
      loopB.removeFromCanvas();

    }
  }
}








