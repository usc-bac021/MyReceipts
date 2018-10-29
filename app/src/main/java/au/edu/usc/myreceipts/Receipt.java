package au.edu.usc.myreceipts;

import android.location.Location;
import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

public class Receipt {

    private UUID mId;

    private Date mDate;

    private String mTitle;
    private String mShopName;
    private String mComment;

    private Location mLocation;

    private double mLongitude;
    private double mLatitude;


    public Receipt() {
        this(UUID.randomUUID());
    }

    public Receipt(UUID uuid) {
        mId = uuid;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String shopName) {
        mShopName = shopName;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public double getLongitude() { return mLongitude; }

    public void setLongitude(double longitude) { mLongitude = longitude; }

    public double getLatitude() { return mLatitude; }

    public void setLatitude(double latitude) { mLatitude = latitude; }

    public String getFormattedDate() {
        return "Date: " + DateFormat.format("EEE, dd MMM yyyy", getDate()).toString();
    }

    public String getPhotoFilename(){
        return "IMG_" + getId().toString() + ".jpg";
    }
}
