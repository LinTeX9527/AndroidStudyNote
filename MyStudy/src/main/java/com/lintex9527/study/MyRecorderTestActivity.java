package com.lintex9527.study;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * http://blog.csdn.net/jiangliloveyou/article/details/11218555
 *
 * 千万注意：
 * 运行该程序必须要开启录音权限、存储权限。
 *
 * @author LinTeX9527
 * 2017-09-17 10:16
 */
public class MyRecorderTestActivity extends Activity implements OnClickListener{

	private Toast mToast;

	private TextView stateView;
	
	private Button btnStart, btnStop, btnPlay, btnFinish;
	
	private RecorderTask recorderTask;
	
	private PlayerTask playerTask;
	
	private File audioFile;
	
	private boolean isRecording = true, isPlaying = false;
	
	private static final int MYRECORDER_FREQUENCY = 8000;
	
	private static final int MYRECORDER_CHANNELCONFIG = AudioFormat.CHANNEL_CONFIGURATION_MONO;
	
	private static final int MYRECORDER_AUDIOENCODING = AudioFormat.ENCODING_PCM_16BIT;
	
	private static final String MYRECORDING_FILE_PREFIX = "myrecording";

	private static final String MYRECORDING_FILE_SUFFIX = ".pcm";
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myrecorder_test);

		mToast = Toast.makeText(getBaseContext(), "", Toast.LENGTH_SHORT);

		stateView = (TextView) findViewById(R.id.view_state);
		stateView.setText("REC start");
		
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStop = (Button) findViewById(R.id.btnStop);
		btnPlay = (Button) findViewById(R.id.btnPlay);
		btnFinish = (Button) findViewById(R.id.btnFinish);
		
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnPlay.setOnClickListener(this);
		btnFinish.setOnClickListener(this);
		
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
		btnPlay.setEnabled(false);
		btnFinish.setEnabled(false);

		File fpath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/files");
		fpath.mkdirs();
		try {
			audioFile = File.createTempFile(MYRECORDING_FILE_PREFIX, MYRECORDING_FILE_SUFFIX, fpath);
			showTips("set file ok");
		} catch (IOException e) {
			showTips(getResources().getString(R.string.recWarnning));
			e.printStackTrace();
		}
		
	}

	private void showTips(String msg){
		mToast.setText(msg);
		mToast.setDuration(Toast.LENGTH_SHORT);
		mToast.show();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {

		case R.id.btnStart:
			recorderTask = new RecorderTask();
			recorderTask.execute();
			break;

		case R.id.btnStop:
			this.isRecording = false;
			break;
			
		case R.id.btnPlay:
			playerTask = new PlayerTask();
			playerTask.execute();
			break;
			
		case R.id.btnFinish:
			this.isPlaying = false;
			break;
			
		default:
			break;
		}
	}
	
	
	private class RecorderTask extends AsyncTask<Void, Integer, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			isRecording = true;
			try {
				
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(audioFile)));
				int bufferSize = AudioRecord.getMinBufferSize(MYRECORDER_FREQUENCY, MYRECORDER_CHANNELCONFIG, MYRECORDER_AUDIOENCODING);
				AudioRecord myRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, MYRECORDER_FREQUENCY, MYRECORDER_CHANNELCONFIG, MYRECORDER_AUDIOENCODING, bufferSize);
				short[] buffer = new short[bufferSize];
				
				myRecord.startRecording();
				
				int pr = 0;
				while(isRecording){
					int bufferReadResult = myRecord.read(buffer, 0, buffer.length);
					for (int i = 0; i < bufferReadResult; i ++){
						dos.writeShort(buffer[i]);
					}
					
					publishProgress(pr);
					pr ++;
				}
				
				myRecord.stop();
				Toast.makeText(getBaseContext(), "REC stop", Toast.LENGTH_SHORT).show();
				dos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}


		@Override
		protected void onProgressUpdate(Integer... values) {
			stateView.setText( String.format("%s", values[0].toString()));
		}

		@Override
		protected void onPreExecute() {
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
			btnPlay.setEnabled(false);
			btnFinish.setEnabled(false);
		}

		
		@Override
		protected void onPostExecute(Void result) {
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
			btnPlay.setEnabled(true);
			btnFinish.setEnabled(false);
		}

		
		
	} // end of class RecorderTask
	
	
	private class PlayerTask extends AsyncTask<Void, Integer, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			isPlaying = true;
			int bufferSize = AudioTrack.getMinBufferSize(MYRECORDER_FREQUENCY, MYRECORDER_CHANNELCONFIG, MYRECORDER_AUDIOENCODING);
			short[] buffer = new short[bufferSize/4];
			try {
				DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(audioFile)));
				
				AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, MYRECORDER_FREQUENCY, MYRECORDER_CHANNELCONFIG, MYRECORDER_AUDIOENCODING, bufferSize, AudioTrack.MODE_STREAM);
				track.play();
				
				while(isPlaying && dis.available()>0){
					int i = 0;
					while(dis.available()>0 && i<buffer.length){
						buffer[i] = dis.readShort();
						i ++;
					}
					track.write(buffer, 0, buffer.length);
				}
				
				track.stop();
				dis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			btnStart.setEnabled(false);
			btnStop.setEnabled(false);
			btnPlay.setEnabled(false);
			btnFinish.setEnabled(true);
		}
		
		@Override
		protected void onPostExecute(Void result) {
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
			btnPlay.setEnabled(true);
			btnFinish.setEnabled(false);
		}


	} // end of class PlayerTask
}
