/** 
 * file name: Grid.java
 * Author:   Guanyi Lu 
 * Date:     11/15/2018 
 * Sources of help: Piazza, Tutor, Textbook     
 */ 

import java.util.Random;
import objectdraw.*;
import java.awt.*;

/**
 * Creating a grid in canvas for snakeGame
 */

public class Grid {

  //make 2D array for gridCell
  private GridCell[][] gridCell;
  // means a single gridCell
  private GridCell cell;
  private Grid grid;
  private Random rand;
  private int rows;
  private int columns;  
  //keep track the numbers of empty cells
  private int emptyCells;
  private Location centerPoint;

/**
 * Making a grid in canvas for snakeGame, and there are lost of gridcells
 *
 * @param int rows is the number of rows
 * @param int columns is the number of columns
 */

  public Grid ( int rows, int columns ) {
    //passin the value
    this.rows = rows;
    this.columns = columns;

    resetGrid();
    emptyCells = rows * columns;
    //Create a random generator
    rand = new Random ();

  }

/**
 * Reset the Grid the there is numbers of rows and columns
 *
 */

  public void resetGrid() {

    // create 2d array for all gridCells
    gridCell = new GridCell[ rows ][ columns ];

    //putting all the gridCells inside of gris
    for ( int l = 0; l < rows; l++ ) {

      for ( int i = 0; i < columns; i++ ) {

         gridCell[ l ][ i ] = new GridCell( l, i, grid );
         
      }
    }//end of for	
  }

/**
 * help us to get the width of the grid
 *
 * @return the width of the grid
 */

  public int getWidth() {

   return columns*PA8Constants.GRID_CELL_SIZE; 

  }

/**
 * help us get the height of the grid
 *
 * @return the height of the grid
 */

  public int getHeight() {

    return rows*PA8Constants.GRID_CELL_SIZE;
  }

/**
 * help us to get the center point of the grid
 *
 * @return the location of the center point
 */

  public Location centerPoint() {

    //x-axis of the center point
    int x = rows*PA8Constants.GRID_CELL_SIZE /
               PA8Constants.NUM_GRID_SIZE_DIMENSIONS;

    //y-axis of the ceter point
    int y = columns*PA8Constants.GRID_CELL_SIZE / 
               PA8Constants.NUM_GRID_SIZE_DIMENSIONS;

    centerPoint = new Location ( x, y );

    return centerPoint;

  }

/**
 * help us to get the specific gridCell
 * 
 * @param int whichRow is the row numberof that cell
 * @param int whichColumn is the column number of that cell
 * @return GridCell gridvell is an gridcell inside of grid
 */

  public GridCell getGridCell ( int whichRow, int whichColumn ) {

    //set up the margin
    if ( whichRow >= 0 && whichRow < rows && whichColumn < 
                                     columns && whichColumn >= 0 ) {

      return gridCell[ whichRow ] [ whichColumn ];
    } 
      //defult is null
      return null; 
  }

/**
 * help us to get the cell that next to current cell
 *
 * @param GridCell cell is the current cell that we know
 *
 * @param Direction dir id the direction passsed in
 * 
 * @return GridCell gridvell is an gridcell inside of grid

 */

  public GridCell getCellNeighbor(GridCell cell, Direction dir) {

    this.cell = cell;

    int newRow;
    int newColumn;

    //when the direction is UP
    if ( dir == Direction.UP ) {

      newRow = cell.rowsLoc() + dir.getY();

      return getGridCell ( newRow, cell.columnsLoc() );
    }

    //when the direction is DOWN
    if ( dir == Direction.DOWN ) {
      
      newRow = cell.rowsLoc() + dir.getY();

      return getGridCell ( newRow, cell.columnsLoc() );
    }

    //when the direction is LEFT
    if ( dir == Direction.LEFT ) {

      newColumn = cell.columnsLoc() + dir.getX();

      return getGridCell ( cell.rowsLoc(), newColumn );
    }

    //when the direction is RIGHT
    if ( dir == Direction.RIGHT ) {

      newColumn = cell.columnsLoc() + dir.getX();

      return getGridCell ( cell.rowsLoc(), newColumn );

    }

    //when the direction is NONE

    if ( dir == Direction.NONE ) {

      return getGridCell( cell.rowsLoc(), cell.columnsLoc() );
    }

    return null;

  }

/**
 * help us to get an random empty cell
 *
 * @return GridCell randomEmptyCell is a empty cell that randomly chosed
 */

  public GridCell getRandomEmptyCell() {

    boolean findECell = false;

    //randomly insert the rows and columns
    int rowNum = rand.nextInt( rows );
    int columnNum = rand.nextInt ( columns );

    if ( emptyCells == 0 ) {

      return null;
    }

    // we keep looking for an empty until we find one
    while ( findECell == false ) {

      getGridCell( rowNum, columnNum );

      if ( getGridCell( rowNum, columnNum ).ifEmpty() == true ) {

        findECell = true;
      } 

      rowNum = rand.nextInt( rows );
      columnNum = rand.nextInt ( columns );

    }
    //return the random we got
    return getGridCell( rowNum, columnNum );

  }

/**
 * help us to draw the lines for cells
 *
 * @param DrawingCanvas canvas is the canvas contains everything
 */

  public void draw (DrawingCanvas canvas) {

    // new the grid
    FilledRect grid = new FilledRect ( 0, 0, this.getWidth() , 
                                       this.getHeight(), canvas );
    //set up the color
    grid.setColor( PA8Constants.BACKGROUND_COLOR );

    // draw the line
    for ( int n = 0; n < rows + 1; n++ ) {

      Line hLine = new Line ( 0, n * PA8Constants.GRID_CELL_SIZE,
                              this.getWidth(), 
                              n * PA8Constants.GRID_CELL_SIZE, 
                              canvas ); 
      // set upt the colors
      hLine.setColor( PA8Constants.GRID_LINE_COLOR );
    }

    // draw the vertical lines
    for ( int m = 0; m < columns; m++ ) {

      Line vLine = new Line ( m * PA8Constants.GRID_CELL_SIZE, 0,
                              m * PA8Constants.GRID_CELL_SIZE,
                              this.getHeight(), canvas );

      // set up the color
      vLine.setColor( PA8Constants.GRID_LINE_COLOR );
      
    }
  }
}



