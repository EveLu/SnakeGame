/** 
 * file name: Snake.java 
 * Author:   Guanyi Lu 
 * Date:     11/15/2018 
 * Sources of help: Piazza, Tutor, Textbook     
 */

import Acme.*;
import objectdraw.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Create a snake and it is activeObject
 *
 * It can move and follow the directions given by keyboard
 *
 */

public class Snake extends ActiveObject {

  private Grid grid;
  private long delay;
  private DrawingCanvas canvas;
  private SnakeGame snakeGame;
  //Create an Snake head
  private SnakeSegment snakeHead;
  // Create an arrayList for snakeBody
  private ArrayList< SnakeSegment > snakeBody;
  private Direction dir;
  //Determine if the snake dead or not
  private boolean dead = false;
  public boolean paused = false;
  private int d = 0;
  private boolean win = false;

/**
 * Create a snake and it is activeObject
 *
 * It can move and follow the directions given by keyboard
 *
 * @param SnakeGame snakeGame is the game that passed in
 * @param Grid grid is the grid we created and passed in
 * @param DrawingCanvas canvas is the canvas that contain the grid
 * @param long delay is the delay time for the snake
 */

  public Snake ( SnakeGame snakeGame, Grid grid, DrawingCanvas canvas,
                 long delay ) {

    this.snakeGame = snakeGame;
    this.grid = grid;
    this.canvas = canvas;
    this.delay = delay;

    //Create the new snake head in an random cell
    snakeHead = new SnakeSegment ( grid.getRandomEmptyCell(), canvas );
    // defult direction
    dir = Direction.NONE;

    // make an arraylist for snakeBody
    snakeBody = new ArrayList< SnakeSegment >();

    start();

  }

/**
 * when we paused the game or un pause the game
 *
 * change the value of boolean
 */

  public void pause() {

      paused = !paused;

  }

/**
 * Set up the direction for snake head and its body
 *
 * @param Direction dir is the direction passed in
 *
 */

  public void setDirection( Direction dir ) {

    if ( paused == false ) {

      // Make sure when snake has body, it can not go back
      if ( snakeBody.size() > 0 && !this.dir.isOpposite( dir ) ) {
        
        this.dir = dir;

      } 

      // when there is only one head, no restrictions for moving direction
      if ( snakeBody.size() <= 0 ) {
        this.dir = dir;

      } //end of if

    } // enf of pause-false if

  }

/**
 * snake is an activeobject, so we need an run method to run
 *
 * the snake will eat the fruits and grow and moving continuously
 */

  public void run() {


    // stop the game when the snake dead or win the game
    while ( dead == false && win == false ) {

      // when game is unpaused
      if ( paused == false ) {

        snakeGame.unPause();

        //determine if we won the game
        if ( snakeGame.ifWin() == true ) {

          snakeGame.winGame();
          win = true;

        }

        //determine if the snake still moving
        boolean move = this.move();

        if ( move == false ) {

          // if snake is not moving, it is dead
          snakeGame.lossGame();
          dead = true;
        }//end of if

      } else {

        snakeGame.pause();

      }    

      pause( delay );

    }//end of while

  }

/**
 * determine if snake still moving or not
 *
 * @return boolean move is to see if it is move
 */

  public boolean move() {

    //ausuming it is not eat anything yet
    boolean eat = false;

    // create two gridcell for head and body
    GridCell headLoc = snakeHead.whichCell();
    GridCell nextLoc = grid.getCellNeighbor( headLoc, dir );

    // if snake hit the wall or eat itself, it dies
    if ( nextLoc == null || nextLoc.hasSnakeSegment() == true ) {

      die();
      return false;
    } 

    // when there is a fruitLoop in the front
    if ( nextLoc.hasFruitLoop() == true ) {

      //eat it
      nextLoc.removeFruitLoop();
      eat = true;
 
      // the snake will grow one unit everytime when it eat an fruitLoop
      SnakeSegment body = new SnakeSegment( headLoc, canvas );
      snakeBody.add( body ); 
    }

      //move the head
      snakeHead.move( nextLoc );
      nextLoc = headLoc;

    // adding the bodies to arrayList
    for ( SnakeSegment a : snakeBody ) { 

      headLoc = a.whichCell();
     
      headLoc.removeSnakeSegment ();

      a.move ( nextLoc );

      nextLoc.insertSnakeSegment( a );

      nextLoc = headLoc;

    }//end of for

    // if the snake eat the fruitLoop
    if ( eat == true && win == false ) {
 
      //An new random fruitLoop will show up
      FruitLoop fruitLoop = new FruitLoop ( grid.getRandomEmptyCell(), 
                                PA8Constants.FRUIT_LOOP_COLORS
                                [ ++d % 
                                PA8Constants.FRUIT_LOOP_COLORS.length ],
                                canvas );
      //count the fruit be eaten
      snakeGame.eatFruit();
  
    }

    return true;
  }

/**
 * when snake is dead
 * we loss the game
 */

  public void die() {

    dead = true;
    snakeGame.lossGame();
  }

/**
 * help us to se if the game is win or not
 *
 */

  public void win() {

    if ( win == true ) {
    
      snakeGame.winGame();
      win = false;
    }
  }

/**
 * help us to determine when should we restart the game
 *
 * @return boolean beRestart is to determine should we restart
 */

  public boolean beRestart() {

    if ( win == true || dead == true ) {

      return true;
    }

    return false;

  }

}



