package com.example.mvvmroomcatapi;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder>{

    private Context context;
    private List<Model> cats;

    public CatAdapter(Context context, List<Model> cats) {
        this.context = context;
        this.cats = cats;
    }

    @NonNull
    @Override
    public CatAdapter.CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itempic,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Model cat=cats.get(position);
        Picasso.get().load(cat.getUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public void getAllDatas(List<Model> cats)
    {
        this.cats=cats;
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView image;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageView);
        }
    }
}