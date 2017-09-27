package com.lintex9527.study;

import java.io.IOException;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

@TargetApi(23) public class AudioRecordTestActivity extends Activity {
	
	private static final String LOG_TAG = "AudioRecordTestActivity";
	
	private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
	private static String mFileName = null;
	
	private RecordButton mRecordButton = null;
	private MediaRecorder mRecorder = null;
	
	private PlayButton mPlayButton = null;
	private MediaPlayer mPlayer = null;

	private String recStart;
	private String recStop;
	private String audioPlay;
	private String audioFinish;

	// Requesting permission to RECORD_AUDIO
	private boolean permissionToRecordAccepted = false;
	private String[] permissions = {Manifest.permission.RECORD_AUDIO};
	
	
	@Override
	public void onRequestPermissionsResult(int requestCode,
			String[] permissions, int[] grantResults) {

		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		
		switch(requestCode){
		case REQUEST_RECORD_AUDIO_PERMISSION:
			permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
			break;
		}
		if (!permissionToRecordAccepted){
			finish();
		}
	}// end of onRequestPermissionsResult()
	
	
	private void onRecord(boolean start){
		if (start){
			startRecording();
		} else {
			stopRecording();
		}
	}
	
	private void onPlay(boolean start){
		if (start){
			startPlaying();
		} else {
			stopPlaying();
		}
	}
	
	private void startPlaying(){
		mPlayer = new MediaPlayer();
		try {
			mPlayer.setDataSource(mFileName);
			mPlayer.setLooping(true);
			mPlayer.prepare();
			mPlayer.start();
		} catch (IOException e) {
			Log.e(LOG_TAG, "play prepare() failed.");
			e.printStackTrace();
		}
	} // end of startPlaying().
	
	
	private void stopPlaying(){
		mPlayer.pause();
		mPlayer.release();
		mPlayer = null;
	} // end of stopPlaying()
	
	
	private void startRecording(){
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(mFileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		
		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(LOG_TAG, "record prepare() failed!");
			e.printStackTrace();
		}
		
		mRecorder.start();
	}
	
	
	private void stopRecording(){
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}
	
	

	class RecordButton extends android.support.v7.widget.AppCompatButton{
		boolean mStartRecording = true;
		
		OnClickListener clicker = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onRecord(mStartRecording);
				if (mStartRecording){
					setText(recStop);
				} else {
					setText(recStart);
				}
				mStartRecording = !mStartRecording;
				
			}
		};

		public RecordButton(Context context) {
			super(context);
			setText(recStart);
			setOnClickListener(clicker);
		}
	} // end of RecordButton.
	
	
	class PlayButton extends android.support.v7.widget.AppCompatButton{
		boolean mStartPlaying = true;
		
		OnClickListener clicker = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onPlay(mStartPlaying);
				if (mStartPlaying){
					setText(audioFinish);
				} else {
					setText(audioPlay);
				}
				mStartPlaying = !mStartPlaying;
			}
		};

		public PlayButton(Context context) {
			super(context);
			setText(audioPlay);
			setOnClickListener(clicker);
		}
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		recStart = getResources().getString(R.string.recStart);
		recStop = getResources().getString(R.string.recStop);
		audioPlay = getResources().getString(R.string.audioPlay);
		audioFinish = getResources().getString(R.string.audioFinish);

		// Record to the external cache directory for visibility
		mFileName = getExternalCacheDir().getAbsolutePath();
		mFileName += "/audiorecordtest.3gp";
		
		requestPermissions(permissions, REQUEST_RECORD_AUDIO_PERMISSION);
		
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		mRecordButton = new RecordButton(this);
		mRecordButton.setTextSize((float) 24.0);
		ll.addView(mRecordButton, 
				new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 
				ViewGroup.LayoutParams.WRAP_CONTENT, 
				0));
		
		mPlayButton = new PlayButton(this);
		mPlayButton.setTextSize((float) 24.0);
		ll.addView(mPlayButton, 
				new LinearLayout.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, 
						ViewGroup.LayoutParams.WRAP_CONTENT, 
						0));
		setContentView(ll);
	}



	@Override
	protected void onStop() {
		super.onStop();
		
		if (mRecorder != null){
			mRecorder.release();
			mRecorder = null;
		}
		
		if (mPlayer != null){
			mPlayer.release();
			mPlayer = null;
		}
	}
	
}
