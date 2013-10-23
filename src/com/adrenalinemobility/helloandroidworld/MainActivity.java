package com.adrenalinemobility.helloandroidworld;

import org.json.JSONObject;

import io.adrenaline.API;
import io.adrenaline.AdrenalineIo;
import io.adrenaline.ApiResponse;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "HelloAndroidWorld";
	
	private class PingApiServer extends AsyncTask<TextView, Void, ApiResponse> {
		TextView status = null;
		
		@Override
		protected ApiResponse doInBackground(TextView... arg0) {
			status = arg0[0];
			ApiResponse resp = AdrenalineIo.post(API.LOGIN_CHALLENGE, new JSONObject(), true); 
			return resp;
		}
		
		@Override
		protected void onPostExecute(ApiResponse resp) {
			if (resp.ok()) {
				status.setText("All systems are go!");
				Log.d(TAG, "All systems are go!");
			} else {
				status.setText("Error -> " + resp.status());
				Log.e(TAG, "Error -> " + resp.status());
			}
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		AdrenalineIo.init(getApplicationContext());

		TextView status = (TextView) findViewById(R.id.textView1);
		status.setText("Checking server...");
		new PingApiServer().execute(status);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
