package com.example.javathread;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements Contract.View {
    Button readBtn, writeBtn;
    EditText input;
    TextView text;
    private String filename = "file.txt";
    TextView textView;

    private Contract.Presenter presenter;
    private PostAdapter adapter;
    private RecyclerView listComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readBtn = findViewById(R.id.readBtn);
        writeBtn = findViewById(R.id.writeBtn);
        input = findViewById(R.id.input);
        text = findViewById(R.id.text);

        presenter = new Presenter(this);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = input.getText().toString();
                File path = getApplicationContext().getFilesDir();
                presenter.writeFile(filename, content, path);
            }
        });

        readBtn.setOnClickListener(v -> {
            File path = getApplicationContext().getFilesDir();
            presenter.readFile(filename, path);
        });

        listComments = findViewById(R.id.listAPI);
        listComments.setLayoutManager(new LinearLayoutManager(this));

        List<Comment> commentList = new ArrayList<>();
        adapter = new PostAdapter(commentList);
        listComments.setAdapter(adapter);

        presenter.fetchComment();
    }

    @Override
    public void showWriteSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWriteError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showReadSuccess(String content) {
        text = findViewById(R.id.text);
        text.setText(content);
    }

    @Override
    public void showReadError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComments(List<Comment> comments) {
        adapter.setComments(comments);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



}