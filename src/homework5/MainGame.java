/**
 * MainGame
 * 
 * Main class for game implementation
 * 
 * @author Marco Antonio Peyrot A00815262
 * @author Mario Sergio Fuentes AA01036141
 * @version 4.6
 * @date 23/02/2015
 */

package homework5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainGame extends JFrame implements Runnable, KeyListener {

    // Game variable declaration
    private boolean bPause;
    private boolean bGameOver;
    private boolean bLoading;
    private boolean bFirstTime;
    private boolean bBluePower;
    private boolean bScores;
    private boolean bShowFace;
    private int iJFrameWidth;
    private int iJFrameHeight;
    private int iLevel;
    private int iBlockR;
    private int iBlockC;
    private int iDifficulty;
    private int iCounterBlue;
    private int iCantBombs;
    private int iFaceCounter;
    private int iFaceIndex;
    private Image imaImageJFrame;
    private Image imaImageInstructions;
    private ImageIcon imiImageInstructions;
    private Saturn086_Player satPlayer;
    private Saturn086_DefaultEnemy[][] satArrBlocks;
    private Saturn086_DefaultEnemy satBall;
    private Saturn086_DefaultEnemy[] satArrBombs;
    private Graphics graGraficsJFrame; // Graphic objects of the image
    private LinkedList lklAnimations;
    private Animacion aniAux;
    private Animacion aniBarGreen;
    private Animacion aniBarYellow;
    private Animacion aniBarRed;
    private Saturn086_DefaultEnemy[] satArrFaces;
    
    // green bar images
    Image imaGreen0;
    Image imaGreen1;
    Image imaGreen2;
    
    // yellow bar images
    Image imaYellow0;
    Image imaYellow1;
    Image imaYellow2;
    
    // green bar images
    Image imaRed0;
    Image imaRed1;
    Image imaRed2;
    
    // BLOCK animation images
    Image imaBlock0;
    Image imaBlock1;
    Image imaBlock2;
    
    // starting time
    private long lTiempoActual;
    
    // sounds
    private SoundClip socMainMusic;
    private SoundClip socMenuMusic;
    private SoundClip socLife;
    private SoundClip socExplosion;
    private SoundClip socSpeed;
    private SoundClip socBlock;
    
    /**
     * MainGame
     * 
     * Default game constructor. Used to call explicitly the init and start
     * methods when a new MainGame variable is created.
     */
    public MainGame() {
        init();
        start();
    }
    
    /** 
     * init
     * 
     * In this method all variables are initialized and objects are created.
     */
    public void init() {
        // Variables
        bGameOver = false;
        bPause = false;
        bLoading = true;
        bFirstTime = true;
        bBluePower = false;
        bScores = false;
        iJFrameHeight = 600;
        iJFrameWidth = 816;
        iBlockR = 3;
        iBlockC = 16;
        iLevel = 1;
        iDifficulty = 1;
        iCounterBlue = 500;
              
        // animations images
               
        //loading color bar images
        // green
        imaGreen0 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("verde1.png"));
        imaGreen1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("verde2.png"));
        imaGreen2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("verde3.png"));
        // yellow
        imaYellow0 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("yellow1.png"));
        imaYellow1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("yellow2.png"));
        imaYellow2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("yellow3.png"));
        // red
        imaRed0 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("rojo1.png"));
        imaRed1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("rojo2.png"));
        imaRed2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("rojo3.png"));
        
        // loading boom images
        imaBlock0 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("frame_000.gif"));
        imaBlock1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("frame_001.gif"));
        imaBlock2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("frame_002.gif"));
        
        // Create bar animations
        // green
        aniBarGreen = new Animacion();
        aniBarGreen.sumaCuadro(imaGreen0, 300);
        aniBarGreen.sumaCuadro(imaGreen1, 300);
        aniBarGreen.sumaCuadro(imaGreen2, 300);
        aniBarGreen.setPosX(0);
        aniBarGreen.setPosY(0);
        aniBarGreen.setTiempoActual(System.currentTimeMillis());
        // yellow
        aniBarYellow = new Animacion();
        aniBarYellow.sumaCuadro(imaYellow0, 300);
        aniBarYellow.sumaCuadro(imaYellow1, 300);
        aniBarYellow.sumaCuadro(imaYellow2, 300);
        aniBarYellow.setPosX(0);
        aniBarYellow.setPosY(0);
        aniBarYellow.setTiempoActual(System.currentTimeMillis());
        // green
        aniBarRed = new Animacion();
        aniBarRed.sumaCuadro(imaRed0, 300);
        aniBarRed.sumaCuadro(imaRed1, 300);
        aniBarRed.sumaCuadro(imaRed2, 300);
        aniBarRed.setPosX(0);
        aniBarRed.setPosY(0);
        aniBarRed.setTiempoActual(System.currentTimeMillis());
        
        // Create bar animations
        // green
        aniBarGreen = new Animacion();
        aniBarGreen.sumaCuadro(imaGreen0, 300);
        aniBarGreen.sumaCuadro(imaGreen1, 300);
        aniBarGreen.sumaCuadro(imaGreen2, 300);
        aniBarGreen.setPosX(0);
        aniBarGreen.setPosY(0);
        aniBarGreen.setTiempoActual(System.currentTimeMillis());
        // yellow
        aniBarYellow = new Animacion();
        aniBarYellow.sumaCuadro(imaYellow0, 300);
        aniBarYellow.sumaCuadro(imaYellow1, 300);
        aniBarYellow.sumaCuadro(imaYellow2, 300);
        aniBarYellow.setPosX(0);
        aniBarYellow.setPosY(0);
        aniBarYellow.setTiempoActual(System.currentTimeMillis());
        // green
        aniBarRed = new Animacion();
        aniBarRed.sumaCuadro(imaRed0, 300);
        aniBarRed.sumaCuadro(imaRed1, 300);
        aniBarRed.sumaCuadro(imaRed2, 300);
        aniBarRed.setPosX(0);
        aniBarRed.setPosY(0);
        aniBarRed.setTiempoActual(System.currentTimeMillis());
        
        // initialized animations linked list
        lklAnimations = new LinkedList<Animacion>();

        // sound settings
        iCantBombs = 5; // Number of bombs that appear in each level
        socMainMusic = new SoundClip("Menu.wav"); // Assign music file
        socMainMusic.setLooping(true); // Play forever
        socMenuMusic = new SoundClip("Menu.wav"); // Assign music file
        socMenuMusic.setLooping(true); // Play forever
        socMainMusic.stop(); // Stop music from playing
        socMenuMusic.play(); // Play Main menu music
        socLife = new SoundClip("life.wav"); // Sound for life bonus
        socExplosion = new SoundClip("Bomb.wav"); // Sound for explosion bonus
        socSpeed = new SoundClip("speed.wav"); // Sound for speed bonus
        socBlock = new SoundClip("coin.wav"); // Sound for normal block

        // Load instruction's image
        imaImageInstructions = Toolkit.getDefaultToolkit().
                getImage(this.getClass().getResource("ImageInstructions.png"));
        // Make image small
        imaImageInstructions = imaImageInstructions.getScaledInstance(100, 140,
                java.awt.Image.SCALE_DEFAULT);
        imiImageInstructions = new ImageIcon(imaImageInstructions);

        // Set JFrame size
        setSize(iJFrameWidth, iJFrameHeight);
        
        // Objects inicialization
        satArrBlocks = new Saturn086_DefaultEnemy[iBlockR][iBlockC];
        satPlayer = new Saturn086_Player();
        satBall = new Saturn086_DefaultEnemy();
        satArrBombs = new Saturn086_DefaultEnemy[iCantBombs];
        satArrFaces = new Saturn086_DefaultEnemy[4];
        
        // Reset all objects to their default values
        newGame();
        
        //Adds to the JFrame the capability to hear keyboard events
	addKeyListener(this);
    }
    
    public void newGame() {
        // New game player attributes inicialization
        playerNewGame();
        // New game blocks attributes inicialization
        blocksNewGame(iBlockR, iBlockC);
        // New game ball attributes inicialization
        ballNewGame(iDifficulty);
        // Add bombs
        createBombs();
        // Add bonuses
        selectPowerUps(iBlockR, iBlockC);
        // Add lives to each block
        selectBlockLives(iBlockR, iBlockC); 
        // Select how many blocks will be shown ér level
        randomFigure(iBlockR, iBlockC);
        iFaceCounter = 40;
        newFaces();
    }
    
    /** 
     * start
     * 
     * Overwritten method from class <code>JFrame</code>.<P>
     * In this method the main thread is created. It is called after init or
     * when the user visits another application and then returns to this
     * <code>JFrame</code>
     */
    public void start () {
        // Declare a thread
        Thread th = new Thread (this);
        // Start thread
        th.start ();
    }
    
    /**
     * paintFaces
     * 
     * Method used for painting the faces
     * 
     * @param graDibujo is the image where the faces are drawn
     */
    public void paintFaces(Graphics graDibujo){
        for (int iI = 0; iI < 4; iI ++) {
            satArrFaces[iI].paint(graDibujo, this);
        }
    }
    
    /**
     * newFaces
     * 
     * Set faces to their default values
     */
    public void newFaces() {
        for (int iI = 0; iI < 4; iI ++) {
            satArrFaces[iI] = new Saturn086_DefaultEnemy();
            satArrFaces[iI].setBehavior(Saturn086_Object.eBehavior.STOP_RIGHT);
            satArrFaces[iI].setImageIcon("face" + iI + ".gif", 80, 100);
            satArrFaces[iI].setISpeed(2);
            satArrFaces[iI].setStrName("FACE" + iI);
            satArrFaces[iI].setBPaint(false);
        }
        
        satArrFaces[0].setIPosX(0);
        satArrFaces[0].setIPosY(iJFrameHeight - 100);
        satArrFaces[1].setIPosX(iJFrameWidth / 4);
        satArrFaces[1].setIPosY(iJFrameHeight - 100);
        satArrFaces[2].setIPosX(iJFrameWidth / 2);
        satArrFaces[2].setIPosY(iJFrameHeight - 100);
        satArrFaces[3].setIPosX(iJFrameWidth - 80);
        satArrFaces[3].setIPosY(iJFrameHeight - 100);
    }
    
    /** 
     * run
     * 
     * Overwritten method form class <code>Thread</code>.<P>
     * In this method the main thread is executed, it contains the game's
     * instructions
     */
    @Override
    public void run() {  
        
        lTiempoActual = System.currentTimeMillis();
        // The Game cycle never stops until the game is exited
        while (true) { 
            // if game is not paused or lost continue with normal game execution
            if (!bPause && !bGameOver && !bLoading) {
                actualize();
                checkCollision();
            }
            if (bScores) {
                try {
                    JDialogScore.updateRanking(null,satPlayer.getIScore(),
                            "puntuaciones.txt");

                } catch (IOException ioeError) {
                     System.out.println("Hubo un error en lectura de archivo" + 
                            ioeError.toString());
                }
                bScores = false;
            }
            repaint();
            try	{
                // El thread se duerme.
                Thread.sleep (20);
            }
            catch (InterruptedException iexError) {
                System.out.println("Hubo un error en el juego " + 
                        iexError.toString());
            }
	}  
    }
    
     /** 
     * actualize
     * 
     * Method that updates the objects status 
     */
    public void actualize() {
        satPlayer.move(); // Move bar
        satBall.move(); // Move ball
        
        if (aniBarGreen != null && aniBarYellow != null && aniBarRed != null) {
                    //Determina el tiempo que ha transcurrido desde que el
                    //Applet inicio su ejecución
            long tiempoTranscurrido =
                System.currentTimeMillis() - lTiempoActual;

            //Guarda el tiempo actual
            lTiempoActual += tiempoTranscurrido;

            //Actualiza la animación en base al tiempo transcurrido
            aniBarGreen.actualiza(tiempoTranscurrido);
            aniBarYellow.actualiza(tiempoTranscurrido);
            aniBarRed.actualiza(tiempoTranscurrido);
        }
        
        // determines image to be displayed by playing bar
        if (aniBarGreen != null && aniBarYellow != null && aniBarRed != null) {
                    
            long lTiempoTranscurrido =
                System.currentTimeMillis() - lTiempoActual;

            //Guarda el tiempo actual
            lTiempoActual += lTiempoTranscurrido;

            //Actualiza la animación en base al tiempo transcurrido
            aniBarGreen.actualiza(lTiempoTranscurrido);
            aniBarYellow.actualiza(lTiempoTranscurrido);
            aniBarRed.actualiza(lTiempoTranscurrido); 
        }
        
        
        if(bShowFace) {
            iFaceCounter--;                    
        }
        
        if(iFaceCounter <= 0){
            satArrFaces[iFaceIndex].setBPaint(false);
            iFaceCounter = 40;
            bShowFace = false;
        }
        
        // updateAnimations
        for (int iI = 0; iI < lklAnimations.size(); iI ++) {
            Animacion aniFor = (Animacion) lklAnimations.get(iI);
            // check if animation is active and surpasses timeout
            if( !aniFor.isBoolTermina() && aniFor.getTiempoDeAnimacion() > 
                    270 ) {
                aniFor.setBoolTermina(true);
            // check if animations is active and below timeout    
            }
            else if (!aniFor.isBoolTermina() && aniFor.getTiempoDeAnimacion() 
                    < 270) {

                //Time since the JFrame's execution
                long lTiempoTranscurrido =
                    System.currentTimeMillis() - aniFor.getTiempoActual();

                aniFor.setTiempoActual( aniFor.getTiempoActual() + 
                        lTiempoTranscurrido );
                aniFor.actualiza(lTiempoTranscurrido);
                
            }
        }
        
        moveBombs(); // When activated move bombs downwards
        
        // If blue block is touched
        if (bBluePower) {
            // Active for 10 seconds
            iCounterBlue--;
            // When time is up
            if(iCounterBlue <= 0) {
                bBluePower = false;
                // Return ball to normal speed
                satBall.setISpeed(satBall.getISpeed() - 2);
                iCounterBlue = 500;
            }
        }
        
        // If a life is lost or new game
        if (satBall.getBehavior() == Saturn086_Object.eBehavior.STOP_RIGHT) {
            // Ball stays in the bar
            ballStartPos();
        }
        else {
            // Start normal move movement
            satBall.move();
        }
        
        // If game is lost
        if(satPlayer.getILives() <= 0) {
            bGameOver = true;
            bLoading = true;
            bFirstTime = true;
        }
        
        // If all blocks are eliminated
        if(!(satPlayer.getIScore() == 0) && checkWin()) {
            // Advance in level
            iLevel ++;
            // Save player's current lifes
            int iLives = satPlayer.getILives();
            // Save player's socre
            int iScore = satPlayer.getIScore();
            // Reset to default values
            newGame();
            // Set player's lifes
            satPlayer.setILives(iLives);
            // Set player's score
            satPlayer.setIScore(iScore);
        }
    }
    
    /**
     * bounceLeft
     * 
     * Ball bounces on left side
     */
    public void bounceLeft() {
        if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_UP) {
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_RIGHT);
        }
        else if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_LEFT) {
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_DOWN);
        }
    }
    
    /**
     * bounceRight
     * 
     * Ball bounces on right side
     */
    public void bounceRight() {
        if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_RIGHT) {
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_UP);
        }
        else if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_DOWN) {
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_LEFT);
        }    
    }
    
    /**
     * bounceTop
     * 
     * Ball bounces on top side
     */
    public void bounceTop() {
        if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_UP) {
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_LEFT);
        }
        else if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_RIGHT){
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_DOWN);
        }
    }
    
    /**
     * bounceBottom
     * 
     * Ball bounces on bottom side
     */
    public void bounceBottom() {
        if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_DOWN) {
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_RIGHT);
        }
        else if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_LEFT) {
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_UP);
        }
    }
    
    /**
     * checkCollision
     * 
     * Method that checks the collisions between objects
     */
    public void checkCollision(){
        // Player cannot get outside the JFrame
        satPlayer.marginContainer(iJFrameWidth, iJFrameHeight);
        // Ball bounces on left margin
        if (satBall.leftMarginCheck()) {
            bounceLeft();
        }
        // Ball bounces on right margin
        if (satBall.rightMarginCheck(iJFrameWidth)) {
            bounceRight();
        }
        // Ball bounces on upper margin
        if (satBall.topMarginCheck()) {
            bounceTop();
        }
        // Ball bounces on bottom margin
        if (satBall.bottomMarginCheck(iJFrameHeight)) {
            bounceBottom();
            satBall.setBehavior(Saturn086_Object.eBehavior.STOP_RIGHT);
            // Player looses 1 live
            satPlayer.setILives(satPlayer.getILives() - 1);
            // Used to reset blue power values
            if (bBluePower == true) {
                bBluePower = false;
                satBall.setISpeed(satBall.getISpeed() - 2);
                iCounterBlue = 500;
            }
            if (satPlayer.getILives() <= 0) {
                bScores = true;
            }
        }
        
        // Collision with blocks
        intersectBlocks(iBlockR, iBlockC);
        // Collision with player
        intersectPlayer();
        // Collision with falling bombs
        intersectBombs();
    }
    
    /**
     * paint
     * 
     * Overwritten from the class <code>JFrame</code>,
     * which inherits from class Container.<P>
     * this method updates the container and defines when the paint 
     * method is used
     * 
     * @param graGrafico is an <code>Graphics</code> object used to paint.
     */
    public void paint (Graphics graGrafico) {
        int iHeight = 30;
        // Initialize the DoubleBuffer
        if (imaImageJFrame == null) {
            imaImageJFrame = createImage (this.getSize().width, 
                    this.getSize().height);
            graGraficsJFrame = imaImageJFrame.getGraphics ();
        }

        // Update background image
        URL urlImageBG = this.getClass().getResource("GameBackground.jpeg");
        // Show loading image
        if (bLoading) {
            urlImageBG = this.getClass().getResource("GameLoading.jpg");
            iHeight = 0;
        }
        Image imaImageBG = 
                Toolkit.getDefaultToolkit().getImage(urlImageBG);
        graGraficsJFrame.drawImage(imaImageBG, 0, 
                iHeight, getWidth(), getHeight(), this);

        // Update the Foreground.
        graGraficsJFrame.setColor (getForeground());
        paint2(graGraficsJFrame);

        // Paint the updated image
        graGrafico.drawImage (imaImageJFrame, 0, 0, this);
    }
    
    /**
     * paint
     * 
     * Overwritten method from class <code>JFrame</code>,
     * which is inherited from class Container.<P>
     * This method paints the image with the updated position, also if the image
     * is not loaded it displays a warning message
     * 
     * @param graDibujo is an <code>Graphics</code> object used to draw
     */
    public void paint2(Graphics graDibujo) {
        // Verify that game hasn't ended
        if (bGameOver || bLoading) {
            // Draw white lines
            graDibujo.setColor(Color.white);
            // set font
            Font fonFont = new Font("Arial", Font.PLAIN, 30);
            graDibujo.setFont(fonFont);

            // If game was lost
            if(bGameOver) {
                graDibujo.drawString("Game Over", iJFrameWidth / 2 - 55 ,
                        60);
                fonFont = new Font("Arial", Font.PLAIN, 15);
                graDibujo.setFont(fonFont);
                graDibujo.drawString("Points: " + satPlayer.getIScore(),
                        iJFrameWidth - 190 , iJFrameHeight - 230);
                graDibujo.drawString("Level: " + iLevel,
                        iJFrameWidth - 190 , iJFrameHeight - 200);
            }
            else {
               graDibujo.drawString("Main Menu", iJFrameWidth / 2 - 55 ,
                        60); 
            }
            // Return to standard font
            fonFont = new Font("Arial", Font.PLAIN, 15);
            graDibujo.setFont(fonFont);

            // Display options
            graDibujo.drawString("Press:", iJFrameWidth - 190 ,
                    iJFrameHeight - 150);
            graDibujo.drawString("\'S\' -> Start New Game", 
                    iJFrameWidth - 190 , iJFrameHeight - 120);
            graDibujo.drawString("\'M\' -> Main Menu", 
                    iJFrameWidth - 190 , iJFrameHeight - 100);
            graDibujo.drawString("\'H\' -> Game Instructions", 
                    iJFrameWidth - 190, iJFrameHeight - 80);
            graDibujo.drawString("\'P\' -> Pause Game", 
                    iJFrameWidth - 190, iJFrameHeight - 60);
            graDibujo.drawString("\'R\'/\'L\' -> Ball Direction", 
                    iJFrameWidth - 190, iJFrameHeight - 40);
            graDibujo.drawString("\'W\' -> High Scores", 
                    iJFrameWidth - 190, iJFrameHeight - 20);

            fonFont = new Font("Arial", Font.PLAIN, 15);
            graDibujo.setFont(fonFont);
        }
        else {
            // if object images are loaded
            if (satPlayer.getImageIcon() != null && satArrBlocks != null &&
                    !bLoading && satBall.getImageIcon() != null && 
                    satArrBombs != null) {
                // change player icon depending on lives status
                if (satPlayer.getILives() > 2) {
                    satPlayer.setImage(aniBarGreen.getImagen());
                } else if (satPlayer.getILives() == 2) {
                    satPlayer.setImage(aniBarYellow.getImagen());
                } else {
                    satPlayer.setImage(aniBarRed.getImagen());
                }
                satPlayer.paint(graDibujo, this);

                if (bShowFace) {
                    paintFaces(graDibujo);
                }
                paintBlocks(graDibujo, iBlockR, iBlockC);
                paintBombs(graDibujo);
                satBall.paint(graDibujo, this);
                // Paint player's lives
                graDibujo.setColor(Color.white);
                graDibujo.drawString(String.valueOf("Lives: " + 
                        satPlayer.getILives()), 10, 50);
                // Paint player's points
                graDibujo.drawString(String.valueOf("Points: " + 
                        satPlayer.getIScore()), 100, 50);
                
                // Paint level
                graDibujo.drawString(String.valueOf("Level: " + iLevel) ,
                        250, 50);
                // Paint time counter for BLUE power
                // Paint time for blue power
                graDibujo.setColor(Color.yellow);
                graDibujo.drawString(String.valueOf("Time left: " + 
                        (iCounterBlue / 50) + " sec.") ,
                        350, 50);
                
                graDibujo.setColor(Color.black);
                
                // Paint animations
                if (lklAnimations != null){
                    for (Object objAux : lklAnimations) {
                        Animacion aniAux = (Animacion) objAux;
                        if (aniAux != null && !aniAux.isBoolTermina()) {
                             graDibujo.drawImage(aniAux.getImagen(), 
                                     (int) aniAux.getPosX(), 
                                     (int) aniAux.getPosY(), this);
                        } 
                    }
                }
                // Paint Pause
                if (bPause) {
                    // Show pause message in large font
                    Font fonFont = new Font("Arial", Font.PLAIN, 40);
                    graDibujo.setFont(fonFont);
                    graDibujo.setColor(Color.white);
                    graDibujo.drawString(String.valueOf("GAME PAUSED") ,
                            iJFrameWidth / 2 - 150, iJFrameHeight / 2 + 50);

                    fonFont = new Font("Arial", Font.PLAIN, 15);
                    graDibujo.setFont(fonFont);
                    graDibujo.setColor(Color.black);
                }
            }
            else {
                // Display message if images are not loaded
                graDibujo.drawString("No se cargo la imagen..", 20, 20);
            }    
        }         
    }

    /**
     * keyTyped
     * 
     * Metodo sobrescrito de la interface <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar una 
     * tecla que no es de accion.
     * 
     * @param keyEvent es el <code>KeyEvent</code> que se genera en al 
     * presionar.
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Not supported in this application
    }

    /**
     * keyPressed
     * 
     * Metodo sobrescrito de la interface <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al dejar presionada
     * alguna tecla.
     * 
     * @param keyEvent es el <code>KeyEvent</code> que se genera al presionar.
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        // Verify that game is not paused
        if (!bPause) {
            // Move bar
            if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                satPlayer.setBehavior(Saturn086_Object.eBehavior.MOVE_RIGHT);
            }
            else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                satPlayer.setBehavior(Saturn086_Object.eBehavior.MOVE_LEFT);
            }
        }
    }

    /**
     * keyReleased
     * Metodo sobrescrito de la interface <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al soltar la tecla.
     * 
     * @param keyEvent es el <code>KeyEvent</code> que se genera en al soltar.
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // Change pause state
        if (keyEvent.getKeyCode() == 'P') {
            bPause = !bPause;
        }
        // end game
        if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {  
            bGameOver = true;
            bLoading = true;
            bFirstTime = true;
            
            socMainMusic.stop();
            socMenuMusic.play();
        }
        // Open main menu
        if (keyEvent.getKeyCode() == 'M' && !bFirstTime) {
            bLoading = !bLoading;
            
            // Play main menu music
            if (bLoading) {
                socMainMusic.stop();
                socMenuMusic.play();
            }
            // Play main game music
            else {
                socMenuMusic.stop();
                socMainMusic.play();
            }
        }
        
        // open high score menu
        if (keyEvent.getKeyCode() == 'W') {
            // open jdialog
            try {
                JDialogScore.updateRanking(null,-1,
                        "puntuaciones.txt");
            } catch (IOException ioeError) {
                System.out.println("Hubo un error en lectura de archivo" + 
                        ioeError.toString());
            }
        }
        
        // Display instructions
        if(bLoading && keyEvent.getKeyCode() == 'H') {
            JOptionPane.showMessageDialog(this, "Breaking Bad\n\n"
                    + "You have found some anfetamine contraband\n"
                    + "produced by Walter. Help Hank to destroy\n "
                    + "them by throwing your handy ball at them.\n\n"
                    + "Move the bar with the left and right arrow\n"
                    + "keys. If your ball touches the ground (Which you\n"
                    + "don't like), you loose a life. When you start\n"
                    + "or loose a life, throw the ball to the left or\n"
                    + "the right by pressing \'R\' or \'L\'.\n\n"
                    + "Walter is tricky sometimes, so be careful with\n"
                    + "some bombs he has hidden in his cargo, if they\n"
                    + "touch the bar, you loose a life (and cry).\n\n"
                    + "Each anfetamine destroyed grants you 10 points\n"
                    + "Each one has a number representing its resistance,\n"
                    + "in other words, the number of hits required to\n"
                    + "destroy it. A PINK anfetamine grants you a life,\n"
                    + "a BLUE anfetamine accelerates your throws for 10 sec.,\n"
                    + "a GREEN anfetamine is a glitchy bomb that will explode\n"
                    + "on contact and destroy all surrounding anfetamines.\n\n"
                    + "The game ends when all lives are lost or all\n"
                    + "anfetamines are destroyed. You can exit the game at\n"
                    + "any time with the \'ESC\' key.", "Instructions",
                    JOptionPane.PLAIN_MESSAGE, imiImageInstructions);
        }
        
        // Start new game
        if (keyEvent.getKeyCode() == 'S' && bLoading) {
            if (bFirstTime) {
                bFirstTime = false;
                bGameOver = false;
                bBluePower = false;
            }
            
            // Reset object's attributes to their default values
            newGame();            
            bLoading = false;
            
            // Play main menu music
            if (bLoading) {
                socMainMusic.stop();
                socMenuMusic.play();
            }
            // Play main game music
            else {
                socMenuMusic.stop();
                socMainMusic.play();
            }
        }
        // Verify that game is not paused
        if (!bPause) {
            // Check keyboard
            if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                satPlayer.setBehavior(Saturn086_Object.eBehavior.STOP_RIGHT);
            }
            else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                satPlayer.setBehavior(Saturn086_Object.eBehavior.STOP_LEFT);
            }
        }
        if (satBall.getBehavior() == Saturn086_Object.eBehavior.STOP_RIGHT) {
            // Start ball movement to the left
            if (keyEvent.getKeyCode() == 'L') {  
                satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_UP);
            }
            // Start ball movement to the right
            else if (keyEvent.getKeyCode() == 'R') {  
                satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_RIGHT);
            }
        }
    }
    
    /**
     * playerNewGame
     * 
     * Used to establish players default values
     */
    public void playerNewGame() {
        satPlayer.setImageIcon("bar.png", 100, 30);
        satPlayer.setBehavior(Saturn086_Object.eBehavior.STOP_RIGHT);
        satPlayer.setISpeed(8);
        satPlayer.setILives(5);
        satPlayer.setIScore(0);
        satPlayer.setIPosY(iJFrameHeight - satPlayer.getHeight() - 10);
        satPlayer.setIPosX((iJFrameWidth / 2) - (satPlayer.getWidth() / 2));
    }
    
    /**
     * blocksNewGame
     * 
     * Used to establish the blocks default value
     * 
     * @param iBlockR is an <code> int </code> with the row quantity
     * @param iBlockC is an <code> int </code> with the column quantity
     */
    public void blocksNewGame(int iBlockR, int iBlockC) {
        for (int iI = 0; iI < iBlockR; iI ++) {
            for (int iJ = 0; iJ < iBlockC; iJ ++) {
                satArrBlocks[iI][iJ] = new Saturn086_DefaultEnemy();
                satArrBlocks[iI][iJ].setImageIcon("orange.png", 50, 30);
                satArrBlocks[iI][iJ].setBehavior
                        (Saturn086_Object.eBehavior.STOP_RIGHT);
                satArrBlocks[iI][iJ].setISpeed(0);
                satArrBlocks[iI][iJ].setIPosX(iJ * 
                        satArrBlocks[iI][iJ].getWidth() + 8);
                satArrBlocks[iI][iJ].setIPosY(iI * 
                        satArrBlocks[iI][iJ].getHeight() + 64);
                satArrBlocks[iI][iJ].setStrName("NORMAL");
                satArrBlocks[iI][iJ].setBPaint(false);
            }
        }
    }
    
    /**
     * numberOfBlocks
     * 
     * used to select number of blocks to be displayed
     * 
     * @return an <code> int </code> with the number 
     */
    public int numberOfBlocks() {
        // The number depends on the current level
       int iCant = iLevel * 4; 
       
       if (iCant > (iBlockR * iBlockC)) {
            iCant = (iBlockR * iBlockC);
       }
       
       return iCant;
    }
    
    /**
     * randomFigure
     * 
     * Select which blocks to show
     * 
     * @param iBlockR is an <code> int </code> with the row quantity
     * @param iBlockC is an <code> int </code> with the column quantity
     */
    public void randomFigure(int iBlockR, int iBlockC) {
        int iR;
        int iC;
        
        // Generate random coordinate
        for (int iK = 0; iK < numberOfBlocks() - (satPlayer.getIScore() / 2); 
                iK ++) {
            iR = (int) (Math.random() * iBlockR);
            iC = (int) (Math.random() * iBlockC);
            satArrBlocks[iR][iC].setBPaint(true);
        }
    }
    
    /**
     * ballNewGame
     * 
     * Used to establish the ball's default values
     * 
     * @param iDifficulty is an <code> int </code> with the current difficulty
     */
    public void ballNewGame(int iDifficulty) {
        satBall.setImageIcon("ball.gif", 30, 30);
        satBall.setBehavior(Saturn086_Object.eBehavior.STOP_RIGHT);
        satBall.setISpeed((2 * iDifficulty) + 1);
        satBall.setStrName("Ball");
        ballStartPos();
    }
    
    /**
     * ballStartPos
     * 
     * Used to determine where the ball appears (Follows the bar)
     */
    public void ballStartPos() {
        satBall.setIPosX(satPlayer.getIPosX() + (satPlayer.getWidth() / 2) - 
                (satBall.getWidth() / 2));
        satBall.setIPosY(satPlayer.getIPosY() - (satBall.getHeight()));
    }
    
    /**
     * paintBlocks
     * 
     * Paints the specified blocks
     * 
     * @param graDibujo is the image where the blocks are painted
     * @param iBlockR is an <code> int </code> with the row quantity
     * @param iBlockC is an <code> int </code> with the column quantity
     */
    public void paintBlocks(Graphics graDibujo, int iBlockR, int iBlockC) {
        for (int iI = 0; iI < iBlockR; iI ++) {
            for (int iJ = 0; iJ < iBlockC; iJ ++) {
                if(satArrBlocks[iI][iJ].getBPaint()) {
                    satArrBlocks[iI][iJ].paint(graDibujo, this);
                    showBlockLives(graDibujo, iI, iJ);
                }
            }
        }
    }
    
    /**
     * paintBombs
     * 
     * Paint a bomb when it starts to fall
     * 
     * @param graDibujo  is the image where the bombs are painted
     */
    public void paintBombs(Graphics graDibujo) {
        for (int iI = 0; iI < iCantBombs; iI ++) {
            if(satArrBombs[iI].getBPaint()) {
                satArrBombs[iI].paint(graDibujo, this);
            }
        }
    }
    
    /**
     * intersectBombs
     * 
     * Method used to check when a bomb touches the bar
     */
    public void intersectBombs() {
        for (int iI = 0; iI < iCantBombs; iI ++) {
            if (satPlayer.intersects(satArrBombs[iI])) {
                satArrBombs[iI].setBehavior(
                        Saturn086_Object.eBehavior.STOP_RIGHT);
                satArrBombs[iI].setIPosX(-100);
                satArrBombs[iI].setIPosY(-100);
                //iWidthBar = iWidthBar - (iWidthBar / 8);
                satPlayer.setILives(satPlayer.getILives() - 1);
                socExplosion.play();
                //satPlayer.resizeImageIcon(iWidthBar, iHeightBar);
                //satPlayer.marginContainer(iWidthBar, iHeightBar);
                
            }
        }
    }
    
    /**
     * intersectBlocks
     * 
     * Method used to check when the ball touches a block
     * 
     * @param iBlockR is an <code> int </code> with the row quantity
     * @param iBlockC is an <code> int </code> with the column quantity
     */
    public void intersectBlocks(int iBlockR, int iBlockC) {
        for (int iI = 0; iI < iBlockR; iI ++) {
            for (int iJ = 0; iJ < iBlockC; iJ ++) {
                // Eliminate block if lives get to 0
                if (satArrBlocks[iI][iJ].intersects(satBall)) {
                                    
                    if (satArrBlocks[iI][iJ].getBPaint()) {
                        // Win points

                        satPlayer.setIScore(satPlayer.getIScore() + 10);

                        if (satArrBlocks[iI][iJ].getStrName().equals("NORMAL")
                                || satArrBlocks[iI][iJ].getStrName().
                                        equals("BOMBER")) {
                            
                            socBlock.play();
                        }
                        
                        // add animation to linked list
                        aniAux = new Animacion();
                        // edit animation contents
                        aniAux.sumaCuadro(imaBlock0, 100);
                        aniAux.sumaCuadro(imaBlock1, 100);
                        aniAux.sumaCuadro(imaBlock2, 100);
                        aniAux.setPosX(satArrBlocks[iI][iJ].getIPosX());
                        aniAux.setPosY(satArrBlocks[iI][iJ].getIPosY());
                        aniAux.setTiempoActual(System.currentTimeMillis());
                        lklAnimations.add(aniAux);

                        if (satArrBlocks[iI][iJ].getILives() > 0) {
                            checkPowerUps(iI, iJ);
                            satArrBlocks[iI][iJ].setILives(
                                    satArrBlocks[iI][iJ].getILives() - 1); 
                        }
                        // Eliminate block
                        if (!satArrBlocks[iI][iJ].getStrName().
                                equals("NORMAL")) {
                            satArrBlocks[iI][iJ].setILives(0);
                            
                            if(!bShowFace) {
                                iFaceIndex = (int)(Math.random() * 4);
                                satArrFaces[iFaceIndex].setBPaint(true);
                                bShowFace = true;
                            }
                        }
                    }
                    // If block is visible
                    if (satArrBlocks[iI][iJ].getBPaint()) { 
                        // Ball bounces on bottom part
                        if (satArrBlocks[iI][iJ].intersectsBottom(satBall)) {
                            bounceTop();
                        }
                        // Ball bounces on upper part
                        if (satArrBlocks[iI][iJ].intersectsTop(satBall)) {
                            bounceBottom();
                        }
                        // Ball bounces on left part
                        if (satArrBlocks[iI][iJ].intersectsLeft(satBall)) {
                            bounceRight();
                        }
                        // Ball bounces on right part
                        if (satArrBlocks[iI][iJ].intersectsRight(satBall)) {
                            bounceLeft();
                        }
                    }
                }
                // Upon hit dissapear block
                if (satArrBlocks[iI][iJ].getILives() <= 0 && 
                        satArrBlocks[iI][iJ].getBPaint()) {
                    satPlayer.setIScore(satPlayer.getIScore() + 10);
                    satArrBlocks[iI][iJ].setBPaint(false);
                }   
            }
        }
    }
        
    /**
     * intersectPlayer
     * 
     * Used to check when the ball touches the player
     */
    public void intersectPlayer() {
        // Ball bounces on upper part
        if (satPlayer.intersectsTop(satBall)) {
            bounceBottom();
        }
        // Ball bounces on left part
        if (satPlayer.intersectsLeft(satBall)) {
            bounceRight();
        }
        // Ball bounces on right part
        if (satPlayer.intersectsRight(satBall)) {
            bounceLeft();
        }
    }
    
    /**
     * selectBlockLives
     * 
     * Assign each block a life from 1 to 3
     * 
     * @param iBlockR is an <code> int </code> with the row quantity
     * @param iBlockC is an <code> int </code> with the column quantity
     */
    public void selectBlockLives(int iBlockR, int iBlockC) {
        // Number of lives for each block generation
        int iLives;
                
        for (int iI = 0; iI < iBlockR; iI ++) {
            for (int iJ = 0; iJ < iBlockC; iJ ++) {
                // Generate a life between 1 amd 3
                iLives = (int) (Math.random() * 3) + 1;
                satArrBlocks[iI][iJ].setILives(iLives);
            }
        }
    }
    
    /**
     * showBlockLives
     * 
     * Used to show each block's life
     * 
     * @param graDibujo is the image where the lives are painted
     * @param iR is an <code> int </code> with the row
     * @param iC is an <code> int </code> with the column
     */
    public void showBlockLives(Graphics graDibujo, int iR, int iC) {
        graDibujo.drawString(Integer.toString(satArrBlocks[iR][iC].getILives()),
                satArrBlocks[iR][iC].getIPosX() + 
                        (satArrBlocks[iR][iC].getWidth() / 2),
                satArrBlocks[iR][iC].getIPosY() +
                        (satArrBlocks[iR][iC].getHeight() / 2));
    }
    
    /**
     * createBombs
     * 
     * Set the bomb's default values
     */
    public void createBombs() {
        for(int iI = 0; iI < iCantBombs; iI ++) {
            satArrBombs[iI] = new Saturn086_DefaultEnemy();
            satArrBombs[iI].setBehavior(Saturn086_Object.eBehavior.STOP_RIGHT);
            satArrBombs[iI].setImageIcon("Bomb.png", 30, 30);
            satArrBombs[iI].setISpeed(6);
            satArrBombs[iI].setStrName("BOMB");
            satArrBombs[iI].setBPaint(false);
        }
    }
    
    /**
     * assignBombs
     * 
     * Assign bomb to a block
     * 
     * @param iR is an <code> int </code> with the row
     * @param iC is an <code> int </code> with the column
     * @param iI is the index of the bombs
     */
    public void assignBombs(int iR, int iC, int iI) {
        satArrBombs[iI].setIPosX(satArrBlocks[iR][iC].getIPosX());
        satArrBombs[iI].setIPosY(satArrBlocks[iR][iC].getIPosY());
    }
    
    /**
     * moveBombs
     * 
     * Used to move the bombs
     */
    public void moveBombs() {
        for (int iI = 0; iI < iCantBombs; iI ++) {
            satArrBombs[iI].move();
        }
    }
    
    /**
     * selectPowerUps
     * 
     * Method used to assign some block bonuses
     * 
     * @param iBlockR is an <code> int </code> with the row quantity
     * @param iBlockC is an <code> int </code> with the column quantity
     */
    public void selectPowerUps(int iBlockR, int iBlockC) {
        // Number of bonus generation
        int iBonus = (int) ((Math.random() * 3) + 1);
        // Type of bonus
        int iBonusID;
        int iR; // Number of row
        int iC; // Number of column
        
        for(int iI = 0; iI < iBonus; iI++) {
            iR = (int) (Math.random() * iBlockR); // Row number
            iC = (int) (Math.random() * iBlockC); // Column number
            iBonusID = (int) (Math.random() * 3); // The bonus for that block
            
            switch (iBonusID) {
                case 0: { // Block gives an extra life when eliminated
                    satArrBlocks[iR][iC].setStrName("LIFE");
                    satArrBlocks[iR][iC].setImageIcon("pink.png", 50, 30);
                    break;
                }
                case 1: { // Block eliminates surrounding blocks when eliminated
                    satArrBlocks[iR][iC].setStrName("BOOM");
                    satArrBlocks[iR][iC].setImageIcon("green.png", 50, 30);
                    break;
                }
                case 2: { // Block reduces the ball's speed by 1 when eliminated
                    satArrBlocks[iR][iC].setStrName("SPEED");
                    satArrBlocks[iR][iC].setImageIcon("blue.png", 50, 30);
                    break;
                }
            }
        }
        
        // Assign bomb to block
        for(int iI = 0; iI < iCantBombs; iI++) {
            iR = (int) (Math.random() * iBlockR); // Row number
            iC = (int) (Math.random() * iBlockC); // Column number
            
            if (satArrBlocks[iR][iC].getStrName().equals("NORMAL")) {
                satArrBlocks[iR][iC].setStrName("BOMBER");
                assignBombs(iR, iC, iI);
            }
            else {
                iI--; // Make another round
            }
        }
    }
    
    /**
     * checkWin
     * 
     * Used to check if the player has passed the level
     * 
     * @return a <code> boolean </code> if the player won 
     */
    public boolean checkWin() {
        // Used to check if the player has won
        boolean bWin = true;
        // If a single block is visible the level hasn't finished        
        for (int iI = 0; iI < iBlockR; iI ++) {
            for (int iJ = 0; iJ < iBlockC; iJ ++) {
                if (satArrBlocks[iI][iJ].getBPaint())
                bWin = false;
            }
        }
        
        return bWin;
    }
    
    /**
     * checkPowerUps
     * 
     * See if current block has a powerup
     * 
     * @param iR is an <code> int </code> with the row
     * @param iC is an <code> int </code> with the column
     */
    public void checkPowerUps(int iR, int iC) {
        
        // Used for the green bonus
        int iR2 = iR;
        int iI = iC;
        int iJ;
        int iK;
        int iILimit = iC;
        
        bShowFace = true;
        
        // Only if it is not a normal block
        if(!satArrBlocks[iR][iC].getStrName().equals("NORMAL")) {
            switch(satArrBlocks[iR][iC].getStrName()) {
                case "LIFE": { // Add life to player
                    satPlayer.setILives(satPlayer.getILives() + 1);
                    socLife.play();
                }
                case "BOOM": { // Destroy surrounding blocks
                    if(iR != 0) { // If not in upper row
                        iR2--;
                    }
                    if(iC != 0) { // If not in left side
                        iI--;
                    }
                    if(iC != iBlockC - 1) { // If not in right side
                        iILimit++;
                    }
                    socExplosion.play();
                    iJ = iI;
                    iK = iI;
                    
                    // Destroy all blocks in upper row
                    for (; iI <= iILimit; iI ++) {
                       if (satArrBlocks[iR2][iI].getBPaint()) {
                           satArrBlocks[iR2][iI].setBPaint(false);
                           satPlayer.setIScore(satPlayer.getIScore() + 10);
                       }
                    }
                    
                    if(iR != 0) { // If not in lower row
                        iR2++;
                    }
                    // Destroy all blocks in lower row
                    for (; iJ <= iILimit; iJ ++) {
                       if (satArrBlocks[iR2][iJ].getBPaint()) {
                           satArrBlocks[iR2][iJ].setBPaint(false);
                           satPlayer.setIScore(satPlayer.getIScore() + 10);
                       }
                    }
                    
                    // Destroy block on the left
                    if (satArrBlocks[iR][iK].getBPaint()) {
                           satArrBlocks[iR][iK].setBPaint(false);
                           satPlayer.setIScore(satPlayer.getIScore() + 10);
                    }
                    
                    // Destroy blockon the right
                    if (satArrBlocks[iR][iILimit].getBPaint()) {
                           satArrBlocks[iR][iILimit].setBPaint(false);
                           satPlayer.setIScore(satPlayer.getIScore() + 10);
                    }
                    
                    break;
                }
                case "SPEED": { // Decrease ball speed
                    satBall.setISpeed(satBall.getISpeed() + 2);
                    bBluePower = true;
                    socSpeed.play();
                    break;
                }
                case "BOMBER": { // If block has a bomb, it drops it
                    for (int iL = 0; iL < iCantBombs; iL ++) {
                        if (satArrBlocks[iR][iC].intersects(satArrBombs[iL])) {
                            satArrBombs[iL].setBehavior(
                                    Saturn086_Object.eBehavior.STOP_DOWN);
                            satArrBombs[iL].setBPaint(true);
                        }
                    }
                    socExplosion.play();
                    break;
                }
            }
        }
    }
}
