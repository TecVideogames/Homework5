package homework5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * JDialogScore
 *
 * Allows modeling <code>JDialosScore</code> objects
 * These are invoked while executing the function updateScore(),
 * which displays an updated score ranking in a JDialog from a record file name, 
 * a game score, and a base JFrame
 * 
 * @author Mario Sergio Fuentes Juarez
 * @version 1.0
 * @date 23/02/2015
 */
public class JDialogScore {
    // internal JDialog object
    private JDialog jdiDialog;
    // text area with score ranking
    private JTextArea jtaRanking; 
    // text field with player's name
    private JTextField jtfPlayerName;
    // confirmation button
    private JButton butOk;
    // file reader
    private static BufferedReader fileIn;
    // linked list with scores
    private static LinkedList lklScores;
        
    /**
     * JDialogScore
     * 
     * Private constructor method that builds a JDialogScore in order to 
     * handle score update
     * 
     * @param fraFrame is the base <code>JFrame</code> on top of which the
     * JDialog is built.
     * @param strScores is the <code>String</code> representing the 
     * list of scores.
     * @param strPlayerName is the <code>String</code> representing the 
     * instruction for writing or not the player's name
     * @param boolUpdate is the <code>boolean</code> indicating whether or
     * not the score list will be updated
     * @param strFileName is the <code>String</code> representing the file name.
     * @param iPos is the <code>integer</code> representing the position of the
     * list that will be updated with the player's score
     */
    private JDialogScore(JFrame fraFrame, String strScores, 
            String strPlayerName, boolean boolUpdate, 
                    final String strFileName, final int iPos){
        
        // jdialog initialization
        jdiDialog = new JDialog(fraFrame, "High Scores", true);
        jdiDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jdiDialog.setMinimumSize(new Dimension(200, 260));
        
        // initialization of score ranking text area
        jtaRanking = new JTextArea();
        jtaRanking.setText(strScores);
        jtaRanking.setEnabled(false);
  
        // initialization of player's name text field
        jtfPlayerName = new JTextField();
        jtfPlayerName.setText(strPlayerName);
        
        // visibility based on update flag
        jtfPlayerName.setEnabled(boolUpdate); 
        
        // initialization of confirmation button
        butOk = new JButton("OK");
        
        // add JComponents to JDialog
        jdiDialog.setLayout(new BorderLayout());
        jdiDialog.add("Center", jtaRanking);
        jdiDialog.add("North", jtfPlayerName);
        jdiDialog.add("South", butOk);
        jdiDialog.pack();
        
        // Listener for closing the JDialog and storing changes to file
        // when OK button is pressed
        butOk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // disposal of JDialog
                jdiDialog.dispose();
                // attempts saving changes to file
                try {
                    PrintWriter fileOut = new 
                            PrintWriter(new FileWriter(strFileName));
                    // each entry of the linked list is stored in file
                    for (int iI = 0; iI < lklScores.size(); iI ++) {
                        Pair<String,Integer> paiAux = 
                                (Pair<String,Integer>) lklScores.get(iI);
                        // if counter is equal to rank of player,
                        // store player's name from text field
                        if (iI == iPos) {
                            fileOut.println(jtfPlayerName.getText() + 
                                    "\n"+paiAux.getR());
                        }
                        else {
                            // otherwise, store players name as stated
                            // in linked list
                            fileOut.println(paiAux.getL()+"\n"+paiAux.getR());
                        }
                     }
                    fileOut.close();
                }
                catch (IOException ioError) {
                    System.out.println("Hubo un error en lectura de archivo" + 
                            ioError.toString());
                }
            }
        });
        
        // Listener for closing the JDialog and storing changes to file
        // when EXIT button is pressed (beheaves as OK button)
        jdiDialog.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) {
                // disposal of JDialog
                jdiDialog.dispose();
                // attempts saving changes to file
                try {
                    PrintWriter fileOut = new 
                            PrintWriter(new FileWriter(strFileName));
                    // each entry of the linked list is stored in file
                    for (int iI = 0; iI < lklScores.size(); iI ++) {
                        Pair<String,Integer> paiAux = 
                                (Pair<String,Integer>) lklScores.get(iI);
                        // if counter is equal to rank of player,
                        // store player's name from text field
                        if (iI == iPos) {
                            fileOut.println(jtfPlayerName.getText() + 
                                    "\n"+paiAux.getR());
                        }
                        else {
                            // otherwise, store players name as stated
                            // in linked list
                            fileOut.println(paiAux.getL()+"\n"+paiAux.getR());
                        }
                     }
                    fileOut.close();
                }
                catch (IOException ioError) {
                    System.out.println("Hubo un error en lectura de archivo" + 
                            ioError.toString());
                }
            }
        });    
        
    }
    /**
     * setVisible
     * 
     * Void method for activating JDialog's visibility
     * @param boolFlag indicating visibility status
     */
    public void setVisible(Boolean boolFlag){
        jdiDialog.setVisible(boolFlag);
    }

    /**
     * updateRanking
     * 
     * Method that updates score ranking given a:
     * 
     * @param fraFrame <code>JFrame</code> on top of which a JDialog is launched
     * @param iPoints <code>integer</code> representing scored points in game
     * @param strFileName <code>String</code> representing file's name
     * @throws IOException
     *  
     */
    public static void updateRanking(JFrame fraFrame, int iPoints, 
            String strFileName) throws IOException {
        
        try {
            // opening file
            fileIn = new BufferedReader(new FileReader(strFileName));
            
            // file reading variables
            String strName;
            String strPoints;
            int iPointsAux;
            
            // construction of linked list of pairs of names and scores 
            // (String,Integer)
            lklScores = new LinkedList< Pair<String,Integer> >();
            
            // read until end of file
            // first line reads name, second line reads points
            while ( (strName=fileIn.readLine()) != null ) {
                strPoints = fileIn.readLine();
                iPointsAux = Integer.parseInt(strPoints);
                // adds new pair to linked list
                lklScores.add(new Pair<String,Integer>(strName,iPointsAux));
            }
            
            int iPos; // tentative rank of score
            boolean boolUpdate; // boolean indicating update status

            // tentative position initialized at the end of linked list              
            iPos = lklScores.size() - 1;
            
            // counter iPos will start moving backwards, until reaching
            // the linked list's start position, or pointing at the 
            // first score greater than the player's score
            while (iPos >=0 &&
                    ((Pair<String,Integer>) (lklScores.get(iPos))).getR() 
                            < iPoints) {
                iPos --;
            }
            
            // if the first greater score is among the first 9 postions
            if (iPos <= 8 && iPoints != -1) {
                // player's score is in 1-10 range and ranking must be updated
                boolUpdate = true;
            }
            else {
                // otherwise, the player's score is out of the 10 first ones
                boolUpdate = false;
            }
            
            // strings containing JComponent values for the JDialog
            String strPlayerName;
            String strScores = "Position\tName\t\tScore\n";
            
            // if player's score will be added to ranking, insert its value
            // to the linked list
            if (boolUpdate && iPoints != -1) {
                lklScores.add(iPos + 1, 
                        new Pair<String,Integer>("********",iPoints));
                // if linked list size overflows, pop last element
                if (lklScores.size() > 10) {
                    lklScores.removeLast();
                }
                // update strPlayerName with congratulations message
                strPlayerName = "Congratulations! Write your name here.";
            }
            else {
                // udate strPlayerName with pitiful message
                strPlayerName = "High scores list.";
            }
            
            // integer indicating player's ranking
            int iRank = 1;
            
            for (Object objAux : lklScores) {
               // casting object
               Pair<String,Integer> paiAux = (Pair<String,Integer>) objAux;
               // concatenating ranking, score and name values to strScores
               strScores += "" + iRank + ".\t" + paiAux.getL() +
                       "\t\t" + paiAux.getR() + "\n";
               iRank ++;
            }
            // complete empty positions
            while (iRank < 11) {
                strScores += "" + iRank + ".\t" + "        " +
                       "\t\t" + "        " + "\n";
                iRank ++;
            }
            // construct JDialog based on the values that will be displayed in
            // the JComponents
            JDialogScore jdpDialog = new JDialogScore 
                    (fraFrame,strScores,strPlayerName,boolUpdate,strFileName,
                            iPos + 1);
            jdpDialog.setVisible(true);
            
        } 
        catch (FileNotFoundException fnfError){
            System.out.println("No se encontro el archivo" + 
                    fnfError.toString());
        }  
    }
    
}