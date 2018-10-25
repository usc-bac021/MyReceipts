package au.edu.usc.myreceipts;

import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

public class Receipt {

    private UUID mId;

    private Date mDate;

    private String mTitle;
    private String mShopName;
    private String mComment;

    private String mLocation;
    private String mImage;

    public Receipt() {
        mId = UUID.randomUUID();
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

    public String getDateAsString() {
        return DateFormat.format("EEE, dd MMM yyyy", mDate).toString();
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

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
