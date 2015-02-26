package homework5;

/**
 * Pair
 *
 * Models parametrized pairs of values 
 *
 * @author Mario Sergio Fuentes Juarez
 * @version 1.0
 * @date 25/02/2015
 */
public class Pair<L,R> {
    
    // left value
    private L l;
    // right value
    private R r;
    
    /**
     * Pair
     * 
     * Constructor method
     * 
     * @param l is <code>L</code> left value
     * @param R is <code>R</code> right value
     * 
     */
    public Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }
    
    /**
     * getL
     * 
     * Access method for left value
     * 
     * @return l is pair's left value
     * 
     */
    public L getL() { 
        return l; 
    }
    
    /**
     * getR
     * 
     * Access method for right value
     * 
     * @return r is pair's right value
     * 
     */
    public R getR() { 
        return r; 
    }
    
    /**
     * setL
     * 
     * Modifier method for changing left value
     * 
     * @param l is L new value
     * 
     */
    public void setL(L l) { 
        this.l = l;
    }
    
    /**
     * setR
     * 
     * Modifier method for changing right value
     * 
     * @param r is R new value
     * 
     */
    public void setR(R r) { 
        this.r = r; 
    }
    
}