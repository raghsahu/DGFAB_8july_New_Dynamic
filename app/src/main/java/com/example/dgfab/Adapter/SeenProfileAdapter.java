package com.example.dgfab.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dgfab.Business_fragments.AnalyticsFrag;
import com.example.dgfab.Business_fragments.AverageFrag;
import com.example.dgfab.Business_fragments.ConnectionsFrag;
import com.example.dgfab.Business_fragments.IntrestFrag;
import com.example.dgfab.Business_fragments.OverviewFrag;
import com.example.dgfab.Business_fragments.ProductFrag;

public class SeenProfileAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public SeenProfileAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                OverviewFrag overviewFrag = new OverviewFrag();
                return overviewFrag;
            case 1:
//
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


//    // this is for fragment tabs
//    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                OverviewFrag overviewFrag = new OverviewFrag();
//                return overviewFrag;
//            case 1:
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
//                return null;
//        }
//    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
