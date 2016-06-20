package math.sevakkalpesh.com.boilerplate_mvp.CakeList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import math.sevakkalpesh.com.boilerplate_mvp.R;
import math.sevakkalpesh.com.boilerplate_mvp.model.Cake_model;
import math.sevakkalpesh.com.boilerplate_mvp.util.recyclerview.ItemClickListener;


/**
 * Created by Cameron Stobie - Waracle tech test  on 10/14/2015.
 */


public class CakesAdapter extends RecyclerView.Adapter<CakesAdapter.ViewHolder>{

    private List<Cake_model> cakesList;
    private int rowLayout;
    private Context mContext;

    public CakesAdapter(List<Cake_model> cakesList, int rowLayout, Context context) {
        this.cakesList = cakesList;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Cake_model cake = cakesList.get(i);
        viewHolder.cakeName.setText(cake.getTitle());
        Picasso.with(mContext)
                .load(cake.getImage())
                .into( viewHolder.cakeImage);
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(mContext, "#" + position + " - " + cake.getTitle() + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "#" + position + " - " + cake.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cakesList == null ? 0 : cakesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView cakeName;
        public ImageView cakeImage;
        public String versionName;
        private ItemClickListener clickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            cakeName = (TextView) itemView.findViewById(R.id.name);
            cakeImage = (ImageView)itemView.findViewById(R.id.img);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }
        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }

    }
}
