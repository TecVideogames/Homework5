/**
 * MainGame
 * 
 * Main class for game implementation
 * 
 * @author Marco Antonio Peyrot A00815262
 * @author Mario Sergio Fuentes AA01036141
 * @version 1.0
 * @date 23/02/2015
 */

package homework5;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.JFrame;

public class MainGame extends JFrame implements Runnable, KeyListener {

    // Game variable declaration
    private boolean bPause;
    private boolean bGameOver;
    private boolean bLoading;
    private int iJFrameWidth;
    private int iJFrameHeight;
    private int iBlockR;
    private int iBlockC;
    private int iDifficulty;
    private int iCounterLoading;
    private Image imaImageJFrame;
    private Image imaImageGameOver;
    private Saturn086_Player satPlayer;
    private Saturn086_DefaultEnemy[][] satArrBlocks;
    private Saturn086_DefaultEnemy satBall;
    private Graphics graGraficsJFrame; // Graphic objects of the image
    
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
        iJFrameHeight = 600;
        iJFrameWidth = 816;
        iBlockR = 3;
        iBlockC = 16;
        iDifficulty = 1;
        iCounterLoading = 100;
        // Set JFrame size
        setSize(iJFrameWidth, iJFrameHeight);
        
        // Objects inicialization
        satArrBlocks = new Saturn086_DefaultEnemy[iBlockR][iBlockC];
        satPlayer = new Saturn086_Player();
        satBall = new Saturn086_DefaultEnemy();
        
        // New game player attributes inicialization
        playerNewGame();
        // New game blocks attributes inicialization
        blocksNewGame(iBlockR, iBlockC);
        // New game ball attributes inicialization
        ballNewGame(iDifficulty);
        // Add bonuses
        selectPowerUps(iBlockR, iBlockC);
        // Add lives to each block
        selectBlockLives(iBlockR, iBlockC);
        
