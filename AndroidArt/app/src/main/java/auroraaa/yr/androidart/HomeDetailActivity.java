package auroraaa.yr.androidart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import auroraaa.yr.library.item.HomeItem;

public class HomeDetailActivity extends AppCompatActivity {

    private String[] url;

    private ViewPager mViewPager;
    private int[] imgheights;
    private int screenWidth;

    private TextView mTitle;
    private TextView mDetails;
    private TextView mUrl;


    @Override
    public void onCreate(Bundle savedInstanceBundle) {

        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_homedetail);

        Intent intent = getIntent();
        HomeItem data = (HomeItem) intent.getSerializableExtra("data");
        url = new String[data.getImages().size()];
        int i=0;
        for(String s: data.getImages()){
            url[i] = s.replace("\\", "");
            i += 1;
        }

        screenWidth = this.getScreenWidth(this);
        initView();

        mTitle = (TextView) findViewById(R.id.home_detail_title);
        mDetails = (TextView) findViewById(R.id.home_detail_details);
        mUrl = (TextView) findViewById(R.id.home_detail_url);

        mTitle.setText(data.getTitle());
        mDetails.setText(data.getDetails());
        mUrl.setText(data.getVedioUrl().replace("\\", ""));

    }

    public void initView(){
        mViewPager = (ViewPager) findViewById(R.id.home_detail_viewpager);
        Glide.with(this).load(url[0]).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                float scale = (float) resource.getHeight()/resource.getWidth();
                int defaultHeight = (int)(scale*screenWidth);
                initViewPager(defaultHeight);
            }
        });
    }

    private void initViewPager(final int defaultHeight){
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                if(imgheights==null||imgheights.length!=url.length){
                    imgheights = null;
                    imgheights = new int[url.length];
                }
                return url.length;
            }

            @NotNull
            @Override
            public Object instantiateItem(ViewGroup container, final int position){
              final ImageView imageView = new ImageView(HomeDetailActivity.this);
              imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

              Glide.with(getApplicationContext()).load(url[position]).asBitmap().placeholder(R.mipmap.ic_launcher).into(new ImageViewTarget<Bitmap>(imageView) {
                  @Override
                  protected void setResource(Bitmap resource) {
                      if(resource!=null){
                          float scale = (float) resource.getHeight()/resource.getWidth();
                          imgheights[position] = (int) (scale*screenWidth);
                          imageView.setImageBitmap(resource);
                      }
                      else {
                          Toast.makeText(HomeDetailActivity.this, "图片为空", Toast.LENGTH_LONG).show();
                      }
                  }
              });
              container.addView(imageView);
              return imageView;
            };

            @Override
            public void destroyItem(ViewGroup container, int position, Object object){
                container.removeView((View)object);
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }
        });

        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        params.height = defaultHeight;
        mViewPager.setLayoutParams(params);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==imgheights.length-1){
                    return;
                }
                int height = (int)((imgheights[position]==0?defaultHeight:imgheights[position])
                        *(1-positionOffset)+(imgheights[position+1]==0?defaultHeight:imgheights[position+1])*positionOffset);

                ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
                params.height = height;
                mViewPager.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

}
