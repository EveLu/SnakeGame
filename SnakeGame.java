/** 
 * file name: SnakeGame.java 
 * Author:   Guanyi Lu 
 * Date:     11/15/2018 
 * Sources of help: Piazza, Tutor, Textbook     
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import objectdraw.*;
import java.awt.*;  
import javax.swing.*; 
import java.lang.Object;
 
/**
 * create the game of snake
 */

public class SnakeGame extends WindowController 
                       implements KeyListener {

  private int loopsToWin;
  private long delay;
  private Grid grid;
  private FruitLoop fruitLoop; 
  private Snake snake;
  private int fruitEaten;
  private int mostEated;
  private PopupMessage message;
  private Text text;
  private Text texts;
  private JLabel eaten;
  private JLabel toWin;
  private JLabel mostEaten;

/**
 * create the snake game, put all the components together
 *
 * @param Grid grid is the grid that in the canvs
 * @param int loosToWin is the number of the fruits snake has to eat to win
 * @param long delay is the delay time of the snake movemnet
 */

  public SnakeGame ( Grid grid, int loopsToWin, long delay ) {

    this.grid = grid;
    this.loopsToWin = loopsToWin;
    this.delay = delay;
    this.snake = snake;
  }

/**
 * String the one or two number people enter and take then 
 *
 * as seed and delay time.
 *
 * @param args the command line arguments passed in
 */

  public static void main( String[] args ) {

    int rows = 0;
    int columns = 0;
    int loopsToWin = 0;
    long delay = 0;

    // make sure there are only three numbers enterd
    if ( args.length > PA8Constants.MAX_NUM_ARGS ) {

      System.out.println( PA8Constants.NUM_ARGS_ERR );
      usageLines();
      System.exit(1);

    }

    //check the length of arg
    if ( args.length > 0 ) {
     
      if ( args[0].indexOf( "x" ) == -1 ) {

        System.out.format( PA8Constants.GRID_SIZE_FMT_ERR, args[0] );
        usageLines();
        System.exit(1);
      }

    // ignore x and X
      String lowerCase = args[0].toLowerCase();

      String[] rAndc = lowerCase.split( PA8Constants.GRID_SIZE_DELIM );

      //Check the condition for rows and columns
      try {

        rows = Integer.parseInt( rAndc[0] );

      } catch ( NumberFormatException nfe ) {

         System.out.format( PA8Constants.INT_ERR, 
                            PA8Constants.ROWS_STR, rAndc[0] );
         usageLines();
         System.exit(1);
      }

      //make sure the number entered inside of the range
      if ( rows < PA8Constants.MIN_ROWS || rows > PA8Constants.MAX_ROWS ) {

        System.out.format( PA8Constants.RANGE_ERR, PA8Constants.ROWS_STR, 
                           rows, PA8Constants.MIN_ROWS,
                           PA8Constants.MAX_ROWS );
        usageLines();
        System.exit(1);
      }

     // make sure the string insered is integer
     try {

       columns = Integer.parseInt( rAndc[1] );

     } catch ( NumberFormatException nfe ) {

          System.out.format( PA8Constants.INT_ERR,
                             PA8Constants.COLS_STR, rAndc[1] );
          usageLines();
          System.exit(1);
     }

     // check the range
     if ( columns < PA8Constants.MIN_COLS || 
          columns > PA8Constants.MAX_COLS ) {

       System.out.format( PA8Constants.RANGE_ERR, PA8Constants.COLS_STR, 
                          columns,
                          PA8Constants.MIN_COLS, 
                          PA8Constants.MAX_COLS );
       usageLines();
       System.exit(1);
     }


     //Check loopsToWin data entered is an integer and inside the range
     if ( args.length > 1 ) {

        try {
 
          loopsToWin = Integer.parseInt( args[1] );

        } catch (NumberFormatException nfe ) {

          System.out.format( PA8Constants.INT_ERR, 
                             PA8Constants.LOOPS_STR, args[1] );
          usageLines();
          System.exit(1);
        }

          if ( loopsToWin < PA8Constants.MIN_LOOPS_TO_WIN || 
               loopsToWin > ( rows * columns -1 ) ) {

            System.out.format( PA8Constants.LOOPS_RANGE_ERR, 1 );
            usageLines();
            System.exit(1);
        
          }

        // Check the condition for delay entered
        if ( args.length > PA8Constants.DELAY_IDX ) {
      
          try {
 
            delay = Integer.parseInt( args[ PA8Constants.DELAY_IDX ] );

          } catch (NumberFormatException nfe ) {

            System.out.format( PA8Constants.INT_ERR, PA8Constants.DELAY_STR,
                               args[ PA8Constants.DELAY_IDX ] );
            usageLines();
            System.exit(1);
          }

          if ( delay < PA8Constants.MIN_DELAY || 
               delay > PA8Constants.MAX_DELAY ) {

            System.out.format( PA8Constants.RANGE_ERR, PA8Constants.DELAY_STR,
                               delay, 
                               PA8Constants.MIN_DELAY, 
                               PA8Constants.MAX_DELAY );
            usageLines();
            System.exit(1);
          }

        } // end of 2 if

      }// end of 1 if

    } //end of 0 if

    // set defult value for rows and columns
    if ( args.length == 0 ) {

      rows = PA8Constants.DEFAULT_ROWS;
      columns = PA8Constants.DEFAULT_COLS;

    }

    // set defult value for loops to win
    if ( loopsToWin == 0 ) {

      loopsToWin = rows * columns - 1;
    }

    // set defult value for delay time
    if ( delay == 0 ) {

      delay = PA8Constants.DEFAULT_ANIMATION_DELAY;
    }

    usageLines();
    // create the new grid
    Grid grid = new Grid( rows, columns );

    new Acme.MainFrame( new SnakeGame( grid, loopsToWin, delay ), 
                        args, grid.getWidth() + 1, 
                        grid.getHeight() + PA8Constants.SCORE_PANEL_HEIGHT ); 
  }

/**
 * help us to easy print out the instructions
 *
 */

  public static void usageLines() {

    System.out.format( PA8Constants.USAGE_STR, PA8Constants.MIN_ROWS, 
                       PA8Constants.MAX_ROWS, PA8Constants.MIN_COLS,
                       PA8Constants.MAX_COLS, PA8Constants.DEFAULT_ROWS,
                       PA8Constants.DEFAULT_COLS, 
                       PA8Constants.MIN_LOOPS_TO_WIN,
                       PA8Constants.MIN_DELAY,
                       PA8Constants.MAX_DELAY, 
                       PA8Constants.DEFAULT_ANIMATION_DELAY );

  }

/**
 * draw the grid and intial the game
 */

  public void begin() {

    this.initGame();

    // create the panels
    JPanel labelPanel = new JPanel ();
    labelPanel.setLayout( new GridLayout ( 1, 3 ) );

    fruitEaten = 0;
    mostEated = 0;

    // set up teh panels

    mostEaten = new JLabel ( PA8Constants.MOST_LOOPS_EATEN_STR + "0" );
    toWin = new JLabel ( PA8Constants.LOOPS_TO_WIN_STR + loopsToWin );
    eaten = new JLabel ( PA8Constants.LOOPS_EATEN_STR + "0" );

    labelPanel.add( mostEaten, SwingConstants.CENTER );
    labelPanel.add( toWin, SwingConstants.CENTER );
    labelPanel.add( eaten, SwingConstants.CENTER );
    
    this.add( labelPanel, BorderLayout.NORTH );
    this.validate();

    // add key listeners
    canvas.addKeyListener ( this );
    canvas.requestFocusInWindow();

    // create the message
    message = new PopupMessage ( grid.centerPoint(), 
                             PA8Constants.POPUP_COLOR, 
                             canvas );

    text = new Text ( PA8Constants.PAUSE_STR, 0, 0, canvas );

    texts = new Text ( PA8Constants.RESTART_STR, 0, 0, canvas );

    text.setColor ( PA8Constants.TEXT_COLOR );

    texts.setColor ( PA8Constants.TEXT_COLOR );

    text.hide();
    texts.hide();

  }

/**
 * start the game, set up everything
 */

  private void initGame() {

    grid.resetGrid();

    fruitEaten = 0;

    // draw the grid
    grid.draw( canvas );

    // create new snake
    snake = new Snake ( this, grid, canvas, delay );
   
    int c = -1;
   
    c = ( c + 1 ) % PA8Constants.FRUIT_LOOP_COLORS.length;

    // create the new fruit loop
    fruitLoop = new FruitLoop ( grid.getRandomEmptyCell(), 
                                PA8Constants.FRUIT_LOOP_COLORS[ c ],
                                canvas );

  }

/**
 * get the command when we press the keyboard
 *
 * @param KeyEvent evt is the command that people press the keyboard
 */

  public void keyPressed( KeyEvent evt ) {
 
      switch ( evt.getKeyCode() ) {

        case KeyEvent.VK_UP: snake.setDirection ( Direction.UP ); break;

        case KeyEvent.VK_DOWN: snake.setDirection ( Direction.DOWN ); break;

        case KeyEvent.VK_LEFT: snake.setDirection ( Direction.LEFT ); break;

        case KeyEvent.VK_RIGHT: snake.setDirection ( Direction.RIGHT ); break;

        case KeyEvent.VK_SPACE: snake.pause(); break;

        case KeyEvent.VK_R: this.restart(); break;

        default: snake.setDirection ( Direction.NONE ); break;


      }//end of switch

  }// end of keyPressed

/**
 * help us to update the panel when the numbers changed
 */

  public void setMostEaten() {

    if ( mostEated > fruitEaten ) {

      mostEated = mostEated;
    } else {

      mostEated = fruitEaten;
    }  
    mostEaten.setText ( PA8Constants.MOST_LOOPS_EATEN_STR + mostEated );
  }

/**
 * help us count how many fruits has been eaten
 */

  public void eatFruit() {

    fruitEaten++;
    eaten.setText ( PA8Constants.LOOPS_EATEN_STR + fruitEaten );
  }


/**
 * help us to show the message when we win the game
 *
 */

  public void winGame() {

    text.setText( PA8Constants.WIN_STR );
    // set up the texts
    text.setFontSize( PA8Constants.BIG_FONT_SIZE );
    texts.setFontSize( PA8Constants.SMALL_FONT_SIZE );
    // display the texts
    message.display( text, 0, -PA8Constants.TEXT_Y_OFFSET );
    message.display( texts, 0, PA8Constants.TEXT_Y_OFFSET );
  }

/**
 * help us show the message when we pause the game
 */
  public void pause() {

    text.setText( PA8Constants.PAUSE_STR );
    text.setFontSize( PA8Constants.BIG_FONT_SIZE );
    message.display( text );
  }

/**
 * help us show the message when we loss the game
 */

  public void lossGame() {

    text.setText( PA8Constants.LOSE_STR );

    text.setFontSize( PA8Constants.BIG_FONT_SIZE );
    texts.setFontSize( PA8Constants.SMALL_FONT_SIZE );
    // show the message
    message.display( text, 0, -PA8Constants.TEXT_Y_OFFSET );
    message.display( texts, 0, PA8Constants.TEXT_Y_OFFSET );
    
    this.setMostEaten();
  }

/**
 * help us to restart the game when people press R key
 */

  public void restart() {

    if ( snake.beRestart() == true ) {

      this.message.hide();

      eaten.setText ( PA8Constants.LOOPS_EATEN_STR + "0" );

      this.initGame();
    }

  }

/**
 * check if the people win the game
 *
 * @return boolean ifWin is to determine if the game is iwn or not
 */

  public boolean ifWin () {

    if ( fruitEaten == loopsToWin ) {

      return true;
    }

   return false;
  }

/**
 * help us to hide the message when it's unpaused
 */

  public void unPause() {

    if ( message != null ) {

      message.hide();
    }
  }

/**
 * get the command when people released the key
 */

  public void keyReleased( KeyEvent evt ) {}

/**
 * get the value when peiple typed on the keyboard
 */

  public void keyTyped( KeyEvent evt ) {}



}


