package au.edu.usc.myreceipts;

import android.content.Context;
import android.content.ContentValues;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import au.edu.usc.myreceipts.database.ReceiptBaseHelper;
import au.edu.usc.myreceipts.database.ReceiptCursorWrapper;
import au.edu.usc.myreceipts.database.ReceiptDbSchema;
import au.edu.usc.myreceipts.database.ReceiptDbSchema.ReceiptTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ReceiptPile {
    private static ReceiptPile sReceiptPile;

    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static ReceiptPile get(Context context){
        if (sReceiptPile == null){
            sReceiptPile = new ReceiptPile(context);
        }
        return sReceiptPile;
    }


    private ReceiptPile(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ReceiptBaseHelper(mContext).getWritableDatabase();
    }


    public void addReceipt(Receipt r){
        ContentValues values = getContentValues(r);
        mDatabase.insert(ReceiptTable.NAME, null, values);
    }


    public void deleteReceipt(Receipt r) {
        String uuidString = r.getId().toString();
        mDatabase.delete(ReceiptTable.NAME, ReceiptTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }


    public List<Receipt> getReceipts(){
        List<Receipt> receipts = new ArrayList<>();
        ReceiptCursorWrapper cursor = queryReceipts(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                receipts.add(cursor.getReceipt());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return receipts;
    }


    public Receipt getReceipt(UUID id){
        ReceiptCursorWrapper cursor = queryReceipts(
                ReceiptTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getReceipt();
        } finally {
            cursor.close();
        }
    }


    public File getPhotoFile(Receipt receipt){
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, receipt.getPhotoFilename());
    }


    public void updateReceipt(Receipt receipt){
        String uuidString = receipt.getId().toString();
        ContentValues values = getContentValues(receipt);
        mDatabase.update(ReceiptTable.NAME, values, ReceiptTable.Cols.UUID + " = ?",
                new String[] { uuidString});
    }


    private ReceiptCursorWrapper queryReceipts(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                ReceiptTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new ReceiptCursorWrapper(cursor);
    }


    private static ContentValues getContentValues(Receipt receipt){
        ContentValues values = new ContentValues();
        values.put(ReceiptTable.Cols.UUID, receipt.getId().toString());
        values.put(ReceiptTable.Cols.TITLE, receipt.getTitle());
        values.put(ReceiptTable.Cols.SHOPNAME, receipt.getShopName());
        values.put(ReceiptTable.Cols.COMMENT, receipt.getComment());
        values.put(ReceiptTable.Cols.DATE, receipt.getDate().getTime());
        values.put(ReceiptTable.Cols.LONGITUDE, receipt.getLongitude());
        values.put(ReceiptTable.Cols.LATITUDE, receipt.getLatitude());
        return values;
    }
}






















//package au.edu.usc.myreceipts;
//
//import android.content.Context;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.UUID;
//
//public class ReceiptPile {
//    private static ReceiptPile sReceiptPile;
//
//    private LinkedHashMap<UUID, Receipt> mReceipts;
//
//    public static ReceiptPile get(Context context) {
//        if (sReceiptPile == null) {
//            sReceiptPile = new ReceiptPile(context);
//        }
//        return sReceiptPile;
//    }
//
//    private ReceiptPile(Context context) {
//        mReceipts = new LinkedHashMap<>();
//        for (int i = 0; i < 1; i++) {
//            Receipt receipt = new Receipt();
//            receipt.setTitle("Example Receipt");
//            receipt.setShopName("Example Shop Name");
//            mReceipts.put(receipt.getId(), receipt);
//        }
//    }
//
//    public List<Receipt> getReceipts() {
//        return new ArrayList<>(mReceipts.values());
//    }
//
//    public Receipt getReceipt(UUID id) {
//        return mReceipts.get(id);
//    }
//
//    public void addReceipt(Receipt r) {
//        mReceipts.put(r.getId(), r);
//    }
//}