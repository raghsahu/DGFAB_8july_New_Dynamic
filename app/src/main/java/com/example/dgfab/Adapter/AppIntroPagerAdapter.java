package com.example.dgfab.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dgfab.Activity.Buyer_guest_login.PermissionActivity;
import com.example.dgfab.R;
import com.example.dgfab.Activity.SplashScreens.App_Intro;

public class AppIntroPagerAdapter extends PagerAdapter {
    private Context mContext;
    LayoutInflater mLayoutInflater;
    private int[] mResources;
    private App_Intro activity;


    public AppIntroPagerAdapter(App_Intro activity, Context mContext, int[] mResources) {
        this.mContext = mContext;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mResources = mResources;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View itemView = mLayoutInflater.inflate(R.layout.appintropager_adapter, container, false);
        //ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_foster);
        ImageView iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        TextView ctvText = (TextView) itemView.findViewById(R.id.ctvText);
        TextView ctvText1 = (TextView) itemView.findViewById(R.id.ctvText1);
        imageView.setImageResource(mResources[position]);
        setDescText(position, ctvText, ctvText1, iv_icon);


        container.addView(itemView);
        ctvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 2) {
                    mContext.startActivity(new Intent(mContext, PermissionActivity.class));

                    ((Activity) mContext).finish();
                    activity.overridePendingTransition(R.anim.anim_slide_in_left,
                            R.anim.anim_slide_out_left);
                }

                int pos = position + 1;
                activity.scrollPage(pos);


            }
        });
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setDescText(int pos, TextView ctvText, TextView txt, ImageView img) {
        switch (pos) {
            case 0:
                ctvText.setText(mContext.getString(R.string.first_intro));
                txt.setText(mContext.getString(R.string.first_intro_msg));
              //  img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.intro_icon_1));
                break;
            case 1:
                ctvText.setText(mContext.getString(R.string.second_intro));
                txt.setText(mContext.getString(R.string.first_intro_msg));
               // img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.intro_icon_2));
                break;
            case 2:
                ctvText.setText(mContext.getString(R.string.third_intro));
                txt.setText(mContext.getString(R.string.first_intro_msg));
               // img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.intro_icon_3));
                break;
        }
    }

}
