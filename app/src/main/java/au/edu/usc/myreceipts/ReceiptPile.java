package au.edu.usc.myreceipts;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class ReceiptPile {
    private static ReceiptPile sReceiptPile;

    private LinkedHashMap<UUID, Receipt> mReceipts;

    public static ReceiptPile get(Context context) {
        if (sReceiptPile == null) {
            sReceiptPile = new ReceiptPile(context);
        }
        return sReceiptPile;
    }

    private ReceiptPile(Context context) {
        mReceipts = new LinkedHashMap<>();
        for (int i = 0; i < 5; i++) {
            Receipt receipt = new Receipt();
            receipt.setTitle("Receipt #" + i);
            receipt.setShopName("Shop " + i);
            mReceipts.put(receipt.getId(), receipt);
        }
    }

    public List<Receipt> getReceipts() {
        return new ArrayList<>(mReceipts.values());
    }

    public Receipt getReceipt(UUID id) {
        return mReceipts.get(id);
    }

    public void addReceipt(Receipt r) {
        mReceipts.put(r.getId(), r);
    }
}
