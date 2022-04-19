package com.example.C_Learning;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Category_Adopter extends RecyclerView.Adapter<Category_Adopter.viewholder> {

    private List<Category_model> category_modelList;

    public Category_Adopter(List<Category_model> category_modelList) {
        this.category_modelList = category_modelList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catagory_item01,parent,false);
       return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.setData(category_modelList.get(position).getUrl(),category_modelList.get(position).getName(),category_modelList.get(position).getSets());
    }

    @Override
    public int getItemCount() {

        return category_modelList.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        private CircleImageView imageView02;
        private TextView title;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView02=itemView.findViewById(R.id.image_view02);
            title = itemView.findViewById(R.id.title002);
        }

        private void setData(String url, final String title, final int sets){
            Glide.with(itemView.getContext()).load(url).into(imageView02);
            this.title.setText(title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent setintent = new Intent(itemView.getContext(),SetsActivity.class);
                    setintent.putExtra("title",title);
                    setintent.putExtra("sets",sets);
                    itemView.getContext().startActivity(setintent);

                }
            });
        }
    }
}
