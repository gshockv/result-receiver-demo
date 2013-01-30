package com.gshockv.receiverdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onStartButtonClick(View anchor) {
		Toast.makeText(this, "Start process", Toast.LENGTH_SHORT).show();
	}
}
