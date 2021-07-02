package auroraaa.yr.androidart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import auroraaa.yr.library.data.GlobalData;
import auroraaa.yr.library.item.DashboardItem;

public class DashboardDetailActivity extends AppCompatActivity {
    private String url;
    private String title;

    private Button mButton;
    private TextView mTextview;
    private ImageView mImageView;

    @Override
    public void onCreate(Bundle savedBundleInstance) {

        super.onCreate(savedBundleInstance);
        setContentView(R.layout.activity_dashboarddetail);

        Intent intent = getIntent();
        DashboardItem item = (DashboardItem) intent.getSerializableExtra("data");

        mImageView = (ImageView) findViewById(R.id.dashboard_detail_img);
        mTextview = (TextView) findViewById(R.id.dashboard_detail_title);
        mButton = (Button) findViewById(R.id.dashboard_detail_button);

        String url = item.getUrl().replace("\\", "");

        Glide.with(getApplicationContext()).load(url).asBitmap().into(mImageView);
        mTextview.setText(item.getTitle());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "加入收藏成功", Toast.LENGTH_SHORT).show();
                mButton.setText("已加入收藏");
                GlobalData globalData = (GlobalData) getApplication();
                globalData.addLike(item);
            }
        });
    }
}
