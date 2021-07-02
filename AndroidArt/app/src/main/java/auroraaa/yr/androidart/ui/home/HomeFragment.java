package auroraaa.yr.androidart.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import auroraaa.yr.androidart.R;
import auroraaa.yr.library.item.HomeItem;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private View root;

    private HomeViewModel homeViewModel;

    private String cmsContent = "";

    private List<HomeItem> itemList = new ArrayList<>();
    public RecyclerView recyclerView;
    public HomeItemAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);

        initData();
        initRecyclerView();

        // final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        return root;

    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView) root.findViewById(R.id.home_recycler);
        while(itemList.isEmpty()){
            // 等数据传回来
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        adapter = new HomeItemAdapter(this.getContext(), itemList);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        String path = "https://hello-cloudbase-3ga7i4t13018ef95-1305331950.ap-shanghai.service.tcloudbase.com/rest-api/v1.0/pictures";

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(path).build();
                try {
                    Response response  = client.newCall(request).execute();
                    cmsContent = response.body().string();
                    // System.out.println(cmsContent);
                    readJSONContent(cmsContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void readJSONContent(String cmsContent){
        try {
            JSONArray jsonArray = new JSONObject(cmsContent).getJSONArray("data");
            System.out.println(jsonArray.length());
            for(int i=0;i<jsonArray.length();i++){
                JSONObject temp = (JSONObject) jsonArray.get(i);
                String id = (String) temp.get("_id");
                String title = (String) temp.get("title");
                JSONArray imgsArray = (JSONArray) temp.get("images");
                List<String> images = new ArrayList<>();
                for(int j=0;j<imgsArray.length();j++){
                    images.add((String)imgsArray.get(j));
                }
                String username = (String) temp.get("username");
                String details = (String) temp.get("details");
                int likes = (int) temp.get("likes");
                String vedioUrl = (String) temp.get("vedioUrl");
                itemList.add(new HomeItem(id, images, title, username, likes, details, vedioUrl));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}