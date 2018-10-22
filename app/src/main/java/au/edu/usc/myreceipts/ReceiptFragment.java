package au.edu.usc.myreceipts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

public class ReceiptFragment extends Fragment {

    private static final String ARG_RECEIPT_ID = "receipt_id";

    private Receipt mReceipt;

    private EditText mTitleField;
//    private EditText mShopNameField;
//    private EditText mComment;

    private Button mDateButton;
//    private Button mLocationButton;
//    private Button mImageButton;
//    private Button mReportButton;
//
//    private TextView mLocationTextView;
//
//    private ImageView mImageView;


    public static ReceiptFragment newInstance(UUID receiptId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_RECEIPT_ID, receiptId);
        ReceiptFragment fragment = new ReceiptFragment();
        fragment.setArguments(args);        
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID receiptId = (UUID) getArguments().getSerializable(ARG_RECEIPT_ID);
        mReceipt = ReceiptPile.get(getActivity()).getReceipt(receiptId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_receipt, container, false);

        mTitleField = (EditText) v.findViewById(R.id.receipt_title);
        mTitleField.setText(mReceipt.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mReceipt.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

//        mShopNameField = (EditText) v.findViewById(R.id.receipt_shopname);
//        mShopNameField.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mReceipt.setShopName(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//
//
//        mComment = (EditText) v.findViewById(R.id.receipt_comment);
//        mComment.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mReceipt.setComment(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });

        mDateButton = (Button) v.findViewById(R.id.receipt_date);
        mDateButton.setText(mReceipt.getDate().toString());
        mDateButton.setEnabled(false);


        return v;
    }
}
