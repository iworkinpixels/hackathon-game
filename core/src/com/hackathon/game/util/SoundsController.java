package com.hackathon.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.hackathon.game.Constants;

/**
 * Created by tjago on 2016-03-02.
 * Resources taken from:
 * @link http://www.mediacollege.com/downloads/music/
 * @link http://soundbible.com/tags-scream.html
 *
 * When you are done with using the Music instance you have to dispose it via the dispose() method
 *
 * v1 - small incremental leak in memory :-)
 */
public class SoundsController {

    public static final float FULL_VOLUME = 1.0f;
    public static final float HALF_VOLUME = 0.5f;
    public static final float ONE_TENTH_VOLUME = 0.1f;
    private final boolean LOOP = true;
    private final boolean PLAY_ONCE = false;
    private Music music;

    public SoundsController() {
        this.music = Gdx.audio.newMusic(Gdx.files.internal(Constants.BACKGROUND_MUSIC_FILE));
    }

    public void playBgMusic() {
        playFile(Gdx.files.internal(Constants.BACKGROUND_MUSIC_FILE), FULL_VOLUME, LOOP);
    }

    private void playFile(FileHandle fileHandle, float volume, boolean looped) {

        this.music = Gdx.audio.newMusic(fileHandle);
        this.music.setVolume(volume);
        this.music.setLooping(looped);
        this.music.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                dispose();
            }
        });
        this.music.play();
    }

    public void playSound(Sounds soundChoice) {
        switch (soundChoice) {
            case FEMALE_SCREAM:
                playFile(Gdx.files.internal(Constants.SCREAM_FEMALE), ONE_TENTH_VOLUME, PLAY_ONCE);
                break;
            default:
                throw new EnumConstantNotPresentException(Sounds.class, soundChoice.toString());
        }
    }

    public void dispose() {
        this.music.dispose();
    }
}
