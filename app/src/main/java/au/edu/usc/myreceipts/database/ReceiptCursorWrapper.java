package au.edu.usc.myreceipts.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import au.edu.usc.myreceipts.Receipt;
import au.edu.usc.myreceipts.database.ReceiptDbSchema.ReceiptTable;

import java.util.Date;
import java.util.UUID;


public class ReceiptCursorWrapper extends CursorWrapper {

    public ReceiptCursorWrapper(Cursor cursor){
        super(cursor);
    }



    public Receipt getReceipt(){
        String uuidString = getString(getColumnIndex(ReceiptTable.Cols.UUID));
        String title = getString(getColumnIndex(ReceiptTable.Cols.TITLE));
        String shopname = getString(getColumnIndex(ReceiptTable.Cols.SHOPNAME));
        String comments = getString(getColumnIndex(ReceiptTable.Cols.COMMENT));
        long date = getLong(getColumnIndex(ReceiptTable.Cols.DATE));
        double longitude = getDouble(getColumnIndex(ReceiptTable.Cols.DATE));
        double latitude = getDouble(getColumnIndex(ReceiptTable.Cols.DATE));

        Receipt receipt = new Receipt(UUID.fromString(uuidString));
        receipt.setTitle(title);
        receipt.setShopName(shopname);
        receipt.setComment(comments);
        receipt.setDate(new Date(date));
        receipt.setLongitude(longitude);
        receipt.setLatitude(latitude);

        return receipt;

    }

}
