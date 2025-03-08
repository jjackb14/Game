package core.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class to manage the sounds in the game.
 */
public final class Sound {

    /** The audio clip to play. */
    private Clip clip;

    /** An ArrayList of the paths to the sounds. */
    private ArrayList<URL> soundURL = new ArrayList<>();

    /**
     * Constructs a new sound object.
     * On construction all the sounds will be loaded into the ArrayList.
     */
    public Sound() {
        soundURL.add(0, getClass().getResource("/sound/BlueBoyAdventure.wav"));
        soundURL.add(1, getClass().getResource("/sound/coin.wav"));
        soundURL.add(2, getClass().getResource("/sound/powerup.wav"));
        soundURL.add(3, getClass().getResource("/sound/unlock.wav"));
        soundURL.add(4, getClass().getResource("/sound/fanfare.wav"));
    }

    /**
     * Sets the audio file to be played.
     * @param idx the index of the audio file in the ArrayList.
     */
    public void setFile(int idx) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL.get(idx));
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();    //still thinking about how to handle this :)
        }
    }

    /**
     * Starts playing the loaded audio clip.
     * The functionality of this method is delegated to {@link Clip}'s start method but encapsulated in a
     * public method.
     */
    public void play() {
        clip.start();
    }

    /**
     * Continuously loops the loaded audio clip.
     * The functionality of this method is delegated to {@link Clip}'s loop method but encapsulated in a
     * public method.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops playing the currently loaded audio clip.
     * The functionality of this method is delegated to {@link Clip}'s stop method but encapsulated in a
     * public method.
     */
    public void stop() {
        clip.stop();
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public ArrayList<URL> getSoundURL() {
        return soundURL;
    }

    public void setSoundURL(ArrayList<URL> soundURL) {
        this.soundURL = soundURL;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sound sound = (Sound) o;
        return Objects.equals(clip, sound.clip) && Objects.equals(soundURL, sound.soundURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clip, soundURL);
    }

    @Override
    public String toString() {
        return "Sound{" +
                "clip=" + clip +
                ", soundURL=" + soundURL +
                '}';
    }
}
