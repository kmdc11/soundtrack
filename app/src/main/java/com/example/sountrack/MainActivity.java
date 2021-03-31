package com.example.sountrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {
    private Button btnRecord1, btnRecord2, btnRecord3, btnStop, btnPlay1, btnPlay2, btnPlay3, guitar, dramatic,orchestra;
    private MediaPlayer mplayer1, mplayer2, mplayer3, mp1,mp2,mp3;
    private MediaRecorder mRecorder1, mRecorder2, mRecorder3;
    private static final String LOG_TAG = "AudioRecording";
    private static String mFilename1 = null;
    private static String mFilename2 = null;
    private static String mFilename3 = null;
    private int recorder = 0;


    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guitar = this.findViewById(R.id.music1);
        dramatic = this.findViewById(R.id.music2);
        orchestra = this.findViewById(R.id.music3);

        btnRecord1 = this.findViewById(R.id.record1);
        btnRecord2 = this.findViewById(R.id.record2);
        btnRecord3 = this.findViewById(R.id.record3);
        btnStop = this.findViewById(R.id.stop);
        btnPlay1 = this.findViewById(R.id.play);
        btnPlay2 = this.findViewById(R.id.play2);
        btnPlay3 = this.findViewById(R.id.play3);

        mFilename1 = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFilename1 += "/record1.3gp";

        mFilename2 = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFilename2 += "/record2.3gp";

        mFilename3 = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFilename3 += "/record3.3gp";


// for recording sounds
        btnRecord1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(CheckPermissions()) {
                    btnRecord1.setEnabled(false);
                    btnPlay1.setEnabled(false);
                    btnPlay2.setEnabled(false);
                    btnPlay3.setEnabled(false);
                    btnStop.setEnabled(true);

                    mRecorder1 = new MediaRecorder();
                    mRecorder1.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mRecorder1.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mRecorder1.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mRecorder1.setOutputFile(mFilename1);
                    try {
                        mRecorder1.prepare();
                    } catch (IOException e) {
                        Log.e(LOG_TAG,"prepare() failed" );
                    }
                    mRecorder1.start();
                    recorder=1;
                } else {
                    RequestPermissions();

                }
            }
        });

        btnRecord2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(CheckPermissions()) {
                    btnRecord2.setEnabled(false);
                    btnPlay1.setEnabled(false);
                    btnPlay2.setEnabled(false);
                    btnPlay3.setEnabled(false);
                    btnStop.setEnabled(true);
                    mRecorder2 = new MediaRecorder();
                    mRecorder2.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mRecorder2.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mRecorder2.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mRecorder2.setOutputFile(mFilename2);
                    try {
                        mRecorder2.prepare();
                    } catch (IOException e) {
                        Log.e(LOG_TAG,"prepare() failed" );
                    }
                    mRecorder2.start();
                    recorder=2;
                } else {
                    RequestPermissions();

                }
            }
        });

        btnRecord3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(CheckPermissions()) {
                    btnRecord3.setEnabled(false);
                    btnPlay1.setEnabled(false);
                    btnPlay2.setEnabled(false);
                    btnPlay3.setEnabled(false);
                    btnStop.setEnabled(true);
                    mRecorder3 = new MediaRecorder();
                    mRecorder3.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mRecorder3.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mRecorder3.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mRecorder3.setOutputFile(mFilename3);
                    try {
                        mRecorder3.prepare();
                    } catch (IOException e) {
                        Log.e(LOG_TAG,"prepare() failed" );
                    }
                    mRecorder3.start();
                    recorder=3;
                } else {
                    RequestPermissions();

                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStop.setEnabled(false);
                btnPlay1.setEnabled(true);
                btnPlay2.setEnabled(true);
                btnPlay3.setEnabled(true);
                switch (recorder){
                    case 1:
                        btnRecord1.setEnabled(true);
                        mRecorder1.stop();
                        mRecorder1.release();
                        mRecorder1 = null;
                        break;
                    case 2:
                        btnRecord2.setEnabled(true);
                        mRecorder2.stop();
                        mRecorder2.release();
                        mRecorder2 = null;

                        break;
                    case 3:
                        btnRecord3.setEnabled(true);
                        mRecorder3.stop();
                        mRecorder3.release();
                        mRecorder3 = null;
                        break;
                    default:
                        break;
                }}
        });

        btnPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRecord1.setEnabled(true);
                btnStop.setEnabled(false);
                btnPlay1.setEnabled(true);

                if (mplayer1 != null) {
                    mplayer1.release();
                    mplayer1 = null;
                }
                mplayer1 = new MediaPlayer();
                try {
                    mplayer1.setDataSource(mFilename1);
                    mplayer1.prepare();
                    mplayer1.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    mplayer1.release();
                    mplayer1 = null;
                }

            }
        });
        btnPlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mplayer2 != null) {
                    mplayer2.release();
                    mplayer2 = null;

                }
                mplayer2 = new MediaPlayer();
                try {
                    mplayer2.setDataSource(mFilename2);
                    mplayer2.prepare();
                    mplayer2.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    mplayer2.release();
                    mplayer2 = null;
                }
            }
        });
        btnPlay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mplayer3 != null) {
                    mplayer3.release();
                    mplayer3 = null;

                }
                mplayer3 = new MediaPlayer();
                try {
                    mplayer3.setDataSource(mFilename3);
                    mplayer3.prepare();
                    mplayer3.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    mplayer3.release();
                    mplayer3 = null;
                }}
        });
    }

    public boolean CheckPermissions() {
        int resultExternalStorage = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int resultRecordAudio = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return (resultExternalStorage == PackageManager.PERMISSION_GRANTED &&
                resultRecordAudio == PackageManager.PERMISSION_GRANTED);
    }
    private void RequestPermissions() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {RECORD_AUDIO, WRITE_EXTERNAL_STORAGE},
                REQUEST_AUDIO_PERMISSION_CODE);
    }

     public void toggleGuitar(View v) {

         mp1 = MediaPlayer.create(this, R.raw.guitar);
         mp1.setLooping(false);
         mp1.seekTo(0);
         mp1.setVolume(0.5f, 0.5f);

         try {
            if (mp1.isPlaying()) {
                mp1.stop();
                mp1.release();
                mp1 = MediaPlayer.create(this,R.raw.guitar);
            }else {
                mp1.start();
            }
        } catch(Exception e) {e.printStackTrace();}
     }

    public void toggleDramatic(View v) {
        mp2 = MediaPlayer.create(this, R.raw.dramatic);
        mp2.setLooping(false);
        mp2.seekTo(0);
        mp2.setVolume(0.5f, 0.5f);
        try {
            if (mp2.isPlaying()) {
                mp2.stop();
                mp2.release();
                mp2 = MediaPlayer.create(this,R.raw.dramatic);
            }else {
                mp2.start();
            }
        } catch(Exception e) {e.printStackTrace();}
    }

    public void toggleOrchestra(View v) {
        mp3 = MediaPlayer.create(this, R.raw.orchestra);
        mp3.setLooping(false);
        mp3.seekTo(0);
        mp3.setVolume(0.5f, 0.5f);
        try {
            if (mp3.isPlaying()) {
                mp3.stop();
                mp3.release();
                mp3 = MediaPlayer.create(this,R.raw.orchestra);
            }else {
                mp3.start();
            }
        } catch(Exception e) {e.printStackTrace();}
    }
}