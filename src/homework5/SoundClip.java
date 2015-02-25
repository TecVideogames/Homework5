package homework5;

/**
 * SoundClip
 * 
 * Used to play audio on JFrames
 */
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.net.URL;

public class SoundClip {
    // Variables
    private AudioInputStream aisSample;
    private Clip cliClip;
    private boolean bLooping = false;
    private int iRepeat = 0;
    private String strFileName = ""; // Nombre del archivo 

    /**
     * SoundClip
     * 
     * Default Constructor
     */
    public SoundClip() {
        try {
            //Create sound buffer
            cliClip = AudioSystem.getClip();
        }
        catch (LineUnavailableException lueException) { 
        }
    }

    /** 
     * SoundClip
     * 
     * Constructor with parameters, it calls the load method which uploads 
     * the audio file
     * 
     * @param strFileName is the <code>String</code> with the file's name.
     */
    public SoundClip(String strFileName) {
        //Call default constructor
        this();
        //Upload sound file
        load(strFileName);
    }

    /**
     * getClip
     * 
     * Access method which returns an Clip
     * 
     * @return cliClip is an <code>Clip</code> object.
     */
    public Clip getClip() { 
        return cliClip; 
    }

    /** 
     * setLooping
     * 
     * Used to enable sound repetition
     * 
     * @param bLooping is a  <code>boolean</code>. 
     */
    public void setLooping(boolean bLooping) {
        this.bLooping = bLooping; 
    }

    /**
     * getLooping
     * 
     * Returns a boolean if the sound is supposed to be repeated
     * 
     * @return bLooping  is a <code>boleano</code>. 
     */
    public boolean getLooping() {
        return bLooping;
    }

    /** 
     * setRepeat
     * 
     * Method used to define number of repetitions
     * 
     * @param iRepeat is an <code>int</code> which representes the number 
     * of repetitions
     */
    public void setRepeat(int iRepeat) {
        this.iRepeat = iRepeat;
    }

    /**
     * getRepeat
     * 
     * Method used to return the number of repetitions
     * 
     * @return iRepeat is an <code>int</code> with the number of repetitions 
     */
    public int getRepeat() { 
        return iRepeat; 
    }

    /**
     * setFilename
     * 
     * Assigns the file's name
     * 
     * @param strFileName is a <code>String</code> with the file's name. 
     */
    public void setFilename(String strFileName) { 
        this.strFileName = strFileName; 
    }

    /**
     * getFilename
     * 
     * Returns the file's name
     * 
     * @return strFileName is a <code>String</code> with the file's name
     */
    public String getFilename() { 
        return strFileName;
    }

    /**
     * isLoaded
     * 
     * Verify that the file is loaded
     * 
     * @return aisSample is an <code>aisSample</code> object.
     */
    public boolean isLoaded() {
        return (boolean)(aisSample != null);
    }

    /** 
     * Method that returns the file's URL
     * 
     * @param strFileName is a <code>String</code> with the file's name 
     */
    private URL getURL(String strFileName) {
        URL url = null;
        try {
            url = this.getClass().getResource(strFileName);
        }
        catch (Exception excException) { 
        }
        return url;
    }

    /**
     * load
     * 
     * Method that loads the sound file
     * 
     * @param strAudioFile is a <code>String</code> with the file's name
     * @return <code> boolean </code> if the file was able to be loaded
     */
    public boolean load(String strAudioFile) {
        try {
            setFilename(strAudioFile);
            aisSample = AudioSystem.getAudioInputStream(getURL(strFileName));
            cliClip.open(aisSample);
            return true;
        } 
        catch (IOException ioeException) {
            return false;
        }
        catch (UnsupportedAudioFileException ioeException) {
            return false;
        }
        catch (LineUnavailableException ioeException) {
            return false;
        }
    }

    /**
     * play
     * 
     * Method which plays the sound
     */
    public void play() {
        // quit if sound is not loaded
        if (!isLoaded()) {
            return;
        }
        // Start audio again
        cliClip.setFramePosition(0);

        // Play sound with opcional repetition
        if (bLooping) {
            cliClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        else {
            cliClip.loop(iRepeat);
        }
    }

    /**
     * stop
     * 
     * Stops the sound
     */
    public void stop() {
        cliClip.stop();
    }
}
