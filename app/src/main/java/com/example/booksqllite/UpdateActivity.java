package com.example.booksqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private TextView txt_title_update,
            txt_author_update,txt_page_update;
    private Button btn_update,btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final Book book = (Book) getIntent().getSerializableExtra("book");

        txt_title_update = findViewById(R.id.txt_title_update);
        txt_author_update = findViewById(R.id.txt_author_update);
        txt_page_update = findViewById(R.id.txt_page_update);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        setViewUpdate(book);


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDatabaseHelper bookDatabaseHelper = new BookDatabaseHelper(UpdateActivity.this);
                Book book1 = new Book(book.getId(),txt_title_update.getText().toString(),txt_author_update.getText().toString(),Integer.valueOf(txt_page_update.getText().toString()));
                boolean check = bookDatabaseHelper.updateBook(book1);
                if (check){
                    Toast.makeText(UpdateActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UpdateActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateActivity.this);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Do you want to delete this book?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BookDatabaseHelper bookDatabaseHelper = new BookDatabaseHelper(UpdateActivity.this);
                        boolean check = bookDatabaseHelper.deleteBook(book.getId()+"");
                        if (check){
                            Toast.makeText(UpdateActivity.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(UpdateActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.create().show();
            }
        });

    }

    private void setViewUpdate(Book book){
        txt_title_update.setText(book.getTitle());
        txt_author_update.setText(book.getAuthor());
        txt_page_update.setText(book.getPage()+"");
    }
}