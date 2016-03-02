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

    private final boolean LOOP = true;
    private final boolean PLAY_ONCE = false;
    private Music music;

    public SoundsController() {
        this.music = Gdx.audio.newMusic(Gdx.files.internal(Constants.BACKGROUND_MUSIC_FILE));
    }

    public void playBgMusic() {
        playFile(Gdx.files.internal(Constants.BACKGROUND_MUSIC_FILE), LOOP);
    }

    private void playFile(FileHandle fileHandle, boolean looped) {

        System.out.println("Playing sound: " + fileHandle.toString());

        this.music = Gdx.audio.newMusic(fileHandle);
        this.music.setVolume(0.3f);
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
                playFile(Gdx.files.internal(Constants.SCREAM_FEMALE), PLAY_ONCE);
                break;
            default:
                throw new EnumConstantNotPresentException(Sounds.class, soundChoice.toString());
        }
    }

    public void dispose() {
        this.music.dispose();
    }
}
