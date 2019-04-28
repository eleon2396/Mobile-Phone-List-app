package com.example.eleon.project2cs478;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/*
    Eric Leon (eleon23) 654889611
    CS 478 Project 2:
        - This application will have a list of 6 phones of my choosing into a list view:
        - Upon short clicking on one of the items in the list the user will be directed to another activity:
            1. Inside this new activity the user will be shown a full image of the phone.
               If the user clicks anywhere on the image, he/she will be directed to the official web page of the phone selected.
        - Upon a long click the user will be shown a context window:
            1. Inside this context window a user will be able to choose one of three options:
                a) Display the devices specifications.
                b) Show the entire image of the device.
                c) Show the official web page of the device.

    PHONES BEING USED: Google Pixel 2, Iphone 8 Plus, I phone X MAX,
                        Samsung Note 9, Google Pixel 3, Samsung Galaxy S9;
 */

/*
NEED TO RESEARCH THE MEMORY ALLOCATION OF THE IMAGES AND HOW TO FREE THEM
 */
public class MainActivity extends AppCompatActivity {

    //Private variables to hold all the information needed for the project
    //This includes string arrays for the phone names, specifications, web links, and images
    private String[] phoneNames;
    private String[] specsInfo;
    private String[] webLinks;
    private String[] specifiedSpecs;
    private int[] images;

    //Private variable for the list being populated
    private ListView mainList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the title for the app
        TextView title = findViewById(R.id.titletextView);
        title.setText("Please Select Any of the Following Phones:");

        //Set the resource variable
        Resources res = getResources();
        //Initialize the string arrays to their specific arrays
        phoneNames = res.getStringArray(R.array.PhoneNames);
        specsInfo = res.getStringArray(R.array.SpecInfo);
        webLinks = res.getStringArray(R.array.phoneWebPages);
        specifiedSpecs = res.getStringArray(R.array.detailedSpecsInfo);
        images = new int[]{
                R.drawable.iphonexsmax, R.drawable.iphone8plus, R.drawable.samsungnote9, R.drawable.samsunggalaxys9, R.drawable.googlepixel3, R.drawable.googlepixel2
        };

        //Get the list view and set it up with a context menu
        mainList = findViewById(R.id.phoneImageListView);
        registerForContextMenu(mainList);

        //Create the custom item adapter class and set it for the list view
        itemAdapter adapter =new itemAdapter(this, phoneNames, specsInfo, images);
        mainList.setAdapter(adapter);

        //Call method to display image in another activity
        displayImage();

    }

    //Overring method for the context menu for when an item is long clicked on
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Choose one of the following");
        getMenuInflater().inflate(R.menu.contextmenu, menu);
    }

    //override the method for the item selected in the context menu (Long Clicked)
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Create an adapter that will get the information from the item that triggered the context menu
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        //Switch statement to see what the user selects
        switch (item.getItemId()){
            //This case is for when the user wants to see the specifications of the phone
            case R.id.option_1:
                contextMenuOption1(info.position);
                return true;
            //This case is for when the user wants to see the full image of the phone
            case R.id.option_2:
                contextMenuOption2(info.position);
                return true;
            //This case is for when the user just wants to go directly to the phones website
            case R.id.option_3:
                contextMenuOption3(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    //Function for the first context menu option for the specifications of the phone selected
    public void contextMenuOption1(int pos){

        //Create the explicit intent to launch the specific activity
        Intent specIntent = new Intent(getApplicationContext(), displayPhoneSpecsActivity.class);
        //add the correct information into the intent for the specifications
        specIntent.putExtra("com.example.eleon.SPEC_INDEX", specifiedSpecs[pos]);
        specIntent.putExtra("com.example.eleon.NAME_INDEX", phoneNames[pos]);
        //start it
        startActivity(specIntent);
    }

    //Function for the second context menu option for displaying the large image of the phone selected
    public void contextMenuOption2(int pos){
        //Create explicit intent to activity displaying the images
        Intent imageDisplayIntent = new Intent(getApplicationContext(), displayImageActivity.class);
        //Add the position of the image into the intent
        imageDisplayIntent.putExtra("com.example.eleon.ITEM_INDEX", pos);
        //Get the web links associated with it
        String web = webLinks[pos];
        //add it to the intent
        imageDisplayIntent.putExtra("com.exmaple.eleon.WEB_INDEX", web);
        //Start it
        startActivity(imageDisplayIntent);
    }

    //Function for the third content menu option for displaying just the website of the phone selected
    public void contextMenuOption3(int pos){
        //Set the web link for the image selected
        String web = webLinks[pos];
        //Create implicit intent
        Intent webIntent = new Intent(Intent.ACTION_VIEW);
        //set the data for the intent and URI parse it
        webIntent.setData(Uri.parse(web));
        //Start the activity
        startActivity(webIntent);
    }

    //function to display the image clicked (short clicked)
    public void displayImage(){

            //Item click listener to listen for an item in the list is clicked
            mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //get the web link associated with the image
                    String web = webLinks[position];
                    //Create the explicit intent to launch the activity that will display the image
                    Intent imageDisplayIntent = new Intent(getApplicationContext(), displayImageActivity.class);
                    //Add the information into the intent like the position of the image
                    imageDisplayIntent.putExtra("com.example.eleon.ITEM_INDEX", position);
                    //Add more information into the intent like the web like for the specific image
                    imageDisplayIntent.putExtra("com.exmaple.eleon.WEB_INDEX", web);
                    //Start it
                    startActivity(imageDisplayIntent);
                }
            });
    }

}
