package com.example.eleon.project2cs478;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/*
    Eric Leon eleon23 654889611
    This Activity will just display the image that was clicked on in the list view
    and then if the user clicks anywhere on the image the application will take them
    to the official website of the phone selected
 */
public class displayImageActivity extends AppCompatActivity {

    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        //Get the intent
        Intent intent = getIntent();

        //get the index and web link sent through the intent
        int index = intent.getIntExtra("com.example.eleon.ITEM_INDEX", -1);
        final String webLink = intent.getStringExtra("com.exmaple.eleon.WEB_INDEX");

        //if the image was found
        if(index > -1){
            //get the index of the image
            int imgIndex = getImage(index);
            //get the image view to populate it
            img = findViewById(R.id.phoneDisplayImageView);
            //scale the image correctly
            scaleIMG(img, imgIndex);
            //set the on click listener for when the user clicks anywhere on the images
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //set the implicit intent
                    Intent webIntent = new Intent(Intent.ACTION_VIEW);
                    //set the data of the intent with the parse web link
                    webIntent.setData(Uri.parse(webLink));
                    //start it
                    startActivity(webIntent);
                }
            });
        }

    }

    //Function to the get the image that was selected in the list view
    private int getImage(int index){
        switch (index){
            case 0: return R.drawable.iphonexsmax;
            case 1: return R.drawable.iphone8plus;
            case 2: return R.drawable.samsungnote9;
            case 3: return R.drawable.samsunggalaxys9;
            case 4: return R.drawable.googlepixel3;
            case 5: return R.drawable.googlepixel2;
            default: return -1;
        }
    }

    //function to free up memory and exit the activity OnDestroy
    //Need to call the garbage collector
    @Override
    public void onDestroy() {
        super.onDestroy();
        img.setImageDrawable(null);
        img.destroyDrawingCache();
        Runtime.getRuntime().gc();
        System.gc();
        finish();
    }

    //function to properly scale the image
    private void scaleIMG(ImageView img, int imgIndex){

        //set the display screen and setup the bitmap options
       // Display screen = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        BitmapFactory.Options options = new BitmapFactory.Options();

        //set the bounds and the resoure
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), imgIndex, options);

        //set the width and the screenwidth
        int imgWidth = options.outWidth;
        int screenWidth = displayMetrics.widthPixels;

        //make sure the width is correct
        if(imgWidth > screenWidth){
            //set the ratio and the options sample size
            int ratio  = Math.round( (float) imgWidth / (float) screenWidth);
            options.inSampleSize = ratio;
        }

        //reset the bounds and create the bitmap factory with the variables
        options.inJustDecodeBounds = false;
        Bitmap scaleImg = BitmapFactory.decodeResource(getResources(), imgIndex, options);
        //set the bitmap to the newly scaled image
        img.setImageBitmap(scaleImg);
    }

}
