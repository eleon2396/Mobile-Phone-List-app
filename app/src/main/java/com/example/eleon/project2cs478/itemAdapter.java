package com.example.eleon.project2cs478;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/*
    Eric Leon eleon23 654889611
    This custom item adapter class will populate my unique list view with a thumbnail and two strings for each item
 */

public class itemAdapter extends BaseAdapter {

    //create the string arrays needed for the this adapter which includes a phone names, specifications, and images
    private String[] phoneNames;
    private String [] infoSpecs;
    private int[] images;
    //also create the inflator variabe
    private LayoutInflater minflator;

    //Create the view holder structure to hold information from past list view items
    //Also used to set the list view items as well.
    static class ViewHolder{
        TextView phoneName;
        TextView specs;
        ImageView thumbNail;
    }

    //Constructor to intialize all the string arrays
    public itemAdapter(Context c, String[] pn, String[] sp, int[] imgs){
        this.phoneNames = pn;
        this.infoSpecs = sp;
        this.images = imgs;
        minflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //function to return the length of the phone names length
    @Override
    public int getCount() {
        return phoneNames.length;
    }

    //function to get item at a certain position
    @Override
    public Object getItem(int position) {
        return phoneNames[position];
    }

    //function to return the position in the array
    @Override
    public long getItemId(int position) {
        return position;
    }

    //Function to populate my customer List view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       //Create ViewHolder object to hold information from list view items
        ViewHolder holder;
        //Its a new item in the list so set and get some information, then set the tag
        if(convertView == null){
            //New Item being created and intialize the inflator for the new item
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_phones_info, null);
            holder = new ViewHolder();

            //get the strings associated with the list view
            holder.phoneName = convertView.findViewById(R.id.phoneNameTextView);
            holder.specs = convertView.findViewById(R.id.specsTextView);
            holder.thumbNail = convertView.findViewById(R.id.thumbNailImageView);
            //set the tag for the view holder
            convertView.setTag(holder);
        }
        //Else its being reused so get the tag
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        //set the information: the phone name, specs, and thumbnail for the items.
        holder.phoneName.setText(phoneNames[position]);
        holder.specs.setText(infoSpecs[position]);
        Bitmap bMap = BitmapFactory.decodeResource(convertView.getResources(), images[position]);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 200, 200, true);
        holder.thumbNail.setImageBitmap(bMapScaled);





      /*  //Set the inflator
        View v = minflator.inflate(R.layout.detailed_phones_info, null);

        //Get the images, phone name and specifications text views
        ImageView img = (ImageView) v.findViewById(R.id.thumbNailImageView);
        TextView phoneName = (TextView) v.findViewById(R.id.phoneNameTextView);
        TextView specsInfo = (TextView) v.findViewById(R.id.specsTextView);

        //Set the text for the phone names and specifications
        phoneName.setText(phoneNames[position]);
        specsInfo.setText(infoSpecs[position]);


        //Make the image into an thumbnail for the list view
        //This code resizes the image using bitmap operations and then sets the image
        Bitmap bMap = BitmapFactory.decodeResource(v.getResources(), images[position]);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 200, 200, true);
        img.setImageBitmap(bMapScaled);*/


        //Return the view that has been updated
        return convertView;
    }
}