        //Adds to the JFrame the capability to hear keyboard events
	addKeyListener(this);
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
     * run
     * 
     * Overwritten method form class <code>Thread</code>.<P>
     * In this method the main thread is executed, it contains the game's
     * instructions
     */
    @Override
    public void run() {        
        // The Game cycle never stops until the game is exited
        while (true) { 
            // if game is not paused or lost continue with normal game execution
            if (!bPause && !bGameOver && !bLoading) {
                actualize();
                checkCollision();
            }
            else {
                if (bLoading) {
                    iCounterLoading--;
                    if(iCounterLoading == 0) {
                        bLoading = false;
                    }
                }
                // Code for menu SERGIO VAS AQUI
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
        satPlayer.move();
        satBall.move();
    }
    
    // Ball bounces on left side
    public void bounceLeft() {
        if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_UP) {
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_RIGHT);
        }
        else if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_LEFT) {
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_DOWN);
        }
    }
    
    // Ball bounces on right side
    public void bounceRight() {
        if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_RIGHT) {
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_UP);
        }
        else if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_DOWN) {
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_LEFT);
        }    
    }
    
    // Ball bounces on top side
    public void bounceTop() {
        if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_UP) {
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_LEFT);
        }
        else if(satBall.getBehavior() == Saturn086_Object.eBehavior.MOVE_RIGHT){
            satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_DOWN);
        }
    }
    
    // Ball bounces on bottom side
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
        if(satBall.leftMarginCheck()) {
            bounceLeft();
        }
        // Ball bounces on right margin
        if(satBall.rightMarginCheck(iJFrameWidth)) {
            bounceRight();
        }
        // Ball bounces on upper margin
        if(satBall.topMarginCheck()) {
            bounceTop();
        }
        // Ball bounces on bottom margin
        if(satBall.bottomMarginCheck(iJFrameHeight)) {
            bounceBottom();
            // Player looses 1 live
            satPlayer.setILives(satPlayer.getILives() - 1);
        }
        
        // Collision with blocks
        intersectBlocks(iBlockR, iBlockC);
        // Collision with player
        intersectPlayer();
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
        URL urlImageBG = this.getClass().getResource("GameBackground.jpg");
        
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
        if (bGameOver) {
            // se dibuja imagen de fin de juego
            graDibujo.drawImage(imaImageGameOver, 210, 100, this);
        }
        else {
            // if object images are loaded
            if (satPlayer.getImageIcon() != null && satArrBlocks != null &&
                    !bLoading) {
                satPlayer.paint(graDibujo, this);
                paintBlocks(graDibujo, iBlockR, iBlockC);
                satBall.paint(graDibujo, this);
                // Paint player's lives
                graDibujo.drawString(String.valueOf("Lives: " + 
                        satPlayer.getILives()), 10, 50);
                // Paint player's points
                graDibujo.drawString(String.valueOf("Points: " + 
                        satPlayer.getIScore()), 100, 50);
            } // Display message if images are not loaded
            else {
                // Display message while image uploads
                graDibujo.drawString("No se cargo la imagen..", 20, 20);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Not supported in this application
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        // se verifica que el juego no este pausado
        if (!bPause) {
            // se checa presion de teclas y se actualize casilla correspondiente
            if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                satPlayer.setBehavior(Saturn086_Object.eBehavior.MOVE_RIGHT);
            }
            else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                satPlayer.setBehavior(Saturn086_Object.eBehavior.MOVE_LEFT);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // se cambia el estado de pausa
        if (keyEvent.getKeyCode() == 'P') {
            bPause = !bPause;
        }
        // terminar juego
        if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {  
            bGameOver = true;
        }
        // se verifica que el juego no este pausado
        if (!bPause) {
            // se checa presion de teclas y se actualize casilla correspondiente
            if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                satPlayer.setBehavior(Saturn086_Object.eBehavior.STOP_RIGHT);
            }
            else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                satPlayer.setBehavior(Saturn086_Object.eBehavior.STOP_LEFT);
            }
        }
    }
    
    public void playerNewGame() {
        satPlayer.setImageIcon("bar.png", 100, 30);
        satPlayer.setBehavior(Saturn086_Object.eBehavior.STOP_RIGHT);
        satPlayer.setISpeed(8);
        satPlayer.setILives(5);
        satPlayer.setIScore(0);
        satPlayer.setIPosY(iJFrameHeight - satPlayer.getHeight() - 10);
        satPlayer.setIPosX((iJFrameWidth / 2) - (satPlayer.getWidth() / 2));
    }
    
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
            }
        }
    }
    
    public void ballNewGame(int iDifficulty) {
        satBall.setImageIcon("ball.gif", 30, 30);
        satBall.setBehavior(Saturn086_Object.eBehavior.MOVE_UP);
        satBall.setISpeed((2 * iDifficulty) + 2);
        satBall.setStrName("Ball");
        satBall.setIPosX(iJFrameWidth / 2);
        satBall.setIPosY(iJFrameHeight / 2);
    }
    
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
    
    public void intersectBlocks(int iBlockR, int iBlockC) {
        for (int iI = 0; iI < iBlockR; iI ++) {
            for (int iJ = 0; iJ < iBlockC; iJ ++) {
                // Eliminate block if lives get to 0
                if (satArrBlocks[iI][iJ].intersects(satBall)) {
                    if (satArrBlocks[iI][iJ].getBPaint()) {
                        // Win points
                        if (satArrBlocks[iI][iJ].getILives() > 0) {
                            checkPowerUps(iI, iJ);
                            satArrBlocks[iI][iJ].setILives(
                                    satArrBlocks[iI][iJ].getILives() - 1); 
                        }
                        
                        if (!satArrBlocks[iI][iJ].getStrName().
                                equals("NORMAL")) {
                            satArrBlocks[iI][iJ].setILives(0);
                        }
                    }
                    
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
                
                if (satArrBlocks[iI][iJ].getILives() <= 0) {
                    satPlayer.setIScore(satPlayer.getIScore() + 10);
                    satArrBlocks[iI][iJ].setBPaint(false);
                }
                
            }
        }
    }
    
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
    
    public void showBlockLives(Graphics graDibujo, int iR, int iC) {
        graDibujo.drawString(Integer.toString(satArrBlocks[iR][iC].getILives()),
                satArrBlocks[iR][iC].getIPosX() + 
                        (satArrBlocks[iR][iC].getWidth() / 2),
                satArrBlocks[iR][iC].getIPosY() +
                        (satArrBlocks[iR][iC].getHeight() / 2));
    }
    
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
    }
    
    public void checkPowerUps(int iR, int iC) {
        int iR2 = iR;
        int iI = iC;
        int iJ;
        int iK;
        int iILimit = iC;
        
        if(!satArrBlocks[iR][iC].getStrName().equals("NORMAL")) {
            switch(satArrBlocks[iR][iC].getStrName()) {
                case "LIFE": { // Add life to player
                    satPlayer.setILives(satPlayer.getILives() + 1);
                    break;
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
                    satBall.setISpeed(satBall.getISpeed() - 1);
                    break;
                }
            }
        }
    }
}
