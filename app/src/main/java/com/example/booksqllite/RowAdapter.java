package com.example.booksqllite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.MyRowHolder> {
    private Activity activity;
    private Context context;
    private List<Book> listBook;

    public RowAdapter(Activity activity, Context context,List<Book> listBook){
        this.activity = activity;
        this.context = context;
        this.listBook = listBook;
    }

    @NonNull
    @Override
    public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyRowHolder(LayoutInflater.from(context).inflate(R.layout.custome_row_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
        final Book book = listBook.get(position);
        holder.book_id_txt.setText(book.getId()+"");
        holder.book_title_txt.setText(book.getTitle());
        holder.book_author_txt.setText(book.getAuthor());
        holder.book_pages_txt.setText(book.getPage()+"");

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("book",book);
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBook.size();
    }

    class MyRowHolder extends RecyclerView.ViewHolder{

        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;
        LinearLayout mainLayout;
        public MyRowHolder(@NonNull View view) {
            super(view);
            book_id_txt = view.findViewById(R.id.book_id_txt);
            book_title_txt = view.findViewById(R.id.book_title_txt);
            book_author_txt = view.findViewById(R.id.book_author_txt);
            book_pages_txt = view.findViewById(R.id.book_pages_txt);
            mainLayout = view.findViewById(R.id.mainLayout);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);

        }
    }
}
