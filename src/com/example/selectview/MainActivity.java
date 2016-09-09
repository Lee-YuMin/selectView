package com.example.selectview;

import java.util.ArrayList;
import java.util.List;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemSelectedListener, ValueEventListener {

	private Firebase myFirebaseRef;
	private TextView tvScene;
	private Spinner spnScene, spnType, spnInterval;
	private Button btnData;
	private List<String> sceneList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Firebase.setAndroidContext(this);

		setContentView(R.layout.activity_main);

		tvScene = (TextView) findViewById(R.id.tv_scene);
		spnScene = (Spinner) findViewById(R.id.spn_select_scene);
		spnType = (Spinner) findViewById(R.id.spn_select_type);
		spnInterval = (Spinner) findViewById(R.id.spn_select_intervavl);
		btnData = (Button)findViewById(R.id.intent_button);
		
		spnScene.setOnItemSelectedListener(this);

		// typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// spnType.setAdapter(typeAdapter);

		// 파이어 베이스 주소 Set
		myFirebaseRef = new Firebase("https://bluetoothscan.firebaseio.com/");

		// 파이어 베이스 Value 가져오기
		myFirebaseRef.getRoot().addValueEventListener(this);
		
		btnData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), DataTest.class);
				i.putExtra("scene",spnScene.getSelectedItem().toString());
				i.putExtra("type", spnType.getSelectedItem().toString());
				i.putExtra("interval", spnInterval.getSelectedItem().toString());
				startActivity(i);
			}
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

	// getter / setter

	public List<String> getSceneList() {
		return sceneList;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDataChange(DataSnapshot snapshot) {
		// TODO Auto-generated method stub

		for (DataSnapshot child : snapshot.getChildren()) {
			sceneList.add(child.getKey());
		}

		setSceneSpinnerAdapter(this);

	}

	private void setSceneSpinnerAdapter(Activity context) {
		ArrayAdapter<String> sceneAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,
				sceneList);
		sceneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnScene.setAdapter(sceneAdapter);
	}

	@Override
	public void onCancelled(FirebaseError arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
