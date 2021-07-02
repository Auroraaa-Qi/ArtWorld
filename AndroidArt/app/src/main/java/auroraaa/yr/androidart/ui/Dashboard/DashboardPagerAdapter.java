package auroraaa.yr.androidart.ui.Dashboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboardPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> list;


    public DashboardPagerAdapter(FragmentManager fm){
        super(fm);
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setData(List<Fragment> l){
        this.list.clear();
        this.list.addAll(l);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "马面裙";
            case 1:
                return "百迭裙";
            case 2:
                return "交嵛裙";
        }
        return null;
    }
}
