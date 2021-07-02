package auroraaa.yr.androidart.ui.Dashboard;

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
// import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.io.Serializable;
import java.util.List;

import auroraaa.yr.androidart.DashboardDetailActivity;
import auroraaa.yr.androidart.R;
import auroraaa.yr.library.item.DashboardItem;

public class DashboardItemAdapter extends RecyclerView.Adapter<DashboardItemAdapter.ViewHolder> {

    private Context context;
    private List<DashboardItem> list;

    public DashboardItemAdapter(Context c, List<DashboardItem> l){
        this.context = c;
        this.list = l;
    }

    @NonNull
    @Override
    public DashboardItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_item, parent, false);
        return new DashboardItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardItemAdapter.ViewHolder holder, int position) {
        DashboardItem item = list.get(position);
        holder.mText.setText(item.getTitle());

        // Glide.with(context).load(iconPath).into(holder.mStar);

        String url = item.getUrl().replace("\\", "");
        Glide.with(context).load(url).into(holder.mImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DashboardDetailActivity.class);
                intent.putExtra("data", item);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        TextView mText;
        ImageView mStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.dashboard_item_img);
            mText = itemView.findViewById(R.id.dashboard_item_title);
            mStar = itemView.findViewById(R.id.dashboard_item_star);
        }
    }
}
