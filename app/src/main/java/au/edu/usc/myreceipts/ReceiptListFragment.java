package au.edu.usc.myreceipts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ReceiptListFragment extends Fragment {


    private RecyclerView mReceiptRecyclerView;
    private ReceiptAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receipt_list, container, false);
        mReceiptRecyclerView = (RecyclerView) view.findViewById(R.id.receipt_recycler_view);
        mReceiptRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


//    Menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_receipt_list, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_receipt:
                Receipt receipt = new Receipt();
                ReceiptPile.get(getActivity()).addReceipt(receipt);
                Intent intent = ReceiptActivity.newIntent(getActivity(), receipt.getId());
                startActivity(intent);
                return true;
            case R.id.help:
                Intent intent1 = new Intent(getActivity(), HelpPageActivity.class);
                startActivity(intent1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        ReceiptPile receiptPile = ReceiptPile.get(getActivity());
        List<Receipt> receipts = receiptPile.getReceipts();
        if (mAdapter == null) {
            mAdapter = new ReceiptAdapter(receipts);
            mReceiptRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setReceipts(receipts);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class ReceiptHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Receipt mReceipt;

        private TextView mTitleTextView;
        private TextView mShopNameTextView;
        private TextView mDateTextView;

        public ReceiptHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_receipt, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = itemView.findViewById(R.id.receipt_title);
            mShopNameTextView = itemView.findViewById(R.id.receipt_shop_name);
            mDateTextView = itemView.findViewById(R.id.receipt_date);
        }

        public void bind(Receipt receipt){
            mReceipt = receipt;
            mTitleTextView.setText(mReceipt.getTitle());
            mShopNameTextView.setText(mReceipt.getShopName());
            mDateTextView.setText(mReceipt.getFormattedDate());
        }

        @Override
        public void onClick(View view) {
            Intent intent = ReceiptActivity.newIntent(getActivity(), mReceipt.getId());
            startActivity(intent);
        }
    }

    private class ReceiptAdapter extends RecyclerView.Adapter<ReceiptHolder> {

        private List<Receipt> mReceipts;

        public ReceiptAdapter(List<Receipt> receipts) {
            mReceipts = receipts;
        }

        @Override
        public ReceiptHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ReceiptHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ReceiptHolder holder, int position) {
            Receipt receipt = mReceipts.get(position);
            holder.bind(receipt);
        }

        @Override
        public int getItemCount() {
            return mReceipts.size();
        }

        public void setReceipts(List<Receipt> receipts) {
            mReceipts = receipts;
        }
    }
}
