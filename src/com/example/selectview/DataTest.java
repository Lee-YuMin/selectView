package com.example.selectview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DataTest extends Activity {
	
	private TextView tvScene, tvType, tvInterval;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_data);
		
		tvScene = (TextView)findViewById(R.id.data_scene);
		tvType = (TextView)findViewById(R.id.data_type);
		tvInterval = (TextView)findViewById(R.id.data_interval);
		
		Intent i = getIntent();
		String dataScene = i.getExtras().getString("scene");
		String dataType = i.getExtras().getString("type");
		String dataInterval= i.getExtras().getString("interval");
		
		tvScene.setText(dataScene);
		tvType.setText(dataType);
		tvInterval.setText(dataInterval);
	}

}
