package com.gshockv.receiverdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gshockv.receiverdemo.AppResultsReceiver;
import com.gshockv.receiverdemo.Constants;
import com.gshockv.receiverdemo.R;
import com.gshockv.receiverdemo.service.AppService;

public class MainActivity extends Activity implements AppResultsReceiver.Receiver {
	
	private AppResultsReceiver mReceiver;
	
	private ProgressBar mProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mProgress = (ProgressBar) findViewById(R.id.progressBar); 
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mReceiver = new AppResultsReceiver(new Handler());
		mReceiver.setReceiver(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mReceiver.setReceiver(null);
	}

	public void onStartButtonClick(View anchor) {
		final Intent intent = new Intent("SOME_COMMAND_ACTION", null, this, AppService.class);
		intent.putExtra(Constants.RECEIVER, mReceiver);
		startService(intent);
	}

	@Override
	public void onReceiveResult(int resultCode, Bundle data) {
		switch (resultCode) {
			case Constants.STATUS_RUNNING :
				mProgress.setVisibility(View.VISIBLE);
				break;
			case Constants.STATUS_FINISHED :
				mProgress.setVisibility(View.INVISIBLE);
				Toast.makeText(this, "Service finished with data: " 
						+ data.getString(Constants.RECEIVER_DATA), Toast.LENGTH_SHORT).show();
				break;
		}
	}
}
