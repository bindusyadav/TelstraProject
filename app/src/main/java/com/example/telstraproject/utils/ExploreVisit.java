package com.example.telstraproject.utils;

public class ExploreVisit {
    private String mImageUrl;
    private String mSubTitle;
    private String mDescription;


    public ExploreVisit(String imageUrl,String subTitle,String description){
        mImageUrl = imageUrl;
        mSubTitle = subTitle;
        mDescription = description;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmSubTitle() {
        return mSubTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public void setmSubTitle(String mSubTitle) {
        this.mSubTitle = mSubTitle;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
