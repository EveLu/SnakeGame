/** 
 * file name: GridCell.java
 * Author:   Guanyi Lu 
 * Date:     11/15/2018 
 * Sources of help: Piazza, Tutor, Textbook     
 */

import objectdraw.*;

/**
 * Creating a grid cell inside of grid
 */

public class GridCell {

  private FilledRect gridCell;
  private Location loc;
  private FruitLoop fruitLoop;
  private SnakeSegment snakeSegment;
  private int row;
  private int column;
  private Grid grid; 
  private boolean empty;

/**
 * create the cells for the grid
 *
 * @param int row is the row number passed in
 * @param int column is the column number passed in
 * @param Grid grid is the grid passed that contains these cells
 */

  public GridCell ( int row, int column, Grid grid ) {

    this.row = row;
    this.column = column;
    this.grid = grid;

    //intial the gridcell as empty
    fruitLoop = null;
    snakeSegment = null; 

    //create new location for this grid cell
    loc = new Location ( column*PA8Constants.GRID_CELL_SIZE,
                          row*PA8Constants.GRID_CELL_SIZE );
    

  }
 
/**
 * help us get the location of this grid cell
 *
 * @return Location loc is the location of this gridCell
 */

  public Location loc() {

    return loc;
  }

/**
 * help us to get the row number
 *
 * @return int rowsLoc is the number of row
 */

  public int rowsLoc () {
  
    return row;
  }

/**
 * help us to get the column number
 *
 * @return int columnLoc is the number of column
 */

  public int columnsLoc () {


    return column;
  }

/**
 * help us to check if the cell is empty or what
 *
 * @return boolean ifEmpty if the cell is empty
 */

  public boolean ifEmpty () {

    if ( fruitLoop == null && snakeSegment == null ) {

      return true;

    } else {

     return false;
    }
  }

/**
 * help us to insert the fruit loop to this cell
 *
 * @param FruitLoop fruitLoop is the fruit that passed in and be inserted
 *
 */

  public void insertFruitLoop ( FruitLoop fruitLoop ) {

    this.fruitLoop = fruitLoop;
  }

/**
 * help us to insert the snake segmnet to this cell
 *
 * @param SnakeSegment snakeSegment is the fruit that passed in and be inserted
 */

  public void insertSnakeSegment ( SnakeSegment snakeSegment ) {

    this.snakeSegment = snakeSegment;
  }

/**
 * help us to check if this cell has the fruit loop or not
 *
 * @return boolean hasFruitLoop is determin if there is a fruit or not
 */

  public boolean hasFruitLoop () {

    if ( fruitLoop != null ) {

      return true;

    } else {

      return false;
    }
  }

/**
 * help us to check if this cell has the Snake Segment or not
 *
 * @return boolean hasSnakeSegment is determin if there is a SnakeSegment
 */

  public boolean hasSnakeSegment () {

    if ( snakeSegment != null ) {

      return true;
      
    } else {

      return false;
    }
  }

/**
 * help us to remove the fruitLoop that inside of the cell
 */

  public void removeFruitLoop () {

    if( fruitLoop != null ) {

      fruitLoop.removeLoops();
      this.fruitLoop = null;
    }
  }

/**
 * help us to remove the snake segment that in the grid cell
 */

  public void removeSnakeSegment () {

    this.snakeSegment = null;
  }

}






