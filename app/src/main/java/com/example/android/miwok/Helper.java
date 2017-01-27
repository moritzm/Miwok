package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Created by moritzmoldenhauer on 26/01/2017.
 */

public class Helper {

    private static MediaPlayer mMediaPlayer;

    private static AudioManager.OnAudioFocusChangeListener afChangeListener;
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private static MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };
    private static AudioManager am;

    public static void playMedia(Context context, int id) {
        releaseMediaPlayer();
        am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        createListener();
        int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mMediaPlayer = MediaPlayer.create(context, id);
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
        }
    }

    private static void createListener() {
        afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                    // Pause playback because your Audio Focus was
                    // temporarily stolen, but will be back soon.
                    // i.e. for a phone call
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    // Stop playback, because you lost the Audio Focus.
                    // i.e. the user started some other playback app
                    // Remember to unregister your controls/buttons here.
                    // And release the kra — Audio Focus!
                    // You’re done.
                    releaseMediaPlayer();
                    am.abandonAudioFocus(afChangeListener);
                } else if (focusChange ==
                        AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    // Lower the volume, because something else is also
                    // playing audio over you.
                    // i.e. for notifications or navigation directions
                    // Depending on your audio playback, you may prefer to
                    // pause playback here instead. You do you.
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // Resume playback, because you hold the Audio Focus
                    // again!
                    // i.e. the phone call ended or the nav directions
                    // are finished
                    // If you implement ducking and lower the volume, be
                    // sure to return it to normal here, as well.
                    mMediaPlayer.start();
                }
            }
        };
    }

    public static void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }

        if(am != null) {
            am.abandonAudioFocus(afChangeListener);
        }
    }


}
