package com.example.mvpappexample;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

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

    private class Device {
        String typeOfDevice() {return "";};
    }

    private class VirtualDevice extends Device {
        @Override
        String typeOfDevice() {
            return "Virtual device";
        }
    }

    private class RealDevice extends Device {
        @Override
        String typeOfDevice() {
            return "Real device";
        }
    }

    private class Create {
        Device typeDevice() { return null; };
        String displayTypeDevice() {
            Device type = this.typeDevice();
            return type.typeOfDevice();
        }
    }

    private class CreateVirtual extends Create {
        @Override
        Device typeDevice() {
            return new VirtualDevice();
        }
    }

    private class CreateReal extends Create {
        @Override
        Device typeDevice() {
            return new RealDevice();
        }
    }

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

        presenter = new Presenter(this, new Model());

        button.setOnClickListener(v ->
                presenter.checkAccount(email.getText().toString(), password.getText().toString()));

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
            Create virtual = new CreateVirtual();
            checkDevice.setText(virtual.displayTypeDevice());
        }
        else {
            Create real = new CreateReal();
            checkDevice.setText(real.displayTypeDevice());
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