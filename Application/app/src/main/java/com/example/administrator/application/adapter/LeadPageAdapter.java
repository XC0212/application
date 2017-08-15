package com.example.administrator.application.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class LeadPageAdapter extends PagerAdapter {
    public ArrayList<ImageView> pages;
    public LeadPageAdapter(ArrayList<ImageView> pages){
        this.pages=pages;
    }

    public ArrayList<ImageView> getPages() {
        return pages;
    }

    public void setPages(ArrayList<ImageView> pages) {
        this.pages = pages;
    }


    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) pages.get(position));

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView((View) pages.get(position));
        return pages.get(position);
    }

}
