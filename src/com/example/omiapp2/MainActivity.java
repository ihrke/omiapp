package com.example.omiapp2;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
    	/*TextView field=(TextView)findViewById(R.id.fullscreen_content);
		field.setMovementMethod(new ScrollingMovementMethod());*/
	}

    /** Called when the user clicks the clear button */
    public void clearText(View view) {
    	TextView field=(TextView)findViewById(R.id.fullscreen_content);
    	field.setText("");    	
    }
    
    /** Called when the user clicks the clear button */
    public void insertEnter(View view) {
    	TextView field=(TextView)findViewById(R.id.fullscreen_content);
    	field.append("\n");
    }
    
    protected static final int RESULT_SPEECH = 1;

    /** Called when the user clicks the clear button */
    public void recordText(View view) {
    	TextView field=(TextView)findViewById(R.id.fullscreen_content);
    	
        Intent intent = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

        try {
            startActivityForResult(intent, RESULT_SPEECH);
            field.append("\n");
        } catch (ActivityNotFoundException a) {
            Toast t = Toast.makeText(getApplicationContext(),
                    "Opps! Your device doesn't support Speech to Text",
                    Toast.LENGTH_SHORT);
            t.show();
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    	TextView field=(TextView)findViewById(R.id.fullscreen_content);

        switch (requestCode) {
        case RESULT_SPEECH: {
            if (resultCode == RESULT_OK && null != data) {
 
                ArrayList<String> text = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
 
                field.append(text.get(0));
            }
            break;
        }
 
        }
    }

}
