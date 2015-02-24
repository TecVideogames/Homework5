package homework5;

/**
 * Saturn086_VisualObject
 * 
 * Abstract class that extends Saturn086_Object and adds visual functionality 
 * 
 * @author Marco Antonio Peyrot A0815262
 * @version 1.0
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.net.URL;
import javax.swing.ImageIcon;

public abstract class Saturn086_VisualObject extends Saturn086_Object {
    // Object's visual attributes
    protected ImageIcon imiIcon; // Object's image icon which contains the image
    protected Image imaImage; // Object's image
    protected URL urlImage; // Object's image URL
    private boolean bPaint; // Enable object visualization
    
    /**
     * setBPaint
     * 
     * Set object's status for being painted
     * 
     * @param bPaint is a <code> boolean </code> which determines if the object
     * will be painted
     */
    public void setBPaint(boolean bPaint) {
        this.bPaint = bPaint;
    }
    
    /**
     * getBPaint
     * 
     * Return the object's status for being painted
     * 
     * @return a <code> boolean </code> with the bPaint flag
     */
    public boolean getBPaint() {
        return bPaint;
    }
    /**
     * setImageURL
     * 
     * Set object's image URL (Only for internal use)
     * 
     * @param strURL is a <code> String </code> which specifies the images' URL
     */
    private void setImageURL(String strURL) {
        urlImage = this.getClass().getResource(strURL);
    }
    
    /**
     * setImage
     * 
     * Set object's image (Only for internal use)
     */
    private void setImage() {
        imaImage = Toolkit.getDefaultToolkit().getImage(urlImage);        
    }
    
    /**
     * resizeImage
     * 
     * Resize object's image to a desired size
     * 
     * @param iSizeX is an <code> int </code> for the image's
     * new horizontal size
     * @param iSizeY is an <code> int </code> for the image's new vertical size
     */
    private void resizeImage(int iSizeX, int iSizeY) {
        imaImage = imaImage.getScaledInstance(iSizeX, iSizeY,
                java.awt.Image.SCALE_DEFAULT);
    }
    
    /**
     * setImageIcon
     * 
     * Set object's image icon
     * 
     * @param strURL is a <code> String </code> which specifies the image's URL
     * @param iSizeX is an <code> int </code> for the image's
     * new horizontal size
     * @param iSizeY is an <code> int </code> for the image's new vertical size
     */
    public void setImageIcon(String strURL, int iSizeX, int iSizeY) {
        // Use internal methods
        setImageURL(strURL);
        setImage();
        resizeImage(iSizeX, iSizeY);
        // Create object's image icon
        imiIcon = new ImageIcon(imaImage);
    }
    
    /**
     * getImageIcon
     * 
     * Get object's image icon
     * 
     * @return an <code> ImageIcon </code> which represents
     * the object's ImageIcon.
     */
    public ImageIcon getImageIcon() {
        return imiIcon;
    }
    
    /**
     * getImage
     * 
     * Get object's image
     * 
     * @return an <code> Image </code> with the object's image 
     */
    public Image getImage() {
        return imaImage;
    }
    
    /**
     * getImageURL
     * 
     * Get image's URL
     * 
     * @return an <code> URL </code> which refers to the image's URL
     */
    public URL getImageURL() {
        return urlImage;
    }
    
    /**
     * getWidth
     * 
     * Get the object's width according to its current ImageIcon
     * 
     * @return an <code> int </code> with the image icon's width
     */
    public int getWidth() {
        return imiIcon.getIconWidth();
    }
    
    /**
     * getHeight
     * 
     * Get the object's height according to its current ImageIcon
     * 
     * @return an <code> int </code> with the image icon's height
     */
    public int getHeight() {
        return imiIcon.getIconHeight();
    }
    
    /**
     * marginContainer
     * 
     * Makes this object remain inside the applet
     * 
     * @param iWidth is an <code> int </code> with the applet's width
     * @param iHeight is an <code> int </code> with the applet's height
     */
    public void marginContainer(int iWidth, int iHeight) {
        // Check for object's x-coordinate
        if (leftMarginCheck()) {
            setIPosX(0);
        }
        else if (rightMarginCheck(iWidth)) {
            setIPosX(iWidth - getWidth());
        }
        // Check for object's y-coordinate
        if (topMarginCheck()) {
            setIPosY(0);
        }
        else if (bottomMarginCheck(iHeight)) {
            setIPosY(iHeight - getHeight());
        }
    }
    
    /**
     * leftMarginCheck
     * 
     * Return true if object collides with the left applet margin
     * 
     * @return a <code> boolean </code> if collision occurs
     */
    public boolean leftMarginCheck() {
        return iPosX < 0;
    }
    
    /**
     * rightMarginCheck
     * 
     * Return true if object collides with the right applet margin
     * 
     * @param iWidth is an <code> int </code> with the applet's width
     * 
     * @return a <code> boolean </code> if collision occurs
     */
    public boolean rightMarginCheck(int iWidth) {
        return (iPosX + getWidth()) > iWidth;
    }
    
    /**
     * topMarginCheck
     * 
     * Return true if object collides with the top applet margin
     * 
     * @return a <code> boolean </code> if collision occurs
     */
    public boolean topMarginCheck() {
        return iPosY < 30;
    }
    
    /**
     * bottomMarginCheck
     * 
     * Return true if object collides with the bottom applet margin
     * 
     * @param iHeight is an <code> int </code> with the applet's height
     * @return a <code> boolean </code> if collision occurs
     */
    public boolean bottomMarginCheck(int iHeight) {
        return (iPosY + getHeight()) > iHeight;
    }
    
    /**
     * paint
     * 
     * Paints the object
     * 
     * @param graGraphic is an object from <code> Graphics </code> 
     * used for painting
     * @param imoObserver is an object from <code> ImageObserver </code>
     * used for painting the object in an applet
     */
    public void paint(Graphics graGraphic, ImageObserver imoObserver) {
        graGraphic.drawImage(getImage(), getIPosX(), getIPosY(), imoObserver);
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
    public abstract boolean intersectsRight(Object objParameter);
    
    /**
     * intersectsLeft
     * 
     * Checks if this instance's left side intersects with another instace
     * 
     * @param objParameter is the <code> Object </code> to which we want to know
     * if an intersection occurs
     * @return a <code> boolean </code> if an intersection exists
     */
    public abstract boolean intersectsLeft(Object objParameter);
    
    /**
     * intersectsTop
     * 
     * Checks if this instance's top side intersects with another instace
     * 
     * @param objParameter is the <code> Object </code> to which we want to know
     * if an intersection occurs
     * @return a <code> boolean </code> if an intersection exists
     */
    public abstract boolean intersectsTop(Object objParameter);
    
    /**
     * intersectsBottom
     * 
     * Checks if this instance's bottom side intersects with another instace
     * 
     * @param objParameter is the <code> Object </code> to which we want to know
     * if an intersection occurs
     * @return a <code> boolean </code> if an intersection exists
     */
    public abstract boolean intersectsBottom(Object objParameter);
}
