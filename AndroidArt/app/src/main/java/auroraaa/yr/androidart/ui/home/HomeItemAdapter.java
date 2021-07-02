package auroraaa.yr.androidart.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import auroraaa.yr.androidart.DashboardDetailActivity;
import auroraaa.yr.androidart.HomeDetailActivity;
import auroraaa.yr.androidart.R;
import auroraaa.yr.androidart.ui.Dashboard.DashboardItemAdapter;
import auroraaa.yr.library.item.HomeItem;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.ViewHolder> {

    private Context context;
    private List<HomeItem> homeItems = new ArrayList<>();

    public HomeItemAdapter(Context c, List<HomeItem> l){
        this.context = c;
        this.homeItems = l;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false);
        return new HomeItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeItem item = homeItems.get(position);
        holder.mText.setText(item.getTitle());

        String url = item.getImgId().replace("\\", "");
        Glide.with(context).load(url).asBitmap().into(holder.mImage);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeDetailActivity.class);
                intent.putExtra("data", item);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        TextView mText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.home_item_img);
            mText = itemView.findViewById(R.id.home_item_title);
        }
    }
}
