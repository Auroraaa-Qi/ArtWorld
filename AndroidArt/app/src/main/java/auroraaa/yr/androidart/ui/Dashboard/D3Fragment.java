package auroraaa.yr.androidart.ui.Dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import auroraaa.yr.androidart.R;
import auroraaa.yr.library.item.DashboardItem;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class D3Fragment extends Fragment {
    private View root;
    private String cmsContent;
    private List<DashboardItem> list = new ArrayList<>();

    public RecyclerView recyclerView;
    public DashboardItemAdapter adapter3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_d, container, false);
        initData();
        initRecyclerView();
        return root;
    }

    private void initRecyclerView(){
        recyclerView = root.findViewById(R.id.dashboard_recycler);
        while(list.isEmpty()){
            // 等数据传回来
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter3 = new DashboardItemAdapter(getContext(), list);
        recyclerView.setAdapter(adapter3);
    }

    private void initData(){
        String path = "https://hello-cloudbase-3ga7i4t13018ef95-1305331950.ap-shanghai.service.tcloudbase.com/rest-api/v1.0/jiaoyu";
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
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void readJSONContent(String content) throws JSONException {
        JSONArray jsonArray = new JSONObject(cmsContent).getJSONArray("data");
        for(int i=0;i<jsonArray.length();i++){
            JSONObject temp = (JSONObject) jsonArray.get(i);
            String id = (String) temp.get("_id");
            String title = (String) temp.get("title");
            String path = (String) temp.get("image");
            list.add(new DashboardItem(id, title, path));
        }
    }
}
