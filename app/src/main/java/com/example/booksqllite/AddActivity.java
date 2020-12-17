package com.example.booksqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private TextView txt_title_update,
            txt_author_update,txt_page_update;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        txt_title_update = findViewById(R.id.txt_title_update);
        txt_author_update = findViewById(R.id.txt_author_update);
        txt_page_update = findViewById(R.id.txt_page_update);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDatabaseHelper bookDatabaseHelper = new BookDatabaseHelper(AddActivity.this);
                if (txt_title_update.getText().toString().trim().length()== 0 || txt_author_update.getText().toString().trim().length()==0 ||
                txt_page_update.getText().toString().trim().length() == 0) {
                    Toast.makeText(AddActivity.this, "Empty field", Toast.LENGTH_SHORT).show();
                    return;
                }
                Book book = new Book(txt_title_update.getText().toString(),txt_author_update.getText().toString(),Integer.valueOf(txt_page_update.getText().toString()));
                boolean check = bookDatabaseHelper.addBook(book);
                if (check){
                    Toast.makeText(AddActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroyADD","CALL");
    }
}