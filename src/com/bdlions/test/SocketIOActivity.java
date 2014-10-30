package com.bdlions.test;

import java.net.MalformedURLException;
import org.json.JSONObject;
import com.bdlions.io.socket.*;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SocketIOActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.abcd);

		SocketIO socket;
		try {
			socket = new SocketIO("http://172.17.155.43:8080/");

			socket.connect(new IOCallback() {

				@Override
				public void onMessage(JSONObject arg0, IOAcknowledge arg1) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onMessage(String arg0, IOAcknowledge arg1) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onError(SocketIOException arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onDisconnect() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onConnect() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "Connected",
							Toast.LENGTH_SHORT).show();
				}

				@Override
				public void on(String event, IOAcknowledge ack, Object... params) {
					// TODO Auto-generated method stub

				}
			});

			// This line is cached until the connection is establisched.
			//socket.send("Hello Server!");
			//socket.emit("sendChat", "alamgir");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public class MessageReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			final String chatMessage = (String) intent.getExtras().get(
					"chatMessage");
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(getApplicationContext(), chatMessage,
							Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
}
