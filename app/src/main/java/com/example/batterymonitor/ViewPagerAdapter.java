package com.example.batterymonitor;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private static final int CARD_ITEM_SIZE = 2;
    private String tabTitles[] = new String[] { "Information", "Saver" };

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull @Override public Fragment createFragment(int position) {
        if (position == 0)
            return InformationFragment.newInstance("", "");
        else
            return SaverFragment.newInstance("", "");

    }
    @Override public int getItemCount() {
        return CARD_ITEM_SIZE;
    }

    public View getTabView(int position, Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_item, null);
        TextView tv = view.findViewById(R.id.tabText);
        tv.setText(tabTitles[position]);
        if(position == 0)
            tv.setTextColor(context.getResources().getColor(R.color.colorBlue_1));
        else
            tv.setTextColor(context.getResources().getColor(R.color.colorYellow));
        return view;
    }

    public void SetOnSelectView(TabLayout tabLayout, int position, Context context) {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        View selected = tab.getCustomView();
        TextView tv = (TextView) selected.findViewById(R.id.tabText);
        tv.setTextColor(context.getResources().getColor(R.color.colorBlue_1));
    }

    public void SetUnSelectView(TabLayout tabLayout,int position, Context context) {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        View selected = tab.getCustomView();
        TextView tv = (TextView) selected.findViewById(R.id.tabText);
        tv.setTextColor(context.getResources().getColor(R.color.colorYellow));
    }
}
