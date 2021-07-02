package auroraaa.yr.androidart.ui.Dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import auroraaa.yr.androidart.R;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private  ViewPager viewPager;
    private TabLayout tabLayout;
    List<Fragment> list;
    DashboardPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        tabLayout = (TabLayout) root.findViewById(R.id.top_tab);
        viewPager = (ViewPager) root.findViewById(R.id.dashboard_pager);
        list = new ArrayList<>();
        list.add(new D1Fragment());
        list.add(new D2Fragment());
        list.add(new D3Fragment());
        adapter = new DashboardPagerAdapter(getChildFragmentManager());
        adapter.setData(list);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(adapter);
        return root;
    }
}
