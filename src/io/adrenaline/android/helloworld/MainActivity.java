package io.adrenaline.android.helloworld;

import io.adrenaline.AdrenalineIo;
import io.adrenaline.ApiResponse;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "HelloAndroidWorld";

	private TextView status;

	private class PingApiServer extends AsyncTask<Void, Void, ApiResponse> {

		@Override
		protected void onPostExecute(ApiResponse resp) {
			if (resp.ok()) {
				status.setText("Connection Successful. Welcome to: " + resp.getString("name"));
			} else {
				status.setText("Error -> " + resp.status());
				Log.e(TAG, "Error -> " + resp.status());
			}
		}

		@Override
		protected ApiResponse doInBackground(Void... params) {
			return AdrenalineIo.getAppDetails();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		status = (TextView) findViewById(R.id.textView1);
		status.setText("Checking server...");

		// check adrenaline.io server
		new PingApiServer().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
