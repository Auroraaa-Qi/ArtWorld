package auroraaa.yr.androidart.ui.Me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import auroraaa.yr.androidart.LoginActivity;
import auroraaa.yr.androidart.R;
import auroraaa.yr.androidart.RegisterActivity;
import auroraaa.yr.androidart.ui.Dashboard.DashboardItemAdapter;
import auroraaa.yr.library.data.GlobalData;
import auroraaa.yr.library.item.DashboardItem;

import static android.app.Activity.RESULT_OK;

public class MeFragment extends Fragment {

    private MeViewModel meViewModel;
    private static String username = "";
    LinearLayout l1 ;
    Button loginBtn, registerBtn;
    private ImageView mAvatar;
    TextView me_title;
    GlobalData globalData;
    ArrayList<DashboardItem> likes = new ArrayList<>();
    String defaultAvatar = "https://6865-hello-cloudbase-3ga7i4t13018ef95-1305331950.tcb.qcloud.la/cloudbase-cms/upload/2021-07-01/ilxwxk71yn9r6ix4bhcde0lad0z5vndp_.jpeg";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        meViewModel =
                new ViewModelProvider(this).get(MeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_me, container, false);
//        final TextView textView = root.findViewById(R.id.text_me);
//        meViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        l1 = (LinearLayout) root.findViewById(R.id.me_login);
        loginBtn = (Button) root.findViewById(R.id.login_button);
        registerBtn = (Button) root.findViewById(R.id.register_button);
        mAvatar = (ImageView) root.findViewById(R.id.me_avatar);
        me_title = (TextView) root.findViewById(R.id.me_title);
        if(username==""){
            loginBtn.setVisibility(View.VISIBLE);
            registerBtn.setVisibility(View.VISIBLE);
        }
        else {
            me_title.setText("我们等了你很久。欢迎回来，"+username);
            Glide.with(getContext()).load(defaultAvatar).into(mAvatar);
            // me_title.setText("我们等了你很久，欢迎回来，"+username+"。");
            globalData = (GlobalData) getActivity().getApplication();
            likes = globalData.getLikes();
            RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.me_recycler);
            DashboardItemAdapter adapter;
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            adapter = new DashboardItemAdapter(getContext(), likes);
            recyclerView.setAdapter(adapter);
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity().getApplicationContext(), LoginActivity.class), 1);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity().getApplicationContext(), RegisterActivity.class), 2);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            l1.setVisibility(View.INVISIBLE);
            loginBtn.setVisibility(View.INVISIBLE);
            registerBtn.setVisibility(View.INVISIBLE);
            username = data.getStringExtra("username");
            me_title.setText("我们等了你很久。欢迎回来，"+username);
            Glide.with(getContext()).load(defaultAvatar).into(mAvatar);
        }
    }
}