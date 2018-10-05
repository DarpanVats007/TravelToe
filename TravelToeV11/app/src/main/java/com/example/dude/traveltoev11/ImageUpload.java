package com.example.dude.traveltoev11;

public class ImageUpload {



    private String mName;
    private String mImageUrl;

    public ImageUpload (){

        // empty constructor needed
    }
    public ImageUpload (String name, String mImageUrl){
        if (name.trim ().equals ("")){
            name = "no name";
        }
        mName = name;
        mImageUrl=mImageUrl;
    }
    public String getmName(){

        return mName;
    }
    public void setmName(String name){

        mName = name;
    }
    public String getmImageUrl(){

        return mImageUrl;
    }
    public void setmImageUrl(String imageUrl){
        mImageUrl = imageUrl;
    }
}
