package homework5;

/**
 * Saturn086_Object
 * 
 * Abstract superclass of all Saturn086 defined objects for games
 * 
 * @author Marco Antonio Peyrot A0815262
 * @version 1.0
 */
public abstract class Saturn086_Object {
    // Object's basic attributes
    protected int iPosX; // Object's position in the x-axis
    protected int iPosY; // Object's position in the y-axis
    protected eBehavior behavior; //Object's behavior
    // Object's behavioral attributes
    public static enum eBehavior { // Possible object's behavior
        MOVE_UP, MOVE_DOWN, MOVE_RIGHT, MOVE_LEFT, STOP_UP,
        STOP_DOWN, STOP_RIGHT, STOP_LEFT
    }
    
    /**
     * setIPosX
     * 
     * Set the object's x-coordinate
     * 
     * @param iPosX is an <code> int </code> that represents
     * the desired coordinate
     */
    public void setIPosX(int iPosX) {
        this.iPosX = iPosX;
    }
    
    /**
     * getIPosX
     * 
     * Get the object's x-coordinate
     * 
     * @return an <code> int </code> that represents 
     * the object's current x-coordinate
     */
    public int getIPosX() {
        return this.iPosX;
    }
    
    /**
     * setIPosY
     * 
     * Set the object's y-coordinate
     * 
     * @param iPosY is an <code> int </code> that represents
     * the desired coordinate
     */
    public void setIPosY(int iPosY) {
        this.iPosY = iPosY;
    }
    
    /**
     * getIPosY
     * 
     * Get the object's y-coordinate
     * 
     *@return an <code> int </code> that represents 
     * the object's current y-coordinate
     */
    public int getIPosY() {
        return this.iPosY;
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
    public abstract boolean intersects(Object objParameter);
}
