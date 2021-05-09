package com.example.keyboarddialphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText_main);
        if(editText != null) {
            //if vie is found, set the listener for editext.
            editText.setOnEditorActionListener(
                    new TextView.OnEditorActionListener() {
                        /**
                         * 
                         * @param v The view that was clicked
                         * @param actionId identifier of the action
                         * @param event If triggered by the Enter key, this is the event
                         * @return
                         */
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            
                            boolean handled = false;
                            //If the action for the keyboard is defined as 
                            // IME_ACTION_SEND
                            //CALL the dialNubmer method and return true.
                            if(actionId == EditorInfo.IME_ACTION_SEND) {
                                dialNumber();
                                handled = true;
                            }
                            return false;
                        }
                    }
            );
        }
        
    }

    private void dialNumber() {

        EditText editText = findViewById(R.id.editText_main);
        String phoneNum = null;
        //if the editText field is not null
        //concatenate "tel: " with the phone number string.
        if (editText != null) phoneNum = "tel:" + editText.getText().toString();
        //log the concatenated phone number for dialing.
        Log.d(TAG, "dialNumber: " + phoneNum);

        //specify the intent
        Intent intent = new Intent(Intent.ACTION_DIAL);
        //Set the data for the intent as the phone number.
        intent.setData(Uri.parse(phoneNum));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "ImplicitIntents: Can't handle this!");
        }
    }
}