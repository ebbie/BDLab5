package com.conorb.test;
/*
 * Copyright 2012 Conor Byrne.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 

 */
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class FaceBounceActivity extends Activity {
	
	FBView view;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        Log.v("FB Act","In Oncreate");
        
        //fbv = (FBView)findViewById(R.id.FBView);
        startService(new Intent(this,ConnectionService.class));
        
        //Intent view = new Intent(this,FBView.class);
		//startActivity(view);
        view= new FBView(this);
		registerReceiver(receiver, new IntentFilter("myproject"));
		setContentView(view);
        
        
    }
    
	public BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			if (bundle!=null) {
				
				//extra data inserted into the fired intent
				String data = bundle.getString("data");
				Log.i("data in main class", data);
				
				
				if ("stomp".equalsIgnoreCase(data)) {
					view.togglePause();
				}
				
				
				//Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
			}else{
				Log.i("data in main class", "bundle null");
				//Toast.makeText(getApplicationContext(), "not", Toast.LENGTH_SHORT).show();
			}
			//handleResult(bundle);
		}

		
	};
    
    @Override 
    protected void onPause(){
    	super.onPause();
    	Log.v("FB Act","In OnPause");
    	finish();
    }

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.v("FB Act","In OnRestart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("FB Act","In OnResume");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v("FB Act","In OnStart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v("FB Act","In OnStop");
	}
    
	protected void onDestroy(){
		super.onDestroy();
		Log.v("FB Act","In OnDestroy");
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_MENU) {
	    	Log.v("FB Act", "Menu key pressed!");
	    	view.togglePause();
	    	return true;
	    }
	    else{
	    	return super.onKeyUp(keyCode, event);
	    }
	    
	}
//    
    
}
