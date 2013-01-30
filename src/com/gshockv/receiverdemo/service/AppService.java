package com.gshockv.receiverdemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.gshockv.receiverdemo.Constants;

public class AppService extends IntentService {
	
	public AppService() {
		this("AppService");
	}
	
	public AppService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		final ResultReceiver receiver = intent.getParcelableExtra(Constants.RECEIVER);
		receiver.send(Constants.STATUS_RUNNING, Bundle.EMPTY);
		final Bundle data = new Bundle();
		
		try {
			Thread.sleep(Constants.SERVICE_DELAY);
			data.putString(Constants.RECEIVER_DATA, "Sample result data");
		} catch (InterruptedException e) {
			data.putString(Constants.RECEIVER_DATA, "Error");
		}
		receiver.send(Constants.STATUS_FINISHED, data);
	}
}
