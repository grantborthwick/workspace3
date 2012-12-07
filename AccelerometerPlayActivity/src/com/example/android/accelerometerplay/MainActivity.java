package com.example.android.accelerometerplay;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Context context = this;
	TextView textRows;
	TextView textColumns;
	TextView textBalls;
	TextView textTraps;
	TextView textBallSize;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textRows = (TextView) findViewById(R.id.editText2);
		textColumns = (TextView) findViewById(R.id.editText1);
		textBalls = (TextView) findViewById(R.id.editText3);
		textTraps = (TextView) findViewById(R.id.editText4);
		textBallSize = (TextView) findViewById(R.id.editText5);
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {	
		public void onClick(View arg0){
				Intent levelDown = new Intent(context, AccelerometerPlayActivity.class);
				Bundle parem = setParameters();
				parem.putBoolean("AlarmMode", false);
				levelDown.putExtras(parem);
				((Activity)context).startActivityForResult(levelDown, 1);
				}			
		});
		
		Button buttonAlarm = (Button) findViewById(R.id.button3);
		buttonAlarm.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0){
				Intent levelDown = new Intent(context, AccelerometerPlayActivity.class);
				Bundle parem = setParameters();
				parem.putBoolean("AlarmMode", true);
				levelDown.putExtras(parem);
				((Activity)context).startActivityForResult(levelDown, 1);			}
			
		});
		Button buttonQuit = (Button) findViewById(R.id.button2);
		buttonQuit.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0){finish();}
		});
	}
	
	public Bundle setParameters(){
		Bundle parem = new Bundle();
		int CellCountX,CellCountY,NUM_PARTICLES;
		float TrapBoxRatio,BallSize;
		try{CellCountX = Integer.parseInt(textRows.getText().toString());}
		catch (Exception e){CellCountX = 15;}
		
		try{CellCountY = Integer.parseInt(textColumns.getText().toString());}
		catch (Exception e){CellCountY = 10;}
		
		try{NUM_PARTICLES = Integer.parseInt(textBalls.getText().toString());}
		catch(Exception e){NUM_PARTICLES = 1;}
		
		try{TrapBoxRatio = Float.parseFloat(textTraps.getText().toString());}
		catch(Exception e){TrapBoxRatio=0;}
		
		try{BallSize = Float.parseFloat(textBallSize.getText().toString());}
		catch(Exception e){BallSize = 1/1.75f;}
		
		CellCountX = Math.min(Math.max(CellCountX,2),30);
		CellCountY = Math.min(Math.max(CellCountY,2),40);
		NUM_PARTICLES = Math.min(Math.max(1,NUM_PARTICLES),1000);
		if(TrapBoxRatio>0 && TrapBoxRatio < 4){TrapBoxRatio=4;}
		if(BallSize>0.8){BallSize=.8f;}
		if(BallSize<0.2){BallSize=0.2f;}
				
		textRows.setText(((Integer)CellCountX).toString());
		textColumns.setText(((Integer)CellCountY).toString());
		textBalls.setText(((Integer)NUM_PARTICLES).toString());
		textTraps.setText(((Float)TrapBoxRatio).toString());
		textBallSize.setText(((Float)BallSize).toString());
		
		parem.putInt("CellCountX", CellCountX);
		parem.putInt("CellCountY", CellCountY);
		parem.putInt("NUM_PARTICLES", NUM_PARTICLES);
		parem.putFloat("TrapBoxRatio", TrapBoxRatio);
		parem.putFloat("BallSize", BallSize);
		parem.putInt("level", 1);
		
		return parem;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
	}
}
