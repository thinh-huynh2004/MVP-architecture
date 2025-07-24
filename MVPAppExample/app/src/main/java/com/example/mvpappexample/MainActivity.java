package com.example.mvpappexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity implements Contract.View {

    private Contract.Presenter presenter;
    private Button button;
    private TextView statusLogin;
    private TextView email;
    private TextView password;
    private TextView checkDevice;
    private Button loadImage;
    private ImageView imageView;
    private String mUrl = "https://commondatastorage.googleapis.com/codeskulptor-demos/riceracer_assets/img/car_4.png";
    private boolean clicked = false;
    String model = Build.MODEL;
    String manufacturer = Build.MANUFACTURER;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusLogin = findViewById(R.id.statusLogin);
        button = findViewById(R.id.button);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        imageView = findViewById(R.id.imageView);
        loadImage = findViewById(R.id.loadImage);
        checkDevice = findViewById(R.id.checkDevice);

        ExecutorService service = Executors.newSingleThreadExecutor();

        presenter = new Presenter(this, new Model());

        button.setOnClickListener(v ->
                presenter.checkAccount(email.getText().toString(), password.getText().toString()));

        Handler handler = new Handler(Looper.getMainLooper());

        loadImage.setOnClickListener(v -> {
            if (clicked == true) {
                clicked = false;
                imageView.setImageBitmap(null);
                loadImage.setText("Load Image");
            }
            else {
                clicked = true;
                presenter.loadImage(mUrl);
                loadImage.setText("Hide Image");
            }
        });

        if (model.contains("sdk")) {
            checkDevice.setText("virtual device");
        }
        else {
            checkDevice.setText("real device");
        }

    }

    @Override
    public void showImage(Bitmap bitmap) {
        imageView = findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onSuccess(String message) {
        statusLogin.setText(message);
    }

    @Override
    public void onError(String message) {
        statusLogin.setText(message);
    }
}