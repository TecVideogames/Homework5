/**
 * Saturn086_Player
 * 
 * Class that extends Saturn086_Character and implements all necessary functions
 * for a human user to interact in a game
 * 
 * @author Marco Antonio Peyrot A0815262
 * @version 1.0
 */

package homework5;

import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Saturn086_Player extends Saturn086_Character{

    // Object attributes
    private int iScore; // Player's score which are gained or lost as game runs
    
    /**
     * Saturn086_Player
     * 
     * Constructor
     */
    Saturn086_Player() {
        setStrName("Saturn086_Player");
        setIPosX(0);
        setIPosY(0);
        setILives(3);
        setISpeed(0);
        setImageIcon("ImageNotFound.jpg", 100, 100);
        setBehavior(eBehavior.STOP_RIGHT);
        setIScore(0);
        setBPaint(true);
    }
    
    /**
     * setIScore
     * 
     * Method for changing the player's score
     * 
     * @param iScore is an <code> int </code> with the new score
     */
    public void setIScore(int iScore) {
        this.iScore = iScore;
    }
    
    /**
     * getIScore
     * 
     * Method used to get the player's current score
     * 
     * @return an <code> int </code> with the score
     */
    public int getIScore() {
        return iScore;
    }
    
    /**
     * move
     * d
     * Method used for AI and keyboard movement
     */
    @Override
    public void move() {
        switch (behavior) {
            case MOVE_LEFT: { // Move left iSpeed pixels
                setIPosX(getIPosX() - iSpeed);
                break;    
            }
            case MOVE_RIGHT: { // Move right iSpeed pixels
                setIPosX(getIPosX() + iSpeed);
                break;    	
            }
        }
    }

    /**
     * moveMouse
     * 
     * Method used for mouse movement
     * 
     * @param iPosX is an <code> int </code> with the pointer's x-coordinate
     * @param iPosY is an <code> int </code> with the pointer's y-coordinate
     */
    @Override
    public void moveMouse(int iPosX, int iPosY) {
        // Not defined for this application
    }

    /**
     * pointerInside
     * 
     * Method that checks if mouse pointer is inside this object
     * 
     * @param iPosX is an <code> int </code> with the pointer's x-coordinate
     * @param iPosY is an <code> int </code> with the pointer's y-coordinate
     * @return a <code> boolean </code> if pointer is inside this object
     */
    @Override
    public boolean pointerInside(int iPosX, int iPosY) {
        // Create container figure
        Ellipse2D.Float ellThis = new Ellipse2D.Float(this.getIPosX(),
                this.getIPosY(), this.getWidth(), this.getHeight());
        
        return ellThis.contains(iPosX, iPosY);
    }

    /**
     * startPos
     * 
     * Method that determines where the object instance 
     * must appear in the applet
     * 
     * @param iWidth is an <code> int </code> with the applet's width
     * @param iHeight is an <code> int </code> with the applet's height
     */
    @Override
    public void startPos(int iWidth, int iHeight) {
        // Not implemented in this application
    }

    /**
     * intersectsRight
     * 
     * Checks if this instance's right side intersects with another instace
     * 
     * @param objParameter is the <code> Object </code> to which we want to know
     * if an intersection occurs
     * @return a <code> boolean </code> if an intersection exists
     */
    @Override
    public boolean intersectsRight(Object objParameter) {
        // Check that the parameter is an Ball
        if (objParameter instanceof Saturn086_DefaultEnemy) {
            // Cast object to Saturn086_DefaultEnemy object
            Saturn086_DefaultEnemy satBall;
            satBall = (Saturn086_DefaultEnemy) objParameter;
            
            // Create margin line
            Line2D ln2This = new Line2D.Float(this.getIPosX() + this.getWidth(), 
                    this.getIPosY(), this.getIPosX() + this.getWidth(), 
                    this.getIPosY() + this.getHeight());         
            
            return satBall.getStrName().equals("Ball") && 
                    ln2This.intersects(satBall.getIPosX(), 
                    satBall.getIPosY(), satBall.getWidth(),
                    satBall.getHeight());
        }
        return false;
    }

    /**
     * intersectsLeft
     * 
     * Checks if this instance's left side intersects with another instace
     * 
     * @param objParameter is the <code> Object </code> to which we want to know
     * if an intersection occurs
     * @return a <code> boolean </code> if an intersection exists
     */
    @Override
    public boolean intersectsLeft(Object objParameter) {
        // Check that the parameter is an Ball
        if (objParameter instanceof Saturn086_DefaultEnemy) {
            // Cast object to Saturn086_DefaultEnemy object
            Saturn086_DefaultEnemy satBall;
            satBall = (Saturn086_DefaultEnemy) objParameter;
            
            // Create margin line
            Line2D ln2This = new Line2D.Float(this.getIPosX(), 
                    this.getIPosY(), this.getIPosX(), 
                    this.getIPosY() + this.getHeight());         
            
            return satBall.getStrName().equals("Ball") && 
                    ln2This.intersects(satBall.getIPosX(), 
                    satBall.getIPosY(), satBall.getWidth(),
                    satBall.getHeight());
        }
        return false;
    }

    /**
     * intersectsTop
     * 
     * Checks if this instance's top side intersects with another instace
     * 
     * @param objParameter is the <code> Object </code> to which we want to know
     * if an intersection occurs
     * @return a <code> boolean </code> if an intersection exists
     */
    @Override
    public boolean intersectsTop(Object objParameter) {
        // Check that the parameter is an Ball
        if (objParameter instanceof Saturn086_DefaultEnemy) {
            // Cast object to Saturn086_DefaultEnemy object
            Saturn086_DefaultEnemy satBall;
            satBall = (Saturn086_DefaultEnemy) objParameter;
            
            // Create margin line
            Line2D ln2This = new Line2D.Float(this.getIPosX(), 
                    this.getIPosY(), this.getIPosX() + this.getWidth(), 
                    this.getIPosY());         
            
            return satBall.getStrName().equals("Ball") && 
                    ln2This.intersects(satBall.getIPosX(), 
                    satBall.getIPosY(), satBall.getWidth(),
                    satBall.getHeight());
        }
        return false;
    }

    /**
     * intersectsBottom
     * 
     * Checks if this instance's bottom side intersects with another instace
     * 
     * @param objParameter is the <code> Object </code> to which we want to know
     * if an intersection occurs
     * @return a <code> boolean </code> if an intersection exists
     */
    public boolean intersectsBottom(Object objParameter) {
        // Not defined for this application
        return false;
    }

    /**
     * intersects
     * 
     * Checks if this instance intersects with another instance
     * 
     * @param objParameter is an <code> Object </code> to which the intersection
     * is checked
     * @return a <code> boolean </code> if the intersection occurs
     */
    @Override
    public boolean intersects(Object objParameter) {
        // Check that the parameter is an Bomb
        if (objParameter instanceof Saturn086_DefaultEnemy) {
            // Cast object to Saturn086_DefaultEnemy object
            Saturn086_DefaultEnemy satBomb;
            satBomb = (Saturn086_DefaultEnemy) objParameter;
            
            // Create container figure
            Rectangle elfThis = new Rectangle(this.getIPosX(), 
                    this.getIPosY(), this.getWidth(), this.getHeight());

            Area areThis = new Area(elfThis);
            
            return  satBomb.getStrName().equals("BOMB") && areThis.intersects(
                    satBomb.getIPosX(), satBomb.getIPosY(), satBomb.getWidth(),
                    satBomb.getHeight());
        }
        return false;
    }   
}
