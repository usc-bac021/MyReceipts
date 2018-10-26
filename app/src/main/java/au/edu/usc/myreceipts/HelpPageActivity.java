package au.edu.usc.myreceipts;

import android.support.v4.app.Fragment;

public class HelpPageActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new HelpPageFragment();
    }


}