package com.xgxzatx.ardudroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Toast.makeText(this, "Conectando ao medidor...", 
				Toast.LENGTH_SHORT).show();
		try {
			
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.splash_screen, menu);
//        return true;
//    }

    
}
