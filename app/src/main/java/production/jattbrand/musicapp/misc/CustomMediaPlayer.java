package production.jattbrand.musicapp.misc;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import static com.google.android.exoplayer2.Player.STATE_ENDED;
import static com.google.android.exoplayer2.Player.STATE_IDLE;

public class CustomMediaPlayer {

    private String TAG = "CustomMediaPlayer";
    private static SimpleExoPlayer mediaPlayer;
    private Context context;
    private static CustomMediaPlayer instance = null;

    public static CustomMediaPlayer getInstance(Context context) {
        if (instance == null)
            instance = new CustomMediaPlayer(context);
        instance.context = context;
        return instance;
    }

    private CustomMediaPlayer(Context context) {
        this.context = context;

        mediaPlayer = ExoPlayerFactory.newSimpleInstance(context, new DefaultRenderersFactory(context),
                new DefaultTrackSelector(), new DefaultLoadControl());
    }

    public SimpleExoPlayer getMediaPlayer() {

        if (mediaPlayer==null)
            init();
        return mediaPlayer;
    }

    public CustomMediaPlayer init() {
        AudioAttributes attr = new AudioAttributes.Builder()
                .setUsage(C.USAGE_MEDIA)
                .setContentType(C.CONTENT_TYPE_MUSIC)
                .build();
        getMediaPlayer().setAudioAttributes(attr);
        initListener();
        return this;
    }

    private MediaSource extractMediaSourceFromUri(Uri uri) {
        String userAgent = Util.getUserAgent(context, "exo");

        return new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(context, userAgent))
                .setExtractorsFactory(new DefaultExtractorsFactory()).createMediaSource(uri);
    }

    public boolean isPlaying() {
        return getMediaPlayer() != null
                && (getMediaPlayer().getPlaybackState() != STATE_ENDED)
                && (getMediaPlayer().getPlaybackState() != STATE_IDLE)
                && getMediaPlayer().getPlayWhenReady();
    }

    public CustomMediaPlayer resume() {
        getMediaPlayer().setPlayWhenReady(true);
        return this;
    }

    public CustomMediaPlayer pause() {
        getMediaPlayer().setPlayWhenReady(false);

        return this;
    }

    public CustomMediaPlayer play() {

        if (isPlaying())
            pause();
        else resume();

        return this;
    }

    public CustomMediaPlayer stop() {
        pause();
        getMediaPlayer().stop();
        return this;
    }

    public CustomMediaPlayer playNew(String url) {

        getInstance(context)
                .init()
                .setMedia(url)
                .play();

        return this;
    }

    public CustomMediaPlayer setMedia(String url) {
        MediaSource mediaSource = extractMediaSourceFromUri(Uri.parse(url));
        getMediaPlayer().prepare(mediaSource);
        return this;
    }

    private void initListener(){

        mediaPlayer.addListener(new Player.EventListener() {

            @Override
            public void onPlayerStateChanged(boolean isPlaying, int playbackState) {

                if (isPlaying)
                    Log.e(TAG, "onPlayerStateChanged: Playing" );
                else
                    Log.e(TAG, "onPlayerStateChanged: Paused" );
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });


        mediaPlayer.addAnalyticsListener(new AnalyticsListener() {
            @Override
            public void onSeekStarted(EventTime eventTime) {

            }

            @Override
            public void onSeekProcessed(EventTime eventTime) {

            }
        });

    }
}
