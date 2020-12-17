package com.example.booksqllite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private RecyclerView rv_book;
    private List<Book> listBook;
    private BookDatabaseHelper bookDatabaseHelper = new BookDatabaseHelper(MainActivity.this);
    private RowAdapter rowAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        rv_book = findViewById(R.id.rv_book);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        Log.e("RECALL","REALLY");
        getAllBook();
    }

    private void getAllBook(){
        listBook = bookDatabaseHelper.getAllRow();
        Log.e("SIZE",listBook.size()+"");
        rowAdapter = new RowAdapter(MainActivity.this,this,listBook);
        rv_book.setLayoutManager(new LinearLayoutManager(this));
        rv_book.setAdapter(rowAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }
}