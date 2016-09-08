package com.example.selectview;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Firebase myFirebaseRef;
	private TextView tvScene;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Firebase.setAndroidContext(this);
		
		setContentView(R.layout.activity_main);
		
		tvScene = (TextView)findViewById(R.id.tv_scene);
		
		
		// 파이어 베이스 주소 Set
        myFirebaseRef = new Firebase("https://bluetoothscan.firebaseio.com/");
 
        //파이어 베이스 Value 가져오기
        myFirebaseRef.getRoot().addValueEventListener(new ValueEventListener() {
			  @Override
			  public void onDataChange(DataSnapshot snapshot) {
				  tvScene.setText(snapshot.getValue().toString());
			  }
			  @Override public void onCancelled(FirebaseError error) { }
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
