/**
 * Main
 * 
 * Starting point for game execution
 * 
 * @author Marco Antonio Peyrot A00815262
 * @author Mario Sergio Fuentes AA01036141
 * @version 1.0
 * @date 23/02/2015
 */

package homework5;

import javax.swing.JFrame;

public class Main {
    /**
     * main
     * 
     * First method to be executed when the program executes
     * 
     * @param args is a <code> String[] </code> with the command line arguments
     */
    public static void main(String[] args) {
        // Create new game
        MainGame mgaGame = new MainGame();
        // Activate game visualization
        mgaGame.setVisible(true);
        // Close game when the JFrame closes
        mgaGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
