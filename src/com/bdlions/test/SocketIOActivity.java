package com.bdlions.test;

import java.net.MalformedURLException;

import org.json.JSONObject;

import com.bdlions.io.socket.*;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SocketIOActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_chat);

		final SocketIO socket;
		try {
			socket = new SocketIO("http://10.0.2.2:8080/");

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
					UserInfo userInfo = new UserInfo();
					userInfo.setUserId(2);
					userInfo.setFirstName("nazmul");
					userInfo.setLastName("hasan");
					userInfo.setRoomId(100);
					Gson gson = new Gson();
					
					socket.emit("adduser", gson.toJson(userInfo));
				}

				@Override
				public void on(String event, IOAcknowledge ack, final Object... params) {
					// TODO Auto-generated method stub
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							LinearLayout chatList = (LinearLayout) findViewById(R.id.chatList);
							TextView tv = new TextView(getApplicationContext());
							tv.setTextColor(Color.BLACK);
							tv.setText(params[0].toString() + ": "+params[1].toString());
							chatList.addView(tv);
						}
					});
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
	
}
