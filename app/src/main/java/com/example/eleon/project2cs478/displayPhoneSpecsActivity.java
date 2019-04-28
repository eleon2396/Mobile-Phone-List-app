package com.example.eleon.project2cs478;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class displayPhoneSpecsActivity extends AppCompatActivity {

    /*
    Eric Leon eleon23 654889611
    This activity is just to display the phones specifications on the screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_phone_specs);
        //Get the text view
        TextView displaySpecs = (TextView) findViewById(R.id.phoneSpecTextView);
        TextView phoneName = (TextView)findViewById(R.id.phoneTextView);

        //Get the intent and retrieve information
        Intent intent = getIntent();
        String spec = intent.getStringExtra("com.example.eleon.SPEC_INDEX");
        String name = intent.getStringExtra("com.example.eleon.NAME_INDEX");
        //Display the specifications of the phone
        displaySpecs.setText(spec);
        phoneName.setText(name);


    }

    //finish the activity onDestroy
    @Override
    public void onDestroy(){
        super.onDestroy();
        finish();
    }

}
