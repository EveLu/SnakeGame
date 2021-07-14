/** 
 * file name: SnakeSegment.java 
 * Author:   Guanyi Lu 
 * Date:     11/15/2018 
 * Sources of help: Piazza, Tutor, Textbook     
 */

import objectdraw.*;
import java.awt.Color;

/**
 * create the segments of snake
 * for snake head or body
 */

public class SnakeSegment {

  private FilledOval snakeSeg;
  private GridCell gridCell;

/**
 * create the segment of snake
 *
 * @param GridCell gridCell is the gridCell of the segment is in
 * @param DrawingCanvas canvas is the Canavs that contains everything
 */

  public SnakeSegment ( GridCell gridCell, DrawingCanvas canvas ) {

    this.gridCell = gridCell;

    // draw the segment which is a circle
    snakeSeg = new FilledOval ( gridCell.loc(), PA8Constants.GRID_CELL_SIZE,
                                PA8Constants.GRID_CELL_SIZE, canvas );   

    // set the color to white
    snakeSeg.setColor( Color.WHITE );
  }

/**
 * determine which cell of segment is in
 *
 * @return GridCell gridCell of the segment is in
 */

  public GridCell whichCell() {

    return gridCell;
  }

/**
 * help us to move the segments
 *
 * @param GridCell newCell is the new  cell that segment moved to
 *
 */

  public void move( GridCell newCell) {

      // move to the new position
      snakeSeg.moveTo( newCell.loc() );
      //update the cell
      gridCell = newCell;
  }

/**
 * remove the segment from canvas
 */

  public void removeSnakeSegment() {

    snakeSeg.removeFromCanvas();

  }










}
