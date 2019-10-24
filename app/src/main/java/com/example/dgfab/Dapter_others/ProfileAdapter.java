package com.example.dgfab.Dapter_others;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ProfileAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public ProfileAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
//            case 0:
//                OverviewFrag overviewFrag = new OverviewFrag();
//                return overviewFrag;
//            case 1:
//
//                ProductFrag productFrag = new ProductFrag();
//                return productFrag;
//            case 2:
//                AverageFrag averageFrag = new AverageFrag();
//                return averageFrag;
//            case 3:
//                AnalyticsFrag analyticsFrag = new AnalyticsFrag();
//                return analyticsFrag;
//            case 4:
//                ConnectionsFrag connectionsFrag = new ConnectionsFrag();
//                return connectionsFrag;
//            case 5:
//                IntrestFrag intrestFrag = new IntrestFrag();
//                return intrestFrag;
//            default:

        }
        return null;
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

  /*  // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                OverviewFrag overviewFrag = new OverviewFrag();
                return overviewFrag;
            case 1:
                ProductFrag productFrag = new ProductFrag();
                return productFrag;
            case 2:
                AverageFrag averageFrag = new AverageFrag();
                return averageFrag;
            case 3:
                AnalyticsFrag analyticsFrag = new AnalyticsFrag();
                return analyticsFrag;
            case 4:
                ConnectionsFrag connectionsFrag = new ConnectionsFrag();
                return connectionsFrag;
            case 5:
                IntrestFrag intrestFrag = new IntrestFrag();
                return intrestFrag;
            default:
                return null;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }*/
}
