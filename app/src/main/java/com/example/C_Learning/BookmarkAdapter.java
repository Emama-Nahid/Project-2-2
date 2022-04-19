package com.example.C_Learning;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.Viewholder> {

    private List<QustionModel> list;

    public BookmarkAdapter(List<QustionModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_item,parent,false);
            return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.setData(list.get(position).getQustion(),list.get(position).getCorrectANS(),position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private TextView qustion , answer;
        private ImageButton deleteBtn;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            qustion=itemView.findViewById(R.id.qustion001);
            answer=itemView.findViewById(R.id.answer001);
            deleteBtn=itemView.findViewById(R.id.delete_btn);


        }

        private void setData(String qustion, String answer, final int position){
            this.qustion.setText(qustion);
            this.answer.setText(answer);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyItemRemoved(position);


                }
            });
        }
    }
}
